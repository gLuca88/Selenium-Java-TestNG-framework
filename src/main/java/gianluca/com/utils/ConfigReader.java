package gianluca.com.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

	private static Properties properties = new Properties();

	static {
		try {
			// Legge l’ambiente indicato da -Denv=qa
			String env = System.getProperty("env", "qa").toLowerCase();

			// Carica il file corrispondente
			String filePath = String.format("src/main/resources/environments/%s.properties", env);

			FileInputStream fis = new FileInputStream(filePath);
			properties.load(fis);

			System.out.println(">> ENVIRONMENT LOADED: " + env.toUpperCase());

		} catch (Exception e) {
			throw new RuntimeException("Errore nel caricamento dell'ambiente", e);
		}
	}

	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
}