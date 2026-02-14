package h05.mineable;

import h05.BaseDurable;
import h05.equipment.Tool;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

/**
 * Represents a rock that can be mined using tools.
 *
 * @author Nhan Huynh, Nico Schnieders
 */
public class Rock extends BaseDurable implements Mineable {

    /**
     * Constructs a new {@link Rock} instance.
     */
    public Rock() {
    }

    @StudentImplementationRequired("H5.3")
    @Override
    public @NotNull String getName() {
        return "Rock";
    }

    @StudentImplementationRequired("H5.3")
    @Override
    public @NotNull MiningProgress getProgress() {
        if (getDurability() < 100 & getDurability() >= 1) {
            return MiningProgress.IN_PROGRESS;
        } else if (getDurability() == 0) {
            return MiningProgress.COMPLETED;
        } else {
            return MiningProgress.UNSTARTED;
        }
    }

    @StudentImplementationRequired("H5.3")
    @Override
    public boolean onMined(@Nullable Tool tool) {
        if (getDurability() > 0) {
            double power;
            if (tool != null) {
                if (tool.getName().equals("Axe")) {
                    power = 1.5 * tool.getMiningPower();
                } else {
                    power = 2 * tool.getMiningPower();
                }
            } else {
                power = 5;
            }
            double newDurability = getDurability()-power < 0? 0:getDurability()-power;
            setDurability(newDurability);
        }
        return getDurability() == 0;

    }

}
