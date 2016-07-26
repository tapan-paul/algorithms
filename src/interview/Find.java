package interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * Implement a method 'find' that will find the starting index
 * (zero based) where the second list occurs as a sub-list in the first list.
 * It should return -1 if the sub-list cannot be found. Arguments are always given, not empty.
 * Sample Input 1 list1 = (1, 2, 3) list2 = (2, 3) Sample Output 1
 * 1 Explanation As second list (2, 3) is sub-list in first list (1, 2, 3) at index 1
 * Sample Input 2 list1 = (1, 2, 3) list2 = (3, 2) Sample Output 2 -1
 *
 * @tapan .
 */
public class Find {

    public static void main(String[] args) {

        List<Integer> l1 = new ArrayList<>();
        l1.add(1);
        l1.add(2);
        l1.add(3);

        List<Integer> l2 = new ArrayList<>();
        l2.add(1);
        l2.add(2);

        System.out.println(Collections.indexOfSubList(l1, l2));
        System.out.println(find(l1,l2));
    }

    static int find(List<Integer> l1, List<Integer> l2) {

        Integer l2_first = l2.get(0);

        for (int i = 0; i < l1.size(); i++) {
            Integer i1 = l1.get(i);
            if (i1.equals(l2_first)) {
                int k =1;int j;
                for ( j= i+1; j < l1.size() && k < l2.size(); j++) {
                    if (!l1.get(j).equals(l2.get(k))) {
                        return -1;
                    }
                    k++;
                }
                if (k==l2.size()) {
                    return i;
                }
                return -1;
            }

        }

        return -1;
    }

}
