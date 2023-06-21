public class Posicao {
    private Peca peca;


    public void setPeca(Peca peca) {
        this.peca = peca;
    }

    public Peca getPeca() {
        return peca;
    }

    @Override
    public String toString() {
        return "Posicao{" +
                "peca = " + peca +
                '}';
    }
}
