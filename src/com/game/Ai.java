package com.game;

public class Ai {
    private int[][] squareCost = new int[SquareXO.getFieldSize()][SquareXO.getFieldSize()];
    private int maxI = 0;
    private int maxJ = 0;

    public void erase () {
        for (int i = 0; i < SquareXO.getFieldSize(); i++) {
            for (int j = 0; j < SquareXO.getFieldSize(); j++) {
                squareCost[i][j] = 0;
            }
        }
    }

    public int getMaxI() {
        return maxI;
    }

    public int getMaxJ() {
        return maxJ;
    }

    public void addCost (int i, int j) {
        squareCost[i][j] = 10;
        squareCost[i-1][j]++;
        squareCost[i+1][j]++;
        squareCost[i][j+1]++;
        squareCost[i][j-1]++;
        squareCost[i-1][j-1]++;
        squareCost[i+1][j+1]++;
        squareCost[i-1][j+1]++;
        squareCost[i+1][j-1]++;
    }

    public void findMAx() {
        int max = 0;
        for (int i = 0; i < SquareXO.getFieldSize(); i++) {
            for (int j = 0; j < SquareXO.getFieldSize(); j++) {
                if (squareCost[i][j] > max && squareCost[i][j] != 10) {
                    max = squareCost[i][j];
                    maxI = i;
                    maxJ = j;
                }
            }
        }
    }

}
