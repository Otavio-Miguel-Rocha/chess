package Partida.Tabuleiro.Pecas;

import Partida.Tabuleiro.Posicao;
import Partida.Tabuleiro.Tabuleiro;

import java.util.ArrayList;
import java.util.List;

public class Torre extends Peca {
    private boolean primeiroMovimento = true;

    public Torre(int id, String cor, Posicao posicao) {
        super(id, cor, posicao);
    }

    @Override
    public List<Posicao> possiveisMovimentos(Tabuleiro tabuleiro) {
        Posicao posicaoAtual = this.getPosicao();
        int posicaoNoTabuleiro = tabuleiro.getPosicoes().indexOf(posicaoAtual);
        List<Posicao> possiveisMovimentos = new ArrayList<>();


        for (int i = posicaoNoTabuleiro + 8; i < tabuleiro.getPosicoes().size(); i += 8) {
            if (verificaPeca(tabuleiro.getPosicoes().get(i), possiveisMovimentos)) {
                break;
            }
        }
        for (int i = posicaoNoTabuleiro - 8; i >= 0; i -= 8) {
            if (verificaPeca(tabuleiro.getPosicoes().get(10), possiveisMovimentos)) {
                break;
            }
        }
        for (int i = (validaExtremidade(posicaoNoTabuleiro + 1) ? 64 : posicaoNoTabuleiro + 1);
             i < tabuleiro.getPosicoes().size(); i++) {
            if (verificaPeca(tabuleiro.getPosicoes().get(i), possiveisMovimentos) || validaExtremidade(i + 1)) {
                break;
            }
        }
        for (int i = (validaExtremidade(posicaoNoTabuleiro) ? -1 : posicaoNoTabuleiro - 1); i >= 0; i--) {
            if (validaExtremidade(i + 1) || verificaPeca(tabuleiro.getPosicoes().get(i), possiveisMovimentos)) {
                break;
            }
        }
        return possiveisMovimentos;
    }

    public boolean isPrimeiroMovimento() {
        return primeiroMovimento;
    }

    public void setPrimeiroMovimento() {
        this.primeiroMovimento = false;
    }

    @Override
    public String toString() {
        if (this.getCor().equals("Preto")) {
            return "TP";
        }
        return "TB";
    }

    @Override
    public String toString2() {
        if (this.getCor().equals("Preto")) {
            return "Torre Preta";
        } else {
            return "Torre Branca";
        }
    }
}
