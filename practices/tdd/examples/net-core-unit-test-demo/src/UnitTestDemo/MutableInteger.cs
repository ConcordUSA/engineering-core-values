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