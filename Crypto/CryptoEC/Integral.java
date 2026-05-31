
public class Integral extends Operations<Integral>
{
    private int value;


    public Integral(int v) 
    {
        value = v;
    }

    public int getValue() 
    {
        return value;
    }

   
    public Integral zero() 
    {
        return new Integral(0);
    }

    
    public Integral one() 
    {
        return new Integral(1);
    }

    public boolean equal(Integral i) 
    {
        return value == i.value;
    }

    public Integral add(Integral i)
    {
        return new Integral(value + i.value);
    }

    
    public Integral mul(Integral i)
    {
        return new Integral(value * i.value);
    }


    public Integral sub(Integral i) 
    {
        return new Integral(value - i.value);
    }


    public Integral div(Integral i)
    {
        return new Integral(value / i.value);
    }


    public Integral mod(Integral i)
    {
        return new Integral((value % i.value + i.value) % i.value);
    }


    public Integral neg() {
        return new Integral(-value);
    }

    public void setValue(Integral i) 
    {
        this.value = i.value;
    }


    public String toString()
    {
        return Integer.toString(value);
    }

}