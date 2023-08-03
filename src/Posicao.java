public class Posicao {
    private Peca peca;
    private String identificadorPosicao;


    public void setPeca(Peca peca) {
        this.peca = peca;

    }

    public Peca getPeca() {
        return this.peca;
    }

    public void setID(String identificador) {
        this.identificadorPosicao = identificador;
    }

    public String getID() {
        return this.identificadorPosicao;
    }

    @Override
    public String toString() {
        return "Posicao{" +
                "peca = " + peca +
                '}';
    }
}
