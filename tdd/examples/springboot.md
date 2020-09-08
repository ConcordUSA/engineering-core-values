# Unit Tesing Best Practices

Test Driven Design is one of the ways we deliver on the Concord Engineering Core Value of Simplicity.
However, executing Test Driven Desing requires a strong foundation in various testing strategies and technologies.
This document strives to provide an overview of the why and how of Unit Testing.
For the time being the how segments are heavily focused on Java with Spring Boot, however, the reasons to test are language agnostic.

## Why Write Unit Tests

Unit tests allow us to make changes to a class, confident that the changes have not broken any core functionality of the class.
When coupled with the Test Driven Design principle of Test First Development, unit tests allow us to confidently deliver code, while building a regression suite for any future changes.
The larger and more complex your class (or the application the class is a part of) the more beneficial unit tests are, though the cost of adding unit tests to an existing project increases with the same parameters.
For this reason, it is always suggested to start writing unit tests now, when the cost is lowest.

### What Should I Unit Test

In general every method you write and expose should be unit tested, and every logical branch of that method should be covered by at least one test.

### What Should I Leave Out of My Unit Tests

This is often the more difficult question to answer, however, it is just as important.
In general don't unit test things that aren't the responsibility of the class under test.

If you find yourself writing unit tests for any of the following, it may be a good idea to take a step back and either find a more appropriate method to test the functionality or reconsider the value of the test.

* IDE generated methods
* Framework functionality utilized within your code
* Methods of other classes within your application
* Private methods or methods which are not exposed

## Cool, So How do I Unit Test My Application

A good first step in unit testing an application is familiarizing yourself with a framework to unit test the language your application is written in. Eg. in Java, JUnit is ubiquitous and supplements like AssertJ, Mockito, and Hamcrest are very common especially in conjuction with SpringBoot.

For the sake of argument, lets assume we need a class that performs basic arithmetic and that we're going to call that class `MutableInteger`. We know that we're going to need to be able to construct our class with an initial value, retrieve the current value, and be able to add and subtract from the current value.
Since we have an idea of how we want the class to behave we're ready to write some tests.

<!-- TODO not sure if the package/imports are useful here, left them to avoid confusion but they definitely clutter things up a bit -->
<!-- TODO potentially refactor common setup into a @BeforeEach method, seemed like a lot for a intro example though-->

```java
package com.concordusa.unittestdemo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class MutableIntegerTest {

    private MutableInteger tested;

    @Test
    public void whenMutableIntegerConstructed_CurrentValueIsEqualToConstructorParameter(){
        //arrange
        Integer expected = 5;
        tested = new MutableInteger(expected);

        //act
        Integer actual = tested.getCurrentValue();

        //assert
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void whenAdd_CurrentValueIsIncremented(){
        //arrange
        Integer expected = 10;
        tested = new MutableInteger(5);

        //act
        tested.add(5);
        Integer actual = tested.getCurrentValue();

        //assert
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void whenSubtract_CurrentValueIsDecrementedParameter(){
        //arrange
        Integer expected = 0;
        tested = new MutableInteger(5);

        //act
        tested.subtract(5);
        Integer actual = tested.getCurrentValue();

        //assert
        assertThat(actual).isEqualTo(expected);
    }
}
```

You may notice that the test names all state an expectation of a result when some action is performed. Also, the tests are setup with three sections:

* arrange: which does all the prep for the act section
* act: which performs the action described in the test's name
* assert: which verifies the expected result is observed

Now that the tests have been written we can implement the class and make sure the tests pass.
You may chose to implement the class like this:

```java
package com.concordusa.unittestdemo.service;

public class MutableInteger {

    private Integer currentValue;

    public MutableInteger(final Integer initialValue) {
        currentValue = initialValue;
    }

    public Integer getCurrentValue() {
        return currentValue;
    }

    public void add(final Integer increment) {
        currentValue = currentValue + increment;
    }

    public void subtract(final Integer decrement){
        currentValue = currentValue - decrement;
    }
}
```

## Semi-Advanced Techniques and Common Pitfalls Thereof

### Mocking

Mocking allows you to declare the behavior of a dependency without actually calling the implementation of the class. This allows you to test classes with a dependencies in relative isolation, instead trusting the unit tests of the dependent class to be valid.

#### Mocking Example

Perhaps the requirement that drove the need for the `MutableInteger` class above was a need to track a venue's remaining capacity. This `RemainingCapacity` class might need to decrement a `MutableInteger` that it holds when a party is admitted to the venue. To test that behavior, while trusting the `MutableInteger` tests we've already written, we can mock the behavior of the `MutableInteger`.

