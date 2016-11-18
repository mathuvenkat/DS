package datastructures;
public class HeapTree
{
   //Min heap tree :

   int[] arr;
   int size = -1; // returns curr ptr

   public HeapTree(int size)
   {
      arr = new int[size];

   }

   public void insert(int val)
   {
      if (isFull())
         throw new RuntimeException( "Array size is full,cannot insert more elements");

      size++;
      arr[size] = val;
      if (size > 0)
         bubbleup();
   }

   public void remove()
   {
      if (isEmpty())
         throw new RuntimeException("No more elements available on heap array");

      //move last element to first position and bubble down;
      arr[0] = arr[size];
      size--;
      bubbledown();
   }

   public int getMin()
   {
      return arr[0];
   }

   public void traverseInOrder()
   {
      traverse(0);
   }

   private void traverse(int i)
   {
      if (i > size)
         return;

      traverse((2 * i) + 1);
      System.out.println(arr[i]);
      traverse((2 * i) + 2);
   }

   private boolean isEmpty()
   {
      if (size < 0)
         return true;
      else
         return false;
   }

   private boolean isFull()
   {
      if (size == arr.length - 1)
         return true;
      else
         return false;
   }

   private void bubbleup()
   {
      int index = size;

      if (index == 0)
         return;

      while (index != 0 && hasParent(index)) {
         int parentIndex = parentIndex(index);
         if (arr[parentIndex] > arr[index]) {
            swap(parentIndex, index);
         }
         index = parentIndex;
      }
   }

   private void bubbledown()
   {
      int index = 0;
      while (hasChild(index)) {
         int leftindex = (2 * index) + 1;
         int rightindex = (2 * index) + 2;
         int newindex;

         // get the min
         if (arr[leftindex] < arr[rightindex]) {
            newindex = leftindex;
         } else {
            newindex = rightindex;
         }

         if (arr[index] > arr[newindex]) {
            swap(index, newindex);
         }
         index = newindex;
      }
   }

   private void swap(int i,
                     int j)
   {
      int temp = arr[i];
      arr[i] = arr[j];
      arr[j] = temp;
   }

   private boolean hasParent(int index)
   {
      if (parentIndex(index) >= 0)
         return true;
      else
         return false;
   }

   private int parentIndex(int index)
   {
      int parentIndex = (int) Math.floor((index - 1) / 2);
      return parentIndex;
   }

   private boolean hasChild(int index)
   {
      int leftChild = (2 * index) + 1;

      if (leftChild > size)
         return false;
      else
         return true;
   }

}
