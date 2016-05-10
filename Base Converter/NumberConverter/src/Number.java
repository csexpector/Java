


import java.util.ArrayList;
import javafx.util.Pair;

public class Number 
{
    short[] Int;
    short[] NonRep;
    short[] Rep;
    short Base;
    public Number()
    {
        Int = new short[0];
        NonRep = new short[0];
        Rep = new short[0];
        Base = 10;
    }
    public Number(short[] IntPart, short[] NonRepPart, short[] RepPart, short base)
    {
        
        Int = new short[IntPart.length];
        System.arraycopy(IntPart, 0, Int, 0, Int.length);
        NonRep = new short[NonRepPart.length];
        System.arraycopy(NonRepPart, 0, NonRep, 0, NonRep.length);
        Rep = new short[RepPart.length];
        System.arraycopy(RepPart, 0, Rep, 0, Rep.length);
        Base = base;
        
    }
    final int MAXLEN = 100;
    public String ShortArrayToString(short[] S) 
    {    
        //String[] numberToChars = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F",
        //    "G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
        //    "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        
        String s = "";
        for (int i = 0; i < S.length; i++) 
        {           
            s += S[i];          
        }      
        return s;
    }         
    @Override
    public String toString() 
    {          
        String val = "(";          
        val += ShortArrayToString(Int);          
        val += ".";          
        val += ShortArrayToString(NonRep);     
        if (Rep.length > 0)
        {
            val += "{";          
            val += ShortArrayToString(Rep);          
            val += "})_";     
        }
        else
        {
            val += ")_";
        }
        val += Base;   
        return val;
    }   
    public int Base10ToBaseN(int base10, int n)
    {
        int d = 1, q, count = 0, res = 0;
        int[] qs = new int[MAXLEN];
        
        while ( d != 0 )
        {
            d = base10 / n;
            q = base10 % n;
            qs[count++] = q;
            base10 = d;
        }
        
        for (int i = 0; i < count; i++)
        {
            res = (int) (res + qs[i] * Math.pow(10, i));
        }
        return res;
    }
    public int BaseNToBase10(int baseN, int n)
    {
        int res = 0, q, i = 0;
        while (baseN != 0)
        {
            q = baseN % 10;
            res = (int) (res + q * Math.pow(n, i++));
            baseN /= 10;
        }
        return res;
    }
    public short[] XBtoUR(short[] X, short B, short R)
    {
        int xb = ShortsToInt(X);
        int base10 = BaseNToBase10(xb, B);
        int baseR = Base10ToBaseN(base10, R);
        short[] res = IntToShorts(baseR);
        return res;
    }
    public int ShortsToInt(short[] X)
    {
        int res = 0;
        for (int i = 0; i < X.length; i++)
        {
            res = (int) (res + X[i] * Math.pow(10, X.length - i - 1));
        }
        
        return res;
    }
    public short[] IntToShorts(int n)
    {
        int len = 0;
        int m = n;
        while (m != 0) 
        {
            m /= 10;
            len++;
        }
        short[] res = new short[len];
        for (int i = 0; i < len; i++)
        {
            res[len - 1 - i] = (short) (n % 10);
            n /= 10;
        }
        
        return res;
    }
    public short[] FractionBaseBToBase10(short[] baseB, int B)
    {
        double res = 0;
        for (int i = 0; i < baseB.length; i++)
        {
            res += baseB[i] * Math.pow(B, 0-i-1);
        }
        res *= Math.pow(10, baseB.length );
        return IntToShorts((int)res);
    }
    public Pair<short[], short[]> FractionBaseBToFractionBaseR(short[] baseB, short B, short R)
    {
        short[] baseBTo10 = FractionBaseBToBase10(baseB, B);
        Pair<short[],short[]> p = FractionBase10ToBaseB(baseBTo10, R);
        return p;
    }
    public Pair<short[],short[]> FractionBase10ToBaseB(short[] base10, int B)
    {
        int intFrac = ShortsToInt(base10);
        double frac0 = intFrac / Math.pow(10, base10.length);
        int len = 0, pos;
        ArrayList<Double> fracs = new ArrayList<Double>();
        ArrayList<Integer> res = new ArrayList<Integer>();
        while (true)
        {
            frac0 = frac0 * B;
            len++;
            double fracPart = GetFractionPart(frac0); 
            pos = fracs.indexOf(frac0);
            fracs.add(frac0);
            int    intPart  = GetIntPart(frac0); 
            if (pos != -1)  
            {
                len--;
                break;
            }
            else
            {
                res.add(intPart);
            }
            if (fracPart == 0d) break; // stop condition
            frac0 = fracPart;
        }
        if (pos != -1) 
        {
            //pos = 0;
            short[] norep = new short[pos];
            short[] rep = new short[len-pos];
            for (int i = 0; i < pos; i++)
                norep[i] = (short)(int)res.get(i);
            for (int i = pos; i < len; i++)
                rep[i - pos] = (short)(int)res.get(i);

            return new Pair<short[],short[]>(norep, rep);
        }
        else
        {
            short[] norep = new short[len];
            short[] rep = new short[0];
            for (int i = 0; i < len; i++)
                norep[i] = (short)(int)res.get(i);
            return new Pair<short[],short[]>(norep, rep);
        }
    }
    public double GetFractionPart(double y)
    {
        double x = y;
        int count = 0;
        while (true)
        {
            x = x * 10;
            count++;
            if ((x - (int)x) >= 0) break;
        }
        int yi = (int) y;
        yi *= Math.pow(10, count);
        y  *= Math.pow(10, count);
        y  = y - yi;
        y /= Math.pow(10, count);
        return y;
    }
    public int GetIntPart(double y)
    {
        double x = GetFractionPart(y);
        return ((int)( y - x));
    }
    public Number convert(short B)
    {
        short[] IntPartToBaseB = XBtoUR(Int, this.Base, B);
        Pair<short[], short[]> frac = FractionBaseBToFractionBaseR(NonRep, Base, B);
        Number N = new Number(IntPartToBaseB, frac.getKey(), frac.getValue(), B);
        
        return N;
    }
}
