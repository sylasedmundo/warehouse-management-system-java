package org.example.model;

import org.example.collections.StaticQueue;

import java.io.PrintWriter;
import java.util.List;

//информационный объект секции
public class Section {

    private String number;

    private StaticQueue cells;

    private int countOfCells;

    public Section(String number, int countOfCells) {
        this.number = number;
        this.countOfCells = countOfCells;
        this.cells = new StaticQueue(countOfCells);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        Section section = (Section) obj;
        return this.number.equals(section.number) && this.countOfCells == section.countOfCells;
    }

    public void addCell(Cell cell) {
        this.cells.enqueue(cell);
    }

    public Cell removeCell() {
        return this.cells.dequeue();
    }

    public List<Cell> getCellList() {
        return this.cells.toList();
    }

    public void upload(PrintWriter printWriter) {
        List<Cell> cellList = getCellList();
        printWriter.println("Секция " + this.number + ". Количество ячеек: " + this.countOfCells);
        cellList.forEach(it -> it.upload(printWriter));
    }

    public String getNumber() {
        return this.number;
    }

    public boolean isFullCells() {
        return this.cells.isFull();
    }

    @Override
    public String toString() {
        return "Секция " + number + ". Количество ячеек: " + countOfCells;
    }
}
