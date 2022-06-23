package lab;

import java.util.*;

public class Ship {
    private String type;
    private int length;
    private boolean horizontal;
    private String[][] coordinates;
    private boolean[] partsBombed;

    public Ship() {
    }

    public Ship(String type, int length) {
        this.type = type;
        this.length = length;
        coordinates = new String[length][2];
        partsBombed = new boolean[length];
        setOrientation();
    }

    public String getType() {
        return type;
    }

    public int getLength() {
        return length;
    }

    public boolean setOrientation() {

        //initalises random variable
        Random random = new Random();

        horizontal = random.nextBoolean();

        return horizontal;
    }

    public boolean getOrientation() {
        return horizontal;
    }

    public void setStartingCoordinate(String[] seaRows, int seaColumns) {

        //initalises random variable
        Random random = new Random();

        int minRow = 0;
        int maxRow = seaRows.length - 1;
        /*set the ship's row range between A to J
        by accessing its index range between 0 to 9*/
        String startingShipRow = seaRows[minRow + random.nextInt((maxRow - minRow) + 1)];

        int minColumn = 0;
        int maxColumn = seaColumns - 1;
        //set the ship's column range 0 - 9
        int startingShipColumn = minColumn + random.nextInt((maxColumn - minColumn) + 1);

        coordinates[0][0] = startingShipRow;
        coordinates[0][1] = Integer.toString(startingShipColumn);
    }

    public String[][] getStartingCoordinate() {

        String[][] startingCoordinate = new String[1][2];

        startingCoordinate[0][0] = coordinates[0][0];
        startingCoordinate[0][1] = coordinates[0][1];

        return startingCoordinate;
    }

    public void setOtherCoordinates(String[] shipRows, int startRowIndex) {

        String startingShipRow = coordinates[0][0];

        int startingShipColumn = Integer.parseInt(coordinates[0][1]);

        int increment = 1;
        for (int row = 1; row < length; row++) {

            if (horizontal == true) {
                coordinates[row][0] = startingShipRow;
                coordinates[row][1] = Integer.toString(startingShipColumn + increment);

            } else {
                coordinates[row][0] = shipRows[startRowIndex + increment];
                coordinates[row][1] = Integer.toString(startingShipColumn);
            }

            increment++;
        }//end of for loop
    }

    public String[][] getCoordinates() {
        return coordinates;
    }

    public int getPartsLeft() {

        int partsLeft = partsBombed.length;

        for (int index = 0; index < partsBombed.length; index++) {
            
            if (partsBombed[index] == true) {
                partsLeft = partsLeft - 1;
            }
        }

        return partsLeft;
    }

    public boolean getAllPartsBombed() {

        boolean allPartsBombed = false;
        int totalPartsBombed = 0;
        for (int index = 0; index < partsBombed.length; index++) {

            if (partsBombed[index] == true) {
                totalPartsBombed = totalPartsBombed + 1;
            }
        }//end of for loop

        if (totalPartsBombed == partsBombed.length) {
            allPartsBombed = true;
        }

        return allPartsBombed;
    }

    public boolean partsHasBeenBombed(int index) {

        boolean partsHasBeenBombed = false;

        if (partsBombed[index] == true) {
            partsHasBeenBombed = true;
        }

        return partsHasBeenBombed;
    }

    public void partsIsBombed(int index) {
        partsBombed[index] = true;
    }

    public String[] getShipPartsStatus() {

        String[] shipPartsStatus = new String[length];

        for (int index = 0; index < partsBombed.length; index++) {

            if (partsBombed[index] == false) {
                shipPartsStatus[index] = "O";
            } else {
                shipPartsStatus[index] = "X";
            }
        }

        return shipPartsStatus;
    }

    public void printCoordinates() {

        System.out.print("Ship type: " + type);

        System.out.print(" | Orientation: ");
        if (horizontal == true) {
            System.out.print("Horizontal");
        } else {
            System.out.print("Vertical");
        }

        System.out.println(" | length: " + length);
        System.out.print("Coordinates: ");

        int count = 1;
        for (int row = 0; row < coordinates.length; row++) {

            System.out.print("(");

            for (int column = 0; column < coordinates[row].length; column++) {

                System.out.print(coordinates[row][column]);

                if (column == 0) {
                    System.out.print(",");
                }
            }//end of 2nd for loop
            System.out.print(") ");

            if (count % 6 == 0) {
                System.out.println();
            }
            count++;
        }//end of 1st for loop

        System.out.println();
    }
}