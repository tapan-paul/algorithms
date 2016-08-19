package interview;

import java.util.ArrayList;
import java.util.List;

/**
 * @tapan .
 */
public class GetMax {

    public static void main(String[] args) {

        List<Integer> l = new ArrayList<>();
        l.add(1);
        l.add(3);
        l.add(5);
        l.add(6);
        l.add(10);
        l.add(11);
        System.out.println(getMax(l));

    }

    static int getMax(List<Integer> l){

        if (l.size()==1) {
            return l.get(0);
        }

        int curr = l.get(0);

        int max = getMax(l.subList(1,l.size()));

        if (curr > max) {
            max = curr;
        }

        return max;

    }
}
