package interview;

import edu.princeton.cs.algs4.StdOut;

/**
 *
 * Given an array of size n, rotate it by d elements.
 * @tapan .
 */
public class RotateArray {


    public static void main(String[] args) {

        int a[] = {1,2,3,4,5};
        int n = 5;
        int d = 2;
        int a1[] = {2, 4, 6, 8 ,10, 12, 14, 16, 18, 20};
        n = 10;
        d = 3;
        int []b =rotate(a1, n, d);
        for (int i = 0; i < b.length; i++) {
            StdOut.print(b[i] + " ");
        }

    }

    static int[] rotate(int a[], int n, int d) {

        int diff = n - d;
        int b[] = new int[a.length];

        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }

        for (int i = 0; i < a.length; i++) {

            if (i < d) {
                b[i] = a[i + diff];
                b[i + diff] = a[i];
            }
            else {
                b[i-d] = a[i];
            }

        }
        return b;

    }

}
