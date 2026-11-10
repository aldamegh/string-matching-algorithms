/** Overlapping-match example with no GUI or timing dependency. */
public class MatchDemo {
    private static void show(String algorithm, String[] result) {
        String positions = result[0].replaceAll(", $", "");
        System.out.println("  " + algorithm + ": [" + positions + "] (" + result[1] + " matches)");
    }

    public static void main(String[] args) {
        String text = "ABABABA";
        String pattern = "ABA";
        System.out.println("Text:    " + text);
        System.out.println("Pattern: " + pattern);
        System.out.println("\nOverlapping matches (zero-based UTF-16 positions)");
        show("Brute force", StringMatching.BruteForceStringMatch(text, pattern));
        show("KMP", StringMatching.KMP(text, pattern));
        show("Boyer-Moore (bad character)", StringMatching.BMStringMatch(text, pattern));
        System.out.println("\nAll three find positions 0, 2, and 4.");
    }
}
