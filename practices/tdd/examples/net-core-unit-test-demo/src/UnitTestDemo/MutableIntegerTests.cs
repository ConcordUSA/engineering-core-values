using NUnit.Framework;

namespace UnitTestDemo
{
    public class MutableIntegerTests
    {
        private MutableInteger tested;

        [Test]
        public void WhenMutableIntegerConstructed_CurrentValueIsEqualToConstructorParameter()
        {
            //arrange
            int expected = 5;
            tested = new MutableInteger(expected);

            //act
            int actual = tested.GetCurrentValue();

            //assert
            Assert.AreEqual(expected, actual);
        }

        [Test]
        public void WhenAdd_CurrentValueIsIncremented()
        {
            //arrange
            int expected = 10;
            tested = new MutableInteger(5);

            //act
            tested.Add(5);
            int actual = tested.GetCurrentValue();

            //assert
            Assert.AreEqual(expected, actual);
        }

        [Test]
        public void WhenSubtract_CurrentValueIsDecrementedParameter()
        {
            //arrange
            int expected = 0;
            tested = new MutableInteger(5);

            //act
            tested.Subtract(5);
            int actual = tested.GetCurrentValue();

            //assert
            Assert.AreEqual(expected, actual);
        }

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
    }
}
