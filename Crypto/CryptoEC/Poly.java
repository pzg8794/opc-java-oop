import java.util.List;
import java.util.ArrayList;

public class Poly extends Operations<Poly> 
{
	
    private GaloisField<Integral> poly_zero;
    private List<GaloisField<Integral>> poly_coeff;


    public Poly(GaloisField<Integral> poly_zero) 
    {
        this.poly_zero = poly_zero;
        poly_coeff = new ArrayList<GaloisField<Integral>>();
    }


    public Poly(Poly p) 
    {
        poly_coeff = new ArrayList<GaloisField<Integral>>(p.poly_coeff);
        this.poly_zero = p.poly_zero;
    }


    public void setCoefficients(int... ints)
    {
        poly_coeff.clear();
        for (int i : ints) {
            poly_coeff.add(new GaloisField<Integral>(new Integral(i), poly_zero.N));
        }
        this.clean();
    }

    public List<GaloisField<Integral>> getCoefficients()
    {
        return this.poly_coeff;
    }


    public Poly zero() 
    {
        return new Poly(poly_zero);
    }


    public Poly one() 
    {
        Poly one = new Poly(poly_zero);
        one.poly_coeff.add(poly_zero.one());
        return one;
    }


    public boolean equal(Poly i) 
    {
    	
        if (poly_coeff.size() != i.poly_coeff.size())
            return false;
        
        for (int j = 0; j < poly_coeff.size(); j++) 
        {
            if (!poly_coeff.get(j).equal(i.poly_coeff.get(j)))
            {
                return false;
            }
        }
        return true;
    }


    public Poly add(Poly p) 
    {
        Poly result = new Poly(poly_zero);

        if (this.poly_coeff.size() < p.poly_coeff.size()) 
        {
            int i;
            for (i = 0; i < this.poly_coeff.size(); i++) 
            {
                result.poly_coeff.add(this.poly_coeff.get(i).add(p.poly_coeff.get(i)));
            }
            for (; i < p.poly_coeff.size(); i++) 
            {
                result.poly_coeff.add(p.poly_coeff.get(i));
            }
        }
        else 
        {
            int i;
            for (i = 0; i < p.poly_coeff.size(); i++) 
            {
                result.poly_coeff.add(this.poly_coeff.get(i).add(p.poly_coeff.get(i)));
            }
            for (; i < this.poly_coeff.size(); i++) 
            {
                result.poly_coeff.add(this.poly_coeff.get(i));
            }
        }
        result.clean();
        return result;
    }


    public Poly mul(Poly p) 
    {
        Poly result = new Poly(poly_zero);

        for (int i = 0; i < this.poly_coeff.size()+ p.poly_coeff.size() - 1; i++) 
        {
            result.poly_coeff.add(poly_zero);
        }
      
        for (int i = 0; i < this.poly_coeff.size(); i++)
        {
            for (int j = 0; j < p.poly_coeff.size(); j++) 
            {
            	GaloisField<Integral> coefficient = result.poly_coeff.get(i+j).add(this.poly_coeff.get(i).mul(p.poly_coeff.get(j)));
                result.poly_coeff.set(i+j, coefficient);
            }
        }
        result.clean();
        return result;
    }


    public int clean() 
    {
        int counter = 0;
        while (!this.poly_coeff.isEmpty()
                && this.poly_coeff.get(this.poly_coeff.size() - 1).equal(poly_zero)) 
        {
            counter++;
            this.poly_coeff.remove(this.poly_coeff.size() - 1);
        }
        return counter;
    }


    public Poly sub(Poly p) 
    {
        Poly result = new Poly(poly_zero);

        if (this.poly_coeff.size() < p.poly_coeff.size()) 
        {
            int i;
            
            for (i = 0; i < this.poly_coeff.size(); i++)
            {
                result.poly_coeff.add(this.poly_coeff.get(i).sub(p.poly_coeff.get(i)));
            }
            
            for (; i < p.poly_coeff.size(); i++) 
            {
                result.poly_coeff.add(p.poly_coeff.get(i).neg());
            }
        }
        else 
        {
            int i;
            
            for (i = 0; i < p.poly_coeff.size(); i++)
                result.poly_coeff.add(this.poly_coeff.get(i).sub(p.poly_coeff.get(i)));
            
            for (; i < this.poly_coeff.size(); i++) 
                result.poly_coeff.add(this.poly_coeff.get(i));
        }
        result.clean();
        return result;
    }


    public Poly div(Poly p) 
    {
    
        if (this.poly_coeff.size() < p.poly_coeff.size())
            return zero();
        
        Poly result = new Poly(poly_zero);

        for (int i = 0; i < this.poly_coeff.size()- p.poly_coeff.size() + 1; i++) 
            result.poly_coeff.add(poly_zero);
        
        Poly temp = new Poly(this);

        for (int j = temp.poly_coeff.size() - p.poly_coeff.size(); j >= 0;)
        {
     
            GaloisField<Integral> c = temp.poly_coeff.get(temp.poly_coeff.size() - 1).div(p.poly_coeff.get(p.poly_coeff.size() - 1));
            result.poly_coeff.set(j, c);

            for (int i = 1; i <= p.poly_coeff.size(); i++) 
            {
            	GaloisField<Integral> coefficient = temp.poly_coeff.get( temp.poly_coeff.size()-i).sub(p.poly_coeff.get(p.poly_coeff.size()-i).mul(c));
                temp.poly_coeff.set(temp.poly_coeff.size()-i, coefficient);
            }
            
            j -= temp.clean();
        }
        
        result.clean();
        return result;
    }


    public Poly mod(Poly p)
    {
        if (this.poly_coeff.size() < p.poly_coeff.size()) 
            return this;

        Poly temp = new Poly(this);
        for (int j = temp.poly_coeff.size() - p.poly_coeff.size(); j >= 0;) 
        {
            GaloisField<Integral> c =temp.poly_coeff.get(temp.poly_coeff.size() - 1).div(p.poly_coeff.get(p.poly_coeff.size() - 1));
            for (int i = 1; i <= p.poly_coeff.size(); i++)
            {
            	GaloisField<Integral> coefficient = temp.poly_coeff.get(temp.poly_coeff.size() - i).sub(p.poly_coeff.get(p.poly_coeff.size() - i).mul(c));
                temp.poly_coeff.set(temp.poly_coeff.size()-i, coefficient);
            }
            
            j -= temp.clean();
        }
        temp.clean();
        return temp;
    }


    public Poly neg() 
    {
        Poly result = new Poly(poly_zero);

        for (GaloisField<Integral> c : this.poly_coeff) 
            result.poly_coeff.add(c.neg());
        return result;
    }


    public String toString() 
    {
        if (this.poly_coeff.isEmpty())
            return "0";
        StringBuilder sb = new StringBuilder();
        
        for (int i = this.poly_coeff.size() - 1; i >= 0; i--) 
        {
            String str_coeff;
            
            if (this.poly_coeff.get(i).equal(poly_zero))
                continue;
            
            if (this.poly_coeff.get(i).equal(poly_zero.one()) && i != 0) 
                str_coeff = "";
            else 
                str_coeff = "" + this.poly_coeff.get(i);
            
            if (i == 0) 
            {
                sb.append(str_coeff + " + ");
                continue;
            }
            
            if (i == 1)
            {
                sb.append(str_coeff + "x + ");
                continue;
            }
            
            sb.append(str_coeff + "x^" + i + " + ");
        }
        return sb.substring(0, sb.length() - 3);
    }


    public void setValue(Poly p) 
    {
        this.poly_zero = p.poly_zero;
        this.poly_coeff = p.poly_coeff;
    }
}