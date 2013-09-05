package test;
import junit.framework.Assert;
import main.BTree;
import main.Node;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BTreeTest
{
   BTree tree;

   @BeforeMethod(alwaysRun = true)
   public void testSetUp()
      throws Exception
   {
      tree = new BTree();
      tree.root = new Node(1);
      tree.root.left = new Node(2);
      tree.root.right = new Node(3);
      tree.root.left.left = new Node(4);
      tree.root.left.right = new Node(5);
      tree.root.right.left = new Node(6);
      tree.root.right.left.left = new Node(10);
      tree.root.right.right = new Node(7);
      tree.root.right.right.right = new Node(8);
      tree.root.right.right.right.left = new Node(9);

   }

   @Test()
   public void test001()
   {
      Assert.assertTrue(tree.isExists(new Node(2)));
      Assert.assertTrue(tree.isExists(new Node(10)));
      Assert.assertTrue(tree.isExists(new Node(1)));
      Assert.assertTrue(tree.isExists(new Node(9)));

   }

   @Test()
   public void test002()
   {
      Assert.assertFalse(tree.isExists(new Node(15)));
   }

   @Test(expectedExceptions = RuntimeException.class)
   public void test003()
   {
      tree.isExists(null);
   }

}
