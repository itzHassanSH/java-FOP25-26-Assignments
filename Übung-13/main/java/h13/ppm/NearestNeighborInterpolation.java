package h13.ppm;

import h13.ppm.image.PPMHeader;
import h13.ppm.image.PPMImage;
import h13.ppm.image.PixelColor;
import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

/**
 * Class to provide class method that implements nearest neighbor-interpolation of images.
 */
public final class NearestNeighborInterpolation {

    @DoNotTouch
    private NearestNeighborInterpolation() {
    }

    /**
     * Scales an image using nearest neighbor-interpolation given a scale factor.
     * The new image's dimensions are the originals scaled by scale factor (rounded towards 0).
     *
     * @param original    the original image to be scaled
     * @param scaleFactor the factor by which the image's dimensions should be scaled
     * @return a new image scaled by scaleFactor
     */
    @StudentImplementationRequired("H13.1.2")
    public static PPMImage scaleImage(PPMImage original, float scaleFactor) {
        // TODO: H13.1.2
        int width = original.header().width();
        int height = original.header().height();
        int widthNew = (int) (width * scaleFactor);
        int heightNew = (int) (height * scaleFactor);
        PixelColor[][] newPixelColours = new PixelColor[heightNew][widthNew];

        for (int i = 0; i<heightNew; i++) {
            int yOrig = Math.clamp(Math.round(i / scaleFactor), 0, height);
            for (int j = 0; j<widthNew; j++) {
                int xOrig = Math.clamp(Math.round(j / scaleFactor), 0, width);
                newPixelColours[i][j] = original.pixelData()[yOrig][xOrig];

            }
        }
        return new PPMImage(new PPMHeader(widthNew, heightNew), newPixelColours);
    }
}
