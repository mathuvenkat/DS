package ds;


public class PriorityQueueFindKLargestElements {

   private int[] queue;
   private int maxSize;

   private int INTEGER_MAX = 2147483647;

   //position where element can be inserted
   private int currentIndex = 0;

   public int[] getLargest() {
      return queue;
   }


   public PriorityQueueFindKLargestElements(int size) {
      this.maxSize = size;
      queue = new int[size];
   }

   public void add(int element) {

      if (currentIndex < maxSize) {
         queue[currentIndex] = element;
         System.out.println("Added element " + element + "at " + currentIndex);
         bubbleUp();
         currentIndex++;
      } else {
         if (element > peek()) {
            System.out.println("Max size reached - dequeue element");
            remove();
            queue[currentIndex] = element;
            bubbleUp();
            currentIndex++;
         } else {
            System.out.println("New value lesser than root - ignore");
         }
      }
   }

   public void remove() {
      System.out.println("Removing : " + queue[0]);
      queue[0] = queue[currentIndex - 1];
      queue[currentIndex - 1] = INTEGER_MAX;
      bubbleDown();
      currentIndex--;
   }

   public int peek() {
      return queue[0];
   }

   private void swap(int i, int j) {
      int tmp = queue[i];
      queue[i] = queue[j];
      queue[j] = tmp;
   }

   private void bubbleUp() {

      int childIndex = currentIndex;
      int parentIndex = getParentIndex(childIndex);

      while (parentIndex >= 0 && childIndex != 0) {
         if (queue[parentIndex] > queue[childIndex]) {
            swap(parentIndex, childIndex);
         }
         childIndex = parentIndex;
         parentIndex = getParentIndex(childIndex);
      }
   }

   private int getParentIndex(int index) {
      int parentIndex = (index - 1) / 2;
      return parentIndex;
   }


   private int getMiminumChildIndex(int index) {
      int leftChildIndex = (2 * index) + 1;

      if (leftChildIndex > maxSize - 1) {
         return index;
      }

      int rightChildIndex = (2 * index) + 2;

      if (queue[index] < queue[leftChildIndex]
            && queue[index] < queue[rightChildIndex])
         return index;


      if (queue[leftChildIndex] < queue[rightChildIndex]) {
         return leftChildIndex;
      } else {
         return rightChildIndex;
      }
   }

   private void bubbleDown() {

      int parentIndex = 0;
      int childIndex = getMiminumChildIndex(parentIndex);

      if (childIndex == parentIndex) {
         return;
      }

      while (childIndex < maxSize && (parentIndex != childIndex)) {
         swap(parentIndex, childIndex);
         parentIndex = childIndex;
         childIndex = getMiminumChildIndex(parentIndex);
      }
   }

   public static void main(String args[]) {
      PriorityQueueFindKLargestElements ins =
            new PriorityQueueFindKLargestElements(5);

      int arr[] = { 5, 11, 3, 12, 2, 1, 13, 16, 9, 8, 7, 4, 3, 20 };

      for (int e : arr) {
         ins.add(e);
      }

      int[] res = ins.getLargest();
      System.out.println("Completed processing: " + res.length);
      for (int i = 0; i < res.length; i++) {
         System.out.println("Res: " + res[i]);
      }

   }
}
