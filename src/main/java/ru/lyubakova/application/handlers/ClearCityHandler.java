package ru.lyubakova.application.handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import ru.lyubakova.application.context.Beans;

public class ClearCityHandler implements EventHandler<MouseEvent> {
    @Override
    public void handle(MouseEvent event) {
        Beans.getCitiesController()
                .clearAll();
    }
}
