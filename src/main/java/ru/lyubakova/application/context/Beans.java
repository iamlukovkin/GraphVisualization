package ru.lyubakova.application.context;

import javafx.scene.Parent;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import ru.lyubakova.application.controllers.CitiesController;
import ru.lyubakova.application.viewModels.GraphViewController;
import ru.lyubakova.application.viewModels.MainController;

public class Beans {

    @Getter
    private static CitiesController citiesController = new CitiesController();

    @Setter
    @Getter
    private static Stage mainStage;

    @Getter
    private static MainController mainController;

    public static void setMainController(MainController mainController) {
        if (Beans.mainController != null) {
            return;
        }
        Beans.mainController = mainController;
    }

    public static Parent getMainView() {
        return mainController.getRoot();
    }

    private static GraphViewController orientedGraphController;

    public static GraphViewController orientedGraphController() {
        if (orientedGraphController == null) {
            orientedGraphController = new GraphViewController();
        }
        return orientedGraphController;
    }

}
