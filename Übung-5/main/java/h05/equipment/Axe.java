package h05.equipment;

import org.jetbrains.annotations.NotNull;

public class Axe extends BaseEquipment implements Tool {
    @Override
    public double getMiningPower() {
        return 5;
    }

    @Override
    public @NotNull String getName() {
        return "Axe";
    }
}
