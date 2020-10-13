namespace UnitTestDemo
{
    public interface IMutableInteger
    {
        int GetCurrentValue();

        void Add(int increment);

        void Subtract(int decrement);
    }
}