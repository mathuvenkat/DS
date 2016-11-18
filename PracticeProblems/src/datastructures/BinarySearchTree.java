package datastructures;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BinarySearchTree {
   public Node root;

   public class Node {
      public int data;
      Node left;
      Node right;

      public Node(int data) {
         this.data = data;
         this.left = null;
         this.right = null;

      }

   }

   public BinarySearchTree(int data) {
      Node node = new Node(data);
      this.root = node;
   }

   public Node insertRecursive(Node node, int data) {
      if (node == null) {
         return new Node(data);
      }

      if (data < node.data) {
         node.left = insertRecursive(node.left, data);
      } else {
         node.right = insertRecursive(node.right, data);
      }

      return node;
   }

   public void insert(int data) {
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

   public int maxDepth() {
      return (maxDepth(root));
   }

   private int maxDepth(Node node) {
      if (node == null)
         return 0;

      int left = maxDepth(node.left);
      int right = maxDepth(node.right);

      if (left > right)
         return left + 1;
      else
         return right + 1;

   }

   public int size() {
      return (size(root));
   }

   private int size(Node node) {
      if (node == null)
         return 0;

      return (size(node.left) + 1 + size(node.right));
   }


   public void removeNode(int data, Node node) {
      if (node == null) {
         return;
      }

      if (node.data == data) {
         if (node.left == null && node.right == null) {
            node = null;
         }

         //2. leaf
         else if (node.left == null) {
            Node min = findMin(node.right);
            node.data = min.data;
            min = null;
         }

         else if (node.right == null) {
            Node maxLeft = getMax(node.left);
            node.data = maxLeft.data;
            maxLeft = null;
         }

         else {
            Node maxLeft = getMax(node.left);
            node.data = maxLeft.data;
            //largest value in left subtree will not have a right subtree .
            //So move its left child to its place
            //maxLeft = maxLeft.left;
            removeNode(maxLeft.data, node.left);
         }
         return;
      }
      if (data < node.data) {
         removeNode(data, node.left);
      } else {
         removeNode(data, node.right);
      }
   }


   public Node getMax(Node n) {
      if (n.right == null) {
         return n;
      }
      return (getMax(n.right));
   }

   public Node findMin(Node n) {
      if (n.left == null) {
         return n;
      }
      return (findMin(n.left));
   }


   public void remove(int data) {
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

   private Node getMin(Node node) {
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

   public List<Integer> print() {
      List<Integer> test = new ArrayList<Integer>();
      print(root, test);
      return test;
   }

   public void print(Node node, List<Integer> arr) {
      if (node == null)
         return;

      print(node.left, arr);
      System.out.println(node.data);
      arr.add(node.data);
      print(node.right, arr);
   }

   public List<Integer> printPreOrder() {
      List<Integer> test = new ArrayList<Integer>();
      printPreOrder(root, test);
      return test;
   }

   public void printPreOrder(Node node, List<Integer> arr) {
      if (node == null)
         return;


      System.out.println(node.data);
      arr.add(node.data);

      printPreOrder(node.left, arr);
      printPreOrder(node.right, arr);

   }


   public List<Integer> printPostOrder() {
      List<Integer> test = new ArrayList<Integer>();
      printPostOrder(root, test);
      return test;
   }

   public void printPostOrder(Node node, List<Integer> arr) {
      if (node == null)
         return;


      printPostOrder(node.left, arr);
      printPostOrder(node.right, arr);

      System.out.println(node.data);
      arr.add(node.data);
   }

   public List<Node> returnRootToNodePath(int n) {
      java.util.LinkedList<Node> nodeList = new java.util.LinkedList<Node>();
      Queue<List<Node>> queue = new java.util.LinkedList<List<Node>>();

      nodeList.add(root);
      queue.add(nodeList);
      return returnRootToNodePath(n, queue);
   }


   public List<Node> returnRootToNodePath(int n, Queue<List<Node>> path) {

      while (!path.isEmpty()) {

         List<Node> tmp = path.poll();
         Node lastelement = tmp.get(tmp.size() - 1);
         if (lastelement.data == n) {
            return tmp;
         }

         List<Node> leftChild = new java.util.LinkedList<Node>();
         leftChild.addAll(tmp);

         if (lastelement.left != null) {
            leftChild.add(lastelement.left);
            path.add(leftChild);
         }

         List<Node> rightChild = new java.util.LinkedList<Node>();
         rightChild.addAll(tmp);
         if (lastelement.right != null) {
            rightChild.add(lastelement.right);
            path.add(rightChild);
         }

      }

      return null;

   }

   public void printRootToLeafPathsDFS() {
      printRootToLeafPathsDFS(root, new ArrayList<Integer>());
   }

   public void printRootToLeafPathsDFS(Node node, List<Integer> arr) {

      if (node == null)
         return;

      if (node.left == null && node.right == null) {
         System.out.println("Path found...");
         arr.add(node.data);
         for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i));
         }
         return;
      }

      arr.add(node.data);
      List<Integer> temp = new ArrayList<Integer>();
      temp.addAll(arr);

      printRootToLeafPathsDFS(node.left, arr);
      printRootToLeafPathsDFS(node.right, temp);


   }


   public void printRootToLeafPaths() {
      printRootToLeafPaths(root, new ArrayList<Integer>());
   }

   public void printRootToLeafPaths(Node node, List<Integer> arr) {
      if (node == null) {
         return;
      }

      if (node.left == null && node.right == null) {
         System.out.println("Path found...");
         for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i));
         }
         System.out.println(node.data);
         return;
      }

      arr.add(node.data);

      List<Integer> arrList = new ArrayList<Integer>();
      arrList.addAll(arr);


      printRootToLeafPaths(node.left, arrList);

      //We dont need the left paths traversed which are in arrList - so use arr

      arrList = arr;

      printRootToLeafPaths(node.right, arrList);


   }


   //returns if one node is descendant of the other
   //used to decide which side of the tree the nodes is on.
   public boolean isLeftDescendantOf(Node root, Node a) {
      if (root == null) {
         return false;
      }
      if (root == a) {
         return true;
      } else {
         return (isLeftDescendantOf(root.left, a)
               || isLeftDescendantOf(root.right, a));
      }

   }
   
   private Node getNode(int a, Node node) {
      if (node == null) {
         return null;
      }
      if (node.data == a) {
         return node;
      }
      if (a < node.data) {
         node = node.left;
      } else {
         node = node.right;
      }
      return getNode(a, node);

   }
   
   public Node findCommmonAncestor(int a, int b){
      
      Node aNode= getNode(a,root);
      Node bNode= getNode(b,root);
      return findCommonAncestor(aNode,bNode,root);
   }

   public Node findCommonAncestor(Node a, Node b, Node root) {

      boolean isLefta = isLeftDescendantOf(root.left, a);
      boolean isLeftb = isLeftDescendantOf(root.left, b);

      if ((isLefta && !isLeftb) || (!isLefta && isLeftb)) {
         return root;
      }

      else {
         //means they are on the same side.
         if (isLefta) {
            return findCommonAncestor(a, b, root.left);
         } else {
            return findCommonAncestor(a, b, root.right);
         }

      }
     
   }


   //roots of both the trees
   public boolean sameTree(Node a, Node b) {
      if ((a == null & b != null) || (b == null && a != null)) {
         return false;
      }

      if (a == null && b == null) {
         return true;
      }
      return (sameTree(a.left, b.left) && sameTree(a.right, b.right));
   }

   //is valid BST
   public boolean isBST(Node node, int min, int max) {
      if (node == null) {
         return true;
      }

      if (node.data < min || node.data > max) {
         return false;
      }

      boolean left = isBST(node.left, min, node.data);
      boolean right = isBST(node.right, node.data, max);
      return left && right;

   }

   public void createDuplicatesOnLeft() {
      createDuplicatesOnLeft(root);
   }

   public void createDuplicatesOnLeft(Node node) {
      if (node == null) {
         return;
      }
      createDuplicatesOnLeft(node.left);

      Node oldLeft = node.left;
      node.left = new Node(node.data);
      node.left.left = oldLeft;

      createDuplicatesOnLeft(node.right);

   }


   public void createMirrorImage() {
      createMirrorImage(root);
   }

   public void createMirrorImage(Node node) {
      if (node == null) {
         return;
      }
      createMirrorImage(node.left);
      createMirrorImage(node.right);

      Node oldLeft = node.left;
      node.left = node.right;
      node.right = oldLeft;


   }

   public Node constructTreeFromInAndPostorders(int[] inorder, int[] postorder) {
      Node root =
            constructTreeFromInAndPostorders(inorder, postorder, 0, inorder.length - 1,
                  new int[] { postorder.length - 1 });
      return root;
   }

   public Node constructTreeFromInAndPostorders(int[] inorder, int[] postorder,
         int InStart, int InEnd, int[] postIndex) {

      if (InStart >InEnd) {
         return null;
      }




      int root = postorder[postIndex[0]];
      postIndex[0]--;
      Node rootNode = new Node(root);

      if (InStart == InEnd) {
         return rootNode;
      }

      int inOrderIndex = getInorderIndex(root, inorder);
      rootNode.right =
            constructTreeFromInAndPostorders(inorder, postorder,
                  inOrderIndex + 1, InEnd, postIndex);

      rootNode.left =
            constructTreeFromInAndPostorders(inorder, postorder, InStart,
                  inOrderIndex - 1, postIndex);


      return rootNode;
   }


   public Node constructTree(int[] inorder, int[] preorder) {
      //      Node root =
      //            constructTree(inorder, preorder, 0, preorder.length - 1, 0,
      //                  inorder.length - 1);
      //      return root;

      Node root =
            constructTree(inorder, preorder, 0, inorder.length - 1,
                  new int[] { 0 });
      return root;
   }

   public Node constructTree(int[] inorder, int[] preorder, int InStart,
         int InEnd, int[] preIndex) {

      if (InStart > InEnd) {
         return null;
      }

      int root = preorder[preIndex[0]];
      preIndex[0]++;
      Node rootNode = new Node(root);

      if (InStart == InEnd) {
         return rootNode;
      }

      //preIndex[0]++;

      int inOrderIndex = getInorderIndex(root, inorder);

      rootNode.left =
            constructTree(inorder, preorder, InStart, inOrderIndex - 1,
                  preIndex);
      rootNode.right =
            constructTree(inorder, preorder, inOrderIndex + 1, InEnd, preIndex);
      return rootNode;
   }

   public Node constructTree(int[] inorder, int[] preorder, int PreStart,
         int PreEnd, int InStart, int InEnd) {

      if (PreStart > PreEnd || InStart > InEnd) {
         return null;
      }


      int root = preorder[PreStart];
      Node rootNode = new Node(root);
      int inOrderIndex = getInorderIndex(root, inorder);

      rootNode.left =
            constructTree(inorder, preorder, PreStart + 1, PreStart
                  + inOrderIndex, InStart, inOrderIndex - 1);
      rootNode.right =
            constructTree(inorder, preorder, PreStart
                  + (inOrderIndex - InStart) + 1, PreEnd, inOrderIndex + 1,
                  InEnd);
      return rootNode;


   }

   private int getInorderIndex(int val, int[] Inorder) {
      for (int i = 0; i < Inorder.length; i++) {
         if (Inorder[i] == val) {
            return i;
         }
      }
      return -1;
   }


   public void printLineByLine() {
      printLineByLine(root);
   }

   public void printLineByLine(Node node) {
      Queue<Node> parentQueue = new java.util.LinkedList<Node>();
      Queue<Node> childQueue = new java.util.LinkedList<Node>();

      parentQueue.add(node);
      System.out.println(" " + node.data);
      while (!parentQueue.isEmpty()) {
         node = parentQueue.remove();

         if (node.left != null) {
            childQueue.add(node.left);
         }
         if (node.right != null) {
            childQueue.add(node.right);
         }
         if (parentQueue.isEmpty()) {
            Queue<Node> tmpchildQueue = new java.util.LinkedList<Node>();
            tmpchildQueue.addAll(childQueue);
            parentQueue = tmpchildQueue;
            while (!childQueue.isEmpty()) {
               System.out.print(" " + childQueue.remove().data);
            }
            System.out.println();
         }
      }
   }


   /**
    * Print a tree in zig-zag format
    */
   public void printzigzag() {
      printzigzag(root);
   }

   private void printzigzag(Node node) {

      int currlevel = 0;
      Stack<Node> curr = new Stack<Node>();
      Stack<Node> child = new Stack<Node>();
      curr.push(node);

      while (!curr.empty()) {

         Node top = curr.pop();
         System.out.println("Element: " + top.data);

         if (currlevel % 2 == 0) {
            if (top.left != null) {
               child.push(top.left);
            }
            if (top.right != null) {
               child.push(top.right);
            }
         } else {
            if (top.right != null) {
               child.push(top.right);
            }
            if (top.left != null) {
               child.push(top.left);
            }
         }

         if (curr.isEmpty()) {
            curr = child;
            child = new Stack<Node>();
            currlevel++;
         }
      }


   }
}
