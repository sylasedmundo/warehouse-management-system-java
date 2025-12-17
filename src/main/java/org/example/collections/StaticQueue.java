package org.example.collections;

import org.example.model.Cell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StaticQueue {

    private Cell[] elements;
    private int front;
    private int rear;

    public StaticQueue(int length) {
        this.elements = new Cell[length];
        this.front = -1;
        this.rear = -1;
    }

    //добавление нового элемента
    public void enqueue(Cell element) {
        if (isFull()) {
            System.err.println("Очередь полная");
        } else {
            if (front == -1) //проверка, если элемент добавляется в пустую очередь
                front = 0;
            rear = (rear + 1) % elements.length; // подсчет индекса для добавления
            elements[rear] = element;
        }
    }

    //удаление элемента из начала очереди
    public Cell dequeue() {
        if (isEmpty()) { // проверка на пустоту очереди
            System.err.println("Очередь пустая");
            return null;
        }
        Cell result = elements[front];
        if (front == rear) { // если элемент был единственным в очереди
            front = -1;
            rear = -1;
        } else {
            front = (front + 1) % elements.length; // подсчет индекса дл удаления
        }
        return result;
    }

    //проверка на полноту очереди
    public boolean isFull() {
        return (this.front == 0 && this.rear == this.elements.length - 1)
                || ((this.rear + 1) % this.elements.length == front);
    }

    //проверка на пустоту очереди
    public boolean isEmpty() {
        return this.front == -1;
    }


    public List<Cell> toList() {
        if (isEmpty())
            return Collections.emptyList();
        List<Cell> result = new ArrayList<>();
        int i;
        for (i = front; i != rear; i = (i + 1) % elements.length) {
            result.add(elements[i]);
        }
        result.add(elements[i]);
        return result;
    }

}
