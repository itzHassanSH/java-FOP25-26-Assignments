package h06;

/**
 * Base class for a transport vehicle
 */
public abstract class MeansOfTransport {
    protected final TransportType transportType;

    /**
     * Constructs a new {@link MeansOfTransport} instance.
     */
    public MeansOfTransport(TransportType transportType) {
        this.transportType = transportType;
    }

    /**
     * Returns the type of vehicle
     *
     * @return the type of vehicle
     */
    public TransportType getTransportType() {
        return transportType;
    }

    /**
     * Simulates the vehicle moving over a {@code distance} and returns amount of Energy consumed
     *
     * @param distance How far the car should move (in metres)
     * @return the amount of Energy consumed
     */
    public abstract double letMeMove(int distance);
}
