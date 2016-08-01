package interview;

import edu.princeton.cs.algs4.StdOut;

/**
 * @tapan .
 */
public class PermuationString {

    public static void main(String[] args) {

        String s1 = "ABC";

        // permutation is order matters, ie here it would be n!
        // eg if "ABC", then 6 output


        permutation("", s1);

    }


    private static void permutation(String prefix,String s1) {

        if (s1.length() == 0) {
            StdOut.println(prefix);
        }else {
            for (int i = 0; i < s1.length(); i++) {
                permutation(String.valueOf(prefix  + s1.charAt(i)) , s1.substring(0, i) + s1.substring(i+1));
            }
        }
    }


}
