# Simple Design

The simplest design is often (almost always) the best solution to a given problem. The simplest solution is the easiest to maintain and the easiest to change (assuming you have appropriate testing of course). The assertion here is that trying to "build for the future" or "build in extensibility" ends up being more costly as we cannot predict what future requirements will be. Solve for the now, iterate on future later.

## The What and the Why

### The What

Simple design refers to designing your code in a simple, clean and elegant way, but not necessarily simplistic. The following are guidelines to simple design:

1. "You Aren't Gonna Need It"(YAGNI): Design details should be implemented when you need them, not when you foresee you will need them.
2. Avoid duplication: duplicate code is hard to read, understand and and maintain.
3. Self documenting: Readable and clean code makes the code self documenting and lessens the need to maintain documentation in the code. Descriptive naming and a clean layout are key factors in self documenting.
4. Fail Fast: Test the limits of your design to determine where it will fail you and when improvements need to be made.
5. Minimize the number of components: use the least number of classes and methods to achieve what you need.

### The Why

Simple design increases efficiency by only implementing what is necessary, and makes code easier to maintain and change.

## Resources

1. [Simple Design Principles](https://www.jamesshore.com/v2/books/aoad1/simple_design)
2. [What is Simple Design?](https://www.agilealliance.org/glossary/simple-design/)