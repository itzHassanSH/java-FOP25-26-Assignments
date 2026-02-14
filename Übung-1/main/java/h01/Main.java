package h01;

import fopbot.Direction;
import fopbot.RobotFamily;
import fopbot.World;
import fopbot.Robot;
import org.tudalgo.algoutils.student.annotation.DoNotTouch;

/**
 * Main entry point in executing the program.
 */
public class Main {

    /**
     * The subtask to run.
     */
    public static int runToSubtask = -1;

    /**
     * The world speed in milliseconds.
     */
    public static int delay = 100;

    /**
     * Main entry point in executing the program.
     *
     * @param args program arguments, currently ignored
     */
    public static void main(String[] args) {
        // This sets up the FOPBot World with a 4x4 grid. (DO NOT TOUCH)
        setupWorld();

        // TODO H1.1: Initialize 3 robots
        Robot franzi = new Robot(2,4, Direction.LEFT, 6, RobotFamily.SQUARE_BLACK);
        Robot omar = new Robot(4, 1, Direction.RIGHT, 10, RobotFamily.SQUARE_RED);
        Robot peter = new Robot(8, 2, Direction.RIGHT, 9, RobotFamily.SQUARE_YELLOW);

        if (runToSubtask == 0) return; // DO NOT TOUCH

        // TODO H1.2: draw "F" with franzi
        franzi.putCoin();
        franzi.move();
        franzi.putCoin();
        franzi.turnLeft();
        franzi.move();
        franzi.putCoin();
        franzi.move();
        franzi.turnLeft();
        franzi.move();
        franzi.putCoin();
        franzi.turnLeft();
        franzi.turnLeft();
        franzi.move();
        franzi.putCoin();
        franzi.turnLeft();
        franzi.move();
        franzi.putCoin();
        franzi.move();

        if (runToSubtask == 1) return; // DO NOT TOUCH

        // TODO H1.3: draw "O" with omar
        for (int i0=0; i0<2; i0++) {
            for (int i=0; i<2; i++) {
                omar.move();
                omar.putCoin();
            }
            omar.turnLeft();
            for (int i=0; i<3; i++) {
                omar.move();
                omar.putCoin();
            }
            if (omar.hasAnyCoins()) {omar.turnLeft();
            } else {omar.move();}
        }

        if (runToSubtask == 2) return; // DO NOT TOUCH

        // TODO H1.4: draw "P" with peter
        while (peter.hasAnyCoins()) {
            peter.move();
            peter.putCoin();
            while (!peter.isFrontClear()) {
                peter.turnLeft();
            }
        }
        peter.move();

    }

    @DoNotTouch
    public static void setupWorld() {
        // variable representing width/size of world
        final int worldSizeX = 12;
        final int worldSizeY = 6;
        // setting world size symmetrical, meaning height = width
        World.setSize(worldSizeX, worldSizeY);

        for (int y = 1; y <= 4; y++) {
            World.placeVerticalWall(7, y);
        }

        for (int y = 2; y <= 4; y++) {
            World.placeVerticalWall(10, y);
        }

        World.placeVerticalWall(8, 1);
        World.placeVerticalWall(8, 3);
        World.placeVerticalWall(9, 3);

        for (int x = 8; x <= 10; x++) {
            World.placeHorizontalWall(x, 4);
        }

        for (int x = 9; x <= 10; x++) {
            World.placeHorizontalWall(x, 1);
        }

        World.placeHorizontalWall(9, 2);
        World.placeHorizontalWall(9, 3);

        // speed of how fast the world gets refreshed (e.g. how fast the robot(s) act)
        // the lower the number, the faster the refresh
        World.setDelay(delay);
        // make it possible to see the world window
        World.setVisible(true);
    }

}
