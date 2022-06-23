package lab;

import java.util.*;

public class Player {

    private String coordinate[][];
    private int bombs;

    public Player() {
        this(34);
    }

    public Player(int bombs) {
        coordinate = new String[1][2];
        this.bombs = bombs;
    }

    public void enterCoordinate() {

        Scanner input = new Scanner(System.in);
        System.out.print("Enter location (row column): ");
        String RowAndColumn = input.nextLine();

        if (RowAndColumn.length() == 2) {

            String lowerCaseRow = Character.toString(RowAndColumn.charAt(0));
            String upperCaseRow = lowerCaseRow.toUpperCase();

            int intColumn = Integer.parseInt(Character.toString(RowAndColumn.charAt(1)));

            intColumn = intColumn - 1;
            String stringColumn = Integer.toString(intColumn);

            coordinate[0][0] = upperCaseRow;
            coordinate[0][1] = stringColumn;

        } else if (RowAndColumn.length() == 3) {

            String lowerCaseRow = Character.toString(RowAndColumn.charAt(0));
            String upperCaseRow = lowerCaseRow.toUpperCase();

            String stringColumn1 = Character.toString(RowAndColumn.charAt(1));
            String stringColumn2 = Character.toString(RowAndColumn.charAt(2));

            String combineStringColumn = stringColumn1 + stringColumn2;

            int intColumn = Integer.parseInt(combineStringColumn);
            intColumn = intColumn - 1;
            String stringColumn = Integer.toString(intColumn);

            coordinate[0][0] = upperCaseRow;
            coordinate[0][1] = stringColumn;

        } else {
            System.out.println("Invalid input!!");
        }
    }

    public String[][] getCoordinate() {
        return coordinate;
    }

    public void decreaseBombs() {
        bombs = bombs - 1;
    }

    public int getBombsLeft() {
        return bombs;
    }
}