/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  BrobInt.java
 * Purpose    :  Learning exercise to implement arbitrarily large numbers and their operations
 * @author    :  B.J. Johnson
 * Date       :  2017-04-04
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2017-04-04  B.J. Johnson  Initial writing and begin coding
 *  1.1.0  2017-04-13  B.J. Johnson  Completed addByt, addInt, compareTo, equals, toString, Constructor,
 *                                     validateDigits, two reversers, and valueOf methods; revamped equals
 *                                     and compareTo methods to use the Java String methods; ready to
 *                                     start work on subtractByte and subtractInt methods
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Arrays;

public class BrobInt {

   public static final BrobInt ZERO     = new BrobInt(  "0" );      /// Constant for "zero"
   public static final BrobInt ONE      = new BrobInt(  "1" );      /// Constant for "one"
   public static final BrobInt TWO      = new BrobInt(  "2" );      /// Constant for "two"
   public static final BrobInt THREE    = new BrobInt(  "3" );      /// Constant for "three"
   public static final BrobInt FOUR     = new BrobInt(  "4" );      /// Constant for "four"
   public static final BrobInt FIVE     = new BrobInt(  "5" );      /// Constant for "five"
   public static final BrobInt SIX      = new BrobInt(  "6" );      /// Constant for "six"
   public static final BrobInt SEVEN    = new BrobInt(  "7" );      /// Constant for "seven"
   public static final BrobInt EIGHT    = new BrobInt(  "8" );      /// Constant for "eight"
   public static final BrobInt NINE     = new BrobInt(  "9" );      /// Constant for "nine"
   public static final BrobInt TEN      = new BrobInt( "10" );      /// Constant for "ten"

  /// Some constants for other intrinsic data types
  ///  these can help speed up the math if they fit into the proper memory space
   public static final BrobInt MAX_INT  = new BrobInt( Integer.valueOf( Integer.MAX_VALUE ).toString() );
   public static final BrobInt MIN_INT  = new BrobInt( Integer.valueOf( Integer.MIN_VALUE ).toString() );
   public static final BrobInt MAX_LONG = new BrobInt( Long.valueOf( Long.MAX_VALUE ).toString() );
   public static final BrobInt MIN_LONG = new BrobInt( Long.valueOf( Long.MIN_VALUE ).toString() );

  /// These are the internal fields
   public  String internalValue = "";        // internal String representation of this BrobInt
   public  byte   sign          = 0;         // "0" is positive, "1" is negative
   private String reversed      = "";        // the backwards version of the internal String representation
   public  int[]  intVersion    = null;      // byte array for storing the string values; uses the reversed string

