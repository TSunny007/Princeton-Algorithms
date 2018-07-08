package assignments.week2;


import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] randomQueue;
    private int size;
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

    private void halveQueue() {
        if (size == randomQueue.length/4) {
            Item[] halved = (Item[]) new Object[randomQueue.length / 2];
            for (int i =0; i < randomQueue.length; i++) {
                halved[i] = randomQueue[i];
            }
            randomQueue = halved;
        }
    }

    // remove and return a random item
    public Item dequeue() {
        halveQueue();
        if (size == 0) throw new NoSuchElementException();
        int removeIndex = StdRandom.uniform(size);
        Item returnItem = randomQueue[removeIndex];
        if (removeIndex != size - 1) {
            randomQueue[removeIndex] = randomQueue[size - 1];
        }
        randomQueue[size - 1] = null;
        size --;
        return returnItem;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        return randomQueue[StdRandom.uniform(size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            int currentIndex = 0;
            int[] indecies = StdRandom.permutation(size);
            @Override
            public boolean hasNext() {
                return currentIndex != size;
            }

            @Override
            public Item next() {
                return randomQueue[indecies[currentIndex++]];
            }
        };
    }
    // unit testing (optional)
    public static void main(String[] args)  {
    }
}
