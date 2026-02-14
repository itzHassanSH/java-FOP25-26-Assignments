package h05.equipment;

import h05.entity.Miner;
import org.jetbrains.annotations.NotNull;

public class TelephotoLens extends BaseEquipment implements UsableEquipment {
    private final int rangeEnhancement;

    public TelephotoLens(int rangeEnhancement) {
        this.rangeEnhancement = rangeEnhancement;
    }

    public int getRangeEnhancement() {
        return this.rangeEnhancement;
    }

    @Override
    public @NotNull String getName() {
        return "TelephotoLens";
    }

    @Override
    public void use(@NotNull Miner miner) {
        if (getCondition() != EquipmentCondition.BROKEN) {
            miner.getCamera().setVisibilityRange(miner.getCamera().getVisibilityRange() + getRangeEnhancement());
            setDurability(0);
        }
    }
}
