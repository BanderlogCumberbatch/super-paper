package org.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

/**
 * Класс предоставляет параметры конфигурации из файлов, установленных в system properties
 */
public final class ParametersProvider {

    /**
     * Список параметров
     */
    private final ArrayList<Properties> propertiesList = new ArrayList<>();

    /**
     * Получить название конфигурационного файла из system properties.
     * @return список названий конфигурационных файлов
     */
    private static ArrayList<String> getConfigFileNames() {
        ArrayList<String> configFileNames = new ArrayList<>();
        for (String key: System.getProperties().stringPropertyNames()) {
            if (key.startsWith("config.location")) {
                String[] fileNames = System.getProperties().getProperty(key)
                        .split(";");
                configFileNames.addAll(Arrays.asList(fileNames));
            }
        }
        System.out.println(configFileNames);
        return configFileNames;
    }

    /**
     * helpers.ParametersProvider конструктор.
     * @throws IOException когда невозможно получить конфиги
     */
    private ParametersProvider() throws IOException {
        ArrayList<String> configFileNames = getConfigFileNames();
        for (String fileName: configFileNames) {
            Properties properties = new Properties();
            properties.loadFromXML(new FileInputStream(fileName));
            propertiesList.add(properties);
        }
    }

    /**
     * helpers.ParametersProvider instance holder
     */
    private static ParametersProvider instance;

    /**
     * helpers.ParametersProvider геттер.
     * @return helpers.ParametersProvider instance
     * @throws IOException когда невозможно получить конфиги
     */
    private static ParametersProvider getInstance() throws IOException {
        if (instance == null) {
            instance = new ParametersProvider();
        }
        return instance;
    }

    /**
     * Получить параметры из списка по их ключу.
     * @param key параметра, найденного в конфиге
     * @return параметр или пустую строку, если он не был найден
     * @throws IOException когда невозможно получить конфиги
     */
    public static String getProperty(final String key) throws IOException {
        for (Properties properties: getInstance().propertiesList) {
            String result = properties.getProperty(key, null);
            if (result != null) {
                return result;
            }
        }
        return "";
    }
}
