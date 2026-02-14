package h05.equipment;

import h05.entity.Miner;
import org.jetbrains.annotations.NotNull;

public class Powerbank extends BaseEquipment implements UsableEquipment{
    private final double capacity;

    public Powerbank (double capacity) {
        this.capacity = capacity;
    }

    public double getCapacity() {
        return this.capacity;
    }

    @Override
    public @NotNull String getName() {
        return "Powerbank";
    }

    @Override
    public void use(@NotNull Miner miner) {
        if (getCondition() != EquipmentCondition.BROKEN) {
            miner.getBattery().increaseDurability(100);
            reduceDurability(getCapacity()/2);
        }
    }
}
