import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\julio\\Downloads\\auckland-1326209457.jpg");
        try {
            BufferedImage image = ImageIO.read(file);
            int height = image.getHeight();
            int width = image.getWidth();
            System.out.println("largura: " + width);
            System.out.println("altura: " + height);
            System.out.println("total de pixels: " + (height * width));
            File saved_image = new File("C:\\Users\\julio\\Downloads\\imagem_salva.jpg");
            ImageIO.write(image, "jpg", saved_image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}