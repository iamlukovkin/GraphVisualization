package ru.lyubakova.application.viewModels;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import ru.lyubakova.application.ApplicationRunner;
import ru.lyubakova.application.handlers.InputCoordinateHandler;
import ru.lyubakova.application.handlers.InputIntegerHandler;
import ru.lyubakova.application.handlers.InputNameHandler;
import ru.lyubakova.application.handlers.SaveCityHandler;
import ru.lyubakova.application.models.City;

import java.io.IOException;

public class CityAddWindowController {


    @FXML
    private TextField populationField;

    @FXML
    private Text errorPopulation;

    @Setter
    @Getter
    private Stage stage;

    @FXML
    private Button save;

    @FXML
    private Text errorLongitude;

    @FXML
    private Text errorLatitude;

    @FXML
    private Text errorName;

    @FXML
    private TextField cityNameField;

    @FXML
    private TextField latitudeField;

    @FXML
    private TextField longitudeField;

    @Getter
    private InputCoordinateHandler latitudeHandler;

    @Getter
    private InputCoordinateHandler longitudeHandler;

    @Getter
    private InputNameHandler nameHandler;

    @Getter
    private InputIntegerHandler populationHandler;

    @FXML
    public void initialize() {
        latitudeHandler = new InputCoordinateHandler(latitudeField, errorLatitude);
        longitudeHandler = new InputCoordinateHandler(longitudeField, errorLongitude);
        nameHandler = new InputNameHandler(cityNameField, errorName);
        populationHandler = new InputIntegerHandler(populationField, errorPopulation);
        save.setOnMouseClicked(new SaveCityHandler(this));
        checkButton();
        populationField.setOnKeyReleased(keyEvent -> {
            populationHandler.handle(keyEvent);
            checkButton();
        });
        latitudeField.setOnKeyReleased(keyEvent -> {
            latitudeHandler.handle(keyEvent);
            checkButton();
        });
        longitudeField.setOnKeyReleased(keyEvent -> {
            longitudeHandler.handle(keyEvent);
            checkButton();
        });
        cityNameField.setOnKeyReleased(keyEvent -> {
            nameHandler.handle(keyEvent);
            checkButton();
        });
    }

    private void checkButton() {
        save.setDisable(!(
                latitudeHandler.fieldIsCorrect() &&
                        longitudeHandler.fieldIsCorrect() &&
                        nameHandler.fieldIsCorrect()
        ));
    }

    public static Parent createWindow(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    ApplicationRunner.class.getResource("city-add-window.fxml"));
            Parent parent = loader.load();
            CityAddWindowController controller = loader.getController();
            controller.setStage(stage);
            return parent;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public City createCity() {
        return new City(
                cityNameField.getText(),
                Float.parseFloat(latitudeField.getText()),
                Float.parseFloat(longitudeField.getText()),
                Integer.parseInt(populationField.getText())
        );
    }
}
