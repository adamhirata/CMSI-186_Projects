/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File Name     :  DynamicChangeMaker.java
 *  Description   :  Has a method which creates the optimal number of
 *                   coins to make the desired amount of change
 *  @author       :  Adam Hirata (UID: 956306941)
 *  Date Created  :  04/26/18
 *  Last Edited   :  05/03/18
 *  Notes         :  None
 *  Warnings      :  None
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class DynamicChangeMaker {
   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to create the desired amount with the optimal number of coins
    *  @param     denominations  Integer array with the types of coins
    *  @param     target         The integer amount to create
    *  @return    Tuple          A tuple containing the amounts of each
    *                            type of coin needed to make the target
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static Tuple makeChangeWithDynamicProgramming ( int[] denominations, int target ) {
      //testing the validity of the arguments
      try {
         DynamicChangeMaker.validateArguments( denominations, target );
      } catch ( Exception e ) { 
         System.out.println( "BAD DATA: All denominations must be greater than 0 with no repeats, and the target must be a non-negative integer\n" );
         return new Tuple( 0 ); 
      }
      
      //vars for the dynamic programming algorithm
      int rowCount      = denominations.length;
      int colCount      = target + 1;
      Tuple[][] table   = new Tuple[rowCount][colCount];
      
      //dynamic programming algorithm
      for ( int i = 0; i < rowCount; i++ ) {
         for ( int j = 0; j < colCount; j++ ) {
            //creating the 0th column
            if ( 0 == j ) {
               table[i][j] = new Tuple( denominations.length );
            } else {
               //creating all other tuples
               if ( denominations[i] > j ) {
                  table[i][j] = Tuple.IMPOSSIBLE;
               } else {
                  table[i][j] = new Tuple( denominations.length );
                  table[i][j].setElement( i, 1 );
                  if ( table[i][j - denominations[i]].isImpossible() ) {
                     table[i][j] = Tuple.IMPOSSIBLE;
                  } else {
                     table[i][j] = table[i][j].add( table[i][j - denominations[i]] );
                  }
               }
            }
            if ( 0 != i ) {
               if ( table[i][j].isImpossible() ) {
                  table[i][j] = table[i - 1][j];
               }
               if ( ( table[i][j].total() > table[i - 1][j].total() ) && !table[i - 1][j].isImpossible() ) {
                  table[i][j] = table[i - 1][j];
               }
            }
         }
      }
      return table[rowCount - 1][colCount - 1];
   }
   
   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to test the arguments passed into makeChangeWithDynamicProgramming
    *  @param     denominations  Integer array with the types of coins
    *  @param     target         The integer amount to create
    *  @return    void           Note: will throw an IllegalArgumentException
    *                            for faulty data
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static void validateArguments ( int[] denominations, int target ) throws IllegalArgumentException {
      for ( int i = 0; i < denominations.length; i++ ) {
         for ( int k = 0; k < denominations.length; k++ ) {
            if ( denominations[i] <= 0 ) {
               throw new IllegalArgumentException();
            } else if ( ( denominations[i] == denominations[k] ) && ( i != k ) ) {
               throw new IllegalArgumentException();
            }
         }
      }
      if ( target < 0 ) {
         throw new IllegalArgumentException();
      }
   }
}
