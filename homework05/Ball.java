/** ==================================================
  * File Name     : Ball.java
  * Author        : Adam Hirata (UID: 956306941)
  * Date Created  : 3/13/2018
  * Description   : A program that simulates a ball
  *                 and is used in conjunction with
  *                 SoccerSim.java and Clock.java
  * ==================================================*/


import java.text.DecimalFormat;

public class Ball {
   
   //necessary variables
   private double x           = 0;
   private double y           = 0;
   private double xSpeed      = 0;
   private double ySpeed      = 0;
   private double timeSlice   = 0;
   
   //Constructor for the Ball class
   public Ball(double X, double Y, double XSpeed, double YSpeed, double TimeSlice) {
      this.x         = X;
      this.y         = Y;
      this.xSpeed    = XSpeed;
      this.ySpeed    = YSpeed;
      this.timeSlice = TimeSlice;
   }
   
   /**=============================================
   Methods to get the X and Y positions of the ball
   ==============================================*/
   public double getX() {
      return this.x;
   }
   
   public double getY() {
      return this.y;
   }
   
   /**========================
   Method which moves the ball
   =========================*/
   public void move() {
      this.x += this.xSpeed;
      this.y += this.ySpeed;
      this.changeVelocity();
   }
   
   /**===================================================================
   Method which calculates the new velocity after accounting for friction
     Also stops the ball if it is moving less than 1 ft/sec
   ====================================================================*/
   public void changeVelocity() {
      this.xSpeed = this.xSpeed * (Math.pow(.99, this.timeSlice));
      this.ySpeed = this.ySpeed * (Math.pow(.99, this.timeSlice));
      if (Math.abs(this.xSpeed) < .083) {
         this.xSpeed = 0;
      }
      if (Math.abs(this.ySpeed) < .083) {
         this.ySpeed = 0;
      }
   }
   
   /**================================================================
   Method which returns true if the ball is not moving, false if it is
   =================================================================*/
   public boolean isAtRest() {
      if ((0.0 == this.xSpeed) && (0.0 == this.ySpeed)) {
         return true;
      }
      return false;
   }
   
   /**================================================================
   Method which returns true if the two balls collide, false otherwise
   =================================================================*/
   public boolean didCollide (Ball ball) {
      if (Math.sqrt(Math.pow(this.getX() - ball.getX(), 2) + Math.pow(this.getY() - ball.getY(), 2)) < .741666666) {
         return true;
      }
      return false;
   }
   
   /**===================================================================================
   Method that gives the string representation of the ball's position and velocity vector
   ====================================================================================*/
   public String toString() {
      DecimalFormat template = new DecimalFormat("##0.000");
      StringBuffer result = new StringBuffer("position = ");
      if ((179 < Math.abs(this.getX())) || (119 < Math.abs(this.getY()))) {
         result.append("out of bounds      ");
      } else {
         result.append("(" + template.format(this.getX()) + ", " + template.format(this.getY()) + ")      ");
      }
      result.append("velocity = ");
      if ((0.00001 > Math.abs(this.xSpeed)) && (0.00001 > Math.abs(this.ySpeed))) {
         result.append("at rest");
      } else {
         result.append("<" + template.format(this.xSpeed) + ", " + template.format(this.ySpeed) + ">");
      }
      return result.toString();
   }
   
   /**===================================
   Main method that holds the class tests
   ====================================*/
   public static void main(String args[]) {
      Ball kappa   = new Ball(0.0, 0.0, Math.random() * 10.0, Math.random() * 10.0, 1.0);
      Clock clocky = new Clock(1.0);
      System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                         "=== TESTS FOR Ball.java METHODS ===\n" +
                         "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
      System.out.println("  [!] NOTE: toString() uses getX() and getY(). Furthermore, move() uses changeVelocity()");
      System.out.println("  Creating new ball with initial " + kappa.toString() + "\n\n");
      for (int i = 0; i < 10; i++) {
         System.out.println("At time: " + clocky.toString() + ":\n  " + kappa.toString() + "\n");
         kappa.move();
         clocky.tick();
      }
      System.out.println("Creating two new balls to test didCollide()");
      Ball test1 = new Ball(kappa.getX() + 1, kappa.getY() + 1, 0.0, 0.0, 1.0);
      Ball test2 = new Ball(kappa.getX() + 0.370, kappa.getY(), 0.0, 0.0, 1.0);
      System.out.println("Testing didCollide between original ball and test ball 1...");
      System.out.println("  [!] Original: " + kappa.toString());
      System.out.println("  [!] Ball 1  : " + test1.toString());
      System.out.println("  Expecting result: false. Actual result: " + kappa.didCollide(test1));
      System.out.println("\nTesting didCollide between original ball and test ball 2...");
      System.out.println("  [!] Original: " + kappa.toString());
      System.out.println("  [!] Ball 2  : " + test2.toString());
      System.out.println("  Expecting result: true. Actual result: " + kappa.didCollide(test2));
      
   }
}