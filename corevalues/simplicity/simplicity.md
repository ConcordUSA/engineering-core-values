# Simplicity

Simplicity is highly valued in software development. The simplest solution is often the best solution. It is the easiest to maintain, the easiest to change and often provides extensibility and future reuse without adding complexity. A common acronym related to simplicity is KISS or Keep It Simple Stupid, and applies to many areas of the software development life cycle.

## [Simple Design](/practices/simpleDesign/readme.md)

Simple design refers to designing your code in a simple, clean and elegant way. See the informational page for simple design for more detail.

## Single Responsibility

The Single Responsibility Principle(SRP) helps keep your code simple by requiring classes, methods and tests have one responsibility and that responsibility should be completely encapsulated in that method, class or test. This reduces dependencies and complexity making code easier to understand, test and change. For examples and more information on the single responsibility principle, see the object oriented workshop [exercises and slides](/oo-workshop/mobprogrammingexercises/src/main/java/com/example/srp).

## Composition vs Inheritance

Inheritance is when one class effectively copies or inherits attributes from a parent class. Where composition describes a class that references one or more objects of other classes in instance variables. Inheritance is a helpful tool for development, but multiple inheritances can quickly complicate your code. Often it is best not to force behavior through inheritance, but rather delegate it through composition. For example, Don't subclass Car from Engine, instead structure Car such that is HAS an Engine. This is more intuitive to the human understanding of a car and is less abstract.

### Deep vs Shallow Hierarchies

A deep hierarchy includes deeply nested classes and prefers inheritance over composition. A shallow hierarchy prefers composition over inheritance and includes very few nested classes. Try to keep you hierarchy simple, it is often better to develop a shallow hierarchy than a deep hierarchy. This limits the abstractions and makes code more readable. Never create more abstractions than are currently needed, remember YAGNI -- You Aren't Going to Need IT, and you can always refactor later if you need to.

## Law of Demeter

The Law of Demeter is a guideline to avoid coupling and simply states:
Don't rely on internal structure and objects you aren't closely related to. Only send methods to objects you hold directly or objects passed in to you.

Coupling complicates code and entangles functionality making code harder to understand and test. However, coupling can not always be avoided.

### Examples

#### Removing Coupling

- aClaim.lines().sorted("ENCOUNTER_DATE").getFirst().getField("ENCOUNTER_DATE")​
  - This line makes a lot of assumptions about the structure and implementation of the Claims object. ​
  - The logic may or may not be correct (or consistent across your application). ​
  - This may or may not respond well to null values.​

- aClaim.getFirstEncounterDate()​
  - A method like this lets you evolve the structure and implementation of the Claim object without breaking code that is using your class correctly.​
  - Factoring out a method like this lets you be consistent in domain logic and handling exceptional cases.

#### Chained Function Calls

The intent of the law of Demeter is to avoid coupling not to avoid chained calls.

```java
FinancialSimulation aFinancialSimulation = new FinancialSimulationBuilder()​
  .withExpectedReturnsType("Normal-Distribution",.05,.02)​
  .withExpectedInflationType("Normal-Distribution",.03,.01)​
  .withCompoundingType("Quarterly")​
  .withDurationInYears(30)​
  .withTitle("30-Year Simulation, Baseline Assumptions")​
  .build();
```

While this appears to be a violation of the law of Demeter, it is not as long as every message send is to aFinancialSimulation.

#### Recognizing Coupling

```java
aHeader = aClaim.getHeader()​
aMember = aHeader.getMember();​
Address anAddress = aMember.getAddress();​
String zipCode = anAddress.getZipCode();​
```

This doesn't exactly look like chained calls... but clearly is. ​

- What if the Member moves from the ClaimHeader to somewhere else?​
- What if a Claim's member starts supporting multiple address types?​
- What if member address starts handling 'effective dates' for history?​    ​

Even though we aren't doing all of our nested calls in one line, we're still making structural assumptions about (coupling) the internal implementation details of Claim in our code (and probably in other places). This leads to code RIGIDITY, and maps back to a violation of SRP.​

It's less of an issue in cases where you know what you are getting is a well formed, understood object (like a Map), as opposed to something more local and nuanced (such as a ClaimsHeader).

#### Unavoidable Coupling

Sometimes a library doesn't give you a good option other than to work with 'downstream' objects. This is especially true with very multipurpose libraries and 'one stop shops' that give simple access to other foundational libraries (generally not SRP-friendly).​

In this example, Pandas (a powerful data analysis framework) gives users an easy way to graph using Matplotlib.​

```python
# import pandas as pd
# data = pd.read_fwf('myfile.log',header=None,names=['time','amount'],widths=[27,6])
# ...

axes = data.plot()
axes.axhline(30, color='red', ls=':', label='tolerance')
axes.legend()
```

This is super nice, but your code will also accrue low-level dependencies on Matplotlib objects and break with either Pandas DataFrame changes or Matplotlib changes.​
The stability and heavy use of these libraries helps offset some of the risk. To further minimize risk, we can avoid chasing further in the implementation like axes.xaxis.props.tickmarks.(…)

## Combating Complexity with Code Smells

How do you sniff out complexity? Well you can use code smells!
Code smells are indicators in the sources code of deeper issues. A great guide for sniffing out complexities in your code can be found [here](https://refactoring.guru/refactoring/smells).
