package dsProblems;
import org.junit.Assert;

public class FindPivotOfRotatedArray {


   public static void main(String args[]) {
      int res = findPivotOfRotatedArray(new int[]{4,5,6,7,8,1,2,3});
      Assert.assertTrue(res == 5);

      res = findPivotOfRotatedArray(new int[]{11,12,13,9,10});
      Assert.assertTrue(res == 3);

      res = findPivotOfRotatedArray(new int[]{4,1,2,3});
      Assert.assertTrue(res == 1);


      res = findPivotOfRotatedArray(new int[]{1,2,3,4,5});
      Assert.assertTrue(res == 0);


   }

   //find how many times the array has been rotated

   public static int findPivotOfRotatedArray(int[] arr) {

      int left = 0;
      int right = arr.length - 1;
      int mid = left + (right - left) / 2;

      while (right-left>1) {
         mid = left + (right - left) / 2;
         if (arr[left] > arr[mid]) {
            right = mid;
            //pivot lies in this region
         }
         else if (arr[mid] > arr[right]) {
            //pivot lies here
            left = mid;
         }else {
            System.out.println("Array has not been rotated");
            return 0;
         }


      }
      System.out.println("ans: " + (left + 1));
      return left + 1;
      //left is index - so  number of positions is left + 1
   }


}
