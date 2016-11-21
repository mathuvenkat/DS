package mathProblems;

import java.util.logging.Logger;

/**
 * @author Gokul
 * 
 */
public class IntegerDivision {

   /**
    * Method that does Integer Division
    * 
    * @param input
    * @return output of Integer Division
    */
   protected DivisionOutput divide(DivisionInput input) {

      int currentQuotient, currentRemainder, rangeStart, rangeEnd, counter = 0;
      // run prechecks to see if output can be computed without more expensive search
      DivisionOutput b = runPreChecks(input);
      if (b != null) {
         return b;
      }
      // determine starting range for binary search, range for binary search will be dependent on the numerator
      // If numerator and denominator take the same sign, the quotient will be positive, thus choosing the range of 0 to abs(numerator)
      // If numerator and denominator take the opposite sign, the quotient will be negative, thus choosing the range of 0 to -abs(numerator)
      // Using XOR operator for sign checks
      if ((input.getNumerator() < 0) ^ (input.getDenominator() < 0)) {
         rangeStart = -Math.abs(input.getNumerator());
         rangeEnd = 0;
      } else {
         rangeStart = 0;
         rangeEnd = Math.abs(input.getNumerator());
      }
      // Iterative procedure for binary search
      while (true) {
         // counter to track the number of Iterations - optional
         counter++;
         // temporary quotient takes the midpoint between the ranges
         // bit wise operation to avoid division operator
         currentQuotient = rangeStart + (Math.abs(rangeEnd - rangeStart) >>> 1);
         System.out.println("CurrentQuotient " + currentQuotient);
         currentRemainder = (input.getNumerator()
               - (input.getDenominator() * (currentQuotient)));
         System.out.println("CurrentRemainder " + currentRemainder);
         // two required criteria for success
         // 1: when remainder is less than the denominator
         // 2: when remainder and denominator have the same sign
         if (Math.abs(currentRemainder) < Math.abs(input.getDenominator())
               && (!(currentRemainder < 0) ^ (input.getDenominator() < 0))) {
            break;
         }
         // identify the new reduced range for Binary Search based on the criteria implemented below
         // reset either the start or end to the current quotient
         if (input.getDenominator() > 0) {
            if (currentRemainder >= input.getDenominator()) {
               rangeStart = currentQuotient;
            } else {
               rangeEnd = currentQuotient;
            }
         } else {
            if (currentRemainder <= input.getDenominator()) {
               rangeStart = currentQuotient;
            } else {
               rangeEnd = currentQuotient;
            }
         }
         System.out.println("start:  " + rangeStart);
         System.out.println("end:  " + rangeEnd);
      }
      Logger.getLogger("X").fine(
            input.getNumerator() + "  :   Number of Iterations " + counter);
      return new DivisionOutput(currentQuotient, currentRemainder);
   }

   /**
    * Method to check for special cases on the input thus avoiding more
    * expensive integer division
    * 
    * @param input
    * @return output of Integer Division
    */
   private DivisionOutput runPreChecks(DivisionInput input) {

      DivisionOutput output = null;
      // handling divide by zero case
      if (input.getDenominator() == 0) {
         throw new ArithmeticException(" Divide by zero error - invalid case");
      }
      // when numerator is zero, avoid the search and compute the output directly in O(1)
      else if (input.getNumerator() == 0) {
         output = new DivisionOutput(0, 0);
      }
      // when absolute value of numerator and denominator are same , avoid the search and compute the output directly in O(1)
      // quotient is 1 if numerator and denominator are same sign, -1 otherwise
      else if (Math.abs(input.getNumerator()) == Math
            .abs(input.getDenominator())) {
         if ((input.getNumerator() < 0) ^ (input.getDenominator() < 0)) {
            output = new DivisionOutput(-1, 0);
         } else {
            output = new DivisionOutput(1, 0);
         }
      }
      // when absolute value of denominator is 1, avoid the search and compute the output directly in O(1)
      // quotient is positive if numerator and denominator are same sign, negative otherwise
      else if (Math.abs(input.getDenominator()) == 1) {
         if ((input.getNumerator() < 0) ^ (input.getDenominator() < 0)) {
            output = new DivisionOutput(-Math.abs(input.getNumerator()), 0);
         } else {
            output = new DivisionOutput(Math.abs(input.getNumerator()), 0);
         }
      }
      return output;
   }

   /**
    * Type that encapsulates output - quotient & remainder
    * 
    */
   public static class DivisionOutput {

      private final int quotient, remainder;

      public DivisionOutput(int quotient, int remainder) {

         this.remainder = remainder;
         this.quotient = quotient;
      }

      public int getQuotient() {

         return quotient;
      }

      public int getRemainder() {

         return remainder;
      }

      @Override
      public String toString() {

         return "Quotient : " + getQuotient() + "; Remainder :"
               + getRemainder();
      }
   }

   /**
    * Type that encapsulates input - numerator & denominator
    * 
    */
   public static class DivisionInput {

      private final int numerator, denominator;

      public DivisionInput(int numerator, int denominator) {

         this.denominator = denominator;
         this.numerator = numerator;
      }

      public int getNumerator() {

         return numerator;
      }

      public int getDenominator() {

         return denominator;
      }

      @Override
      public String toString() {

         return "Numerator : " + getNumerator() + ";  Denominator :"
               + getDenominator();
      }
   }

   public static void main(String args[]) throws Exception {

      IntegerDivision idiv = new IntegerDivision();
      DivisionInput input = null;
      String invalidInputMessage =
            "Invalid input: Please enter two integers- Numerator and Denominator";
      //                if (args.length != 2) {
      //                        System.out.println(invalidInputMessage);
      //                        return;
      //                }
      try {
         input = new DivisionInput(50, 7);
      } catch (NumberFormatException nfe) {
         System.out.println(invalidInputMessage);
         return;
      }
      DivisionOutput output = idiv.divide(input);
      Logger.getLogger("X").info(input.toString() + " \n" + output.toString());
   }

}
