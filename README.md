# Engineering Core Values

This document is used to begin to aggregate the Core Values, and the subsequent practices, that Concord takes to delivery.

The core values are:

1. [Clean Code](/corevalues/cleancode/cleancode.md)
2. [Fast Feedback](/corevalues/fastfeedback/fastfeedback.md)
3. Repeatability
4. Simplicity
5. [Operational Excellence](/corevalues/operationallyready/operationally-ready.md)
6. Security

## Practices

In order to realize these values there are 8 practices that we as an organization need to agree to adhere to. These are:

### [Vertical Slicing](practices/verticalSlicing/readme.md)

Ensures that business value has been delivered at the end of every feature/story. This is as opposed to "horizontal slicing" i.e. creating two different user stories for the frontend and backend work required to deliver some business value.

### Simple Design

The simplest design is often (almost always) the best solution to a given problem. The simplest solution is the easiest to maintain and the easiest to change (assuming you have appropriate testing of course). The assertion here is that trying to "build for the future" or "build in extensibility" ends up being more costly as we cannot predict what future requirements will be. Solve for the now, iterate on future later.

### Collective Code Ownership

In collective code ownership, the entire team is responsible for the code. Everyone works together to produce a product of quality. No one individual is greater than the rest of the team members. This practice helps to reduce the need for hero work and eliminate silos of information within the team.

### [Pair Programming](practices/pairProgramming/readme.md)

Pair programming is the concept of two team members working on a single story/feature. Often, this is two developers working back and forth (there are numerous patterns/strategies to use) to deliver business value. Some high value pairs could be frontend and backend specialists. Each can help drive their specialty areas as well as help train the other. Additionally, QA and Devs can pair to ensure that their features are delivered with appropriate tests in place.

### Refactoring

Refactoring is very tightly coupled to Simple Design and TDD. TDD enables simple design, while the practice of refactoring ensures that our code bases adapt to new or changing requirements while still keeping its maintainability.

### [Test Driven Development](practices/tdd/readme.md)

Test driven development enables us to build software with a simple design and enables us to ruthlessly refactor. It also enables us to ship software quickly and frequently (umm Agile anyone??) as we have a bed of tests delivered with all new functionality. This also allows you to refactor with confidence.

### [Continuous Integration](practices/cicd/readme.md)

Continuous Integration is a software development practice where members of a team integrate their work frequently, usually each person integrates at least daily - leading to multiple integrations per day. Each integration is verified by an automated build (including test) to detect integration errors as quickly as possible. Many teams find that this approach leads to significantly reduced integration problems and allows a team to develop cohesive software more rapidly.

### Automate Repeatable Tasks

Humans are error prone; we are very bad at doing the same thing over and over. Machines, on the other hand, are very good at repeatable tasks! When working on a software project, we should strive to reduce any manual steps as much as possible. This will save time, reduce errors, but also add predictability to our software. "Works on my machine" is never an acceptable excuse! =)

## TODO: Thoughts to consider

### Review Processes?

Should we discuss things to look for or methods of reviewing code / giving feedback?

### Results Over Ceremony

Once you understand the goal, make the simplest plan on how to get there. Don't let the process hinder results.

Examples of some "ceremony smell":

- *Is this a story or a task?*
- *What should go into what sprint?*
- *Is this story 6 hours or 8?*

Avoid tendencies towards:

- Over-engineering decision flows (i.e. Jira workflows)
- Automating processes that will likely change
- Requiring tools or methods of work that are simply personal preferences

### Getting Started with the OO Workshop

#### Domain Modeling

Step 1: Open the ShoppingCartTest.java file in the test directory and notice the commented out test cases at the top of the file.
Your job is to uncomment these tests and implement the methods required for them to pass successfully.

Step 2: The following 5 tests in the ShoppingCartTest.java file need to be implemented.
These tests outline the business requirements in the test names.
Arrange the tests so that the prior conditions outlined in the `when` part of the test name are satisfied.
Then act on the required object and assert that the conditions are satisfied when the action is finished.
In some cases, you will need to implement new functionality in the class you are testing.

#### Dependency Inversion

Start by refactoring the classes in the `dependencyinversion.problem` directory to satisfy DIP.
You will need to consider how to best align your classes with this principle before implementing anything.
Afterwards, adjust the test classes to pass using the refactored classes.

#### Interface Segregation

Refactor the classes in the problem directory to adhere to the Interface Segregation Principle and make sure all the test cases are accounted for.
You will want to distinguish which functionalities belong to each child class before implementing.

#### Open Closed Principle

Your task is to refactor the OrderReport.java and OrderReportTest.java to adhere to the open closed principles.
