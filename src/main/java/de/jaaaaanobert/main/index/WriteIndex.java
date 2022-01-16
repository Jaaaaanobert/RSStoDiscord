package de.jaaaaanobert.main.index;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class WriteIndex {


    public void writeIndex( String guid, String instance ) throws IOException {
        FileOutputStream fos = new FileOutputStream( instance + ".index", true );
        fos.write( ( guid + "\n" ).getBytes( StandardCharsets.UTF_8 ) );
        fos.close();
    }
}
