package de.jaaaaanobert.main;


import de.jaaaaanobert.main.rssreader.GetNewEntries;

public class main {
    public static void main( String[] args ) {

        new GetNewEntries().sync( "Dortmund", "https://www.dortmund.de/de/dortmund_de/feeds/news_rss.xml" );
        new GetNewEntries().sync( "Gelsenkirchen", "https://www.gelsenkirchen.de/de/_meta/aktuelles/artikel/newsfeed/" );
    }
}


