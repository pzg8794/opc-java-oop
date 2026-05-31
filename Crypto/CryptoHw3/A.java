import java.math.BigDecimal;


public class A {

	public int prime;
	public int nPrime;
	BigDecimal counter1;
	BigDecimal counter2;
	
	public long a;

	public A(long a) {
		this.setA(a);
	}

	public long getA() {
		return a;
	}

	public void setA(long a) {
		this.a = a;
	}

	
	public String toString(){
		return "( "+ a + " ) ";
	}
}
