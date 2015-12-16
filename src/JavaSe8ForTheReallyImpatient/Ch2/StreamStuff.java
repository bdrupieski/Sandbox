package JavaSe8ForTheReallyImpatient.Ch2;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamStuff {

    public static void main(String[] args) throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("alice.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
        System.out.println(words.size());

        long count = words.stream().filter(x -> x.length() > 12).count();
        System.out.println(count);

        Stream<Double> randomStream = Stream.generate(Math::random);
        List<Double> fiveRandomNumbers = randomStream.limit(5).collect(Collectors.toList());
        System.out.println(fiveRandomNumbers);

        System.out.println(Stream.empty().collect(Collectors.toSet()));
        System.out.println(Stream.generate(() -> "echo").limit(5).collect(Collectors.toList()));

        Stream<BigInteger> integers = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE));
        System.out.println(integers.filter(x -> x.mod(BigInteger.valueOf(17)).equals(BigInteger.ZERO)).limit(5).collect(Collectors.toList()));

        Stream<String> lowercaseWords = words.stream().map(String::toLowerCase);
        System.out.println(lowercaseWords.limit(10).collect(Collectors.toList()));

        Stream<Character> characterStream = words.stream().map(StreamStuff::characterStream).flatMap(x -> x);
        System.out.println(characterStream.limit(50).collect(Collectors.toList()));

        Stream<Character> characterStream2 = words.stream().flatMap(StreamStuff::characterStream);
        printStream(characterStream2.skip(50).limit(50));

        System.out.println(Stream.concat(Stream.of('h', 'e', 'l', 'l', 'o'), Stream.of('w', 'o', 'r', 'l', 'd')).collect(Collectors.toSet()));

        Stream<String> uniqueWords = Stream.of("merrily", "merrily", "merrily", "gently").distinct();
        printStream(uniqueWords);

        Stream<String> longestFirst = words.stream().sorted(Comparator.comparing(String::length).reversed()).limit(10);
        printStream(longestFirst);
        Stream<String> shortestFirst = words.stream().sorted(Comparator.comparing(String::length)).limit(10);
        printStream(shortestFirst);
    }

    public static <T> void printStream(Stream<T> s) {
        System.out.println(s.collect(Collectors.toList()));
    }

    public static Stream<Character> characterStream(String s) {
        List<Character> result = new ArrayList<>();
        for (char c : s.toCharArray()) result.add(c);
        return result.stream();
    }
}
