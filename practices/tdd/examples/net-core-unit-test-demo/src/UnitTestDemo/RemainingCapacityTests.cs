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
    }
}