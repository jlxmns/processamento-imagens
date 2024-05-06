package app;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

public class OperacoesLocais {
    public static void convolucionalMedia(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage output = new BufferedImage(width, height, image.getType());

        for(int h = 0; h < height; h++) {
            for(int w = 0; w < width; w++) {
                if (h == 0 || w == 0 || h == height - 1 || w == width - 1) {
                    int rgb = image.getRGB(w, h);
                    output.setRGB(w, h, rgb);
                    continue;
                }

                int[] vizinhanca = new int[9];
                int count = 0;
                for(int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        Color cor = new Color(image.getRGB(w + j, h + i));
                        vizinhanca[count++] = cor.getRed();
                    }
                }

                int media = Arrays.stream(vizinhanca).sum() / 9;

                Color novaCor = new Color(media, media, media);
                output.setRGB(w, h, novaCor.getRGB());
            }
        }

        ManipulaImagem.salvarImagem(output, "png", new File("C:\\Users\\julio\\Downloads\\meninoMedia.png"));
    }

    public static void convolucionalMediana (BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage output = new BufferedImage(width, height, image.getType());

        for(int h = 0; h < height; h++) {
            for(int w = 0; w < width; w++) {
                if (h == 0 || w == 0 || h == height - 1 || w == width - 1) {
                    int rgb = image.getRGB(w, h);
                    output.setRGB(w, h, rgb);
                    continue;
                }

                int[] vizinhanca = new int[9];
                int count = 0;
                for(int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        Color cor = new Color(image.getRGB(w + j, h + i));
                        vizinhanca[count++] = cor.getRed();
                    }
                }

                Arrays.sort(vizinhanca);
                int mediana = vizinhanca[4];

                Color novaCor = new Color(mediana, mediana, mediana);
                output.setRGB(w, h, novaCor.getRGB());
            }
        }

        ManipulaImagem.salvarImagem(output, "png", new File("C:\\Users\\julio\\Downloads\\meninoMediana.png"));
    }

    public static void convolucionalGaussiano(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage output = new BufferedImage(width, height, image.getType());

        for(int h = 0; h < height; h++) {
            for(int w = 0; w < width; w++) {
                if (h == 0 || w == 0 || h == height - 1 || w == width - 1) {
                    int rgb = image.getRGB(w, h);
                    output.setRGB(w, h, rgb);
                    continue;
                }

                int[] vizinhanca = new int[9];
                int count = 0;
                double[][] gaussianFilter = {
                        {0.0625, 0.125, 0.0625},
                        {0.125, 0.25, 0.125},
                        {0.0625, 0.125, 0.0625}
                };
                for(int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        Color cor = new Color(image.getRGB(w + j, h + i));
                        vizinhanca[count++] = (int) (cor.getRed() * gaussianFilter[i+1][j+1]);
                    }
                }


                int soma = Arrays.stream(vizinhanca).sum();

                Color novaCor = new Color(soma, soma, soma);
                output.setRGB(w, h, novaCor.getRGB());
            }
        }

        ManipulaImagem.salvarImagem(output, "png", new File("C:\\Users\\julio\\Downloads\\meninoGaussiano.png"));
    }

    public static void convolucional(BufferedImage image, int[][] kernel) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage output = new BufferedImage(width, height, image.getType());

        for(int h = 0; h < height; h++) {
            for(int w = 0; w < width; w++) {
                if (h == 0 || w == 0 || h == height - 1 || w == width - 1) {
                    int rgb = image.getRGB(w, h);
                    output.setRGB(w, h, rgb);
                    continue;
                }

                int[] vizinhanca = new int[9];
                int count = 0;
                int resultado = 0;
                for(int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        Color cor = new Color(image.getRGB(w + j, h + i));
                        vizinhanca[count] = cor.getRed() * kernel[i+1][j+1];
                        if (vizinhanca[count] > 255)
                            vizinhanca[count] = 255;
                        else if (vizinhanca[count] < 0)
                            vizinhanca[count] = 0;
//                        AJEITAR !!!!!
                        resultado += vizinhanca[count];
                        count++;
                    }
                }


                int soma = Arrays.stream(vizinhanca).sum() / 9;

                Color novaCor = new Color(soma, soma, soma);
                output.setRGB(w, h, novaCor.getRGB());
            }
        }

        ManipulaImagem.salvarImagem(output, "png", new File("C:\\Users\\julio\\Downloads\\meninoConvolucional.png"));
    }
}