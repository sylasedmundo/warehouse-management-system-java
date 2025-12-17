package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.example.App;
import org.example.model.Storage;

import java.io.IOException;

public class FileSaverController {

    @FXML
    private TextField fileName;

    private Alert alert = new Alert(Alert.AlertType.WARNING);

    private Storage storage = Storage.getStorage();

    // возврат на страницу с секциями
    @FXML
    void backToSection(MouseEvent event) throws IOException {
        App.setRoot("sections");
    }

    //обработка события по клику на кнопку сохранения в файл
    @FXML
    void saveToFile(MouseEvent event) {
        if (fileName.getText().isEmpty()) {
            alert.setTitle("Ошибка сохранения в файл");
            alert.setContentText("Пожалуйста введите файл, в который нужно сохранить");
            alert.show();
        } else {
            try {
                storage.upload(fileName.getText());
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Сохранение в файл");
                alert1.setContentText("Сохранение в файл " + fileName.getText() + " прошло успешно");
                alert1.show();
            } catch (Exception e) {
                alert.setTitle("Ошибка сохранения в файл");
                alert.setContentText("Произошла ошибка во время сохранения данных в файл");
                alert.show();
            }
        }
    }
}
