package org.example.model;

import java.io.PrintWriter;

//информационный объект ячейки
public class Cell {

    private String number;

    private String state;


    public Cell(String number, String state) {
        this.number = number;
        this.state = state;
    }

    public String getNumber() {
        return this.number;
    }

    public String getState() {
        return this.state;
    }

    public void upload(PrintWriter printWriter) {
        printWriter.println("Ячейка " + this.number + ". Состояние: " + this.state);
    }

    @Override
    public String toString() {
        return "Ячейка " + this.number + ". Состояние " + this.state;
    }
}
