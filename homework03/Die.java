/**==========================================

  File name:    Die.java
  Date Created: 02/03/2018
  Author:       Adam Hirata
  UID:          956306941
  Description:  A program with the public
                class "Die" that emulates a 
                dice with n number of sides;
                n is an int.
  Last Edit:    2/17/18
  
===========================================*/

public class Die {
   
   private int sides;
   private int currentVal;
   
   //Constructor for Die class
   public Die(int nSides) {
      if (nSides >= 4) {
         this.sides = nSides;
      } else {
         throw new IllegalArgumentException("The number of sides of your die must be greater than 4");
      }
      this.currentVal = 0;
   }
   
   /** Method to get a random roll and store the current die's value
       @param   none
       @return  int   random integer between 1 and nSides
   */
   public int roll() {
      double roll = Math.random() * (double) this.sides;
      this.currentVal = (int) roll + 1;
      return (int) roll;
   }
   
   /** Method to get the current value of the die
       @param   none
       @return  int   the currentVal of this specific die
   */
   public int getValue() {
      return this.currentVal;
   }
   
   /** Method to set the sides of the die to a different value
       @param   int   the desired number of sides
       @return  int   the current number of sides of this die
   */
   public int setSides(int nSides) {
      if (nSides >= 4) {
         this.sides = nSides;
      } else {
         throw new IllegalArgumentException("The number of sides of your die must be at least 4");
      }
      return this.sides;
   }
   
   /** Method to get a string representation of this die
       @param   none
       @return  String  the number value of the die as a string
   */
   public String toString() {
      return "[" + Integer.toString(this.getValue()) + "]";
   }
   
   /** Method to get a string representation of this die (static)
       @param   none
       @return  String  the number value of the die as a string
   */
   public static String toString(Die d) {
      return "[" + Integer.toString(d.getValue()) + "]";
   }
      
   
   public static void main(String args[]) {
      Die d = new Die(6);
      
      //tests for Die class
      System.out.println("=== Tests for Die class methods ===");
      System.out.println("    (roll 1) die has 6 sides:\n    Rolling...");
      d.roll();
      System.out.println("    Current Value: " + d.getValue());
      System.out.println("    String form: " + d.toString());
      System.out.println("\n    (roll 2) die has 6 sides:\n    Rolling...");
      d.roll();
      System.out.println("    Current Value: " + d.getValue());
      System.out.println("    String form: " + d.toString());
      System.out.println("\n    (roll 3) die has 6 sides:\n    Rolling...");
      d.roll();
      System.out.println("    Current Value: " + d.getValue());
      System.out.println("    String form: " + d.toString());
      d.setSides(700);
      System.out.println("\n    [!]: die now has 700 sides [!]\n");
      System.out.println("    (roll 4) die has 700 sides:\n    Rolling...");
      d.roll();
      System.out.println("    Current Value: " + d.getValue());
      System.out.println("    String form: " + d.toString());
      System.out.println("\n    (roll 5) die has 700 sides:\n    Rolling...");
      d.roll();
      System.out.println("    Current Value: " + d.getValue());
      System.out.println("    String form: " + d.toString());
      System.out.println("\n    (roll 6) die has 700 sides:\n    Rolling...");
      d.roll();
      System.out.println("    Current Value: " + d.getValue());
      System.out.println("    String form: " + d.toString());
   }
}