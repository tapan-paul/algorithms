import java.util.List;

/**
 * Created by l071882 on 2/08/2016.
 */
public class SwapKthElement {

    public static void main(String[] args) {


    }

    static void swapKthElement(ListNode<Integer> l1, int k) {

        int counter = 0;
        ListNode<Integer> item1 = null;
        ListNode<Integer> first = l1;

        while(first != null) {
            if (counter == k) {
                item1 = first;
            }
            first = first.next;
            counter++;
        }

        if (k>counter) {
            return;
        }

        // If x (kth node from start) and y(kth node from end)
        // are same
        if (2*k - 1 == counter)
            return;

        int kthLast = counter - k;

        int startK = k;
        ListNode<Integer> item2 = item1;
        while(item2 != null) {
            if (startK == kthLast) {
                break;
            }
            startK++;
            item2 = item2.next;
        }

        Integer tmp = item1.value;
        item1.value = item2.value;
        item2.value = tmp;
    }

    private class ListNode<Item> {

        ListNode next;
        Item value;
    }
}
