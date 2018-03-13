/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  ClockSolver.java
 *  Purpose       :  The main program for the ClockSolver class
 *  @see
 *  @author       :  B.J. Johnson
 *  @studentAuthor:  Adam Hirata (UID: 956306941)
 *  Date written  :  2017-02-28
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
  *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-28  B.J. Johnson  Initial writing and release
 *  @version 1.0.1  2018-03-13  Adam Hirata   Finished working simulation and submission
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

import java.text.DecimalFormat;

public class ClockSolver {
  /**
   *  Class field definintions go here
   */
   
   private static final double EPSILON_VALUE       = 0.1;      // small value for double-precision comparisons

  /**
   *  Constructor
   *  This just calls the superclass constructor, which is "Object"
   */
   public ClockSolver() {
      super();
   }

 /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  @param  args  String array of the arguments from the command line
   *                args[0] is the angle for which we are looking
   *                args[1] is the time slice; this is optional and defaults to 60 seconds
   */
   public static void main(String args[]) {
      
      //Variables for the main method
      
      ClockSolver cs             = new ClockSolver();
      Clock clocky               = null;
      DecimalFormat template     = new DecimalFormat("#0.0");
      double targetAngle;
      
      //Testing arguments and constructing the correct clock
      
      cs.handleInitialArguments(args);
      targetAngle = Double.parseDouble(args[0]) % 360;
      clocky = cs.clockBuilder(args);
      
      System.out.print("    Finding angle of " + template.format(targetAngle) + 
                       "\n    With a time slice of "                        );
      try {
         System.out.println(template.format(Double.parseDouble(args[1])));
      } catch (Exception e) {
         System.out.println("60.0 (default)");
      }
      System.out.println("    For best results with my epsilon value (0.1), try a time slice of 1.0 or less\n\n");
      
      //while-loop to calculate and print the results
      
      while(clocky.getTotalSeconds() <= 43200) {
         if ((Math.abs(clocky.getHandAngle() - targetAngle) < EPSILON_VALUE) || (Math.abs(clocky.getHandAngle() - (360 - targetAngle)) < EPSILON_VALUE)) {
            System.out.println("    === Found target angle at " + clocky.toString());
         }
         clocky.tick();
      }
      System.out.println("\n    [!] NOTE: For best results with my epsilon value (0.1), try a time slice of 1.0 or less");
      System.out.println("    Exiting program...\n");
      System.exit(0);
   }
   
   /**
   *  Method to handle all the input arguments from the command line
   *   this sets up the variables for the simulation
   */
   public void handleInitialArguments(String args[]) {
     // args[0] specifies the angle for which you are looking
     //  your simulation will find all the angles in the 12-hour day at which those angles occur
     // args[1] if present will specify a time slice value; if not present, defaults to 60 seconds
     // you may want to consider using args[2] for an "angle window"

      System.out.println("\n   Hello world, from the ClockSolver program!!\n\n") ;   
      if(0 == args.length) {
         System.out.println("   Sorry you must enter at least one argument\n" +
                            "   Usage: java ClockSolver <angle> [timeSlice]\n" +
                            "   [!] NOTE: For best results with my epsilon value (0.1), \n       try a time slice of 1.0 or less\n" +
                            "   Please try again...........");
         System.exit(1);
      }
      
      //testing the angle argument
      try {
         Clock.validateAngleArg(args[0]);
      } catch (Exception e) {
         System.out.println("   Sorry you must enter at least one argument\n" +
                            "   Usage: java ClockSolver <angle> [timeSlice]\n" +
                            "   <angle> must be a positive double-like value\n" +
                            "   [!] NOTE: For best results with my epsilon value (0.1), \n       try a time slice of 1.0 or less\n" +
                            "   Please try again...........");
         System.exit(1);
      }
      
      //testing to see if a valid timeSlice argument was given
      try {
         if (2 == args.length) {
            if (-1.0 == Clock.validateTimeSliceArg(args[1])) {
               throw new NumberFormatException("null");
            }
         }
      } catch (Exception e) {
         System.out.println("   Sorry you must enter at least one argument\n" +
                            "   Usage: java ClockSolver <angle> [timeSlice]\n" +
                            "   [timeSlice] must be a double-like value greater than zero, less than 1800.0\n" +
                            "   [!] NOTE: For best results with my epsilon value (0.1), \n       try a time slice of 1.0 or less\n" +
                            "   Please try again...........");
         System.exit(1);
      }
   }
   
   /**
   *  Method to create a new clock depending on the time slice from the command-line args
   *  Uses a time slice of 60.0 if there is no user input for the time slice
   */
   public Clock clockBuilder (String args[]) {
      try {
         Clock clock1 = new Clock(Double.parseDouble(args[1]));
         return clock1;
      } catch (Exception e) {}
      Clock clock1 = new Clock(60.0);
      return clock1;
   }
}