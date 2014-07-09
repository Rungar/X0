package com.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import com.game.SquareXO;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //String name = reader.readLine();
        //System.out.println(name);
        SquareXO mySquare = new SquareXO();
        mySquare.eraseField();
        mySquare.showField();


        int i = 0;
        boolean whoIs = true;
        char who = ' ';
        while (!mySquare.win(who) && i < 9) {
            if (whoIs) {
                who = 'X';
            } else {
                who = 'O';
            }
            System.out.println("Make your move " + who);
            if (mySquare.markField(Integer.parseInt(reader.readLine()),who)) {
                i++;
                whoIs = !whoIs;
            } else {
                System.out.println("Wrong move");
            }
         }

    }
}
