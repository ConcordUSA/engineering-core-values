# Refactoring

Refactoring is very tightly coupled to Simple Design and TDD. TDD enables simple design, while the practice of refactoring ensures that our code bases adapt to new or changing requirements while still keeping its maintainability. To put in simple terms refactoring is a systematic process of improving code without creating new functionality that can transform a mess into clean code and simple design.

## The What and The Why

###  But what is "clean code" anyway?

1. Clean code is obvious for other programmers.
And I’m not talking about super sophisticated algorithms. Poor variable naming, bloated classes and methods, magic numbers -you name it- all of that makes code sloppy and difficult to grasp.

2. Clean code doesn’t contain duplication.
Each time you have to make a change in a duplicate code, you have to remember to make the same change to every instance. This increases the cognitive load and slows down the progress.

3. Clean code contains a minimal number of classes and other moving parts.
Less code is less stuff to keep in your head. Less code is less maintenance. Less code is fewer bugs. Code is liability, keep it short and simple.

4. Clean code passes all tests.
You know your code is dirty when only 95% of your tests passed. You know you’re screwed when you test coverage is 0%.

5. Clean code is easier and cheaper to maintain!

### Technical debt and causes

Technical debt is one of the main reasons you would want to refactor code, with the main cultrips being:

1. Business pressure

2. Lack of understanding of the consequences of technical debt

3. Failing to combat the strict coherence of components

4. Lack of tests

5. Lack of documentation

6. Lack of interaction between team members

7. Long-term simultaneous development in several branches

8. Delayed refactoring (yes, this can lead to stacking more technical debt ontop of each other)

9. Lack of compliance monitoring

## When to do it

1. The rule of Three
	When you’re doing something for the first time, just get it done.
	When you’re doing something similar for the second time, cringe at having to repeat but do the same thing anyway.
	When you’re doing something for the third time, start refactoring.
2. When adding a feature
Refactoring helps you understand other people’s code. If you have to deal with someone else’s dirty code, try to refactor it first. Clean code is much easier to grasp. You will improve it not only for yourself but also for those who use it after you.

3. When fixing a bug
Bugs in code behave just like those in real life: they live in the darkest, dirtiest places in the code. Clean your code and the errors will practically discover themselves.

4. During a code review
The code review may be the last chance to tidy up the code before it becomes available to the public.


## How to do it

Refactoring should be done as a series of small changes, each of which makes the existing code slightly better while still leaving the program in working order.

1. The code should become cleaner.
	refer to "Clean Code"
2. New functionality shouldn’t be created during refactoring.
	Don’t mix refactoring and direct development of new features. Try to separate these processes at least within the confines of individual commits.
3. All existing tests must pass after refactoring.

	There are two cases when tests can break down after refactoring:
	You made an error during refactoring. This one is a no-brainer: go ahead and fix the error.
	Your tests were too low-level. For example, you were testing private methods of classes.

In this case, the tests are to blame. You can either refactor the tests themselves or write an entirely new set of higher-level tests

## Examples (TODO)

1. Composing Methods
	Much of refactoring is devoted to correctly composing methods. In most cases, excessively long methods are the root of all evil. The vagaries of code inside these methods conceal the execution logic and make the method extremely hard to understand—and even harder to change.
	TODO
	TODO
	TODO
2. Moving features between objects
3. Simplifying Expressions
4. Symplifying Method calls
5. Generalization