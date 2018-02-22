/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  DiceSet.java
 *  Purpose       :  Provides a class describing a set of dice
 *  Author        :  B.J. Johnson
 *  Student       :  Adam Hirata (UID 956306941)
 *  Date          :  2017-02-09
 *  Description   :  This class provides everything needed (pretty much) to describe a set of dice.  The
 *                   idea here is to have an implementing class that uses the Die.java class.  Includes
 *                   the following:
 *                   public DiceSet( int k, int n );                  // Constructor for a set of k dice each with n-sides
 *                   public int sum();                                // Returns the present sum of this set of dice
 *                   public void roll();                              // Randomly rolls all of the dice in this set
 *                   public void rollIndividual( int i );             // Randomly rolls only the ith die in this set
 *                   public int getIndividual( int i );               // Gets the value of the ith die in this set
 *                   public String toString();                        // Returns a stringy representation of this set of dice
 *                   public static String toString( DiceSet ds );     // Classwide version of the preceding instance method
 *                   public boolean isIdentical( DiceSet ds );        // Returns true iff this set is identical to the set ds
 *                   public static void main( String[] args );        // The built-in test program for this class
 *
 *  Notes         :  Stolen from Dr. Dorin pretty much verbatim, then modified to show some interesting
 *                   things about Java, and to add this header block and some JavaDoc comments.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the number of sides or pips is out of range
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-09  B.J. Johnson  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public class DiceSet {

  /**
   * private instance data
   */
   private int count;
   private int sides;
   private Die[] ds = null;

   // public constructor:
  /**
   * constructor
   * @param  count int value containing total dice count
   * @param  sides int value containing the number of pips on each die
   * @throws IllegalArgumentException if one or both arguments don't make sense
   * @note   parameters are checked for validity; invalid values throw "IllegalArgumentException"
   */
   public DiceSet(int count, int sides) {
      ds = new Die[ count ];
      if ((count >= 1) && (sides >= 4)) {
         this.count = count;
         this.sides = sides;
      } else {
         throw new IllegalArgumentException("The number of dice must be at least 1, and the number of sides of each die must be at least 4");
      }
      for (int i = 0; i < this.count; i++) {
         this.ds[i] = new Die(this.sides);
         this.ds[i].roll();
      }
   }

  /**
   * @return the sum of all the dice values in the set
   */
   public int sum() {
      int result = 0;
      for (int i = 0; i < this.ds.length; i++) {
         result += this.ds[i].getValue();
      }
      return result;
   }

  /**
   * Randomly rolls all of the dice in this set
   *  NOTE: you will need to use one of the "toString()" methods to obtain
   *  the values of the dice in the set
   */
   public void roll() {
       for (int i = 0; i < this.ds.length; i++) {
         this.ds[i].roll();
      }
   }

  /**
   * Randomly rolls a single die of the dice in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die to roll
   * @return the integer value of the newly rolled die
   * @trhows IllegalArgumentException if the index is out of range
   */
   public int rollIndividual(int dieIndex) {
      return this.ds[dieIndex - 1].roll();
   }

  /**
   * Gets the value of the die in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die to roll
   * @trhows IllegalArgumentException if the index is out of range
   */
   public int getIndividual(int dieIndex) {
      return this.ds[dieIndex - 1].getValue();
   }

  /**
   * @return Public Instance method that returns a String representation of the DiceSet instance
   */
   public String toString() {
      StringBuffer result = new StringBuffer();
      for (int i = 0; i < this.ds.length; i++) {
         result.append(this.ds[i].toString());
      }
      return result.toString();
   }

  /**
   * @return Class-wide version of the preceding instance method
   */
   public static String toString(DiceSet ds) {
      return ds.toString();
   }

  /**
   * @return  true iff this set is identical to the set passed as an argument
   */
   public boolean isIdentical(DiceSet ds) {
      String first  = this.toString();
      String second = ds.toString();
      if (this.ds.length != ds.ds.length) {
         return false;
      }
      if (first.equals(second)) {
         return true;
      }
      return false;
   }
  /**
   * A little test main to check things out
   */
   public static void main(String[] args) {
      DiceSet test  = new DiceSet(10, 10);
      DiceSet test2 = new DiceSet(14, 10);
      DiceSet test3 = new DiceSet(10, 10);
      
      //tests for roll and toString
      System.out.println("\n\n\n=== Tests for roll() and toString() ===\nNOTE: DiceSet.toString(DiceSet test) uses test.toString\n");
      System.out.println("(roll 1) current set: " + DiceSet.toString(test) + " sum: " + test.sum());
      test.roll();
      System.out.println("(roll 2) current set: " + DiceSet.toString(test) + " sum: " + test.sum());
      test.roll();
      System.out.println("(roll 3) current set: " + DiceSet.toString(test) + " sum: " + test.sum());
      test.roll();
      System.out.println("(roll 4) current set: " + DiceSet.toString(test) + " sum: " + test.sum());
      test.roll();
      System.out.println("(roll 5) current set: " + DiceSet.toString(test) + " sum: " + test.sum());
      test.roll();
      System.out.println("(roll 6) current set: " + DiceSet.toString(test) + " sum: " + test.sum());
      
      //tests for rollIndividual
      System.out.println("\n=== Tests for rollIndividual() ===\n");
      System.out.println("current set: " + test.toString() + " die rolled: none\n");
      test.rollIndividual(1);
      System.out.println("current set: " + test.toString() + " die rolled: #1");
      test.rollIndividual(2);
      System.out.println("current set: " + test.toString() + " die rolled: #2");
      test.rollIndividual(3);
      System.out.println("current set: " + test.toString() + " die rolled: #3");
      test.rollIndividual(4);
      System.out.println("current set: " + test.toString() + " die rolled: #4");
      test.rollIndividual(5);
      System.out.println("current set: " + test.toString() + " die rolled: #5");
      test.rollIndividual(6);
      System.out.println("current set: " + test.toString() + " die rolled: #6");
      System.out.println("\ncurrent set: " + test.toString() + " die rolled: none\n");
      test.rollIndividual(1);
      System.out.println("current set: " + test.toString() + " die rolled: #1");
      test.rollIndividual(2);
      System.out.println("current set: " + test.toString() + " die rolled: #2");
      test.rollIndividual(3);
      System.out.println("current set: " + test.toString() + " die rolled: #3");
      test.rollIndividual(4);
      System.out.println("current set: " + test.toString() + " die rolled: #4");
      test.rollIndividual(5);
      System.out.println("current set: " + test.toString() + " die rolled: #5");
      test.rollIndividual(6);
      System.out.println("current set: " + test.toString() + " die rolled: #6");
      
      //tests for isIdentical
      System.out.println("\n=== Tests for isIdentical() ===\n");
      System.out.println("set 1: " + test.toString());
      System.out.println("set 2: " + test2.toString());
      System.out.println("Are they the same? " + test.isIdentical(test2));
      System.out.println("set 1: " + test.toString());
      System.out.println("set 2: " + test3.toString());
      System.out.println("Are they the same? " + test.isIdentical(test3));
      for (int i = 0; i < test.ds.length; i++) {
         while (true) {
            test3.ds[i].roll();
            if (test3.ds[i].getValue() == test.ds[i].getValue()) {
               break;
            }
         }
      }
      System.out.println("set 1: " + test.toString());
      System.out.println("set 2: " + test3.toString());
      System.out.println("Are they the same? " + test.isIdentical(test3));
      test.roll();
      System.out.println("set 1: " + test.toString());
      System.out.println("set 2: " + test.toString());
      System.out.println("Are they the same? " + test.isIdentical(test));
   }
}