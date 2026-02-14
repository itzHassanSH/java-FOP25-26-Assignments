package h09.device;

import h09.connection.Connection;
import h09.exceptions.ConnectionClosedException;
import h09.exceptions.InternetException;
import h09.exceptions.packet.PacketException;
import h09.utils.TCPUtils;
import h09.packet.Packet;
import h09.utils.ThrowingPacketWaiter;
import h09.utils.Verbose;
import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.SolutionOnly;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import static h09.packet.PacketType.*;

import java.util.concurrent.ThreadLocalRandom;

/**
 * A TCP client implementation that extends the abstract {@link Client} class.
 * Implements TCP-specific functionality for establishing connections,
 * sending and receiving data with proper sequencing and acknowledgment.
 */
@DoNotTouch
public class TCPClient extends Client {

    /**
     * The current sequence number for TCP communication.
     */
    private int sequence;

    public TCPClient(int serverPort) throws InternetException {
        super(serverPort);
        sequence = ThreadLocalRandom.current().nextInt(1, 1000);
    }

    /**
     * Connects to the remote server by sending a SYN packet and awaiting
     * a response SYN packet from the server. Also validates the returned
     * packet.
     *
     * @throws InternetException If there is an error with the network connection
     * @throws PacketException   If there is an error with packet handling
     */
    @Override
    @StudentImplementationRequired("H9.4.1")
    public void connect() throws InternetException, PacketException {
        Connection con = getConn();

        Packet receivedPacket = TCPUtils.try3Times( () -> {
            con.sendPacket(sequence+1, SYN, null);
            return con.waitForPacketTimeout(5000);
        }, sequence+2);

        receivedPacket.expectType(SYN);
        receivedPacket.expectSequenceNumber(sequence+=2);
        receivedPacket.validateChecksum();

        // TODO: H9.4.1 - remove if implemented
    }

    /**
     * Sends a {@link String} of variable, unbounded length to the remote server. Packs the
     * data into one or more DATA packets if necessary. For each DATA packet
     * also awaits the acknowledgement packet and validates it.
     * <p>
     * After all data is sent, a DATA packet with content {@code "<EOF>"} is sent
     * to signal the end of the client's data. The response ACK is also validated.
     *
     * @param data The data to send
     * @throws InternetException        If there is an error with the network connection
     * @throws PacketException          If there is an error with packet handling
     * @throws IllegalArgumentException if data is null
     */
    @Override
    @StudentImplementationRequired("H9.4.2")
    public void send(String data) throws InternetException, PacketException {
        if (data.isEmpty()) {
            throw new IllegalArgumentException("Data is null");
        }

        Connection con = getConn();

        int loopCount = Math.round((float) data.length()/8);
        for (int i = 0; i < loopCount; i++) {
            String dataPacked = data.substring(i * 8, Math.min(i*8+8, data.length()));

            Packet acknowledgement = TCPUtils.try3Times(() -> {
                con.sendPacket(sequence+1, DATA, dataPacked);
                return con.waitForPacketTimeout(5000);
            }, sequence+2+dataPacked.length());

            acknowledgement.expectType(ACK);
            acknowledgement.expectSequenceNumber(sequence+= 2+dataPacked.length());
            acknowledgement.validateChecksum();
        }
        // Without this part of the code, the server throws PacketTypeException in receive, since from its side the sending never ended, as an
        // EOF was never sent out.
        Packet acknowledgement = TCPUtils.try3Times(() -> {
            con.sendPacket(sequence+1, DATA, "<EOF>");
            return con.waitForPacketTimeout(5000);
        }, sequence+2+5);

        acknowledgement.expectType(ACK);
        acknowledgement.expectSequenceNumber(sequence+= 2+5);
        acknowledgement.validateChecksum();

         // TODO: H9.4.2 - remove if implemented
    }

    /**
     * Retrieves the message from the remote server, combining multiple
     * DATA packets if necessary.
     * <p>
     * First sends an ACK to signal that the server can now send data.
     * Then awaits a DATA packet from the server, validates it and responds
     * with an acknowledgement. Repeats receiving and acknowledging data
     * until the server sends a DATA packet with {@code "<EOF>"}.
     * The EOF packet does not need to be acknowledged.
     *
     * @return the complete message from the server
     * @throws InternetException If there is an error with the network connection
     * @throws PacketException   If there is an error with packet handling
     */
    @Override
    @StudentImplementationRequired("H9.4.3")
    public String receive() throws InternetException, PacketException {
        Connection con = getConn();

        String data = "";
        StringBuilder dataCombined = new StringBuilder();
        while (!data.equals("<EOF>")) {
            Packet dataPacket = TCPUtils.try3Times(() -> {
                con.sendPacket(sequence+1, ACK, null);
                return con.waitForPacket();
            }, sequence + 2);
            dataPacket.expectType(DATA);
            dataPacket.expectSequenceNumber(sequence += 2);
            dataPacket.validateChecksum();

            data = dataPacket.getData();
            // Dont append EOF
            if (!data.equals("<EOF>")) {
                dataCombined.append(data);
            }
            sequence += dataPacket.getData().length();
        }

        return dataCombined.toString(); // TODO: H9.4.3 - remove if implemented
    }

    /**
     * Closes the connection. Sends a CLOSE packet to the
     * server with a sequence number of {@code Integer.MAX_VALUE}.
     * If the connection is already closed or another error occurs
     * while sending the packet, the error is caught and ignored.
     * At the end calls close on the superclass.
     */
    @Override
    @StudentImplementationRequired("H9.4.4")
    public void close() {
        try {
            Connection con = getConn();
            con.sendPacket(Integer.MAX_VALUE, CLOSE, null);
        }
        catch ( InternetException exc) {
            Verbose.out.println(exc.getMessage());
        }
        // supposed to do:
        super.close();
        // TODO: H9.4.4 - remove if implemented
    }
}
