package h05.equipment;

import h05.BaseDurable;
import org.jetbrains.annotations.NotNull;

public abstract class BaseEquipment extends BaseDurable implements Equipment {
    private EquipmentCondition equipmentCondition;

    public @NotNull EquipmentCondition getCondition() {
        if (getDurability()>= 81) {
            this.equipmentCondition = EquipmentCondition.NEW;
        } else if (getDurability()>=41) {
            this.equipmentCondition = EquipmentCondition.USED;
        } else if (getDurability()>=1) {
            this.equipmentCondition = EquipmentCondition.DAMAGED;
        } else {
            this.equipmentCondition = EquipmentCondition.BROKEN;
        }
        return this.equipmentCondition;
    }
}

