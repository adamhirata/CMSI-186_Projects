/**==========================================

  File name:    Die.java
  Date Created: 02/03/2018
  Author:       Adam Hirata
  UID:          956306941
  Description:  A program with the public
                class HighRoll that emulates
                a dice game.
  Last Edit:    2/21/18
  
===========================================*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HighRoll {
   
   private static boolean resume   = false;  //determines whether the user made it past creating the dice set
   private static DiceSet playDice = null;   //the dice set which the user will be playing with
   private static int highScore    = 0;      //current high score
   
   public static void main(String args[]) {
    
      System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n== Welcome to the High Roll game! ==");
      BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
      String inputLine     = null;
      
      /**====================================
      A while-loop that prompts for arguments
      until the user enters a valid number of
      dice and a valid number of sides
      =====================================*/

      while (true) {
         int dice  = 0;
         int sides = 0;
         
         /**===================================
         Asking the user for the number of dice
         ====================================*/
         
         System.out.println("\n    Enter the number of dice for the game (should be greater than 1):");
         System.out.println("    Type \"quit\" to quit\n");
         System.out.print(">>");
         try {
            inputLine = input.readLine();
            if (inputLine.equals("quit")) {
               System.out.println("Exiting the game...");
               break;
            }
            try {
               dice = Integer.parseInt(inputLine);
            }
            catch(NumberFormatException nfe) {}
         }
         catch(IOException ioe) {
            System.out.println("Caught IOException");
         }
         
         /**================================================
         Asking the user for the number of sides on each die
         =================================================*/
         
         System.out.println("\n    Now, enter the number of sides each die has (should be greater than 4):");
         System.out.println("    Type \"quit\" to quit\n");
         System.out.print(">>");
         try {
            inputLine = input.readLine();
            if (inputLine.equals("quit")) {
               System.out.println("Exiting the game...");
               break;
            }
            try {
               sides = Integer.parseInt(inputLine);
            }
            catch(NumberFormatException nfe) {}
         }
         catch(IOException ioe) {
            System.out.println("Caught IOException");
         }
         
         /**=========================================
         Checking to see if the user had valid inputs
         ==========================================*/

         try {
            playDice = new DiceSet(dice, sides);
         }
         catch(IllegalArgumentException e) {
         System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n    [!] The arguments you passed were not valid, please try again. [!]");
         }
         try {
            if (playDice.toString().charAt(0) == '[') {
               resume = true;
               break;
            }
         }
         catch(NullPointerException npe) {}
      }
      
      /**=========================================
      New while-loop that contains the actual game
      ==========================================*/
      
      if (resume) {
         while (true) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n    Your current dice set: " + playDice.toString());
            System.out.println("    Choose an option below:"      );
            System.out.println("      1: Roll all the dice"       );
            System.out.println("      2: Roll a single die"       );
            System.out.println("      3: Calculate current score" );
            System.out.println("      4: Save as high score"      );
            System.out.println("      5: Display high score"      );
            System.out.println("      6: Quit or type \"quit\" to quit\n");
            System.out.print(">>");
            
            /**=============================
            Handling all the possible inputs
            ==============================*/
            
            try {
               inputLine = input.readLine();
               switch (inputLine) {
                  case "1": playDice.roll();
                            System.out.print("    Rolling... Click enter to continue");
                            try {inputLine = input.readLine();}
                            catch(IOException ioe) {}
                            break;
                  case "2": System.out.println("    Select which die you would like to roll");
                            System.out.print(">>");
                            try {
                               inputLine = input.readLine();
                               try {
                                  playDice.rollIndividual(Integer.parseInt(inputLine));
                               }
                               catch (Exception e) {
                                  System.out.println("    Invalid number, returning to menu...");
                               }
                            }
                            catch(IOException ioe) {}
                            System.out.print("    Click enter to continue");
                            try {inputLine = input.readLine();}
                            catch(IOException ioe) {}
                            break;
                  case "3": System.out.println("    Your current score is: " + playDice.sum());
                            System.out.print("    Click enter to continue");
                            try {inputLine = input.readLine();}
                            catch(IOException ioe) {}
                            break;
                  case "4": System.out.println("    Saving high score as " + playDice.sum() + "...");
                            highScore = playDice.sum();
                            System.out.print("    Click enter to continue");
                            try {inputLine = input.readLine();}
                            catch(IOException ioe) {}
                            break;
                  case "5": System.out.println("    Current high score: " + highScore);
                            highScore = playDice.sum();
                            System.out.print("    Click enter to continue");
                            try {inputLine = input.readLine();}
                            catch(IOException ioe) {}
                            break;
                  case "6":
                  case "quit": System.out.print("    Thanks for playing! Exiting the game...");
                               System.exit(-1);
                               break;
                  default: System.out.println("    Not a valid input, returning to menu...");
                           System.out.print("    Click enter to continue");
                           try {inputLine = input.readLine();}
                           catch(IOException ioe) {}
                           break;
               }
            }
            catch(IOException ioe) {
               System.out.println("Caught IOException");
            }
         }
      }
   }
}
