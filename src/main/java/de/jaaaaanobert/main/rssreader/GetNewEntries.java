package de.jaaaaanobert.main.rssreader;

import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.io.FeedException;
import de.jaaaaanobert.main.http.Webhook;
import de.jaaaaanobert.main.index.IndexTools;
import de.jaaaaanobert.main.util.TimeTools;

import java.io.IOException;
import java.util.List;

public class GetNewEntries {

    public void sync( String instanceName, String feedURL, String discordURL ) {

        try {
            ReadRSSFeed feed = new ReadRSSFeed( feedURL );
            TimeTools tools = new TimeTools();

            List<SyndEntryImpl> remoteEntry = feed.getFeedEntrys();

            boolean newItems = false;

            for ( SyndEntryImpl s : remoteEntry ) {
                if ( tools.entryIsNew( s.getPublishedDate().getTime() ) ) {
                    new Webhook().sendPost( s, instanceName, discordURL );

                    System.out.println( "New entry: " + s.getTitle() );
                    newItems = true;
                }
            }

            if ( !newItems ) {
                System.out.println( "No new Items in instance " + instanceName );
            }
            new IndexTools().writeIndex();

        } catch ( IOException | FeedException e ) {
            e.printStackTrace();
        }
    }
}
