package de.podolak.agenten.utilities;

import java.util.Stack;

/**
 *
 * @version $version$
 * @author $author$
 */
public class Utilities {
    private static Stack<Long> timestack = new Stack();
    private static String timestackIndentation = "";
    private static long minimumDuration = 0;

    /**
     * Starts a time measurement. Use <code>stop()</code> to get the time since the
     * corresponding <code>start()</code> call. Subsequent start calls have to be
     * followed by their stop calls respectively (LIFO buffer).
     * The parameter defines a threshold. Durations that are smaller than the given
     * minimum duration in milliseconds are not displayed (default minimum duration is zero).
     */
    public static void start(long md) {
        minimumDuration = md;
        start();
    }

    /**
     * Starts a time measurement. Use <code>stop()</code> to get the time since the
     * corresponding <code>start()</code> call. Subsequent start calls have to be
     * followed by their stop calls respectively (LIFO buffer).
     */
    public static void start() {
        timestack.push(System.nanoTime());
        timestackIndentation += "  ";
    }

    /**
     * Stops a time measurement and outputs the time since the corresponding start call
     * in millisseconds. A call of <code>stop()</code> corresponds to the last <code>
     * start()</code> call that has not yet been stopped (LIFO buffer).
     */
    public static void stop() {
        stop("Messung");
    }

    /**
     * Stops a time measurement and outputs the time since the corresponding start call
     * in millisseconds. A call of <code>stop()</code> corresponds to the last <code>
     * start()</code> call that has not yet been stopped (LIFO buffer).
     */
    public static void stop(String text) {
        timestackIndentation = removeRight(timestackIndentation, 2);
        long duration = (System.nanoTime() - timestack.pop()) / 1000000;
        if (duration > minimumDuration) {
            System.out.println(timestackIndentation + text + " dauerte " + duration + " Millisekunden");
        }
    }

    /**
     * Returns a string where a defined number of characters are removed from the right.
     * The first parameter defines the string to operate with. The second parameter defines
     * the number of characters to be cut off at the right side of the string, e.g. <code>
     * removeRight("bulldozer", 5) return "dozer".
     *
     * @param text string to cut characters off from
     * @param number number of characters to cut off
     * @return text with the right number characters removed
     */
    public static String removeRight(String text, int number) {
        return text.substring(0, text.length() - number);
    }

    /**
     * Returns a StringBuilder where a defined number of characters are removed from the right.
     * The first parameter defines the StringBuilder to operate with. The second parameter defines
     * the number of characters to be cut off at the right side of the string, e.g. <code>
     * removeRight("bulldozer", 5) return "dozer".
     *
     * @param text StringBuilder to cut characters off from
     * @param number number of characters to cut off
     */
    public static void removeRight(StringBuilder text, int number) {
        text.delete(text.length() - number, text.length());
    }

    public static final String getStringNTimes(String text, int count) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < count; i++) {
            stringBuilder.append(text);
        }

        return stringBuilder.toString();
    }
}
