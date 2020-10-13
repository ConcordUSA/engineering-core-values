# How do I Unit Test My .NET Core Application

In this example we will be testing simple .NET Core classes using the NUnit testing framework and Moq mocking library.

For the sake of argument, lets assume we need a class that performs basic arithmetic and that we're going to call that class `MutableInteger`. We know that we're going to need to be able to construct our class with an initial value, retrieve the current value, and be able to add and subtract from the current value.
Since we have an idea of how we want the class to behave we're ready to write some tests.

```csharp
using NUnit.Framework;

namespace UnitTestDemo
{
    public class MutableIntegerTests
    {
        private MutableInteger _tested;

        [Test]
        public void WhenMutableIntegerConstructed_CurrentValueIsEqualToConstructorParameter()
        {
            //arrange
            int expected = 5;
            _tested = new MutableInteger(expected);

            //act
            int actual = _tested.GetCurrentValue();

            //assert
            Assert.AreEqual(expected, actual);
        }

        [Test]
        public void WhenAdd_CurrentValueIsIncremented()
        {
            //arrange
            int expected = 10;
            _tested = new MutableInteger(5);

            //act
            _tested.Add(5);
            int actual = _tested.GetCurrentValue();

            //assert
            Assert.AreEqual(expected, actual);
        }

        [Test]
        public void WhenSubtract_CurrentValueIsDecrementedParameter()
        {
            //arrange
            int expected = 0;
            _tested = new MutableInteger(5);

            //act
            _tested.Subtract(5);
            int actual = _tested.GetCurrentValue();

            //assert
            Assert.AreEqual(expected, actual);
        }
    }
}
```

You may notice that the test names all state an expectation of a result when some action is performed. Also, the tests are setup with three sections:

* arrange: which does all the prep for the act section
* act: which performs the action described in the test's name
* assert: which verifies the expected result is observed

Now that the tests have been written we can implement the class and make sure the tests pass.
You may chose to implement the class like this:

```csharp
namespace UnitTestDemo
{
    public class MutableInteger
    {
        private int currentValue;

        public MutableInteger()
        {
            currentValue = 0;
        }

        public MutableInteger(int initialValue) 
        {
            currentValue = initialValue;
        }

        public int GetCurrentValue() 
        {
            return currentValue;
        }

        public void Add(int increment) 
        {
            currentValue = currentValue + increment;
        }

        public void Subtract(int decrement)
        {
            currentValue = currentValue - decrement;
        }
    }
}
```

## Examples of Semi-Advanced Techniques

### Mocking Example

Perhaps the requirement that drove the need for the `MutableInteger` class above was a need to track a venue's remaining capacity. This `RemainingCapacity` class might need to decrement a `MutableInteger` that it holds when a party is admitted to the venue. To test that behavior, while trusting the `MutableInteger` tests we've already written, we can mock the behavior of the `MutableInteger`.

```csharp
using Moq;
using NUnit.Framework;

namespace UnitTestDemo
{
    public class RemainingCapacityTests
    {
        private RemainingCapacity tested;
        private int partySize;

        [SetUp]
        public void Setup()
        {
        }        

        [Test]
        public void WhenAdmitParty_CapacityReducedByPartySize()
        {
            //arrange
            Mock<MutableInteger> mockMutableInteger = new Mock<MutableInteger>();
            mockMutableInteger.Setup(mock => mock.Subtract(It.IsAny<int>()));
            tested = new RemainingCapacity(mockMutableInteger.Object);
            partySize = 12;

            //act
            tested.AdmitParty(partySize);

            //assert
            mockMutableInteger.Verify(mock => mock.Subtract(12), Times.Once());
        }
    }
}
```

With the mock we are able to verify that the mock is interacted with as we expect, but we don't actually test the behavior of `MutableInteger`.

**IMPORTANT**
When using the Moq framework, Moq requires that you allow for overriding methods that need to be mocked during testing. A couple different ways of doing this would be:
1. Create an Interface for the class that you would like to mock that would allow Moq to use a mock implementation for the method
```csharp
namespace UnitTestDemo
{
    public interface IMutableInteger
    {
        int GetCurrentValue();

        void Add(int increment);

        void Subtract(int decrement);
    }
}
```
Your mock then becomes:
```csharp
Mock<IMutableInteger> mockMutableInteger = Mock<IMutableInteger>();
```

