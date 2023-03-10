package de.jaaaaanobert.main.rssreader;

import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class ReadRSSFeed {

    public List getFeedEntrys() {
        return feedEntrys;
    }

    private final List<SyndEntryImpl> feedEntrys;

    public ReadRSSFeed( String feedURL ) throws IOException, FeedException {
        SyndFeed feed = new SyndFeedInput().build( new XmlReader( new URL( feedURL ) ) );
        this.feedEntrys = feed.getEntries();
    }

}
