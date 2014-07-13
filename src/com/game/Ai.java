package com.game;

public class Ai {

    public static final int DEFAULT_SQUAREX_COST = 10;
    public static final int DEFAULT_SQUAREO_COST = -20;
    //public static final int WHERE_TO_MOVE_COST = 20;


    private int fieldSize;
    private int[] squareCost;
    private int maxI;
    private int arraySize;


    public Ai(Field field) {
        fieldSize = field.getFieldSize();
        arraySize = field.getArraySize();
        squareCost = new int[arraySize];
    }


    /*public void erase () {
        for (int i = 0; i < (Field.getArraySize()); i++) {
            squareCost[i] = 0;
        }
    }*/

    public int getMaxI() {
        return maxI;
    }



    public void addCost (int i) {
        squareCost[i] = DEFAULT_SQUAREX_COST;
        if (i+1 < arraySize) {
            if (squareCost[i + 1] != DEFAULT_SQUAREX_COST && (i + 1) % fieldSize !=0 && squareCost[i + 1] != DEFAULT_SQUAREO_COST ) {
                squareCost[i + 1]++;
            }
        }
        if (i!= 0) {
            if (squareCost[i - 1] != DEFAULT_SQUAREX_COST  && i % fieldSize != 0  && squareCost[i - 1] != DEFAULT_SQUAREO_COST ) {
                squareCost[i - 1]++;
            }
        }
        if (i - fieldSize - 1 >= 0 && (i - fieldSize) % fieldSize != 0) {
            if (squareCost[i - fieldSize] != DEFAULT_SQUAREX_COST  && squareCost[i - fieldSize] != DEFAULT_SQUAREO_COST ) {
                squareCost[i - fieldSize]++;
            }
            if (squareCost[i - fieldSize - 1] != DEFAULT_SQUAREX_COST  && squareCost[i -fieldSize -1] != DEFAULT_SQUAREO_COST ) {
                squareCost[i - fieldSize - 1]++;
            }
        }
        if(i - fieldSize >= 0) {
            if((i - fieldSize + 1) % fieldSize != 0) {
                if (squareCost[i - fieldSize + 1] != DEFAULT_SQUAREX_COST  && squareCost[i - fieldSize + 1] != DEFAULT_SQUAREO_COST ) {
                    squareCost[i - fieldSize + 1]++;
                }
            }
        }

        if (i + fieldSize + 1 <= arraySize) {
            if (squareCost[i + fieldSize] != DEFAULT_SQUAREX_COST  && squareCost[i + fieldSize] != DEFAULT_SQUAREO_COST ) {
                squareCost[i + fieldSize]++;
            }
            if ((i+fieldSize) % fieldSize !=0) {
                if (squareCost[i + fieldSize - 1] != DEFAULT_SQUAREX_COST  && squareCost[i + fieldSize -1] != DEFAULT_SQUAREO_COST ) {
                    squareCost[i + fieldSize - 1]++;
                }
            }
            if ((i + fieldSize + 1) % fieldSize != 0) {
                if (squareCost[i + fieldSize + 1] != DEFAULT_SQUAREX_COST  && squareCost[i + fieldSize + 1] != DEFAULT_SQUAREO_COST ) {
                    squareCost[i + fieldSize + 1]++;
                }
            }
        }
    }

   public void showCost(){
        for (int i = 0; i < (arraySize); i++) {
            System.out.print(squareCost[i] + " ");
            if ((i+1)%fieldSize==0) {
                System.out.println();
            }
        }
    }

    public void findMax() {
        int max = 0;
        findMove();
        for (int i = 0; i < arraySize; i++) {
            if (squareCost[i] > max && squareCost[i] != DEFAULT_SQUAREX_COST && squareCost[i] > DEFAULT_SQUAREO_COST) {
                max = squareCost[i];
                maxI = i;
            }
        }
        squareCost[maxI] = DEFAULT_SQUAREO_COST;
        showCost();
    }

