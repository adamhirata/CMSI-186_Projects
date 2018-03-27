/** ==================================================
  * File Name     : SoccerSim.java
  * Author        : Adam Hirata (UID: 956306941)
  * Date Created  : 3/26/2018
  * Description   : A program that simulates balls
  *                 moving around a soccer field
  *                 and is used in conjunction with
  *                 Ball.java and Clock.java
  * Notes         : Does not completely ignore balls
  *                 which are at rest, still prints
  *                 rested ball's position and velocity
  *                 every tick. Also checks for
  *                 collisions with stopped balls, so
  *                 long as the rested ball is still
  *                 in bounds.
  * ==================================================*/


import java.text.DecimalFormat;

public class SoccerSim {
   private static String errorMessage = new String("  [!] Error. Correct Usage:\n  java Soccer Sim x, y, xSpeed, ySpeed, ... [timeSlice]\n\n    add as many quartets of x, y, xSpeed, and ySpeed as you want\n    just so long as each quartet is ordered correctly internally\n\n  NOTE: all arguments should be double-like values\n  EXTRA NOTE: the field is 360 x 240 with the center at (0,0)\n\n    ");

   /**===================================================
   Method to test if there is a valid number of arguments
   ====================================================*/
   
   public static void testArgs (String args[]) {
      if (((args.length % 4) == 2) || ((args.length % 4) == 3) || (args.length < 4)) {
         System.out.println("\n\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
                            "\nThe amount of arguments is incorrect... please try again\n" + 
                            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n" + errorMessage);
         System.exit(1);
      }
   }
   
   /**==============================================================================================
   Method to create all the balls on the field, also checks the valididty of the time slice argument
   ===============================================================================================*/
   
   public static Ball[] addBalls(String args[]) {
      Ball[] result    = null;
      double timeSlice = 1.0;
      int arrLength    = args.length / 4;
      
      if (1 == (args.length % 4)) {
         try {
            if (-1 == Clock.validateTimeSliceArg(args[args.length - 1])) {
               throw new IllegalArgumentException();
            }
         } catch (Exception e) {
            System.out.println("\n\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
                               "\nThe time slice argument must be a double-like value between 0 and 1800... please try again\n" + 
                               "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n" + errorMessage);
            System.exit(1);
         }
         timeSlice = Double.parseDouble(args[args.length - 1]);
         arrLength = (args.length - 1) / 4;
      }
      
      result = new Ball[arrLength];
      int j = 0;
      for (int i = 0; j < (arrLength * 4); i++) {
         try {
            result[i] = new Ball(Double.parseDouble(args[j]), Double.parseDouble(args[j + 1]), Double.parseDouble(args[j + 2]), Double.parseDouble(args[j + 3]), timeSlice);
            j += 4;
         } catch (Exception e) {
            System.out.println("\n\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"+ 
                               "Error creating new ball... please try again\n" + 
                               "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n" + errorMessage);
            System.exit(1);
         }
      }
      return result;
   }
   
   /**============
   The main method
   =============*/
   
   public static void main(String args[]) {
      Ball[] allBalls        = null;
      DecimalFormat template = new DecimalFormat("##0.00");
      double timeSlice       = 1.0;
      boolean isThereMotion  = true;
      
      SoccerSim.testArgs(args);
      allBalls = SoccerSim.addBalls(args);
      
      //quickly making a clock to keep track of the current time
      if (1 == (args.length % 4)) {
         timeSlice = Double.parseDouble(args[args.length - 1]);
      }
      Clock clock = new Clock(timeSlice);
      
      //quickly making a pole (actually a ball object)
      Ball pole = new Ball((Math.random() * 360) - 179, (Math.random() * 240) - 119, 0, 0, timeSlice);
      
      System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n" +
                         "Beginning tests for collision with a " + template.format(timeSlice) + " second time slice...\n" +
                         "There is a pole located at " + pole.toString());
      
      //while loop that only stops when all balls are at rest
      while (isThereMotion) {
         
         //printing each ball
         System.out.println("\n\n[!] Gathering report for time: " + clock.toString());
         for (int i = 0; i < allBalls.length; i++) {
            System.out.println("      Ball #" + (i + 1) + ":   " + allBalls[i].toString());
            if (allBalls[i].didCollide(pole)) {
               System.out.println("\n\n\n[!] COLLISION: Ball #" + (i + 1) + " collided with the pole");
               System.exit(0);
            }
         }
         
         //checking for collisions
         for (int i = 0; i < allBalls.length; i++) {
            for (int j = 0; j < allBalls.length; j++) {
               if ((i != j) && allBalls[i].didCollide(allBalls[j]) && (179 > Math.abs(allBalls[i].getX())) && (119 > Math.abs(allBalls[i].getY()))) {
                  System.out.println("\n\n\n[!] COLLISION: Ball #" + (i + 1) + " collided with Ball #" + (j + 1) + "\nEnding the program...");
                  System.exit(0);
               }
            }
         }
         
         //stopping the program if all balls are at rest
         int counter = 0;
         for (int i = 0; i < allBalls.length; i++) {
            if (allBalls[i].isAtRest()) {
               counter++;
            }
         }
         if (counter == allBalls.length) {
            break;
         }
         
         //moving all the balls and progressing the clock
         for (int i = 0; i < allBalls.length; i++) {
            allBalls[i].move();
         }
         clock.tick();
      }
      
      //will only reach here if all balls become stopped, program should have stopped otherwise
      System.out.println("\n\n\n[!] REACHED END OF PROGRAM... NO COLLISION POSSIBLE\nExiting the program...");
   }
}