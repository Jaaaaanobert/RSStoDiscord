package de.jaaaaanobert.main.config;

import de.jaaaaanobert.main.util.TimeTools;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class ConfigFileReader {

    private int instanceCount = 1;
    private int interval = 20;
    //private ArrayList<String> instanceName = new ArrayList<>();
    //private ArrayList<String> feedURL = new ArrayList<>();
    //private ArrayList<String> discordURL = new ArrayList<>();

    private HashMap<String, RSSInstance> config = new HashMap<>();

    public int getInterval() {
        return interval;
    }

    private ConfigFileReader() {
        Properties configprop = new Properties();

        if ( configCreated() ) {
            try {
                configprop.load( new BufferedInputStream( new FileInputStream( "config.properties" ) ) );
                instanceCount = Integer.parseInt( configprop.getProperty( "instances" ) );
                interval = Integer.parseInt( configprop.getProperty( "interval" ) );

                for ( int i = 1; instanceCount >= i; i++ ) {
                    //instanceName.add( i - 1, configprop.getProperty( "instancename" + i ) );
                    //feedURL.add( i - 1, configprop.getProperty( "feedurl" + i ) );
                    //discordURL.add( i - 1, configprop.getProperty( "discordurl" + i ) );

                    config.put( configprop.getProperty( "instancename" + i ), new RSSInstance(
                            configprop.getProperty( "instancename" + i ),
                            configprop.getProperty( "feedurl" + i ),
                            configprop.getProperty( "discordurl" + i )
                    ) );

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

    public HashMap<String, RSSInstance> getConfig() {
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
            properties.setProperty( "timestamp", String.valueOf( new TimeTools().getTimeUnix() ) );
            properties.setProperty( "interval", "20" );

            properties.store( new FileOutputStream( file ), null );

        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
}
