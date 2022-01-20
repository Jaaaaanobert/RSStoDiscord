package de.jaaaaanobert.main.config;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConfigFileReader {

    private int instanceCount = 1;
    private int interval = 20;
    private ArrayList<String> instanceName = new ArrayList<>();
    private ArrayList<String> feedURL = new ArrayList<>();
    private ArrayList<String> discordURL = new ArrayList<>();

    public int getInterval() {
        return interval;
    }

    private ConfigFileReader() {
        Properties config = new Properties();

        if ( configCreated() ) {

            try {
                config.load( new BufferedInputStream( new FileInputStream( "config.properties" ) ) );
                instanceCount = Integer.parseInt( config.getProperty( "instances" ) );
                interval = Integer.parseInt( config.getProperty( "interval" ) );

                for ( int i = 1; instanceCount >= i; i++ ) {
                    instanceName.add( i - 1, config.getProperty( "instancename" + i ) );
                    feedURL.add( i - 1, config.getProperty( "feedurl" + i ) );
                    discordURL.add( i - 1, config.getProperty( "discordurl" + i ) );
                }

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

    public List<RSSInstance> getConfig() {
        List<RSSInstance> config = new ArrayList<>();

        for ( int i = 1; instanceCount >= i; i++ )
            config.add( new RSSInstance( instanceName.get( i - 1 ), feedURL.get( i - 1 ), discordURL.get( i - 1 ) ) );

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
            properties.setProperty( "instancename1", "" );
            properties.setProperty( "feedurl1", "" );
            properties.setProperty( "discordurl1", "" );
            properties.setProperty( "interval", "20" );

            properties.store( new FileOutputStream( file ), null );

        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
}
