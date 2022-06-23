package lab;

import java.util.*;

public class Game {

    private Sea sea = new Sea();

    private String[] shipTypes = {"Carrier", "Battleship", "Cruiser", "Submarine", "Destroyer"};
    private int[] shipLengths = {5, 4, 3, 3, 2};
    private ArrayList<Ship> shipList = new ArrayList<Ship>();
    private int totalShipsBombed = 0;

    private Player player = new Player();

    public Game() {

    }

    public void createShips() {

        for (int shipNumber = 0; shipNumber < shipTypes.length; shipNumber++) {

            Ship ship = new Ship(shipTypes[shipNumber], shipLengths[shipNumber]);

            ship.setStartingCoordinate(sea.getRows(), sea.getColumns());

            boolean duplicate = false;

            String[][] shipStartingCoordinate = ship.getStartingCoordinate();
            int lengthOfShip = ship.getLength();
            boolean shipHorizontal = ship.getOrientation();

            if (checkOutOfRange(shipStartingCoordinate, lengthOfShip, shipHorizontal) != true) {

                int startingRowIndex = sea.convertRowToInteger(shipStartingCoordinate[0][0]);

                ship.setOtherCoordinates(sea.getRows(), startingRowIndex);

                String[][] newShipCoordinates = ship.getCoordinates();

                if (shipNumber > 0) {

                    for (int index = 0; index < shipList.size(); index++) {

                        String[][] oldShipCoordinates = shipList.get(index).getCoordinates();

                        if (checkDuplicate(newShipCoordinates, oldShipCoordinates) == true) {
                            duplicate = true;
                            shipNumber--;
                            break;
                        }
                    }//end of 2nd for loop

                    if (duplicate != true) {
                        shipList.add(ship);
                    }

                } else {
                    shipList.add(ship);
                }

            } else {
                shipNumber--;
            }

        }//end of 1st for loop

        // for (Ship eachShip: shipList) {
        //     System.out.println();
        //     eachShip.printCoordinates();
        // }

        // System.out.println();
        
    }//end of createShips method

    public boolean checkOutOfRange(String[][] shipStartingCoordinate, int lengthOfShip, boolean shipHorizontal) {

        int shipStartingRow = sea.convertRowToInteger(shipStartingCoordinate[0][0]);
        int ShipStartingColumn = Integer.parseInt(shipStartingCoordinate[0][1]);

        int maxRow = sea.getRows().length - 1;
        int maxColumn = sea.getColumns() - 1;

        boolean outOfRange = false;

        if (shipHorizontal == true) {

            if (ShipStartingColumn + (lengthOfShip - 1) > maxColumn) {
                outOfRange = true;
            }

        } else {

            if (shipStartingRow + (lengthOfShip - 1) > maxRow) {
                outOfRange = true;
            }
        }

        return outOfRange;
    }

    public boolean checkDuplicate(String[][] coordinate1, String[][] coordinate2) {

        boolean duplicate = false;

        for (int row_a = 0; row_a < coordinate1.length; row_a++) {
            for (int row_b = 0; row_b < coordinate2.length; row_b++) {

                if ((coordinate1[row_a][0].equals(coordinate2[row_b][0]))
                        && (coordinate1[row_a][1].equals(coordinate2[row_b][1]))) {

                    duplicate = true;
                    break;
                }
            }//end of 2nd for loop

            if (duplicate == true) {
                break;
            }
        }

        return duplicate;
    }

    public ArrayList<Ship> getShipList() {
        return shipList;
    }

    public Sea getSea() {
        return sea;
    }

    public Player getPlayer() {
        return player;
    }

    public int getTotalShipsBombed() {
        return totalShipsBombed;
    }

    public boolean checkInputOutOfRange(String[][] playerCoordinate) {

        boolean outOfRange = false;

        if (sea.convertRowToInteger(playerCoordinate[0][0]) == -1) {
            outOfRange = true;
        }

        return outOfRange;
    }

    public int checkPlayerHitsShip(String[][] playerCoordinate, String[][] shipCoordinates) {

        int partOfShipHit = -1;

        for (int row_a = 0; row_a < playerCoordinate.length; row_a++) {
            for (int row_b = 0; row_b < shipCoordinates.length; row_b++) {

                if ((playerCoordinate[row_a][0].equals(shipCoordinates[row_b][0]))
                        && (playerCoordinate[row_a][1].equals(shipCoordinates[row_b][1]))) {

                    partOfShipHit = row_b;
                    break;
                }
            }//end of 2nd for loop
        }//end of 1st for loop

        return partOfShipHit;
    }

    public void increaseTotalShipsBombed() {
        totalShipsBombed = totalShipsBombed + 1;
    }
}