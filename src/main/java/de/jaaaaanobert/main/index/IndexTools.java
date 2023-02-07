package de.jaaaaanobert.main.index;


import de.jaaaaanobert.main.util.TimeTools;

import java.io.*;
import java.nio.file.Files;
import java.util.Properties;

public class IndexTools {

	public void writeIndex() {
		try {
		File file = new File( "config.properties" );
		Properties properties = new Properties();
		properties.load( new InputStreamReader( Files.newInputStream( file.toPath() ) ) ) ;
		properties.setProperty( "sync", String.valueOf( new TimeTools().getUnixTime() ) );

			properties.store( new FileOutputStream( file, false ), null );

		} catch ( IOException e ) {
			throw new RuntimeException( e );
		}
	}

}
