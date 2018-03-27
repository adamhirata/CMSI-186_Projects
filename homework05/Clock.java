/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Clock.java
 *  Purpose       :  Provides a class defining methods for the ClockSolver class
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

public class Clock {
  /**
   *  Class field definintions go here
   */
   private static final double DEFAULT_TIME_SLICE_IN_SECONDS   = 60.0;
   private static final double INVALID_ARGUMENT_VALUE          = -1.0;
   private static final double MAXIMUM_DEGREE_VALUE            = 360.0;
   private static final double HOUR_HAND_DEGREES_PER_SECOND    = 0.00834;
   private static final double MINUTE_HAND_DEGREES_PER_SECOND  = 0.1;
   private static double TICK                                  = 0;
   private static double TICKS_ELAPSED                         = 0;

  /**
   *  Constructor goes here
   */
   public Clock(double tick) {
      this.TICK = tick;
      this.TICKS_ELAPSED = TICKS_ELAPSED;
   }

  /**
   *  Methods go here
   *
   *  Method to calculate the next tick from the time increment
   *  @return double-precision value of the current clock tick
   */
   public double tick() {
      this.TICKS_ELAPSED += this.TICK;
      return this.TICKS_ELAPSED;
   }

  /**
   *  Method to validate the angle argument
   *  @param   argValue  String from the main programs args[0] input
   *  @return  double-precision value of the argument
   *  @throws  NumberFormatException
   */
   public static double validateAngleArg(String argValue) throws NumberFormatException {
      try {
         if (Double.parseDouble(argValue) < 0) {
            throw new NumberFormatException("null");
         }
         return (Double.parseDouble(argValue) % MAXIMUM_DEGREE_VALUE);
      }
      catch (NumberFormatException nfe) {
         throw new NumberFormatException("Error on input");
      }
   }

  /**
   *  Method to validate the optional time slice argument
   *  @param  argValue  String from the main programs args[1] input
   *  @return double-precision value of the argument or -1.0 if invalid
   *  note: if the main program determines there IS no optional argument supplied,
   *         I have elected to have it substitute the string "60.0" and call this
   *         method anyhow.  That makes the main program code more uniform, but
   *         this is a DESIGN DECISION, not a requirement!
   *  note: remember that the time slice, if it is small will cause the simulation
   *         to take a VERY LONG TIME to complete!
   */
   public static double validateTimeSliceArg(String argValue) {
      if ((Double.parseDouble(argValue) > 0) && (Double.parseDouble(argValue) <= 1800.00)) {
         return Double.parseDouble(argValue);
      }
      return INVALID_ARGUMENT_VALUE;
   }

  /**
   *  Method to calculate and return the current position of the hour hand
   *  @return double-precision value of the hour hand location
   */
   public double getHourHandAngle() {
      return (this.TICKS_ELAPSED * HOUR_HAND_DEGREES_PER_SECOND) % MAXIMUM_DEGREE_VALUE;
   }

  /**
   *  Method to calculate and return the current position of the minute hand
   *  @return double-precision value of the minute hand location
   */
   public double getMinuteHandAngle() {
      return (this.TICKS_ELAPSED * MINUTE_HAND_DEGREES_PER_SECOND) % MAXIMUM_DEGREE_VALUE;
   }

  /**
   *  Method to calculate and return the angle between the hands
   *  @return double-precision value of the angle between the two hands
   */
   public double getHandAngle() {
      if ((this.getHourHandAngle() - this.getMinuteHandAngle()) > 0) {
         return this.getHourHandAngle() - this.getMinuteHandAngle();
      }
      return this.getMinuteHandAngle() - this.getHourHandAngle();
   }

  /**
   *  Method to fetch the total number of seconds
   *   we can use this to tell when 12 hours have elapsed
   *  @return double-precision value the total seconds private variable
   */
   public double getTotalSeconds() {
      return this.TICKS_ELAPSED;
   }

