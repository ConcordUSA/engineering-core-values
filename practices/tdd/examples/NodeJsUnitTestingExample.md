# Cool, So How do I Unit Test My Node.js Application

In this example we will be testing simple Node.js Javascript classes using jest.

While the Node.js ecosystem can be used many different ways with many different file formats available, this example is plain javascript, and avoids requiring babel or similar tools to setup.

For the sake of argument, lets assume we need a class that performs basic arithmetic and that we're going to call that class `MutableInteger`. We know that we're going to need to be able to construct our class with an initial value, retrieve the current value, and be able to add and subtract from the current value.
Since we have an idea of how we want the class to behave we're ready to write some tests.

<!-- TODO potentially refactor common setup into a beforeEach method, seemed like a lot for a intro example though-->

```javascript
const MutableInteger = require('../src/mutable-integer').default

test('current value is initialized to constructor param', () => {
    //arrange
    let expected = 5;
    let tested = new MutableInteger(expected);

    //act
    let actual = tested.getCurrentValue();

    //assert
    expect(actual).toBe(expected);
});

test('add 5 increments initial 5 to 10', () => {
    //arrange
    let expected = 10;
    let tested = new MutableInteger(5);

    //act
    tested.add(5);
    let actual = tested.getCurrentValue();

    //assert
    expect(actual).toBe(expected);
});

test('subtract 5 decrements initial 5 to 0', () => {
    //arrange
    let expected = 0;
    let tested = new MutableInteger(5);

    //act
    tested.subtract(5);
    let actual = tested.getCurrentValue();

    //assert
    expect(actual).toBe(expected);
});
```

You may notice that the test descriptions all state an expectation of a result when some action is performed. Also, the tests are setup with three sections:

* arrange: which does all the prep for the act section
* act: which performs the action described in the test's name
* assert: which verifies the expected result is observed

Now that the tests have been written we can implement the class and make sure the tests pass.
You may chose to implement the class like this:

```javascript
class MutableInteger{
    constructor(initialValue){
        this.currentValue = initialValue;
    }

    getCurrentValue() {
        return this.currentValue;
    }

    add(increment) {
        this.currentValue = this.currentValue + increment;
    }

    subtract(decrement){
        this.currentValue = this.currentValue - decrement;
    }
}

exports.default = MutableInteger
```

## Examples of Semi-Advanced Techniques

### Mocking Example

Perhaps the requirement that drove the need for the `MutableInteger` class above was a need to track a venue's remaining capacity. This `RemainingCapacity` class might need to decrement a `MutableInteger` that it holds when a party is admitted to the venue. To test that behavior, while trusting the `MutableInteger` tests we've already written, we can mock the behavior of the `MutableInteger`.

```javascript
const MutableInteger = require('../src/mutable-integer').default
const RemainingCapacity = require('../src/remaining-capacity').default

test('admit party calls MutableInteger subtract', () => {
    //arrange
    let mockSubtract = jest.fn();
    let mockMutableInteger = {subtract: mockSubtract};
    let tested = new RemainingCapacity(mockMutableInteger);
    let partySize = 12;

    //act
    tested.admitParty(partySize);

    //assert
    expect(mockSubtract.mock.calls.length).toBe(1);
});
```

With the mock we are able to verify that the mock is interacted with as we expect, but we don't actually test the behavior of `MutableInteger`.
Now that we have a test, we are ready to implement `admitParty`.

```java
class RemainingCapacity {

    constructor(spaceCapacity){
        this.remainingCapacity = spaceCapacity;
    }

    admitParty(partySize){
        this.remainingCapacity.subtract(partySize);
    }
}

exports.default = RemainingCapacity
```

Perhaps in addition to admiting a party we need to check if there is capacity in our venue for a party, or even further we may want to admit a party if and only if there is remaining capacity sufficient for that party.
Those tests could be written to not only assert a mock is interacted with in a certain way but to declare the behavior of the mock when it is called.

Let's add the tests to `RemainingCapacityTest`:

```javascript
test('admit party if allowed entry rejects parties beyond capacity', () => {
    //arrange
    let mockGetCurrentValue = jest.fn();
    let mockSubtract = jest.fn();
    let mockMutableInteger = {subtract: mockSubtract, getCurrentValue: mockGetCurrentValue};
    tested = new RemainingCapacity(mockMutableInteger);
    partySize = 12;
    mockGetCurrentValue.mockReturnValueOnce(0);

    //act
    let actual = tested.admitPartyIfAllowedEntry(12);

    //assert
    expect(actual).toBe(false);
});

test('rejecting a party does not reduce capacity', () => {
    //arrange
    let mockGetCurrentValue = jest.fn();
    let mockSubtract = jest.fn();
    let mockMutableInteger = {subtract: mockSubtract, getCurrentValue: mockGetCurrentValue};
    tested = new RemainingCapacity(mockMutableInteger);
    partySize = 12;
    mockGetCurrentValue.mockReturnValueOnce(0);

    //act
    tested.admitPartyIfAllowedEntry(12);

    //assert
    expect(mockSubtract.mock.calls.length).toBe(0);
});

test('admit party if allowed entry accepts parties within capacity', () => {
    //arrange
    let mockGetCurrentValue = jest.fn();
    let mockSubtract = jest.fn();
    let mockMutableInteger = {subtract: mockSubtract, getCurrentValue: mockGetCurrentValue};
    tested = new RemainingCapacity(mockMutableInteger);
    partySize = 12;
    mockGetCurrentValue.mockReturnValueOnce(15);

    //act
    let actual = tested.admitPartyIfAllowedEntry(12);

    //assert
    expect(actual).toBe(true);
});

test('accepting a party reduces capacity', () => {
    //arrange
    let mockGetCurrentValue = jest.fn();
    let mockSubtract = jest.fn();
    let mockMutableInteger = {subtract: mockSubtract, getCurrentValue: mockGetCurrentValue};
    tested = new RemainingCapacity(mockMutableInteger);
    partySize = 12;
    mockGetCurrentValue.mockReturnValueOnce(15);

    //act
    tested.admitPartyIfAllowedEntry(12);

    //assert
    expect(mockSubtract.mock.calls.length).toBe(1);
    expect(mockSubtract.mock.calls[0][0]).toBe(12);
});
```