```java
package com.concordusa.unittestdemo.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class RemainingCapacityTest {

    private RemainingCapacity tested;
    private int partySize;

    @Test
    public void whenAdmitParty_CapacityReducedByPartySize(){
        //arrange
        MutableInteger mockMutableInteger = Mockito.mock(MutableInteger.class);
        tested = new RemainingCapacity(mockMutableInteger);
        partySize = 12;

        //act
        tested.admitParty(partySize);

        //assert
        Mockito.verify(mockMutableInteger, Mockito.times(1)).subtract(eq(12));
    }
```

With the mock we are able to verify that the mock is interacted with as we expect, but we don't actually test the behavior of the mocked class.
This can allow you to test and implement a class with a dependency that is still in progress, or just to reduce the setup required for your method with more complex dependencies.
Now that we have a test we are ready to implement the method.

```java
package com.concordusa.unittestdemo.service;

public class RemainingCapacity {
    private final MutableInteger remainingCapacity;

    RemainingCapacity(final MutableInteger spaceCapacity){
        remainingCapacity = spaceCapacity;
    }

    public void admitParty(final Integer partySize){
        remainingCapacity.subtract(partySize);
    }
}
```

Perhaps in addition to admiting a party we need to check if there is capacity in our venue for a party, or even further we may want to admit a party if and only if there is remaining capacity sufficient for that party.
Those tests could be written to not only assert a mock is interacted with in a certain way but to declare the behavior of the mock when it is called.

```java
package com.concordusa.unittestdemo.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class RemainingCapacityTest {

    private RemainingCapacity tested;
    private int partySize;

    @Test
    public void whenAdmitParty_CapacityReducedByPartySize(){
        //arrange
        MutableInteger mockMutableInteger = Mockito.mock(MutableInteger.class);
        tested = new RemainingCapacity(mockMutableInteger);
        partySize = 12;

        //act
        tested.admitParty(partySize);

        //assert
        Mockito.verify(mockMutableInteger, Mockito.times(1)).subtract(eq(12));
    }

    @Test
    public void whenAdmitPartyIfAllowedEntry_PartySizesExceedingCapacityRejected(){
        //arrange
        MutableInteger mockMutableInteger = Mockito.mock(MutableInteger.class);
        tested = new RemainingCapacity(mockMutableInteger);
        partySize = 12;
        when(mockMutableInteger.getCurrentValue()).thenReturn(0);

        boolean actual = tested.admitPartyIfAllowedEntry(12);

        assertThat(actual).isEqualTo(false);
    }

    @Test
    public void whenAdmitPartyIfAllowedEntry_RejectedPartiesDoNotAffectRemainingCapacity(){
        //arrange
        MutableInteger mockMutableInteger = Mockito.mock(MutableInteger.class);
        tested = new RemainingCapacity(mockMutableInteger);
        partySize = 12;
        when(mockMutableInteger.getCurrentValue()).thenReturn(0);

        //act
        tested.admitPartyIfAllowedEntry(12);

        //assert
        verify(mockMutableInteger, times(0)).subtract(any());
    }

    @Test
    public void whenAdmitPartyIfAllowedEntry_PartiesWithinCapacityAllowedEntry(){
        //arrange
        MutableInteger mockMutableInteger = Mockito.mock(MutableInteger.class);
        tested = new RemainingCapacity(mockMutableInteger);
        partySize = 12;
        when(mockMutableInteger.getCurrentValue()).thenReturn(15);

        boolean actual = tested.admitPartyIfAllowedEntry(12);

        assertThat(actual).isEqualTo(true);
    }

    @Test
    public void whenAdmitPartyIfAllowedEntry_AcceptedPartiesDoAffectRemainingCapacity(){
        //arrange
        MutableInteger mockMutableInteger = Mockito.mock(MutableInteger.class);
        tested = new RemainingCapacity(mockMutableInteger);
        partySize = 12;
        when(mockMutableInteger.getCurrentValue()).thenReturn(15);

        //act
        tested.admitPartyIfAllowedEntry(12);

        //assert
        verify(mockMutableInteger, times(1)).subtract(eq(12));
    }
}
```

An implementation of those methods to pass the tests may look like this:

