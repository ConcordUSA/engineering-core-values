# Refactoring

Refactoring is very tightly coupled to Simple Design and TDD. TDD enables simple design, while the practice of refactoring ensures that our code bases adapt to new or changing requirements while still keeping its maintainability.

## The What and The Why

### The What

Refactoring is a systematic process for improving code without creating new functionality.

### The Why

The goal of refactoring is to remediate technical debt and transform sloppy code into [clean code](/corevalues/cleancode/cleancode.md) with a simple design.

#### Technical Debt and Causes

Technical debt reflects the implied cost of reworking code caused by choosing a quick and easy solution instead of a better more involved approach. Technical debt is one of the main reasons you would want to refactor code, with the main culprits being:

1. Business pressure

2. Lack of tests

3. Lack of interaction between team members

4. Long-term simultaneous development in several branches

5. Delayed refactoring (yes, this can lead to stacking more technical debt on top of each other)

6. Lack of compliance monitoring

## When to Refactor

1. The rule of Three:
    - When you’re doing something for the first time, just get it done.
    - When you’re doing something similar for the second time, cringe at having to repeat but do the same thing anyway.
    - When you’re doing something for the third time, start refactoring.

2. When adding a feature:
Refactoring helps you understand other people’s code. If you have to deal with someone elses dirty code, try to refactor it first. Clean code is much easier to grasp. You will improve it not only for yourself but also for those who use it after you.

3. When fixing a bug:
Bugs in code behave just like those in real life: they live in the darkest, dirtiest places in the code. Clean your code and the errors will practically discover themselves.

4. During a code review:
The code review may be the last chance to tidy up the code before it becomes available to the public.

## How to Refactor

Refactoring should be done as a series of small changes, each of which makes the existing code slightly better while still leaving the program in working order.

1. Clean the code! Reference Concord's standards for "[Clean Code](/corevalues/cleancode/cleancode.md)"

2. Do not add new functionality
    - Don’t mix refactoring and direct development of new features. Try to separate these processes at least within the confines of individual commits.

3. Ensure all existing tests pass after refactoring
There are two cases when tests can break down after refactoring:
    - You made an error during refactoring. This one is a no-brainer: go ahead and fix the error.
    - Your tests were too low-level. For example, you were testing private methods of classes. In this case, the tests are to blame. You can either refactor the tests themselves or write an entirely new set of higher-level tests

## Examples

1. Simplifying Methods
Much of refactoring is devoted to fixing poorly composed methods. In most cases, excessively long methods conceal the execution logic and make the method extremely hard to understand.

Complex method

```java
    public ShoppingCart addItemToShoppingCart(final String shoppingCartId, final CartItem item) {
        final ShoppingCart shoppingCart = this.repository.find(shoppingCartId);

        shoppingCart.getItems().add((item));

        this.repository.save(shoppingCart);

        return shoppingCart;
    }
```

Simplified method

```java
   public void addItemToShoppingCart(final SolutionCartItem item) {

        this.items.add(item);
    }
```

For full solution see the [object oriented workshop domain modeling anemic example and solution.](oo-workshop/mobprogrammingexercises/src/main/java/com/example/domainmodeling/anemic)
2. Generalization
Generalizing methods and code structure makes code more readable and flexible.

See [Liskov substitution folder](oo-workshop/mobprogrammingexercises/src/main/java/com/example/liskovsubstitution/mobprogramming) in the object oriented workshop problem and solution. Here we generalize the transportation object and then inherit from this to create a transportation object with engines and without instead of assuming all modes of transportation include engines.
