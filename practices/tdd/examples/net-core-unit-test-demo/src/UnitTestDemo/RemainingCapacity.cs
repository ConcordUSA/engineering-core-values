namespace UnitTestDemo
{
    public class RemainingCapacity
    {
        private MutableInteger remainingCapacity;

        public RemainingCapacity(MutableInteger spaceCapacity)
        {
            remainingCapacity = spaceCapacity;
        }

        public int GetRemainingCapacity()
        {
            return remainingCapacity.GetCurrentValue();
        }

        public bool AllowEntry(int partySize)
        {
            return remainingCapacity.GetCurrentValue() >= partySize;
        }

        public void AdmitParty(int partySize)
        {
            remainingCapacity.Subtract(partySize);
        }

        public void RemoveParty(int partySize)
        {
            remainingCapacity.Add(partySize);
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
            if(remainingCapacity.GetCurrentValue() >= 0)
            {
                return;
            }

            remainingCapacity.Subtract(remainingCapacity.GetCurrentValue());
        }

        public void EnforceCapacity_B()
        {
            if(remainingCapacity.GetCurrentValue() < 0)
            {
                remainingCapacity.Add(-1 * remainingCapacity.GetCurrentValue());
            }
        }
    }
}
