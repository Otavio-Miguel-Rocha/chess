import java.util.ArrayList;
import java.util.List;

public class Rei extends Peca {
    private boolean primeiroMovimento;

    public Rei(String cor, Posicao posicao) {
        super(cor, posicao);
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
                if (validaExtremidade(posicaoNoTabuleiro + 1) && !(
                        indice == posicaoNoTabuleiro - 7 ||
                                indice == posicaoNoTabuleiro + 1 ||
                                indice == posicaoNoTabuleiro + 9
                )) {
                    verificaPeca(posicao, possiveisMovimentos);
                }
                //coluna a
                else if (validaExtremidade(posicaoNoTabuleiro) && !(
                        indice == posicaoNoTabuleiro - 9 ||
                                indice == posicaoNoTabuleiro - 1 ||
                                indice == posicaoNoTabuleiro + 7
                )) {
                    verificaPeca(posicao, possiveisMovimentos);
                } else {
                    verificaPeca(posicao, possiveisMovimentos);
                }
            }
        }
        return possiveisMovimentos;
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
        if (this.getCor().equals("Preto")){
            return "Rei Preto";
        } else {
            return "Rei Branco";
        }
    }
}
