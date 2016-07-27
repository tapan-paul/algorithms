/**
 * Created by l071882 on 27/07/2016.
 */
public class FizzBuzz {

    public static void main(String[] args) {

        int [] a = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        boolean isDiv = false;
        for (int i = 0; i < a.length; i++) {
            int value = a[i];
            isDiv = false;
            if ((value/3 >= 0) && (value%3) == 0) {
                System.out.print("Fizz");
                isDiv = true;
            }
            if ((value/5 >= 0)&& (value%5) == 0) {
                System.out.print("Buzz");
                isDiv = true;
            }

            if (!isDiv) {
                System.out.print(a[i]);
            }
            System.out.println();
        }
    }
}
