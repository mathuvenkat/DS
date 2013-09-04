import junit.framework.Assert;

import org.testng.annotations.Test;



public class TestCases
{

   @Test()
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


   @Test()
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


      int min = tree.getMin();

      tree.remove();

      int newmin = tree.getMin();
      tree.traverseInOrder();


   }


}
