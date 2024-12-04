package ru.lyubakova.application.handlers;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import lombok.Getter;

public class InputCoordinateHandler implements EventHandler<KeyEvent>, FieldCheck {

    @Getter
    private final TextField field;

    private final Text error;

    public InputCoordinateHandler(TextField field, Text error) {
        this.field = field;
        this.error = error;
    }

    @Override
    public void handle(KeyEvent event) {
        fieldIsCorrect();
    }

    public boolean fieldIsCorrect() {
        error.setVisible(false);
        try {
            float coordinate = Float.parseFloat(field.getText());
            if (coordinate < -180 || coordinate > 180) {
                error.setText("Значение должно быть в диапазоне от -180 до 180");
                error.setVisible(true);
            }
        } catch (NumberFormatException e) {
            error.setText("Некорректное значение");
            error.setVisible(true);
        }
        return !error.isVisible();
    }
}
