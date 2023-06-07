import java.util.ArrayList;

public class Bispo extends Peca {
    @Override
    public ArrayList<Posicao> possiveisMovimentos(Tabuleiro tabuleiro) {
        Posicao posicaoAtual = this.getPosicao();
        int posicaoNoTabuleiro = tabuleiro.getPosicoes().indexOf(posicaoAtual);
        ArrayList<Posicao> possiveisMovimentos = new ArrayList<>();
        //if ternário
        for (int i = (posicaoNoTabuleiro % 8 == 0 ? 64 : posicaoNoTabuleiro+7); i < tabuleiro.getPosicoes().size(); i += 7) {
            verificaPeca(tabuleiro.getPosicoes().get(i), possiveisMovimentos);
            if( i % 8 == 0){
                break;
            }
        }
        for (int i = (posicaoNoTabuleiro % 8 == 0 ? -1 : posicaoNoTabuleiro - 7); i >= 0; i -= 7) {
            possiveisMovimentos.add(tabuleiro.getPosicoes().get(i));
            if( (i+1) % 8 == 0){
                break;
            }
        }
        for (int i = (posicaoNoTabuleiro % 8 == 0 ? 64 : posicaoNoTabuleiro+9); i < tabuleiro.getPosicoes().size(); i += 9) {
            possiveisMovimentos.add(tabuleiro.getPosicoes().get(i));
            if( (i+1) % 8 == 0){
                break;
            }
        }
        for (int i = (posicaoNoTabuleiro % 8 == 0 ? -1 : posicaoNoTabuleiro - 9); i >= 0; i -= 9) {
            possiveisMovimentos.add(tabuleiro.getPosicoes().get(i));
            if( i % 8 == 0){
                break;
            }
        }

//        for (Posicao posicao: tabuleiro.getPosicoes()) {
//            int posicaoFor = tabuleiro.getPosicoes().indexOf(posicao);
//            if((posicaoFor - posicaoNoTabuleiro) % 7 == 0){
//                possiveisMovimentos.add(posicao);
//            }
//            else if((posicaoFor - posicaoNoTabuleiro) % 9 == 0){
//                possiveisMovimentos.add(posicao);
//            }

//            for ( int i = 1 ; i <= 7 ; i ++){
//                if( posicaoFor + 7*i == posicaoNoTabuleiro){
//                }
//                if ( posicaoFor - 7*i == posicaoNoTabuleiro ){
//
//                }
//                if( posicaoFor + 9*i == posicaoNoTabuleiro){
//
//                }
//                if ( posicaoFor - 9*i == posicaoNoTabuleiro ){
//                }
//            }
//        }
        return possiveisMovimentos;
    }
}
