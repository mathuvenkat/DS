package ds;
import java.util.Random;

public class Substring
{
   //returns position of substring
   public static int subString(String s,
                               String t)
   {
      int slen = s.length();
      int tlen = t.length();

      for (int i = 0; i < slen; i++) {
         for (int j = 0; j < tlen; j++) {
            System.out.println("big" + s.charAt(i + j));
            System.out.println("small" + t.charAt(j));

            if (s.charAt(i + j) != t.charAt(j))
               break;

            if (j == tlen - 1)
               return i;
         }
      }
      return -1;
   }

   public static void printshuffle(int n)
   {
      int[] arr = new int[n];

      for (int i = 0; i < n; i++)
         arr[i] = i + 1;

      for (int l = 0; l < n; l++)
         System.out.println("original order" + arr[l]);

      for (int j = n - 1; j > 0; j--) {
         Random r = new Random();
         int random = r.nextInt(j);

         System.out.println("random number between 0 and " + j + "is" + random);

         // swap
         int temp = arr[j];
         arr[j] = arr[random];
         arr[random] = temp;

      }

      for (int k = 0; k < n; k++)
         System.out.println("shuffled order " + arr[k]);
   }

   public static void merge(int[] a,
                            int[] b)
   {
      int[] arr = new int[a.length + b.length];

      int i = 0;
      int j = 0;
      int k = 0;

      int alen = a.length;
      int blen = b.length;

      while (i < alen && j < blen) {
         if (a[i] < b[j]) {
            arr[k] = a[i];
            i++;
         } else if (a[i] > b[j]) {
            arr[k] = b[j];
            j++;
         } else if (a[i] == b[j]) {
            arr[k] = a[i];
            i++;
            j++;
         }

         k++;
      }

      if (i >= a.length) {
         while (j < b.length) {

            arr[k] = b[j];
            k++;
            j++;
         }

      } else if (j >= b.length) {
         while (i < a.length) {
            arr[k] = a[i];
            k++;
            i++;
         }

      }

      for (int m = 0; m < arr.length; m++)
         System.out.println("merged order " + arr[m]);
   }

   public static int repeatedChar(String s)
   {
      char[] str = s.toCharArray();

      int start = 0;
      int potential_start = 0;
      int count = 1;
      int maxcount = 1;

      for (int i = 1; i < str.length; i++) {
         if (str[i] == str[i - 1]) {
            count++;
            if (count > maxcount) {
               maxcount = count;
               start = potential_start;
            }
         }

         else {
            potential_start = i;
            count = 1;
         }

      }

      return start;
   }

   public static int maxSum(int[] arr)
   {

      int maxsum = 0;
      int currentSum = 0;

      int maxStartIndex = 0;
      int maxEndIndex = 0;

      int maxStartNow = 0;

      for (int curr = 0; curr < arr.length; curr++) {

         System.out.println("curr element" + arr[curr]);

         currentSum += arr[curr];

         System.out.println("curr sum" + currentSum);
         System.out.println("maxStartNow" + maxStartNow);

         if (currentSum > maxsum) {
            maxsum = currentSum;
            maxStartIndex = maxStartNow;
            maxEndIndex = curr;
         } else {
            System.out.println("changing index curr sum" + currentSum);
            if (currentSum < 0) {
               currentSum = 0;
               maxStartNow = curr + 1;

            }
         }

      }

      System.out.println("Start at " + maxStartIndex + " end at " + maxEndIndex);
      return maxsum;

   }

   private static void swap(int[] arr,
                            int i,
                            int j)
   {
      int temp = arr[i];
      arr[i] = arr[j];
      arr[j] = temp;
   }

   public static void sortnegpos(int[] arr)
   {

      int n_index = 0;
      int p_index = arr.length - 1;

      for (int i = 0; i <= p_index;) {

         if (arr[i] < 0) {
            swap(arr, i, n_index);
            n_index++;
            i++;

         }

         else if (arr[i] > 0) {
            swap(arr, i, p_index);
            p_index--;
            // Note that we don't increment i here because the element
            // that was swapped is unknown
         }

         else
            // if arr[i]==0
            i++;
      }

   }

   public static void multiplyArr(int[] a , int[] b){
      int[] c= new int[a.length + b.length];
      int row = c.length - 1;

      for( int j = b.length -1 ; j >=0 ; j--){
         int carry = 0;
         int shift = row;

         for( int i = a.length -1 ; i >= 0 ;i--){
            int m = a[i]*b[j];
            int sum = m + carry + c[shift];
            c[shift] = sum%10;
            carry = sum/10;
            shift--;
         }
         c[shift] = carry = c[shift];
         row--;

      }

   }

   public static void main(String args[])
   {
      System.out.println( "result is " + Substring.subString("abcabcd",
       "abcd") );
      Substring.printshuffle(15);

      int[] arr = { 1, 5, 8, 14, 25 };
      int[] arr1 = { 1, 4, 6, 8, 13, 27 };
      Substring.merge(arr, arr1);

      int arr5[] = {1 , -3 , 5 ,-2 ,9,-8,-6,4};


      maxSum(arr5);

   }

}
