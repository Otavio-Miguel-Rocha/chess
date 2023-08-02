import java.util.ArrayList;
import java.util.List;

public class Peao extends Peca {
    private boolean primeiroMovimento = true;

    public Peao(String cor, Posicao posicao) {
        super(cor, posicao);
    }

    @Override
    public List<Posicao> possiveisMovimentos(Tabuleiro tabuleiro) {
        List<Posicao> possiveisMovimentos = new ArrayList<>();
        Posicao posicaoAtual = this.getPosicao();
        int posicaoNoTabuleiro = tabuleiro.getPosicoes().indexOf(posicaoAtual);

        List<Posicao> posicoesTabuleiro = tabuleiro.getPosicoes();
        if (this.getCor().equals("Preto")) {
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
        } else {
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
        return possiveisMovimentos;
    }


    public void setPrimeiroMovimento(){
        this.primeiroMovimento = false;
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
        if (this.getCor().equals("Preto")){
            return "Peão Preto";
        } else {
            return "Peão Branco";
        }
    }
}
