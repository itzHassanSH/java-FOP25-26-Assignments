package h06;

public class DriveableLawnMower extends FuelPoweredVehicle{
    // shouldve been private static final double BASE_CONSUMPTION
    protected static final double BASE_CONSUMPTION = 0.5;
    protected boolean bladeSpinning = false;
    public DriveableLawnMower(FuelType fuelType, int fillingLevel) {
        super(fuelType, TransportType.CAR, fillingLevel);
    }

    public boolean isBladeSpinning() {
        return bladeSpinning;
    }

    public void spinBlade() {
        if (motorRunning) {
            bladeSpinning = true;
        }
    }

    public void stopBlade() {
        bladeSpinning = false;
    }

    @Override
    public double letMeMove(int distance) {
        if (!motorRunning) {
            return 0;
        }
        double distanceKm = (double) distance/1000;
        double fuelConsumed = (distanceKm) * BASE_CONSUMPTION;
        int fuelConsumedInt = Math.min(fillingLevel, ((int) fuelConsumed));

        fillingLevel -= fuelConsumedInt;

        return fuelConsumedInt;
    }
}
