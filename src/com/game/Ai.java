package com.game;

public class Ai {

    public static final int DEFAULT_SQUAREX_COST = 10;
    public static final int DEFAULT_SQUAREO_COST = 50;
    public static final int WHERE_TO_MOVE_COST = 20;


    private int[][] squareCost = new int[SquareXO.getFieldSize() + 2][SquareXO.getFieldSize() + 2];
    private int maxI = 0;
    private int maxJ = 0;

    public void erase () {
        for (int i = 0; i < (SquareXO.getFieldSize() + 2); i++) {
            for (int j = 0; j < (SquareXO.getFieldSize() + 2); j++) {
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

    public void addCost (int i1, int j1) {
        int i = i1 + 1;
        int j = j1 + 1;
        squareCost[i][j] = DEFAULT_SQUAREX_COST;
        if (squareCost[i-1][j] !=DEFAULT_SQUAREX_COST && squareCost[i-1][j] != DEFAULT_SQUAREO_COST) {
            squareCost[i-1][j]++;
        }
        if (squareCost[i+1][j] != DEFAULT_SQUAREX_COST && squareCost[i+1][j] != DEFAULT_SQUAREO_COST) {
            squareCost[i+1][j]++;
        }
        if (squareCost[i][j+1] != DEFAULT_SQUAREX_COST && squareCost[i][j+1] != DEFAULT_SQUAREO_COST) {
            squareCost[i][j+1]++;
        }
        if (squareCost[i][j-1] != DEFAULT_SQUAREX_COST && squareCost[i][j-1] != DEFAULT_SQUAREO_COST) {
            squareCost[i][j-1]++;
        }
        if (squareCost[i-1][j-1] != DEFAULT_SQUAREX_COST && squareCost[i-1][j-1] != DEFAULT_SQUAREO_COST) {
            squareCost[i-1][j-1]++;
        }
        if (squareCost[i+1][j+1] != DEFAULT_SQUAREX_COST && squareCost[i+1][j+1] != DEFAULT_SQUAREO_COST) {
            squareCost[i+1][j+1]++;
        }
        if (squareCost[i-1][j+1] != DEFAULT_SQUAREX_COST && squareCost[i-1][j+1] != DEFAULT_SQUAREO_COST) {
            squareCost[i-1][j+1]++;
        }
        if (squareCost[i+1][j-1] != DEFAULT_SQUAREX_COST && squareCost[i+1][j-1] != DEFAULT_SQUAREO_COST) {
            squareCost[i+1][j-1]++;
        }
    }

    public void showCost(){
        for (int i = 0; i < (SquareXO.getFieldSize() + 2); i++) {
            for (int j = 0; j < (SquareXO.getFieldSize() + 2); j++) {
                System.out.print(squareCost[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void findMAx() {
        int max = 0;
        findMove();
        for (int i = 1; i < SquareXO.getFieldSize() + 1; i++) {
            for (int j = 1; j < SquareXO.getFieldSize() + 1; j++) {
                if (squareCost[i][j] > max && squareCost[i][j] != Ai.DEFAULT_SQUAREX_COST && squareCost[i][j] < DEFAULT_SQUAREO_COST) {
                    max = squareCost[i][j];
                    maxI = i-1;
                    maxJ = j-1;
                }
            }
        }
        squareCost[maxI + 1][maxJ + 1] = DEFAULT_SQUAREO_COST;
    }

    public void findMove()  {
        for (int i = 1; i < SquareXO.getFieldSize() + 1; i++) {
            for (int j = 1; j < SquareXO.getFieldSize() + 1; j++) {
                if (squareCost[i][j] == Ai.DEFAULT_SQUAREX_COST) {
                    if (squareCost[i-1][j] == Ai.DEFAULT_SQUAREX_COST && squareCost[i+1][j] != DEFAULT_SQUAREO_COST) {
                        squareCost[i+1][j] = WHERE_TO_MOVE_COST;
                    }
                    if (squareCost[i+1][j] == Ai.DEFAULT_SQUAREX_COST && squareCost[i-1][j] != DEFAULT_SQUAREO_COST) {
                        squareCost[i-1][j] = WHERE_TO_MOVE_COST;
                    }
                    if (squareCost[i-1][j-1] == Ai.DEFAULT_SQUAREX_COST && squareCost[i+1][j+1] != DEFAULT_SQUAREO_COST) {
                        squareCost[i+1][j+1] = WHERE_TO_MOVE_COST;
                    }
                    if (squareCost[i+1][j+1] == Ai.DEFAULT_SQUAREX_COST && squareCost[i-1][j-1] != DEFAULT_SQUAREO_COST) {
                        squareCost[i-1][j-1] = WHERE_TO_MOVE_COST;
                    }
                    if (squareCost[i][j+1] == Ai.DEFAULT_SQUAREX_COST && squareCost[i][j-1] != DEFAULT_SQUAREO_COST) {
                        squareCost[i][j-1] = WHERE_TO_MOVE_COST;
                    }
                    if (squareCost[i][j-1] == Ai.DEFAULT_SQUAREX_COST && squareCost[i][j+1] != DEFAULT_SQUAREO_COST) {
                        squareCost[i][j+1] = WHERE_TO_MOVE_COST;
                    }
                }
            }
        }
    }

}
