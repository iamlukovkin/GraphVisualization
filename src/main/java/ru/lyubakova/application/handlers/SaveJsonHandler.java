package ru.lyubakova.application.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import ru.lyubakova.application.context.Beans;
import ru.lyubakova.application.models.City;
import ru.lyubakova.application.utils.DistanceCalculator;
import ru.lyubakova.application.utils.TravelingSalesmanProblemSolver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SaveJsonHandler implements EventHandler<MouseEvent> {
    @Override
    public void handle(MouseEvent event) {
        Stage stage = new Stage();
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Выберите папку для сохранения");
        File file = chooser.showDialog(stage);
        saveFile(file);
    }

    private static void saveFile(File file) {
        if (file != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                objectMapper.writeValue(
                        new File(file, "cities.json"),
                        findPath());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static List<City> findPath() {
        List<City> cities = Beans.getCitiesController().getCities();
        List<List<Double>> distances = DistanceCalculator.makeDistances(cities);
        int[] path = TravelingSalesmanProblemSolver.nearestNeighbor(
                TravelingSalesmanProblemSolver.toMatrix(distances), 0);
        List<City> citiesPath = new ArrayList<>();
        for (int i : path) {
            citiesPath.add(cities.get(i));
        }
        return citiesPath;
    }
}
