
package to.garazuj.converter;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.imgscalr.Scalr.Mode;
import org.springframework.stereotype.Component;

@Component
public class ImgResizer {

    private static int WIDTH = 96;
    private static int HEIGHT = 96;

    public ImgResizer() {
    }

    public static byte[] resize(byte[] imageDataFile, String formatName) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageDataFile));
        BufferedImage bufferedThumbnail = Scalr
                .resize(bufferedImage, Method.QUALITY, Mode.FIT_TO_WIDTH, WIDTH, HEIGHT,
                        Scalr.OP_ANTIALIAS);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedThumbnail, formatName, outputStream);

        return outputStream.toByteArray();
    }

    //lepsza ale dluzsza
    public static byte[] bicubicResize(byte[] imageDataFile, String formatName) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageDataFile));
        BufferedImage bufferedThumbnail = Scalr
                .resize(bufferedImage, Method.QUALITY, Mode.FIT_TO_WIDTH, WIDTH, HEIGHT,
                        Scalr.OP_ANTIALIAS);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedThumbnail, formatName, outputStream);

        return outputStream.toByteArray();
    }

}
