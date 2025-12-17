package org.example;

import org.example.model.Cell;
import org.example.model.Section;
import org.example.model.Storage;

import java.io.File;
import java.util.Scanner;


//класс для загрузки данных с файла
public class StorageLoader {

    public static void loadStorage(String fileName) throws Exception {
        File file = new File(fileName);
        if (!file.exists()) { // проверка на существования файла
            throw new Exception();
        }
        Scanner scanner = new Scanner(file); // создание сканнера для считывания данных
        Storage storage = Storage.getStorage();
        Section section = null;
        while (scanner.hasNextLine()) {
            // процесс обработки каждой строки путем поиска ключевых словы и деления строки на слова
            String s = scanner.nextLine();
            if (s.startsWith("Секция")) {
                String[] words = s.split(" ");
                String sectionName = words[1];
                int countOfCells = Integer.parseInt(words[words.length - 1]);
                section = new Section(sectionName, countOfCells);
                storage.addSection(section);
            } else if (s.startsWith("Ячейка")) {
                if (section == null)
                    throw new Exception();
                String[] strs = s.split(" ");
                Cell cell = new Cell(strs[1], strs[strs.length -1]);
                if (section.isFullCells())
                    throw new Exception();
                section.addCell(cell);
            }
        }
    }
}
