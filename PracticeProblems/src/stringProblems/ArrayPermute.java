package stringProblems;


public class ArrayPermute {

   /**
    * @param args
    *           the command line arguments
    */
   void printArray(int[] a) {
      for (int i = 0; i < a.length; i++) {
         System.out.print(a[i] + " ");

      }
      System.out.println("");
   }

   void permute(int[] a, int k) {
      if (k == a.length)
         printArray(a);
      else
         for (int i = k; i < a.length; i++) {
            int temp = a[k];
            a[k] = a[i];
            a[i] = temp;
            permute(a, k + 1);
            temp = a[k];
            a[k] = a[i];
            a[i] = temp;
         }
   }

   public static void main(String[] args) {
      ArrayPermute p = new ArrayPermute();
      int a[] = { 1, 2, 3 };
      //p.permute(a, 0);

      p.perm2(a, a.length);
   }


   void perm2(int[] a, int n) {
      if (n == 1) {
         print(a);
         return;
      }
      for (int i = 0; i < n; i++) {
         swap(a, i, n - 1);
         perm2(a, n - 1);
         swap(a, i, n - 1);
      }
   }

   private void print(int[] a) {
      System.out.println("");
      for (int i = 0; i < a.length; i++) {
         System.out.print(a[i]);
      }

   }

   // swap the characters at indices i and j
   void swap(int[] a, int i, int j) {
      int c;
      c = a[i];
      a[i] = a[j];
      a[j] = c;
   }

}
