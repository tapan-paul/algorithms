import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @tapan .
 */
public class Deque<Item> implements Iterable<Item> {

    private class Node<Item> {


        Node(Item item) {
            this.value = item;
        }

        Node<Item> next;
        Node<Item> prev;
        Item value;
    }

    private Node<Item> first;
    private Node<Item> last;
    private int size;

    // construct an empty deque
    public Deque()  {
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size(){
        return size;
    }

    private Node<Item> newNode(Item value) {

        Node<Item> node = new Node(value);
        return node;
    }

    // add the item to the front
    public void addFirst(Item item){

        if (item == null) {
            throw new NullPointerException();
        }

        Node<Item> newNode = newNode(item);
        size++;

        if (isEmpty()) {
            first = newNode;
            last = first;
            return;
        }

        Node<Item> oldFirst = first;
        first = newNode;
        first.next = oldFirst;
        oldFirst.prev = first;

    }

    // add the item to the end
    public void addLast(Item item){

        if (item == null) {
            throw new NullPointerException();
        }

        Node<Item> newNode = newNode(item);
        size++;
        if (isEmpty()) {
            first = newNode;
            last = first;
            return;
        }

        Node<Item> oldLast = last;
        last = newNode;
        oldLast.next = last;
        last.prev = oldLast;


    }

    // remove and return the item from the front
    public Item removeFirst(){

        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item item = first.value;
        first = first.next;
        if (size()==1) {
            first = null;
            last = null;
        }else {
            first.prev = null;
        }
        size--;
        return item;

    }

    // remove and return the item from the end
    public Item removeLast(){

        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item item = last.value;
        last = last.prev;
        if (size()==1) {
            first = null;
            last = null;
        }else {
            last.next = null;
        }
        size--;
        return item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator(){

        return new DequeIterator();
    }

    // unit testing
    public static void main(String[] args){

    }

    private class DequeIterator implements Iterator<Item> {

        private Node<Item> curr = first;

        @Override
        public boolean hasNext() {
            return curr!=null;
        }

        @Override
        public Item next() {

            if (curr==null) {
                throw new NoSuchElementException();
            }

            Item item = curr.value;
            curr = curr.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}