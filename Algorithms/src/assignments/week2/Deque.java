package assignments.week2;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class Deque<Item> implements Iterable<Item> {
    private Item[] deque;
    private int head;
    private int tail;
    private int size;

    // construct an empty deque
    public Deque() {
        deque = (Item[]) new Object[32];
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size > 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    private void checkDoubleQueue() {
        if (size == deque.length - 1) {
            Item[] doubled = (Item[]) new Object[deque.length * 2];
            for (int k = 0; (k+head)%deque.length != tail+1; k++) {
                doubled[k] = deque[(k+head)%deque.length];
            }
            head = 0;
            tail = deque.length-3;
            deque = doubled;
        }
    }

    private void checkHalveQueue() {
        if (size == deque.length / 4) {
            Item[] halved = (Item[]) new Object[deque.length / 2];
            for (int i = head, k =0; i != tail; i=(i+1)%deque.length) {
                halved[k++] = deque[i];
            }
            deque = halved;
            head = 0;
            tail = deque.length / 4 - 1;
        }
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("null addition to the deque");
        if (size == 0) {
            deque[0] = item;
        } else {
            checkDoubleQueue();
            head = (head == 0) ? deque.length -1: head - 1;
            deque[head] = item;
        }
            size++;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("null addition to the deque");
        if (size == 0) {
            deque[0] = item;
        } else {
            checkDoubleQueue();
            tail = (tail == deque.length -1) ? 0: tail + 1;
            deque[tail] = item;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) throw new NoSuchElementException("Nothing in the deque");
        //checkHalveQueue();
        Item dequeue = deque[head];
        deque[head] = null;
        head = (head == deque.length -1) ? 0: head + 1;
        size--;
        return dequeue;
    }


    // remove and return the item from the end
    public Item removeLast() {
        if (size == 0) throw new NoSuchElementException("Nothing in the deque");
        //checkHalveQueue();
        Item pop = deque[tail];
        deque[tail] = null;
        tail = (tail == 0) ? deque.length -1: tail - 1;
        size--;
        return pop;
    }

    // return an iterator over items in order from front to end
    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {

            private int currentIndex = head;

            @Override
            public boolean hasNext() {
                return currentIndex %deque.length!= tail+1;
            }

            @Override
            public Item next() {
                if (!hasNext()) throw new NoSuchElementException("Nothing in the deque");
                return deque[currentIndex++%deque.length];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }


    public static void main(String[] args) {
        Deque<Integer> d = new Deque<>();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int action = random.nextInt(10);
            switch(action) {
                case 0:
                case 1:
                case 2:
                case 3:
                    d.addFirst(i);
                    break;
                case 4:
                case 5:
                case 6:
                case 7:
                    d.addLast(i);
                    break;
                case 8:
                    d.removeFirst();
                    break;
                case 9:
                    d.removeLast();
                    break;
            }

        }
        for (Integer element : d) {
            System.out.print(element+ " ");
        }
    }
}
