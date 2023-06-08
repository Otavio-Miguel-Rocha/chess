import java.util.ArrayList;

public class Bispo extends Peca {
    @Override
    public ArrayList<Posicao> possiveisMovimentos(Tabuleiro tabuleiro) {
        Posicao posicaoAtual = this.getPosicao();
        int posicaoNoTabuleiro = tabuleiro.getPosicoes().indexOf(posicaoAtual);
        ArrayList<Posicao> possiveisMovimentos = new ArrayList<>();
        //if ternário
        for (int i = (posicaoNoTabuleiro % 8 == 0 ? 64 : posicaoNoTabuleiro+7); i < tabuleiro.getPosicoes().size(); i += 7) {
            if( i % 8 == 0 || verificaPeca(tabuleiro.getPosicoes().get(i), possiveisMovimentos)){
                break;
            }
        }
        for (int i = (posicaoNoTabuleiro % 8 == 0 ? -1 : posicaoNoTabuleiro - 7); i >= 0; i -= 7) {
            if( (i+1) % 8 == 0 || verificaPeca(tabuleiro.getPosicoes().get(i), possiveisMovimentos)){
                break;
            }
        }
        for (int i = (posicaoNoTabuleiro % 8 == 0 ? 64 : posicaoNoTabuleiro+9); i < tabuleiro.getPosicoes().size(); i += 9) {
            if( (i+1) % 8 == 0 || verificaPeca(tabuleiro.getPosicoes().get(i), possiveisMovimentos)){
                break;
            }
        }
        for (int i = (posicaoNoTabuleiro % 8 == 0 ? -1 : posicaoNoTabuleiro - 9); i >= 0; i -= 9) {
            if( i % 8 == 0 || verificaPeca(tabuleiro.getPosicoes().get(i), possiveisMovimentos)){
                break;
            }
        }
        return possiveisMovimentos;
    }
}
