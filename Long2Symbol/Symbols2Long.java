public class Symbols2Long
{
 /**
  * Converts a series of symbols (the encoding of a nubmer in a certain
  * system) into a long.
  * <p>
  * Given a String which is the encoding of an long in a certain
  * symbol system, and array of characters which is the symbols from
  * this system, return the long.  (This homework is exactly the
  * reverse of Long2Symbols, see the comment there.)
  * <p>
  * ERROR HANDLING: 
  *   - (1) Throw IllegalArgumentException if the the original String
  *     has length zero.
  *   - (2) Throw IllegalArgumentException if the first character in the String
  *     matches the symbol for zero (except for the case where it is
  *     the only character).
  *   - (3) Throw IllegalArgumentException if any character in the original
  *     String doesn't exist in the symbol-array.
  *   - (4) Throw IllegalArgumentException if the length of the symbol-array
  *     is less than 2.
  * <p>
  * You do *NOT* have to worry about the possibility that the String
  * might encode a very large number.  You may assume that the encoded
  * number fits into the positive range of a long (but you don't have
  * to confirm this).
  * <p>
  * HINT 1: Write a second method which, given a symbol and the
  *         symbol-array, returns the index of that symbol in the array.
  * HINT 2: Use recursion to make this method easier.  Remove one symbol
  *         from the String, convert it to a numeric value, and then
  *         use recursion to find the value of the rest of the String.
  *
  * @param encoding A non-empty String made entirely of symbols from the next param
  * @param symbols  The symbols of the encoding
  * @return         A long giving the value
  */
 public static long symbols2Long(String encoding, char[] symbols)
 {
  //TODO: your code here
  if (encoding.length() == 0 || symbols.length < 2) 
  {
    throw new IllegalArgumentException();
  }  
  else if ( (encoding.length() > 1) && (encoding.charAt(0) == symbols[0]) ) 
    {      
      //System.out.println(encoding);
      //System.out.println(symbols[0]);
      throw new IllegalArgumentException();
    }
  else if (isInEncodingButNotInSymbols(encoding, symbols)) throw new IllegalArgumentException();
  else
  {
    // convert last character to long
    long base = symbols.length;

    long n = convertEncodingToLong(encoding.charAt(encoding.length() - 1), symbols);
    if (encoding.length() == 1) return n;
    else
    {
      String newencoding = encoding.substring(0, encoding.length() - 1);
      return n + base*symbols2Long(newencoding, symbols);
    }
  }
 }

public static boolean isInEncodingButNotInSymbols(String encoding, char[] symbols)
{
  for (int i = 0; i < encoding.length(); i++)
  {
    boolean found = false;
    for (int j = 0; j < symbols.length; j++)
      if (encoding.charAt(i) == symbols[j]) found = true;
    if (!found) return true;
  }
  return false;
}
public static long convertEncodingToLong(char c, char[] symbols)
{
  for (long i = 0; i < symbols.length; i++)
  {
    if (c == symbols[(int)i]) return i;
  }
  throw new IllegalArgumentException();
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

  Cs127A_v4.assertEq(symbols2Long("0", base10       ), 0);
  Cs127A_v4.assertEq(symbols2Long("o", base10_weird1), 0);
  Cs127A_v4.assertEq(symbols2Long("9", base10_weird2), 0);
  Cs127A_v4.assertEq(symbols2Long("0", base2        ), 0);
  Cs127A_v4.assertEq(symbols2Long("q", base2_weird1 ), 0);
  Cs127A_v4.assertEq(symbols2Long("1", base2_weird2 ), 0);
  Cs127A_v4.assertEq(symbols2Long("0", base8        ), 0);
  Cs127A_v4.assertEq(symbols2Long("a", base8_weird1 ), 0);
  Cs127A_v4.assertEq(symbols2Long("1", base8_weird2 ), 0);
  Cs127A_v4.assertEq(symbols2Long("0", base16       ), 0);
  Cs127A_v4.assertEq(symbols2Long("0", base16b      ), 0);
  Cs127A_v4.assertEq(symbols2Long("a", base16_weird1), 0);

  Cs127A_v4.assertEq(symbols2Long("1", base10       ), 1);
  Cs127A_v4.assertEq(symbols2Long("l", base10_weird1), 1);
  Cs127A_v4.assertEq(symbols2Long("8", base10_weird2), 1);
  Cs127A_v4.assertEq(symbols2Long("1", base2        ), 1);
  Cs127A_v4.assertEq(symbols2Long("l", base2_weird1 ), 1);
  Cs127A_v4.assertEq(symbols2Long("0", base2_weird2 ), 1);
  Cs127A_v4.assertEq(symbols2Long("1", base8        ), 1);
  Cs127A_v4.assertEq(symbols2Long("b", base8_weird1 ), 1);
  Cs127A_v4.assertEq(symbols2Long("2", base8_weird2 ), 1);
  Cs127A_v4.assertEq(symbols2Long("1", base16       ), 1);
  Cs127A_v4.assertEq(symbols2Long("1", base16b      ), 1);
  Cs127A_v4.assertEq(symbols2Long("b", base16_weird1), 1);

  Cs127A_v4.assertEq(symbols2Long("2", base10       ), 2);
  Cs127A_v4.assertEq(symbols2Long("z", base10_weird1), 2);
  Cs127A_v4.assertEq(symbols2Long("7", base10_weird2), 2);
  Cs127A_v4.assertEq(symbols2Long("10", base2        ), 2);
  Cs127A_v4.assertEq(symbols2Long("lq", base2_weird1 ), 2);
  Cs127A_v4.assertEq(symbols2Long("01", base2_weird2 ), 2);
  Cs127A_v4.assertEq(symbols2Long("2", base8        ), 2);
  Cs127A_v4.assertEq(symbols2Long("c", base8_weird1 ), 2);
  Cs127A_v4.assertEq(symbols2Long("3", base8_weird2 ), 2);
  Cs127A_v4.assertEq(symbols2Long("2", base16       ), 2);
  Cs127A_v4.assertEq(symbols2Long("2", base16b      ), 2);
  Cs127A_v4.assertEq(symbols2Long("c", base16_weird1), 2);

  Cs127A_v4.assertEq(symbols2Long("101", base10       ), 101);
  Cs127A_v4.assertEq(symbols2Long("lol", base10_weird1), 101);
  Cs127A_v4.assertEq(symbols2Long("898", base10_weird2), 101);
  Cs127A_v4.assertEq(symbols2Long("1100101", base2        ), 101);
  Cs127A_v4.assertEq(symbols2Long("llqqlql", base2_weird1 ), 101);
  Cs127A_v4.assertEq(symbols2Long("0011010", base2_weird2 ), 101);
  Cs127A_v4.assertEq(symbols2Long("145", base8        ), 101);
  Cs127A_v4.assertEq(symbols2Long("bef", base8_weird1 ), 101);
  Cs127A_v4.assertEq(symbols2Long("256", base8_weird2 ), 101);
  Cs127A_v4.assertEq(symbols2Long("65", base16       ), 101);
  Cs127A_v4.assertEq(symbols2Long("65", base16b      ), 101);
  Cs127A_v4.assertEq(symbols2Long("gf", base16_weird1), 101);

  boolean thrown;
  // 1st test
  System.out.println("Testing to ensure that the method throws an exception properly (test 1 of 4)...");
  thrown = false;
  try {
   long l = symbols2Long("", base10);
   System.out.println("OOPS: Your code did not throw an exception like it was supposed to!  symbols2Long(\"\",...) returned "+l);
  }
  catch (IllegalArgumentException e)
  {
   System.out.println("Exception thrown as expected.");
   thrown = true;
  }
  Cs127A_v4.assertEq(thrown, true);

// 2nd test
  System.out.println("Testing to ensure that the method throws an exception properly (test 2 of 4)...");
  thrown = false;
  try {
   long l = symbols2Long("01", base10);
   System.out.println("OOPS: Your code did not throw an exception like it was supposed to!  symbols2Long(\"01\",...) returned "+l);
  }
  catch (IllegalArgumentException e)
  {
   System.out.println("Exception thrown as expected.");
   thrown = true;
  }
  Cs127A_v4.assertEq(thrown, true);

  // 3rd test

  System.out.println("Testing to ensure that the method throws an exception properly (test 3 of 4)...");
  thrown = false;
  try {
   long l = symbols2Long("x", base10);
   System.out.println("OOPS: Your code did not throw an exception like it was supposed to!  symbols2Long(\"x\",...) returned "+l);
  }
  catch (IllegalArgumentException e)
  {
   System.out.println("Exception thrown as expected.");
   thrown = true;
  }
  Cs127A_v4.assertEq(thrown, true);

  // 4th test

  System.out.println("Testing to ensure that the method throws an exception properly (test 4 of 4)...");
  thrown = false;
  try {
   char[] tooShort = {'a'};
   long l = symbols2Long("a", tooShort);
   System.out.println("OOPS: Your code did not throw an exception like it was supposed to!  symbols2Long(\"a\",{'a'}) returned "+l);
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

