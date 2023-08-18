package Partida.Tabuleiro.Pecas;

import Partida.Tabuleiro.Posicao;
import Partida.Tabuleiro.Tabuleiro;

import java.util.ArrayList;
import java.util.List;

public class Peao extends Peca {
    private boolean primeiroMovimento = true;
    private boolean movimentoDuplo = false;

    public Peao(int id, String cor, Posicao posicao) {
        super(id, cor, posicao);
    }

    @Override
    public List<Posicao> possiveisMovimentos(Tabuleiro tabuleiro) {
        List<Posicao> possiveisMovimentos = new ArrayList<>();
        int posicaoNoTabuleiro = tabuleiro.getPosicoes().indexOf(this.getPosicao());
        List<Posicao> posicoesTabuleiro = tabuleiro.getPosicoes();


        if (this.getCor().equals("Preto")) {
            if (posicaoNoTabuleiro <= 56) {
                if (posicoesTabuleiro.get(posicaoNoTabuleiro + 8).getPeca() == null) {
                    possiveisMovimentos.add(posicoesTabuleiro.get(posicaoNoTabuleiro + 8));
                    if (posicoesTabuleiro.get(posicaoNoTabuleiro + 16).getPeca() == null) {
                        if (this.primeiroMovimento) {
                            possiveisMovimentos.add(posicoesTabuleiro.get(posicaoNoTabuleiro + 16));
                        }
                    }
                }
                if (posicoesTabuleiro.get(posicaoNoTabuleiro + 9).getPeca() != null
                        && !validaExtremidade(posicaoNoTabuleiro + 1)) {
                    if (posicoesTabuleiro.get(posicaoNoTabuleiro + 9).getPeca().getCor().equals("Branco")) {
                        possiveisMovimentos.add(posicoesTabuleiro.get(posicaoNoTabuleiro + 9));
                    }
                }
                if (posicoesTabuleiro.get(posicaoNoTabuleiro + 7).getPeca() != null
                        && !validaExtremidade(posicaoNoTabuleiro)) {
                    if (posicoesTabuleiro.get(posicaoNoTabuleiro + 7).getPeca().getCor().equals("Branco")) {
                        possiveisMovimentos.add(posicoesTabuleiro.get(posicaoNoTabuleiro + 7));
                    }
                }
            }
        } else {
            if (posicaoNoTabuleiro >= 8) {
                if (posicoesTabuleiro.get(posicaoNoTabuleiro - 8).getPeca() == null) {
                    possiveisMovimentos.add(posicoesTabuleiro.get(posicaoNoTabuleiro - 8));
                    if (this.primeiroMovimento) {
                        if (posicoesTabuleiro.get(posicaoNoTabuleiro - 16).getPeca() == null) {
                            possiveisMovimentos.add(posicoesTabuleiro.get(posicaoNoTabuleiro - 16));
                        }
                    }
                }
                if (posicoesTabuleiro.get(posicaoNoTabuleiro - 9).getPeca() != null
                        && !validaExtremidade(posicaoNoTabuleiro)) {
                    if (posicoesTabuleiro.get(posicaoNoTabuleiro - 9).getPeca().getCor().equals("Preto")) {
                        possiveisMovimentos.add(posicoesTabuleiro.get(posicaoNoTabuleiro - 9));
                    }
                }
                if (posicoesTabuleiro.get(posicaoNoTabuleiro - 7).getPeca() != null
                        && !validaExtremidade(posicaoNoTabuleiro + 1)) {
                    if (posicoesTabuleiro.get(posicaoNoTabuleiro - 7).getPeca().getCor().equals("Preto")) {
                        possiveisMovimentos.add(posicoesTabuleiro.get(posicaoNoTabuleiro - 7));
                    }
                }
            }
        }

        //EL PASSANT
        if (this.getCor().equals("Branco")) {
            if (!(validaExtremidade(posicaoNoTabuleiro + 1))) {
                Posicao posicaoElPassantDireita = posicoesTabuleiro.get(posicaoNoTabuleiro + 1);
                Peca pecaLateralDireita = posicaoElPassantDireita.getPeca();
                if (pecaLateralDireita != null) {
                    if (pecaLateralDireita instanceof Peao) {
                        if (pecaLateralDireita.getCor().equals("Preto")) {
                            if (((Peao) pecaLateralDireita).isMovimentoDuplo()) {
                                possiveisMovimentos.add(posicoesTabuleiro.get(posicaoNoTabuleiro - 7));
                            }
                        }
                    }
                }
            }
            //COLUNA 1
            if (!(validaExtremidade(posicaoNoTabuleiro))) {
                Posicao posicaoElPassantEsquerda = posicoesTabuleiro.get(posicaoNoTabuleiro - 1);
                Peca pecaLateralEsquerda = posicaoElPassantEsquerda.getPeca();
                if (pecaLateralEsquerda != null) {
                    if (pecaLateralEsquerda instanceof Peao) {
                        if (pecaLateralEsquerda.getCor().equals("Preto")) {
                            if (((Peao) pecaLateralEsquerda).isMovimentoDuplo()) {
                                possiveisMovimentos.add(posicoesTabuleiro.get(posicaoNoTabuleiro - 9));
                            }
                        }
                    }
                }
            }
        } else {
            if (!(validaExtremidade(posicaoNoTabuleiro))) {
                Posicao posicaoElPassantEsquerda = posicoesTabuleiro.get(posicaoNoTabuleiro - 1);
                Peca pecaLateralEsquerda = posicaoElPassantEsquerda.getPeca();
                if (pecaLateralEsquerda != null) {
                    if (pecaLateralEsquerda instanceof Peao) {
                        if (pecaLateralEsquerda.getCor().equals("Branco")) {
                            if (((Peao) pecaLateralEsquerda).isMovimentoDuplo()) {
                                possiveisMovimentos.add(posicoesTabuleiro.get(posicaoNoTabuleiro + 7));
                            }
                        }
                    }
                }
            }
            if (!(validaExtremidade(posicaoNoTabuleiro + 1))) {
                Posicao posicaoElPassantDireita = posicoesTabuleiro.get(posicaoNoTabuleiro + 1);
                Peca pecaLateralDireita = posicaoElPassantDireita.getPeca();
                if (pecaLateralDireita != null) {
                    if (pecaLateralDireita instanceof Peao) {
                        if (pecaLateralDireita.getCor().equals("Branco")) {
                            System.out.println(((Peao) pecaLateralDireita).isMovimentoDuplo());
                            if (((Peao) pecaLateralDireita).isMovimentoDuplo()) {
                                possiveisMovimentos.add(posicoesTabuleiro.get(posicaoNoTabuleiro + 9));
                            }
                        }
                    }
                }
            }
        }
        return possiveisMovimentos;
    }


    public boolean verificaPromocao(Tabuleiro tabuleiro) {
        int indiceNoTabuleiro = tabuleiro.getPosicoes().indexOf(this.getPosicao());
        if (this.getCor().equals("Branco")) {
            if (indiceNoTabuleiro <= 7) {
                return true;
            }
        } else {
            if (indiceNoTabuleiro >= 56) {
                return true;
            }
        }
        return false;
    }

    public boolean isPrimeiroMovimento() {
        return primeiroMovimento;
    }

    public void setPrimeiroMovimento() {
        this.primeiroMovimento = false;
    }

    public boolean isMovimentoDuplo() {
        return movimentoDuplo;
    }

    public void setMovimentoDuplo(boolean movimentoDuplo) {
        this.movimentoDuplo = movimentoDuplo;
    }


    @Override
    public String toString() {
        if (this.getCor().equals("Preto")) {
            return "PP";
        }
        return "PB";
    }

    @Override
    public String toString2() {
        if (this.getCor().equals("Preto")) {
            return "Peão Preto";
        } else {
            return "Peão Branco";
        }
    }
}
