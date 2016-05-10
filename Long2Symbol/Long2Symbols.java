public class Long2Symbols
{
 /**
  * Converts a long to a series of symbols - the encoding of the
  * number in any base, given any symbol system.
  * <p>
  * Return a String which is the encoding of the long in the symbol
  * system given.  The list of symbols will be given as an array of
  * characters; the base of the encoding is simply the number of
  * symbols in the array.  For instance, if the symbols given are
  * the array {'0','1','2','3'}, then this is a base-4 encoding and
  * the symbols are the ordinary digits.  Thus, the encoding of
  * the number 20 (decimal) would be "110" (4^2 + 4^1).  If the
  * array was {'0','1'}, then this is base-2, and the encoding would
  * be "10100" (2^4 + 2^2).
  * <p>
  * But since the symbols are given in an array, don't expect that
  * the encoding will necessarily look like a number.  If the array
  * given is {'$','.'}, then 20 (decimal) would be encoded as
  * ".$.$$".  (This is the same encoding as the previous example -
  * just with different symbols.)
  * <p>
  * Why the complexity?  So that it's impossible to use standard
  * Java library functions to format a number!  (Plus, it's cool to
  * see that the symbols don't really matter!)
  * <p>
  * Note about hard-coding: It is perfectly OK to hardcode for the
  * value 0 in this method.  That's what a professional would do,
  * so it is *NOT* cheating!
  * <p>
  * ERROR HANDLING: 
  *   - Throw IllegalArgumentException if the number to encode is negative.
  *   - Throw IllegalArgumentException if the length of the array is less than 2.
  * <p>
  * SYNTAX TRICKS:
  *   - The index to an array must be an int, not a long.  So when you
  *     calculate an index into the symbols[] array, make sure that you
  *     cast the index to an int *AFTER* you have calculated it.
  *     Otherwise, your code won't compile.
  *   - You can convert a single char to a String by concatenating it to
  *     the empty string.
  * <p>
  * HINT: Use recursion to make this method easier.  Figure out one symbol -
  *       and then use recursion to find the rest of the encoding.
  *
  * @param val     A non-negative number
  * @param symbols The symbols to encode the number
  * @return        A String giving the encoding
  */
 public static String long2Symbols(long val, char[] symbols)
 {
    long n = (long) symbols.length;
    if (val < 0)
        throw new IllegalArgumentException();
    else if (n < 2)
        throw new IllegalArgumentException();
    else // val and symbols are valid
    {
      /*
        This is the "math" algorithm to convert base 10 to any base
        1.  Divide the "desired" base INTO the number you are trying to convert.
        2.  Write the quotient (the answer) with a remainder like you did in elementary school.
        3.  Repeat this division process using the whole number from the previous quotient (the number in front of the remainder).
        4.  Continue repeating this division until the number in front of the remainder is only zero.
        5.  The answer is the remainders read from the bottom up.
       */

      // and here is what we do in programming
      long remainder = val % n; // in math, this is what we store, but ...
      long quotient = val / n;
      char c = symbols[(int)remainder]; // to decode, this is what we store
      String s = String.valueOf(c);     // just to convert from character to string
      if (quotient == 0) return s;      // stop condition
      else
        return long2Symbols(quotient, symbols) + s; // call recursively until we met the stop condition
    }

 }
        
 


 /**
  * Main function; does the basic tests.
  */
 public static void main(String[] args)
 {

  
  char[] base10        = {'0','1','2','3','4','5','6','7','8','9'};
  char[] base10_weird1 = {'o','l','z','E','A','S','C','T','8','P'};
  char[] base10_weird2 = {'9','8','7','6','5','4','3','2','1','0'};

  char[] base2        = {'0','1'};
  char[] base2_weird1 = {'q','l'};
  char[] base2_weird2 = {'1','0'};

  char[] base8        = {'0','1','2','3','4','5','6','7'};
  char[] base8_weird1 = {'a','b','c','d','e','f','g','h'};
  char[] base8_weird2 = {'1','2','3','4','5','6','7','8'};

  char[] base16        = {'0','1','2','3','4','5','6','7',
                          '8','9','a','b','c','d','e','f'};
  char[] base16b       = {'0','1','2','3','4','5','6','7',
                          '8','9','A','B','C','D','E','F'};
  char[] base16_weird1 = {'a','b','c','d','e','f','g','h',
                          'A','B','C','D','E','F','G','H'};

  Cs127A_v4.assertEq(long2Symbols(0, base10       ), "0");
  Cs127A_v4.assertEq(long2Symbols(0, base10_weird1), "o");
  Cs127A_v4.assertEq(long2Symbols(0, base10_weird2), "9");
  Cs127A_v4.assertEq(long2Symbols(0, base2        ), "0");
  Cs127A_v4.assertEq(long2Symbols(0, base2_weird1 ), "q");
  Cs127A_v4.assertEq(long2Symbols(0, base2_weird2 ), "1");
  Cs127A_v4.assertEq(long2Symbols(0, base8        ), "0");
  Cs127A_v4.assertEq(long2Symbols(0, base8_weird1 ), "a");
  Cs127A_v4.assertEq(long2Symbols(0, base8_weird2 ), "1");
  Cs127A_v4.assertEq(long2Symbols(0, base16       ), "0");
  Cs127A_v4.assertEq(long2Symbols(0, base16b      ), "0");
  Cs127A_v4.assertEq(long2Symbols(0, base16_weird1), "a");

  Cs127A_v4.assertEq(long2Symbols(1, base10       ), "1");
  Cs127A_v4.assertEq(long2Symbols(1, base10_weird1), "l");
  Cs127A_v4.assertEq(long2Symbols(1, base10_weird2), "8");
  Cs127A_v4.assertEq(long2Symbols(1, base2        ), "1");
  Cs127A_v4.assertEq(long2Symbols(1, base2_weird1 ), "l");
  Cs127A_v4.assertEq(long2Symbols(1, base2_weird2 ), "0");
  Cs127A_v4.assertEq(long2Symbols(1, base8        ), "1");
  Cs127A_v4.assertEq(long2Symbols(1, base8_weird1 ), "b");
  Cs127A_v4.assertEq(long2Symbols(1, base8_weird2 ), "2");
  Cs127A_v4.assertEq(long2Symbols(1, base16       ), "1");
  Cs127A_v4.assertEq(long2Symbols(1, base16b      ), "1");
  Cs127A_v4.assertEq(long2Symbols(1, base16_weird1), "b");

  Cs127A_v4.assertEq(long2Symbols(2, base10       ), "2");
  Cs127A_v4.assertEq(long2Symbols(2, base10_weird1), "z");
  Cs127A_v4.assertEq(long2Symbols(2, base10_weird2), "7");
  Cs127A_v4.assertEq(long2Symbols(2, base2        ), "10");
  Cs127A_v4.assertEq(long2Symbols(2, base2_weird1 ), "lq");
  Cs127A_v4.assertEq(long2Symbols(2, base2_weird2 ), "01");
  Cs127A_v4.assertEq(long2Symbols(2, base8        ), "2");
  Cs127A_v4.assertEq(long2Symbols(2, base8_weird1 ), "c");
  Cs127A_v4.assertEq(long2Symbols(2, base8_weird2 ), "3");
  Cs127A_v4.assertEq(long2Symbols(2, base16       ), "2");
  Cs127A_v4.assertEq(long2Symbols(2, base16b      ), "2");
  Cs127A_v4.assertEq(long2Symbols(2, base16_weird1), "c");

  Cs127A_v4.assertEq(long2Symbols(101, base10       ), "101");
  Cs127A_v4.assertEq(long2Symbols(101, base10_weird1), "lol");
  Cs127A_v4.assertEq(long2Symbols(101, base10_weird2), "898");
  Cs127A_v4.assertEq(long2Symbols(101, base2        ), "1100101");
  Cs127A_v4.assertEq(long2Symbols(101, base2_weird1 ), "llqqlql");
  Cs127A_v4.assertEq(long2Symbols(101, base2_weird2 ), "0011010");
  Cs127A_v4.assertEq(long2Symbols(101, base8        ), "145");
  Cs127A_v4.assertEq(long2Symbols(101, base8_weird1 ), "bef");
  Cs127A_v4.assertEq(long2Symbols(101, base8_weird2 ), "256");
  Cs127A_v4.assertEq(long2Symbols(101, base16       ), "65");
  Cs127A_v4.assertEq(long2Symbols(101, base16b      ), "65");
  Cs127A_v4.assertEq(long2Symbols(101, base16_weird1), "gf");

  boolean thrown;

  System.out.println("Testing to ensure that the method throws an exception properly (test 1 of 2)...");
  thrown = false;
  try {
   String s = long2Symbols(-1, base10);
   System.out.println("OOPS: Your code did not throw an exception like it was supposed to!  long2Symbols(-1,...) returned "+s);
  }
  catch (IllegalArgumentException e)
  {
   System.out.println("Exception thrown as expected.");
   thrown = true;
  }
  Cs127A_v4.assertEq(thrown, true);

  System.out.println("Testing to ensure that the method throws an exception properly (test 2 of 2)...");
  thrown = false;
  try {
   char[] tooShort = {'a'};
   String s = long2Symbols(0, tooShort);
   System.out.println("OOPS: Your code did not throw an exception like it was supposed to!  long2Symbols(0,{'a'}) returned "+s);
  }
  catch (IllegalArgumentException e)
  {
   System.out.println("Exception thrown as expected.");
   thrown = true;
  }
  Cs127A_v4.assertEq(thrown, true);

  Cs127A_v4.reportResults();
 
 }

}

