package de.jaaaaanobert.main;


import de.jaaaaanobert.main.config.ConfigFileReader;
import de.jaaaaanobert.main.config.RSSInstance;
import de.jaaaaanobert.main.rssreader.GetNewEntries;

import java.util.*;

public class main {
    public static void main( String[] args ) {

        ConfigFileReader cfr = ConfigFileReader.getInstance();
        ArrayList<RSSInstance> instances = cfr.getConfig();


        Timer timer = new Timer();
        timer.schedule( new TimerTask() {
            @Override
            public void run() {

                for ( RSSInstance o : instances ) {

                    System.out.println( "Instance created: " + o.getInstanceName() );

                    new GetNewEntries().sync( o.getInstanceName(), o.getInstanceFeedURL(), o.getInstanceDiscordURL());
                }
            }
        }, 0, 1000L * 60 * cfr.getInterval() );


    }
}


