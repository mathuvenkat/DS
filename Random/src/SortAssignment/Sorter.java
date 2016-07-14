package SortAssignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * Sorter class with main method defined to sort command line arguments.
 *
 */
public class Sorter {

   public static void main(String args[]) {

      List<String> list = new ArrayList<String>();

      for (String arg : args) {
         //Filter out empty spaces
         if (arg.length() != 0) {
            list.add(arg);
         }
      }

      Collections.sort(list, new CustomComparator());
      System.out.println("Sorted order is: ");
      for (String s : list) {
         System.out.println(s);

      }
   }
}
