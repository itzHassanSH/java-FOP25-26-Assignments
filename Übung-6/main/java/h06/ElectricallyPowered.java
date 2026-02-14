package h06;

/**
 * A device/vehicle that's electrically powered of {@link PlugType}
 */
public interface ElectricallyPowered {
    /**
     * Performs the devices actions for {@code duration} amount of time
     */
    void use(int duration);

    /**
     * Returns the supported Plug type of this device
     *
     * @return the supported Plug type of this device
     */
    PlugType getSupportedPlugType();
}
