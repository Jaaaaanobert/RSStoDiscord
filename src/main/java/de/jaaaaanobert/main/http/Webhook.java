package de.jaaaaanobert.main.http;

import com.squareup.okhttp.*;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import org.json.JSONObject;

import java.io.IOException;

public class Webhook {

    public void sendPost( SyndEntryImpl entry, String instanceName ) {

        JSONObject json = new JSONObject();
        json.put( "username", instanceName );
        json.put( "content", entry.getTitle() + "\n" + entry.getLink() );

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create( MediaType.parse( "application/json" ), json.toString() );
        Request request = new Request.Builder()
                .url( "https://discord.com/api/webhooks/931275689716490390/CgZsaOro0zcBmWbUkiN8OMGEgtvoe3WvEFyQyNTeL-jvcJHOy9GSa1hZV2tv3S5NUj7t" )
                .post( body )
                .build();

        try {
            client.newCall( request ).execute();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
}
