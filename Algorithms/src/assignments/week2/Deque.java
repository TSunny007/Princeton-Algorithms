package assignments.week2;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class Deque<Item> implements Iterable<Item> {

    private class Node<Item> {
        private Item item;
        private Node previous;
        private Node next;

        Node(Item item, Node prev, Node next) {
            this.item = item;
            this.previous = prev;
            this.next = next;
        }
    }

    private class DequeIterator implements Iterator<Item> {
        private Node<Item> rn = head;
        @Override
        public boolean hasNext() {
            return rn != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item currentItem = rn.item;
            rn = rn.next;
            return currentItem;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private Node<Item> head;
    private Node<Item>  tail;
    private int size;

    // construct an empty deque
    public Deque() {
        size = 0;
        head = null;
        tail = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("null addition to the deque");
        if (size == 0) {
            head = new Node(item, null, null);
            tail = head;
        } else {
            Node newHead = new Node(item, null, head);
            head.previous = newHead;
            head = newHead;
        }
        size++;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("null addition to the deque");
        if (size == 0) {
            head = new Node(item, null, null);
            tail = head;
        } else {
            Node newTail = new Node(item, tail, null);
            tail.next = newTail;
            tail = newTail;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) throw new NoSuchElementException("Nothing in the deque");
        Item first = head.item;
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.previous = null;
        }
        size--;
        return first;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (size == 0) throw new NoSuchElementException("Nothing in the deque");
        Item last = tail.item;
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            tail = tail.previous;
            tail.next = null;
        }
        size--;
        return last;
    }

    // return an iterator over items in order from front to end
    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }


    public static void main(String[] args) {
        Deque<Integer> d = new Deque<>();
        Random random = new Random();
        for (int i = 0; i < 66; i++) {
            int action = random.nextInt(10);
            switch (action) {
                    case 0:
                    case 1:
                    case 2:
                        d.addFirst(i);
                        break;
                    case 3:
                    case 8:
                    case 4:
                        d.addLast(i);
                        break;
                    case 5:
                    case 6:
                        d.removeFirst();
                        break;
                    case 7:
                    case 9:
                        d.addLast(i);
                }
        }
        for (Integer element : d) {
            System.out.print(element + " ");
        }
    }
}
