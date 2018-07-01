import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MorseConverter {

    private static Map<String, String> morseToLetter;

    static {
        morseToLetter = new HashMap<>();
        morseToLetter.put(".-", "A");
        morseToLetter.put("-...", "B");
        morseToLetter.put("-.-.", "C");
        morseToLetter.put("-..", "D");
        morseToLetter.put(".", "E");
        morseToLetter.put("..-.", "F");
        morseToLetter.put("--.", "G");
        morseToLetter.put("....", "H");
        morseToLetter.put("..", "I");
        morseToLetter.put(".---", "J");
        morseToLetter.put("-.-", "K");
        morseToLetter.put(".-..", "L");
        morseToLetter.put("--", "M");
        morseToLetter.put("-.", "N");
        morseToLetter.put("---", "O");
        morseToLetter.put(".--.", "P");
        morseToLetter.put("--.-", "Q");
        morseToLetter.put(".-.", "R");
        morseToLetter.put("...", "S");
        morseToLetter.put("-", "T");
        morseToLetter.put("..-", "U");
        morseToLetter.put("...-", "V");
        morseToLetter.put(".--", "W");
        morseToLetter.put("-..-", "X");
        morseToLetter.put("-.--", "Y");
        morseToLetter.put("--..", "Z");
    }

    public static String convertMorse(String morse) {
        return Arrays.stream(morse.split(" ")).map(x -> morseToLetter.getOrDefault(x, "")).collect(Collectors.joining());
    }

    public static void main(String[] args) {
        System.out.println(convertMorse(".... . .-.. .-.. --- .... --- .-- .- .-. . -.-- --- ..- ... - --- .--."));
        System.out.println(convertMorse("- .... . --.- ..- .. -.-. -.- -... .-. --- .-- -. ..-. --- -..- .--- " +
                "..- -- .--. ... --- ...- . .-. - .... . .-.. .- --.. -.-- -.. --- --. ... - --- .--."));
    }
}
