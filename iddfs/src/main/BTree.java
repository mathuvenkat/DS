package main;
public class BTree
{
   public Node root;

   /**
    * This indicates if there are more levels in the tree to perform DFS on
    * and is set to false on each iteration and is reset to true if there is a
    * further level to be explored that was not explored in this iteration.
    */
   boolean continuelevel = true;

   /** Performs a iterative deepening depth first search to check if the input node
    * exists in the tree.
    * The iterative invocation of DFS stops when there are no more levels in the tree
    * by setting class level variable .
    *
    * Returns true if node is present , false otherwise
    *
    * @param  node  the node to be checked , if it exists in the tree
    * with root root.
    * @see continuelevel
   */
   public boolean isExists(Node node)
   {
      if( this.root == null)
      {
         return false;
      }
      if( node == null)
         throw new RuntimeException("Please pass in a non-null value");

      boolean ret = false;
      int level = 0;
      while (continuelevel) {
         continuelevel = false;
         boolean res = DFS(root, node, level);

         if (res == true) {
            ret = true;
         } else {
            level++;
         }
      }
      continuelevel = true;
      return ret;
   }

   /** Recursive method , which performs DFS till the level passed in to check if
    * toFind exists in the tree.
    * If there are further levels in the tree , which is not being explored currently
    * class variable continuelevel is set to true to indicate there are more
    * levels.
    *
    * Returns TRUE if the node toFind exists in the tree ,
    * returns FALSE otherwise.
    *
    * @param  curr  root of the subtree DFS is being performed on
    * @param  toFind  the node to be checked if it exists in the tree
    * @return  true if the node toFind exists , false otherwise
    *
    */
   private boolean DFS(Node curr,
                      Node toFind,
                      int level)
   {
      if (level < 0) {
         if (curr != null)
            continuelevel = true;

         return false;
      }

      if (curr == null)
         return false;

      if (level == 0) {
         if ( curr.equals(toFind)) {
            return true;
         }
      }

      level = level - 1;
      return (DFS(curr.left, toFind, level) || DFS(curr.right, toFind, level));
   }

}
