package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private int countThreads;

    public Config() {
        Properties prop = new Properties();

        try (InputStream stream = Config.class.getResourceAsStream("/app.properties")) {
            prop.load(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.countThreads = Integer.parseInt(prop.getProperty("countThreads"));
    }

    public int getCountThreads() {
        return countThreads;
    }
}
