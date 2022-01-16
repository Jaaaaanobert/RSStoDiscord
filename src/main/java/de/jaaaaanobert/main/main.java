package de.jaaaaanobert.main;


import de.jaaaaanobert.main.index.ReadIndex;
import de.jaaaaanobert.main.rssreader.GetNewEntries;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class main {
    public static void main( String[] args ) {

        Timer timer = new Timer();
        timer.schedule( new TimerTask() {
            @Override
            public void run() {
                try {
                    ArrayList<String> localDortmund = new ReadIndex().readFile( "Dortmund" );
                    ArrayList<String> localGelsenkirchen = new ReadIndex().readFile( "Gelsenkirchen" );

                    new GetNewEntries().sync( "Dortmund", "https://www.dortmund.de/de/dortmund_de/feeds/news_rss.xml",
                            localDortmund );

                    new GetNewEntries().sync( "Gelsenkirchen", "https://www.gelsenkirchen.de/de/_meta/aktuelles/artikel/newsfeed/",
                            localGelsenkirchen );

                } catch ( IOException e ) {
                    e.printStackTrace();
                }
            }
        },0, 1000 * 60 * 60 );




    }
}


