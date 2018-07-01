package assignments.week2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] randomQueue;
    private int size;
    private Random random = new Random();
    // construct an empty randomized queue
    public RandomizedQueue()       {
        randomQueue = (Item[])new Object[32];
    }
    // is the randomized queue empty?
    public boolean isEmpty()    {
        return size == 0;
    }
    // return the number of items on the randomized queue
    public int size()    {
        return size;
    }

    // add the item
    public void enqueue(Item item)  {
        doubleQueue();
        randomQueue[size++] = item;
    }

    private void doubleQueue() {
        if (size == randomQueue.length) {
            Item[] doubled = (Item[]) new Object[randomQueue.length * 2];
            for (int i =0; i < randomQueue.length; i++) {
                doubled[i] = randomQueue[i];
            }
            randomQueue = doubled;
        }
    }

    // remove and return a random item
    public Item dequeue() {
        int removeIndex = random.nextInt(size);
        Item returnItem = randomQueue[removeIndex];
        if (removeIndex != size -1) {
            randomQueue[removeIndex] = randomQueue[size -1];
        }
        randomQueue[size -1] = null;
        size --;
        return returnItem;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        return randomQueue[random.nextInt(size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        List<Integer> indexOrder = IntStream.range(0, size).boxed().collect(Collectors.toList());
        Collections.shuffle(indexOrder);

        return new Iterator<Item>() {
            @Override
            public boolean hasNext() {
                return !indexOrder.isEmpty();
            }

            @Override
            public Item next() {
                return randomQueue[indexOrder.remove(indexOrder.size()-1)];
            }
        };
    }
    // unit testing (optional)
    public static void main(String[] args)  {

    }
}
