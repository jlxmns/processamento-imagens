package app;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ManipulaImagem {

    public static void exibirImagens(BufferedImage... images) {
        JFrame frame = new JFrame();
        frame.setTitle("Processamento de Imagens");
        frame.getContentPane().setLayout(new FlowLayout());
        for (BufferedImage image : images) {
            JLabel label = new JLabel(new ImageIcon(image));
            frame.getContentPane().add(label);
        }
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static BufferedImage abrirImagem(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void salvarImagem(BufferedImage image, String formatName, File file) {
        try {
            ImageIO.write(image, formatName, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
