package str;

import java.util.HashMap;
import java.util.Map;

public class Solution {

   // Given a string, find the longest continuos substring with no repeated characters.
   // try these 
   //Input "abc" Output "abc"
   //Input "abcda" Output "abcd" "bcda"
   //Input "king of chinaloyd" Output “f chinaloyd"
   //Input aabbc Output ab bc
   //Input abaaabc Output abc
   //Input "abcdae" Output "bcdae"


   public static void main(String[] args) {
      Solution solution = new Solution();
      String[] input =
            new String[] { "abc", "abcda", "king of chinaloyd", "aabbc",
                  "abaaabc", "abcdae", "aabb", "atabb", "aaa" };

      for (String s : input) {
         String output = solution.findlongestSubstring(s);
         System.out.println(String.format("Longest substring of %s is: %s", s,
               output));
      }
   }


   public String findlongestSubstring(String s) {

      Map<Character, Integer> map = new HashMap<Character, Integer>();

      int startIndex = 0;
      int endIndex = 0;
      int m_startIndex = 0;
      int m_endIndex = 0;
      int currLength = 1;
      int maxlength = 1;

      Integer prevIndex;
      map.put(s.charAt(0), 0);

      //currLength is the window of the string considered. 

      for (int i = 1; i < s.length(); i++) {
         prevIndex = map.get(s.charAt(i));
         if (prevIndex == null || prevIndex < i - currLength) {
            currLength++;
            if (currLength > maxlength) {
               maxlength = currLength;
               m_endIndex = i;
               endIndex = i;
               m_startIndex = startIndex;
            }
         } else {
            startIndex = prevIndex + 1;
            endIndex = i;
            currLength = (endIndex - startIndex) + 1;
         }
         map.put(s.charAt(i), i);
      }

      System.out.println(String.format("maxLength of %s is: %s", s, maxlength));
      StringBuilder str = new StringBuilder();
      for (int i = m_startIndex; i <= m_endIndex; i++) {
         str = str.append(s.charAt(i));
      }
      return str.toString();
   }

}
