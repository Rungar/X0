package com.game;

import java.util.ArrayList;

public class History {
    private int arraySize;

    public History(Field field) {
        arraySize = field.getArraySize();
    }

    private ArrayList <Integer> array= new ArrayList<Integer>(arraySize);

    public void addMove(int currentMove) {
        array.add(currentMove);
    }

    public void delMove() {
        array.remove(array.size() - 1);
    }

    public void backToMove(int i) {
        while (array.size() > i) {
            delMove();
        }
    }

    public int getMove(int i) {
        return array.get(i);
    }


    public int getArraySize() {
        return array.size();
    }
}
