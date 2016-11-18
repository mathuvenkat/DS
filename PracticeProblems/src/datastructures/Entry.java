package datastructures;

public class Entry<K,V>
{
   K key;
   V value;
   Entry<K,V> next;

   public Entry(K k, V v)
   {
     this.key = k;
     this.value = v;
   }

   public Entry(K k, V v , Entry<K,V> next)
   {
     this.key = k;
     this.value = v;
     this.next = next;
   }

   public K getKey()
   {
     return this.key;
   }

   public V getValue()
   {
      return this.value;
   }

   public void setValue( V val)
   {
      this.value = val;
   }

   public void setNext(Entry<K,V> next)
   {
      this.next = next;
   }

   public Entry<K,V> getNext( )
   {
      return this.next;
   }

}
