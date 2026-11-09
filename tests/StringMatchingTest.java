import java.util.Arrays;
import java.util.Random;

public class StringMatchingTest {
    private static int checks;
    private static void verify(String text, String pattern) {
        StringBuilder positions = new StringBuilder();
        int count = 0;
        for (int start = 0; start <= text.length(); start++) {
            if (text.indexOf(pattern, start) == start) { positions.append(start).append(", "); count++; }
        }
        String[] expected = {positions.toString(), Integer.toString(count)};
        String[][] actual = {StringMatching.BruteForceStringMatch(text, pattern),
            StringMatching.KMP(text, pattern), StringMatching.BMStringMatch(text, pattern)};
        for (String[] result : actual) {
            checks++;
            if (!Arrays.equals(expected, result)) throw new AssertionError("Mismatch: " + text + " / " + pattern + " => " + Arrays.toString(result));
        }
    }
    public static void main(String[] args) {
        verify("aaaaaa", "aaa");
        verify("abababaca", "ababaca");
        verify("abc", "abcdef");
        verify("", ""); verify("", "a"); verify("abc", "");
        verify("سلام سلام", "سلام");
        verify("😀x😀", "😀");
        Random random = new Random(42);
        String alphabet = "abسم";
        for (int trial = 0; trial < 500; trial++) {
            StringBuilder text = new StringBuilder(), pattern = new StringBuilder();
            int n = random.nextInt(40), m = random.nextInt(12);
            for (int i = 0; i < n; i++) text.append(alphabet.charAt(random.nextInt(alphabet.length())));
            for (int i = 0; i < m; i++) pattern.append(alphabet.charAt(random.nextInt(alphabet.length())));
            verify(text.toString(), pattern.toString());
        }
        String[] saved = StringMatching.BruteForceStringMatch("aaa", "a");
        StringMatching.BruteForceStringMatch("x", "y");
        if (!saved[1].equals("3")) throw new AssertionError("Shared mutable results");
        System.out.println(checks + " algorithm comparisons passed; independent result arrays checked.");
    }
}
