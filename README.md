# Engineering Core Values

This document is used to begin to aggregate the Core Values, and the subsequent practices, that Concord takes to delivery.

The core values are:

1. Clean Code
2. Fast Feedback
3. Repeatability
4. Simplicity
5. Operational Excellence
6. Security

## Practices

In order to realize these values there are 8 practices that we as an organization need to agree to adhere to. These are:

### Vertical Slicing

This is the idea that at the end every feature/story business value has been delivered. The opposite of this would be "horizontal slicing" i.e. creating two different user stories for the frontend and backend work required to deliver some business value.

### Simple Design

The simplest design is often (almost always) the best solution to a given problem. The simplest solution is the easiest to maintain and the easiest to change (assuming you have appropriate testing of course). The assertion here is that trying to "build for the future" or "build in extensibility" ends up being more costly as we cannot predict what future requirements will be.

### Collective Code Ownership

In collective code ownership, the entire team is responsible for the code. Everyone works together to produce a product of quality. No one individual is greater than the rest of the team members. This practice helps to reduce the need for hero work and eliminate silos of information within the team.

### Pair Programming

Pair programming is the concept of two team members working on a single story/feature. Often, this is two developers working back and forth (there are numerous patterns/strategies to use) to deliver business value. Some high value pairs could be frontend and backend specialists. Each can help drive their specialty areas as well as help train the other. Additionally, QA and Devs can pair to ensure that their features are delivered with appropriate tests in place. 

### Refactoring

Refactoring is very tightly coupled to Simple Design and TDD. TDD enables simple design, while the practice of refactoring ensures that our code bases adapt to new or changing requirements while still keeping its maintainability. 

### Test Driven Design

Test driven design enables us to build software with a simple design and enables us to ruthlessly refactor. It also enables us to ship software quickly and frequently (umm Agile anyone??) as we have a bed of tests delivered with all new functionality. 

### Continuous Integration

Continuous Integration is a software development practice where members of a team integrate their work frequently, usually each person integrates at least daily - leading to multiple integrations per day. Each integration is verified by an automated build (including test) to detect integration errors as quickly as possible. Many teams find that this approach leads to significantly reduced integration problems and allows a team to develop cohesive software more rapidly.

### Automate Repeatable Tasks

Humans are error prone; we are very bad at doing the same thing over and over. Machines, on the other hand, are very good at repeatable tasks! When working on a software project, we should strive to reduce any manual steps as much as possible. This will save time, reduce errors, but also add predictability to our software. "Works on my machine" is never an acceptable excuse! =)