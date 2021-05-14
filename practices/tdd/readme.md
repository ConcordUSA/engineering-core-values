# Test Driven Development

Test driven development enables us to build software with a simple design so that we can ruthlessly refactor our code to fit changing requirements and our understanding of the problem domain. It also enables us to ship software quickly and frequently (umm Agile anyone??) as we have a bed of tests delivered with all new functionality.

## The What and The Why

### The What

So, what is Test Driven Development? Wikipedia states: **​​Test-driven development (TDD) is a software development process that relies on the repetition of a very short development cycle: requirements are turned into very specific test cases, then the code is improved so that the tests pass. This is opposed to software development that allows code to be added that is not proven to meet requirements.**. In other words, TDD is process by which a developer(s) incrementally writes software by writing tests, getting those tests to pass, and refactoring. This process might looking something like:

1. Start Feature​

2. Add a test​

3. Run all tests and confirm new test fails​

4. Write smallest amount of code to get test to pass​

5. Run all tests​

6. Refactor Code (when appropriate)

7. Repeat

### The Why

The key to making this all work is to think small and simple. You can have a larger plan in mind of **how** a feature should be implemented, however, creating an appropriate test bed should be the first priority. If you have a set of tests such that when they all pass we know the code "works", then we can refactor that code and be certain it still works.

"TDD encourages simple designs and inspires confidence" - Kent Beck

So what are some of the benefits that TDD provides to make this a practice worth doing?

1. ***Codified Acceptance Criteria*** - TDD forces us to focus on test automation from the onset of any work on a feature. TDD combined with other practices such as Behavior Driven Development (BDD) and Acceptance Test Driven Development (ATDD) our teams can build a suite of tests that focus on the Acceptance Criteria for the features of our products/applications.

2. ***Documentation as Tests*** - Traditional documentation easily becomes out of date over time. However, if you have a bed of tests, that written based on the desired functionality of our products/applications, developers can use these **as** documentation of how the product/application should work. If TDD is continuously practiced, then this type of documentation will never be out of date!

3. ***Fewer Bugs*** - This one may go without saying, but it's still an important benefit of TDD. Because tests are written before code, developers ensure that the code they assume works, well..does!

4. ***Easier Debugging*** - Debugging becomes a breeze with TDD. Rather than having to spend time creating data and stepping through an IDE's debugger, TDD developers write a test (with the given conditions) that fails. Then they simply fix the bug and ensure all tests pass.

5. ***Regression Suite*** - As bugs are found, and tests are created via the debugging process, a suite of regression tests naturally gets built up. This ensures that those pesky "regression defects" don't happen!

6. ***Easier Refactoring*** - Refactoring is a key component in the process of TDD. By building your test suites, you enable yourself and your team to refactor any given implementation, while immediately having the confidence that you haven't broken anything.

7. ***Faster Velocity*** - This may seem counter-intuitive at first, since you are inherently writing more code. However, after a short amount of time, the amount of time that is saved by not having to manually test (or more importantly fix regression issues) leads to an overall increase in velocity. And the more features that are added, the greater the velocity gains will be!

## Getting Started

TDD starts with writing a test for a feature that you want to add to your code. Here are some things to keep in mind when writing these tests:

#### ***Where should the tests go?***

**Separate the implementation from the testing code.** To keep things organized, it's a good idea to have two source directories: one for the implementation code and one for the test code. For example, in a java project you would have your implementation code in src/main/java and the test code in src/test/java.

#### ***What should the names of the tests be?***

**Name test classes similarly to the classes they're testing.** An easy way to do this is to use the same name as the implementation class with "Test" at the end. So if you have a "Calculator" class, the test class would be called "CalculatorTest".

**Use descriptive names for test methods.** There are different unit test naming conventions that you can use to make it easier to understand what your tests are doing. Here are some popular naming conventions with examples:

1. test{Feature being tested}
	- testFailToWithdrawMoneyIfAccountIsInvalid
	- testApplicationIsNotAcceptedIfMandatoryFieldsAreMissing

2. When_StateUnderTest_Expect_ExpectedBehavior
	- When_InvalidAccount_Expect_WithdrawMoneyToFail
	- When_MandatoryFieldsAreMissing_Expect_ApplicationAcceptToFail

3. Given_Preconditions_When_StateUnderTest_Then_ExpectedBehavior
	- Given_UserIsAuthenticated_When_InvalidAccountNumberIsUsedToWithdrawMoney_Then_TransactionsWillFail
	- Given_MandatoryFieldsAreMissing_When_ApplicationIsSubmitted_Then_ApplicationIsNotAccepted

