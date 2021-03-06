package test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import stringProblems.longestSubstringWithNoRepeatedCharacters;

import org.testng.annotations.Test;
import org.testng.internal.junit.ArrayAsserts;

import datastructures.BinarySearchTree;
import datastructures.HeapTree;
import datastructures.LinkedList;
import datastructures.Trie;
import datastructures.BinarySearchTree.Node;
import graph.Graph;

public class TestCases {

   //@Test()
   public void TrieTest() throws Exception {
      Trie trie = new Trie();
      trie.insert("aditi");
      trie.insert("adira");
      trie.insert("advait");
      trie.insert("aditya");
      trie.insert("advik");
      trie.insert("aarush");
      trie.insert("aaradhana");

      trie.insert("mathu", "3127525813");
      trie.insert("mad", "8220447177");
      trie.insert("moorthy", "9842445435");
      trie.insert("gokul", "3128230915");
      trie.insert("mom", "8220447283");

      Assert.assertTrue(trie.containsKey("adira"));
      Assert.assertFalse(trie.containsKey("adir"));
      Assert.assertTrue(trie.containsKey("mom"));
      Assert.assertTrue(trie.containsKey("mathu"));

      trie.substrings("ad");
      trie.substrings("adv");
      trie.substrings("m");
      trie.substrings("aa");

   }

   //@Test()
   public void HeapTest() throws Exception {
      HeapTree tree = new HeapTree(6);

      tree.insert(5);
      tree.insert(8);
      tree.insert(4);
      tree.insert(11);
      tree.insert(14);
      tree.insert(15);

      tree.traverseInOrder();
      Assert.assertTrue(tree.getMin() == 5);

      tree.remove();
      Assert.assertTrue(tree.getMin() == 4);
      tree.traverseInOrder();
   }

   //@Test()
   public void LinkedListTest() throws Exception {
      LinkedList list = new LinkedList('n');
      list.insert('o');
      list.insert('o');
      list.insert('n');

      Assert.assertTrue(list.isPalin());

      LinkedList list1 = new LinkedList('n');
      list1.insert('o');
      list1.insert('r');
      list1.insert('o');
      list1.insert('n');

      Assert.assertTrue(list1.isPalin());

      LinkedList list2 = new LinkedList('n');
      list2.insert('o');
      list2.insert('r');
      list2.insert('a');
      list2.insert('n');

      Assert.assertFalse(list2.isPalin());
      list2.print(list2.getHead());

   }

   @Test()
   public void BSTTest() throws Exception {
      BinarySearchTree bt = new BinarySearchTree(16);
      bt.insert(10);
      bt.insert(19);
      bt.insert(17);
      bt.insert(18);
      bt.insert(8);
      bt.insert(14);
      bt.insert(25);
      bt.insert(9);
      bt.insert(13);
      bt.insert(15);
      bt.insert(7);
      bt.insert(6);
      bt.insert(30);

      System.out.println("Inserted 14 elements");
      Assert.assertTrue(bt.size() == 14);

      System.out.println("Inorder traversal of tree");
      List<Integer> inOrder = bt.print();

      System.out.println("Preorder traversal of tree");
      List<Integer> preOrder = bt.printPreOrder();

      System.out.println("Postorder traversal of tree");
      List<Integer> postOrder = bt.printPostOrder();

      System.out.println("Zig zag printing of tree");
      bt.printzigzag();

      System.out.println("Print line order");
      bt.printLineByLine();


      System.out.println("Print between root and another node");
      List<Node> res1 = bt.returnRootToNodePath(6);
      for (Node n : res1) {
         System.out.print(" " + n.data);
      }

      int[] inOrderArr = new int[inOrder.size()];
      for (int i = 0; i < inOrder.size(); i++) {
         inOrderArr[i] = inOrder.get(i);
      }
      int[] preOrderArr = new int[preOrder.size()];
      for (int i = 0; i < preOrder.size(); i++) {
         preOrderArr[i] = preOrder.get(i);
      }
      int[] postOrderArr = new int[postOrder.size()];
      for (int i = 0; i < postOrder.size(); i++) {
         postOrderArr[i] = postOrder.get(i);
      }
      
      System.out.println("Find common ancestor");
      Node lca = bt.findCommmonAncestor(6,15);
      Assert.assertTrue(lca.data == 10);
      
      lca = bt.findCommmonAncestor(7, 9);
      Assert.assertTrue(lca.data == 8);

      lca = bt.findCommmonAncestor(7, 30);
      Assert.assertTrue(lca.data == 16);


      System.out
            .println("Construct tree given preorder and inorder traversals");
      Node constructedRoot = bt.constructTree(inOrderArr, preOrderArr);

      List<Integer> res = new ArrayList<Integer>();
      bt.printPreOrder(constructedRoot, res);

      for (int i = 0; i < res.size(); i++) {
         Assert.assertTrue(preOrderArr[i] == res.get(i));
      }

      System.out
            .println("Construct tree given postorder and inorder traversals");
      constructedRoot =
            bt.constructTreeFromInAndPostorders(inOrderArr, postOrderArr);

      res = new ArrayList<Integer>();
      bt.printPostOrder(constructedRoot, res);

      for (int i = 0; i < res.size(); i++) {
         Assert.assertTrue(postOrderArr[i] == res.get(i));
      }


      System.out.println("Root to leaf paths");
      bt.printRootToLeafPaths();

      System.out.println("Root to leaf paths dfs");
      bt.printRootToLeafPathsDFS();

      Assert.assertTrue(bt.maxDepth() == 5);

      System.out
            .println("Removing 10 - left element with right child having left child");


      bt.remove(10);
      Assert.assertTrue(bt.maxDepth() == 5);
      Assert.assertTrue(bt.size() == 13);

      System.out
            .println("Removing 19 - element with right child - having no left child  and left child having right child");
      bt.remove(19);
      Assert.assertTrue(bt.size() == 12);

      System.out.println("Removing 16 - Root of tree");
      bt.remove(16);
      Assert.assertTrue(bt.size() == 11);

      System.out
            .println("Removing 7 - element with no right children - also reducing level of tree");
      bt.remove(7);
      Assert.assertTrue(bt.size() == 10);
      Assert.assertTrue(bt.maxDepth() == 4);

      System.out.println("Removing 6 - leaf");
      bt.remove(6);
      Assert.assertTrue(bt.size() == 9);
      Assert.assertTrue(bt.maxDepth() == 4);

      System.out
            .println("Inorder traversal of tree after removal of several elements");
      bt.print();

      System.out
            .println("Preorder traversal of tree after removal of several elements");
      bt.printPreOrder();

      System.out.println("Creating duplicates");
      bt.createDuplicatesOnLeft();

      System.out.println("Inorder traversal of tree after duplicate");
      bt.print();

      System.out.println("Preorder traversal of tree after duplicate");
      bt.printPreOrder();

      System.out.println("Creating Mirror Image");
      bt.createMirrorImage();

      System.out.println("Inorder traversal of tree after mirror image");
      bt.print();

   }

