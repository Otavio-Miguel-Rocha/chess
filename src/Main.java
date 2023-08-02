import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static Jogador jogadorBrancas = new Jogador("Jorge", "Senh@123");
    static Jogador jogadorPretas = new Jogador("Wilson", "wilson");
    static Tabuleiro tabuleiro = new Tabuleiro();

    public static void main(String[] args) {
        definicoesPreJogo();
        boolean fimDeJogo = false;
        for ( int i = 0 ; !fimDeJogo; i++ ) {
            tabuleiro();
            if(i % 2 == 0){
                System.out.println( jogadorBrancas.getNome() + " é a sua vez!");
                jogada(jogadorBrancas);
            } else {
                System.out.println( jogadorPretas.getNome() + " é a sua vez!");
                jogada(jogadorPretas);
            }
//            Peca peca = jogadorBrancas.getPecas().get(escolha);
//            System.out.println(peca);
//            //Escolha da posição
//            ArrayList<Posicao> posicoes = peca.possiveisMovimentos(tabuleiro);
//            System.out.println(posicoes);
//            int escolherPosicao = sc.nextInt();
//            Posicao posicao = posicoes.get(escolherPosicao);
//            // Movimentação da peça escolhida para a posiçaõ desejada
//            jogadorBrancas.moverPeca(peca, posicao, tabuleiro, jogadorPretas);
//            validarVitoria(jogadorPretas);
        }
    }

    private static void tabuleiro() {
        System.out.println(jogadorPretas.getNome());
        System.out.println(jogadorPretas.getPecasAdversarioAbatidas());

        System.out.print("      1      ");
        System.out.print("     2      ");
        System.out.print("     3      ");
        System.out.print("     4      ");
        System.out.print("     5      ");
        System.out.print("     6      ");
        System.out.print("     7      ");
        System.out.print("     8      \n");
        for (Posicao posicao : tabuleiro.getPosicoes()) {
            int posicaoNoTabuleiro = tabuleiro.getPosicoes().indexOf(posicao);
            if (posicaoNoTabuleiro % 8 == 0) {
                System.out.print("|  ");
            }
            if (posicao.getPeca() != null) {
                if (posicao.getPeca() instanceof Rei || posicao.getPeca() instanceof Rainha) {
                    System.out.print("  " + posicao.getPeca() + "  ");
                } else {
                    System.out.print("   " + posicao.getPeca() + "  ");
                }
            } else {
                System.out.print("       ");
            }

            if ((posicaoNoTabuleiro + 1) % 8 == 0 || posicaoNoTabuleiro == 63) {
                if (posicaoNoTabuleiro == 7) {
                    System.out.print(" |  H ");
                }
                if (posicaoNoTabuleiro == 15) {
                    System.out.print(" |  G ");
                }
                if (posicaoNoTabuleiro == 23) {
                    System.out.print(" |  F ");
                }
                if (posicaoNoTabuleiro == 31) {
                    System.out.print(" |  E ");
                }
                if (posicaoNoTabuleiro == 39) {
                    System.out.print(" |  D ");
                }
                if (posicaoNoTabuleiro == 47) {
                    System.out.print(" |  C ");
                }
                if (posicaoNoTabuleiro == 55) {
                    System.out.print(" |  B ");
                }
                if (posicaoNoTabuleiro == 63) {
                    System.out.print(" |  A ");
                }
                if (posicaoNoTabuleiro != 1 || posicaoNoTabuleiro != 64) {
                    System.out.print("\n--------------------------------------------------------" +
                            "----------------------------------------\n");
                }
            } else {
                System.out.print("  |  ");
            }
        }
        System.out.println(jogadorBrancas.getNome());
        System.out.println(jogadorBrancas.getPecasAdversarioAbatidas());
    }

    private static void definicoesPreJogo() {
        jogadorBrancas.setCor("Branco", tabuleiro);
        jogadorPretas.setCor("Preto", tabuleiro);
    }

    private static boolean jogada(Jogador jogadorDaVez){
        boolean validaJogada = false;
        do{
        //Solicita jogada para o jogador da vez
        System.out.println("Insira as coordenadas do tabuleiro: (por exemplo: B4)");
        //faz o request da posição conforme coordenada passada
        Posicao posicaoInserida = tabuleiro.verificarPosicao(sc.next());
        //verifica se a coordenada repassada é válida
        if (posicaoInserida != null) {
            //Verifica se existe uma peça naquela coordenada.
            if (posicaoInserida.getPeca() != null) {
                //Verifica se a peça selecionada é semelhante ao do jogador da vez
                if (posicaoInserida.getPeca().getCor().equals(jogadorDaVez.getCor())) {
                    String possiveisMovimentos = "";
                    //varre a lista e mostra as possíveis jogadas
                    for (Posicao posicao : posicaoInserida.getPeca().possiveisMovimentos(tabuleiro)) {
                        if (posicao.getPeca() != null) {
                            possiveisMovimentos += posicao.getPeca().getClass() + " - " + posicao.getID() + "\n";
                        } else {
                            possiveisMovimentos += "Espaço Vazio - " + posicao.getID() + "\n";
                        }
                    }
                    //verifica se existe algum possível movimento, verificando se a String foi alterada
                    if (possiveisMovimentos == "") {
                        System.out.println("Essa peça não possui nenhum possível movimento.");
                    } else {
                        //Solicita a posição final da peça selecionada
                        System.out.println("Insira a coordenada para a jogada da peça: ");
                        System.out.println(possiveisMovimentos);
                        Posicao posicaoMovida = tabuleiro.verificarPosicao(sc.next());
                        //Verifica se a coordenada passada é correta
                        if (posicaoMovida != null) {
                            //Chama mover peça que faz a função de analisar e executar o movimento se for possível
                            if (jogadorDaVez.moverPeca(posicaoInserida.getPeca(), posicaoMovida,
                                    tabuleiro, jogadorPretas)) {
                                //Jogada Confirmada
                                System.out.println("Peça Movida");
                                validaJogada = true;
                            } else {
                                //Jogada Inválida
                                System.out.println("Jogada inválida.");
                            }
                        } else {
                            System.out.println("Posicão Inválida");
                        }
                    }
                } else {
                    System.out.println("Essa é a peça do adversário.");
                }
            } else {
                System.out.println("Nenhuma peça para ser movimentada na posição.");
            }
        } else {
            System.out.println("Posição não existente!");
        }
        }while (!validaJogada);
        return true;
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
