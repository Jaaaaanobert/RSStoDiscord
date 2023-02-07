package de.jaaaaanobert.main.rssreader;

import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.io.FeedException;
import de.jaaaaanobert.main.util.RSSFilter;
import de.jaaaaanobert.main.http.Webhook;
import de.jaaaaanobert.main.index.WriteIndex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetNewEntries {

    public void sync( String instanceName, String feedURL, String discordURL,  ArrayList<String> guidList ) {

        try {
            ReadRSSFeed feed = new ReadRSSFeed( feedURL );

            List<SyndEntryImpl> remoteEntry = feed.getFeedEntrys();

            boolean newItems = false;

            for ( SyndEntryImpl s : remoteEntry ) {
                if ( !guidList.contains( s.getUri() ) ) {
                    guidList.add( s.getUri() );
                    new WriteIndex().writeIndex( s.getUri(), instanceName );
                    if ( instanceName.equals( "Dortmund" ) && new RSSFilter().titleFilter( s.getTitle(), "Verkehr:" ) ) {
                        new Webhook().sendPost( s, instanceName, discordURL );
                    } else if ( !instanceName.equals( "Dortmund" ) )
                        new Webhook().sendPost( s, instanceName, discordURL );

                    System.out.println( s.getUri() + " is new!" );
                    newItems = true;
                }
            }

            if ( !newItems ) {
                System.out.println( "No new Items in instance " + instanceName );
            }
        } catch ( IOException | FeedException e ) {
            e.printStackTrace();
        }
    }
}