   //@Test()
   public void GraphTest() throws Exception {
      System.out
            .println("UNDIRECTED GRAPH ASUMING CONNECTION EXISTS BETWEEN BOTH VERTICES");

      Graph g = new Graph(10);

      g.insertEdge(0, 8, false);
      g.insertEdge(8, 2, false);
      g.insertEdge(2, 1, false);
      g.insertEdge(8, 9, false);
      g.insertEdge(1, 9, false);
      g.insertEdge(1, 3, false);
      g.insertEdge(1, 7, false);
      g.insertEdge(2, 4, false);
      g.insertEdge(3, 5, false);
      g.insertEdge(7, 6, false);
      g.insertEdge(5, 6, false);

      System.out.println("BFS ..");
      g.BFS(2);

      System.out.println("DFS ..");
      g.DFS(2);

      System.out.println("DIRECTED GRAPH TESTS....");

      Graph g1 = new Graph(8);
      g1.insertEdge(0, 1, true);
      g1.insertEdge(1, 2, true);
      g1.insertEdge(2, 3, true);
      g1.insertEdge(1, 3, true);

      System.out.println("BFS ..");
      g1.BFS(0);

      System.out.println("DFS ..");
      g1.DFS(0);

      Assert.assertFalse(g1.cycleExists());

      System.out.println("Adding edge to create cycle");
      g1.insertEdge(3, 0, true);
      Assert.assertTrue(g1.cycleExists());

      System.out.println("Adding edge");
      g1.insertEdge(4, 5, true);
      Assert.assertTrue(g1.numComponents() == 4);

      System.out.println("Adding annother edge");
      g1.insertEdge(6, 7, true);
      Assert.assertTrue(g1.numComponents() == 3);

      System.out.println("Addding edge but this doesnt link components");
      g1.insertEdge(4, 0, true);
      Assert.assertTrue(g1.numComponents() == 3);

      System.out.println("Linking two components");
      g1.insertEdge(0, 4, true);
      Assert.assertTrue(g1.numComponents() == 2);

   }

   //@Test()
   public void LongestSubstringTest() throws Exception {
      String res;
      longestSubstringWithNoRepeatedCharacters sol = new longestSubstringWithNoRepeatedCharacters();
      res = sol.findlongestSubstring("abc");
      Assert.assertTrue(res.length() == 3);


      res = sol.findlongestSubstring("abcda");
      Assert.assertTrue(res.length() == 4);

      res = sol.findlongestSubstring("king of chinaloyd");
      Assert.assertTrue(res.length() == 11);

      res = sol.findlongestSubstring("aabbc");
      Assert.assertTrue(res.length() == 2);

      res = sol.findlongestSubstring("abaaabc");
      Assert.assertTrue(res.length() == 3);

      res = sol.findlongestSubstring("abcdae");
      Assert.assertTrue(res.length() == 5);

      res = sol.findlongestSubstring("aabb");
      Assert.assertTrue(res.length() == 2);

      res = sol.findlongestSubstring("atabb");
      Assert.assertTrue(res.length() == 3);

      res = sol.findlongestSubstring("aaa");
      Assert.assertTrue(res.length() == 1);
   }

}
