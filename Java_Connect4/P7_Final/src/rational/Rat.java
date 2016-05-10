
package rational;

import java.math.BigInteger;
/**
 * Provides rational number (fraction) objects.
 */
public class Rat
{
    /** 
     * The numerator.  At all times, gcd(num, den) = 1
     */
    private BigInteger num;
    
    /**
     * The denominator.  At all times, den > 0
     */
    private BigInteger den;
    
    /**
     * Creates the rational number 0
     */
    public Rat ()
    {
        num = new BigInteger("0");
        den = new BigInteger("1");
    }

    /**
     * Creates the rational number n
     */
    public Rat (BigInteger n)
    {
        num = n;
        den = new BigInteger("1");
    }

    /**
     * If d is zero, throws an IllegalArgumentException. Otherwise creates the
     * rational number n/d
     */
    public Rat (BigInteger n, BigInteger d)
    {
        BigInteger zero = new BigInteger("0");
        // Make sure the denominator is non-zero
        if (d.equals(zero))
        {
            throw new IllegalArgumentException();
        }

        // Make the denominator non-zero
        if (d.max(zero).equals(zero))
        {
            d = d.negate();
            n = n.negate();
        }
        
        // Reduce to lowest terms
        //BigInteger g = gcd(Math.abs(n), d);
        BigInteger g = n.abs().gcd(d);
        //n = n / g;
        n = n.divide(g);
        //d = d / g;
        d = d.divide(g);
        
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
        //long newNum = this.num*r.den + this.den*r.num;
        //long newDen = this.den * r.den;
        //return new Rat(newNum, newDen);
        BigInteger newNum = num.multiply(r.den).add(den.multiply(r.num));
        BigInteger newDen = den.multiply(r.den);
        return new Rat(newNum, newDen);
    }

    /**
     * Returns the difference of this and r
     * a/b - c/d = (ad - bc) / bd
     */
    public Rat sub (Rat r)
    {
        //long newNum = this.num*r.den - this.den*r.num;
        //long newDen = this.den * r.den;
        //return new Rat(newNum, newDen);
        BigInteger newNum = num.multiply(r.den).subtract(den.multiply(r.num));
        BigInteger newDen = den.multiply(r.den);
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
        //long newNum = this.num * r.num;
        //long newDen = this.den * r.den;
        //return new Rat(newNum, newDen);
        BigInteger newNum = num.multiply(r.num);
        BigInteger newDen = den.multiply(r.den);
        return new Rat(newNum, newDen);
    }

    /**
     * If r is zero, throws an IllegalArgumentException. Otherwise, returns the
     * quotient of this and r.
     * a/b / c/d = ad / bc
     */
    public Rat div (Rat r)
    {
        BigInteger zero = new BigInteger("0");
        // Make sure the denominator is non-zero
        if (r.equals(zero))
        {
            throw new IllegalArgumentException();
        }
        //long newNum = this.num * r.den;
        //long newDen = this.den * r.num;
        BigInteger newNum = num.multiply(r.den);
        BigInteger newDen = den.multiply(r.num);
        return new Rat(newNum, newDen);
    }

    /**
     * Returns a negative number if this < r, zero if this = r, a positive
     * number if this > r
     * To compare a/b and c/d, compare ad and bc   
     */
    public int compareTo (Rat r)
    {
        /*
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
        } */
        BigInteger left = num.multiply(r.den);
        BigInteger right = den.multiply(r.num);
        if (left.equals(right)) return 0;
        else if (left.max(right).equals(right)) // left < right
        {
            return -1;
        }
        else if (left.max(right).equals(left)) // left > right
        {
            return 1;
        }
        return 0;
    }

    /**
     * Returns a string version of this in simplest and lowest terms. Examples:
     * 3/4 => "3/4" 6/8 => "3/4" 2/1 => "2" 0/8 => "0" 3/-4 => "-3/4"
     */
    public String toString ()
    {
        BigInteger one = new BigInteger("1");
        if (den.equals(one))
        {
            return num + "";
        }
        else
        {
            return num.toString(10) + "/" + den.toString(10);
        }
    }

    /**
     * Converts this to the closest double
     */
    //public double toDouble ()
    //{
    //   return (1.0 * num) / den;
    //}

    /**
     * Returns the greatest common divisor of a and b, where a >= 0 and b > 0.
     */
    /*
    public static long gcd (long a, long b)
    {
        while (b > 0)
        {
            long remainder = a % b;
            a = b;
            b = remainder;
        }
        return a;
    }/*/
}
