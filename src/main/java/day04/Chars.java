package day04;

import java.util.Map;
import java.util.TreeMap;

public class Chars {
    public Map<Character, Integer> countCharsWithCompute(String s) {
        Map<Character, Integer> result = new TreeMap<>();
        for (char c : s.toCharArray()) {
            result.compute(c, (k, v) -> v == null ? 1 : v + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        Chars chars=new Chars();
        System.out.println(chars.countCharsWithCompute("Tele van a szőlős tetűvel."));
    }
}
