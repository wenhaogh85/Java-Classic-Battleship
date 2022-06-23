package lab;

import java.util.*;

public class Sea {
    private final String[][] map = new String[10][10];
    private final String[] rows = {
                                    "A", "B", "C", "D", "E",
                                    "F", "G", "H", "I", "J"
                                };
    private final int columns = 10;
    private final String tile = ".";

    public Sea() {

        for (int row_n = 0; row_n < map.length; row_n++) {
            Arrays.fill(map[row_n], tile);
        }
    }

    public String[] getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int convertRowToInteger(String shipRow) {

        int storeIndex = -1;

        for (int index = 0; index < rows.length; index++) {

            if (shipRow.equals(rows[index])) {
                storeIndex = index;
                break;
            }
        }

        return storeIndex;
    }

    public void updateMap(String[][] coordinates, String symbol) {

        for (int row = 0; row < coordinates.length; row++) {

            int column = 0;

            String stringRow = coordinates[row][column];
            String stringColumn = coordinates[row][column + 1];

            int intRow = convertRowToInteger(stringRow);
            int intColumn = Integer.parseInt(stringColumn);

            map[intRow][intColumn] = symbol;
        }
    }

    public void printMap() {

        //aligns top label for 1st part
        System.out.print("                    ");

        for (int value = 1; value <= 10; value++) {
            System.out.print(value + " ");
        }

        //align side labels
        System.out.println();

        //loop through 2D array and prints each element
        int index = 0;
        for (int row = 0; row < map.length; row++) {

            System.out.print("                 " + rows[index] + "  ");

            //loops through the 2D array and prints each element
            for (int column = 0; column < map[row].length; column++) {
                System.out.print(map[row][column] + " ");
            }//end of 2nd for loop

            System.out.println();
            index++;
        }//end of 1st for loop
    }
}