package de.jaaaaanobert.main.filter;

public class RSSFilter {

    public boolean titleFilter( String title, String filterText ) {
        return title.startsWith( filterText );
    }
}
