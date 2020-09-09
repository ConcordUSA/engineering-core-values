# Unit Tesing Best Practices

Test Driven Design is one of the ways we deliver on the Concord Engineering Core Value of Simplicity.
However, executing Test Driven Desing requires a strong foundation in various testing strategies and technologies.
This document strives to provide an overview of the why and how of Unit Testing.
While the how segments are heavily technology dependent, the reasons to test are language agnostic.

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

* [Java Example](./JavaUnitTestingExample.md)
* [Node.js Example](./NodeJsUnitTestingExample.md)

A good first step in unit testing an application is familiarizing yourself with a framework to unit test the language your application is written in. Eg. in Java, JUnit is ubiquitous and supplements like AssertJ, Mockito, and Hamcrest are very common especially in conjuction with SpringBoot.

In the linked documents a technology specific example is provided for unit testing a basic class that keeps track of an integer value along with a class which uses that tracking for the capacity of a space with limited occupancy.

As you look through the examples notice how the test cases are defined before the classes are implemented.

## Semi-Advanced Techniques and Common Pitfalls Thereof

### Mocking

Mocking allows you to declare the behavior of a dependency without actually calling the implementation of the class.
This allows you to test classes with a dependencies in relative isolation, instead trusting the unit tests of the dependent class to be valid.
With mocking we are also able to verify that the mock is interacted with as we expect without actually testing the behavior of the mocked class.
This can allow you to test and implement a class with a dependency that is still in progress, or just reduce the setup required for a method with more complex dependencies.

#### Mocking Pitfalls

Excessive use of mocking and verification of calling mocks can lead to brittle unit tests.
A test is considered brittle if trivial changes to implementation decisions in the class break the test.
Often this manifests as tests requiring mocks to be called in a particular order, when the tested operation is actually order independent.

### Parameterized Testing

Parameterized testing allows you to use the same test code for a large number of test scenarios.  This can be helpful to cover methods with many logical paths (eg. large switch statements) and for methods that don't require large amounts of setup to test. This is especially useful when testing simple transformation methods like arithmatic, string manipulation, or mapping methods.

#### Parameterized Pitfalls

For many classes parameterized tests are a poor fit.
Often the setup required for a test is too individualized for multiple runs with the same setup to be of value.
