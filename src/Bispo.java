import java.util.ArrayList;

public class Bispo extends Peca {

    public Bispo(String cor, Posicao posicao) {
        super(cor, posicao);
    }

    @Override
    public ArrayList<Posicao> possiveisMovimentos(Tabuleiro tabuleiro) {
        Posicao posicaoAtual = this.getPosicao();
        int posicaoNoTabuleiro = tabuleiro.getPosicoes().indexOf(posicaoAtual);
        ArrayList<Posicao> possiveisMovimentos = new ArrayList<>();
        //if ternário
        for (int i = (validaExtremidade(posicaoNoTabuleiro) ? 64 : posicaoNoTabuleiro + 7); i < tabuleiro.getPosicoes().size(); i += 7) {
            if (verificaPeca(tabuleiro.getPosicoes().get(i), possiveisMovimentos) || validaExtremidade(i)) {
                break;
            }
        }
        for (int i = (validaExtremidade(posicaoNoTabuleiro + 1) ? -1 : posicaoNoTabuleiro - 7); i >= 0; i -= 7) {
            if (verificaPeca(tabuleiro.getPosicoes().get(i), possiveisMovimentos) || validaExtremidade(i + 1)) {
                break;
            }
        }
        for (int i = (validaExtremidade(posicaoNoTabuleiro + 1) ? 64 : posicaoNoTabuleiro + 9); i < tabuleiro.getPosicoes().size(); i += 9) {
            if (verificaPeca(tabuleiro.getPosicoes().get(i), possiveisMovimentos) || validaExtremidade(i + 1)) {
                break;
            }
        }
        for (int i = (validaExtremidade(posicaoNoTabuleiro) ? -1 : posicaoNoTabuleiro - 9); i >= 0; i -= 9) {
            if (verificaPeca(tabuleiro.getPosicoes().get(i), possiveisMovimentos) || validaExtremidade(i)) {
                break;
            }
        }
        return possiveisMovimentos;
    }

    @Override
    public String toString() {
        if (this.getCor().equals("Preto")) {
            return "BP";
        }
        return "BB";
    }

    @Override
    public String toString2() {
        return "Bispo " + "\n" +
                super.toString2() + "\n";
    }
}
