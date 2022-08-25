import java.util.ArrayList;

public class Ansiolitico {

    private static final double[] probabilidades = {1.0, 0.37, 0.49, 0.50, 0.49, 0.44, 0.48, 0.49, 0.49, 0.60, 1.0};

    public static int[][] ansiolitico(int iteracoes, int[] estados, int[][] matrizPassosAnsiolitico) {

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

                        if (listaNumerosAleatorios.get(numeroAleatorio) <= probabilidade){

                            posicao = estados[numeroPosicao - 1];
                            trajetoria.add(posicao);
                            probabilidade = probabilidades[numeroPosicao - 1];
                            parada = true;

                            if (matrizPassosAnsiolitico[numeroPosicao - 1][iteracao] == 0) {
                                matrizPassosAnsiolitico[numeroPosicao - 1][iteracao] = contadorPassos;
                            }

                            numeroPosicao -= 1;

                        } else if (listaNumerosAleatorios.get(numeroAleatorio) > probabilidade) {

                            posicao = estados[numeroPosicao + 1];
                            trajetoria.add(posicao);
                            probabilidade = probabilidades[numeroPosicao + 1];
                            parada = true;

                            if (matrizPassosAnsiolitico[numeroPosicao + 1][iteracao] == 0){
                                matrizPassosAnsiolitico[numeroPosicao + 1][iteracao] = contadorPassos;
                            }

                            numeroPosicao += 1;
                        }

                    } else if (numeroPosicao == 0 && listaNumerosAleatorios.get(numeroAleatorio) <= probabilidade) {

                        posicao = estados[numeroPosicao + 1];
                        trajetoria.add(posicao);
                        probabilidade = probabilidades[numeroPosicao + 1];
                        parada = true;
                        chegadaExtremidade1 = true;

                        if (matrizPassosAnsiolitico[numeroPosicao][iteracao] == 0) {
                            matrizPassosAnsiolitico[numeroPosicao][iteracao] = contadorPassos;
                        }

                        numeroPosicao += 1;

                    } else if (numeroPosicao == 10 && listaNumerosAleatorios.get(numeroAleatorio) <= probabilidade) {

                        posicao = estados[numeroPosicao - 1];
                        trajetoria.add(posicao);
                        probabilidade = probabilidades[numeroPosicao - 1];
                        parada = true;
                        chegadaExtremidade11 = true;

                        if (matrizPassosAnsiolitico[numeroPosicao][iteracao] == 0) {
                            matrizPassosAnsiolitico[numeroPosicao][iteracao] = contadorPassos;
                        }

                        numeroPosicao -= 1;
                    }
                }
                numeroAleatorio += 1;
            }
        }
        return matrizPassosAnsiolitico;
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

        System.out.println("\n\t\t\t\t ANSIOLÍTICO");
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
