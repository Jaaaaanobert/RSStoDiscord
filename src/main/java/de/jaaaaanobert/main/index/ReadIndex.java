package de.jaaaaanobert.main.index;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ReadIndex {


    public ArrayList<String> readFile( String instance ) throws IOException {

        ArrayList<String> guid = new ArrayList<>();

        new File( instance + ".index" ).createNewFile();
        BufferedReader bf = new BufferedReader(
                new InputStreamReader( new FileInputStream( instance + ".index" ), StandardCharsets.UTF_8 ) );
        String input;
        while ( ( input = bf.readLine() ) != null ) {
            guid.add( input );
        }

        return guid;
    }

}
