package com.ashtonchen.rssreader.Subscription.dummy;

import com.ashtonchen.rssreader.Subscription.Model.Subscription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class Subscriptions {

    private static final List<Subscription> ITEMS = new ArrayList<>();
    private static final Map<String, Subscription> ITEM_MAP = new HashMap<>();

    static {
        for (int i = 0; i < 25; i++) {
            Subscription item = new Subscription();
            item.setTitle(i + " item");
            item.setDescription(i + " description");
            item.setThumbnailURL(i + " thumbnail URL");
            ITEMS.add(item);
        }
    }

    public static Subscription get(int index) {
        return ITEMS.get(index);
    }

    public static void add(Subscription item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getUrl(), item);
    }

    public static Subscription getByItem(Subscription item) {
        return ITEM_MAP.get(item.getUrl());
    }

    public static int getSize() {
        return ITEMS.size();
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }
}
