package lruCacheProblem;

public interface lruCacheInterface<K, V> {

   public void add(K key, V object);

   public V get(K key);


}
