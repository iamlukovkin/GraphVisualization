package ru.lyubakova.application.handlers;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class InputNameHandler implements EventHandler<KeyEvent>, FieldCheck {

    private final TextField field;
    private final Text error;

    public InputNameHandler(TextField field, Text error) {
        this.field = field;
        this.error = error;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        fieldIsCorrect();
    }

    public boolean fieldIsCorrect() {
        error.setVisible(false);
        String name = field.getText();
        if (name.isEmpty()) {
            error.setText("Название не может быть пустым");
            error.setVisible(true);
        }
        return !error.isVisible();
    }
}
