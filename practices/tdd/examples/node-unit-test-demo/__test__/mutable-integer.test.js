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
