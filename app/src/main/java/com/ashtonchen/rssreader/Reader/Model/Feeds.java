package com.ashtonchen.rssreader.Reader.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class Feeds {

    /**
     * An array of feed items.
     */
    private static List<Feed> feeds = new ArrayList<Feed>();

    public static void reset() {
        feeds = new ArrayList<Feed>();
    }
    public static void add(Feed item) {
        feeds.add(item);
    }

    public static Feed get(int index) {
        return feeds.get(index);
    }

    public static List<Feed> getFeeds() {
        return feeds;
    }

    public static int size() {
        return feeds.size();
    }

    public static void addAll(List<Feed> list) {
        if (feeds != null) {
            feeds.addAll(list);
            Collections.sort(feeds);
        } else {
            feeds = list;
        }
    }
}
