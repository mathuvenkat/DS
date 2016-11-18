package lruCacheProblem;

public class Node<K, V> {
   Node<K, V> prev;
   Node<K, V> next;

   K key;
   V value;

   public Node(Node<K, V> prev, Node<K, V> next, K key, V value) {
      super();
      this.prev = prev;
      this.next = next;
      this.key = key;
      this.value = value;
   }

}
