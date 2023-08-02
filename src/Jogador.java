import java.util.ArrayList;

public class Jogador {
    private String nome;
    private String senha;
    private String cor;
    private double pontos;
    private ArrayList<Peca> pecas;
    private ArrayList<Peca> pecasDoAdversario = new ArrayList<>();

    public Jogador(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
        this.pecas = new ArrayList<>();
    }

    public boolean moverPeca(Peca peca, Posicao posicao,
                             Tabuleiro tabuleiro, Jogador adversario) {
        Peca pecaAdversaria = posicao.getPeca();
        boolean valida = peca.mover(tabuleiro, posicao);
        if (pecaAdversaria != null && valida) {
            adversario.pecas.remove(pecaAdversaria);
            pecasDoAdversario.add(pecaAdversaria);

        }
        return valida;
    }

    public boolean proporEmpate(Jogador jogador) {
        return true;
    }

    public void desistir() {
    }


    public String getNome(){
        return this.nome;
    }

    public void setCor(String cor, Tabuleiro tabuleiro) {
        this.cor = cor;
        for (Posicao posicao : tabuleiro.getPosicoes()) {
//            if(posicao.getPeca().getCor().equals(this.cor)){
//                this.pecas.add(posicao.getPeca());
//            }
            if (posicao.getPeca() != null) {
                if (posicao.getPeca().getCor().equals(this.cor)) {
                    this.pecas.add(posicao.getPeca());
                }
            }
        }
    }

    public String getCor(){
        System.out.println(this.cor);
        return this.cor;
    }


    public ArrayList<Peca> getPecas() {
        return pecas;
    }

    public ArrayList<Peca> getPecasAdversarioAbatidas() {
        return pecasDoAdversario;
    }
}
