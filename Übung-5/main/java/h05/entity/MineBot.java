package h05.entity;

import fopbot.Direction;
import fopbot.Robot;
import fopbot.World;
import h05.base.game.GameSettings;
import h05.base.mineable.BasicInventory;
import h05.base.mineable.Inventory;
import h05.base.ui.InfoPopup;
import h05.equipment.*;
import h05.mineable.Mineable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A basic implementation of a mining robot that can mine resources in the world.
 *
 * @author Nhan Huynh, Nico Schnieders
 */
@DoNotTouch
public class MineBot extends Robot implements Miner {

    /**
     * The default capacity of the mine bot, which determines how much equipment it can hold.
     */
    @DoNotTouch
    public static final int DEFAULT_CAPACITY = 4;

    /**
     * The game settings of this mine bot, which provides access to the world and other entities.
     */
    @DoNotTouch
    private final @NotNull GameSettings settings;

    /**
     * The array of equipments that this mine bot can hold, which can include empty slots if it has not been fully
     * equipped.
     */
    @DoNotTouch
    private final @NotNull Equipment[] equipments;

    /**
     * The inventory of this mine bot, which holds the resources it has mined.
     */
    @DoNotTouch
    private final @NotNull Inventory inventory;

    /**
     * The index of the next available slot in the equipments array, which is used to add new equipment.
     */
    @DoNotTouch
    private int nextIndex;

    /**
     * The battery of this mine bot, which determines the lifetime and energy of the bot.
     */
    @DoNotTouch
    private @NotNull Battery battery;

    /**
     * The camera of this mine bot, which determines the visibility range of the bot.
     */
    @DoNotTouch
    private @NotNull Camera camera;

    /**
     * The tool of this mine bot, which can be used to mine resources.
     */
    @DoNotTouch
    private @Nullable Tool tool;

    /**
     * Constructs a new {@link MineBot} instance with the specified position, game settings, and capacity.
     *
     * @param x        the x-coordinate of the mine bot
     * @param y        the y-coordinate of the mine bot
     * @param settings the game settings of this mine bot, which provides access to the world and other entities
     * @param capacity the capacity of this mine bot, which determines how much equipment it can hold
     */
    @DoNotTouch
    public MineBot(int x, int y, @NotNull GameSettings settings, int capacity) {
        super(x, y);
        this.settings = settings;
        this.equipments = new Equipment[capacity];
        this.battery = new Battery();
        this.camera = new Camera();
        this.equipments[0] = this.battery;
        this.equipments[1] = this.camera;
        nextIndex = 2;
        this.inventory = new BasicInventory();
        for (Point point : getVision(x, y)) {
            settings.removeFog(point.x, point.y);
        }
    }

    /**
     * Constructs a new {@link MineBot} instance with the specified position and game settings, using the default
     * capacity of {@value  DEFAULT_CAPACITY}.
     *
     * @param x        the x-coordinate of the mine bot
     * @param y        the y-coordinate of the mine bot
     * @param settings the game settings of this mine bot, which provides access to the world and other entities
     */
    @DoNotTouch
    public MineBot(int x, int y, @NotNull GameSettings settings) {
        this(x, y, settings, DEFAULT_CAPACITY);
    }

    @StudentImplementationRequired("H5.4.2")
    @Override
    public @NotNull Point[] getVision(int x, int y) {
        List<Point> coordinates = new ArrayList<>();
//        coordinates.add(new Point(x, y));

        if (getCamera().getVisibilityRange() >= 1) {
            int radius = getCamera().getVisibilityRange();
            for (int row = -radius; row<radius+1; row++) {
                for (int column = -radius; column < radius+1; column++) {
                    if (x+column < 0 || x+column >= World.getWidth()) {
                        continue;
                    } else if (y+row < 0 || y+row >= World.getHeight()) {
                        break;
                    }

                    coordinates.add(new Point(x+column, y+row));
                }
            }
        }

        return coordinates.toArray(new Point[0]);
    }

    @StudentImplementationRequired("H5.4.2")
    @Override
    public void updateVision(int oldX, int oldY, int newX, int newY) {

        Point[] viewArea = getVision(oldX, oldY);
        for (Point point: viewArea) {
            settings.placeFog(point.x, point.y);
        }

        viewArea = getVision(newX, newY);
        for (Point point: viewArea) {
            settings.removeFog(point.x, point.y);
        }
    }

    @StudentImplementationRequired("H5.4.3")
    @Override
    public void move() {
        if (getBattery().getCondition() != EquipmentCondition.BROKEN) {
            int oldX = getX();
            int oldY = getY();
            switch (getDirection()) {
                case UP -> setY(getY()+1);
                case DOWN -> setY(getY()-1);
                case RIGHT -> setX(getX()+1);
                default -> setX(getX()-1);
            }
            updateVision(oldX, oldY, getX(), getY());

            getBattery().reduceDurability(getNumberOfEquipments());
        }
    }

    @DoNotTouch
    @Override
    public @NotNull GameSettings getGameSettings() {
        return settings;
    }

    @DoNotTouch
    @Override
    public Equipment[] getEquipments() {
        Equipment[] equipments = new Equipment[nextIndex];
        System.arraycopy(this.equipments, 0, equipments, 0, nextIndex);
        return equipments;
    }

