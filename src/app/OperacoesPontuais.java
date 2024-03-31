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

        ManipulaImagem.salvarImagem(imageB, "jpg", new File("C:\\Users\\julio\\Downloads\\bandaBlue.jpg"));
        ManipulaImagem.salvarImagem(imageG, "jpg", new File("C:\\Users\\julio\\Downloads\\bandaGreen.jpg"));
        ManipulaImagem.salvarImagem(imageR, "jpg", new File("C:\\Users\\julio\\Downloads\\bandaRed.jpg"));
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

        ManipulaImagem.salvarImagem(negativo, "jpg", new File("C:\\Users\\julio\\Downloads\\negativo.jpg"));
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

        ManipulaImagem.salvarImagem(grayscale, "jpg", new File("C:\\Users\\julio\\Downloads\\grayscale.jpg"));

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

        ManipulaImagem.salvarImagem(binarizacao, "jpg", new File("C:\\Users\\julio\\Downloads\\binarizacao.jpg"));
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
                Color newColor = null;

                if (tipo.equalsIgnoreCase("red")) {
                    if (checarCorValida(red + valor).equals("true")) {
                        newColor = new Color(red + valor, green, blue);
                    } else if (checarCorValida(red + valor).equals("menor")) {
                        newColor = new Color(0, green, blue);
                    } else {
                        newColor = new Color(255, green, blue);
                    }
                } else if (tipo.equalsIgnoreCase("green")) {
                    if (checarCorValida(green + valor).equals("true")) {
                        newColor = new Color(red, green + valor, blue);
                    } else if (checarCorValida(red + valor).equals("menor")) {
                        newColor = new Color(red, 0, blue);
                    } else {
                        newColor = new Color(red, 255, blue);
                    }
                } else {
                    if (checarCorValida(blue + valor).equals("true")) {
                        newColor = new Color(red, green, blue + valor);
                    } else if (checarCorValida(red + valor).equals("menor")) {
                        newColor = new Color(red, green, 0);
                    } else {
                        newColor = new Color(red, green, 255);
                    }
                }
                tonalidadeAumentada.setRGB(i, j, newColor.getRGB());
            }
        }

        ManipulaImagem.salvarImagem(tonalidadeAumentada, "jpg", new File("C:\\Users\\julio\\Downloads\\tonalidadeAumentada.jpg"));

    }

    public static void criarBrilhoAumentado(BufferedImage image, int height, int width, int valor) {
        BufferedImage brilhoAumentado = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color myColor = new Color(image.getRGB(i, j));
                int red = myColor.getRed();
                int green = myColor.getGreen();
                int blue = myColor.getBlue();
                Color newColor = null;
                red += valor;
                green += valor;
                blue += valor;

                if (checarCorValida(red).equals("menor")) {
                    red = 0;
                } else if (checarCorValida(red).equals("maior")) {
                    red = 255;
                }

                if (checarCorValida(green).equals("menor")) {
                    green = 0;
                } else if (checarCorValida(green).equals("maior")) {
                    green = 255;
                }

                if (checarCorValida(blue).equals("menor")) {
                    blue = 0;
                } else if (checarCorValida(blue).equals("maior")) {
                    blue = 255;
                }

                newColor = new Color(red, green, blue);
                brilhoAumentado.setRGB(i, j, newColor.getRGB());
            }
        }

        ManipulaImagem.salvarImagem(brilhoAumentado, "jpg", new File("C:\\Users\\julio\\Downloads\\brilhoAumentadoInt.jpg"));
    }

    public static void criarBrilhoAumentadoMulti(BufferedImage image, int height, int width, float valor) {
        BufferedImage brilhoAumentado = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color myColor = new Color(image.getRGB(i, j));
                int red = myColor.getRed();
                int green = myColor.getGreen();
                int blue = myColor.getBlue();
                Color newColor = null;
                red *= valor;
                green *= valor;
                blue *= valor;

                if (checarCorValida(red).equals("menor")) {
                    red = 0;
                } else if (checarCorValida(red).equals("maior")) {
                    red = 255;
                }

                if (checarCorValida(green).equals("menor")) {
                    green = 0;
                } else if (checarCorValida(green).equals("maior")) {
                    green = 255;
                }

                if (checarCorValida(blue).equals("menor")) {
                    blue = 0;
                } else if (checarCorValida(blue).equals("maior")) {
                    blue = 255;
                }

                newColor = new Color(red, green, blue);
                brilhoAumentado.setRGB(i, j, newColor.getRGB());
            }
        }

        ManipulaImagem.salvarImagem(brilhoAumentado, "jpg", new File("C:\\Users\\julio\\Downloads\\brilhoAumentadoMulti.jpg"));
    }

    private static String checarCorValida(int cor) {
        String result = "ok";
        if (cor > 255) {
            result = "maior";
        } else if (cor < 0) {
            result = "menor";
        }

        return result;
    }
}