  /**
   *  Method to return a String representation of this clock
   *  @return String value of the current clock
   */
   public String toString() {
      DecimalFormat template  = new DecimalFormat("#0.00");
      double ticksToHours     = this.TICKS_ELAPSED;
      double ticksToMinutes   = this.TICKS_ELAPSED % 3600;
      double hours = 0;
      while (ticksToHours >= 3600) {
         ticksToHours -= 3600;
         hours += 1;
      }
      double minutes = 0;
      while (ticksToMinutes > 60) {
         ticksToMinutes -= 60;
         minutes += 1;
      }
      double seconds = (this.TICKS_ELAPSED % 3600) % 60;
      return "time " + template.format(hours) + " hours,  " + template.format(minutes) + " minutes,  " + template.format(seconds) + " seconds";
   }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  be sure to make LOTS of tests!!
   *  remember you are trying to BREAK your code, not just prove it works!
   */
   public static void main(String args[]) {

      System.out.println("\nCLOCK CLASS TESTER PROGRAM\n" +
                         "--------------------------\n");
      System.out.println("  Creating a new clock: ");
      Clock clock = new Clock(341.0);
      System.out.println("    New clock created: " + clock.toString());
      
      System.out.println("\n === Testing validateAngleArg().... ===");
      System.out.println("      sending 'kappa degrees',   expecting double value  NFE");
      try {System.out.println((0.0 == clock.validateAngleArg("kappa")) ? " - got 0.0" : " - no joy");}
      catch(Exception e) {System.out.println ("        - Exception thrown: " + e.toString());}
      System.out.println("      sending '0.0 degrees',     expecting double value  0.0");
      try {System.out.println((0.0 == clock.validateAngleArg("0.0")) ? "        - got 0.0" : "        - no joy");}
      catch(Exception e) {System.out.println ("        - Exception thrown: " + e.toString());}
      System.out.println("      sending '90.0 degrees',    expecting double value  90.0");
      try {System.out.println((90.0 == clock.validateAngleArg("90.0")) ? "        - got 90.0" : "        - no joy");}
      catch(Exception e) {System.out.println ("        - Exception thrown: " + e.toString());}
      System.out.println("      sending '360.0 degrees',   expecting double value  0.0");
      try {System.out.println((0.0 == clock.validateAngleArg("360.0")) ? "        - got 0.0" : "        - no joy");}
      catch(Exception e) {System.out.println ("        - Exception thrown: " + e.toString());}
      System.out.println("      sending '450.0 degrees',   expecting double value  90.0");
      try {System.out.println((90.0 == clock.validateAngleArg("450.0")) ? "        - got 90.0" : "        - no joy");}
      catch(Exception e) {System.out.println ("        - Exception thrown: " + e.toString());}
      
      System.out.println("\n === Testing validateTimeSliceArg().... ===");
      System.out.println("      sending 'kappa seconds',   expecting double value  NFE");
      try {System.out.println((0.0 == clock.validateTimeSliceArg("kappa")) ? "        - got 0.0" : " - no joy");}
      catch(Exception e) {System.out.println ("        - Exception thrown: " + e.toString());}
      System.out.println("      sending '0.0 seconds',     expecting double value  -1.0");
      try {System.out.println((-1.0 == clock.validateTimeSliceArg("0.0")) ? "       - got -1.0" : " - no joy");}
      catch(Exception e) {System.out.println ("        - Exception thrown: " + e.toString());}
      System.out.println("      sending '1.0 seconds',     expecting double value  1.0");
      try {System.out.println((1.0 == clock.validateTimeSliceArg("1.0")) ? "       - got 1.0" : " - no joy");}
      catch(Exception e) {System.out.println ("        - Exception thrown: " + e.toString());}
      System.out.println("      sending '60.0 seconds',    expecting double value  60.0");
      try {System.out.println((60.0 == clock.validateTimeSliceArg("60.0")) ? "       - got 60.0" : " - no joy");}
      catch(Exception e) {System.out.println ("        - Exception thrown: " + e.toString());}
      System.out.println("      sending '1800.0 seconds',  expecting double value  1800.0");
      try {System.out.println((1800.0 == clock.validateTimeSliceArg("1800.0")) ? "       - got 1800.0" : " - no joy");}
      catch(Exception e) {System.out.println ("        - Exception thrown: " + e.toString());}
      System.out.println("      sending '1801.0 seconds',  expecting double value  -1.0");
      try {System.out.println((-1.0 == clock.validateTimeSliceArg("1801.0")) ? "       - got -1.0" : " - no joy");}
      catch(Exception e) {System.out.println ("        - Exception thrown: " + e.toString());}
      
      System.out.println("\n === Testing tick(), getHourHandAngle(), getMinuteHandAngle(), getHandAngle(), and toString().... ===");
      System.out.println(  " === NOTE: getHandAngle() uses getHourHandAngle() and getMinuteHandAngle() ===");
      DecimalFormat angle = new DecimalFormat("##0.00");
      
      for (int i = 0; i < 10; i ++) {
         System.out.println("    At " + clock.toString() + ":");
         System.out.println("      Hand Angle is " + angle.format(clock.getHandAngle()));
         System.out.println("      Adding a tick of " + clock.TICK + " seconds");
         clock.tick();
       }
      
    }
}

