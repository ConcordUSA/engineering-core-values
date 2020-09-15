# Clean Code

Clean code is the idea that software should be written in a way such that it is easy to understand and maintain.

So what are some practices that we can follow to write “clean code”?

## Meaningful names

Naming is hard in software - however it goes a long way into making code easy to understand and maintain.  When creating classes, methods, variables, etc. it’s important that the name makes it clear what it is for. For example, consider the following variable representing a list of users:

```java
private final List<User> list = new ArrayList();
```

By its instantiation it is clear that this is a list of users. However, what about if need to maintain two lists of users?

```java
private final List<User> list1 = new ArrayList();
private final List<User> list2 = new ArrayList();
```

Let's assume we need to add a list of users to another list.  With that in mind better names for the lists might be:

```java
private final List<User> currentUsers = new ArrayList();
private final List<User> usersToAdd = new ArrayList();
```

Naming can also be useful for determining if a class or method is doing too much (and thus violating the Single Responsibility Principle).  If you cannot think of a clear and concise name to describe what a method is doing, that is probably a sign it’s doing too much!

Naming consistency is also very important when writing clean code.  Picking a consistent name for an entity throughout a code base can go a long way in helping others (and yourself) improve the readability of a code base.

## Comments

The phrase “self documenting code” often makes some engineer’s skin crawl - but there is merit to this principle.  Use of comments should be used only to explain why we’re doing something in the code.  For example, a weird workaround to deal with some quirky external dependency.  Comments should never describe what code is doing.  This is a clear sign that it is overly complex, the naming is unclear, or both.

This is also important because over time the code might change, and then the code and comments 'say' different things/disagree.

## Functions

There are a number of practices to keep in mind when writing clean functions including:

1. Keep methods small
2. Do one thing
3. Small number of arguments
4. Easy to test

***Keep Methods Small***

“The first rule of functions is that they should be small. The second rule of functions is that they should be smaller than that.”

Functions should be small, do one thing, and be easy to test.

A function, generally, should be no more than a handful of lines.  There is no magic number here, I can’t tell you how small a function should be but I know a function is too large when i see it.  Some common ways to tell if a function is too long:

1. Lots of if/else statements
2. It’s hard to tell what the function does “at a glance”
3. You find yourself writing comments to explain what something is doing

If a method hits any of the above cases that often means you may want to consider factoring out pieces of the function into other smaller functions that the method in question calls.

***Do one thing***

A function should do one thing, and do it well.  Said another way, a method should have only one reason to change.  This means that a function should only be concerned with a specific area or part of a domain, which given a new or changed requirement, would require said function to be modified.  

Side effects are a common code smell that can tell us a function is not doing one thing only.  Side effects are when a method does something that is not overtly obvious.  For example, take the following method that returns the next the integer after the provided integer:

```java
private Integer getNextNumber(final Integer number){
    final Integer nextNumber = ++number;
    return nextNumber;
}
```

In the above function we return a new number that directly follows the provided number.  While this may be a contrived example, it as an interesting side effect.  This will not only return the next number, but will also increment the number passed in!  To avoid side effects the method could be re-written as:

```java
private Integer getNextNumber(final Integer number){
    final Integer nextNumber = number + 1;
    return nextNumber;
}
```

***Arguments***

A long list of arguments is often a code smell that should be avoided.  If a method has more than, say, 3 arguments it’s probably a sign that either:

1. The method is doing too much
2. There is a new Class that should be introduced to represent the data.

Another code smell relating to arguments is the idea of “flag arguments”.  These are arguments that change the behavior of the method based on a boolean value.  These can make the methods less obvious as to what they’re doing.  

***Easy to test***

Above all, functions (and of course their containing classes) should be easy to test.  Simply put - if it’s painful to setup your unit tests for a given method under test, that is a sign that the method is doing too much and should be refactored.  This pain can include setting up mocks, test data, etc.  If our classes and methods are small and “do one thing” our unit tests should not require a large amount of setup.


**Example:**

Consider the methods included in the following class

```java
public class Main {
    public static void main(String[] args) {
        printMortgage(...);
        printPaymentSchedule(...);
    }
    private static void printMortgage() {}
    private static void printPaymentSchedule() {}
    public static double readNumber() {}
    public static double calculateBalance() {}
    public static double calculateMortgage() {}
}
```

Below the class has been abstracted into classes to reflect their single responsibility.

```java
// Main
public class Main {
    public static void main(String[] args) {
        var calculator = new MortgageCalculator(...);
        var report = new MortgageReport(...);
        report.printMortgage();
        report.printPaymentSchedule();
    }
}

// Console.java
public class Console {
  public static double readNumber() {}
}

// MortgageCalculator.java
public class MortgageCalculator {
  public MortgageCalculator() {}
  public double calculateBalance() {}
  public double calculateMortgage() {}
}

// MortageReport.java
public class MortgageReport {
  public MortgageReport() {}
  public void printPaymentSchedule() {}
  public void printMortgage() {}
}
```
This process:
- increases reusability
- increases testability
- increases readability
- lowers risks for bugs

Each class can be tested and extended without affecting its consumers.