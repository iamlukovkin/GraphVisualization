module ru.lyubakova.application {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires com.fasterxml.jackson.databind;
    requires smartgraph;
    requires java.desktop;

    opens ru.lyubakova.application to javafx.fxml;
    exports ru.lyubakova.application;
    exports ru.lyubakova.application.models;
    exports ru.lyubakova.application.viewModels;
    opens ru.lyubakova.application.viewModels to javafx.fxml;
    exports ru.lyubakova.application.utils;
    opens ru.lyubakova.application.utils to com.fasterxml.jackson.databind;
    opens ru.lyubakova.application.models to javafx.fxml;
    exports ru.lyubakova.application.handlers;
    opens ru.lyubakova.application.handlers to javafx.fxml;
}