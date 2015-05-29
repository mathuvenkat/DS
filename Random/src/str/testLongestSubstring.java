package str;

import org.junit.Assert;
import org.testng.annotations.Test;


public class testLongestSubstring {

   @Test()
   public void LongestSubstringTest() throws Exception {
      String res;
      Solution sol = new Solution();
      res = sol.findlongestSubstring("abc");
      Assert.assertTrue(res.length() == 3);


      res = sol.findlongestSubstring("abcda");
      Assert.assertTrue(res.length() == 4);

      res = sol.findlongestSubstring("king of chinaloyd");
      Assert.assertTrue(res.length() == 11);

      res = sol.findlongestSubstring("aabbc");
      Assert.assertTrue(res.length() == 2);

      res = sol.findlongestSubstring("abaaabc");
      Assert.assertTrue(res.length() == 3);

      res = sol.findlongestSubstring("abcdae");
      Assert.assertTrue(res.length() == 5);

      res = sol.findlongestSubstring("aabb");
      Assert.assertTrue(res.length() == 2);

      res = sol.findlongestSubstring("atabb");
      Assert.assertTrue(res.length() == 3);

      res = sol.findlongestSubstring("aaa");
      Assert.assertTrue(res.length() == 1);
   }

}
