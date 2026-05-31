
public abstract class Operations<T>
{
	
    public abstract T zero();
    public abstract T one();

    public boolean equal(T i)
    {
    	return false;
    }
    
    public T add(T i)
    {
		return i;
    }
    
    public T mul(T i)
    {
		return i;
    }
    
    public T sub(T i)
    {
		return i;
    }
    
    public T div(T i)
    {
		return i;
    }
    
    public T mod(T i)
    {
		return i;
    }
    
    public T neg()
    {
		return null;
    }
    
    public void setValue(T i)
    {}
}