package interview;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 *
 * Given two strings,  and , determine if they share a common substring.

 Input Format

 The first line contains a single integer, , denoting the number of  pairs you must check.
 Each pair is defined over two lines:

 The first line contains string .
 The second line contains string .
 Constraints

 and  consist of lowercase English letters only.
 Output Format

 For each  pair of strings, print YES on a new line if the two strings share a common substring; if no such common substring exists, print NO on a new line.


 * @tapan .
 */
public class TwoStrings {

    public static void main(String[] args) {

        String[] words = {"hello", "world", "hi", "world"};
        int N = 2;

        for (int i = 0; i< words.length && i+1< words.length; i+=N) {
            String s1 = words[i];
            String s2 = words[i+1];
            char[] s1Chars = s1.toCharArray();
            char[] s2Chars = s2.toCharArray();
            Arrays.sort(s2Chars);
            boolean isFound = false;
            for (int j = 0; j < s1Chars.length; j++) {
                int result = Arrays.binarySearch(s2Chars, s1Chars[j]);
                if (result >= 0) {
                    StdOut.println("YES");
                    isFound = true;
                    break;
                }
            }
            if (!isFound){
                StdOut.println("NO");
            }
        }

    }
}
