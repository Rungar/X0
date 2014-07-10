package com.game;


public class Field {

    private static int toWin = 3;

    private Ai comp;

    private static int fieldSize;

    private static int arraySize;

    enum Value {
        X,
        O
    }
    private Value[] field;

    public Field(int size) {
        fieldSize = size;
        field = new Value[fieldSize * fieldSize];
        if (size > 5 && size < 10) {
            toWin = 4;
        }
        if (size > 9) {
            toWin = 5;
        }
        arraySize = fieldSize * fieldSize;
        comp = new Ai();

    }


    public static int getFieldSize() {
        return fieldSize;
    }

    public static int getArraySize() {
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

    public void showCell(int k){
        String s;
        if (field[k] == null) {
            s = String.valueOf(k);
        } else {
            s = field[k] == Value.X ? "X" : "#";
        }
            if (k <= 9 || field[k] != null) {
                System.out.print("[ " + s + "]");
            } else {
                System.out.print("[" + s + "]");
            }
    }

    public boolean markField(String line) {
        int squareNum = 1;
        if (line.matches("[0-9]+")) {
            squareNum = Integer.parseInt(line);

            if (field[squareNum] == null) {
                field[squareNum] = Value.X;
                comp.addCost(squareNum);
                showField();
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }




    public boolean win(char whoMove) {
        for (int i = 0; i < arraySize; i++) {
            int v = 1;
            int g = 1;
            int d1 = 1;
            int d2 = 1;
            if (field[i] != null) {
                for (int j = 1; j <= toWin; j++) {
                    if(i + j < arraySize) {
                        if (field[i] == field[i+j]) {
                            g++;
                        }
                    }
                    if (i+j*fieldSize + j < arraySize){
                        if (field[i] == field[i+j * fieldSize]) {
                            v++;
                        }
                        if (field[i] == field[i+j*fieldSize + j]) {
                            d1++;
                        }
                        if (field[i]==field[i+j*fieldSize-j]) {
                            d2++;
                        }
                    }
                }
            }
            if (g == toWin || v == toWin || d1 == toWin || d2 == toWin) {
                System.out.println(whoMove + " win!");return true;
            }
        }
        return false;
    }

    public void aiMove(){
        comp.findMAx();
        field[comp.getMaxI()] = Value.O;
        showField();
        System.out.println();
        //comp.showCost();
    }

}