    public void findMove()  {
        for (int i = 0; i < arraySize; i++) {
                if (squareCost[i] == DEFAULT_SQUAREX_COST) {
                    if (findMoveI(i,1) > 1 && squareCost[i+findMoveI(i, 1)] > DEFAULT_SQUAREO_COST&& squareCost[i+findMoveI(i, 1)] < findMoveI(i, 1)) {
                        squareCost[i+findMoveI(i, 1)] = findMoveI(i,1) * 10;
                    }
                    if (findMoveI(i,(-1)) > 1) {
                        if (squareCost[i-findMoveI(i,(-1))] > DEFAULT_SQUAREO_COST && squareCost[i-findMoveI(i,(-1))] < findMoveI(i, (-1))) {
                            squareCost[i-findMoveI(i,(-1))] = findMoveI(i,(-1)) * 10;
                        }
                    }
                    if (findMoveI(i,fieldSize) > 1) {
                            if(squareCost[i+fieldSize * findMoveI(i,fieldSize)] > DEFAULT_SQUAREO_COST && squareCost[i+fieldSize * findMoveI(i,fieldSize)] < findMoveI(i,fieldSize)) {
                                squareCost[i+fieldSize * findMoveI(i,fieldSize)] = findMoveI(i,fieldSize) * 101;
                            }
                    }
                    if (findMoveI(i,1+fieldSize) > 1) {
                        if (squareCost[i+(1+fieldSize) * findMoveI(i,1+fieldSize)] > DEFAULT_SQUAREO_COST && squareCost[i+(1+fieldSize) * findMoveI(i,1+fieldSize)] < findMoveI(i,1+fieldSize)) {
                            squareCost[i+(1+fieldSize) * findMoveI(i,1+fieldSize)] = findMoveI(i,1 + fieldSize) * 10;
                        }
                    }
                    if (findMoveI(i,1-fieldSize) > 1) {
                        if(squareCost[i+(1-fieldSize) * findMoveI(i,1-fieldSize)] > DEFAULT_SQUAREO_COST && squareCost[i+(1-fieldSize) * findMoveI(i,1-fieldSize)] < findMoveI(i,1-fieldSize)) {
                            squareCost[i+(1-fieldSize) * findMoveI(i,1-fieldSize)] = findMoveI(i,1 - fieldSize) * 10;
                        }
                    }
                    if (findMoveI(i,(-fieldSize)) > 1) {
                        if (squareCost[i-(fieldSize) * findMoveI(i,(-fieldSize))] > DEFAULT_SQUAREO_COST && squareCost[i-(fieldSize) * findMoveI(i,(-fieldSize))] < findMoveI(i,(-fieldSize))) {
                            squareCost[i-(fieldSize) * findMoveI(i,(-fieldSize))] = findMoveI(i,(-fieldSize)) * 10;
                        }
                    }
                    if (findMoveI(i,(-(1+fieldSize))) > 1) {
                        if(squareCost[i+((-(1+fieldSize))) * findMoveI(i,(-(1+fieldSize)))] > DEFAULT_SQUAREO_COST && squareCost[i+((-(1+fieldSize))) * findMoveI(i,(-(1+fieldSize)))] < findMoveI(i,(-(1+fieldSize)))) {
                            squareCost[i+((-(1+fieldSize))) * findMoveI(i,(-(1+fieldSize)))] = findMoveI(i,(-(1+fieldSize))) * 10;
                        }
                    }
                    if (findMoveI(i,fieldSize - 1) > 1) {
                        if(squareCost[i+(fieldSize - 1) * findMoveI(i,fieldSize - 1)] > DEFAULT_SQUAREO_COST && squareCost[i+(fieldSize - 1) * findMoveI(i,fieldSize - 1)] < findMoveI(i,fieldSize - 1)) {
                            squareCost[i+(fieldSize - 1) * findMoveI(i,fieldSize - 1)] = findMoveI(i,fieldSize - 1) * 10;
                        }
                    }
                }
        }
    }

    private int findMoveI(int i, int diff) {
        int j = 1;
        while (nextI(i, diff, j)>= 0 && i + diff * j < arraySize) {

            boolean isEdge1 = nextI(i, diff, j - 1) % fieldSize  == 0 && nextI(i, diff, j) % fieldSize == (fieldSize - 1);
            boolean isEdge2 = nextI(i, diff, j - 1) % fieldSize  == (fieldSize - 1) && nextI(i, diff, j) % fieldSize == 0;
            if (isEdge1 || isEdge2) {
                return 1;
            }
            if (squareCost[i] == squareCost [i + diff * j]) {
                j++;
            } else {
                return j;
            }
        }
        return 1;

    }


    private int nextI(int i, int diff, int j) {
        return i + diff * j;
    }



}
