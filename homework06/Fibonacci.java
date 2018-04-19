/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File Name  : Fibonacci.java
 * Purpose     :  Test Harness for the BrobInt java class
 * @author     :  Adam Hirata
 * Date        :  2018-4-18
 * Description :  Simple program to find the nth term in the Fibonacci sequence
 * Notes       :  Finds the term iteratively, not recursively
 * Warnings    :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class Fibonacci {

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  The main (and only) method for Fibonacci.java
   *  @param   String[]    from the command line arguments
   *    Note:  checks the validity of the argument without helper methods
   * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static void main ( String args[] ) {   
      int n = 0;
      BrobInt dummy = null;
      BrobInt brob0 = new BrobInt( "0" );
      BrobInt brob1 = new BrobInt( "1" );
      
      //checking for the right amount of arguments
      if ( args.length != 1 ) {
         System.out.println( "Incorrect number of arguments...\n" +
                             "  Correct Usage: java Fibonacci <positive integer>\n" +
                             "  Please try again...");
         System.exit( 1 );
      }
      
      //checking the validity of the argument passed
      try {
         n = Integer.parseInt( args[0] );
         if ( 0 >= n ) {
            throw new IllegalArgumentException ();
         }
      } catch ( Exception e ) {
         System.out.println( "Invalid argument passed\n" +
                             "  Correct Usage: java Fibonacci <positive integer>\n" +
                             "  Please try again...");
         System.exit( 1 );
      }
      
      System.out.println( "\n\n\n\n\n==========================================\n" +
                          "Hello World, from the Fibonacci program...\n" +
                          "==========================================\n" );
                          
      //now calculating the element of the series at n
      if ( ( n == 1 ) || ( n == 2 ) ) {
         System.out.println( "Element of the Fibonacci Sequence at index: " + n +
                             "\n  " + ( n - 1 ) );
         System.exit( 0 );
      }
      
      for ( int i = 0; i < ( n - 2 ); i++ ) {
         dummy = new BrobInt( brob0.add( brob1 ).toString() );
         brob0.internalValue = brob1.toString();
         brob1.internalValue = dummy.toString();
         brob0.updateArray();
         brob1.updateArray();
      }
      
      //printing said element and closing the program
      System.out.println( "Element of the Fibonacci Sequence at index: " + n +
                          "\n  " + dummy.toString() +
                          "\n\nClosing program...");
      System.exit( 0 );
   }
}