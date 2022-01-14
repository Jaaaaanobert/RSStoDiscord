package de.jaaaaanobert.main.rssreader;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReadRSSFeed {

    private final SyndFeed feed;
    private final List feedEntrys;
    public final HashMap<String, SyndFeedImpl> feedObjects = new HashMap<>();

    public ReadRSSFeed( String feedURL ) throws IOException, FeedException {
        feed = new SyndFeedInput().build( new XmlReader( new URL( feedURL ) ) );
        this.feedEntrys = feed.getEntries();

        for ( Object o : feed.getEntries() ) {
            feedObjects.put( ( ( SyndFeedImpl ) o ).getUri(), ( SyndFeedImpl ) o );
        }
    }

    public List<String> getGUID() {
        ArrayList<String> guid = new ArrayList<>();
        for ( Object o : feedEntrys ) {
            guid.add( ( ( SyndFeedImpl ) o ).getUri() );
        }
        return guid;
    }

    public List<String> title() {
        ArrayList<String> title = new ArrayList<>();
        for ( Object o : feedEntrys ) {
            title.add( ( ( SyndFeedImpl ) o ).getTitle() );
        }
        return title;
    }

}
