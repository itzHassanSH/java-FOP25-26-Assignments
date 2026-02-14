package h09.exceptions.packet;

import h09.packet.PacketType;

public class PacketSequenceException extends PacketException {
    public PacketSequenceException (int expected, int got) {
        super("Expected: " + expected + ", Got: " +  got);
    }

}
