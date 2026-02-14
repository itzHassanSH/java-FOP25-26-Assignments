package h05.equipment;

import org.jetbrains.annotations.NotNull;

public class Pickaxe extends BaseEquipment implements Tool {

    @Override
    public double getMiningPower() {
        return 15;
    }

    @Override
    public @NotNull String getName() {
        return "Pickaxe";
    }
}
