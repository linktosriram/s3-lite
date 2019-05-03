package io.github.linktosriram.s3lite.util;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public final class StringUtils {

    private static final Pattern SPACES = compile(" +");

    private StringUtils() {
        throw new AssertionError();
    }

    /**
     * Returns a string whose value is this string, with any leading and trailing whitespace removed.
     * Also replaces multiple consecutive whitespaces with a single whitespace.
     *
     * @param str the {@code String} that needs trimming
     * @return A string whose value is this string, with any leading and trailing whitespace removed and no
     * consecutive whitespaces
     */
    public static String trimAll(final String str) {
        return SPACES.matcher(str.trim()).replaceAll(" ");
    }
}
