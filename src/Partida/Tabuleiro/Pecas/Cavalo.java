package Partida.Tabuleiro.Pecas;

import Partida.Tabuleiro.Posicao;
import Partida.Tabuleiro.Tabuleiro;

import java.util.ArrayList;
import java.util.List;

public class Cavalo extends Peca {
    public Cavalo(int id, String cor, Posicao posicao) {
        super(id, cor, posicao);
    }

    @Override
    public List<Posicao> possiveisMovimentos(Tabuleiro tabuleiro) {
        List<Posicao> possiveisMovimentos = new ArrayList<>();
        Posicao posicaoAtual = this.getPosicao();
        int posicaoNoTabuleiro = tabuleiro.getPosicoes().indexOf(posicaoAtual);

        for (Posicao posicao : tabuleiro.getPosicoes()) {
            int indice = tabuleiro.getPosicoes().indexOf(posicao);
            if (indice == posicaoNoTabuleiro - 17 ||
                    indice == posicaoNoTabuleiro - 15 ||
                    indice == posicaoNoTabuleiro - 10 ||
                    indice == posicaoNoTabuleiro - 6 ||
                    indice == posicaoNoTabuleiro + 6 ||
                    indice == posicaoNoTabuleiro + 10 ||
                    indice == posicaoNoTabuleiro + 15 ||
                    indice == posicaoNoTabuleiro + 17) {
                //coluna 8
                if (validaExtremidade(posicaoNoTabuleiro + 1)) {
                    if (!(indice == posicaoNoTabuleiro - 15 || indice == posicaoNoTabuleiro - 6 ||
                            indice == posicaoNoTabuleiro + 10 || indice == posicaoNoTabuleiro + 17)) {
                        verificaPeca(posicao, possiveisMovimentos);
                    }
                }
                //coluna 1
                else if (validaExtremidade(posicaoNoTabuleiro)) {
                    if (!(indice == posicaoNoTabuleiro - 17 || indice == posicaoNoTabuleiro - 10 ||
                            indice == posicaoNoTabuleiro + 6 || indice == posicaoNoTabuleiro + 15)) {
                        verificaPeca(posicao, possiveisMovimentos);
                    }
                }
                //Coluna 2
                else if (validaExtremidade(posicaoNoTabuleiro - 1)) {
                    if (!(indice == posicaoNoTabuleiro - 10 || indice == posicaoNoTabuleiro + 6)) {
                        verificaPeca(posicao, possiveisMovimentos);
                    }
                }
                //Coluna 7
                else if (validaExtremidade(posicaoNoTabuleiro + 2)) {
                    if (!(indice == posicaoNoTabuleiro - 6 || indice == posicaoNoTabuleiro + 10)) {
                        verificaPeca(posicao, possiveisMovimentos);
                    }
                }
                //não é de canto
                else {
                    verificaPeca(posicao, possiveisMovimentos);
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        if (this.getCor().equals("Preto")) {
            return "";
        }
        return "CB";
    }

    @Override
    public String toString2() {
        if (this.getCor().equals("Preto")) {
            return "Cavalo Preto";
        } else {
            return "Cavalo Branco";
        }
    }
}

