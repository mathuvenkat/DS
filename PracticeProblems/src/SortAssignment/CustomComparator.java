package SortAssignment;

import java.util.Comparator;

/**
 *
 * This class implements a custom comparator for sorting strings of various
 * types - numbers , alphanumeric strings Eg. IP addresses , filenames etc. ,
 * dates etc.
 *
 * Strings have to be of ASCII characters and numerical values are treated as
 * base 10 numbers.
 * If the string has numbers - they are compared numerically.
 * Negative numbers are not allowed .i.e -23 will not be considered as a
 * negative number, but "-" will  be considered a special character and rules
 * of ordering will apply per that assumption.
 * Period character is always considered as a special character and not
 * as a decimal point.
 * Sort order among special characters is undefined.
 *
 */
public class CustomComparator implements Comparator<String> {

   /*
    * Functional interface for the conditions
    */
   interface ConditionChecker {
      boolean op(char ch);
   }

   private boolean checkCondition(ConditionChecker cc, char ch) {
      return cc.op(ch);
   }

   ConditionChecker isDigit = (ch) -> Character.isDigit(ch);
   ConditionChecker isLetter = (ch) -> Character.isLetter(ch);
   ConditionChecker isNonAlphaNumeric = (ch) -> !Character.isLetter(ch)
         && !Character.isDigit(ch);


   /**
    * Utility method to return substring of same type
    *
    * @param index
    * @param slength
    * @param input
    * @param block
    * @param cc
    * @return
    */
   private String buildBlock(int index, String input, StringBuilder block,
         ConditionChecker cc) {
      int slength = input.length();
      char c;
      while (index < slength) {
         c = input.charAt(index);
         if (!checkCondition(cc, c)) {
            break;
         }
         block.append(c);
         index++;
      }
      return block.toString();
   }

   /**
    * Returns substring of the same type - either letters, digits or punctuation
    * from the input string
    *
    * @param input
    * @param length
    *           of the string
    * @param index
    *           - start index in the input string to get the substring from
    * @return
    */
   private String getContiguousSameTypeString(String input, int index) {
      StringBuilder block = new StringBuilder();
      char c = input.charAt(index);
      block.append(c);
      index++;
      String returnValue;
      if (checkCondition(isDigit, c)) {
         returnValue = buildBlock(index, input, block, isDigit);
      } else if (checkCondition(isLetter, c)) {
         returnValue = buildBlock(index, input, block, isLetter);
      } else {
         //non-digit ". ,_, - "
         //Eg. valid inputs like ip address , filenames have special characters.
         returnValue = buildBlock(index, input, block, isNonAlphaNumeric);
      }
      return returnValue;
   }


   /**
    * Get number of leading zeroes in a number
    *
    * @param input
    *           string
    * @return number of leading zeroes in the number
    */
   private int getNumberOfLeadingZeroes(String s) {
      int pos = 0;
      while (pos < s.length()) {
         if (s.charAt(pos) == '0') {
            pos++;
         } else {
            break;
         }
      }
      return pos;
   }

   @Override
   public int compare(String s1, String s2) {
      int s1Index = 0;
      int s2Index = 0;
      int s1Length = s1.length();
      int s2Length = s2.length();

      while (s1Index < s1Length && s2Index < s2Length) {
         //Initialize
         String s1Block = getContiguousSameTypeString(s1, s1Index);
         s1Index = s1Index + s1Block.length();
         String s2Block = getContiguousSameTypeString(s2, s2Index);
         s2Index = s2Index + s2Block.length();
         int result = 0;

         if (checkCondition(isDigit, s1Block.charAt(0))
               && checkCondition(isDigit, s2Block.charAt(0))) {
            /*
             *  If both blocks contain numeric characters, sort them numerically
             */
            int s1BlockLength = s1Block.length();
            int s2BlockLength = s2Block.length();
            int s1NumZeroes = getNumberOfLeadingZeroes(s1Block);
            int s2NumZeroes = getNumberOfLeadingZeroes(s2Block);

            /* if numbers are same only differentiated by leading zeroes,
            sort order is undefined. eg. 0007 , 007.
            */
            result =
                  (s1BlockLength - s1NumZeroes) - (s2BlockLength - s2NumZeroes);

            //get strings stripped of leading zeroes for value comparison
            s1Block = s1Block.substring(s1NumZeroes);
            s2Block = s2Block.substring(s2NumZeroes);
            s1BlockLength = s1Block.length();

            // If length of numbers is equal, the first difference decides the return
            if (result == 0) {
               for (int i = 0; i < s1BlockLength; i++) {
                  result = s1Block.charAt(i) - s2Block.charAt(i);
                  if (result != 0) {
                     return result;
                  }
               }
            }
         } else if (checkCondition(isLetter, s1Block.charAt(0))
               && checkCondition(isLetter, s2Block.charAt(0))) {
            /* If both blocks contain letters only- lexicographic sort -
             * upper case is followed by lower case
             */
            result = s1Block.compareTo(s2Block);
         } else {
            /*
             * If blocks are not of same type - ascending order is as follows:
             * punctuation , Digit , Letter
             *
             * s1        s2       res
             * punc     digit/letter     -1
             * digit    letter           -1
             * digit    punc             +1
             * letter   punc/digit       +1
             *
             */
            if (checkCondition(isDigit, s1Block.charAt(0))) {
               if (checkCondition(isLetter, s2Block.charAt(0))) {
                  result = -1;
               } else {
                  result = 1; //punctuation
               }
            } else if (checkCondition(isLetter, s1Block.charAt(0))) {
               result = 1;
            } else {
               if (!checkCondition(isNonAlphaNumeric, s2Block.charAt(0))) {
                  result = -1;
               }
            }
         }

         /*
          * If in this substring search one is greater/smaller than the other - return it and break
          * else continue to compare the remaining part of the strings
          */
         if (result != 0)
            return result;
      }

      //If loop completes, they are equal or the order is non-deterministic based on the defined rules
      return 0;
   }
}
