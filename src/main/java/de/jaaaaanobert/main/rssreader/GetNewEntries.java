package de.jaaaaanobert.main.rssreader;

import com.sun.syndication.io.FeedException;
import de.jaaaaanobert.main.index.IndexGUID;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetNewEntries {

    public void sync( String instanceName, String feedURL ) {

        try {
            ReadRSSFeed feed = new ReadRSSFeed( feedURL );

            List<String> remoteguid = feed.getGUID();

            for ( String s : remoteguid ) {
                if ( !guid.contains( s ) ) {
                    guid.add( s );
                    new IndexGUID().writeIndex( s, instanceName );
                    System.out.println(guid + " is new!");
                }
            }


            feed.getGUID();
        } catch ( IOException e ) {
            e.printStackTrace();
        } catch ( FeedException e ) {
            e.printStackTrace();
        }
    }
}
