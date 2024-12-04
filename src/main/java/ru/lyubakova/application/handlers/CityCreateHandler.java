package ru.lyubakova.application.handlers;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.Getter;
import ru.lyubakova.application.context.Beans;
import ru.lyubakova.application.viewModels.CityAddWindowController;

@Getter
public class CityCreateHandler implements EventHandler<MouseEvent> {

    @Override
    public void handle(MouseEvent event) {
        Beans.getMainView().setDisable(true);
        createWindow();
    }

    private void createWindow() {
        Stage stage = new Stage();
        Parent window = CityAddWindowController.createWindow(stage);
        stage.setTitle("Добавление города");
        stage.setScene(new Scene(window));
        stage.show();
        stage.setOnCloseRequest(e -> Beans.getMainView().setDisable(false));
    }

}
