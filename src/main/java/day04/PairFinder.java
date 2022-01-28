package day04;

import java.lang.reflect.Parameter;
import java.sql.Array;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.function.IntPredicate;

public class PairFinder {

    public int findPairs(int[] arr){

     return  Arrays.stream(arr)
             .distinct()
             .mapToLong(o->Arrays.stream(arr)
                .filter(c->c==o)
                .count()
                 )
             .mapToInt(l-> (int) l/2)
             .reduce(0,Integer::sum);
    }

    public static void main(String[] args) {
        PairFinder pf=new PairFinder();
        int[] i={7, 1, 5, 7, 3, 3, 5, 7, 6, 7};
       // Arrays.stream(pf.findPairs(i)).forEach(System.out::println);
        System.out.println(pf.findPairs(i));
    }
}
