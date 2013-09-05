package main;

public class Node
   {
      public Node left;
      public Node right;
      int data;

      public Node(int data)
      {
         this.left = null;
         this.right= null;
         this.data = data;
      }

      @Override
      public boolean equals(Object obj)
      {
         Node nodeObj = (Node) obj;
         return this.data == nodeObj.data;
      }

      @Override
      public int hashCode()
      {
         return this.data * 12345;
      }

   }

