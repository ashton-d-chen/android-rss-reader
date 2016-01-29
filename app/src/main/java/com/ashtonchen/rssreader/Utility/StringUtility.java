package com.ashtonchen.rssreader.utility;

/**
 * Created by ashtonchen on 15-12-10.
 */
public class StringUtility {

    public static String removeTrailingTags(String text) {
        int index = text.indexOf("<br");
        if (index >= 0) {
            return text.substring(0, index);
        } else {
            return text;
        }
    }
}
