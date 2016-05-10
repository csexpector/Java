package rational;

/**
 * Provides rational number (fraction) objects.
 */
public class Rat
{
    /** 
     * The numerator.  At all times, gcd(num, den) = 1
     */
    private long num;
    
    /**
     * The denominator.  At all times, den > 0
     */
    private long den;
    
    /**
     * Creates the rational number 0
     */
    public Rat ()
    {
        this(0);
    }

    /**
     * Creates the rational number n
     */
    public Rat (long n)
    {
        this(n, 1);
    }

    /**
     * If d is zero, throws an IllegalArgumentException. Otherwise creates the
     * rational number n/d
     */
    public Rat (long n, long d)
    {
        // Make sure the denominator is non-zero
        if (d == 0)
        {
            throw new IllegalArgumentException();
        }

        // Make the denominator non-zero
        if (d < 0)
        {
            d = -d;
            n = -n;
        }
        
        // Reduce to lowest terms
        long g = gcd(Math.abs(n), d);
        n = n / g;
        d = d / g;
        
        // Record the fraction
        num = n;
        den = d;
    }

    /**
     * Returns the sum of this and r
     * Rat x = new Rat(5, 3);
     * Rat y = new Rat(1, 5);
     * Rat z = x.add(y);
     * a/b + c/d = (ad + bc) / bd
     */
    public Rat add (Rat r)
    {
        long newNum = this.num*r.den + this.den*r.num;
        long newDen = this.den * r.den;
        return new Rat(newNum, newDen);
    }

    /**
     * Returns the difference of this and r
     * a/b - c/d = (ad - bc) / bd
     */
    public Rat sub (Rat r)
    {
        long newNum = this.num*r.den - this.den*r.num;
        long newDen = this.den * r.den;
        return new Rat(newNum, newDen);
    }

    /**
     * Returns the product of this and r
     * Rat x = new Rat(5, 3);
     * Rat y = new Rat(1, 5);
     * Rat z = x.mul(y);
     * a/b * c/d = ac/bd
     */
    public Rat mul (Rat r)
    {
        long newNum = this.num * r.num;
        long newDen = this.den * r.den;
        return new Rat(newNum, newDen);
    }

    /**
     * If r is zero, throws an IllegalArgumentException. Otherwise, returns the
     * quotient of this and r.
     * a/b / c/d = ad / bc
     */
    public Rat div (Rat r)
    {
        long newNum = this.num * r.den;
        long newDen = this.den * r.num;
        return new Rat(newNum, newDen);
    }

    /**
     * Returns a negative number if this < r, zero if this = r, a positive
     * number if this > r
     * To compare a/b and c/d, compare ad and bc   
     */
    public int compareTo (Rat r)
    {
        long left = this.num * r.den;
        long right = this.den * r.num;
        if (left < right)
        {
            return -1;
        }
        else if (left > right)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    /**
     * Returns a string version of this in simplest and lowest terms. Examples:
     * 3/4 => "3/4" 6/8 => "3/4" 2/1 => "2" 0/8 => "0" 3/-4 => "-3/4"
     */
    public String toString ()
    {
        if (den == 1)
        {
            return num + "";
        }
        else
        {
            return num + "/" + den;
        }
    }

    /**
     * Converts this to the closest double
     */
    public double toDouble ()
    {
       return (1.0 * num) / den;
    }

    /**
     * Returns the greatest common divisor of a and b, where a >= 0 and b > 0.
     */
    public static long gcd (long a, long b)
    {
        while (b > 0)
        {
            long remainder = a % b;
            a = b;
            b = remainder;
        }
        return a;
    }
}
