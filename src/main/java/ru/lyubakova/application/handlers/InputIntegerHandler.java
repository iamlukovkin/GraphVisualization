package ru.lyubakova.application.handlers;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class InputIntegerHandler implements EventHandler<KeyEvent>, FieldCheck {

    private final TextField field;
    private final Text error;

    public InputIntegerHandler(TextField field, Text error) {
        this.field = field;
        this.error = error;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        fieldIsCorrect();
    }

    @Override
    public boolean fieldIsCorrect() {
        error.setVisible(false);
        try {
            Integer.parseInt(field.getText());
        } catch (NumberFormatException e) {
            error.setText("Некорректное значение");
            error.setVisible(true);
        }
        return !error.isVisible();
    }
}
