# Refactoring

Refactoring mutually benefits both [Simple Design](/practices/simpleDesign/readme.md) and [TDD](/practices/refactoring/readme.md). TDD enables simple design, while the practice of refactoring ensures that our code bases adapt to new or changing requirements while still keeping its maintainability.

## The What and The Why

### The What

Refactoring is a systematic process for improving code without changing existing functionality.

### The Why

The goal of refactoring is to remediate technical debt and transform sloppy code into [clean code](/corevalues/cleancode/cleancode.md) with a [simple design](/practices/simpleDesign/readme.md).

#### Technical Debt and Causes

Technical debt reflects the implied cost of reworking code caused by choosing a quick and easy solution instead of a better more involved approach. Technical debt is one of the main reasons you would want to refactor code, with the main culprits being:

- Business pressure
- Lack of tests
- Lack of interaction between team members
- Long-term simultaneous development in several branches
- Lack of compliance monitoring

## When to Refactor

- Following Red-Green-Refactor in TDD:
  - Red: create a failing test
  - Green: create a passing test the simplest and quickest way possible, breaking clean code or other standards if necessary.
  - Refactor: clean up the code and implementation to follow SOLID principles and fit Concord's guidelines for [clean code](/corevalues/cleancode/cleancode.md) and [simple design](/practices/simpleDesign/readme.md).
- When adding a feature:
Refactoring helps you understand other people’s code. If you have to deal with someone elses dirty code, try to refactor it first. Clean code is much easier to grasp. You will improve it not only for yourself but also for those who use it after you.
- When fixing a bug:
Bugs in code behave just like those in real life: they live in the darkest, dirtiest places in the code. Clean your code and the errors will practically discover themselves.

## How to Refactor

Refactoring should be done as a series of small changes, each of which makes the existing code slightly better while still leaving the program in working order.

1. Clean the code! Reference Concord's standards for "[Clean Code](/corevalues/cleancode/cleancode.md)"
2. Do not add new functionality
    - Don’t mix refactoring and direct development of new features. Try to separate these processes at least within the confines of individual commits.
3. Ensure all existing tests pass after refactoring
There are two cases when tests can break down after refactoring:
    - You made an error during refactoring. This one is a no-brainer: go ahead and fix the error.
    - Your tests were too low-level meaning you are testing implementation details in addition to or instead of functionality.

## Examples

### Simplifying

Simplifying can be done at multiple levels: entire system level, application level, class or method level or even expressions. For guidelines on reducing complexity check out the core value of [Simplicity.](/corevalues/simplicity/simplicity.md)

#### Simplifying methods

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

For the full solution see the [object oriented workshop domain modeling anemic example and solution.](oo-workshop/mobprogrammingexercises/src/main/java/com/example/domainmodeling/anemic)

### Generalization

Generalizing methods and code structure can make code more readable and flexible.

See [Liskov substitution folder](oo-workshop/mobprogrammingexercises/src/main/java/com/example/liskovsubstitution/mobprogramming) in the object oriented workshop problem and solution. Here we generalize the transportation object and then inherit from this to create a transportation object with engines and without, instead of assuming all modes of transportation include engines.
