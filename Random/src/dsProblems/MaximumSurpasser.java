package dsProblems;


public class MaximumSurpasser {

   public class Node {
      Node left;
      Node right;

      int value;
      int surpasserValueOfNode;
      int numOfElementsAtRightSubtree;

      public Node(int value) {
         this.value = value;
         this.left = null;
         this.right = null;
      }

   }

   Node root = null;

   public Node insert(int value) {
      if (root == null) {
         root = new Node(value);
         //others are 0 at root
         return root;
      }

      Node node = root;
      int surpasserCount = 0;
      while (node != null) {
         if (value < node.value) {
            surpasserCount =
                  surpasserCount + node.numOfElementsAtRightSubtree + 1;
            if (node.left == null) {
               node.left = new Node(value);
               node.left.surpasserValueOfNode = surpasserCount;
               return node.left;
            } else {
               node = node.left;
            }
         } else {
            node.numOfElementsAtRightSubtree++;
            if (node.right == null) {

               node.right = new Node(value);
               node.right.surpasserValueOfNode = surpasserCount;
               return node.right;
            }
            node = node.right;
         }
      }
      return null;

   }

   public static void main(String args[]) {
      MaximumSurpasser ms = new MaximumSurpasser();
      
      //take from last to initialize binary search tree so that for each 
      //element surpassers are the number of elements above it 

      int[] arr = { 82, 74, 17, 93, 96, 20, 55, 15, 24, 25, 56 };
      int[] res_arr = new int[arr.length];

      for (int i = arr.length -1 ; i >= 0; i--) {
         Node res = ms.insert(arr[i]);
         res_arr[i] = res.surpasserValueOfNode;
      }

      for (int i = 0; i < res_arr.length; i++) {
         System.out.println("" + res_arr[i]);
      }

   }

}
