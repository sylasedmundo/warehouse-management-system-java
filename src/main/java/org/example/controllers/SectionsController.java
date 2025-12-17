package org.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.example.App;
import org.example.model.Section;
import org.example.model.Storage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SectionsController implements Initializable {

    @FXML
    private ListView<Section> sectionsList;

    @FXML
    private TextField countOfCellsField; // количество ячеек

    @FXML
    private TextField sectionNameField; // название секции

    private Alert alert = new Alert(Alert.AlertType.WARNING);

    private Storage storage;

    //добавление новой секции
    @FXML
    void addSection(MouseEvent event) {
        String name = sectionNameField.getText();
        try {
            if (name.isEmpty() || countOfCellsField.getText().isEmpty()) {
                alert.setTitle("Ошибка добавления секции");
                alert.setContentText("Заполнены не все поля для добавления");
                alert.show();
            } else {
                int countOfCells = Integer.parseInt(countOfCellsField.getText()); // получение количество
                Section section = new Section(name, countOfCells);
                storage.addSection(section);
                sectionsList.getItems().add(section);
                sectionNameField.clear();
                countOfCellsField.clear();
            }
        } catch (Exception e) {
            alert.setTitle("Ошибка добавления секции");
            alert.setContentText("'Количество ячеек' может быть только числовым");
            alert.show();
        }
    }

    // удаление секции
    @FXML
    void removeSection(MouseEvent event) {
        int removedIndex = sectionsList.getSelectionModel().getSelectedIndex();
        if (removedIndex == -1) {
            alert.setTitle("Ошибка удаления секции");
            alert.setContentText("Пожалуйста, выберите секцию, которую хотите удалить");
            alert.show();
        } else {
            Section section = sectionsList.getItems().get(removedIndex);
            int innerIndex = storage.findSection(section);
            storage.removeSection(innerIndex);
            sectionsList.getItems().remove(removedIndex);
        }
    }

    // открыть секцию для просмотра ячеек
    @FXML
    void openSection(MouseEvent event) throws IOException {
        Section selectedItem = sectionsList.getSelectionModel().getSelectedItem(); // получение выбранной секции
        if (selectedItem == null) {
            alert.setTitle("Ошибка открытия секции");
            alert.setContentText("Пожалуйста, выберите секцию, которую хотите открыть");
            alert.show();
        } else {
            storage.setCurrentSection(selectedItem);
            App.setRoot("cells");
        }
    }

    //переход на страницу для сохранения данных файл
    @FXML
    void saveToFile(MouseEvent event) throws IOException {
        App.setRoot("fileSaver");
    }

    //получение данных со склада при загрузке страницы
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        storage = Storage.getStorage();
        sectionsList.getItems().addAll(storage.getSections());
    }
}
