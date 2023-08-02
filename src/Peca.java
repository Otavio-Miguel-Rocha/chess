import java.util.ArrayList;
import java.util.List;

public abstract class Peca {
    private String cor;
    private Posicao posicao;

    public Peca(String cor, Posicao posicao) {
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

    public boolean mover(Tabuleiro tabuleiro, Posicao posicao) {
        List<Posicao> possiveisPosicoes = possiveisMovimentos(tabuleiro);

        for (Posicao posicaoPossivel : possiveisPosicoes) {
            if (posicaoPossivel == posicao) {
                teste(tabuleiro.getPosicoes(), posicao, this);
                // Atribui a peça para a nova posição no tabuleiro
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

    private void teste(List<Posicao> posicoesAtuais, Posicao posicao, Peca peca){
        List<Posicao> copiaTabuleiro = new ArrayList<>();
        for (int i = 0; i < 64; i++) {
            if(posicoesAtuais.get(i) != posicao){
                if(posicoesAtuais.get(i).getPeca() == peca){

                }
                copiaTabuleiro.add(posicoesAtuais.get(i));
            } else{
                //caso seja igual a posição

            }
        }
    }

    public boolean validaExtremidade(int posicaoNoTabuleiro) {
        return posicaoNoTabuleiro % 8 == 0;
    }

    public abstract List<Posicao> possiveisMovimentos(Tabuleiro tabuleiro);


    public Posicao getPosicao() {
        return posicao;
    }

    public String getCor() {
        return cor;
    }

    public String toString2() {
        return "Peca{" + "\n" +
                "Cor = " + cor + "\n" +
                "Posicao = " + posicao.getID() + "\n" +
                '}';
    }
}
