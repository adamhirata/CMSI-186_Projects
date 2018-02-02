/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  StringStuff.java
 *  Purpose       :  A file full of stuff to do with the Java String class
 *  Author        :  B.J. Johnson
 *  Date          :  2017-01-19
 *  Description   :  This file presents a bunch of String-style helper methods.  Although pretty much
 *                   any and every thing you'd want to do with Strings is already made for you in the
 *                   Java String class, this exercise gives you a chance to do it yourself [DIY] for some
 *                   of it and get some experience with designing code that you can then check out using
 *                   the real Java String methods [if you want]
 *  Student Author:  Adam Hirata
 *  Date          :  2018-01-27
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-01-19  B.J. Johnson  Initial writing and release
 *  @version 1.1.0  2017-01-22  B.J. Johnson  Fill in methods to make the program actually work
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Set;
import java.util.LinkedHashSet;

public class StringStuff {

  /**
   * Method to determine if a string contains one of the vowels: A, E, I, O, U, and sometimes Y.
   * Both lower and upper case letters are handled.  In this case, the normal English rule for Y means
   * it gets included.
   *
   * @param s String containing the data to be checked for &quot;vowel-ness&quot;
   * @return  boolean which is true if there is a vowel, or false otherwise
   */
   public static boolean containsVowel(String s) {
      for (int i = 0; i < s.length(); i++)
         switch (s.charAt(i)) {
            case 'A': return true;
            case 'E': return true;
            case 'I': return true;
            case 'O': return true;
            case 'U': return true;
            case 'a': return true;
            case 'e': return true;
            case 'i': return true;
            case 'o': return true;
            case 'u': return true;
            case 'Y': return true;
            case 'y': return true;
            default :            ;
         }
      return false;
   }

