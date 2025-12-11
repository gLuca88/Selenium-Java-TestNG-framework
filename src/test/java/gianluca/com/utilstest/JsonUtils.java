package gianluca.com.utilstest;

import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

public class JsonUtils {

	private static final ObjectMapper mapper = new ObjectMapper();

	/**
	 * Legge un singolo oggetto JSON da resources e lo mappa su un POJO.
	 */
	public static <T> T readJsonFromResources(String resourcePath, Class<T> clazz) {
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream inputStream = classLoader.getResourceAsStream(resourcePath);

			if (inputStream == null) {
				throw new RuntimeException("File JSON non trovato nel classpath: " + resourcePath);
			}

			return mapper.readValue(inputStream, clazz);

		} catch (Exception e) {
			throw new RuntimeException("Errore nella lettura del JSON: " + resourcePath, e);
		}
	}

	/**
	 * Legge un array JSON da resources e lo converte in una List<T>.
	 */
	public static <T> List<T> readJsonArray(String resourcePath, Class<T> clazz) {
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream inputStream = classLoader.getResourceAsStream(resourcePath);

			if (inputStream == null) {
				throw new RuntimeException("File JSON non trovato nel classpath: " + resourcePath);
			}

			// Costruisce dinamicamente il tipo List<T>
			CollectionType listType = mapper.getTypeFactory().constructCollectionType(List.class, clazz);

			return mapper.readValue(inputStream, listType);

		} catch (Exception e) {
			throw new RuntimeException("Errore nella lettura dell'array JSON: " + resourcePath, e);
		}
	}
}
