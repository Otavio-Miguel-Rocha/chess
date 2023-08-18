import Partida.Jogador;
import Partida.Partida;
import Partida.Tabuleiro.Pecas.*;
import Partida.Tabuleiro.Posicao;
import Partida.Tabuleiro.Tabuleiro;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static Partida partida;

    public static void main(String[] args) {
        dadosIniciais();
        boolean fimDeJogo;
        do {
            partida.verificaAfogamento();
            fimDeJogo = partida.validarFimDeJogo();
            if (!fimDeJogo) {
                jogada();
                partida.acrescentarContadorRodada();
            }
        } while (!fimDeJogo);
        System.out.println(partida.imprimirTabuleiro());
        if (partida.getVencedor() != null) {
            System.out.println(partida.getVencedor().getNome() + " venceu a partida!");
        } else if (partida.isAfogamento()) {
            System.out.println("A partida encerrou em afogamento!");
        }
    }

    private static void dadosIniciais() {
        new Jogador("Jorge", "Senh@123");
        new Jogador("Wilson", "wilson");
        partida = new Partida(new Tabuleiro());
    }

    private static void jogada() {
        boolean validaJogada = false;
        do {

            //ARMAZENA OS JOGADORES DA PARTIDA PARA FACILITAR A UTILIZAÇÃO
            Jogador jogadorDaVez = partida.getJogadorRodada();
            Jogador jogadorAdversario = partida.getJogadorAdversario();

            //INDICA AS INSTRUÇÕES E RECEBE A DECISÃO
            String decisao = instrucoesIniciasJogada();

            //CASO SEJA EMPATE
            if (decisao.equals("Empate")) {
                //CHAMA A FUNÇÃO PROPOR EMPATE
                if (proporEmpate(jogadorDaVez, jogadorAdversario)) {
                    System.out.println("A partida terminou em empate!");
                    validaJogada = true;
                }
            }

            //CASO SEJA DESISTÊNCIA
            else if (decisao.equals("Desistir")) {

                //NA CLASSE PARTIDA É DEFINIDO A DESISTÊNCIA E O CHEQUE MATE NO JOGADOR ATUAL
                partida.desistir(jogadorAdversario);
                System.out.println(jogadorDaVez.getNome() + " desistiu da partida!");

                //DEFINE TRUE PARA SAIR DO DO WHILE
                validaJogada = true;
            }

            //CASO SEJA A JOGADA
            else {
                //CHAMA A FUNÇÃO VERIFICAR POSIÇÃO NO TABULEIRO DA PARTIDA
                //QUE VALIDA A COORDENADA INSERIDA
                Posicao posicaoInserida = partida.getTabuleiro().verificarPosicao(decisao);

                // CHAMA O MÉTODO DE VALIDAÇÃO DA POSIÇÃO
                validaJogada = validaPosicaoEscolhida(posicaoInserida, jogadorDaVez, jogadorAdversario);
            }
        } while (!validaJogada);
    }

    private static boolean validaPosicaoEscolhida(Posicao posicaoInserida, Jogador jogadorDaVez, Jogador jogadorAdversario) {
        //verifica se a coordenada repassada é válida
        if (posicaoInserida != null) {

            //Verifica se existe uma peça naquela coordenada.
            Peca pecaSelecionada = posicaoInserida.getPeca();

            if (pecaSelecionada != null) {

                //Verifica se a peça selecionada é semelhante ao do jogador da vez e mostra para o usuário
                System.out.println(pecaSelecionada.toString2());

                if (pecaSelecionada.getCor().equals(jogadorDaVez.getCor())) {

                    //CHAMA O MÉTODO DO TABULEIRO DA PARTIDA QUE IRA FILTRAR E VALIDAR OS POSSÍVEIS MOVIMENTOS
                    //ELIMINANDO A POSSIBILIDADE DO USUÁRIO ENTRAR EM CHEQUE POR CONTA PRÓPRIA

                    List<Posicao> possiveisMovimentos = partida.getTabuleiro().filtrarPossiveisMovimentos(pecaSelecionada, jogadorAdversario);


                    //verifica se existe algum possível movimento, verificando se a lista não voltou vazia
                    if (possiveisMovimentos.size() == 0) {
                        System.out.println("Essa peça não possui nenhum possível movimento.");
                        if (jogadorDaVez.isCheque()) {
                            System.out.println("Você está em cheque!");
                        }
                    }
                    //caso não tenha voltado vazia
                    else {
                        //chama a função que pede e executa o movimento, caso de erro
                        //essa função retorna true ou false, no true ela valida a jogada.
                        return posicaoFinalEscolhaEExecucao(possiveisMovimentos, pecaSelecionada,
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
        return false;
    }

    private static boolean posicaoFinalEscolhaEExecucao(List<Posicao> possiveisMovimentos, Peca pecaSelecionada,
                                                        Jogador jogadorDaVez, Jogador jogadorAdversario) {
        //ARMAZENA O TABULEIRO DA PARTIDA
        Tabuleiro tabuleiro = partida.getTabuleiro();

        //Solicita a posição final da peça selecionada
        System.out.println("Insira a coordenada para a jogada da peça: ");
        System.out.println(partida.listarPossiveisMovimentos(possiveisMovimentos));
        System.out.println("0 - Cancelar e selecionar outra peça.");
        String escolha = sc.next();
        Posicao posicaoMovida = tabuleiro.verificarPosicao(escolha);


        //Verifica se a coordenada passada é correta
        if (posicaoMovida != null) {

            //VERIFICA SE A POSIÇÃO INSERIDA FAZ PARTE DA LISTA DE POSSÍVEIS MOVIMENTOS
            if (possiveisMovimentos.contains(posicaoMovida)) {

                //CRIA UMA VARIÁVEL PARA VALIDAR SE A JOGADA FOI EXECUTADA OU NÃO
                boolean jogadaExecutada = false;

                //ROQUE
                if (posicaoMovida.isRoque()) {
                    jogadaExecutada = partida.executaRoque(pecaSelecionada, posicaoMovida);
                }

                //CHAMA MOVER PEÇA DA CLASSE JOGADOR, CASO A MOVIMENTAÇÃO FOR EXECUTADA, RETORNA TRUE
                else if (jogadorDaVez.moverPeca(pecaSelecionada, posicaoMovida, partida, possiveisMovimentos)) {
                    jogadaExecutada = true;
                }

                //VALIDA A JOGADA EXECUTADA
                if (jogadaExecutada) {

                    //VERIFICA PROMOÇÃO DE UM POSSÍVEL PEÃO
                    verificaPromocao(pecaSelecionada);

                    //VERIFICA O CHEQUE E CHEQUE MATE NA PARTIDA
                    partida.verificaCheque(jogadorDaVez, jogadorAdversario);

                    //AVISA QUANDO ESTEJA
                    if (jogadorAdversario.isCheque()) {
                        System.out.println(jogadorAdversario.getNome() + ", você está em cheque!");
                    }
                    return true;
                }
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


    private static boolean proporEmpate(Jogador jogadorDaVez, Jogador jogadorAdversario) {
        //INSTRUI O JOGADOR PARA O EMPATE
        System.out.println(jogadorDaVez.getNome() + " propos um empate.");
        System.out.println(jogadorAdversario.getNome() + " deseja aceitar?");
        System.out.println("""
                1 - Sim
                2 - Não
                """);

        //VERIFICA O INDICE INSERIDO
        if (sc.nextInt() == 1) {

            //SOLICITA A SENHA DO JOGADOR ADVERSÁRIO
            System.out.println(jogadorAdversario.getNome() + " insira sua senha para confirmar: ");

            //VALIDA A SENHA NA CLASSE JOGADOR
            if (jogadorAdversario.validaSenha(sc.next())) {

                //SETA COMO TRUE O EMPATE NA PARTIDA, RETORNANDO TRUE
                partida.setEmpate(true);
                return true;
            }
        }
        //CANCELA O EMPATE, RETORNANDO FALSE E CONTINUANDO A JOGADA
        System.out.println("Empate cancelado!");
        return false;
    }


    private static void verificaPromocao(Peca pecaSelecionada) {
        Tabuleiro tabuleiro = partida.getTabuleiro();
        if (pecaSelecionada instanceof Peao) {
            if (((Peao) pecaSelecionada).verificaPromocao(tabuleiro)) {
                boolean escolhaPeca = false;
                do {
                    System.out.println("""
                            Para qual peça deseja promover seu peão?
                            1 - Rainha
                            2 - Torre
                            3 - Cavalo
                            4 - Bispo
                            """);
                    switch (sc.nextInt()) {
                        case 1:
                            tabuleiro.executarPromocao(new Rainha(pecaSelecionada.getId(),
                                    pecaSelecionada.getCor(), pecaSelecionada.getPosicao()));
                            escolhaPeca = true;
                            break;
                        case 2:
                            tabuleiro.executarPromocao(new Torre(pecaSelecionada.getId(),
                                    pecaSelecionada.getCor(), pecaSelecionada.getPosicao()));
                            escolhaPeca = true;
                            break;
                        case 3:
                            tabuleiro.executarPromocao(new Cavalo(pecaSelecionada.getId(),
                                    pecaSelecionada.getCor(), pecaSelecionada.getPosicao()));
                            escolhaPeca = true;
                            break;
                        case 4:
                            tabuleiro.executarPromocao(new Bispo(pecaSelecionada.getId(),
                                    pecaSelecionada.getCor(), pecaSelecionada.getPosicao()));
                            escolhaPeca = true;
                            break;
                        default:
                            System.out.println("Insira um índice válido!");
                            break;
                    }
                } while (!escolhaPeca);
            }
        }
    }


    private static String instrucoesIniciasJogada() {
        System.out.println(partida.imprimirTabuleiro());
        System.out.println(partida.getJogadorRodada().getNome() + " é a sua vez!");

        if (partida.getJogadorRodada().isCheque()) {
            System.out.println("Você está em cheque!");
        }
        System.out.println("""
                -Escreva 'Empate' para propor empate
                -Escreva 'Desistir' para desistir """);
        //Solicita jogada para o jogador da vez
        System.out.println("Insira as coordenadas do tabuleiro: (por exemplo: B4)");
        return sc.next();
    }

}
