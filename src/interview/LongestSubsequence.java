import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

/**
 * Given an array of integers, find the length of the longest sub-sequence such that elements in the subsequence are consecutive integers, the consecutive numbers can be in any order.

 Examples

 Input: arr[] = {1, 9, 3, 10, 4, 20, 2};
 Output: 4
 The subsequence 1, 3, 4, 2 is the longest subsequence
 of consecutive elements

 Input: arr[] = {36, 41, 56, 35, 44, 33, 34, 92, 43, 32, 42}
 Output: 5
 The subsequence 36, 35, 33, 34, 32 is the longest subsequence
 of consecutive elements.
 * Created by l071882 on 2/08/2016.
 */
public class LongestSubsequence {

    public static void main(String[] args) {

        int[] a = {1, 9, 3, 10, 4, 20, 2};
        int n = longestSubSequence(a);
        int[] a1 = {36, 41, 56, 35, 44, 33, 34, 92, 43, 32, 42};
        n = longestSubSequence(a1);
        StdOut.print(n);
    }

    static int longestSubSequence(int[] a) {

        int count = 1;
        int max = 0;

        Arrays.sort(a);
        int value = a[0];

        for (int i = 1; i < a.length; i++) {
            if (a[i] == value+1) {
                count++;
            }else {
                count = 1;
            }
            value = a[i];
            if (count>max) {
                max = count;
            }

        }

        return max;
    }

}
