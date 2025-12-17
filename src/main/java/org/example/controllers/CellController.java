package org.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.example.App;
import org.example.model.Cell;
import org.example.model.Storage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CellController implements Initializable {

    @FXML
    private TextField cellNumber;

    @FXML
    private TextField cellState;

    @FXML
    private Label cellLabel;

    @FXML
    private ListView<Cell> listOfCells;

    private Alert alert = new Alert(Alert.AlertType.WARNING);

    private Storage storage = Storage.getStorage();

    @FXML
    void backToSection(MouseEvent event) throws IOException {
        App.setRoot("sections");
    }

    // создание новой ячейки и добавление ее в секцию
    @FXML
    void createCell(MouseEvent event) {
        if (storage.getCurrentSection().isFullCells()) { // проверка на полноту очереди
            alert.setTitle("Добавление ячейки невозможно");
            alert.setContentText("Количество ячеек превышено");
            alert.show();
        } else {
            if (cellNumber.getText().isEmpty() || cellState.getText().isEmpty()) {
                alert.setTitle("Добавление ячейки невозможно");
                alert.setContentText("Заполните все поля");
                alert.show();
            } else {
                Cell cell = new Cell(cellNumber.getText(), cellState.getText());
                listOfCells.getItems().add(cell);
                storage.getCurrentSection().addCell(cell);
                cellNumber.clear();
                cellState.clear();
            }
        }
    }

    //удаление ячейки из секции. Так как ячейки хранятся в очереди, удаление будет из конца
    @FXML
    void removeCell(MouseEvent event) {
        Cell cell = storage.getCurrentSection().removeCell();
        if (cell == null) {
            alert.setTitle("Ошибка удаления ячейки");
            alert.setContentText("Секция пустая. Удаление ячеек невозможно");
            alert.show();
        } else {
            this.listOfCells.getItems().remove(cell);
        }
    }

    //иницаилазация данных при загрузке страницы. Здесь происходит получения ячеек выбранной секции
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Cell> cellList = storage.getCurrentSection().getCellList();
        listOfCells.getItems().addAll(cellList);
        cellLabel.setText("ЯЧЕЙКИ СЕКЦИИ " + storage.getCurrentSection().getNumber());
    }
}
