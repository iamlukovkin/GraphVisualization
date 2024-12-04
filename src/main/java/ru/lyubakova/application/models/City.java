package ru.lyubakova.application.models;

import com.brunomnsilva.smartgraph.graphview.SmartLabelSource;
import com.brunomnsilva.smartgraph.graphview.SmartRadiusSource;
import com.brunomnsilva.smartgraph.graphview.SmartShapeTypeSource;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class City {

    @JsonProperty("name")
    private String name;

    @JsonProperty("latitude")
    private float latitude;

    @JsonProperty("longitude")
    private float longitude;

    @JsonProperty("population")
    private float population;

    public City() {
        this("", 0, 0, 0);
    }

    public City(String name, float latitude, float longitude, float population) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.population = population;
    }

    @SmartLabelSource
    public String getName() {
        return name;
    }

    @SmartShapeTypeSource
    public String getShape() {
        return "circle";
    }

    @SmartRadiusSource
    public Double getRadius() {
        return convertToLogScale(Double.parseDouble(String.valueOf(this.population)));
    }

    private static double convertToLogScale(double value) {
        double minValue = 1;
        double maxValue = 40;

        double minOutputValue = 4;
        double maxOutputValue = 12;

        double mappedValue = (Math.log(value) - Math.log(minValue)) / (Math.log(maxValue) - Math.log(minValue));

        return minOutputValue + mappedValue * (maxOutputValue - minOutputValue);
    }

    public static double distance(City first, City second) {
        final double EARTH_RADIUS = 6378.1;
        double firstLatitude = first.getLatitude();
        double secondLatitude = second.getLatitude();
        double latitudeInDegree = degreeToRadian(secondLatitude - firstLatitude);
        double longitudeInDegree = degreeToRadian(second.getLongitude() - first.getLongitude());
        double expression = Math.sin(latitudeInDegree / 2) * Math.sin(latitudeInDegree / 2) +
                        Math.cos(degreeToRadian(firstLatitude)) * Math.cos(degreeToRadian(secondLatitude)) *
                                Math.sin(longitudeInDegree / 2) * Math.sin(longitudeInDegree / 2);
        return EARTH_RADIUS * 2 * Math.atan2(Math.sqrt(expression), Math.sqrt(1 - expression));
    }

    private static double degreeToRadian(double degree) {
        return degree * (Math.PI / 180);
    }

    @Override
    public String toString() {
        return "City{" + "name='" + name + '\'' + ", latitude=" + latitude + ", longitude=" + longitude + ", population=" + population + ", shape=" + getShape() + ", radius=" + getRadius() + '}';
    }
}
