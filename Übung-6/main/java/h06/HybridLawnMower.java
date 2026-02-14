package h06;

public class HybridLawnMower extends DriveableLawnMower implements HybridPowered {
    private final PlugType supportedPlugType;
    private final double fuelElectricityRatio;

    public HybridLawnMower(FuelType fuelType, int fillingLevel) {
        super(fuelType, fillingLevel);
        fuelElectricityRatio = 0.9;
        supportedPlugType = PlugType.HOME_OUTLET;
    }

    @Override
    public double getFuelElectricityRatio() {
        return fuelElectricityRatio;
    }

    @Override
    public void use(int duration) {
        letMeMove(3*duration);
    }

    @Override
    public PlugType getSupportedPlugType() {
        return supportedPlugType;
    }
}
