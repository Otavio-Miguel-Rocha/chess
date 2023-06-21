import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static Jogador jogadorBrancas = new Jogador("Jorge", "Senh@123");
    static Jogador jogadorPretas = new Jogador("Wilson", "wilson");
    static Tabuleiro tabuleiro = new Tabuleiro();

    public static void main(String[] args) {
        definicoesPreJogo();
        while (true) {
            tabuleiro();
            System.out.println(jogadorBrancas.getPecas());
            int escolha = sc.nextInt();
            Peca peca = jogadorBrancas.getPecas().get(escolha);
            System.out.println(peca);
            //Escolha da posição
            ArrayList<Posicao> posicoes = peca.possiveisMovimentos(tabuleiro);
            System.out.println(posicoes);
            int escolherPosicao = sc.nextInt();
            Posicao posicao = posicoes.get(escolherPosicao);
            // Movimentação da peça escolhida para a posiçaõ desejada
            jogadorBrancas.moverPeca(peca, posicao, tabuleiro, jogadorPretas);
            validarVitoria(jogadorPretas);
        }
    }

    private static void tabuleiro() {
        for (Posicao posicao : tabuleiro.getPosicoes()) {
            int posicaoNoTabuleiro = tabuleiro.getPosicoes().indexOf(posicao);
            if ((posicaoNoTabuleiro % 8 == 0) || ((posicaoNoTabuleiro + 1) == 64)) {
                if (posicaoNoTabuleiro == 8) {
                    System.out.print("    H ");
                }
                if (posicaoNoTabuleiro == 16) {
                    System.out.print("    G ");
                }
                if (posicaoNoTabuleiro == 24) {
                    System.out.print("    F ");
                }
                if (posicaoNoTabuleiro == 32) {
                    System.out.print("    E ");
                }
                if (posicaoNoTabuleiro == 40) {
                    System.out.print("    D ");
                }
                if (posicaoNoTabuleiro == 48) {
                    System.out.print("    C ");
                }
                if (posicaoNoTabuleiro == 56) {
                    System.out.print("    B ");
                }
                if (posicaoNoTabuleiro == 63) {
                    System.out.print("    A ");
                }
                if (posicaoNoTabuleiro != 1 || posicaoNoTabuleiro != 64) {
                    System.out.println("\n------------------------------------------------------------------");
                }
            }
            if (posicao.getPeca() != null) {
                System.out.print("  " + posicao.getPeca() + "  ");
            } else {
                System.out.print("     ");
            }
            System.out.print(" | ");
        }
    }

    private static void definicoesPreJogo() {
        jogadorBrancas.setCor("Branco", tabuleiro);
        jogadorPretas.setCor("Preto", tabuleiro);
    }

    private static boolean validarVitoria(Jogador adversario) {
        for (Peca peca : adversario.getPecas()) {
            if (peca instanceof Rei) {
                return false;
            }
        }
        return true;
    }
}
