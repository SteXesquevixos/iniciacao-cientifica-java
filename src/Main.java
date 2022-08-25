public class Main {

    public static void main(String[] args) {
        int[] estados = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        int iteracoes = 500;

        int[][] matrizPassosAnsiogenico = new int[11][iteracoes];
        int[][] matrizPassosAnsiolitico = new int[11][iteracoes];
        int[][] matrizPassosControle    = new int[11][iteracoes];

        Ansiogenico.ansiogenico(iteracoes, estados, matrizPassosAnsiogenico);
        Ansiolitico.ansiolitico(iteracoes, estados, matrizPassosAnsiolitico);
        Controle.controle(iteracoes, estados, matrizPassosControle);

        Ansiogenico.imprimirMatriz(matrizPassosAnsiogenico);
        Ansiolitico.imprimirMatriz(matrizPassosAnsiolitico);
        Controle.imprimirMatriz(matrizPassosControle);

        Ansiogenico.tempoMedioPrimeiraVisita(matrizPassosAnsiogenico, iteracoes);
        Ansiolitico.tempoMedioPrimeiraVisita(matrizPassosAnsiolitico, iteracoes);
        Controle.tempoMedioPrimeiraVisita(matrizPassosControle, iteracoes);
    }

}
