package ru.lyubakova.application.utils;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class FileServlet {

    public static File selectJsonFile() {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON", "*.json"));
        chooser.setTitle("Выберите JSON-файл");
        return chooser.showOpenDialog(new Stage());
    }
}