An implementation of those methods to pass the tests may look like this:

```javascript
class RemainingCapacity {

    constructor(spaceCapacity){
        this.remainingCapacity = spaceCapacity;
    }

    getRemainingCapacity(){
        return this.remainingCapacity.getCurrentValue();
    }

    allowEntry(partySize){
        return this.remainingCapacity.getCurrentValue() >= partySize;
    }

    admitParty(partySize){
        this.remainingCapacity.subtract(partySize);
    }

    removeParty(partySize){
        this.remainingCapacity.add(partySize);
    }

    admitPartyIfAllowedEntry(partySize){
        if(!this.allowEntry(partySize)){
            return false;
        }
        this.admitParty(partySize);
        return true;
    }
}

exports.default = RemainingCapacity
```

#### Mocking Pitfall Example

Brittle tests are one of the biggest pitfalls while using mocks.

Maybe we additionally want the ability to enforce capacity of our space by kicking people out until we're back at capacity. There are quite a few ways we could go about setting up this test, two examples follow.

```javascript
test('enforce capacity removed excess occupants (a)', () => {
    //arrange
    let mockGetCurrentValue = jest.fn();
    let mockSubtract = jest.fn();
    let mockMutableInteger = {subtract: mockSubtract, getCurrentValue: mockGetCurrentValue};
    tested = new RemainingCapacity(mockMutableInteger);
    mockGetCurrentValue.mockReturnValueOnce(-12).mockReturnValueOnce(-12);

    //act
    tested.enforceCapacity_a();

    //assert
    expect(mockSubtract.mock.calls.length).toBe(1);
    expect(mockSubtract.mock.calls[0][0]).toBe(-12);
});

test('enforce capacity removed excess occupants (b)', () => {
    //arrange
    let mockGetCurrentValue = jest.fn();
    let mockAdd = jest.fn();
    let mockMutableInteger = {add: mockAdd, getCurrentValue: mockGetCurrentValue};
    tested = new RemainingCapacity(mockMutableInteger);
    mockGetCurrentValue.mockReturnValueOnce(-12).mockReturnValueOnce(-12);

    //act
    tested.enforceCapacity_b();

    //assert
    expect(mockAdd.mock.calls.length).toBe(1);
    expect(mockAdd.mock.calls[0][0]).toBe(12);
});
```

Getting either test to pass requires a very specific implementation of the `enforceCapacity` method, despite the fact that the end result would be the same. Therefore, both tests are quite brittle.

```javascript
enforceCapacity_a(){
    if(this.remainingCapacity.getCurrentValue() >= 0){
        return;
    }

    this.remainingCapacity.subtract(this.remainingCapacity.getCurrentValue());
}

enforceCapacity_b(){
    if(this.remainingCapacity.getCurrentValue() < 0){
        this.remainingCapacity.add(-1 * this.remainingCapacity.getCurrentValue());
    }
}
```

Alternatively, if we simply use a real `MutableInteger` instead of a mock we can reduce some of the brittleness.

```java
test('enforce capacity removed excess occupants (either)', () => {
    //arrange
    tested = new RemainingCapacity(new MutableInteger(-12));

    //act
    tested.enforceCapacity_a();
    let actual = tested.getRemainingCapacity();

    //assert
    expect(actual).toBe(0);
});
```

This tradeoff comes at the cost of independence, as we now require `MutableInteger` to be fully implemented and non-buggy. In this case, it doesn't seem like much is lost, however, in more complex classes with nested dependencies, creating a viable instance for testing can be extremely onerous.

### Parameterized Testing Example

Perhaps we want to verify that our add method on `MutableInteger` works for a wider range of values than a single test, but we don't want to write the same test over and over again with slightly different values.

```javascript
describe('parameterized test of add', () => {
    test.each([
        [1, 1, 2],
        [2, 2, 4],
        [Number.MAX_SAFE_INTEGER, Number.MIN_SAFE_INTEGER, -1]
        [0, 0, 0]
    ])('add increments initial value to expected result', 
    ({initialValue, increment, expectedString}) => {
        //arrange
        tested = new MutableInteger(initialValue);
        let expected = parseInt(expectedString);

        //act
        tested.add(increment);
        let actual = tested.getCurrentValue();

        //assert
        expect(actual).toBe(expected);
    })

});
```

This parameterized test runs four times, and makes sure that the first two arguments add up to the third argument (eg. 1 + 1 = 2).
