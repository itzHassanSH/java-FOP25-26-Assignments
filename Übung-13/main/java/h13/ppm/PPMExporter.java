package h13.ppm;

import h13.ppm.image.PPMHeader;
import h13.ppm.image.PPMImage;
import h13.ppm.image.PixelColor;
import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.SolutionOnly;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import java.io.IOException;
import java.io.OutputStream;

/**
 * A PPMExporter exports an in-memory {@link PPMImage} into a given {@link OutputStream}.
 * There is only one output.
 */
public class PPMExporter implements AutoCloseable {

    private final OutputStream output;

    /**
     * Creates a new {@link PPMExporter} with a given {@link OutputStream}.
     *
     * @param output the {@link OutputStream} to write the image's bytes to
     */
    @DoNotTouch
    public PPMExporter(OutputStream output) {
        this.output = output;
    }

    /**
     * Writes the image's bytes into the output
     *
     * @param image the image to be written
     * @throws IOException if an I/O error occurs
     */
    @StudentImplementationRequired("H13.1.3")
    public void export(PPMImage image) throws IOException{
        // TODO: H13.1.3
        emitHeader(image.header());
        emitPixelData(image.pixelData());
        output.flush();
    }

    /**
     * Writes entire the header into output including magic bytes, width, height and max. color.
     * The terminating whitespace character is also written here.
     *
     * @param header the header to be written
     * @throws IOException if an I/O error occurs
     */
    @StudentImplementationRequired("H13.1.3")
    public void emitHeader(PPMHeader header) throws IOException {
        // TODO: H13.1.3
        output.write((byte) (80 & 0xFF));
        output.write((byte) (54 & 0xFF));
        output.write((byte) 32);

        emitDecimal(header.width());
        output.write((byte) 32);
        emitDecimal(header.height());
        output.write((byte) 32);
        emitDecimal(255);
        output.write((byte) 32);

    }

    /**
     * Writes the bytes representing all pixels of an image.
     *
     * @param pixelData the image's pixel row-based
     * @throws IOException if an I/O error occurs
     */
    @StudentImplementationRequired("H13.1.3")
    public void emitPixelData(PixelColor[][] pixelData) throws IOException {
        // TODO: H13.1.3
        for (int i = 0; i < pixelData.length; i ++) {
            for (int j = 0; j < pixelData[i].length; j++) {
                PixelColor pixelColor = pixelData[i][j];
                output.write(pixelColor.red());
                output.write(pixelColor.green());
                output.write(pixelColor.blue());
            }
        }
    }

    /**
     * Writes an integer in decimal format as per PPM spec.
     *
     * @param i the integer to be written as decimal
     * @throws IOException if an I/O error occurs
     */
    @DoNotTouch
    private void emitDecimal(int i) throws IOException {
        String s = String.valueOf(i);
        for (char c : s.toCharArray()) {
            output.write((byte) c); // must be ascii character
        }
    }

    @DoNotTouch
    @Override
    public void close() throws IOException {
        output.close(); // close underlying stream
    }
}
