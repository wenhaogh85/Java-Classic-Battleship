package lab;

import java.util.ArrayList;

public class ShipTest {

    public static void main(String[] args) {

        Sea seaObject = new Sea();
        
        String[] seaRows = {
                            "A", "B", "C", "D", "E",
                            "F", "G", "H", "I", "J"
                        };
        int seaColumns = 10;

        String[] shipTypes = {"Carrier", "Battleship", "Cruiser", "Submarine", "Destroyer"};
        int[] shipLengths = {5, 4, 3, 3, 2};

        for (int index = 0; index < shipTypes.length; index++) {

            System.out.println("--------------------------------------------------");

            Ship ship = new Ship(shipTypes[index], shipLengths[index]);

            ship.setStartingCoordinate(seaRows, seaColumns);

            String[][] startingCoordinate = ship.getStartingCoordinate();

            if (checkOutOfRange(startingCoordinate, ship.getLength(), 
                    ship.getOrientation(), seaRows) != true) {

                int startingRowIndex = convertRowToInteger(startingCoordinate[0][0], seaRows);
                ship.setOtherCoordinates(seaRows, startingRowIndex);
                ship.printCoordinates();
                seaObject.updateMap(ship.getCoordinates(), "O");

            } else {
                index--;
            }
        }

        System.out.println("--------------------------------------------------");
        seaObject.printMap();
    }

    public static boolean checkOutOfRange(String[][] startingCoordinate, int length, 
            boolean horizontal, String[] seaRows) {

        int shipStartingRow = convertRowToInteger(startingCoordinate[0][0], seaRows);
        int shipStartingColumn = Integer.parseInt(startingCoordinate[0][1]);

        int maxRow = 10 - 1;
        int maxColumn = 10 - 1;
        
        boolean outOfBound = false;

        if (horizontal == true) {

            if (shipStartingColumn + (length - 1) > maxColumn) {
                System.out.println("*** Column is out of range!! ***");
                outOfBound = true;
            }

        } else {

            if (shipStartingRow + (length - 1) > maxRow) {
                System.out.println("*** Row is out of range!! ***");
                outOfBound = true;
            }
        }

        return outOfBound;
    }

    public static int convertRowToInteger(String shipRow, String[] seaRows) {

        int storeIndex = -1;

        for (int index = 0; index < seaRows.length; index++) {

            if (shipRow.equals(seaRows[index])) {
                storeIndex = index;
                break;
            }
        }

        return storeIndex;
    }
}