package ru.lyubakova.application.viewModels;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import lombok.Getter;
import ru.lyubakova.application.context.Beans;
import ru.lyubakova.application.context.Handlers;
import ru.lyubakova.application.handlers.*;

public class MainController {

    @FXML
    @Getter
    private VBox root;

    @FXML
    private BorderPane graphs;

    @FXML
    private VBox saveJson;

    @FXML
    private Button clear;

    @FXML
    private Button readJson;
    @FXML
    private Button add;

    @FXML
    private Button delete;

    @FXML
    @Getter
    private VBox cityList;

    @FXML
    public void initialize() {
        setHandlers();
        graphs.setCenter(Beans.orientedGraphController().getContainer());
    }

    private void setHandlers() {
        add.setOnMouseClicked(Handlers.cityCreateHandler());
        delete.setOnMouseClicked(new DeleteCityHandler());
        clear.setOnMouseClicked(new ClearCityHandler());
        readJson.setOnMouseClicked(new ReadJsonHandler());
        saveJson.setOnMouseClicked(new SaveJsonHandler());
    }

}
