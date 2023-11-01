package Partida.Tabuleiro.Pecas;

import Partida.Tabuleiro.Posicao;
import Partida.Tabuleiro.Tabuleiro;

import java.util.ArrayList;
import java.util.List;

public class Rei extends Peca {
    private boolean primeiroMovimento = true;

    public Rei(int id, String cor, Posicao posicao) {
        super(id, cor, posicao);
    }

    @Override
    public List<Posicao> possiveisMovimentos(Tabuleiro tabuleiro) {
        Posicao posicaoAtual = this.getPosicao();
        int posicaoNoTabuleiro = tabuleiro.getPosicoes().indexOf(posicaoAtual);
        List<Posicao> possiveisMovimentos = new ArrayList<>();

        for (Posicao posicao : tabuleiro.getPosicoes()) {
            int indice = tabuleiro.getPosicoes().indexOf(posicao);
            if (indice == posicaoNoTabuleiro - 9 ||
                    indice == posicaoNoTabuleiro - 8 ||
                    indice == posicaoNoTabuleiro - 7 ||
                    indice == posicaoNoTabuleiro - 1 ||
                    indice == posicaoNoTabuleiro + 1 ||
                    indice == posicaoNoTabuleiro + 7 ||
                    indice == posicaoNoTabuleiro + 8 ||
                    indice == posicaoNoTabuleiro + 9) {
                //coluna H
                if (validaExtremidade(posicaoNoTabuleiro + 1)) {
                    if (!(
                            indice == posicaoNoTabuleiro - 7 ||
                                    indice == posicaoNoTabuleiro + 1 ||
                                    indice == posicaoNoTabuleiro + 9
                    )) {
                        verificaPeca(posicao, possiveisMovimentos);
                    }
                }
                //coluna a
                else if (validaExtremidade(posicaoNoTabuleiro)) {
                    if (!(
                            indice == posicaoNoTabuleiro - 9 ||
                                    indice == posicaoNoTabuleiro - 1 ||
                                    indice == posicaoNoTabuleiro + 7
                    )) {
                        verificaPeca(posicao, possiveisMovimentos);
                    }
                } else {
                    verificaPeca(posicao, possiveisMovimentos);
                }
            }
        }
        if (primeiroMovimento) {
            if (this.getCor().equals("Branco")) {
                //direito
                Peca possivelTorreBrancaDireita = tabuleiro.getPosicoes().get(63).getPeca();
                if (possivelTorreBrancaDireita != null) {
                    if (possivelTorreBrancaDireita instanceof Torre) {
                        if (((Torre) possivelTorreBrancaDireita).isPrimeiroMovimento()) {
                            boolean testePosicoesVazias = true;
                            for (int i = posicaoNoTabuleiro + 1; i < 63; i++) {
                                if (tabuleiro.getPosicoes().get(i).getPeca() != null) {
                                    testePosicoesVazias = false;
                                }
                            }
                            if (testePosicoesVazias) {
                                Posicao posicaoFinalReiRoque = tabuleiro.getPosicoes().get(62);
                                possiveisMovimentos.add(posicaoFinalReiRoque);
                                posicaoFinalReiRoque.setRoque(true);
                            }
                        }
                    }
                }

                Peca possivelTorreBrancaEsquerda = tabuleiro.getPosicoes().get(56).getPeca();
                if (possivelTorreBrancaEsquerda != null) {
                    if (possivelTorreBrancaEsquerda instanceof Torre) {
                        if (((Torre) possivelTorreBrancaEsquerda).isPrimeiroMovimento()) {
                            boolean testePosicoesVazias = true;
                            for (int i = posicaoNoTabuleiro - 1; i > 56; i--) {
                                if (tabuleiro.getPosicoes().get(i).getPeca() != null) {
                                    testePosicoesVazias = false;
                                }
                            }
                            if (testePosicoesVazias) {
                                Posicao posicaoFinalReiRoque = tabuleiro.getPosicoes().get(58);
                                possiveisMovimentos.add(posicaoFinalReiRoque);
                                posicaoFinalReiRoque.setRoque(true);
                            }
                        }
                    }
                }
            } else {
                Peca possivelTorrePretoEsquerda = tabuleiro.getPosicoes().get(0).getPeca();
                if (possivelTorrePretoEsquerda != null) {
                    if (possivelTorrePretoEsquerda instanceof Torre) {
                        if (((Torre) possivelTorrePretoEsquerda).isPrimeiroMovimento()) {
                            boolean testePosicoesVazias = true;
                            for (int i = posicaoNoTabuleiro - 1; i > 0; i--) {
                                if (tabuleiro.getPosicoes().get(i).getPeca() != null) {
                                    testePosicoesVazias = false;
                                }
                            }
                            if (testePosicoesVazias) {
                                Posicao posicaoFinalReiRoque = tabuleiro.getPosicoes().get(2);
                                possiveisMovimentos.add(posicaoFinalReiRoque);
                                posicaoFinalReiRoque.setRoque(true);
                            }
                        }
                    }
                }

                Peca possivelTorrePretoDireito = tabuleiro.getPosicoes().get(7).getPeca();
                if (possivelTorrePretoDireito != null) {
                    if (possivelTorrePretoDireito instanceof Torre) {
                        if (((Torre) possivelTorrePretoDireito).isPrimeiroMovimento()) {
                            boolean testePosicoesVazias = true;
                            for (int i = posicaoNoTabuleiro + 1; i < 7; i++) {
                                if (tabuleiro.getPosicoes().get(i).getPeca() != null) {
                                    testePosicoesVazias = false;
                                }
                            }
                            if (testePosicoesVazias) {
                                Posicao posicaoFinalReiRoque = tabuleiro.getPosicoes().get(6);
                                possiveisMovimentos.add(posicaoFinalReiRoque);
                                posicaoFinalReiRoque.setRoque(true);
                            }
                        }
                    }
                }
            }
        }
        return possiveisMovimentos;
    }

    public void setPrimeiroMovimento() {
        this.primeiroMovimento = false;
    }

    public boolean isPrimeiroMovimento() {
        return primeiroMovimento;
    }

    @Override
    public String toString() {
        if (this.getCor().equals("Preto")) {
            return "REP";
        }
        return "REB";
    }

    @Override
    public String toString2() {
        if (this.getCor().equals("Preto")) {
            return "Rei Preto";
        } else {
            return "Rei Branco";
        }
    }


}