#### ***How should the tests be written?***

**Arrange, Act, Assert.** Use this pattern as the structure for your test. 

```
@Test
public void dummyTest(){

//arrange
This is where you set up the testing objects and prepare the prerequisites for your test.

///act
This is where the actual work of the test is performed.

//assert
This is where the result of the test is verified.

}
```

This structure can be seen in all the examples below.

**Write assertions first.** This makes the purpose of the test clear from the beginning.

**Minimize assertions in each test method.** When writing a test it's a good idea to ask yourself "If this test fails, is it obvious _why_ it failed?". When there are multiple assertions, it might not be clear which one is causing your test to fail. So in most cases, it's best to use just one assertion. 

That doesn't mean it's never a good idea to use multiple assertions, though. For example, imagine a test where you want to check that a value is equal to an expected value. You can use an assertion to check this, but there are multiple ways this assertion could fail: the value could be incorrect, or the value could not exist altogether and be null. So before asserting the value is correct, you can use another assertion to check whether the value is null. That way, if the value is null, the test immediately fails. If the value is not null, then when the second assertion fails you can know with confidence that it failed because the value was incorrect. An example of how you would code this in java is below.

```java
@Test
public void dummyTest{

//arrange

//act

//assert
assertNotNull(actual);
assertThat(actual).isEqualTo(expected);

}
```

**Use mocks when appropriate.** In testing, mocking replicates the behavior of a service, object, or process. This can be useful when the System Under Test (SUT) has dependencies on external resources such as databases. Mocking those external dependencies makes it so that you're just testing the SUT and not the external dependencies. This means that if the test fails, you can know that it's happening inside the SUT and not elsewhere. Mocking external resources can also speed up tests because it eliminates the latency in communication with those resources. There are many mocking libraries for a variety of languages and frameworks. You can see how these are implemented in the examples below.

**Avoid coupling tests to implementation details.** A key part of TDD is that your tests shouldn't break when you refactor because you are testing _behaviors_ and not implementation details. If you refactor your code and find that tests start breaking even though the behaviors haven't changed, this is a sign that your tests are coupled to the implementation details. The use of mocks can cause this if you're not careful.

**Tests should run fast.** Fast feedback is critical to TDD being successful. The flow of TDD is broken if you have to wait a few seconds every time you try to run a test. If a test is running slow, make sure it's not doing too much. And, as mentioned above, mocking can also help speed up tests if the SUT has external dependencies.

**Do not introduce dependencies between tests.** Every test should be independent, and they should not have to be performed in a specific order. When tests are dependent on each other, adding new tests can easily break the existing tests.

#### ***How should the implementation code be written?***

**Write the simplest code to pass the test.** The simpler it is, the easier it will be to understand and maintain.

#### ***Okay, but how should the test and implementation code be written with technology / language X, Y, Z?***

See the examples below!

## Examples

The [examples](examples) directory contains practical examples / guidelines for various technologies and technical domains for implementing unit testing.

Thus far:

1. Java
2. NodeJS
   ![Example of TDD Unit Tests](examples/node-ts-express-demo/node-ts-express-tdd.gif)
3. NodeJS + TS Express
4. Dot Net Core

TODO:

1. Java - Spring Boot
2. ASP.NET Core
3. Terraform
4. JavaScript - React
5. JavaScript - Angular
etc

Open a github issue with any further requests!

## Other Resources

1. Concord Lunch and Learn on TDD, BDD, and ATDD: [TDD, BDD, and ATDD Video](https://concordusa1.sharepoint.com/Shared%20Documents/Forms/AllItems.aspx?sortField=Created&isAscending=false&id=%2FShared%20Documents%2FDelivery%20%282020%29%2FConcord%20Lunch%20%26%20Learns%2FLunch%20%26%20Learns%20%28All%29%2FLunch%20and%20Learn%20Videos%2FTDD%2C%20BDD%20ATDD%20Andrew%20Larsen%2006262020%2Emp4&parent=%2FShared%20Documents%2FDelivery%20%282020%29%2FConcord%20Lunch%20%26%20Learns%2FLunch%20%26%20Learns%20%28All%29%2FLunch%20and%20Learn%20Videos)
2. [TDD, Where Did it All Go Wrong](https://www.youtube.com/embed/EZ05e7EMOLM)
3. [Five Underplayed Premises of TDD](https://www.geepawhill.org/2018/01/18/five-underplayed-premises-of-tdd-2/)
