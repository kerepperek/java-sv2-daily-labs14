package day04;

import java.lang.reflect.Parameter;
import java.sql.Array;
import java.sql.SQLOutput;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;

public class PairFinder {


    public int findPairs(int[] arr) {


        return Arrays.stream(arr)
                .distinct()
                .mapToLong(o -> Arrays.stream(arr)
                        .filter(c -> c == o)
                        .count()
                )
                .mapToInt(l -> (int) l / 2)
                .sum();

    }

    public int findPairsx(int[] arr) {
     List<Integer> numbers= Arrays.stream(arr).boxed().toList();
        return numbers.stream()
                .collect(Collectors.toMap(n -> n, n -> 1, Integer::sum))
                .values().stream()
                .mapToInt(v -> v / 2)
                .sum();
    }
    public int findPairsxParalell(int[] arr) {
        List<Integer> numbers= Arrays.stream(arr).boxed().toList();
        return numbers.parallelStream()
                .collect(Collectors.toMap(n -> n, n -> 1, Integer::sum))
                .values().parallelStream()
                .mapToInt(v -> v / 2)
                .sum();
    }

    public int findPairsx2(int[] arr) {
        List<Integer> numbers = Arrays.stream(arr).boxed().collect(Collectors.toList());
        ;
        return numbers.stream()
                .collect(Collectors.toConcurrentMap(n -> n, n -> 1, Integer::sum))
                .values().stream()
                .mapToInt(v -> (int) v / 2)
                .sum();
    }

    public long findPairs2(int[] arr) {

        return Arrays.stream(arr)
                .boxed()
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .values().stream()
                .mapToLong(l -> l / 2)
                .sum();

    }

    public int findPairsAdam(int[] arr) {

        Map<Integer, Integer> pairs = new TreeMap<>();
        for (int i : arr) {
            //  result.compute(i, (k, v) -> v == null ? 1 : v + 1);
            if (!pairs.containsKey(i)) {
                pairs.put(i, 1);
            } else {
                pairs.put(i, pairs.get(i) + 1);
            }
        }
        return pairs.values().stream().mapToInt(i -> i / 2).sum();

    }

    public int findPairsBeatrix(int[] arr) {

        int[] result = Arrays.copyOf(arr, arr.length);
        Arrays.sort(result);
        int count = 0;
        for (int i = 1; i < result.length; i++) {
            if (result[i] == result[i - 1]) {
                count++;
                i++;
            }
        }
        return count;
    }

    public long findPairsY(int[] arr) {

        final int[] temp = {0, 0};//number,count
        return Arrays.stream(arr).sorted()
                .reduce(0, (a, b) -> {
                    temp[1] = temp[0] == b ? temp[1] ^ 1 : 1;
                    temp[0] = b;
                    return a + 1 - temp[1] ;
                });
    }

    public int findPairsRecursion(int[] arr) {
        if (arr.length > 1) {
            Arrays.sort(arr);
            return switch (arr[0] - arr[1]) {
                case 0 -> 1 + findPairsRecursion(Arrays.copyOfRange(arr, 2, arr.length));
                default -> 0 + findPairsRecursion(Arrays.copyOfRange(arr, 1, arr.length));
            };
        }
        return 0;
    }

    public static void main(String[] args) {
        Random rnd = new Random();
        PairFinder pf = new PairFinder();

        int[] x = new int[100000];
        for (int i = 0; i < 100000; i++) {
            x[i] = rnd.nextInt(100) + 1;
        }
        //int[] i={7, 1, 5, 7, 3, 3, 5, 7, 6, 7};
        // Arrays.stream(pf.findPairs(i)).forEach(System.out::println);

        long startTime = System.nanoTime();
        System.out.println("stream:" + pf.findPairs(x));
        long endTime = System.nanoTime();
        System.out.println(endTime - startTime);

        startTime = System.nanoTime();
        System.out.println("stream2:" + pf.findPairs2(x));
        endTime = System.nanoTime();
        System.out.println(endTime - startTime);

        startTime = System.nanoTime();
        System.out.println("streamx:" + pf.findPairsx(x));
        endTime = System.nanoTime();
        System.out.println(endTime - startTime);

        startTime = System.nanoTime();
        System.out.println("streamxParalell:" + pf.findPairsxParalell(x));
        endTime = System.nanoTime();
        System.out.println(endTime - startTime);

//        startTime = System.nanoTime();
//        System.out.println("Recursion:" + pf.findPairsRecursion(x));
//        endTime = System.nanoTime();
//        System.out.println(endTime - startTime);

        startTime = System.nanoTime();
        System.out.println("Adam:" + pf.findPairsAdam(x));
        endTime = System.nanoTime();
        System.out.println(endTime - startTime);

        startTime = System.nanoTime();
        System.out.println("Beatrix:" + pf.findPairsBeatrix(x));
        endTime = System.nanoTime();
        System.out.println(endTime - startTime);

        startTime = System.nanoTime();
        System.out.println("BeatrixY:" + pf.findPairsY(x));
        endTime = System.nanoTime();
        System.out.println(endTime - startTime);


    }
}
