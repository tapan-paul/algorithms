import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by l071882 on 3/08/2016.
 */
public class HasArrayTwoCandidates {

    public static void main(String[] args) {

        Integer a[] = {1, 4, 45, 96, 10, -8};
        int sum = 49;

        StdOut.println(hasArrayTwoCandidates(a, sum));

        StdOut.print(hasArrayTwoCandidates2(a, sum));
    }

    static boolean hasArrayTwoCandidates2(Integer a[], int sum) {

        Arrays.sort(a);

        int value = sum-a[0];
        if (a[0] < 0) {
            value *= -1;
        }
        int j=0;
        for (int i = 0; i < a.length; ) {

            if (a[j] > value) {
                i++;
                j=i;
                if (j>=a.length) {
                    break;
                }
                value = sum - a[j];
                if (a[j] < 0) {
                    value *= -1;
                }
            }else {
                j++;
            }
            if (a[j] == value) {
                return true;
            }

        }


        return false;
    }

    static boolean hasArrayTwoCandidates(Integer a[], int sum) {

        Arrays.sort(a);
        List tmp = Arrays.asList(a);

        for (int i = 0; i < a.length; i++) {
            int value = a[i];
            if (value < 0) {
                value *=-1;
            }
            int diff = sum - value;
            int i1 = Collections.binarySearch(tmp, diff);
            if (i1 >0) {
                return true;
            }

        }


        return false;
    }
}
