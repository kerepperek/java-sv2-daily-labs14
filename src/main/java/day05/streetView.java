package day05;

import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static java.lang.Integer.parseInt;

public class streetView {
    Map<String, List<Integer>> streets = new LinkedHashMap<>();


    public List<String> readFile(Path path) {
        List<String> lines = new ArrayList<>();
        mapping(lines);
        try {
            return lines = Files.readAllLines(path);
        } catch (IOException ioe) {
            throw new IllegalStateException("Can not read file", ioe);
        }
    }

    private void mapping(List<String> fileLines) {
        for (String l : fileLines) {
            String[] split = l.split(" ");
            String street = split[0];
            int side = Integer.parseInt(split[1]);
            streets.compute(street, (k, v) -> v==null?new ArrayList<>():v).add(
                    streets.get(street).stream().mapToInt(i -> i)
                            .filter(i -> i % 2 == side).max().orElse(-side)+2);
        }
    }


    public static void main(String[] args) {
        streetView sv = new streetView();
        List<String> lines = sv.readFile(Path.of("src/main/resources/streets.txt"));
        sv.mapping(lines);
        sv.streets.entrySet().stream().forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));
        ;
    }
}
