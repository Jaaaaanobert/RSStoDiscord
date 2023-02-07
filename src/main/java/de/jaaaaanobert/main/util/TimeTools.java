package de.jaaaaanobert.main.util;

import de.jaaaaanobert.main.config.ConfigFileReader;

public class TimeTools {

    public long getUnixTime() {
        return System.currentTimeMillis();
    }

    public boolean entryIsNew(long unixTime) {
        return ConfigFileReader.getInstance().getLatestSyncTime() < unixTime;
    }
}
