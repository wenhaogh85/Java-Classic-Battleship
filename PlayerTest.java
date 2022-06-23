package lab;

public class PlayerTest {
    public static void main(String[] args) {

        Player player = new Player();

        player.enterCoordinate();

        String[][] temp = player.getCoordinate();

        System.out.print("(" + temp[0][0] + ",");
        System.out.println(temp[0][1] + ")");
    }
}