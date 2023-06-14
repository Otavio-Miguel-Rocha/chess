import java.util.ArrayList;

public class Tabuleiro {
    private ArrayList<Posicao> listaPosicoes = new ArrayList<>();

    public Tabuleiro() {
        for (int i = 0; i < 64; i++) {
            listaPosicoes.add(new Posicao());
            //Peão
            if (i >= 8 && i <= 15) {
                listaPosicoes.get(i).setPeca(new Peao("Preto"));
            }
            if (i >= 48 && i <= 55) {
                listaPosicoes.get(i).setPeca(new Peao("Branco"));
            }
            //
            //Torre
            if (i == 0 || i == 7) {
                listaPosicoes.get(i).setPeca(new Torre("Preto"));
            }
            if (i == 56 || i == 63) {
                listaPosicoes.get(i).setPeca(new Torre("Branco"));
            }
            //
            //Cavalo
            if (i == 1 || i == 6) {
                listaPosicoes.get(i).setPeca(new Cavalo("Preto"));
            }
            if (i == 57 || i == 62) {
                listaPosicoes.get(i).setPeca(new Cavalo("Branco"));
            }
            //
            //Bispo
            if (i == 2 || i == 5) {
                listaPosicoes.get(i).setPeca(new Bispo("Preto"));
            }
            if (i == 58 || i == 61) {
                listaPosicoes.get(i).setPeca(new Bispo("Branco"));
            }
            //
            //Rainha
            if (i == 3) {
                listaPosicoes.get(i).setPeca(new Rainha("Preto"));
            }
            if (i == 59) {
                listaPosicoes.get(i).setPeca(new Rainha("Branco"));
            }
            //
            //Rei
            if (i == 4) {
                listaPosicoes.get(i).setPeca(new Rei("Preto"));
            }
            if (i == 60) {
                listaPosicoes.get(i).setPeca(new Rei("Branco"));
            }
            //
        }
    }

    public void removerPeca(Posicao posicao) {
    }

    public ArrayList<Posicao> getPosicoes() {
        return listaPosicoes;
    }
}
