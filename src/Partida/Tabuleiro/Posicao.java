package Partida.Tabuleiro;

import Partida.Tabuleiro.Pecas.Peca;

public class Posicao {
    private Peca peca;
    private String identificadorPosicao;
    private boolean roque = false;


    public void setPeca(Peca peca) {
        this.peca = peca;

    }

    public Peca getPeca() {
        return this.peca;
    }

    public void setID(String identificador) {
        this.identificadorPosicao = identificador;
    }

    public String getID() {
        return this.identificadorPosicao;
    }

    public boolean isRoque() {
        return roque;
    }

    public void setRoque(boolean roque) {
        this.roque = roque;
    }

    @Override
    public String toString() {
        return "Posicao{" +
                "peca = " + peca +
                '}';
    }
}

