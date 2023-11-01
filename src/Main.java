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
        new Partida(new Tabuleiro());
    }

    private static void jogada() {
        boolean validaJogada = false;
        do {
            Jogador jogadorDaVez = partida.getJogadorRodada();
            Jogador jogadorAdversario = partida.getJogadorAdversario();

            String decisao = instrucoesIniciasJogada();

            if (decisao.equals("Empate")) {
                if (proporEmpate(jogadorDaVez, jogadorAdversario)) {
                    System.out.println("A partida terminou em empate!");
                    validaJogada = true;
                }
            }

            else if (decisao.equals("Desistir")) {

                partida.desistir(jogadorAdversario);
                System.out.println(jogadorDaVez.getNome() + " desistiu da partida!");

                validaJogada = true;
            }

            else {
                Posicao posicaoInserida = partida.getTabuleiro().verificarPosicao(decisao);

                validaJogada = validaPosicaoEscolhida(posicaoInserida, jogadorDaVez, jogadorAdversario);
            }
        } while (!validaJogada);
    }

    private static boolean validaPosicaoEscolhida(Posicao posicaoInserida, Jogador jogadorDaVez, Jogador jogadorAdversario) {
        if (posicaoInserida != null) {

            Peca pecaSelecionada = posicaoInserida.getPeca();

            if (pecaSelecionada != null) {

                System.out.println(pecaSelecionada.toString2());

                if (pecaSelecionada.getCor().equals(jogadorDaVez.getCor())) {


                    List<Posicao> possiveisMovimentos = partida.getTabuleiro().filtrarPossiveisMovimentos(pecaSelecionada, jogadorAdversario);


                    if (possiveisMovimentos.size() == 0) {
                        System.out.println("Essa peça não possui nenhum possível movimento.");
                        if (jogadorDaVez.isCheque()) {
                            System.out.println("Você está em cheque!");
                        }
                    }
                    else {
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
        Tabuleiro tabuleiro = partida.getTabuleiro();


        System.out.println("Insira a coordenada para a jogada da peça: ");
        System.out.println(partida.listarPossiveisMovimentos(possiveisMovimentos));
        System.out.println("0 - Cancelar e selecionar outra peça.");
        String escolha = sc.next();
        Posicao posicaoMovida = tabuleiro.verificarPosicao(escolha);


        if (posicaoMovida != null) {

            if (possiveisMovimentos.contains(posicaoMovida)) {

                boolean jogadaExecutada = false;

                if (posicaoMovida.isRoque()) {
                    jogadaExecutada = partida.executaRoque(pecaSelecionada, posicaoMovida);
                }

                else if (jogadorDaVez.moverPeca(pecaSelecionada, posicaoMovida, partida, possiveisMovimentos)) {
                    jogadaExecutada = true;
                }


                if (jogadaExecutada) {

                    verificaPromocao(pecaSelecionada);

                    partida.verificaCheque(jogadorDaVez, jogadorAdversario);

                    if (jogadorAdversario.isCheque()) {
                        System.out.println(jogadorAdversario.getNome() + ", você está em cheque!");
                    }
                    return true;
                }
            } else {
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
        System.out.println(jogadorDaVez.getNome() + " propos um empate.");
        System.out.println(jogadorAdversario.getNome() + " deseja aceitar?");
        System.out.println("""
                1 - Sim
                2 - Não
                """);

        if (sc.nextInt() == 2) {

            System.out.println(jogadorAdversario.getNome() + " insira sua senha para confirmar: ");

            if (jogadorAdversario.validaSenha(sc.next())) {

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
        System.out.println("Insira as coordenadas do tabuleiro: (por exemplo: B4)");
        return sc.next();
    }

}
