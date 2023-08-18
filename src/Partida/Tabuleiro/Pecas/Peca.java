package Partida.Tabuleiro.Pecas;

import Partida.Tabuleiro.Posicao;
import Partida.Tabuleiro.Tabuleiro;

import java.util.ArrayList;
import java.util.List;

public abstract class Peca {
    private int id;
    private String cor;
    private Posicao posicao;

    public Peca(int id, String cor, Posicao posicao) {
        this.id = id;
        this.cor = cor;
        this.posicao = posicao;
    }

    public boolean verificaPeca(Posicao posicao, List<Posicao> possiveisMovimentos) {
        //VERIFICA SE ESTÁ VAZIA A POSIÇÃO, SE TIVER ADICIONA
        if (posicao.getPeca() == null) {
            possiveisMovimentos.add(posicao);
            return false;
        }
        //VERIFICA SE EXISTE UMA PEÇA DE OUTRA COR NA POSIÇÃO
        if (!posicao.getPeca().getCor().equals(this.getCor())) {
            possiveisMovimentos.add(posicao);
        }
        return true;
    }

    public boolean mover(Posicao posicao, List<Posicao> possiveisMovimentos) {
        for (Posicao posicaoPossivel : possiveisMovimentos) {
            if (posicaoPossivel == posicao) {
                //Atribui a peça para a nova posição no tabuleiro
                posicao.setPeca(this);
                // Remove a peça da posição anterior no tabuleiro
                this.posicao.setPeca(null);
                // Troca a posição atual da peça na lógica
                this.posicao = posicao;
                return true;
            }
        }
        return false;
    }

    public boolean validaExtremidade(int posicaoNoTabuleiro) {
        return posicaoNoTabuleiro % 8 == 0;
    }

    public abstract List<Posicao> possiveisMovimentos(Tabuleiro tabuleiro);

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }

    public int getId() {
        return id;
    }

    public Posicao getPosicao() {
        return this.posicao;
    }

    public String getCor() {
        return this.cor;
    }

    public String toString2() {
        return "Peca{" + "\n" +
                "Cor = " + cor + "\n" +
                "Posicao = " + posicao.getID() + "\n" +
                '}';
    }

}