```java
package com.concordusa.unittestdemo.service;

public class RemainingCapacity {
    private final MutableInteger remainingCapacity;

    RemainingCapacity(final MutableInteger spaceCapacity){
        remainingCapacity = spaceCapacity;
    }

    public Integer getRemainingCapacity(){
        return remainingCapacity.getCurrentValue();
    }

    public boolean allowEntry(final Integer partySize){
        return remainingCapacity.getCurrentValue() >= partySize;
    }

    public void admitParty(final Integer partySize){
        remainingCapacity.subtract(partySize);
    }

    public void removeParty(final Integer partySize){
        remainingCapacity.add(partySize);
    }

    public boolean admitPartyIfAllowedEntry(final Integer partySize){
        if(!allowEntry(partySize)){
            return false;
        }
        admitParty(partySize);
        return true;
    }
}
```

#### Mocking Pitfalls

Excessive use of mocking and verification of calling mocks can lead to brittle unit tests.
A test is considered brittle if trivial changes to implementation decisions in the class break the test.

Maybe we additionally want the ability to enforce capacity of our space and kick people out until we're back at capacity. There are quite a few ways we could go about setting up this test, two examples follow.

```java
@Test
public void whenEnforceCapacityRequiresSpaceItIsAdded_a(){
    //arrange
    MutableInteger mockMutableInteger = Mockito.mock(MutableInteger.class);
    tested = new RemainingCapacity(mockMutableInteger);
    when(mockMutableInteger.getCurrentValue()).thenReturn(-12);

    tested.enforceCapacity_a();

    verify(mockMutableInteger, times(1)).subtract(-12);
}

@Test
public void whenEnforceCapacityRequiresSpaceItIsAdded_b(){
    //arrange
    MutableInteger mockMutableInteger = Mockito.mock(MutableInteger.class);
    tested = new RemainingCapacity(mockMutableInteger);
    when(mockMutableInteger.getCurrentValue()).thenReturn(-12);

    tested.enforceCapacity_b();

    verify(mockMutableInteger, times(1)).add(12);
}
```

Getting either test to pass requires a very specific implementation of the `enforceCapacity` method, despite the fact that the end result would be the same.

```java
public void enforceCapacity_a(){
    if(remainingCapacity.getCurrentValue() >= 0){
        return;
    }

    remainingCapacity.subtract(remainingCapacity.getCurrentValue());
}

public void enforceCapacity_b(){
    if(remainingCapacity.getCurrentValue() < 0){
        remainingCapacity.add(-1 * remainingCapacity.getCurrentValue());
    }
}
```

Alternatively, if we simply use a real `MutableInteger` instead of a mock we reduce some of the brittleness.

```java
@Test
public void whenEnforceCapacityRequiresSpaceItIsAdded_either(){
    //arrange
    tested = new RemainingCapacity(new MutableInteger(-12));

    //act
    tested.enforceCapacity_a();

    //assert
    assertThat(tested.getRemainingCapacity()).isEqualTo(0);
}
```

This tradeoff comes at the cost of independence, as we now require `MutableInteger` to be fully implemented and non-buggy. In this case, it doesn't seem like much is lost, however, in more complex classes with nested dependencies creating a viable instance for testing can be extremely onerous.

### Parameterized Testing

Parameterized testing allows you to use the same test code for a large number of test scenarios.  This can be helpful to cover methods with many logical paths (eg. large switch statements) and for methods that don't require large amounts of setup to test. This is especially useful when testing simple transformation methods like arithmatic, string manipulation, or mapping methods.

#### Parameterized Example

Perhaps we want to verify that our add method on `MutableInteger` works for a wider range of values than a single test, but we don't want to write the same test over and over again with slightly different values.

```java
@ParameterizedTest
@MethodSource("additionArguments")
public void whenAdd_CurrentValueIncrementsByExpectedAmount(Integer initialValue, Integer increment, Integer expectation){
    //arrange
    tested = new MutableInteger(initialValue);

    //act
    tested.add(increment);

    //assert
    assertThat(tested.getCurrentValue()).isEqualTo(expectation);
}

public static List<Arguments> additionArguments(){
    return List.of(
            Arguments.of(1, 1, 2),
            Arguments.of(2, 2, 4),
            Arguments.of(Integer.MIN_VALUE, Integer.MAX_VALUE, -1),
            Arguments.of(0, 0, 0)
    );
}
```

This parameterized test runs four times, and makes sure that the first two arguments add up to the third argument (eg. 1 + 1 = 2).

#### Parameterized Pitfalls

For many classes parameterized tests are a poor fit.
Often the setup required for a test is too individualized for multiple runs with the same setup to be of value.