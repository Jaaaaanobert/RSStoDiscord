package de.jaaaaanobert.main.rssreader;

import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.io.FeedException;
import de.jaaaaanobert.main.filter.RSSFilter;
import de.jaaaaanobert.main.http.Webhook;
import de.jaaaaanobert.main.index.ReadIndex;
import de.jaaaaanobert.main.index.WriteIndex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetNewEntries {

    public void sync( String instanceName, String feedURL ) {

        try {
            ReadRSSFeed feed = new ReadRSSFeed( feedURL );

            List<SyndEntryImpl> remoteEntry = feed.getFeedEntrys();

            ArrayList<String> guidFile = new ReadIndex().readFile( instanceName );

            boolean newItems = false;

            for ( SyndEntryImpl s : remoteEntry ) {
                if ( !guidFile.contains( s.getUri() ) ) {
                    guidFile.add( s.getUri() );
                    new WriteIndex().writeIndex( s.getUri(), instanceName );

                    if ( instanceName.equals( "Dortmund" ) && new RSSFilter().titleFilter( s.getTitle(), "Verkehr:" ) ) {
                        new Webhook().sendPost( s, instanceName );
                    } else if ( !instanceName.equals( "Dortmund" ) )
                        new Webhook().sendPost( s, instanceName );

                    System.out.println( s.getUri() + " is new!" );
                    newItems = true;
                }
            }

            if ( !newItems ) {
                System.out.println( "No new Items in instance! " + instanceName );
            }
        } catch ( IOException | FeedException e ) {
            e.printStackTrace();
        }
    }
}
