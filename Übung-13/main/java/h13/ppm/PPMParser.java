package h13.ppm;

import h13.ppm.image.PPMHeader;
import h13.ppm.image.PPMImage;
import h13.ppm.image.PixelColor;
import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import java.io.IOException;
import java.io.InputStream;

/**
 * Parses a PPM encoded image from a given {@link InputStream}.
 */
public class PPMParser implements AutoCloseable {

    private final InputStream input;

    /**
     * Creates a new {@link PPMParser} that will read an image from the given {@link InputStream}
     *
     * @param input the {@link InputStream} to read from
     */
    @DoNotTouch
    public PPMParser(InputStream input) {
        this.input = input;
    }

    /**
     * Parses one complete image from the input.
     *
     * @return the parsed image
     * @throws IOException if an I/O error occurs
     */
    @StudentImplementationRequired("H13.1.1")
    public PPMImage parse() throws IOException {
        // TODO: H13.1.1
        PPMHeader header = parseHeader();
        PixelColor[][] pixelColors = parsePixelData(header.width(), header.height());
        return new PPMImage(header, pixelColors);
    }

    /**
     * Reads as many bytes as expected according to the image's width and height.
     *
     * @param width  the image's expected width
     * @param height the image's expected height
     * @return a 2-dimensional array containing pixel rows of the whole image
     * @throws IOException if an I/O error occurs
     */
    @StudentImplementationRequired("H13.1.1")
    public PixelColor[][] parsePixelData(int width, int height) throws IOException {
        // TODO: H13.1.1
        int b1, b2, b3;
        PixelColor[][] pixelColors = new PixelColor[height][width];
        for (int i = 0; i <height; i++) {
            for (int j = 0; j < width; j ++) {
                b1 = input.read();
                b2 = input.read();
                b3 = input.read();
                if (b1 == -1 || b2 == -1 || b3 == -1) {
                    throw new PPMFormatException("File ended although expected to continue");
                }
                // We use bitwise AND with int 255 to ensure the bottom 8 bits get captured - regardless of sign
                // This allows just the value to be captured to byte, which needs to be appropriately translated later
                // in case un-signed bits are required, since JAVA byte are automatically signed.
                pixelColors[i][j] = new PixelColor((byte) (b1 & 0xFF), (byte) (b2 & 0xFF), (byte) (b3 & 0xFF));
            }
        }
        if (input.read() != -1) {
            throw new PPMFormatException("File should've ended - however bytes still remaining");
        }
        return pixelColors;
    }

    /**
     * Parses the header from the input and returns its relevant information.
     * Only max color values of 255 are allowed.
     *
     * @return the parsed header
     * @throws IOException if an I/O error occur
     */
    @StudentImplementationRequired("H13.1.1")
    public PPMHeader parseHeader() throws IOException {
        // TODO: H13.1.1
        int b;
        b = input.read();
        if (b != 80) {
            throw new PPMFormatException("First magic bit wrong - expected: 80 got: %d".formatted(b));
        }
        b = input.read();
        if (b != 54) {
            throw new PPMFormatException("Second magic bit wrong - expected 54 got: %d".formatted(b));
        }
        b = input.read();
        if (!Character.isWhitespace(b)) {
            throw new PPMFormatException("Whitespace expected after magic bits - didn't find one");
        }
        int width = parseWhitespaceTerminatedDecimal();
        int height = parseWhitespaceTerminatedDecimal();
        int maxColour = parseWhitespaceTerminatedDecimal();
        if (maxColour == 255) {
            return new PPMHeader(width, height);
        } else {
            throw new PPMFormatException("max colour not equal to 255");
        }
    }

    /**
     * Parses an integer encoded in ASCII decimal as per PPM spec.
     * The decimals end is detected if there is a whitespace character.
     * The terminating whitespace character is also consumed from the stream
     *
     * @return the integer value of the parsed decimal
     * @throws IOException if an I/O error occur
     */
    @DoNotTouch
    private int parseWhitespaceTerminatedDecimal() throws IOException {
        StringBuilder decimal = new StringBuilder();

        int b;
        while (true) {
            b = input.read();
            if (b >= '0' && b <= '9') {
                int digit = b - '0';
                decimal.append(digit);
            } else if (Character.isWhitespace(b)) {
                break;
            } else {
                throw new PPMFormatException("Expected digit or whitespace");
            }
        }

        if (decimal.isEmpty()) {
            throw new PPMFormatException("Expected at least one decimal digit");
        }

        return Integer.parseInt(decimal.toString());
    }

    @DoNotTouch
    @Override
    public void close() throws IOException {
        input.close(); // close underlying stream
    }
}
