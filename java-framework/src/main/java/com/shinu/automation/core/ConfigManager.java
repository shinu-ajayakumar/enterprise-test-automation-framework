package com.shinu.automation.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Reads settings for the chosen environment (-Denv=qa|local, default qa).
 * Lookup order for every key: -D system property  >  environment variable  >  config/<env>.properties
 * e.g. -Dbrowser=edge overrides browser=chrome from the file.
 */
public final class ConfigManager {

    private static final String ENV = System.getProperty("env", "qa").toLowerCase();
    private static final Properties PROPS = load(ENV);

    private static final Logger LOG = LogManager.getLogger(ConfigManager.class);

    static {
        LOG.info("Environment='{}' baseUrl='{}' browser='{}' headless='{}'",
                ENV, get("base.url"), get("browser"), get("headless"));
    }

    private ConfigManager() {
    }

    private static Properties load(String env) {
        String path = "config/" + env + ".properties";
        Properties props = new Properties();
        try (InputStream in = ConfigManager.class.getClassLoader().getResourceAsStream(path)) {
            if (in == null) {
                throw new IllegalStateException("Config file not found on classpath: " + path);
            }
            props.load(in);
        } catch (IOException e) {
            throw new IllegalStateException("Could not read " + path, e);
        }
        return props;
    }

    public static String get(String key) {
        String fromSystem = System.getProperty(key);
        if (fromSystem != null) return fromSystem;

        String fromEnvVar = System.getenv(key.toUpperCase().replace('.', '_'));   // base.url -> BASE_URL
        if (fromEnvVar != null) return fromEnvVar;

        String fromFile = PROPS.getProperty(key);
        if (fromFile != null) return fromFile.trim();

        throw new IllegalStateException("Missing config key '" + key + "' for env '" + ENV + "'");
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }

    public static String env() {
        return ENV;
    }
}