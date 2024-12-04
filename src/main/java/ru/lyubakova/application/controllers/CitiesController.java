package ru.lyubakova.application.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.Getter;
import lombok.Setter;
import ru.lyubakova.application.ApplicationRunner;
import ru.lyubakova.application.context.Beans;
import ru.lyubakova.application.models.City;
import ru.lyubakova.application.viewModels.CityInfoBehaviourController;
import ru.lyubakova.application.viewModels.MainController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CitiesController {

    @Setter
    private MainController mainController;

    @Getter
    private final List<City> cities = new ArrayList<>();

    private final List<CityInfoBehaviourController> behaviours = new ArrayList<>();

    public void add(City city) {
        cities.add(city);
        addToView(city);
        addToGraphs(city);
    }

    public void add(List<City> cities) {
        cities.forEach(this::add);
    }

    private void addToGraphs(City city) {
        Beans.orientedGraphController().addNode(city);
    }

    private void addToView(City city) {
        FXMLLoader loader = new FXMLLoader(
                ApplicationRunner.class.getResource("city-info-behaviour.fxml"));
        try {
            Parent parent = loader.load();
            CityInfoBehaviourController controller = loader.getController();
            controller.setCity(city);
            behaviours.add(controller);
            Beans.getMainController().getCityList().getChildren().add(parent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(City city) {
        removeView(city);
        removeNode(city);
        cities.remove(city);
    }

    private void removeNode(City city) {
        Beans.orientedGraphController().removeNode(city);
    }

private void removeView(City city) {
    CityInfoBehaviourController controller = behaviours.stream()
            .filter(behaviour -> behaviour.getCity().equals(city))
            .findFirst()
            .orElse(null);
    if (controller != null) {
        behaviours.remove(controller);
        Beans.getMainController()
                .getCityList()
                .getChildren()
                .remove(controller.getRoot());
    }
}

public void clearAll() {
    cities.forEach(city -> {
        removeView(city);
        removeNode(city);
    });
    cities.clear();
}
}
