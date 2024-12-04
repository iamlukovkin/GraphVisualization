package ru.lyubakova.application.handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import ru.lyubakova.application.context.Beans;
import ru.lyubakova.application.controllers.CitiesController;
import ru.lyubakova.application.models.City;
import ru.lyubakova.application.viewModels.CityInfoBehaviourController;

import java.util.List;

public class DeleteCityHandler implements EventHandler<MouseEvent> {
    @Override
    public void handle(MouseEvent event) {
        CitiesController citiesController = Beans.getCitiesController();
        List<CityInfoBehaviourController> behaviours = citiesController.getBehaviours();
        CityInfoBehaviourController behaviourController = behaviours.stream()
                .filter(CityInfoBehaviourController::isSelected)
                .findFirst()
                .orElse(null);
        if (behaviourController == null) {
            return;
        }
        City city = behaviourController.getCity();
        citiesController.remove(city);
    }
}
