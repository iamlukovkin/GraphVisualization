package ru.lyubakova.application.handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import ru.lyubakova.application.context.Beans;
import ru.lyubakova.application.controllers.CitiesController;
import ru.lyubakova.application.models.City;
import ru.lyubakova.application.utils.FileServlet;
import ru.lyubakova.application.utils.JsonReader;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ReadJsonHandler implements EventHandler<MouseEvent> {
    @Override
    public void handle(MouseEvent event) {
        File file = FileServlet.selectJsonFile();
        if (file != null) {
            try {
                List<City> cities = JsonReader.readFile(file);
                CitiesController citiesController = Beans.getCitiesController();
                citiesController.clearAll();
                citiesController.add(cities);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
