package h06;

public class FuelPoweredCar extends FuelPoweredVehicle{
    private static final double BASE_CONSUMPTION = 6.5;
    public FuelPoweredCar(FuelType fuelType, int fillingLevel) {
        super(fuelType, TransportType.CAR, fillingLevel);
    }

    @Override
    public double letMeMove(int distance) {
        // Return should be explicitly in Double and the correct value! Same goes for DriveableLawnMower
        if (!motorRunning) {
            // return 0.0;
            return 0;
        }
        double distanceKm = (double) distance/1000;
        double fuelConsumed = (distanceKm/100) * BASE_CONSUMPTION;
        int fuelConsumedInt = Math.min(fillingLevel, ((int) fuelConsumed));

        fillingLevel -= fuelConsumedInt;

        // return fuelConsumed (not fuelConsumedInt)
        return fuelConsumedInt;
    }
}
