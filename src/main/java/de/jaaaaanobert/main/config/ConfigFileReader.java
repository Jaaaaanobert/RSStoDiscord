package de.jaaaaanobert.main.config;

import de.jaaaaanobert.main.util.TimeTools;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Properties;

public class ConfigFileReader {

    private int interval = 20;
    private long latestSyncTime;

    private ArrayList<RSSInstance> config = new ArrayList<>();
    public int getInterval() {
        return interval;
    }
    public long getLatestSyncTime() {
        return latestSyncTime;
    }

    private ConfigFileReader() {
        Properties configprop = new Properties();

        if ( configCreated() ) {
            try {
                BufferedInputStream bf = new BufferedInputStream( Files.newInputStream( Paths.get( "config.properties" ) ) );
                configprop.load( bf );
                int instanceCount = Integer.parseInt( configprop.getProperty( "instances" ) );
                interval = Integer.parseInt( configprop.getProperty( "interval" ) );
                latestSyncTime = Long.parseLong( configprop.getProperty( "sync" ) );

                for ( int i = 1; instanceCount >= i; i++ ) {

                    config.add( new RSSInstance(
                            configprop.getProperty( "instancename-" + i ),
                            configprop.getProperty( "feedurl-" + i ),
                            configprop.getProperty( "discordurl-" + i )
                    ) );

                }

                bf.close();

            } catch ( Exception e ) {
                e.printStackTrace();
            }
        } else {
            createConfig();
            System.out.println( "Please fill config file!" );
            System.exit( 0 );
        }
    }

    private static final ConfigFileReader configFileReader = new ConfigFileReader();

    public static ConfigFileReader getInstance() {
        return configFileReader;
    }

    public ArrayList<RSSInstance> getConfig() {
        return config;
    }

    private boolean configCreated() {
        return new File( "config.properties" ).exists();
    }

    private void createConfig() {
        try {
            File file = new File( "config.properties" );

            Properties properties = new Properties();

            properties.setProperty( "instances", "1" );
            properties.setProperty( "instancename-1", "" );
            properties.setProperty( "feedurl-1", "" );
            properties.setProperty( "discordurl-1", "" );
            properties.setProperty( "sync", String.valueOf( new TimeTools().getUnixTime() ) );
            properties.setProperty( "interval", "20" );

            properties.store( Files.newOutputStream( file.toPath() ), null );

        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
}
