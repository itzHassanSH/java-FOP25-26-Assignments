package h06;

/**
 * A device/vehicle that's both electrically and manually (with fuel) powered
 */
public interface HybridPowered extends ElectricallyPowered, FuelPowered {
    /**
     * Get value between 0,0 and 1,0
     * 0,0 means the vehicle is 100% electrically powered
     * 1,0 means the vehicle is 100% fuel powered
     *
     * @return value between 0,0 and 1,0
     */
    double getFuelElectricityRatio();
}
