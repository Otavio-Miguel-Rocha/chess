import java.util.ArrayList;

public abstract class Peca {
    private String cor;
    private Posicao posicao;

    public boolean verificaPeca(Posicao posicao, ArrayList<Posicao> possiveisMovimentos) {
        if (posicao.getPeca() == null) {
            possiveisMovimentos.add(posicao);
            return false;
        }
        if (!posicao.getPeca().getCor().equals(this.getCor())) {
            possiveisMovimentos.add(posicao);
        }
        return true;

    }

    public void mover(Tabuleiro tabuleiro, Posicao posicao) {
        ArrayList<Posicao> possiveisPosicoes = possiveisMovimentos(tabuleiro);
        for (Posicao posicaoPossivel : possiveisPosicoes) {
            if (posicaoPossivel == posicao) {
                // Atribui a peça para a nova posição no tabuleiro
                posicao.setPeca(this);
                // Remove a peça da posição anterior no tabuleiro
                this.posicao.setPeca(null);
                // Troca a posição atual da peça na lógica
                this.posicao = posicao;
                break;
            }
        }
        this.posicao = posicao;
    }

    public abstract ArrayList<Posicao> possiveisMovimentos(Tabuleiro tabuleiro);

//    public abstract char icone();


    public Posicao getPosicao() {
        return posicao;
    }

    public String getCor() {
        return cor;
    }
}
