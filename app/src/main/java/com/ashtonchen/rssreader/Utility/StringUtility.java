package com.ashtonchen.rssreader.utility;

/**
 * Created by ashtonchen on 15-12-10.
 */
public class StringUtility {

    public static String removeTrailingTags(String text) {
        return text.substring(0, text.indexOf("<br"));
    }
}
