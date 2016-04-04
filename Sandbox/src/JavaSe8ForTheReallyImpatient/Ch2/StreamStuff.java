package JavaSe8ForTheReallyImpatient.Ch2;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
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
        printStream(Stream.generate(() -> "echo").limit(5));

        Stream<BigInteger> integers = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE));
        printStream(integers.filter(x -> x.mod(BigInteger.valueOf(17)).equals(BigInteger.ZERO)).limit(5));

        Stream<String> lowercaseWords = words.stream().map(String::toLowerCase);
        printStream(lowercaseWords.limit(10));

        Stream<Character> characterStream = words.stream().map(StreamStuff::characterStream).flatMap(x -> x);
        printStream(characterStream.limit(50));

        Stream<Character> characterStream2 = words.stream().flatMap(StreamStuff::characterStream);
        printStream(characterStream2.skip(50).limit(50));

        System.out.println(Stream.concat(Stream.of('h', 'e', 'l', 'l', 'o'), Stream.of('w', 'o', 'r', 'l', 'd')).collect(Collectors.toSet()));

        Stream<String> uniqueWords = Stream.of("merrily", "merrily", "merrily", "gently").distinct();
        printStream(uniqueWords);

        Stream<String> longestFirst = words.stream().sorted(Comparator.comparing(String::length).reversed()).limit(10);
        printStream(longestFirst);
        Stream<String> shortestFirst = words.stream().sorted(Comparator.comparing(String::length)).limit(10);
        printStream(shortestFirst);

        printStream(Stream.of("merrily", "merrily", "merrily", "gently").distinct());

        Optional<String> largest = words.stream().max(String::compareToIgnoreCase);
        System.out.println(largest);
        Optional<String> longest = words.stream().max((x, y) -> Integer.compare(x.length(), y.length()));
        System.out.println(longest);

        String dir = Optional.ofNullable((String)null).orElseGet(() -> System.getProperty("user.dir"));
        System.out.println(dir);

        Optional<String> empty = Optional.empty();
        System.out.println(empty.orElse("it was null"));

        Optional<Double> d = Optional.of(3D);
        Optional<Float> flatmapOptional = d.flatMap(StreamStuff::GetOptional1).flatMap(StreamStuff::GetOptional2);
        System.out.println(flatmapOptional);

        Map<String, Long> wordsToFreq = words.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(wordsToFreq.get("the"));
        System.out.println(wordsToFreq.get("zigzag"));
        Map<String, Integer> mapToFreqInt = words.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(x -> 1)));
        System.out.println(mapToFreqInt.get("the"));
        System.out.println(mapToFreqInt.get("zigzag"));

        Map<Integer, Set<String>> wordLengthsToWordsWithThatLength = words.stream().collect(Collectors.groupingBy(String::length, Collectors.toSet()));
        System.out.println(wordLengthsToWordsWithThatLength.get(13));

        Map<Character, IntSummaryStatistics> startingCharactersToWordStats = words.stream()
                .collect(Collectors.groupingBy(x -> x.length() > 0 ? x.charAt(0) : '~',
                         Collectors.summarizingInt(String::length)));
        System.out.println(startingCharactersToWordStats.get('b'));

    }

    public static <T> void printStream(Stream<T> s) {
        System.out.println(s.collect(Collectors.toList()));
    }

    public static Stream<Character> characterStream(String s) {
        List<Character> result = new ArrayList<>();
        for (char c : s.toCharArray()) result.add(c);
        return result.stream();
    }

    public static Optional<String> GetOptional1(Double s) {
        return Optional.of(s.toString());
    }

    public static Optional<Float> GetOptional2(String s) {
        return Optional.of(Float.parseFloat(s));
    }
}
