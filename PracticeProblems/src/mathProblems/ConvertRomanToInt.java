package mathProblems;

//This is the text editor interface. 

//Anything you type or change here will be seen by the other person in real time.


//I = 1, V = 5, X = 10, L = 50, C=100, M=1000

//XLIX = 49, 10 + 50 - 20= 40 + 1 + 10 - 2*1 = 49
//IX = 9
//X = 10
//XI = 11
//XL= 40 = 50 - 10 = 10 + 50 - 2* 10 = 
import java.util.HashMap;
import java.util.Map;

public class ConvertRomanToInt {

   public static void main(String[] args) {
      ConvertRomanToInt t = new ConvertRomanToInt();
      System.out.println(t.toRomanString("XLIX"));
   }

   public int toRomanString(String s) {
      Map<String, Integer> map = new HashMap<String, Integer>();
      map.put("I", 1);
      map.put("V", 5);
      map.put("X", 10);
      map.put("L", 50);
      map.put("C", 100);
      map.put("M", 1000);

      int sum = map.get(Character.toString(s.charAt(0)));
      String prevChar = Character.toString(s.charAt(0));
      int prevDigit = sum;

      for (int i = 1; i < s.length(); i++) {
         int digit = map.get(Character.toString(s.charAt(i)));
         if (digit <= prevDigit) {
            sum = sum + digit;
         } else {
            sum = sum + digit - (2 * map.get(prevChar));
         }
         prevDigit = digit;
         prevChar = Character.toString(s.charAt(i));
      }
      return sum;
   }
}
