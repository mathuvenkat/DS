package datastructures;

import java.util.PriorityQueue;


public class BoundedPriorityQueue extends PriorityQueue<Integer> {

   /**
    *
    */
   private static final long serialVersionUID = 1L;
   int maxSize;

   public BoundedPriorityQueue(int maxSize) {
      this.maxSize = maxSize;
   }


   @Override
   public boolean add(Integer a) {
      if (size() == maxSize) {
         remove();
      }
      return super.add(a);
   }


   public int getNLargest() {
      return poll();
   }


   public static void main(String[] args) {
      BoundedPriorityQueue bp = new BoundedPriorityQueue(8);


      int[] arr = { -2, 6, 7, 12, 3, 4, 17, 100, 4, -18, 63, 45 };

      bp.add(arr[0]);

      for (int i=1; i < arr.length ; i++) {
         if (arr[i] > bp.peek())
            bp.add(arr[i]);
      }

      System.out.println("Answer is " + bp.getNLargest());
   }


}
