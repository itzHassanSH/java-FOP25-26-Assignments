package h06;

/**
 * A device/vehicle that's powered with Fuel of {@link FuelType}
 */
public interface FuelPowered {

    /**
     * Returns the supported Fuel type of this vehicle
     *
     * @return the supported Fuel type of this vehicle
     */
    FuelType getFuelType();
}