1. Use `virtual` in the method's signature to allow mock to override the concrete implementation of the method.
```csharp
namespace UnitTestDemo
{
    public class MutableInteger
    {
        private int currentValue;

        public MutableInteger()
        {
            currentValue = 0;
        }

        public MutableInteger(int initialValue) 
        {
            currentValue = initialValue;
        }

        public virtual int GetCurrentValue() 
        {
            return currentValue;
        }

        public virtual void Add(int increment) 
        {
            currentValue = currentValue + increment;
        }

        public virtual void Subtract(int decrement)
        {
            currentValue = currentValue - decrement;
        }
    }
}
```

Now that we have a test, we are ready to implement `AdmitParty()`.

```csharp
namespace UnitTestDemo
{
    public class RemainingCapacity
    {
        private MutableInteger remainingCapacity;

        public RemainingCapacity(MutableInteger spaceCapacity)
        {
            remainingCapacity = spaceCapacity;
        }

        public void AdmitParty(int partySize)
        {
            remainingCapacity.Subtract(partySize);
        }
    }
}
```

Perhaps in addition to admiting a party we need to check if there is capacity in our venue for a party, or even further we may want to admit a party if and only if there is remaining capacity sufficient for that party.
Those tests could be written to not only assert a mock is interacted with in a certain way but to declare the behavior of the mock when it is called.

Let's add the tests to `RemainingCapacityTest`:

```csharp
[Test]
public void WhenAdmitPartyIfAllowedEntry_PartySizesExceedingCapacityRejected()
{
    // arrange
    Mock<MutableInteger> mockMutableInteger = new Mock<MutableInteger>();
    mockMutableInteger.Setup(mock => mock.GetCurrentValue()).Returns(0);
    tested = new RemainingCapacity(mockMutableInteger.Object);
    partySize = 12;
    
    // act
    bool actual = tested.AdmitPartyIfAllowedEntry(partySize);

    // assert
    Assert.IsFalse(actual);
}

[Test]
public void WhenAdmitPartyIfAllowedEntry_RejectedPartiesDoNotAffectRemainingCapacity()
{
    //arrange
    Mock<MutableInteger> mockMutableInteger = new Mock<MutableInteger>();
    mockMutableInteger.Setup(mock => mock.GetCurrentValue()).Returns(0);
    tested = new RemainingCapacity(mockMutableInteger.Object);
    partySize = 12;

    //act
    tested.AdmitPartyIfAllowedEntry(12);

    //assert
    mockMutableInteger.Verify(mock => mock.Subtract(It.IsAny<int>()), Times.Never());
}

[Test]
public void WhenAdmitPartyIfAllowedEntry_PartiesWithinCapacityAllowedEntry()
{
    //arrange
    Mock<MutableInteger> mockMutableInteger = new Mock<MutableInteger>();
    mockMutableInteger.Setup(mock => mock.GetCurrentValue()).Returns(15);
    tested = new RemainingCapacity(mockMutableInteger.Object);
    partySize = 12;

    bool actual = tested.AdmitPartyIfAllowedEntry(partySize);

    Assert.IsTrue(actual);
}

[Test]
public void WhenAdmitPartyIfAllowedEntry_AcceptedPartiesDoAffectRemainingCapacity()
{
    //arrange
    Mock<MutableInteger> mockMutableInteger = new Mock<MutableInteger>();
    mockMutableInteger.Setup(mock => mock.GetCurrentValue()).Returns(15);
    tested = new RemainingCapacity(mockMutableInteger.Object);
    partySize = 12;

    //act
    tested.AdmitPartyIfAllowedEntry(partySize);

    //assert
    mockMutableInteger.Verify(mock => mock.Subtract(12), Times.Once());      
}
```

An implementation of those methods to pass the tests may look like this:

