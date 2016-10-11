package str;

import java.util.ArrayList;
import java.util.BitSet;

public class PalindromePermutations {

	public static int[] freq = new int[26];
	public static String oddChar = "";

	public static void printpalindromepermutations(String s) {

		String tst = "";
		if (canBePalindrome(s)) {
			for (int i = 0; i < freq.length; i++) {

				int letterFreq = freq[i];

				// the odd char
				if (letterFreq % 2 != 0) {

					int cnt = (letterFreq / 2) + 1;

					for (int j = 0; j < cnt; j++) {
						tst = tst + "" + Character.toString((char) (i + 'a'));
					}
					oddChar = Character.toString((char) (i + 'a'));

				} else {

					for (int j = 0; j < letterFreq / 2; j++) {
						tst = tst + "" + Character.toString((char) (i + 'a'));
					}
				}
			}

			System.out.println("Half word is " + tst);

			ArrayList<String> permutationsHalf = permute(tst);

			for (String perm : permutationsHalf) {
				System.out.println(perm + oddChar + reverse(perm));
			}

		} else {
			System.out.println("Cannot be a plaindrome");
		}

	}

	public static String reverse(String s) {
		StringBuffer rev = new StringBuffer();
		for (int i = s.length() - 1; i >= 0; i--) {
			rev = rev.append(s.charAt(i));
		}
		return rev.toString();
	}

	public static boolean canBePalindrome(String s) {

		for (int i = 0; i < s.length(); i++) {

			int ts = s.charAt(i) - 'a';

			freq[ts]++;

		}

		int odd = 0;
		for (int i = 0; i < freq.length; i++) {
			if (freq[i] % 2 != 0) {
				odd++;
			}
		}

		if (s.length() % 2 != 0) {
			if (odd > 1)
				return false;

		} else {
			if (odd != 0) {
				return false;
			}
		}
		return true;
	}

	public static ArrayList<String> permute(String s) {
		System.out.println(" test " + s.substring(0, 0));
		System.out.println(" test2 " + s.substring(1));
		ArrayList<String> res = new ArrayList<String>();
		permute("", s, res);
		System.out.println("Result of permutations are " + res.size());
		return res;

	}

	public static void permute(String prefix, String s, ArrayList<String> arr) {

		if (s.length() == 0) {
			System.out.println(prefix);
			arr.add(prefix);
		}

		for (int i = 0; i < s.length(); i++) {
			String t = s.substring(0, i);
			String q = s.substring(i + 1);

			permute(prefix + s.charAt(i), t + q, arr);
		}

	}

	public static void main(String args[]) {

		printpalindromepermutations("aabcb");
		permute("abc");
	}

}
