package ru.lyubakova.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.lyubakova.application.context.Beans;

public class ApplicationRunner extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Beans.setMainStage(stage);
        FXMLLoader loader = new FXMLLoader(ApplicationRunner.class.getResource("main.fxml"));
        Parent parent = loader.load();
        Beans.setMainController(loader.getController());
        stage.setScene(new Scene(parent, 1280, 720));
        stage.show();
        Beans.orientedGraphController().getPanel().init();
    }
}
