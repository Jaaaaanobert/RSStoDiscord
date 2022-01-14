package de.jaaaaanobert.main.rssreader;

import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.io.FeedException;
import de.jaaaaanobert.main.filter.RSSFilter;
import de.jaaaaanobert.main.http.Webhook;
import de.jaaaaanobert.main.index.ReadIndex;
import de.jaaaaanobert.main.index.WriteIndex;

import java.io.IOException;
import java.util.List;

public class GetNewEntries {

    public void sync( String instanceName, String feedURL ) {

        try {
            ReadRSSFeed feed = new ReadRSSFeed( feedURL );

            List<SyndEntryImpl> remoteEntry = feed.getFeedEntrys();

            new ReadIndex( instanceName );

            Boolean newItems = false;

            for ( SyndEntryImpl s : remoteEntry ) {
                if ( !ReadIndex.guid.contains( s.getUri() ) ) {
                    ReadIndex.guid.add( s.getUri() );
                    new WriteIndex().writeIndex( s.getUri(), instanceName );

                    if ( instanceName.equals( "Dortmund" ) && new RSSFilter().titleFilter( s.getTitle(), "Verkehr:" ) ) {
                        new Webhook().sendPost( s, instanceName );
                    } else new Webhook().sendPost( s, instanceName );
                    System.out.println( s.getUri() + " is new!" );
                    newItems = true;
                }
            }

            if ( !newItems ) {
                System.out.println( "No new Items!" );
            }
        } catch ( IOException e ) {
            e.printStackTrace();
        } catch ( FeedException e ) {
            e.printStackTrace();
        }
    }
}
