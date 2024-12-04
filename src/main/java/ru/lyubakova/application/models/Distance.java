package ru.lyubakova.application.models;

import com.brunomnsilva.smartgraph.graphview.SmartLabelSource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Distance {

    private double distance;

    @SmartLabelSource
    public String getDisplayDistance() {
        return String.format("%.2f", distance) + " km";
    }

    @Override
    public String toString() {
        return "Distance{" + "distance=" + getDisplayDistance() + '}';
    }

}