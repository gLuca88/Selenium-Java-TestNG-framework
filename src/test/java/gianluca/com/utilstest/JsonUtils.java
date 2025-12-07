package gianluca.com.utilstest;

import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

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
}