  /**
   *  Constructor takes a string and assigns it to the internal storage, checks for a sign character
   *   and handles that accordingly;  it then checks to see if it's all valid digits, and reverses it
   *   for later use
   *  @param  value  String value to make into a BrobInt
   */
   public BrobInt( String Brobdingnagian ) {
      
      //checking the validity of the argument passed
      if ( false == this.validateDigits( Brobdingnagian ) ) {
         throw new IllegalArgumentException( "Error creating new BrobInt" );
      }
      
      //determining the internalValue and sign states
      if ( '-' == Brobdingnagian.charAt( 0 ) ) {
         this.sign          = 1;
         this.internalValue = Brobdingnagian.substring( 1 );
      } else if ( '+' == Brobdingnagian.charAt( 0 ) ) {
         this.internalValue = Brobdingnagian.substring( 1 );
      } else {
         this.internalValue = Brobdingnagian;
      }
      
      //creating the reversed string state
      this.reversed = new StringBuffer( internalValue ).reverse().toString();
      
      //vars needed for creating the integer array
      int arrLength        = 0;
      int MAX_CHARS_LENGTH = 8;
      int i                = 0;
      int start            = internalValue.length() - 8;
      int end              = internalValue.length();
      
      //creating the integer array
      arrLength  = (int) Math.floor( ( ( internalValue.length() - 1 ) / MAX_CHARS_LENGTH ) + 1 );
      intVersion = new int[arrLength];
      while ( end >= MAX_CHARS_LENGTH ) {
         intVersion[i] = Integer.parseInt( internalValue.substring( start, end ) );
         start -= MAX_CHARS_LENGTH;
         end   -= MAX_CHARS_LENGTH;
         i++;
      }
      if ( end > 0 ) {
         intVersion[i] = Integer.parseInt( internalValue.substring( 0, end ) );
      }
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to validate that all the characters in the value are valid decimal digits
   *  @param   String   the string value of the BrobInt
   *  @return  boolean  true if all digits are good
   *  @throws  IllegalArgumentException if something is hinky
   *  note that there is no return false, because of throwing the exception
   *  note also that this must check for the '+' and '-' sign digits
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean validateDigits( String value ) {
      for ( int i = 0; i < value.length(); i++ ) {
         switch ( value.charAt(i) ) {
            case '1': case '2': case '3':
            case '4': case '5': case '6':
            case '7': case '8': case '9':
            case '0': case '-': case '+':; break;
            default: return false;
         }
      }
      return true;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of this BrobInt
   *  @return BrobInt that is the reverse of the value of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt reverser() {
      BrobInt result = new BrobInt( this.reversed );
      return result;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of a BrobInt passed as argument
   *  Note: static method
   *  @param  gint         BrobInt to reverse its value
   *  @return BrobInt that is the reverse of the value of the BrobInt passed as argument
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt reverser( BrobInt gint ) {
      BrobInt result = new BrobInt( gint.reversed );
      return result;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to add the value of a BrobInt passed as argument to this BrobInt using int array
   *  @param  gint         BrobInt to add to this
   *  @return BrobInt that is the sum of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt add( BrobInt gint ) {
      
      //vars needed to figure out how to add
      int[] shorter           = null;
      int[] longer            = null;
      int[] result            = null;
      int i                   = 0;
      boolean signTest        = false;
      StringBuffer stringForm = new StringBuffer();
      
      //figuring out which number is the larger
      if ( this.compareTo( gint ) >= 0 ) {
         longer  = this.intVersion;
         shorter = gint.intVersion;
         result  = new int[longer.length];
      } else {
         longer  = gint.intVersion;
         shorter = this.intVersion;
         result  = new int[longer.length];
      }
      
      //execution if both signs are the same (adding)
      if ( this.sign == gint.sign ) {
         while ( i < shorter.length ) {
            result[i] += ( shorter[i] + longer[i] );
            if ( result[i] > 99999999 ) {
               result[i] -= 100000000;
               try {
                  result[i + 1] += 1;
               } catch ( Exception e ) { result[i] += 100000000; }
            }
            i++;
         }
         
         //adding any further digits from the longer number
         while ( i < longer.length ) {
            result[i] += longer[i];
            if ( result[i] > 99999999 ) {
               result[i] -= 100000000;
               try {
                  result[i + 1] += 1;
               } catch ( Exception e ) { result[i] += 100000000; }
            }
            i++;
         }
      //execution if signs are different (subtracting)
      } else if ( this.sign != gint.sign ) {
         if ( this.internalValue == gint.internalValue ) {
            return new BrobInt( "0" );
         }
         signTest = true;
         while ( i < shorter.length ) {
            result[i] -= ( shorter[i] - longer[i] );
            if ( result[i] < 0 ) {
               result[i] += 100000000;
               try {
                  result[i + 1] -= 1;
               } catch ( Exception e ) { result[i] -= 100000000; }
            }
            i++;
         }
         
         //adding any further digits from the longer number
         while ( i < longer.length ) {
            result[i] -= longer[i];
            if ( result[i] < 0 ) {
               result[i] += 100000000;
               try {
                  result[i + 1] -= 1;
               } catch ( Exception e ) { result[i] -= 100000000; }
            }
            i++;
         }
      }
      
      //constructing the result string
      for ( int j = 0; j < result.length; j++ ) {
         if ( result[j] == 0 ) {
            stringForm.insert( 0, "0" );
         }
         if ( ( ( result.length - 1 )  == j ) && ( 0 == result[j] )) {
            break;
         }
         stringForm.insert( 0, String.valueOf( result[j] ) );
         while ( ( 0 != ( stringForm.length() % 8  ) ) && ( j != result.length - 1 ) ) {
            stringForm.insert( 0, "0" );
         }
      }
      
      //creating the new BrobInt from the string and sign
      BrobInt answer = new BrobInt( stringForm.toString() );
      if ( this.sign == gint.sign ) {
         answer.sign = this.sign;
      } else if ( ( this.compareTo( gint ) > 0 ) && ( signTest ) ) {
         answer.sign = this.sign;
      } else if ( ( this.compareTo( gint ) < 0 ) && ( signTest ) ) {
         answer.sign = gint.sign;
      }
      
      return answer;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to subtract the value of a BrobInt passed as argument to this BrobInt using int array
   *  @param  gint         BrobInt to subtract from this
   *  @return BrobInt that is the difference of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt subtract( BrobInt gint ) {
      
      //handling all sign cases and sending them to add()
      if ( ( this.sign == gint.sign ) && ( this.internalValue == gint.internalValue ) ) {
         return new BrobInt( "0" );
      }
      if ( ( this.sign != gint.sign ) && ( gint.sign == 0 ) ) {
         gint.sign = 1;
         return this.add( gint );
      } else if ( ( this.sign != gint.sign ) && ( gint.sign == 1 ) ) {
         gint.sign = 0;
         return this.add( gint );
      }
      if ( this.sign == 0 ) {
         if ( ( this.compareTo( gint ) > 0 ) && ( gint.sign == 0 ) ) {
            gint.sign = 1;
            return this.add( gint );
         } else if ( ( this.compareTo( gint ) > 0 ) && ( gint.sign == 1 ) ) {
            gint.sign = 0;
            return this.add( gint );
         }
         
         if ( ( this.compareTo( gint ) < 0 ) && ( this.sign == 0 ) ) {
            this.sign = 1;
            return this.add( gint );
         } else if ( ( this.compareTo( gint ) < 0 ) && ( this.sign == 1 ) ) {
            this.sign = 0;
            return this.add( gint );
         }
      } else {
         if ( ( this.compareTo( gint ) < 0 ) && ( gint.sign == 0 ) ) {
            gint.sign = 1;
            return this.add( gint );
         } else if ( ( this.compareTo( gint ) < 0 ) && ( gint.sign == 1 ) ) {
            gint.sign = 0;
            return this.add( gint );
         }
         
         if ( ( this.compareTo( gint ) > 0 ) && ( this.sign == 0 ) ) {
            this.sign = 1;
            return this.add( gint );
         } else if ( ( this.compareTo( gint ) > 0 ) && ( this.sign == 1 ) ) {
            this.sign = 0;
            return this.add( gint );
         }
      }
      return null;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to multiply the value of a BrobInt passed as argument to this BrobInt
   *  @param  gint         BrobInt to multiply by this
   *  @return BrobInt that is the product of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt multiply( BrobInt gint ) {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented, did not get around to it." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to divide the value of this BrobInt by the BrobInt passed as argument
   *  @param  gint         BrobInt to divide this by
   *  @return BrobInt that is the dividend of this BrobInt divided by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt divide( BrobInt gint ) {
      BrobInt dummy = null;
      int counter   = 0;
      byte holder    = 0;
      
      if ( this.sign != gint.sign ) {
         if ( this.sign == 1 ) {
            holder    = this.sign;
            this.sign = gint.sign;
         } else {
            holder    = gint.sign;
            gint.sign = this.sign;
         }
      }
      
      if ( 0 > this.compareTo( gint ) ) {
         return new BrobInt( "0" );
      } else if ( this.equals( gint ) ) {
         return new BrobInt( "1" );
      }
      
      dummy = new BrobInt( gint.toString() );
      
      while ( 0 <= this.compareTo( dummy ) ) {
         dummy.internalValue = ( new BrobInt( dummy.toString() ).add( gint ) ).toString();
         counter++;
         dummy.updateArray();
      }
      
      BrobInt answer = new BrobInt( String.valueOf( counter ) );
      
      if ( this.sign == gint.sign ) {
         answer.sign = 0;
      } else {
         answer.sign = 1;
      }
      
      return answer;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to get the remainder of division of this BrobInt by the one passed as argument
   *  @param  gint         BrobInt to divide this one by
   *  @return BrobInt that is the remainder of division of this BrobInt by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt remainder( BrobInt gint ) {
      BrobInt dummy = null;
      this.sign = gint.sign;
      
      if ( 0 > this.compareTo( gint ) ) {
         return new BrobInt( "0" );
      } else if ( this.equals( gint ) ) {
         return new BrobInt( "0" );
      }
      
      dummy = new BrobInt( gint.toString() );
      
      while ( 0 <= this.compareTo( dummy ) ) {
         dummy.internalValue = ( new BrobInt( dummy.toString() ).add( gint ) ).toString();
         dummy.updateArray();
      }
      dummy.internalValue = ( new BrobInt( dummy.toString() ).subtract( gint ) ).toString();
      dummy.updateArray();
      
      return new BrobInt( this.subtract( dummy ).toString() );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to compare a BrobInt passed as argument to this BrobInt
   *  @param  gint  BrobInt to add to this
   *  @return int   that is one of neg/0/pos if this BrobInt precedes/equals/follows the argument
   *  NOTE: this method performs a lexicographical comparison using the java String "compareTo()" method
   *        THAT was easy.....
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public int compareTo( BrobInt gint ) {
      if( internalValue.length() > gint.internalValue.length() ) {
         return 1;
      } else if( internalValue.length() < gint.internalValue.length() ) {
         return (-1);
      } else {
         for( int i = 0; i < internalValue.length(); i++ ) {
            if( Character.compare(internalValue.charAt(i), gint.internalValue.charAt(i)) > 0 ) {
               return 1;
            } else if( Character.compare(internalValue.charAt(i), gint.internalValue.charAt(i)) < 0 ) {
               return (-1);
            }
         }
      }
      return 0;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to check if a BrobInt passed as argument is equal to this BrobInt
   *  @param  gint     BrobInt to compare to this
   *  @return boolean  that is true if they are equal and false otherwise
   *  NOTE: this method performs a similar lexicographical comparison as the "compareTo()" method above
   *        also using the java String "equals()" method -- THAT was easy, too..........
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean equals( BrobInt gint ) {
      return (internalValue.equals( gint.toString() ));
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a BrobInt given a long value passed as argument
   *  @param  value         long type number to make into a BrobInt
   *  @return BrobInt  which is the BrobInt representation of the long
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt valueOf( long value ) throws NumberFormatException {
      BrobInt gi = null;
      try {
         gi = new BrobInt( Long.valueOf( value ).toString() );
      }
      catch( NumberFormatException nfe ) {
         System.out.println( "\n  Sorry, the value must be numeric of type long." );
      }
      return gi;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a String representation of this BrobInt
   *  @return String  which is the String representation of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public String toString() {
      if ( this.sign == 1 ) {
         return ( "-" + internalValue );
      }
      return internalValue;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to display an Array representation of this BrobInt as its bytes
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public void toArray( int[] d ) {
      System.out.println( Arrays.toString( d ) );
   }
   
  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to update the intVersion state (necessary for divide)
   *  @param   none
   *  @return  nothing
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public void updateArray () {
      int arrLength        = 0;
      int MAX_CHARS_LENGTH = 8;
      int i                = 0;
      int start            = internalValue.length() - 8;
      int end              = internalValue.length();
      
      arrLength       = (int) Math.floor( ( ( this.internalValue.length() - 1 ) / MAX_CHARS_LENGTH ) + 1 );
      this.intVersion = new int[arrLength];
      while ( end >= MAX_CHARS_LENGTH ) {
         intVersion[i] = Integer.parseInt( this.internalValue.substring( start, end ) );
         start -= MAX_CHARS_LENGTH;
         end   -= MAX_CHARS_LENGTH;
         i++;
      }
      if ( end > 0 ) {
         intVersion[i] = Integer.parseInt( internalValue.substring( 0, end ) );
      }
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  the main method redirects the user to the test class
   *  @param  args  String array which contains command line arguments
   *  note:  we don't really care about these
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static void main( String[] args ) {
      System.out.println( "\n  Hello, world, from the BrobInt program!!\n" );
      System.out.println( "\n   You should run your tests from the BrobIntTester...\n" );

      System.exit( 0 );
   }
}