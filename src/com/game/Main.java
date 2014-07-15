package com.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String size;

        boolean notCorrect = true;
        int sizeOfField = 3;

        while (notCorrect) {
            System.out.println("Input field size");
            size = reader.readLine();
            if (size.matches("[0-9]+") && Integer.parseInt(size) > 2) {
                sizeOfField = Integer.parseInt(size);
                notCorrect = false;
            } else {
                System.out.println("Wrong size input");
                notCorrect = true;
            }
        }

        Field mySquare = new Field(sizeOfField);
        mySquare.showField();

        System.out.println("To win on this field you must fill " + mySquare.getToWin()+" cells in line ");


        int moveNumber = 0;

        char whoMove = ' ';
        System.out.println("To make a move input a number of cell, where you want to move");
        System.out.println("If you want back to some move input the key 'z', press Enter, and then input number of move, where you want to come back");

        while (!mySquare.isWin(whoMove) && moveNumber < mySquare.getArraySize()) {
            if (isMoveX(moveNumber)) {
                whoMove = 'X';
            } else {
                whoMove = 'O';
            }
            System.out.println("" + (moveNumber + 1) + ") Make your move " + whoMove);
            if (whoMove == 'X'){
                String inputLine = reader.readLine();
                if (inputLine.equals("z")) {
                    backToMove();
                }
                if (mySquare.markField(inputLine)) {
                    moveNumber++;
            } else {
                    System.out.println("Wrong move");
                }
            } else {
                mySquare.aiMove();
                moveNumber++;
            }

         }

    }

  

    private static boolean isMoveX(int i) {
        return i % 2 == 0;
    }
}
