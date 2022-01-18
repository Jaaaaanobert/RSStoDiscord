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

            List<RSSInstance> instances = ConfigFileReader.getInstance().getConfig();

            HashMap<String, ArrayList<String>> index = new HashMap<>();

            for ( RSSInstance o : instances )
                index.put( o.getInstanceName(), new ReadIndex().readFile( o.getInstanceName() ) );

            //ArrayList<String> localDortmund = new ReadIndex().readFile( "Dortmund" );
            //ArrayList<String> localGelsenkirchen = new ReadIndex().readFile( "Gelsenkirchen" );

            Timer timer = new Timer();
            timer.schedule( new TimerTask() {
                @Override
                public void run() {

                    System.out.println();

                    for ( RSSInstance o : instances ) {

                        System.out.println( "Instance created: " + o.getInstanceName() + " " + o.getInstanceFeedURL() +
                                " " + o.getInstanceDiscordURL() + " " +
                                index.get( o.getInstanceName() ) );

                        new GetNewEntries().sync( o.getInstanceName(), o.getInstanceFeedURL(), o.getInstanceDiscordURL(),
                                index.get( o.getInstanceName() ) );
                    }
                }
            }, 0, 1000 * 60 * 60 );


        } catch ( IOException e ) {
            e.printStackTrace();
        }


    }
}


