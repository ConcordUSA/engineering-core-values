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

6. ***Easier Refactoring*** - Refactoring is a key component in the process of TDD. By building your test suites, you enable 

## Examples

The [examples](examples) directory contains practical examples / guidelines for various technologies and technical domains for implementing unit testing.

Thus far:

1. Java
2. NodeJS
   ![Example of TDD Unit Tests](examples/node-ts-express-demo/node-ts-express-tdd.gif)

TODO:

1. Java - Spring Boot
2. .NET Core
3. ASP.NET Core
4. Terraform
5. JavaScript - React
6. JavaScript - Angular
etc

Open a github issue with any further requests!

## Other Resources

1. Concord Lunch and Learn on TDD, BDD, and ATDD: [TDD, BDD, and ATDD Video](https://concordusa1.sharepoint.com/Shared%20Documents/Forms/AllItems.aspx?sortField=Created&isAscending=false&id=%2FShared%20Documents%2FDelivery%20%282020%29%2FConcord%20Lunch%20%26%20Learns%2FLunch%20%26%20Learns%20%28All%29%2FLunch%20and%20Learn%20Videos%2FTDD%2C%20BDD%20ATDD%20Andrew%20Larsen%2006262020%2Emp4&parent=%2FShared%20Documents%2FDelivery%20%282020%29%2FConcord%20Lunch%20%26%20Learns%2FLunch%20%26%20Learns%20%28All%29%2FLunch%20and%20Learn%20Videos)
2. [TDD, Where Did it All Go Wrong](https://www.youtube.com/embed/EZ05e7EMOLM)
3. [Five Underplayed Premises of TDD](https://www.geepawhill.org/2018/01/18/five-underplayed-premises-of-tdd-2/)