  /**
   * Method to determine if a string is a palindrome.  Does it the brute-force way, checking
   * the first and last, second and last-but-one, etc. against each other.  If something doesn't
   * match that way, returns false, otherwise returns true.
   *
   * @param s String containing the data to be checked for &quot;palindrome-ness&quot;
   * @return  boolean which is true if this a palindrome, or false otherwise
   */
   public static boolean isPalindrome(String s) {
      int j = s.length() - 1;
      for (int i = 0; i < s.length(); i++) {
         if (s.charAt(i) != s.charAt(j - i)) {
            return false;
         }
      }
      return true;
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;EVEN&quot; index
   * numbers of the alphabet.  The letters B, D, F, H, J, L, N, P, R, T, V, X, and Z are even,
   * corresponding to the numbers 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, and 26.
   *
   * @param s String containing the data to be parsed for &quot;even&quot; letters
   * @return  String containing the &quot;even&quot; letters from the input
   */
   public static String evensOnly(String s) {
      String evens  = "bdfhjlnprtvxzBDFHJLNPRTVXZ";
      StringBuffer result = new StringBuffer();
      for (int i = 0; i < s.length(); i++) {
         for (int j = 0; j < evens.length(); j++) {
            if (evens.charAt(j) == s.charAt(i)) {
               result.append(s.charAt(i));
            }
         }
      }
      return result.toString();
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;ODD&quot; index
   * numbers of the alphabet.  The letters A, C, E, G, I, K, M, O, Q, S, U, W, and Y are odd,
   * corresponding to the numbers 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, and 25.
   *
   * @param s String containing the data to be parsed for &quot;odd&quot; letters
   * @return  String containing the &quot;odd&quot; letters from the input
   */
   public static String oddsOnly(String s) {
      String odds  = "acegikmoqsuwyACEGIKMOQSUWY";
      StringBuffer result = new StringBuffer();
      for (int i = 0; i < s.length(); i++) {
         for (int j = 0; j < odds.length(); j++) {
            if (odds.charAt(j) == s.charAt(i)) {
               result.append(s.charAt(i));
            }
         }
      }
      return result.toString();
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;EVEN&quot; index
   * numbers of the alphabet, but with no duplicate characters in the resulting string.
   *
   * @param s String containing the data to be parsed for &quot;even&quot; letters
   * @return  String containing the &quot;even&quot; letters from the input without duplicates
   */
   public static String evensOnlyNoDupes(String s) {
      StringBuffer result = new StringBuffer(StringStuff.evensOnly(s));
      for (int i = 0; i < result.length() - 1; i++) {
         for (int j = i + 1; j < result.length(); j++) {
            if (result.charAt(i) == result.charAt(j)) {
               result.deleteCharAt(j);
               j -= 1;
            }
         }
      }
      return result.toString();
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;ODD&quot; index
   * numbers of the alphabet, but with no duplicate characters in the resulting string.
   *
   * @param s String containing the data to be parsed for &quot;odd&quot; letters
   * @return  String containing the &quot;odd&quot; letters from the input without duplicates
   */
   public static String oddsOnlyNoDupes(String s) {
      StringBuffer result = new StringBuffer(StringStuff.oddsOnly(s));
      for (int i = 0; i < result.length() - 1; i++) {
         for (int j = i + 1; j < result.length(); j++) {
            if (result.charAt(i) == result.charAt(j)) {
               result.deleteCharAt(j);
               j -= 1;
            }
         }
      }
      return result.toString();
   }

  /**
   * Method to return the reverse of a string passed as an argument
   *
   * @param s String containing the data to be reversed
   * @return  String containing the reverse of the input string
   */
   public static String reverse(String s) {
      StringBuffer result = new StringBuffer(s);
      return result.reverse().toString();
   }

  /**
   * Main method to test the methods in this class
   *
   * @param args String array containing command line parameters
   */
   public static void main(String args[]) {
      String vowels1  = new String("Hello");
      String vowels2  = new String("HEllO");
      String noVowels = new String("c4n't vnd3rst4nd th1s");
      String testForY = new String("why");
      String pal1 = new String("a");
      String pal2 = new String("adam");
      String pal3 = new String("madamimadam");
      String pal4 = new String("appa");
      System.out.println("\n==========================\nTests for StringStuff.java\n==========================\n");
      
      //tests for containsVowel
      
      System.out.println("=== Tests for containsVowel ===\n");
      System.out.println("Input: \"Hello\"                 Should return: true.  Result: " + containsVowel(vowels1));
      System.out.println("Input: \"HEllO\"                 Should return: true.  Result: " + containsVowel(vowels2));
      System.out.println("Input: \"c4n't vnd3rst4nd th1s\" Should return: false. Result: " + containsVowel(noVowels));
      System.out.println("Input: \"why\"                   Should return: true.  Result: " + containsVowel(testForY));
      
      //tests for isPalindrome
      
      System.out.println("\n=== Tests for isPalindrome ===\n");
      System.out.println("Input: \"a\"                     Should return: true.  Result: " + isPalindrome(pal1));
      System.out.println("Input: \"adam\"                  Should return: false. Result: " + isPalindrome(pal2));
      System.out.println("Input: \"12345678987654321\"     Should return: true.  Result: " + isPalindrome("12345678987654321"));
      System.out.println("Input: \"123445432\"             Should return: false. Result: " + isPalindrome("123445432"));
      System.out.println("Input: \"madamimadam\"           Should return: true.  Result: " + isPalindrome(pal3));
      System.out.println("Input: \"appa\"                  Should return: true.  Result: " + isPalindrome(pal1));

      //tests for evensOnly

      System.out.println("\n=== Tests for evensOnly ===\n");
      System.out.println("Input: \"acegikmoqsuwyACEGIKMOQSUWY\" Should return:                  Returns: " + evensOnly("acegikmoqsuwyACEGIKMOQSUWY"));
      System.out.println("Input: \"aaAbbBccCddDeeEffF\"         Should return: bbBddDffF        Returns: " + evensOnly("aaAbbBccCddDeeEffF"));
      System.out.println("Input: \"Adam Is My Name\"            Should return: dN               Returns: " + evensOnly("Adam Is My Name"));
      
      //tests for evensOnlyNoDupes
      
      System.out.println("\n=== Tests for evensOnlyNoDupes ===\n");
      System.out.println("Input: \"acegikmoqsuwyACEGIKMOQSUWY\" Should return:                  Returns: " + evensOnlyNoDupes("acegikmoqsuwyACEGIKMOQSUWY"));
      System.out.println("Input: \"aaAbbBccCddDeeEffF\"         Should return: bBdDfF           Returns: " + evensOnlyNoDupes("aaAbbBccCddDeeEffF"));
      System.out.println("Input: \"Adam Is My Name\"            Should return: dN               Returns: " + evensOnlyNoDupes("Adam Is My Name"));
      
      //tests for oddsOnly
      
      System.out.println("\n=== Tests for oddsOnly ===\n");
      System.out.println("Input: \"bdfhjlnprtvxzBDFHJLNPRTVXZ\" Should return:                  Returns: " + oddsOnly("bdfhjlnprtvxzBDFHJLNPRTVXZ"));
      System.out.println("Input: \"aaAbbBccCddDeeEffF\"         Should return: aaAccCeeE        Returns: " + oddsOnly("aaAbbBccCddDeeEffF"));
      System.out.println("Input: \"Adam Is My Name\"            Should return: AamIsMyame       Returns: " + oddsOnly("Adam Is My Name"));
      
      //tests for oddsOnlyNoDupes
      
      System.out.println("\n=== Tests for oddsOnlyNoDupes ===\n");
      System.out.println("Input: \"bdfhjlnprtvxzBDFHJLNPRTVXZ\" Should return:                  Returns: " + oddsOnlyNoDupes("bdfhjlnprtvxzBDFHJLNPRTVXZ"));
      System.out.println("Input: \"aaAbbBccCddDeeEffF\"         Should return: aAcCeE           Returns: " + oddsOnlyNoDupes("aaAbbBccCddDeeEffF"));
      System.out.println("Input: \"Adam Is My Name\"            Should return: AamIsMye         Returns: " + oddsOnlyNoDupes("Adam Is My Name"));
      
      //tests for reverse
      
      System.out.println("\n=== Tests for reverse ===\n");
      System.out.println("Input: \"tset a si sihT\"             Should return: This is a test   Returns: " + reverse("tset a si sihT"));
      System.out.println("Input: \"esrever\"                    Should return: reverse          Returns: " + reverse("esrever"));
      System.out.println("Input: \"Adam Is My Name\"            Should return: emaN yM sI madA  Returns: " + reverse("Adam Is My Name"));
   }
}
