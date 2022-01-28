package day04;

import java.util.*;

import static java.util.Comparator.comparing;

public class Sorted {

    public static final int MAX_ITEMS = 5_000_000;
    public static final int MAX_RANDOM_RANGE = 100;

    Random rnd = new Random();
    List<Item> items;
    List<Item> items1;
    List<Item> items2;
    List<Item> items3;

    public Sorted() {
        items = getItems();
        items1 = List.copyOf(items);
        items2 = List.copyOf(items);
        items3 = List.copyOf(items);
        execute();
    }

    private void execute() {
//        sortDummy();
        long time1 = sortOne();
        long time2 = sortTwo();
        long time3 = sortThree();
        System.out.println();
        System.out.printf("thencomparing:     %10f\n", time1 / 1_000_000_000.0);
        System.out.printf("reverse prio sort: %10f\n", time2 / 1_000_000_000.0);
        System.out.printf("comparator:        %10f\n", time3 / 1_000_000_000.0);
    }

    private long sortOne() {
        System.out.println("\nthencomparing:");
        printFirstThirty(items1);
        long startTime = System.nanoTime();
        List<Item> result = items1.stream()
                .sorted(comparing(Item::getA).thenComparing(Item::getB).thenComparing(Item::getC))
                .toList();
        long stopTime = System.nanoTime();
        System.out.println("time: " + (stopTime - startTime));
        printFirstThirty(result);
        return stopTime - startTime;
    }

    private long sortTwo() {
        System.out.println("\nreverse prio sort:");
        printFirstThirty(items2);
        long startTime = System.nanoTime();
        List<Item> result = items2.stream()
                .sorted(comparing(Item::getC))
                .sorted(comparing(Item::getB))
                .sorted(comparing(Item::getA))
                .toList();
        long stopTime = System.nanoTime();
        System.out.println("time: " + (stopTime - startTime));
        printFirstThirty(result);
        return stopTime - startTime;

    }

    private long sortThree() {
        System.out.println("\ncomparator:");
        printFirstThirty(items3);
        long startTime = System.nanoTime();
        List<Item> result = items3.stream()
                .sorted(new Comparator<Item>() {
                    @Override
                    public int compare(Item o1, Item o2) {
                        int result = o1.getA() - o2.getA();
                        if (result == 0) {
                            result = o1.getB() - o2.getB();

                            if (result == 0) {
                                result = o1.getC() - o2.getC();
                            }
                        }
                        return result;
                    }
                })
                .toList();
        long stopTime = System.nanoTime();
        System.out.println("time: " + (stopTime - startTime));
        printFirstThirty(result);
        return stopTime - startTime;

    }

    private List<Item> getItems() {
        List<Item> result = new ArrayList<>();
        for (int i = 0; i < MAX_ITEMS; i++) {
            result.add(i, new Item(rnd.nextInt(MAX_RANDOM_RANGE),
                    rnd.nextInt(MAX_RANDOM_RANGE),
                    rnd.nextInt(MAX_RANDOM_RANGE)));
        }
        return result;
    }

    private void printFirstThirty(List<Item> items) {
        for (int i = 0; i < 30; i++) {
            System.out.print(items.get(i) + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Sorted sorting = new Sorted();
    }
}

class Item {

    private int a;
    private int b;
    private int c;

    public Item(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getC() {
        return c;
    }

    @Override
    public String toString() {
        return a + ":" + b + ":" + c;
    }
}
