package h02;

import fopbot.Direction;
import fopbot.Robot;

import h02.frameWork.*;

/**
 * Main entry point in executing the program.
 *
 */
public class Main {
    public static final int WORLD_SIZE = 20;
    private static final int RANDOM_NUMBER = (int) (Math.random() * 3);
    private static final WorldType WORLD_TYPE = WorldType.BLOCK_WORLD; // TODO: change WorldType.WALL_WORLD to WorldType.BLOCK_WORLD if you want to test H2.2


    public static void evadeRight(Robot robo) {
        for (int i2=0;i2<3;i2++) {
            robo.turnLeft();
        }
        for (int i=0;i<2;i++) {
            if (robo.isFrontClear()) {robo.move();}
            robo.turnLeft();
            if (robo.isFrontClear()) {robo.move();}
        }
        for (int i2=0;i2<3;i2++) {
            robo.turnLeft();
        }
    }
    public static void evadeLeft(Robot robo) {
        robo.turnLeft();
        for (int i=0;i<2;i++) {
            robo.move();
            for (int i2=0;i2<3;i2++) {
                robo.turnLeft();
            }
            robo.move();
        }
        robo.turnLeft();
    }

    /**
     * Main entry point in executing the program.
     *
     * @param args program arguments, currently ignored
     */
    public static void main(String[] args) {
        AbstractWorld world = WorldFactory.createWorld(WORLD_TYPE, WORLD_SIZE);
        world.start();

        if (WORLD_TYPE == WorldType.WALL_WORLD) {
            // TODO: H2.1.1 (student implementation of robot)
            // Correct implementation (according to Anleitung):
            int numberOfCoins = RANDOM_NUMBER == 0 ? 12 : RANDOM_NUMBER == 1?  18 : 27;
            int numberCoins = switch (RANDOM_NUMBER) {
                case 0 -> 12;
                case 1 -> 18;
                default -> 27;
            };
            Robot Robo = new Robot(0, 0, Direction.RIGHT, numberOfCoins);


            // TODO: H2.1.2 (student implementation for WALL_WORLD)
            // Each loop enables robot to cross 1 wall-sector; the code repeats itself after each wall-sector
            for (int i2=0; i2<3; i2++) {
                // This loop makes sure the robot crosses the wall
                for (int i = 0; i < 10; i++) {
                    Robo.move();
                    while (!Robo.isFrontClear()) {
                        Robo.turnLeft();
                        Robo.move();
                        for (int i1 = 0; i1 < 3; i1++) {
                            Robo.turnLeft();
                        }
                    }
                }
                // The Robot moves along the edges to reach the next corner
                for (int i = 0; i < 3; i++) {
                    Robo.turnLeft();
                }
                for (int i=0; i<2; i++) {
                    while (Robo.isFrontClear()) {
                        Robo.move();
                    }
                    Robo.turnLeft();
                }
                for (int i = 0; i < numberCoins / 3; i++) {
                    Robo.putCoin();
                }
            }


        }




        if (WORLD_TYPE == WorldType.BLOCK_WORLD){
            RobotFactory robotFactory = new RobotFactory(world);
            Robot blockRobot = robotFactory.createRobot();
            // TODO: H2.2 (student implementation for BlOCK_WORLD)
            int xCoord = 0;
            int yCoord = 0;
            if (blockRobot.getX() > 9) {
                xCoord = 19;
            }
            if (blockRobot.getY() > 9) {
                yCoord = 19;
            }


            while (blockRobot.getX() != xCoord | blockRobot.getY() != yCoord) {
                // Robots direction is adjusted so he faces correct x-side
                if ((blockRobot.getDirection() == Direction.RIGHT & xCoord == 0) |
                    (blockRobot.getDirection() == Direction.LEFT & xCoord == 19)) {
                    blockRobot.turnLeft();
                    blockRobot.turnLeft();
                }
                if (blockRobot.getDirection() == Direction.UP) {
                    if (xCoord == 0) {
                        blockRobot.turnLeft();
                    }
                    else {
                        for (int i=0; i<3; i++) {
                            blockRobot.turnLeft();
                        }
                    }
                } else if (blockRobot.getDirection() == Direction.DOWN) {
                    if (xCoord == 0) {
                        for (int i=0; i<3; i++) {
                            blockRobot.turnLeft();
                        }
                    }
                    else {
                        blockRobot.turnLeft();
                    }
                }

                // Robot moves along x-axis, evading upwards (not-efficient version)
                // If robot moves along edge, it can either only evade upwards or downwards without colliding (with edge)
                while (blockRobot.getX() != xCoord) {
                    if (!blockRobot.isFrontClear()) {
                        if ((blockRobot.getDirection() == Direction.RIGHT & blockRobot.getY() == 0) |
                            (blockRobot.getDirection() == Direction.LEFT & blockRobot.getX() == 19)) {
                            evadeLeft(blockRobot);
                        } else {
                            evadeRight(blockRobot);
                        }
                    }
                    if (blockRobot.isFrontClear()) {blockRobot.move();}
                }

                // Robots direction is adjusted so he faces correct y-side
                if ((blockRobot.getDirection() == Direction.LEFT & yCoord ==0) |
                    (blockRobot.getDirection() == Direction.RIGHT & yCoord ==19)) {
                    blockRobot.turnLeft();
                } else {
                    for (int i=0; i<3; i++) {
                        blockRobot.turnLeft();
                    }
                }

                // Robot moves along y-axis, evading left or right, depending on which side it's on
                while (blockRobot.getY() != yCoord) {
                    if (!blockRobot.isFrontClear()) {
                        if ((blockRobot.getDirection() == Direction.UP & blockRobot.getX() == 19) |
                            (blockRobot.getDirection() == Direction.DOWN & blockRobot.getX() == 0)) {
                            evadeLeft(blockRobot);
                        } else {
                            evadeRight(blockRobot);
                        }
                    }
                    if (blockRobot.isFrontClear()) {blockRobot.move();}

                }
            }
            blockRobot.putCoin();
        }
    }
}