    @DoNotTouch
    @Override
    public int getNumberOfEquipments() {
        return nextIndex + 2 + (tool == null ? 0 : 1);
    }

    @StudentImplementationRequired("H5.4.4")
    @Override
    public void use(int index) {
        int checkIndex = 0;
        for (int i = 0; i<equipments.length; i++) {
            if (equipments[i] != null) {
                if (equipments[i].isUsable()) {
                    checkIndex++;

                    if (checkIndex == index & equipments[i].getCondition() != EquipmentCondition.BROKEN) {
                        Objects.requireNonNull(settings.toUsableEquipment(equipments[i])).use(this);

                        if (equipments[i].getName().equals("TelephotoLens")) {
                            updateVision(getX(), getY(), getX(), getY());
                        }

                        settings.update();
                        break;
                    }
                }
            }

        }
    }

    @DoNotTouch
    @Override
    public void equip(@NotNull Equipment equipment) {
        if (equipment.getName().equals("Battery")) {
            Battery newBattery = settings.toBattery(equipment);
            if (newBattery != null) {
                battery = newBattery;
                equipments[0] = newBattery;
            }
        } else if (equipment.getName().equals("Camera")) {
            Camera newCamera = settings.toCamera(equipment);
            if (newCamera != null) {
                camera = newCamera;
                equipments[1] = newCamera;
            }
        } else {
            for (int i = 2; i < nextIndex; i++) {
                if (equipments[i].getName().equals(equipment.getName())) {
                    equipments[i] = equipment;
                    return;
                }
            }
            if (equipment.isTool()) {
                tool = settings.toTool(equipment);
                return;
            }
            if (nextIndex + (tool == null ? -1 : 0) == equipments.length) {
                return;
            }
            equipments[nextIndex] = equipment;
            nextIndex++;
        }
    }

    @DoNotTouch
    @Override
    public void unequip(int index) {
        if (index + 2 < nextIndex) {
            return;
        }
        for (int i = index + 2; i < nextIndex - 1; i++) {
            equipments[i] = equipments[i + 1];
        }
        equipments[nextIndex - 1] = null;
        nextIndex--;
    }

    @DoNotTouch
    @Override
    public @NotNull Battery getBattery() {
        return battery;
    }

    @DoNotTouch
    @Override
    public @NotNull Camera getCamera() {
        return camera;
    }

    @DoNotTouch
    @Override
    public @Nullable Tool getTool() {
        return tool;
    }

    @DoNotTouch
    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }

    @StudentImplementationRequired("H5.4.1")
    @Override
    public void mine() {
        int xCoordinate = getX();
        int yCoordinate = getY();
        boolean isBlocked = false;

        switch (getDirection()) {
            case UP: {
                yCoordinate = getY()+1;
                if (getGameSettings().getWallAt(getX(), getY(), true) != null) {
                    isBlocked = true;
                }
                break;
            }
            case DOWN: {
                yCoordinate = getY()-1;
                if (getY()-1>=0) {
                    if (getGameSettings().getWallAt(getX(), getY()-1, true) != null) {
                        isBlocked = true;
                    }
                }
                break;
            }
            case RIGHT: {
                xCoordinate = getX()+1;
                if (getGameSettings().getWallAt(getX(), getY(), false) != null) {
                    isBlocked = true;
                }
                break;
            }
            default: {
                xCoordinate = getX()-1;
                if (getX()-1 >= 0) {
                    xCoordinate = getX()-1;
                    if (getGameSettings().getWallAt(getX()-1, getY(), false) != null) {
                        isBlocked = true;
                    }
                }
            }
        }


        Mineable block = null;
        if (xCoordinate>= 0 & xCoordinate<World.getWidth()) {
            if (yCoordinate>=0 & yCoordinate<World.getHeight()) {
                block = getGameSettings().getMineableAt(xCoordinate, yCoordinate);
            }
        }

        if (block != null & !isBlocked) {
            boolean mined = block.onMined(tool);

            if (mined) {
                Mineable item = settings.getLootAt(xCoordinate, yCoordinate);

                if (item != null) {
                    boolean added = inventory.add(item);

                    if (!added) {
                        crash();
                    }
                }
            }
        }
//        org.tudalgo.algoutils.student.Student.crash(); // TODO: H5.4.1 - remove if implemented
    }

    @DoNotTouch
    @Override
    public void pickGear() {
        int x = getX();
        int y = getY();
        Equipment equipment = settings.getAndRemoveGearAt(x, y);
        if (equipment != null) {
            Tool oldTool = tool;
            equip(equipment);
            if (equipment.isTool() && oldTool != null) {
                settings.placeGearAt(x, y, oldTool);
            }
        }
    }

    @DoNotTouch
    @Override
    public void handleKeyInput(
        @Nullable Direction direction,
        int selection,
        boolean isPickingGear,
        boolean isMining,
        boolean isInfo
    ) {
        if (direction != null) {
            while (getDirection() != direction) {
                turnLeft();
            }
            if (isFrontClear()) {
                move();
            }
        }
        if (selection != -1) {
            use(selection - 1);
        }
        if (isPickingGear) {
            pickGear();
        }
        if (isMining) {
            mine();
        }
        if (isInfo) {
            InfoPopup.showInfo(inventory);
        }
    }
}
