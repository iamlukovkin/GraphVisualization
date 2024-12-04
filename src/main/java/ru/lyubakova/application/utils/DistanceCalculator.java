package ru.lyubakova.application.utils;

import ru.lyubakova.application.models.City;

import java.util.ArrayList;
import java.util.List;

public class DistanceCalculator {

    public static List<List<Double>> makeDistances(List<City> cities) {
        List<List<Double>> matrix = new ArrayList<>();
        for (City left : cities) {
            List<Double> row = new ArrayList<>();
            for (City right : cities) {
                row.add(City.distance(left, right));
            }
            matrix.add(row);
        }
        return matrix;
    }

}
