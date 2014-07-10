package com.game;

public class Ai {

    public static final int DEFAULT_SQUAREX_COST = 10;
    public static final int DEFAULT_SQUAREO_COST = 50;
    public static final int WHERE_TO_MOVE_COST = 20;


    private int fieldSize = Field.getFieldSize();
    private int[] squareCost = new int[fieldSize*fieldSize];
    private int maxI = 0;



    /*public void erase () {
        for (int i = 0; i < (Field.getArraySize()); i++) {
            squareCost[i] = 0;
        }
    }*/

    public int getMaxI() {
        return maxI;
    }



    public void addCost (int i) {
        //int i = i1;
        squareCost[i] = DEFAULT_SQUAREX_COST;
        if (i+1 < Field.getArraySize()) {
            if (squareCost[i + 1] != DEFAULT_SQUAREX_COST && (i + 1) % fieldSize !=0 ) {
                squareCost[i + 1]++;
            }
        }
        if (i!= 0) {
            if (squareCost[i - 1] != DEFAULT_SQUAREX_COST  && i % fieldSize != 0) {
                squareCost[i - 1]++;
            }
        }
        if (i - fieldSize - 1 >= 0 && (i - fieldSize) % fieldSize != 0) {
            if (squareCost[i - fieldSize] != DEFAULT_SQUAREX_COST) {
                squareCost[i - fieldSize]++;
            }
            if (squareCost[i - fieldSize - 1] != DEFAULT_SQUAREX_COST) {
                squareCost[i - fieldSize - 1]++;
            }
        }
        if(i - fieldSize >= 0) {
            if((i - fieldSize + 1) % fieldSize != 0) {
                if (squareCost[i - fieldSize + 1] != DEFAULT_SQUAREX_COST) {
                    squareCost[i - fieldSize + 1]++;
                }
            }
        }

        if (i + fieldSize + 1 <= Field.getArraySize()) {
            if (squareCost[i + fieldSize] != DEFAULT_SQUAREX_COST) {
                squareCost[i + fieldSize]++;
            }
            if ((i+fieldSize) % fieldSize !=0) {
                if (squareCost[i + fieldSize - 1] != DEFAULT_SQUAREX_COST) {
                    squareCost[i + fieldSize - 1]++;
                }
            }
            if ((i + fieldSize + 1) % fieldSize != 0) {
                if (squareCost[i + fieldSize + 1] != DEFAULT_SQUAREX_COST) {
                    squareCost[i + fieldSize + 1]++;
                }
            }
        }
    }

    public void showCost(){
        for (int i = 0; i < (Field.getArraySize()); i++) {
            //for (int j = 0; j < (Field.getFieldSize() + 2); j++) {
                System.out.print(squareCost[i] + " ");

            if ((i+1)%fieldSize==0) {
                System.out.println();
            }
        }
    }

    public void findMax() {
        int max = 0;
        findMove();
        for (int i = 0; i < Field.getArraySize(); i++) {
            if (squareCost[i] > max && squareCost[i] != DEFAULT_SQUAREX_COST && squareCost[i] < DEFAULT_SQUAREO_COST) {
                max = squareCost[i];
                maxI = i;
            }
        }
        squareCost[maxI] = DEFAULT_SQUAREO_COST;
    }

    public void findMove()  {
        for (int i = 0; i < Field.getArraySize(); i++) {
                if (squareCost[i] == Ai.DEFAULT_SQUAREX_COST) {
                    if (i!=0) {
                        if (squareCost[i-1] == Ai.DEFAULT_SQUAREX_COST && squareCost[i+1] <= DEFAULT_SQUAREO_COST) {
                            squareCost[i+1] = WHERE_TO_MOVE_COST;
                        }
                    }
                    if(i+fieldSize < Field.getArraySize() && i-fieldSize > 0) {
                        if (squareCost[i+fieldSize] == Ai.DEFAULT_SQUAREX_COST && squareCost[i-fieldSize] <= DEFAULT_SQUAREO_COST) {
                            squareCost[i-fieldSize] = WHERE_TO_MOVE_COST;
                        }
                    }
                    if (i - fieldSize >= 0) {
                        if (squareCost[i-fieldSize] == Ai.DEFAULT_SQUAREX_COST && squareCost[i+fieldSize] <= DEFAULT_SQUAREO_COST) {
                            squareCost[i+fieldSize] = WHERE_TO_MOVE_COST;
                        }
                    }
                    if(i+1<Field.getArraySize()) {
                        if (squareCost[i+1] == Ai.DEFAULT_SQUAREX_COST && squareCost[i-1] <= DEFAULT_SQUAREO_COST && i % fieldSize!=0) {
                            squareCost[i-1] = WHERE_TO_MOVE_COST;
                        }
                    }
                    if(i-1-fieldSize >= 0 && i+1+fieldSize <=Field.getArraySize()){
                        if (squareCost[i-1-fieldSize] == Ai.DEFAULT_SQUAREX_COST && squareCost[i+1+fieldSize] <= DEFAULT_SQUAREO_COST) {
                            squareCost[i+1+fieldSize] = WHERE_TO_MOVE_COST;
                        }
                        if(i+1+fieldSize<Field.getArraySize()) {
                            if (squareCost[i+1+fieldSize] == Ai.DEFAULT_SQUAREX_COST && squareCost[i-1-fieldSize] <= DEFAULT_SQUAREO_COST) {
                                squareCost[i-1-fieldSize] = WHERE_TO_MOVE_COST;
                            }
                        }
                    }
                }
        }
    }

}
