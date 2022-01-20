package de.jaaaaanobert.main.http;

import com.squareup.okhttp.*;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import org.json.JSONObject;

import java.io.IOException;

public class Webhook {

    public void sendPost( SyndEntryImpl entry, String instanceName, String discordURL ) {

        JSONObject json = new JSONObject();
        json.put( "username", instanceName );
        json.put( "content", entry.getTitle() + "\n" + entry.getLink() );

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create( MediaType.parse( "application/json" ), json.toString() );
        Request request = new Request.Builder()
                .url( discordURL )
                .post( body )
                .build();

        try {
            client.newCall( request ).execute();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
}
