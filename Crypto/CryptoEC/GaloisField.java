
public class GaloisField<T extends Operations<T>> 
{

	public final T N;
	private T value;

	
	public GaloisField(T value, T N) 
	{
		this.N = N;
		this.value = value.mod(N);
	}


	public GaloisField(T N) 
	{
		this.N = N;
		this.value = N.zero();
	}

	public T getValue() 
	{
		return value;
	}

	public GaloisField<T> zero() 
	{
		return new GaloisField<T>(N);
	}

	public GaloisField<T> one() 
	{
		return new GaloisField<T>(N.one(), N);
	}


	public boolean equal(GaloisField<T> f) 
	{
		return value.equal(f.value);
	}

	public GaloisField<T> add(GaloisField<T> f) 
	{
		return new GaloisField<T>(value.add(f.value), N);
	}

	public GaloisField<T> mul(GaloisField<T> f) 
	{
		return new GaloisField<T>(value.mul(f.value), N);
	}

	public GaloisField<T> sub(GaloisField<T> f) 
	{
		return new GaloisField<T>(value.sub(f.value), N);
	}

	public GaloisField<T> div(GaloisField<T> f)
	{
		return this.mul(f.inverse());
	}

	public GaloisField<T> neg() 
	{
		return new GaloisField<T>(value.neg(), N);
	}

	public GaloisField<T> inverse() 
	{
	
		GCD<T> GCD = new GCD<T>();
		try 
		{
			return new GaloisField<T>(GCD.gcd(value, N).get(1), N);
		}

		catch (ArrayIndexOutOfBoundsException e) 
		{
			throw new ArithmeticException("Division by zero: "+ this.toString());
		}
	}

	public String toString() 
	{
		return value.toString();
	}
}