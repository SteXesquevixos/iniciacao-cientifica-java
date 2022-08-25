import java.util.ArrayList;

public class Controle {

    private static final double[] probabilidades = {1.0, 0.32, 0.46, 0.45, 0.46, 0.30, 0.42, 0.48, 0.49, 0.55, 1.0};

    public static int[][] controle(int iteracoes, int[] estados, int[][] matrizPassosAnsiogenico) {

        for (int iteracao = 0; iteracao < iteracoes; iteracao++){

            ArrayList<Double> listaNumerosAleatorios = new ArrayList<Double>();
            ArrayList<Integer> trajetoria = new ArrayList<Integer>();
            trajetoria.add(6);

            int numeroPosicao = 5;
            int numeroAleatorio = 0;
            int contadorPassos = 0;

            boolean chegadaExtremidade1 = false;
            boolean chegadaExtremidade11 = false;

            while (!chegadaExtremidade1 || !chegadaExtremidade11) {
                listaNumerosAleatorios.add((double)Math.round(Math.random() * 1000) / 1000);

                int posicao;
                double probabilidade = probabilidades[numeroPosicao];

                boolean parada = false;

                while (!parada) {

                    contadorPassos += 1;

                    if (numeroPosicao != 0 && numeroPosicao != 10) {

                        if (listaNumerosAleatorios.get(numeroAleatorio) <= probabilidade) {

                            posicao = estados[numeroPosicao - 1];
                            trajetoria.add(posicao);
                            probabilidade = probabilidades[numeroPosicao - 1];
                            parada = true;

                            if (matrizPassosAnsiogenico[numeroPosicao - 1][iteracao] == 0) {
                                matrizPassosAnsiogenico[numeroPosicao - 1][iteracao] = contadorPassos;
                            }

                            numeroPosicao -= 1;

                        } else if (listaNumerosAleatorios.get(numeroAleatorio) > probabilidade) {

                            posicao = estados[numeroPosicao + 1];
                            trajetoria.add(posicao);
                            probabilidade = probabilidades[numeroPosicao + 1];
                            parada = true;

                            if (matrizPassosAnsiogenico[numeroPosicao + 1][iteracao] == 0){
                                matrizPassosAnsiogenico[numeroPosicao + 1][iteracao] = contadorPassos;
                            }

                            numeroPosicao += 1;
                        }

                    } else if (numeroPosicao == 0 && listaNumerosAleatorios.get(numeroAleatorio) <= probabilidade) {

                        posicao = estados[numeroPosicao + 1];
                        trajetoria.add(posicao);
                        probabilidade = probabilidades[numeroPosicao + 1];
                        parada = true;
                        chegadaExtremidade1 = true;

                        if (matrizPassosAnsiogenico[numeroPosicao][iteracao] == 0) {
                            matrizPassosAnsiogenico[numeroPosicao][iteracao] = contadorPassos;
                        }

                        numeroPosicao += 1;

                    } else if (numeroPosicao == 10 && listaNumerosAleatorios.get(numeroAleatorio) <= probabilidade) {

                        posicao = estados[numeroPosicao - 1];
                        trajetoria.add(posicao);
                        probabilidade = probabilidades[numeroPosicao - 1];
                        parada = true;
                        chegadaExtremidade11 = true;

                        if (matrizPassosAnsiogenico[numeroPosicao][iteracao] == 0) {
                            matrizPassosAnsiogenico[numeroPosicao][iteracao] = contadorPassos;
                        }

                        numeroPosicao -= 1;
                    }
                }
                numeroAleatorio += 1;
            }
        }
        return matrizPassosAnsiogenico;
    }

    public static void imprimirMatriz(int[][] matrizPassosAnsiogenico) {
        for (int linha = 0; linha < matrizPassosAnsiogenico.length; linha++) {
            for (int coluna = 0; coluna < matrizPassosAnsiogenico[linha].length; coluna++) {
                System.out.print(matrizPassosAnsiogenico[linha][coluna] + "\t\t");
            }
            System.out.println();
        }
    }

    public static void tempoMedioPrimeiraVisita(int[][] matrizPassosAnsiogenico, int iteracoes) {
        double[] somas = new double[11];
        double[] medias = new double[11];

        for (int linha = 0; linha < matrizPassosAnsiogenico.length; linha++) {
            double soma = 0;
            for (int coluna = 0; coluna < matrizPassosAnsiogenico[linha].length; coluna++) {
                soma += matrizPassosAnsiogenico[linha][coluna];
                somas[linha] = soma;
            }
        }

        System.out.println("\n\t\t\t\t CONTROLE");
        System.out.println("ESTADOS \t TEMPO MÉDIO DE PRIMEIRA VISITA");
        for (int i = 0; i < somas.length; i++) {
            if (i != 5) {
                medias[i] = somas[i] / iteracoes;
            } else {
                continue;
            }
            System.out.printf("%d \t\t\t %.2f\n",i+1, medias[i]);
        }
    }

}
