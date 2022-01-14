package de.jaaaaanobert.main.index;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class IndexGUID {

    private ArrayList<String> guid = new ArrayList<>();

    public void writeIndex( String guid, String instance ) throws IOException {
        FileOutputStream fos = new FileOutputStream( instance + ".index", true );
        String content = guid;
        fos.write( ( content + "\n" ).getBytes( StandardCharsets.UTF_8 ) );
        fos.close();
    }

    public ArrayList<String> readIndex( String instance ) throws IOException {
        BufferedReader bf = new BufferedReader(  new BufferedReader( new FileReader( "instance" + ".index" ) ) );
        String[] guid = bf.readLine().split( "\n" );

        ArrayList<String> list = new ArrayList<>();

        for ( String s : guid )
            list.add( s );

        return list;
    }
}
