package de.jaaaaanobert.main;


import de.jaaaaanobert.main.config.ConfigFileReader;
import de.jaaaaanobert.main.config.RSSInstance;
import de.jaaaaanobert.main.index.ReadIndex;
import de.jaaaaanobert.main.rssreader.GetNewEntries;

import java.io.IOException;
import java.util.*;

public class main {
    public static void main( String[] args ) {

        try {

            ConfigFileReader cfr = ConfigFileReader.getInstance();

            List<RSSInstance> instances = cfr.getConfig();

            HashMap<String, ArrayList<String>> index = new HashMap<>();

            for ( RSSInstance o : instances )
                index.put( o.getInstanceName(), new ReadIndex().readFile( o.getInstanceName() ) );

            Timer timer = new Timer();
            timer.schedule( new TimerTask() {
                @Override
                public void run() {


                    for ( RSSInstance o : instances ) {

                        System.out.println( "Instance created: " + o.getInstanceName() );

                        new GetNewEntries().sync( o.getInstanceName(), o.getInstanceFeedURL(), o.getInstanceDiscordURL(),
                                index.get( o.getInstanceName() ) );
                    }
                }
            }, 0, 1000L * 60 * cfr.getInterval() );


        } catch ( IOException e ) {
            e.printStackTrace();
        }


    }
}