```csharp
namespace UnitTestDemo
{
    public class RemainingCapacity
    {
        private MutableInteger _remainingCapacity;

        public RemainingCapacity(MutableInteger spaceCapacity)
        {
            _remainingCapacity = spaceCapacity;
        }

        public int GetRemainingCapacity()
        {
            return _remainingCapacity.GetCurrentValue();
        }

        public bool AllowEntry(int partySize)
        {
            return _remainingCapacity.GetCurrentValue() >= partySize;
        }

        public void AdmitParty(int partySize)
        {
            _remainingCapacity.Subtract(partySize);
        }

        public void RemoveParty(int partySize)
        {
            _remainingCapacity.Add(partySize);
        }

        public bool AdmitPartyIfAllowedEntry(int partySize)
        {
            if(!AllowEntry(partySize))
            {
                return false;
            }
            AdmitParty(partySize);
            return true;
        }

        public void EnforceCapacity_A()
        {
            if(_remainingCapacity.GetCurrentValue() >= 0)
            {
                return;
            }

            _remainingCapacity.Subtract(_remainingCapacity.GetCurrentValue());
        }

        public void EnforceCapacity_B()
        {
            if(_remainingCapacity.GetCurrentValue() < 0)
            {
                _remainingCapacity.Add(-1 * _remainingCapacity.GetCurrentValue());
            }
        }
    }
}
```

#### Mocking Pitfall Example

Brittle tests are one of the biggest pitfalls while using mocks.

Maybe we additionally want the ability to enforce capacity of our space by kicking people out until we're back at capacity. There are quite a few ways we could go about setting up this test, two examples follow.

```csharp
[Test]
public void WhenEnforceCapacityRequiresSpaceItIsAdded_A()
{
    //arrange
    Mock<MutableInteger> mockMutableInteger = new Mock<MutableInteger>();
    mockMutableInteger.Setup(mock => mock.GetCurrentValue()).Returns(-12);
    tested = new RemainingCapacity(mockMutableInteger.Object);

    //act
    tested.EnforceCapacity_A();

    //assert
    mockMutableInteger.Verify(mock => mock.Subtract(-12), Times.Once());
}

[Test]
public void WhenEnforceCapacityRequiresSpaceItIsAdded_B()
{
    //arrange
    Mock<MutableInteger> mockMutableInteger = new Mock<MutableInteger>();
    mockMutableInteger.Setup(mock => mock.GetCurrentValue()).Returns(-12);
    tested = new RemainingCapacity(mockMutableInteger.Object);

    //act
    tested.EnforceCapacity_B();

    //assert
    mockMutableInteger.Verify(mock => mock.Add(12), Times.Once());
}
```

Getting either test to pass requires a very specific implementation of the `EnforceCapacity` method, despite the fact that the end result would be the same. Therefore, both tests are quite brittle.

```csharp
public void EnforceCapacity_A()
{
    if(_remainingCapacity.GetCurrentValue() >= 0)
    {
        return;
    }

    _remainingCapacity.Subtract(_remainingCapacity.GetCurrentValue());
}

public void EnforceCapacity_B()
{
    if(_remainingCapacity.GetCurrentValue() < 0)
    {
        _remainingCapacity.Add(-1 * _remainingCapacity.GetCurrentValue());
    }
}
```

Alternatively, if we simply use a real `MutableInteger` instead of a mock we can reduce some of the brittleness.

```csharp
[Test]
public void WhenEnforceCapacityRequiresSpaceItIsAdded_Either()
{
    //arrange
    tested = new RemainingCapacity(new MutableInteger(-12));

    //act
    tested.EnforceCapacity_A();

    //assert
    Assert.AreEqual(0, tested.GetRemainingCapacity());
}
```

This tradeoff comes at the cost of independence, as we now require `MutableInteger` to be fully implemented and non-buggy. In this case, it doesn't seem like much is lost, however, in more complex classes with nested dependencies, creating a viable instance for testing can be extremely onerous.

### Parameterized Testing Example

Perhaps we want to verify that our add method on `MutableInteger` works for a wider range of values than a single test, but we don't want to write the same test over and over again with slightly different values.

```csharp
[TestCase(1, 1, 2)]
[TestCase(2, 2, 4)]
[TestCase(int.MinValue, int.MaxValue, -1)]
[TestCase(0, 0, 0)]
public void WhenAdd_CurrentValueIncrementsByExpectedAmount(int initialValue, int increment, int expectation)
{
    //arrange
    tested = new MutableInteger(initialValue);

    //act
    tested.Add(increment);

    //assert
    Assert.AreEqual(expectation, tested.GetCurrentValue());
}
```

This parameterized test runs four times, and makes sure that the first two arguments add up to the third argument (eg. 1 + 1 = 2).