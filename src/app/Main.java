package app;

import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) {
//        BufferedImage image = ManipulaImagem.abrirImagem("C:\\Users\\julio\\Downloads\\quadrado.jpg");
//        int height = image.getHeight();
//        int width = image.getWidth();
//        System.out.println("largura: " + width);
//        System.out.println("altura: " + height);
//        System.out.println("total de pixels: " + (height * width));
        BufferedImage image2 = ManipulaImagem.abrirImagem("C:\\Users\\julio\\Downloads\\menino.png");
        int height2 = image2.getHeight();
        int width2 = image2.getWidth();

//        Color blue = new Color(0,0,255);
//        Color green = new Color(0,255,0);
//        Color red = new Color(255,0,0);
//        image.setRGB(0,0, blue.getRGB());
//        image.setRGB(width/2, height/2, green.getRGB());
//        image.setRGB(width-1, height-1, red.getRGB());
//
//        System.out.println(Arrays.toString(image.getRGB(0, 0, width, height, null, 0, width)));

//        OperacoesPontuais.criarBandasRGB(image2, height2, width2);
//        OperacoesPontuais.criarNegativo(image2, height2, width2);
//        OperacoesPontuais.criarGrayscale(image2, height2, width2);
//        OperacoesPontuais.criarBinarizacao(image2, height2, width2);
//        OperacoesPontuais.aumentoTonalidade(image2, height2, width2, "red", 50);
//        OperacoesPontuais.criarBrilhoAumentado(image2, height2, width2, 50);
//        OperacoesPontuais.criarBrilhoAumentadoMulti(image2, height2, width2, 1.6f);
//        ManipulaImagem.exibirImagens(image2);
//        OperacoesPontuais.criarBrilhoAumentadoEmY(image2, height2, width2, 100);
//        OperacoesPontuais.criarBrilhoAumentadoEmYMulti(image2, height2, width2, 2);
//        OperacoesPontuais.criarNegativoEmY(image2, height2, width2);
//        Color thiscolor = new Color(image2.getRGB(2000, 500));
//        System.out.println(thiscolor);
//        double[] myYIQ = OperacoesPontuais.RGBtoYIQ(thiscolor);
//        OperacoesPontuais.YIQtoRGB(myYIQ);

//        ManipulaImagem.salvarImagem(image2, "jpg", new File("C:\\Users\\julio\\Downloads\\imagem_salva.jpg"));

//        ------ OPERAÇÕES LOCAIS ------
        int[][] horizontal = {{-1, -2, -1}, {0, 0, 0}, {1, 2, 1}};
        int[][] vertical = {{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}};
        int[][] linhasOeste = {{1, 1, -1}, {1, -2, -1}, {1, 1, -1}};
        int[][] laplaciano = {{0, -1, 0}, {-1, 4, -1}, {0, -1, 0}};
//        OperacoesLocais.convolucionalGaussiano(image2);
//        OperacoesLocais.convolucionalMedia(image2);
//        OperacoesLocais.convolucionalMediana(image2);
        OperacoesLocais.convolucional(image2, horizontal);
//        OperacoesLocais.convolucional(image2, vertical);
//        OperacoesLocais.convolucional(image2, linhasOeste);
//        OperacoesLocais.convolucional(image2, laplaciano);
    }

}