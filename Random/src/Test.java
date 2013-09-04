
public class Test
{

   public class Node
   {
      String data;
      Node next;
   }

   public boolean isPalin(Node node)
   {
      Node mid = findMid(node);

      //odd or even size

      Node reversed = reverse(mid.next);


      while( reversed.next != null)
      {
         if( node.data.equals(reversed.data))
         {
            node = node.next;
            reversed = reversed.next;
         }
         else
            return false;
      }

      return true;
   }

   public Node findMid(Node node)
   {
      Node slow = node;
      Node fast = node.next;

      while( fast != null && fast.next != null)
      {
         slow = slow.next;
         fast = fast.next.next;
      }

      return slow;
   }


   public Node reverse(Node node)
   {
      Node head = node;

      Node prev = null;
      while( node != null)
      {
         Node next = node.next;
         node.next = prev;
         prev = node;
         node = next;
      }

      return node;

   }

}
