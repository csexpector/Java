public class Cs127A_v4
{
 private static int testcaseCount = 0;
 private static int testcaseFails = 0;

 /**
  * Compares a calculated integer to an expected one; reports an
  * error (and increments the Fail count) if they do not match.
  * <p>
  * Is silent if the testcase passes - although it does increment
  * the testcase count.
  */
 public static void assertEq(long value, long expected)
 {
  if (value != expected)
  {
   testcaseFails++;
   System.out.printf("TESTCASE %d FAILED: expected=%d actual=%d\n", testcaseCount, expected,value);
  }

  testcaseCount++;
 }
 public static void assertEq(double value, double expected)
 {
  if (value != expected)
  {
   testcaseFails++;
   System.out.printf("TESTCASE %d FAILED: expected=%d actual=%d\n", testcaseCount, expected,value);
  }

  testcaseCount++;
 }
 public static void assertEq(char value, char expected)
 {
  if (value != expected)
  {
   testcaseFails++;
   System.out.printf("TESTCASE %d FAILED: expected=%d actual=%d\n", testcaseCount, expected,value);
  }

  testcaseCount++;
 }
 public static void assertEq(boolean value, boolean expected)
 {
  if (value != expected)
  {
   testcaseFails++;
   System.out.printf("TESTCASE %d FAILED: expected=%b actual=%b\n", testcaseCount, expected,value);
  }

  testcaseCount++;
 }
 public static void assertEq(String value, String expected)
 {
  if ( ! value.equals(expected))
  {
   testcaseFails++;
   System.out.printf("TESTCASE %d FAILED: expected=%s actual=%s\n", testcaseCount, expected,value);
  }

  testcaseCount++;
 }



 /**
  * Compares an integer array to an expected one; the two arrays *MUST*
  * be different arrays, but they are expected to have the same
  * contents.
  */
 public static void assertEq(int[] value, int[] expected)
 {
  if (value == expected)
  {
   testcaseFails++;
   System.out.printf("TESTCASE %d FAILED: The two arrays are the same array, which is not allowed.\n", testcaseCount);
  }

  else if (value.length != expected.length)
  {
   testcaseFails++;
   System.out.printf("TESTCASE %d FAILED: expected=", testcaseCount);

   dumpArray(expected);
   System.out.printf(" actual=");
   dumpArray(value);
   System.out.println();
  }

  else for (int i=0; i<value.length; i++)
   if (value[i] != expected[i])
   {
    testcaseFails++;
    System.out.printf("TESTCASE %d FAILED: expected=", testcaseCount);

    dumpArray(expected);
    System.out.printf(" actual=");
    dumpArray(value);
    System.out.println();

    break;
   }

  testcaseCount++;
 }



 /**
  * Compares an character array to an expected one; the two arrays *MUST*
  * be different arrays, but they are expected to have the same
  * contents.
  */
 public static void assertEq(char[] value, char[] expected)
 {
  if (value == expected)
  {
   testcaseFails++;
   System.out.printf("TESTCASE %d FAILED: The two arrays are the same array, which is not allowed.\n", testcaseCount);
  }

  else if (value.length != expected.length)
  {
   testcaseFails++;
   System.out.printf("TESTCASE %d FAILED: expected=", testcaseCount);

   dumpArray(expected);
   System.out.printf(" actual=");
   dumpArray(value);
   System.out.println();
  }

  else for (int i=0; i<value.length; i++)
   if (value[i] != expected[i])
   {
    testcaseFails++;
    System.out.printf("TESTCASE %d FAILED: expected=", testcaseCount);

    dumpArray(expected);
    System.out.printf(" actual=");
    dumpArray(value);
    System.out.println();

    break;
   }

  testcaseCount++;
 }



 public static void dumpArray(int[] array)
 {
  System.out.print("{");

  for (int i=0; i<array.length; i++)
  {
   if (i != 0)
    System.out.print(",");
   System.out.print(array[i]);
  }

  System.out.print("}");
 }



 public static void dumpArray(char[] array)
 {
  System.out.print("{");

  for (int i=0; i<array.length; i++)
  {
   if (i != 0)
    System.out.print(",");
   System.out.print(array[i]);
  }

  System.out.print("}");
 }



 public static void reportResults()
 {
  System.out.printf("RESULTS: testcaseCount=%d testcaseFails=%d\n", testcaseCount, testcaseFails);
 }
}

