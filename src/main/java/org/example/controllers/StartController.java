package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.example.App;
import org.example.StorageLoader;

import java.io.IOException;

public class StartController {

    @FXML
    private TextField storageFile;

    private Alert alert = new Alert(Alert.AlertType.WARNING);

    @FXML
    void emptyStorage(MouseEvent event) throws IOException {
        App.setRoot("sections");
    }

    // обработка клика по кнопку для загрузки данных склада из файла
    @FXML
    void loadStorage(MouseEvent event) {
        if (storageFile.getText().isEmpty()) { //проверка того, что поле с файлом не пусто
            alert.setTitle("Ошибка загрузки данных");
            alert.setContentText("Пожалуйста, введите путь до файла");
            alert.show();
        } else {
            try {
                StorageLoader.loadStorage(storageFile.getText()); // загрузка данных из файла
                App.setRoot("sections"); // открытие главного окна
            } catch (Exception e) {
                alert.setTitle("Ошибка загрузки данных");
                alert.setContentText("Во время загрузки информации из файла произошла ошибка считывания файла. Пожалуйста, проверьте наличие файла и его корректность");
                alert.show();
            }
        }
    }

}
