package mathProblems;

import java.util.BitSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;


public class ConvertToRoman {

   public static void main(String args[]) {
      //      System.out.println(fib(7));
      //      System.out.println(fibIterative(7));
      //      System.out.println(factorial(4));
      //      System.out.println(factorialIterative(4));
      //      int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
      //      shuffle(arr);
      //
      //
      //      //      for (int i = 1;i<256;i++) {
      //      //         System.out.println("i="+i+" -> "+RomanNumerals(i));
      //      //       }
      //
      //      //System.out.println(" -> "+RomanNumerals(3992));
      //      System.out.println(" -> " + RomanNumerals(69));


      int[] arr = new int[] { 8, -6, 0, -2, -4, 11, 10, 0, 9, 0 };
      sort(arr);
      
      int res = sumofPrimes(20);
      System.out.println("Sum of primes is " + res);
   }

   public static int fib(int n) {
      if (n == 0)
         return 0;

      if (n == 1)
         return 1;

      else {
         return (fib(n - 1) + fib(n - 2));
      }
   }


   public static int fibIterative(int n) {

      if (n == 0)
         return 0;

      if (n == 1)
         return 1;

      int first = 0;
      int second = 1;

      int sum = 0;
      for (int i = 1; i < n; i++) {
         sum = first + second;
         first = second;
         second = sum;
      }

      return sum;
   }


   public static int factorial(int n) {
      if (n == 0)
         return 1;

      return (factorial(n - 1) * n);
   }

   public static int factorialIterative(int n) {
      if (n == 0)
         return 1;
      int res = 1;
      for (int i = 1; i <= n; i++) {
         res = res * i;
      }
      return res;
   }


   public static void shuffle(int arr[]) {
      Random randomGenerator = new Random();
      int j = arr.length - 1;
      for (int i = 0; i <= j; i++) {
         int random = randomGenerator.nextInt(arr.length);
         int temp = arr[random];
         arr[random] = arr[j];
         arr[j] = temp;
         j--;
      }

      for (int k = 0; k < arr.length; k++) {
         System.out.println(arr[k]);
      }
   }


   /**
    * SUM OF ALL PRIME NUMBERS LESS THAN n Eg : if n = 20 sum = 2 +3 +5 +7
    * +11+13+17+19
    * 
    * @param n
    */
   public static int sumofPrimes(int n) {
      BitSet sieve = new BitSet(n);

      
      for (int i = 2; i < n - 1; i++) {
         int j = 2;
         int prod = 0;
         while (prod < n) {
            prod = i * j;
            if (prod < n) {
               //set non primes to true
               sieve.set(prod);
               j++;
            } else {
               break;
            }
         }
      }

      int sum = 0;
      for (int i = 2; i < n; i++) {
         if (sieve.get(i) == false) {
            sum = sum + i;
         }

      }
      return sum;
   }

   public static String RomanNumerals(int numberToConvert) {
      LinkedHashMap<String, Integer> roman_numerals =
            new LinkedHashMap<String, Integer>();
      roman_numerals.put("M", 1000);
      roman_numerals.put("CM", 900);
      roman_numerals.put("D", 500);
      roman_numerals.put("CD", 400);
      roman_numerals.put("C", 100);
      roman_numerals.put("XC", 90);
      roman_numerals.put("L", 50);
      roman_numerals.put("XL", 40);
      roman_numerals.put("X", 10);
      roman_numerals.put("IX", 9);
      roman_numerals.put("V", 5);
      roman_numerals.put("IV", 4);
      roman_numerals.put("I", 1);
      String res = "";
      for (Map.Entry<String, Integer> entry : roman_numerals.entrySet()) {
         int matches = numberToConvert / entry.getValue();
         res += repeat(entry.getKey(), matches);
         numberToConvert = numberToConvert % entry.getValue();
      }
      return res;
   }

   private static String repeat(String s, int n) {
      if (s == null) {
         return null;
      }
      final StringBuilder sb = new StringBuilder();
      for (int i = 0; i < n; i++) {
         sb.append(s);
      }
      return sb.toString();
   }

   /**
    * Max subsequence in an array
    * 
    * @param inputArray
    * @param size
    */
   void findMaxSumSequence(int inputArray[], int size) {
      int maxSum = inputArray[0];
      int maxStartIndex = 0;
      int maxEndIndex = 0;

      int curSum = inputArray[0];
      int curStartIndex = 0;


      for (int i = 1; i < size; i++) {
         if (curSum < 0) {
            curSum = 0;
            curStartIndex = i;
         }

         curSum = curSum + inputArray[i];

         if (curSum > maxSum) {

            maxSum = curSum;
            maxStartIndex = curStartIndex;
            maxEndIndex = i;
         }
      }

      System.out.println("Max Sum is " + maxSum + " starting at "
            + maxStartIndex + " ending at " + maxEndIndex);
   }

   //sort -, 0 , +ve in an array 
   /**
    * Sort array into -ve , 0 and +ve
    * 
    * @param arr
    */
   public static void sort(int[] arr) {

      int lo = 0;
      int hi = arr.length - 1;
      int mid = 0;

      while (mid <= hi) {
         if (arr[mid] < 0) {
            int tmp = arr[lo];
            arr[lo] = arr[mid];
            arr[mid] = tmp;
            mid++;
            lo++;
         }
         if (arr[mid] == 0) {
            mid++;
         }
         if (arr[mid] > 0) {
            int tmp = arr[hi];
            arr[hi] = arr[mid];
            arr[mid] = tmp;
            hi--;
         }
      }

      for (int i = 0; i < arr.length; i++) {
         System.out.println(arr[i]);
      }

   }
}
