package lruCacheProblem;

import java.util.HashMap;
import java.util.Map;

public class lruCache<K, V> implements lruCacheInterface<K, V> {

   public Map<K, Node<K, V>> map = new HashMap<K, Node<K, V>>();
   private Node<K, V> head = null;
   private Node<K, V> tail = null; //LRU
   public int MAX_SIZE = 5;
   public int currentsize = 0;

   @Override
   public void add(K key, V object) {
      if (map.containsKey(key)) {
         Node<K, V> val = map.get(key);
         val.value = object;
         map.put(key, val);
         return;
      }

      Node<K, V> newNode = new Node<K, V>(null, null, key, object);

      if (currentsize == MAX_SIZE) {
         remove();
      }


      addtoHead(newNode);
      currentsize++;

   }

   private void remove() {
      Node<K, V> newTail = tail.prev;
      map.remove(tail.key);
      tail = newTail;
   }

   @Override
   public V get(K key) {
      Node<K, V> ret = map.get(key);
      if (ret == null) {
         //bring node from disk - create some dummy here
         System.out.println("Object not found..loading from disk " + key.toString() );
         ret = new Node<K, V>(null, null, key, null);
      }

      //add to head
      addtoHead(ret);

      return ret.value;
   }


   private void addtoHead(Node<K, V> n) {
      if (head != null && head != n) {

         //adjust n's prev pointer to point to n's next
         Node<K, V> next = n.next;
         Node<K, V> prev = n.prev;

         if (prev != null) {
            prev.next = next;
         }

         //make n the head
         Node<K, V> prevHead = head;
         head = n;
         n.next = prevHead;
      } else {
         head = n;
         tail = n;
      }
   }

}
