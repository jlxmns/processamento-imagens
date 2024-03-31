package app;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        BufferedImage image = ManipulaImagem.abrirImagem("C:\\Users\\julio\\Downloads\\quadrado.jpg");
        int height = image.getHeight();
        int width = image.getWidth();
        System.out.println("largura: " + width);
        System.out.println("altura: " + height);
        System.out.println("total de pixels: " + (height * width));
        BufferedImage image2 = ManipulaImagem.abrirImagem("C:\\Users\\julio\\Downloads\\EXTERNO_01.png");
        int height2 = image2.getHeight();
        int width2 = image2.getWidth();

        Color blue = new Color(0,0,255);
        Color green = new Color(0,255,0);
        Color red = new Color(255,0,0);
        image.setRGB(0,0, blue.getRGB());
        image.setRGB(width/2, height/2, green.getRGB());
        image.setRGB(width-1, height-1, red.getRGB());

        System.out.println(Arrays.toString(image.getRGB(0, 0, width, height, null, 0, width)));

//        OperacoesPontuais.criarBandasRGB(image2, height2, width2);

//        OperacoesPontuais.criarNegativo(image2, height2, width2);
//        OperacoesPontuais.criarGrayscale(image2, height2, width2);
//        OperacoesPontuais.criarBinarizacao(image2, height2, width2);
//        OperacoesPontuais.aumentoTonalidade(image2, height2, width2, "red", 50);
//        OperacoesPontuais.criarBrilhoAumentado(image2, height2, width2, 50);
        OperacoesPontuais.criarBrilhoAumentadoMulti(image2, height2, width2, 0.5f);
//        ManipulaImagem.exibirImagens(image2);

//        ManipulaImagem.salvarImagem(image2, "jpg", new File("C:\\Users\\julio\\Downloads\\imagem_salva.jpg"));
    }

}