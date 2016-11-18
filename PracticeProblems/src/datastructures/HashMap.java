package datastructures;

public class HashMap<K, V>
{
   int DEFAULT_SIZE = 16;
   Entry<K, V> table[];

   @SuppressWarnings("unchecked")
   public HashMap()
   {
      table = new Entry[DEFAULT_SIZE];
   }

   private int getIndex(K key)
   {
      int hashLoc = key.hashCode() % table.length;
      return hashLoc;
   }

   public void put(K key,
                   V value)
   {
      int hashLoc = getIndex(key);

      if (table[hashLoc] == null) {
         table[hashLoc] = new Entry<K, V>(key, value);
      } else {

         Entry<K, V> entry = table[hashLoc];
         Entry<K, V> firstEntry = entry;

         while (entry != null) {
            if (entry.getKey().equals(key)) {
               entry.setValue(value);
               return;
            } else {
               entry = entry.getNext();
            }
         }

         //key doesnt exist - insert new key
         table[hashLoc] = new Entry<K, V>(key, value, firstEntry);
      }
   }

   public V get(K key)
   {
      int hashLoc = getIndex(key);

      Entry<K, V> entry = table[hashLoc];

      while (entry != null) {
         if (entry.getKey() == key) {
            return entry.getValue();
         } else {
            entry = entry.getNext();
         }
      }
      return null;
   }

}
