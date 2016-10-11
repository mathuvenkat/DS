package SortAssignment;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * This test suite has several combinations of inputs that are sorted using
 * CustomComparator.
 * All inputs given in the problem statement have been added as test cases
 * for clarity
 *
 */
public class SortTestSuite {

   @Test
   public void testSortFileNames() {
      String[] arr =
            { "t9.txt", "t5.txt", "t10.txt", "t0.txt", "t19.txt", "t.txt",
                  "t3.txt", "t007.txt" };
      List<String> list = Arrays.asList(arr);
      Collections.sort(list, new CustomComparator());

      Assert.assertEquals(list.get(0), "t.txt");
      Assert.assertEquals(list.get(1), "t0.txt");
      Assert.assertEquals(list.get(2), "t3.txt");
      Assert.assertEquals(list.get(3), "t5.txt");
      Assert.assertEquals(list.get(4), "t007.txt");
      Assert.assertEquals(list.get(5), "t9.txt");
      Assert.assertEquals(list.get(6), "t10.txt");
      Assert.assertEquals(list.get(7), "t19.txt");
   }

   @Test
   public void testSortIPAddresses() {
      String[] arr =
            { "127.0.0.5", "127.0.0.255", "127.0.0.10", "127.0.0.0",
                  "192.168.0.1", "10.1.2.2" };
      List<String> list = Arrays.asList(arr);
      Collections.sort(list, new CustomComparator());

      Assert.assertEquals(list.get(0), "10.1.2.2");
      Assert.assertEquals(list.get(1), "127.0.0.0");
      Assert.assertEquals(list.get(2), "127.0.0.5");
      Assert.assertEquals(list.get(3), "127.0.0.10");
      Assert.assertEquals(list.get(4), "127.0.0.255");
      Assert.assertEquals(list.get(5), "192.168.0.1");
   }

   @Test
   public void testSortFileNamesWithSpecialCharacters() {
      String[] arr =
            { "7-2-25_vacation2.jpg", "7-2-25_vacation10.jpg",
                  "12-1-10_vacation3.jpg", "12-1-10_vacation3.gif" };
      List<String> list = Arrays.asList(arr);
      Collections.sort(list, new CustomComparator());

      Assert.assertEquals(list.get(0), "7-2-25_vacation2.jpg");
      Assert.assertEquals(list.get(1), "7-2-25_vacation10.jpg");
      Assert.assertEquals(list.get(2), "12-1-10_vacation3.gif");
      Assert.assertEquals(list.get(3), "12-1-10_vacation3.jpg");
   }

   @Test
   public void testAlphanumericAndCase() {
      String[] arr =
            { "foo", "f00", "Foo", "bar", "baz", "qux", "0", "1", "20", "3",
                  "40" };
      List<String> list = Arrays.asList(arr);
      Collections.sort(list, new CustomComparator());

      Assert.assertEquals(list.get(0), "0");
      Assert.assertEquals(list.get(1), "1");
      Assert.assertEquals(list.get(2), "3");
      Assert.assertEquals(list.get(3), "20");
      Assert.assertEquals(list.get(4), "40");
      Assert.assertEquals(list.get(5), "Foo");
      Assert.assertEquals(list.get(6), "bar");
      Assert.assertEquals(list.get(7), "baz");
      Assert.assertEquals(list.get(8), "f00");
      Assert.assertEquals(list.get(9), "foo");
      Assert.assertEquals(list.get(10), "qux");
   }

   @Test
   public void testAlphanumericSimple() {
      String[] arr = { "a1a1", "a1b1", "a1a2", "a1b2", "a2a1" };
      List<String> list = Arrays.asList(arr);
      Collections.sort(list, new CustomComparator());

      Assert.assertEquals(list.get(0), "a1a1");
      Assert.assertEquals(list.get(1), "a1a2");
      Assert.assertEquals(list.get(2), "a1b1");
      Assert.assertEquals(list.get(3), "a1b2");
      Assert.assertEquals(list.get(4), "a2a1");

   }

   @Test
   public void testAllCombinations() {
      String[] arr =
            { "t6.txt", "t0007.txt", "ta", "2013-07-03", "t.txt", ".txt",
                  "127.0.0.1", "t9", "a" };
      List<String> list = Arrays.asList(arr);
      Collections.sort(list, new CustomComparator());

      Assert.assertEquals(list.get(0), ".txt");
      Assert.assertEquals(list.get(1), "127.0.0.1");
      Assert.assertEquals(list.get(2), "2013-07-03");
      Assert.assertEquals(list.get(3), "a");
      Assert.assertEquals(list.get(4), "t.txt");
      Assert.assertEquals(list.get(5), "t6.txt");
      Assert.assertEquals(list.get(6), "t0007.txt");
      Assert.assertEquals(list.get(7), "t9");
      Assert.assertEquals(list.get(8), "ta");
   }

   @Test
   public void testNumbers() {
      String[] arr = { "1", "99", "5", "23", "47", "64", "32", "7", "10" };

      List<String> list = Arrays.asList(arr);
      Collections.sort(list, new CustomComparator());

      Assert.assertEquals(list.get(0), "1");
      Assert.assertEquals(list.get(1), "5");
      Assert.assertEquals(list.get(2), "7");
      Assert.assertEquals(list.get(3), "10");
      Assert.assertEquals(list.get(4), "23");
      Assert.assertEquals(list.get(5), "32");
      Assert.assertEquals(list.get(6), "47");
      Assert.assertEquals(list.get(7), "64");
      Assert.assertEquals(list.get(8), "99");
   }
}
