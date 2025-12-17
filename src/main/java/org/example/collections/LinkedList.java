package org.example.collections;

import org.example.model.Section;

public class LinkedList {

    //внутренний класс, содержащий информационную часть и ссылки на предшественника и последующий элемень
    private class Node {
        Node next;
        Node prev;
        Section element;

        public Node(Section element) {
            this.element = element;
        }
    }

    //ссылка на первый элемент списка
    private Node first;

    //ссылка на последний элемент списка
    private Node last;
    private int size;

    public LinkedList() {
        size = 0;
    }


    //добавление в конец списка
    public void add(Section element) {
        Node node = new Node(element);
        if (first == null) {
            first = last = node;
        } else {
            last.next = node; // замена ссылок у последнего элемента, таким образом новый элемент становится последним
            node.prev = last;
            last = node;
        }
        size++;
    }

    public void addBefore(int index, Section element)  {
        if (index < 0 || index > size) {
            System.err.println("Некорректный индекс");
        } else {
            Node newNode = new Node(element);
            Node nextNode = node(index); // поиск элемента, перед которым требуется добавить элемент
            if (nextNode == null) { // если список пуст и добавляется первый элемент
                first = last = newNode;
            } else {
                newNode.next = nextNode;
                newNode.prev = nextNode.prev;
                if (nextNode.prev == null) { // признак того, что добавляемый элемент становится первым
                    first = newNode;
                } else {
                    nextNode.prev.next = newNode;
                    newNode.prev = newNode;
                }
                nextNode.prev = newNode;
            }
            size++;
        }
    }

    public void addAfter(int index, Section element) {
        if (index < 0 || index > size) {
            System.err.println("Некорректный индекс");
        } else {
            Node newNode = new Node(element);
            Node prevNode = node(index);
            if (prevNode == null) {
                first = last = newNode;
            } else {
                newNode.prev = prevNode;

                if (prevNode.next == null) { // признак того, что новый элемент становится последним
                    last = newNode;
                } else {
                    newNode.next = prevNode.next;
                    prevNode.next.prev = newNode;
                }
                prevNode.next = newNode;
            }
            size++;
        }
    }

    //удаление элемента, для этого необходимо просто заменить ссылки и у предыдущего и последующего элемента
    public void remove(int index) {
        if (index < 0 || index > size) {
            System.err.println("Неккоректный индекс");
        } else {
            Node node = node(index);
            if (node.prev == null) {
                first = node.next;
            } else {
                node.prev.next = node.next;
                node.prev = null;
            }

            if (node.next == null) {
                last = node.prev;
            } else {
                node.next.prev = node.prev;
                node.next = null;
            }
            size--;
        }
    }

    //поиск элемента путем прохода по ссылкам, начиная с начального элемента. Сравнение осуществляется с помощью метода equals
    public int searchElement(Section value) {
        Node temp = first;
        int index = 0;
        while (temp != null) {
            if (temp.element.equals(value))
                return index;
            index++;
            temp = temp.next;
        }
        return -1;
    }

    //получение ноды по индексу
    private Node node(int index) {
        Node temp = first;
        int i = 0;
        while (temp != null) {
            if (i == index)
                return temp;
            i++;
            temp = temp.next;
        }
        return null;
    }

    public Section get(int index) {
        if (index >= size || size == 0)
            return null;
        return node(index).element;
    }

}
