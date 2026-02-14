package h03;

import fopbot.World;

import java.util.Arrays;

/**
 * Main entry point in executing the program.
 */
public class Main {

    /**
     * Main entry point in executing the program.
     *
     * @param args program arguments, currently ignored
     */
    public static void main(String[] args) {
        // - INFO - You can change this value speed up or slow down the simulation
        World.setDelay(10); // set the delay between actions

        // ------- Starting example -------
        // create a pattern array for filling the world with some coins
//        int[][] coins = {
//            {0, 0, 0, 0, 0}, // y = 0
//            {0, 2, 1, 0, 0}, // y = 1
//            {1, 0, 0, 1, 0}, // y = 2
//            {1, 3, 0, 0, 0}, // y = 3
//        };
//
//        initializeWorld(coins); // create the world and set its dimensions
//
//        ControlCenter controlCenter = new ControlCenter(); // create a control center
//        controlCenter.placeCoinsInWorld(coins); // place coins in the world
//
//        // initialize robots
//        ScanRobot[] scanRobots = controlCenter.initScanRobots();
//        CleanRobot[] cleanRobots = controlCenter.initCleaningRobots();
//
//        // scan the world for coins
//        boolean[][] coinsInWorld = controlCenter.moveScanRobots(scanRobots);
//        System.out.println("The following coins were found:");
//        System.out.println(Arrays.deepToString(coinsInWorld));
//
//        // clean the world
//        controlCenter.moveCleanRobots(cleanRobots, coinsInWorld);
//        coinsInWorld = controlCenter.moveScanRobots(scanRobots);
//        System.out.println("The following coins were found after 1st clean:");
//        System.out.println(Arrays.deepToString(coinsInWorld));

        // ------------------------
        // HINT: When everything up to H3.4 is working, you can comment out the above example and
        //       uncomment the full example below to test your implementation (H3.5).
        // ------------------------



        // ------- Full example -------
        int[][] coins = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 4, 4, 0, 0, 4, 4, 0, 1, 0, 0, 0},
            {0, 0, 1, 0, 4, 0, 0, 4, 4, 0, 0, 4, 0, 1, 0, 0},
            {0, 0, 1, 0, 4, 0, 0, 0, 0, 0, 0, 4, 0, 1, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0, 4, 0, 0, 0, 0, 4, 0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 2, 0, 0, 0, 3, 1, 1, 3, 0, 0, 0, 2, 0, 0},
            {0, 0, 0, 2, 0, 3, 0, 0, 0, 0, 3, 0, 2, 0, 0, 0},
            {0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        initializeWorld(coins); // create the world and set its dimensions

        ControlCenter controlCenter = new ControlCenter(); // create a control center
        controlCenter.placeCoinsInWorld(coins); // place coins in the world
        controlCenter.scanAndCleanWorld(); // create and start the robots


    }

    public static void initializeWorld(int[][] coins) {
        World.setSize(coins[0].length, coins.length); // set the world dimensions

        // place block in the bottom right corner
        World.placeBlock(World.getWidth() - 1, 0);
        World.placeHorizontalWall(World.getWidth() - 1, 0);
        World.placeVerticalWall(World.getWidth() - 2, 0);

        World.setVisible(true); // make the world visible
    }
}
