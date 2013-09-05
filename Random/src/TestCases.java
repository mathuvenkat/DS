import junit.framework.Assert;

import org.testng.annotations.Test;


public class TestCases
{

   //@Test()
   public void TrieTest()
      throws Exception
   {
      Trie trie = new Trie();
      trie.insert("aditi" );
      trie.insert("adira");
      trie.insert("advait");
      trie.insert("aditya");
      trie.insert("advik");
      trie.insert("aarush");
      trie.insert("aaradhana");

      trie.insert("mathu" , "3127525813");
      trie.insert("mad" , "8220447177");
      trie.insert("moorthy" , "9842445435" );
      trie.insert("gokul" , "3128230915");
      trie.insert("mom", "8220447283");

      Assert.assertTrue(trie.containsKey("adira"));
      Assert.assertFalse(trie.containsKey("adir"));
      Assert.assertTrue(trie.containsKey("mom"));
      Assert.assertTrue(trie.containsKey("mathu"));

      trie.substrings("ad");
      trie.substrings("adv");
      trie.substrings( "m");
      trie.substrings("aa");

   }


   //@Test()
   public void HeapTest()
      throws Exception
   {
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

   @Test()
   public void LinkedListTest()
      throws Exception
   {
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




   }


}
