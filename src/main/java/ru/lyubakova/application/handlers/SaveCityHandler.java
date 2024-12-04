package ru.lyubakova.application.handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import ru.lyubakova.application.context.Beans;
import ru.lyubakova.application.viewModels.CityAddWindowController;

public class SaveCityHandler implements EventHandler<MouseEvent> {

    private final CityAddWindowController controller;

    public SaveCityHandler(CityAddWindowController controller) {
        this.controller = controller;
    }

    @Override
    public void handle(MouseEvent event) {
        boolean latitudeIsCorrect = controller.getLatitudeHandler().fieldIsCorrect();
        boolean longitudeIsCorrect = controller.getLongitudeHandler().fieldIsCorrect();
        boolean nameIsCorrect = controller.getNameHandler().fieldIsCorrect();
        boolean populationIsCorrect = controller.getPopulationHandler().fieldIsCorrect();
        if (!(latitudeIsCorrect && longitudeIsCorrect
                && populationIsCorrect && nameIsCorrect)) return;
        controller.getStage().getOnCloseRequest().handle(null);
        controller.getStage().close();
        Beans.getCitiesController().add(controller.createCity());
    }
}
