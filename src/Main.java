import java.util.Scanner;

public class Main {

    static private Scanner sc = new Scanner(System.in);
    static private Jogador jogadorBrancas = new Jogador("Jorge", "Senh@123");
    static private Jogador jogadorPretas = new Jogador("Wilson", "wilson");
    static private Tabuleiro tabuleiroVisual = new Tabuleiro();

    public static void main(String[] args) {
        definicoesPreJogo();
        boolean fimDeJogo = false;
        for (int i = 0; !fimDeJogo; i++) {
            tabuleiro();
            if (i % 2 == 0) {
                System.out.println(jogadorBrancas.getNome() + " é a sua vez!");
                jogada(jogadorBrancas);
            } else {
                System.out.println(jogadorPretas.getNome() + " é a sua vez!");
                jogada(jogadorPretas);
            }
            if(validarVitoria(jogadorPretas)){
                System.out.println("Parabéns " + jogadorBrancas.getNome());
                fimDeJogo = true;
            } else if(validarVitoria(jogadorBrancas)){
                System.out.println("Parabéns " + jogadorPretas.getNome());
                fimDeJogo = true;
            }
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
        System.out.print("     8      ");
        System.out.print("\n--------------------------------------------------------" +
                "----------------------------------------\n");
        for (Posicao posicao : tabuleiroVisual.getPosicoes()) {
            int posicaoNoTabuleiro = tabuleiroVisual.getPosicoes().indexOf(posicao);
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
        jogadorBrancas.setCor("Branco", tabuleiroVisual);
        jogadorPretas.setCor("Preto", tabuleiroVisual);
    }

    private static void jogada(Jogador jogadorDaVez) {
        Jogador jogadorAdversario = defineJogadorAdversario(jogadorDaVez);
        boolean validaJogada = false;
        do {
            System.out.println("""
                    -Escreva 'Empate' para propor empate
                    -Escreva 'Desistir' para desistir """);
            //Solicita jogada para o jogador da vez
            System.out.println("Insira as coordenadas do tabuleiro: (por exemplo: B4)");
            String decisao = sc.next();
            if(decisao.equals("Empate")){
                jogadorDaVez.proporEmpate(jogadorDaVez);
            }
            else if(decisao.equals("Desistir")){
                jogadorDaVez.desistir();
                validaJogada = true;
            } else {
                //faz o request da posição conforme coordenada passada
                Posicao posicaoInserida = tabuleiroVisual.verificarPosicao(decisao);

                //verifica se a coordenada repassada é válida
                if (posicaoInserida != null) {
                    //Verifica se existe uma peça naquela coordenada.

                    Peca pecaSelecionada = posicaoInserida.getPeca();
                    if (pecaSelecionada != null) {
                        //Verifica se a peça selecionada é semelhante ao do jogador da vez e mostra para o usuário
                        System.out.println(pecaSelecionada.toString2());

                        if (pecaSelecionada.getCor().equals(jogadorDaVez.getCor())) {

                            String possiveisMovimentos = listaPossiveisMovimentos(pecaSelecionada);

                            //verifica se existe algum possível movimento, verificando se a String foi alteradas
                            if (possiveisMovimentos == "") {
                                System.out.println("Essa peça não possui nenhum possível movimento.");
                            } else {
                                //chama a função que pede e executa o movimento, caso de erro
                                //essa função retorna true ou false, no true ela valida a jogada.
                                validaJogada = posicaoFinalEscolhaEExecucao(possiveisMovimentos, pecaSelecionada,
                                        jogadorDaVez, jogadorAdversario);
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
            }
        } while (!validaJogada);
    }

    private static Jogador defineJogadorAdversario(Jogador jogadorDaVez) {
        //Recebe o jogador da vez e devolve o jogador ao contrário
        if (jogadorDaVez == jogadorBrancas) {
            return jogadorPretas;
        } else {
            return jogadorBrancas;
        }
    }

    private static String listaPossiveisMovimentos(Peca pecaDaPosicao) {
        String possiveisMovimentos = "";
        //varre a lista e mostra as possíveis jogadas
        for (Posicao posicao : pecaDaPosicao.possiveisMovimentos(tabuleiroVisual)) {
            if (posicao.getPeca() != null) {
                possiveisMovimentos += posicao.getPeca().toString2() + " - " + posicao.getID() + "\n";
            } else {
                possiveisMovimentos += "Espaço Vazio - " + posicao.getID() + "\n";
            }
        }
        return possiveisMovimentos;
    }

    private static boolean posicaoFinalEscolhaEExecucao(String possiveisMovimentos, Peca pecaSelecionada,
                                                        Jogador jogadorDaVez, Jogador jogadorAdversario) {
        //Solicita a posição final da peça selecionada
        System.out.println("Insira a coordenada para a jogada da peça: ");
        System.out.println(possiveisMovimentos);
        System.out.println("0 - Cancelar e selecionar outra peça.");
        String escolha = sc.next();
        Posicao posicaoMovida = tabuleiroVisual.verificarPosicao(escolha);
        //Verifica se a coordenada passada é correta
        if (posicaoMovida != null) {
            //Chama mover peça que faz a função de analisar e executar o movimento se for possível
            if (jogadorDaVez.moverPeca(pecaSelecionada, posicaoMovida,
                    tabuleiroVisual, jogadorAdversario)) {
                //Jogada Confirmada
                System.out.println("Peça Movida");
                return true;
            } else {
                //Jogada Inválida
                System.out.println("Jogada inválida.");
            }
        } else {
            if (escolha.equals("0")) {
                System.out.println(pecaSelecionada.toString2() + " desselecionado(a)");
            } else {
                System.out.println("Posicão Inválida");
            }
        }
        return false;
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
