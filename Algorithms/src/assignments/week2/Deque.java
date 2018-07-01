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
            for (int k = head, i = 0; k != (tail + 1)%deque.length; k =  (k == deque.length - 1) ? 0: k+1) {
                doubled[i++] = deque[k];
            }
            head = 0;
            tail = deque.length - 2;
            deque = doubled;
        }
    }

    private void checkHalveQueue() {
        if (deque.length > 32 && size == deque.length / 4){
            Item[] halved = (Item[]) new Object[deque.length / 2];
            for (int k = head, i = 0; k != (tail + 1)%deque.length; k =  (k == deque.length - 1) ? 0: k+1) {
                halved[i++] = deque[k];
            }
            head = 0;
            tail = deque.length / 4 - 1;
            deque = halved;
        }
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("null addition to the deque");
        if (size == 0) {
            deque[0] = item;
        } else {
            checkDoubleQueue();
            head = (head == 0) ? deque.length - 1 : head - 1;
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
            tail = (tail == deque.length - 1) ? 0 : tail + 1;
            deque[tail] = item;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) throw new NoSuchElementException("Nothing in the deque");
        checkHalveQueue();
        Item dequeue = deque[head];
        deque[head] = null;
        head = (head == deque.length - 1) ? 0 : head + 1;
        size--;
        return dequeue;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (size == 0) throw new NoSuchElementException("Nothing in the deque");
        checkHalveQueue();
        Item pop = deque[tail];
        deque[tail] = null;
        tail = (tail == 0) ? deque.length - 1 : tail - 1;
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
                return currentIndex != (tail + 1) % deque.length;
            }

            @Override
            public Item next() {
                if (!hasNext()) throw new NoSuchElementException("Nothing in the deque");
                if (deque[currentIndex] ==  null) {
                    System.out.println("Uh oh!");
                }
                Item returnItem =  deque[currentIndex];
                currentIndex = currentIndex == deque.length-1? 0: currentIndex+1;
                return returnItem;
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
        for (int i = 0; i < 66; i++) {
            int action = random.nextInt(10);
            if (i < 33)
                switch (action) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 8:
                        d.addFirst(i);
                        break;
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 9:
                        d.addLast(i);
                }
            else {
                switch (action) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 8:
                        d.removeFirst();
                        break;
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 9:
                        d.removeLast();
                }
            }

//            switch (action) {
//                    case 0:
//                    case 1:
//                    case 2:
//                        d.addFirst(i);
//                        break;
//                    case 3:
//                    case 8:
//                    case 4:
//                        d.addLast(i);
//                        break;
//                    case 5:
//                    case 6:
//                        d.removeFirst();
//                        break;
//                    case 7:
//                    case 9:
//                        d.addLast(i);
//                }
        }
        for (Integer element : d) {
            System.out.print(element + " ");
        }
    }
}
