import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {
    private List<Posicao> listaPosicoes = new ArrayList<>();

    public Tabuleiro() {
        for (int i = 0; i < 64; i++) {
            listaPosicoes.add(new Posicao());
            atribuiPosicao(i, listaPosicoes.get(i));
            //Peão
            if (i >= 8 && i <= 15) {
                listaPosicoes.get(i).setPeca(new Peao(i, "Preto", listaPosicoes.get(i)));
            }
            if (i >= 48 && i <= 55) {
                listaPosicoes.get(i).setPeca(new Peao(i,"Branco", listaPosicoes.get(i)));
            }
            //
            //Torre
            if (i == 0 || i == 7) {
                listaPosicoes.get(i).setPeca(new Torre(i,"Preto", listaPosicoes.get(i)));
            }
            if (i == 56 || i == 63) {
                listaPosicoes.get(i).setPeca(new Torre(i,"Branco", listaPosicoes.get(i)));
            }
            //
            //Cavalo
            if (i == 1 || i == 6) {
                listaPosicoes.get(i).setPeca(new Cavalo(i,"Preto", listaPosicoes.get(i)));
            }
            if (i == 57 || i == 62) {
                listaPosicoes.get(i).setPeca(new Cavalo(i,"Branco", listaPosicoes.get(i)));
            }
            //
            //Bispo
            if (i == 2 || i == 5) {
                listaPosicoes.get(i).setPeca(new Bispo(i, "Preto", listaPosicoes.get(i)));
            }
            if (i == 58 || i == 61) {
                listaPosicoes.get(i).setPeca(new Bispo(i,"Branco", listaPosicoes.get(i)));
            }
            //
            //Rainha
            if (i == 3) {
                listaPosicoes.get(i).setPeca(new Rainha(i,"Preto", listaPosicoes.get(i)));
            }
            if (i == 59) {
                listaPosicoes.get(i).setPeca(new Rainha(i,"Branco", listaPosicoes.get(i)));
            }
            //
            //Rei
            if (i == 4) {
                listaPosicoes.get(i).setPeca(new Rei(i,"Preto", listaPosicoes.get(i)));
            }
            if (i == 60) {
                listaPosicoes.get(i).setPeca(new Rei(i,"Branco", listaPosicoes.get(i)));
            }
            //
        }
    }

    public void atribuiPosicao(int index, Posicao posicaoManipulada){
        String posicao = "";
        if(index <= 7){
            posicao = "H";
        } else if(index <= 15){
            posicao = "G";
        } else if(index <= 23){
            posicao = "F";
        } else if(index <= 31){
            posicao = "E";
        } else if(index <= 39){
            posicao = "D";
        } else if(index <= 47){
            posicao = "C";
        } else if(index <= 55){
            posicao = "B";
        } else if(index <= 63){
            posicao = "A";
        }
        if(index % 8 == 0){
            //1
            posicao = posicao + "1";
        } else if((index+1) % 8 == 0){
            //8
            posicao = posicao + "8";
        } else if((index+2) % 8 == 0){
            //7
            posicao = posicao + "7";
        } else if((index+3) % 8 == 0){
            //6
            posicao = posicao + "6";
        } else if((index+4) % 8 == 0){
            //5
            posicao = posicao + "5";
        } else if((index+5) % 8 == 0){
            //4
            posicao = posicao + "4";
        } else if((index+6) % 8 == 0){
            //3
            posicao = posicao + "3";
        } else if((index+7) % 8 == 0){
            //2
            posicao = posicao + "2";
        }
        posicaoManipulada.setID(posicao);
    }


    public Posicao verificarPosicao(String coordenada){
        for ( Posicao posicao : listaPosicoes) {
            if(posicao.getID().equals(coordenada)){
                return posicao;
            }
        }
        return null;
    }



    public List<Posicao> getPosicoes() {
        return listaPosicoes;
    }
}
