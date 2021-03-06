package com.inubit.ibis.utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author r4fter
 */
public final class StringUtil {

    /**
     * Private constructor.
     */
    private StringUtil() {
        // do nothing
    }

    public static boolean isNotSet(final String text) {
        return (text == null || text.equals(""));
    }

    public static boolean isSet(final String text) {
        return !isNotSet(text);
    }

    public static boolean isSet(final StringBuffer textBuffer) {
        return !isNotSet(textBuffer);
    }

    public static boolean isNotSet(final StringBuilder textBuilder) {
        return (textBuilder == null || textBuilder.length() == 0);
    }

    public static boolean isNotSet(final StringBuffer textBuffer) {
        return (textBuffer == null || textBuffer.length() == 0);
    }

    public static boolean isOneOf(
            final String searchFor,
            final List<String> valueList) {
        if (valueList == null || valueList.isEmpty()) {
            return false;
        }
        return isOneOf(searchFor, valueList.toArray(new String[0]));
    }

    public static boolean isOneOf(
            final String searchFor,
            final String[] valueList) {
        if (valueList == null) {
            return false;
        }
        for (final String value : valueList) {
            if (isSet(value) && value.equals(searchFor)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param text text to check
     * @return <code>true</code> if the given text contains only white spaces (e.g. \n, \t, \r), <code>false</code>
     * otherwise
     */
    public static boolean isWhitespacesOnly(final String text) {
        if (isSet(text)) {
            final Pattern p = Pattern.compile("^\\s+$");
            final Matcher m = p.matcher(text);
            return m.matches();
        }
        return false;
    }
}
