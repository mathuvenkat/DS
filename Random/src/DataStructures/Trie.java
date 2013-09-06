package DataStructures;
import java.util.HashMap;
import java.util.Map;

public class Trie
{
   TrieNode root;

   private class TrieNode
   {
      char data;
      Map<Character, TrieNode> children;
      boolean isTerminal;
      String phoneNum = "not defined";

      public TrieNode(char data)
      {
         this.data = data;
         this.children = new HashMap<Character, TrieNode>();

      }

      public void setTerminal(boolean value)
      {
         this.isTerminal = value;
      }

      public Map<Character, TrieNode> getChildren()
      {
         return this.children;
      }

      public void setPhoneNum(String num)
      {
         this.phoneNum = num;
      }
   }

   public Trie()
   {
      root = new TrieNode('#');
   }

   public void insert(String name,
                      String... phone)
   {
      int len = name.length();
      TrieNode currentNode = root;

      for (int i = 0; i < len; i++) {
         char current = name.charAt(i);
         if (currentNode.getChildren().get(current) == null) {
            TrieNode node = new TrieNode(current);
            if (i == len - 1) {
               node.setTerminal(true);
               if (phone != null && phone.length > 0) {
                  node.setPhoneNum(phone[0]);
               }
            }
            currentNode.getChildren().put(current, node);
         }

         currentNode = currentNode.getChildren().get(current);

      }
   }

   public boolean containsKey(String key)
   {
      int len = key.length();
      TrieNode currentNode = root;

      for (int i = 0; i < len; i++) {
         char current = key.charAt(i);
         currentNode = currentNode.getChildren().get(current);

         if (currentNode == null) {
            return false;
         }

         if (i == len - 1) {
            if (currentNode.isTerminal) {
               System.out.println("Phone number of " + key + " is "
                        + currentNode.phoneNum);
               return true;
            } else {
               return false;
            }
         }
      }

      return false;
   }

   private TrieNode search(String key)
   {
      int len = key.length();
      TrieNode currentNode = root;

      for (int i = 0; i < len; i++) {
         char current = key.charAt(i);

         currentNode = currentNode.getChildren().get(current);

         if (currentNode == null) {
            System.out.println("Substring not available");
            return null;
         }
      }

      return currentNode;
   }

   private void traverse(String key,
                         TrieNode node)
   {
      key = key + node.data;
      if (node.isTerminal) {
         System.out.println(key);
         return;
      }

      for (TrieNode child : node.getChildren().values()) {
         traverse(key, child);
      }
   }

   public void substrings(String key)
   {
      TrieNode node = search(key);

      traverse(key.substring(0, key.length() - 1), node);
   }

}
