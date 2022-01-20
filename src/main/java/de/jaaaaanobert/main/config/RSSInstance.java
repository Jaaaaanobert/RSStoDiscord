package de.jaaaaanobert.main.config;

public class RSSInstance {

    public String getInstanceName() {
        return instanceName;
    }

    public String getInstanceFeedURL() {
        return instanceFeedURL;
    }

    public String getInstanceDiscordURL() {
        return instanceDiscordURL;
    }

    private String instanceName;
    private String instanceFeedURL;
    private String instanceDiscordURL;

    public RSSInstance(String instanceName, String instanceFeedURL, String instanceDiscordURL) {
        this.instanceName = instanceName;
        this.instanceFeedURL = instanceFeedURL;
        this.instanceDiscordURL = instanceDiscordURL;
    }

}
