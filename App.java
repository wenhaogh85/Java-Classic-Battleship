package lab;

import java.util.*;

public class App {
    public static void main(String[] args) {

        Game game = new Game();

        game.createShips();

        while (true) {

            game.getSea().printMap();

            System.out.println
            (
                "____________________________________________________________\n" +
                "Ships bombed: " + game.getTotalShipsBombed() + "\n" +
                "Bombs left: " + game.getPlayer().getBombsLeft() + "\n" +
                "============================================================\n" + 
                "Ships      | Parts Left  |   Parts Status  |\n" +
                "-----------+-------------+-----------------+\n" +
                game.getShipList().get(0).getType() + "    |      " + 
                game.getShipList().get(0).getPartsLeft() + "      | " +
                Arrays.toString(game.getShipList().get(0).getShipPartsStatus()) + " |\n" +
                "-----------+-------------+-----------------+\n" +

                game.getShipList().get(1).getType() + " |      " + 
                game.getShipList().get(1).getPartsLeft() + "      | " +
                Arrays.toString(game.getShipList().get(1).getShipPartsStatus()) + "    |\n" +
                "-----------+-------------+-----------------+\n" +

                game.getShipList().get(2).getType() + "    |      " + 
                game.getShipList().get(2).getPartsLeft() + "      | " +
                Arrays.toString(game.getShipList().get(2).getShipPartsStatus()) + "       |\n" +
                "-----------+-------------+-----------------+\n" +

                game.getShipList().get(3).getType() + "  |      " + 
                game.getShipList().get(3).getPartsLeft() + "      | " +
                Arrays.toString(game.getShipList().get(3).getShipPartsStatus()) + "       |\n" +
                "-----------+-------------+-----------------+\n" +

                game.getShipList().get(4).getType() + "  |      " + 
                game.getShipList().get(4).getPartsLeft() + "      | " +
                Arrays.toString(game.getShipList().get(4).getShipPartsStatus()) + "          |\n" +
                "============================================================\n"
            );

            if (game.getPlayer().getBombsLeft() == 0 || game.getTotalShipsBombed() == 5) {
                break;

            } else {

                game.getPlayer().enterCoordinate();

                String[][] playerCoordinate = game.getPlayer().getCoordinate();
                String[][] shipCoordinates = new String[1][2];

                boolean playerBombedShip = false;
                int partBombed = 0;
                int shipNumber = 0;

                System.out.println
                (
                    "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ "
                );

                if (game.checkInputOutOfRange(playerCoordinate) != true) {

                    for (int index = 0; index < game.getShipList().size(); index++) {

                        shipCoordinates = game.getShipList().get(index).getCoordinates();

                        if (game.checkPlayerHitsShip(playerCoordinate, shipCoordinates) != -1) {
                            playerBombedShip = true;
                            partBombed = game.checkPlayerHitsShip(playerCoordinate, shipCoordinates);
                            shipNumber = index;
                            break;
                        }
                    }//end of for loop

                    if (playerBombedShip == true) {

                        if (game.getShipList().get(shipNumber).getAllPartsBombed() == true) {
                            System.out.println
                            (
                                "*** " +
                                game.getShipList().get(shipNumber).getType() +
                                " was already fully destroyed!! ***"
                            );

                        } else {

                            if (game.getShipList().get(shipNumber).partsHasBeenBombed(partBombed) == true) {
                                System.out.println
                                (
                                    "*** This part of the " +
                                    game.getShipList().get(shipNumber).getType() +
                                    " has been bombed before!! ***"
                                );

                            } else {

                                game.getShipList().get(shipNumber).partsIsBombed(partBombed);
                                game.getPlayer().decreaseBombs();
                                game.getSea().updateMap(playerCoordinate, "O");

                                if (game.getShipList().get(shipNumber).getAllPartsBombed() == true) {

                                    game.increaseTotalShipsBombed();

                                    System.out.println
                                    (
                                        "*** You have fully destroyed the " +
                                        game.getShipList().get(shipNumber).getType() + "!! ***"
                                    );

                                } else {
                                    System.out.println
                                    (
                                        "*** You have bombed part of the " +
                                        game.getShipList().get(shipNumber).getType() + "!! ***"
                                    );
                                }
                            }
                        }

                    } else {
                        game.getSea().updateMap(playerCoordinate, "X");
                        game.getPlayer().decreaseBombs();

                        System.out.println
                        (
                            "********************* You missed!! *************************"
                        );
                    }

                } else {
                    System.out.println
                    (
                        "************ Your coordinate are out of range!! ************"
                    );
                }
            }

            System.out.println
            (
                "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n"
            );
        }

        System.out.println
        (
            "======================== Game Over ========================="
        );
    }
}