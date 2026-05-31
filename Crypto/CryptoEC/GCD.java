import java.util.ArrayList;

public class GCD<T extends Operations<T>> 
{

    public ArrayList<T> gcd(T m, T n) 
    {
        ArrayList<T> rpq = new ArrayList<T>(3);  
        T temp;
        ArrayList<T> quotients = new ArrayList<T>();

        while (!(m.equal(m.zero()))) 
        {
            quotients.add(n.div(m));
            temp = m;
            m = n.mod(m);
            n = temp;
        }
  
        rpq.add(n);

        quotients.add(m.zero());
        quotients.set(quotients.size() - 2, m.one());
    
        for (int i = quotients.size() - 3; i >= 0; i--) 
            quotients.set(i, quotients.get(i).mul(quotients.get(i + 1)).add(quotients.get(i + 2)));
        
        if (quotients.size() % 2 == 0) 
        {
            rpq.add(quotients.get(0));
            rpq.add(quotients.get(1).neg());
        }
        else 
        {
            rpq.add(quotients.get(0).neg());
            rpq.add(quotients.get(1));
        }

        return rpq;

    }
}