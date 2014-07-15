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
                    moveNumber = backToMove(mySquare);
                } else {
                    if (mySquare.markField(inputLine)) {
                        moveNumber++;
                    } else {
                        System.out.println("Wrong move");
                    }
                }
            } else {
                mySquare.aiMove();
                moveNumber++;
            }

         }

    }

    public static int backToMove(Field square) throws IOException{
        boolean isRight = true;
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        while (isRight) {
            String inputLine = inputReader.readLine();
            if (inputLine.matches("[0-9]+")){
                isRight = false;
                int move = Integer.parseInt(inputLine);
                square.backToMove(move-1);
                System.out.println("Current field position");
                square.showField();
                return move-1;
            } else {
                isRight = true;
                System.out.println("Wrong move number");
            }
        }
        return 0;
    }

    private static boolean isMoveX(int i) {
        return i % 2 == 0;
    }
}
