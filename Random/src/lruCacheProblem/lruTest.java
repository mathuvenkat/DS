package lruCacheProblem;

import org.junit.Test;

public class lruTest {

   @Test
   public void test() {
      lruCacheInterface<Integer, String> lru = new lruCache<Integer, String>();
      lru.add(1, "Three");
      lru.add(2, "two");
      lru.add(3, "two");
      lru.add(4, "two");
      lru.add(5, "two");
      lru.add(6, "two");

      lru.get(2);

      lru.add(7, "two");


      lru.get(3);
      lru.add(15,"fifteen");
      lru.get(5);

   }

}
