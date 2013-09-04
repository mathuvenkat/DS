
public class BT
{
   Node root;

   public BT( int data)
   {
      Node node = new Node(data);
      this.root = node;
   }


   public void insert( int data )
   {
      Node node = root;

      while( node != null)
      {
         if( data < node.data)
         {
            if( node.left == null )
            {
               node.left = new Node(data);
               break;
            }
            else
               node = node.left;
         }

         else
         if( data > node.data)
         {
            if( node.right == null )
            {
               node.right = new Node(data);
               break;
            }
            else
               node = node.right;
         }

      }
   }

   public int maxDepth()
   {
      return(maxDepth(root));
   }

   public int maxDepth(Node node)
   {
      if( node == null)
         return 0;

      int left = maxDepth( node.left);
      int right = maxDepth(node.right);

      System.out.println("Left" + left);
      System.out.println("Right" + right);
      System.out.println("data" + node.data);

      if( left > right)
         return left + 1;
      else
         return right + 1;


   }

   public void print()
   {
      print(root);
   }

   public void print(Node node)
   {
      if( node == null)
         return;

      print( node.left);
      System.out.println(node.data);
      print( node.right);
   }

   public static void main(String args[])
   {
      BT bt = new BT(11);
      bt.insert(7);
      bt.insert(15);
      bt.insert(5);
      bt.insert(8);
      bt.insert(6);
      bt.insert(9);
      bt.insert(4);
      bt.insert(10);

      bt.print();

      int t = bt.maxDepth();
      System.out.println("T" + t);



   }

}
