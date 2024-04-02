package app;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class OperacoesPontuais {
    public static void criarBandasRGB(BufferedImage image2, int height2, int width2) {
        BufferedImage imageR = new BufferedImage(width2, height2, BufferedImage.TYPE_INT_RGB);
        BufferedImage imageG = new BufferedImage(width2, height2, BufferedImage.TYPE_INT_RGB);
        BufferedImage imageB = new BufferedImage(width2, height2, BufferedImage.TYPE_INT_RGB);

        for(int i = 0; i < width2; i++) {
            for(int j = 0; j < height2; j++) {
                Color myColor = new Color(image2.getRGB(i, j));

                Color novaCor = new Color(0,0, myColor.getBlue());
                imageB.setRGB(i, j, novaCor.getRGB());
                novaCor = new Color(myColor.getRed(),0, 0);
                imageR.setRGB(i, j, novaCor.getRGB());
                novaCor = new Color(0, myColor.getRed(), 0);
                imageG.setRGB(i, j, novaCor.getRGB());

            }
        }

        ManipulaImagem.salvarImagem(imageB, "jpg", new File("C:\\Users\\autologon\\Downloads\\bandaBlue.jpg"));
        ManipulaImagem.salvarImagem(imageG, "jpg", new File("C:\\Users\\autologon\\Downloads\\bandaGreen.jpg"));
        ManipulaImagem.salvarImagem(imageR, "jpg", new File("C:\\Users\\autologon\\Downloads\\bandaRed.jpg"));
    }

    public static void criarNegativo(BufferedImage image, int height, int width) {
        BufferedImage negativo = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for(int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color myColor = new Color(image.getRGB(i, j));
                int myRed = myColor.getRed();
                int myGreen = myColor.getGreen();
                int myBlue = myColor.getBlue();
                Color newColor = new Color((255 - myRed), (255 - myGreen), (255 - myBlue));
                negativo.setRGB(i, j, newColor.getRGB());
            }
        }

        ManipulaImagem.salvarImagem(negativo, "jpg", new File("C:\\Users\\autologon\\Downloads\\negativo.jpg"));
    }

    public static BufferedImage criarGrayscale(BufferedImage image, int height, int width) {
        BufferedImage grayscale = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for(int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color myColor = new Color(image.getRGB(i, j));
                int myRed = myColor.getRed();
                int myGreen = myColor.getGreen();
                int myBlue = myColor.getBlue();
                int mediaCinza = (myRed + myGreen + myBlue)/3;
                Color newColor = new Color(mediaCinza, mediaCinza, mediaCinza);
                grayscale.setRGB(i, j, newColor.getRGB());
            }
        }

        ManipulaImagem.salvarImagem(grayscale, "jpg", new File("C:\\Users\\autologon\\Downloads\\grayscale.jpg"));

        return grayscale;
    }

    public static void criarBinarizacao(BufferedImage image, int height, int width) {
        BufferedImage binarizacao = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        BufferedImage grayscale = criarGrayscale(image, height, width);

        for(int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color myColor = new Color(grayscale.getRGB(i, j));
                int currentPixelColor = myColor.getBlue();
                Color newColor = null;
                if (currentPixelColor < 100) {
                    newColor = new Color(0,0,0);
                } else {
                    newColor = new Color(255, 255, 255);
                }
                binarizacao.setRGB(i, j, newColor.getRGB());
            }
        }

        ManipulaImagem.salvarImagem(binarizacao, "jpg", new File("C:\\Users\\autologon\\Downloads\\binarizacao.jpg"));
    }

    public static void aumentoTonalidade(BufferedImage image, int height, int width, String tipo, int valor) {
        BufferedImage tonalidadeAumentada = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        if (
                !tipo.equalsIgnoreCase("red")
                        && !tipo.equalsIgnoreCase("green")
                        && !tipo.equalsIgnoreCase("blue")
        ) {
            throw new RuntimeException("Tipo inválido.");
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color myColor = new Color(image.getRGB(i, j));
                int red = myColor.getRed();
                int green = myColor.getGreen();
                int blue = myColor.getBlue();

                if (tipo.equalsIgnoreCase("red")) {
                    red = checarCorValida(red, valor);
                } else if (tipo.equalsIgnoreCase("green")) {
                    green = checarCorValida(green, valor);
                } else {
                    blue = checarCorValida(blue, valor);
                }
                Color newColor = new Color(red, green, blue);
                tonalidadeAumentada.setRGB(i, j, newColor.getRGB());
            }
        }

        ManipulaImagem.salvarImagem(tonalidadeAumentada, "jpg", new File("C:\\Users\\autologon\\Downloads\\tonalidadeAumentada.jpg"));

    }

    public static void criarBrilhoAumentado(BufferedImage image, int height, int width, int valor) {
        BufferedImage brilhoAumentado = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color myColor = new Color(image.getRGB(i, j));
                int red = myColor.getRed();
                int green = myColor.getGreen();
                int blue = myColor.getBlue();
                red = checarCorValida(red, valor);
                green = checarCorValida(green, valor);
                blue = checarCorValida(blue, valor);

                Color newColor = new Color(red, green, blue);
                brilhoAumentado.setRGB(i, j, newColor.getRGB());
            }
        }

        ManipulaImagem.salvarImagem(brilhoAumentado, "jpg", new File("C:\\Users\\autologon\\Downloads\\brilhoAumentadoInt.jpg"));
    }

    public static void criarBrilhoAumentadoMulti(BufferedImage image, int height, int width, float valor) {
        BufferedImage brilhoAumentado = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color myColor = new Color(image.getRGB(i, j));
                int red = myColor.getRed();
                int green = myColor.getGreen();
                int blue = myColor.getBlue();
                red = checarCorValida(red, valor);
                green = checarCorValida(green, valor);
                blue = checarCorValida(blue, valor);

                Color newColor = new Color(red, green, blue);
                brilhoAumentado.setRGB(i, j, newColor.getRGB());
            }
        }

        ManipulaImagem.salvarImagem(brilhoAumentado, "jpg", new File("C:\\Users\\autologon\\Downloads\\brilhoAumentadoMulti.jpg"));
    }

    private static int checarCorValida(int cor, int valor) {
        cor += valor;
        if (cor > 255) cor = 255;
        else if (cor < 0) cor = 0;

        return cor;
    }

    private static int checarCorValida(int cor, float valor) {
        cor = (int)(cor * valor);
        if (cor > 255) cor = 255;
        else if (cor < 0) cor = 0;

        return cor;
    }
}
