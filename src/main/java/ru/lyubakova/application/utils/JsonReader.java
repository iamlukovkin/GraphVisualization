package ru.lyubakova.application.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.lyubakova.application.models.City;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonReader {

    public static List<City> readFile(File file) throws IOException {
        City[] cities = (new ObjectMapper()).readValue(file, City[].class);
        return List.of(cities);
    }
}
