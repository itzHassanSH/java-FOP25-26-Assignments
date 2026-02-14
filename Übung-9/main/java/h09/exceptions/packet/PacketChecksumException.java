package h09.exceptions.packet;

import h09.packet.PacketType;

public class PacketChecksumException extends PacketException {
    public PacketChecksumException (int expected, int got) {
        super("Expected: " + expected + ", Got: " +  got);
    }

}
