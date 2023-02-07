package de.jaaaaanobert.main.util;

public class RSSFilter {

    public boolean titleFilter( String title, String filterText ) {
        return title.startsWith( filterText );
    }
}
