package h05.equipment;

import fopbot.Direction;
import fopbot.Wall;
import fopbot.World;
import h05.BaseDurable;
import h05.entity.Miner;
import org.jetbrains.annotations.NotNull;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

/**
 * Usable equipment that allows the miner entity to break walls in the world.
 *
 * @author Nhan Huynh, Nico Schnieders
 */
public class WallBreaker extends BaseEquipment implements UsableEquipment {

    /**
     * Constructs a new {@link WallBreaker} instance.
     */
    public WallBreaker() {
    }

    @StudentImplementationRequired("H5.2.5")
    @Override
    public void use(@NotNull Miner miner) {
        if (getCondition() != EquipmentCondition.BROKEN) {
            Direction direction = miner.getDirection();
            int xCoordinate = miner.getX();
            int yCoordinate = miner.getY();
            if (direction == Direction.DOWN) {
                yCoordinate--;
            } else if (direction == Direction.LEFT) {
                xCoordinate--;
            }

            if (xCoordinate>= 0 & xCoordinate< World.getWidth()) {
                if (yCoordinate>=0 & yCoordinate<World.getHeight()) {
                    Wall[] wall_array = miner.getGameSettings().getWallsAt(xCoordinate, yCoordinate);

                    for (Wall wall : wall_array) {
                        if (wall.isHorizontal() & (direction == Direction.UP | direction == Direction.DOWN)) {
                            miner.getGameSettings().removeEntity(wall);
                        } else if (!wall.isHorizontal() & (direction == Direction.LEFT | direction == Direction.RIGHT)) {
                            miner.getGameSettings().removeEntity(wall);
                        }
                    }
                }
            }
        }
    }

    @StudentImplementationRequired("H5.2")
    @Override
    public @NotNull String getName() {
        return "WallBreaker";
    }

}
