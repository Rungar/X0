package com.game;


public class SquareXO {

    private static final int FIELD_SIZE = 3;

    private static final char DEFAULT_CELL_VALUE = ' ';

    private char[][] field = new char[FIELD_SIZE][FIELD_SIZE];



    public void eraseField() {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                field[i][j] = DEFAULT_CELL_VALUE;
            }
        }
    }


    public void showField(){
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                showCell(i, j);
            }
            System.out.println();
        }
    }

    public void showCell(int k, int l){
        System.out.print("[" + field[k][l] + "]");
    }

    public boolean markField(int squareNum, char whoMove) {
        int i;
        int j;
        switch (squareNum) {
            case 1 : i = 2; j = 0; break;
            case 2 : i = 2; j = 1; break;
            case 3 : i = 2; j = 2; break;
            case 4 : i = 1; j = 0; break;
            case 5 : i = 1; j = 1; break;
            case 6 : i = 1; j = 2; break;
            case 7 : i = 0; j = 0; break;
            case 8 : i = 0; j = 1; break;
            case 9 : i = 0; j = 2; break;
            default:
                System.out.println("Wrong input"); return false;
        }
        if (field[i][j] == DEFAULT_CELL_VALUE) {
            field[i][j] = whoMove;
            showField();
            return true;
        } else {
            return false;
        }

    }

    public boolean win(char whoMove) {
        if (field[0][0] == field[0][1] && field[0][0] == field[0][2] && !(field[0][0] == DEFAULT_CELL_VALUE)) {
            System.out.println(whoMove + " win!");
            return true;
        }
        if (field[0][0] == field[1][1] && field[2][2] == field[0][0] && !(field[0][0] == DEFAULT_CELL_VALUE)) {
            System.out.println(whoMove + " win!");
            return true;
        }
        if (field[0][0] == field[1][0] && field[0][0] == field[2][0] && !(field[0][0] == DEFAULT_CELL_VALUE)) {
            System.out.println(whoMove + " win!");
            return true;
        }
        if (field[0][1] == field[1][1] && field[0][1] == field[2][1] && !(field[0][1] == DEFAULT_CELL_VALUE)) {
            System.out.println(whoMove + " win!");
            return true;
        }
        if (field[0][2] == field[1][1] && field[1][1] == field[2][0] && !(field[0][2] == DEFAULT_CELL_VALUE)) {
            System.out.println(whoMove + " win!");
            return true;
        }
        if (field[0][2] == field[1][2] && field[1][2] == field[2][2] && !(field[0][2] == DEFAULT_CELL_VALUE)) {
            System.out.println(whoMove + " win!");
            return true;
        }
        if (field[1][0] == field[1][1] && field[1][1] == field[1][2] && !(field[1][0] == DEFAULT_CELL_VALUE)) {
            System.out.println(whoMove + " win!");
            return true;
        }
        if (field[2][0] == field[2][1] && field[2][1] == field[2][2] && !(field[2][0] == DEFAULT_CELL_VALUE)) {
            System.out.println(whoMove + " win!");
            return true;
        }
        return false;
    }

}
