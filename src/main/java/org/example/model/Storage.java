package org.example.model;

import org.example.collections.LinkedList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

//информационный объект склада
public class Storage {

    private static Storage INSTANCE;
    private LinkedList sections = new LinkedList();
    private static final String STORAGE_NAME = "СКЛАД НОМЕР 12";

    private Section currentSection;

    private Storage() {
    }

    public static Storage getStorage() {
        if (INSTANCE == null)
            INSTANCE = new Storage();
        return INSTANCE;
    }

    public void addSection(Section section) {
        sections.add(section);
    }

    public int findSection(Section section) {
        return sections.searchElement(section);
    }

    public void removeSection(int index) {
        sections.remove(index);
    }

    public List<Section> getSections() {
        List<Section> result = new ArrayList<>();
        int i = 0;
        Section section = sections.get(i);
        while (section != null) {
            result.add(section);
            i++;
            section = sections.get(i);
        }
        return result;
    }

    public void setCurrentSection(Section section) {
        this.currentSection = section;
    }

    public Section getCurrentSection() {
        return this.currentSection;
    }


    // для выгрузки данных в файл
    public void upload(String fileName) throws IOException {
        File file = new File(fileName);
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(fileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        int i = 0;
        Section section = sections.get(i);
        while (section != null) {
            section.upload(printWriter);
            i++;
            section = sections.get(i);
        }
        printWriter.flush();
        printWriter.close();
    }

}
