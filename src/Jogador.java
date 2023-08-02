import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private String nome;
    private String senha;
    private String cor;
    private double pontos;
    private List<Peca> pecas;
    private List<Peca> pecasDoAdversario = new ArrayList<>();

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
        //se a peça jogada for um peão, é declaro que o primeiro movimento já é considerado falso
        if(peca instanceof Peao){
            ((Peao) peca).setPrimeiroMovimento();
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
        return this.cor;
    }


    public List<Peca> getPecas() {
        return pecas;
    }

    public List<Peca> getPecasAdversarioAbatidas() {
        return pecasDoAdversario;
    }
}
