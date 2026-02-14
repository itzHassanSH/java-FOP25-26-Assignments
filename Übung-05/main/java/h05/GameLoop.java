package h05;

import fopbot.Robot;
import fopbot.World;
import h05.base.entity.Gear;
import h05.base.entity.Loot;
import h05.base.game.BasicGameSettings;
import h05.base.game.GameLoopBase;
import h05.entity.AbstractRepairBot;
import h05.entity.MineBot;
import h05.entity.TeleportRepairBot;
import h05.entity.WallBreakerRepairBot;
import h05.equipment.*;
import h05.mineable.Rock;
import h05.mineable.Tree;
import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

/**
 * The game loop for simulating the MineBot world.
 *
 * @author Nhan Huynh, Nico Schnieders
 */
public class GameLoop extends GameLoopBase {

    /**
     * Constructs a new {@link GameLoop} instance for simulating the MineBot world.
     */
    @DoNotTouch
    public GameLoop() {
    }

    @StudentImplementationRequired("H5.6")
    @Override
    protected void setupWorld() {

        World.placeHorizontalWall(0,0);
        World.placeHorizontalWall(4,1);
        World.placeHorizontalWall(5,1);
        World.placeHorizontalWall(6,1);
        World.placeHorizontalWall(3,2);
        World.placeHorizontalWall(4,2);
        World.placeHorizontalWall(5,2);
        World.placeHorizontalWall(4,3);
        World.placeHorizontalWall(5,3);
        World.placeHorizontalWall(1,4);
        World.placeHorizontalWall(2,4);
        World.placeHorizontalWall(3,4);
        World.placeHorizontalWall(5,4);
        World.placeHorizontalWall(6,4);
        World.placeHorizontalWall(0,5);
        World.placeHorizontalWall(1,5);
        World.placeHorizontalWall(2,5);
        World.placeHorizontalWall(4,5);
        World.placeHorizontalWall(5,5);

        World.placeVerticalWall(1, 0);
        World.placeVerticalWall(5, 0);
        for (int i = 0; i<4; i++) {
            World.placeVerticalWall(i, 1);
        }
        World.placeVerticalWall(5, 1);
        World.placeVerticalWall(2, 2);
        World.placeVerticalWall(1, 3);
        World.placeVerticalWall(2, 3);
        World.placeVerticalWall(5, 3);
        World.placeVerticalWall(1, 4);
        World.placeVerticalWall(3, 4);
        World.placeVerticalWall(3, 5);

        World.getGlobalWorld().placeFieldEntity(new Gear(0, 6, new Powerbank(10)));
        World.getGlobalWorld().placeFieldEntity(new Gear(6, 4, new Powerbank(10)));
        World.getGlobalWorld().placeFieldEntity(new Gear(2, 2, new TelephotoLens(1)));
        World.getGlobalWorld().placeFieldEntity(new Gear(5, 3, new TelephotoLens(1)));
        World.getGlobalWorld().placeFieldEntity(new Gear(5, 0, new WallBreaker()));
        World.getGlobalWorld().placeFieldEntity(new Gear(6, 6, new WallBreaker()));
        World.getGlobalWorld().placeFieldEntity(new Gear(6, 3, new Axe()));
        World.getGlobalWorld().placeFieldEntity(new Gear(2, 0, new Pickaxe()));

        World.getGlobalWorld().placeFieldEntity(new Loot(0, 0, new Rock()));
        World.getGlobalWorld().placeFieldEntity(new Loot(3, 0, new Rock()));
        World.getGlobalWorld().placeFieldEntity(new Loot(6, 5, new Rock()));
        World.getGlobalWorld().placeFieldEntity(new Loot(0, 3, new Tree()));
        World.getGlobalWorld().placeFieldEntity(new Loot(3, 4, new Tree()));
        World.getGlobalWorld().placeFieldEntity(new Loot(6, 0, new Tree()));


    }

    @StudentImplementationRequired("H5.6")
    @Override
    protected void initRobots() {
        MineBot mineRobby = new MineBot(1,0, getGameSettings(), 5);
        AbstractRepairBot teleportRepairer = new TeleportRepairBot(3, 2, getGameSettings(), 1);
        AbstractRepairBot breakerRepairer = new WallBreakerRepairBot(4, 3, getGameSettings(), 1);
    }
}
