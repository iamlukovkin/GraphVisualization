package ru.lyubakova.application.viewModels;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import lombok.Getter;
import ru.lyubakova.application.context.Beans;
import ru.lyubakova.application.models.City;

import java.util.stream.Stream;

@Getter
public class CityInfoBehaviourController {

    @FXML
    @Getter
    private VBox root;

    @FXML
    private Label population;

    @FXML
    private Text name;

    @FXML
    private Label latitude;

    @FXML
    private Label longitude;

    private City city;

    @Getter
    private boolean isSelected = false;

    public void setCity(City city) {
        root.setOnMouseClicked(event -> changeSelection());
        this.city = city;
        name.setText(city.getName());
        latitude.setText(String.valueOf(city.getLatitude()));
        longitude.setText(String.valueOf(city.getLongitude()));
        population.setText(String.valueOf(city.getPopulation()));
    }

    private void changeSelection() {
        CityInfoBehaviourController[] array = Beans.getCitiesController()
                .getBehaviours()
                .toArray(CityInfoBehaviourController[]::new);
        Stream.of(array).forEach(controller -> controller.setSelected(false));
        setSelected(!isSelected);
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
        root.getStyleClass().setAll(isSelected ? "link-label-pressed" : "link-label");
    }

}
