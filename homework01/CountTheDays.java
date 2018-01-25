/**==========================
  *Name: Adam Hirata
  *Date submitted: 1/25/18
  *Last edit on  : 1/25/18
  */

public class CountTheDays {
   
   /** The main method of CountTheDays will output the number of days between the input dates
     * @param args String[] array containing line command parameters
     * @return void
     * @note command line arguments must be number representations of the months/days/years
     */
     
   public static void main ( String args[] ) {
      long month1 = Long.parseLong( args[ 0 ] );
      long day1   = Long.parseLong( args[ 1 ] );
      long year1  = Long.parseLong( args[ 2 ] );
      long month2 = Long.parseLong( args[ 3 ] );
      long day2   = Long.parseLong( args[ 4 ] );
      long year2  = Long.parseLong( args[ 5 ] );
      
      long days = CalendarStuff.daysBetween( month1, day1, year1, month2, day2, year2 );
      System.out.println( "\nThere are " + days + " days between your two given dates.\n");
   }
}
