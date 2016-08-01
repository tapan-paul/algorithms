package interview;

import edu.princeton.cs.algs4.StdOut;

/**
 *
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
 * @tapan .
 */
public class AmazonQuestion {

    public static void main(String[] args) {

        int arr[] = {-2, 1, -3, 4, -1, 2, 1, -5, 4, -1 , 10, -4, -6 ,-6};
        int arr2[] = {-2, -3, -4};
        int arr3[] = {3, 3, 3, -7, 20};

        int maxSum = maxSubArray3(arr3);
        StdOut.println(maxSum);

        maxSum = maxSubArray2(arr3);
        StdOut.print(maxSum);

    }


    static int maxSubArray3(int[] A) {
        int max = A[0];
        int[] sum = new int[A.length];
        sum[0] = A[0];

        for (int i = 1; i < A.length; i++) {
            sum[i] = Math.max(A[i], sum[i - 1] + A[i]);
            max = Math.max(max, sum[i]);
        }

        return max;
    }

    static int maxSubArray2(int[] arr) {

        int maxSoFar = arr[0];
        int sum=arr[0];

        for (int i = 1; i < arr.length ; i++ ) {
            if (arr[i] + sum > arr[i]) {
                sum += arr[i];
            }else {
                sum = arr[i];
            }
            if (sum>maxSoFar) {
                maxSoFar =sum;
            }

        }
        return maxSoFar;
    }






    static int maxSubArray(int[] arr) {

        int max_so_far=0;
        int sum=0;

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] + max_so_far > arr[i]) {
                max_so_far += arr[i];
            } else {
                max_so_far = arr[i];
            }

            if (max_so_far >sum) {
                sum=max_so_far;
            }

        }

        return sum;
    }

}

