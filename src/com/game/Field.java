package com.game;


public class Field {

    private final int toWin;

    private Ai comp;

    private History history;

    private int fieldSize;

    private int arraySize;

    enum Value {
        X,
        O,
    }
    private Value[] field;

    public Field(int size) {
        fieldSize = size;
        arraySize = fieldSize * fieldSize;
        field = new Value[arraySize];
        if (size < 5) {
            toWin = 3;
        } else if (size < 8) {
            toWin = 4;
        } else {
            toWin = 5;
        }
        comp = new Ai(this);

        history = new History(this);

    }

    public int getToWin() {
        return toWin;
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public int getArraySize() {
        return fieldSize * fieldSize;
    }

    public void showField(){
        for (int i = 0; i < arraySize; i++) {
            showCell(i);
            if ((i + 1) % fieldSize == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }

    public void showCell(int k) {
        String s;
        if (field[k] == null) {
            s = (k <= 9 ? " " : "") + String.valueOf(k);
        } else {
            s = field[k] == Value.X ? " X" : " #";
        }
        System.out.print("[ " + s + "]");
    }

    public boolean markField(String line) {
        int squareNum;
        if (line.matches("[0-9]+")) {
            squareNum = Integer.parseInt(line);
            if (squareNum >= arraySize) {
                return false;
            }
            if (field[squareNum] == null) {
                comp.addCost(squareNum);
                makeMove(squareNum, Value.X);
                //comp.showCost();
                history.addMove(squareNum);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean findLine(int i, int dif) {
        int lastPosition = i + dif * (toWin - 1);
        if (lastPosition >= 0 && lastPosition < arraySize) {
            for (int j = 1; j < toWin; j++) {
                int position = i + dif * j;
                if (field[position] != field[i]) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean isWin(char whoMove) {
        System.out.println();
        for (int i = 0; i < arraySize; i++) {
            int column = i % fieldSize;
            if (field[i] != null) {
                if (((fieldSize - column >= toWin) && (findLine(i, 1) || findLine(i, fieldSize + 1) || findLine(i, 1 - fieldSize)))
                        || findLine(i, fieldSize)) {
                    System.out.println(whoMove + " win!");
                    return true;
                }
            }
        }
        return false;
    }

    public void aiMove(){
        comp.findMax();
        makeMove(comp.getMaxI(), Value.O);
        history.addMove(comp.getMaxI());
    }

    private void makeMove(int i, Value k) {
        field[i] = k;
        showField();
        System.out.println();
    }

    public void backToMove (int moveNumber) {
        history.backToMove(moveNumber);
        for (int i = 0; i < arraySize; i++) {
            field[i] = null;
        }
        for (int i = 0; i < history.getArraySize(); i++) {
            field[history.getMove(i)] = i % 2 == 0 ? Value.X : Value.O;
        }
    }
}