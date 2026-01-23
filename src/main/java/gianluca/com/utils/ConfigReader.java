package gianluca.com.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

	private static final Properties properties = new Properties();

	static {
		loadEnvironmentProperties();
	}

	private ConfigReader() {
		// utility class
	}

	private static void loadEnvironmentProperties() {
		String env = System.getProperty("env", "qa").toLowerCase();
		String resourcePath = "environments/" + env + ".properties";

		try (InputStream is = ConfigReader.class.getClassLoader().getResourceAsStream(resourcePath)) {

			if (is == null) {
				throw new RuntimeException("File di configurazione non trovato: " + resourcePath);
			}

			properties.load(is);
			System.out.println(">> ENVIRONMENT LOADED: " + env.toUpperCase());

		} catch (IOException e) {
			throw new RuntimeException("Errore nel caricamento dell'ambiente: " + env, e);
		}
	}

	public static String getProperty(String key) {
		String sysValue = System.getProperty(key);
		if (sysValue != null && !sysValue.isBlank()) {
			return sysValue;
		}

		String value = properties.getProperty(key);
		if (value == null) {
			throw new RuntimeException("Proprietà mancante: " + key);
		}

		return value;
	}
}