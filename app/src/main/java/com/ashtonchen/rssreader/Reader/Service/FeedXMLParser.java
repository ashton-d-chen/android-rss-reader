package com.ashtonchen.rssreader.reader.service;

/**
 * Created by Ashton Chen on 15-12-09.
 */

import android.util.Log;
import android.util.Xml;

import com.ashtonchen.rssreader.reader.model.Channel;
import com.ashtonchen.rssreader.reader.model.Feed;
import com.ashtonchen.rssreader.utility.StringUtility;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class FeedXMLParser {
    private static final String ns = null;

    private static final String RSS = "rss";
    private static final String CHANNEL = "channel";
    private static final String ITEM = "item";
    private static final String URL = "url";
    private static final String LINK = "link";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String MEDIA_THUMBNAIL = "media:thumbnail";
    private static final String PUBLICATION_DATE = "pubDate";
    private static final String IMAGE = "image";

    // We don't use namespaces

    public Channel parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readRSS(parser);
        } finally {
            in.close();
        }
    }

    private Channel readRSS(XmlPullParser parser) throws XmlPullParserException, IOException {
        Channel channel = new Channel();
        parser.require(XmlPullParser.START_TAG, ns, RSS);
        while (parser.next() != XmlPullParser.END_TAG) {

            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            Log.d("FeedXMLParser", "getting feeds");

            String name = parser.getName();
            Log.d("FeedXMLParser", name);
            // Starts by looking for the entry tag
            if (name.equals(CHANNEL)) {
                channel = readChannel(parser);
            } else {
                skip(parser);
            }
        }

        return channel;
    }

    private Channel readChannel(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, CHANNEL);

        Channel channel = new Channel();
        List<Feed> feeds = new ArrayList<>();

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            switch (parser.getName()) {
                case TITLE:
                    channel.setTitle(readText(parser, TITLE));
                    break;
                case LINK:
                    channel.setWebUrl(readText(parser, LINK));
                    break;
                case DESCRIPTION:
                    channel.setDescription(readText(parser, DESCRIPTION));
                    break;
                case IMAGE:
                    channel.setThumbnailURL(readImageURL(parser));
                    break;
                case ITEM:
                    Feed feed = readFeed(parser);
                    feed.setWebThumbnailURL(channel.getThumbnailURL());
                    feeds.add(feed);
                    break;
                default:
                    skip(parser);

            }
        }
        for (Feed feed : feeds) {
            Log.d("FeedXMLParser", feed.getTitle());
        }
        channel.setFeeds(feeds);
        return channel;
    }

    // Parses the contents of an entry. If it encounters a title, summary, or link tag, hands them
    // off
    // to their respective &quot;read&quot; methods for processing. Otherwise, skips the tag.
    private Feed readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, ITEM);

        Feed feed = new Feed();

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            switch (parser.getName()) {
                case TITLE:
                    feed.setTitle(readText(parser, TITLE).replace("&#039;", "'"));
                    break;
                case LINK:
                    feed.setUrl(readText(parser, LINK));
                    break;
                case DESCRIPTION:
                    feed.setDescription(StringUtility.removeTrailingTags(readText(parser, DESCRIPTION).replace("&#039;", "'")));
                    break;
                case MEDIA_THUMBNAIL:
                    feed.setThumbnailURL(readThumbnailURL(parser));
                    break;
                case PUBLICATION_DATE:
                    feed.setPubDate(readText(parser, PUBLICATION_DATE));
                    break;
                default:
                    skip(parser);
            }
        }
        return feed;
    }

    private String readText(XmlPullParser parser, String tag) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, tag);
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        parser.require(XmlPullParser.END_TAG, ns, tag);
        return result;
    }

    // Processes channel image
    private String readImageURL(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, IMAGE);
        String imageURL = "";
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals(URL)) {
                imageURL = readText(parser, URL);
            } else {
                skip(parser);
            }
        }
        return imageURL;
    }

    // Processes thumbnail url tags in the feed.
    private String readThumbnailURL(XmlPullParser parser) throws IOException, XmlPullParserException {
        String link = "";
        parser.require(XmlPullParser.START_TAG, ns, MEDIA_THUMBNAIL);
        String tag = parser.getName();
        String relType = parser.getAttributeValue(null, URL);
        if (tag.equals("media:thumbnail")) {
            link = parser.getAttributeValue(null, URL);
            parser.nextTag();
        }
        parser.require(XmlPullParser.END_TAG, ns, MEDIA_THUMBNAIL);
        Log.d(this.getClass().getName(), "Thumbnail URL = " + link);
        return link;
    }

    // Skips tags the parser isn't interested in. Uses depth to handle nested tags. i.e.,
    // if the next tag after a START_TAG isn't a matching END_TAG, it keeps going until it
    // finds the matching END_TAG (as indicated by the value of "depth" being 0).
    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}

