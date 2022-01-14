package de.jaaaaanobert.main.index;

import javafx.scene.shape.Path;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Objects;

public class ReadIndex {

    public static ArrayList<String> guid = new ArrayList<>();

    public ReadIndex( String instance ) throws IOException {
        new File( instance + ".index" ).createNewFile();


        BufferedReader bf = new BufferedReader( new InputStreamReader( new FileInputStream( instance + ".index" ), StandardCharsets.UTF_8 ) );

        String input = null;

        while ( ( input = bf.readLine() ) != null ) {
          guid.add( input );
        }

    }
}
