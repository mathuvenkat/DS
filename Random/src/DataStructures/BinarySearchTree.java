package DataStructures;
public class BinarySearchTree
{
   Node root;

   private class Node
   {
      int data;
      Node left;
      Node right;

      public Node(int data)
      {
         this.data = data;
         this.left = null;
         this.right = null;

      }

   }

   public BinarySearchTree(int data)
   {
      Node node = new Node(data);
      this.root = node;
   }

   public void insert(int data)
   {
      Node node = root;

      while (node != null) {
         if (data < node.data) {
            if (node.left == null) {
               node.left = new Node(data);
               break;
            } else
               node = node.left;
         }

         else if (data > node.data) {
            if (node.right == null) {
               node.right = new Node(data);
               break;
            } else
               node = node.right;
         }

      }
   }

   public int maxDepth()
   {
      return (maxDepth(root));
   }

   private int maxDepth(Node node)
   {
      if (node == null)
         return 0;

      int left = maxDepth(node.left);
      int right = maxDepth(node.right);

      if (left > right)
         return left + 1;
      else
         return right + 1;

   }

   public int size()
   {
      return (size(root));
   }

   private int size(Node node)
   {
      if (node == null)
         return 0;

      return (size(node.left) + 1 + size(node.right));
   }

   public void remove(int data)
   {
      Node curr = root;
      Node parent = null;
      boolean isLeft = false;

      // traverse to the element to be removed and keep a ref to its parent
      while (curr != null) {
         if (curr.data == data) {
            break;
         }
         parent = curr;
         if (data < curr.data) {
            isLeft = true;
            curr = curr.left;
         } else {
            curr = curr.right;
            isLeft = false;
         }
      }

      // 1. Special case
      if (curr.right == null) {
         if (curr.left == null) // leaf node
         {
            if (isLeft) {
               parent.left = null;
            } else {
               parent.right = null;
            }
         } else // non-leaf without right node => move left tree of node to
                // replace node
         {
            if (isLeft) {
               parent.left = curr.left;
            } else {
               parent.right = curr.left;
            }
         }
         return;
      }

      // get min of right subtree and replace that with current node
      Node tempLeft = curr.left;
      Node tempRight = curr.right;
      Node min = getMin(curr.right);

      // //2. Special Case : Node is root- if parent is null - then node is root
      if (parent == null) {
         this.root = min;
         root.left = tempLeft;
         root.right = tempRight;
         return;
      }

      if (min.data < parent.data) {
         parent.left = min;
      } else {
         parent.right = min;
      }

      min.left = tempLeft;

      /*
       * if min equals the current element , then it means curr is the right
         child of parent and is moving to replace the parent
         so its right children remain as-is and need not be replaced
       * Eg:
       *      16
       *        \
       *         19
       *         /  \
       *        17    25
       *               \
       *                30
       *
       *
       * Remove 19
       *
       * find min of right subtree - tree with node 25
       * node 25 has no left child
       * hence it is the minimum
       * and is going to replace 19.
       * Its right tree remains as is and should not be replaced with the old element(19's)
       * right tree - which is itself (25)
       *
       *
       */
      if (min != curr.right) {
         min.right = tempRight;
      }

   }

   private Node getMin(Node node)
   {
      if (node.left == null) {
         // In this case - the node has no left tree and hence it is the min
         return node;
      }

      else {
         Node parent = null;

         while (node.left != null) {
            parent = node;
            node = node.left;
         }

         /*
          *  remove this link from its parent since its going to be moved to
          *  replace the removed element
             AND replace parent.left - with min element.right

             Eg:

             16
              \
                19
               /  \
              17   25
               \    \
               18    30

               In this case : On removal of 16,
               min of rightsubtree(19) is 17

               so 17 replace 16
               and 17's(node's) right tree which is all < 19,
               will now become 19's (parent's) left tree.


          */

         parent.left = node.right;
         return node;
      }
   }

   public void print()
   {
      print(root);
   }

   public void print(Node node)
   {
      if (node == null)
         return;

      print(node.left);
      System.out.println(node.data);
      print(node.right);
   }
}
