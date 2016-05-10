package rational;

import static org.junit.Assert.*;
import java.math.BigInteger;
import org.junit.Test;

public class RatTest
{
    @Test
    public void testConstructor1 ()
    {
        Rat r = new Rat();
        assertEquals("0", r.toString());
    }

    @Test
    public void testConstructor2 ()
    {
        Rat r = new Rat(new BigInteger("555555555555555555555555555"));
        assertEquals("555555555555555555555555555", r.toString());
    }

    @Test
    public void testConstructor3 ()
    {
        Rat r1 = new Rat(new BigInteger("111111111111111111111111111"), new BigInteger("222222222222222222222222222"));
        assertEquals("1/2", r1.toString());

        Rat r2 = new Rat(new BigInteger("1010101010101010101010101010101010101010"), new BigInteger("5050505050505050505050505050505050505050"));
        assertEquals("1/5", r2.toString());

        Rat r3 = new Rat(new BigInteger("-444444444444444444444444444"), new BigInteger("-222222222222222222222222222"));
        assertEquals("2", r3.toString());

        Rat r4 = new Rat(new BigInteger("6666666666"), new BigInteger("-8888888888"));
        assertEquals("-3/4", r4.toString());

        Rat r5 = new Rat(new BigInteger("333333333333333333333333333"), new BigInteger("-999999999999999999999999999"));
        assertEquals("-1/3", r5.toString());
        
        try {
            new Rat(new BigInteger("444444444444444444444444444"), new BigInteger("0"));
            fail("No exception thrown");
        }
        catch (IllegalArgumentException e)
        {           
        }
    }

    @Test
    public void testAdd ()
    {
        Rat r1 = new Rat(new BigInteger("222222222222222222222222222"), new BigInteger("555555555555555555555555555"));
        Rat r2 = new Rat(new BigInteger("333333333333333333333333333"), new BigInteger("444444444444444444444444444"));
        assertEquals("23/20", r1.add(r2).toString());
        
        r1 = new Rat(new BigInteger("111111111111111111111111111"), new BigInteger("777777777777777777777777777"));
        r2 = new Rat(new BigInteger("-111111111111111111111111111"), new BigInteger("555555555555555555555555555"));
        assertEquals("-2/35", r1.add(r2).toString());
    }

    @Test
    public void testSub ()
    {
        Rat r1 = new Rat(new BigInteger("222222222222222222222222222"),new BigInteger("555555555555555555555555555"));
        Rat r2 = new Rat(new BigInteger("333333333333333333333333333"),new BigInteger("444444444444444444444444444"));
        assertEquals("-7/20", r1.sub(r2).toString());
        
        r1 = new Rat(new BigInteger("111111111111111111111111111"), new BigInteger("777777777777777777777777777"));
        r2 = new Rat(new BigInteger("-111111111111111111111111111"), new BigInteger("555555555555555555555555555"));
        assertEquals("12/35", r1.sub(r2).toString());
    }

    @Test
    public void testMul ()
    {
        Rat r1 = new Rat(new BigInteger("222222222222222222222222222"),new BigInteger("555555555555555555555555555"));
        Rat r2 = new Rat(new BigInteger("333333333333333333333333333"),new BigInteger("444444444444444444444444444"));
        assertEquals("3/10", r1.mul(r2).toString());
        
        r1 = new Rat(new BigInteger("111111111111111111111111111"), new BigInteger("777777777777777777777777777"));
        r2 = new Rat(new BigInteger("-111111111111111111111111111"), new BigInteger("555555555555555555555555555"));
        assertEquals("-1/35", r1.mul(r2).toString());
    }

    @Test
    public void testDiv ()
    {
        Rat r1 = new Rat(new BigInteger("222222222222222222222222222"),new BigInteger("555555555555555555555555555"));
        Rat r2 = new Rat(new BigInteger("333333333333333333333333333"),new BigInteger("444444444444444444444444444"));
        assertEquals("8/15", r1.div(r2).toString());
        
        r1 = new Rat(new BigInteger("111111111111111111111111111"), new BigInteger("777777777777777777777777777"));
        r2 = new Rat(new BigInteger("-111111111111111111111111111"), new BigInteger("555555555555555555555555555"));
        assertEquals("-5/7", r1.div(r2).toString());
        
        try {
            r1 = new Rat(new BigInteger("333333333333333333333333333"),new BigInteger("444444444444444444444444444"));
            r2 = new Rat(new BigInteger("0"));
            r1.div(r2);
            fail("No exception thrown");
        }
        catch (IllegalArgumentException e)
        {
        }
    }

    @Test
    public void testCompareTo ()
    {
        Rat r1 = new Rat(new BigInteger("333333333333333333333333333"),new BigInteger("444444444444444444444444444"));
        Rat r2 = new Rat(new BigInteger("6"),new BigInteger("8"));
        Rat r3 = new Rat(new BigInteger("111111111111111111111111111"),new BigInteger("222222222222222222222222222"));
        assertEquals(0, r1.compareTo(r2));
        assertTrue(r1.compareTo(r3) > 0);
        assertTrue(r3.compareTo(r1) < 0);
    }


}
