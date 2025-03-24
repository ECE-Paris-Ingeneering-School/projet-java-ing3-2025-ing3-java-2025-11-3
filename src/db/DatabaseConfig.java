package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class DatabaseConfig {

    private static final String FILE_PATH = "config/db.properties";
    private static final Properties props = new Properties();

    static {
        try (InputStream input = new FileInputStream(FILE_PATH)) {
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Impossible de charger la configuration DB", e);
        }
    }

    public static String getUrl() {
        return props.getProperty("db.url");
    }

    public static String getUsername() {
        return props.getProperty("db.username");
    }

    public static String getPassword() {
        return props.getProperty("db.password");
    }
}
