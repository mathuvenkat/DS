public class LinkedList
{
   Node head;
   Node curr;
   int length = 0;
   Node prevToMid;

   private class Node
   {
      char data;
      Node next;

      public Node(char data)
      {
         this.data = data;
         this.next = null;
      }
   }

   private boolean oddsize()
   {
      if (length % 2 == 0)
         return false;
      else
         return true;
   }

   public LinkedList(char data)
   {
      head = new Node(data);
      curr = head;
      length++;
   }

   public void insert(char data)
   {
      curr.next = new Node(data);
      curr = curr.next;
      length++;
   }

   public boolean isPalin()
   {
      Node mid = findMid(head);
      Node reverseHead;
      boolean ret = true;


      if (oddsize()) {
         // ignore middle element for comparison
         reverseHead = reverse(mid.next);
         mid.next = null;
      } else {
         reverseHead = reverse(mid);
      }

      Node temp = head;
      Node originalreverseHead = reverseHead;

      while (reverseHead != null) {
         if (temp.data == reverseHead.data) {
            temp = temp.next;
            reverseHead = reverseHead.next;
         } else {
            ret = false;
            break;
         }
      }

      if (oddsize())
         revertChanges(originalreverseHead, mid);
      else
         revertChanges(originalreverseHead, prevToMid);


      return ret;
   }

   private void revertChanges(Node mid,
                              Node prev)
   {
      Node originalmid;
      originalmid = reverse(mid);
      prev.next = originalmid;
      print(head);
   }

   public void print(Node node)
   {
      System.out.println();
      Node temp = node;
      while (temp != null) {
         System.out.print(temp.data + "->" + " ");
         temp = temp.next;
      }
   }

   public Node findMid(Node node)
   {
      Node slow = node;
      Node fast = node;
      prevToMid = null;

      while (fast != null && fast.next != null) {
         prevToMid = slow;
         slow = slow.next;
         fast = fast.next.next;
      }

      return slow;
   }

   public Node reverse(Node node)
   {
      Node prev = null;
      while (node != null) {
         Node next = node.next;
         node.next = prev;
         prev = node;
         node = next;
      }

      return prev;
   }

}
