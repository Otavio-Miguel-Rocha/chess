package Partida;

import Partida.Tabuleiro.Pecas.Peao;
import Partida.Tabuleiro.Pecas.Peca;
import Partida.Tabuleiro.Pecas.Rei;
import Partida.Tabuleiro.Pecas.Torre;
import Partida.Tabuleiro.Posicao;
import Partida.Tabuleiro.Tabuleiro;

import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private String nome;
    private String senha;
    private String cor;
    private List<Peca> pecas;
    private List<Peca> pecasDoAdversario = new ArrayList<>();
    private boolean cheque = false;

    private static List<Jogador> listaJogadoresCadastrados = new ArrayList<>();

    public Jogador(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
        this.pecas = new ArrayList<>();
        listaJogadoresCadastrados.add(this);
    }

    public boolean moverPeca(Peca peca, Posicao posicao, Partida partida, List<Posicao> possiveisMovimentos) {

        if (posicao.getPeca() == null) {
            if (peca instanceof Peao) {
                System.out.println(((Peao) peca).isPrimeiroMovimento());
                int indicePosicaoMovida = partida.getTabuleiro().getPosicoes().indexOf(posicao);
                if (peca.getCor().equals("Branco")) {
                    if (indicePosicaoMovida >= 16 && indicePosicaoMovida <= 23) {
                        Peca possivelPeao = partida.getTabuleiro().getPosicoes().get(indicePosicaoMovida + 8).getPeca();
                        if (possivelPeao != null) {
                            if (possivelPeao instanceof Peao && possivelPeao.getCor().equals("Preto")) {
                                if (((Peao) possivelPeao).isMovimentoDuplo()) {
                                    Posicao posicaoPeaoElPassant = possivelPeao.getPosicao();
                                    posicaoPeaoElPassant.setPeca(null);
                                    possivelPeao.setPosicao(null);
                                    partida.getJogadorAdversario().pecas.remove(possivelPeao);
                                    pecasDoAdversario.add(possivelPeao);
                                }
                            }
                        }
                    }
                } else {
                    if (indicePosicaoMovida >= 40 && indicePosicaoMovida <= 47) {
                        Peca possivelPeao = partida.getTabuleiro().getPosicoes().get(indicePosicaoMovida - 8).getPeca();
                        if (possivelPeao != null) {
                            if (possivelPeao instanceof Peao && possivelPeao.getCor().equals("Branco")) {
                                System.out.println(((Peao) possivelPeao).isMovimentoDuplo());
                                if (((Peao) possivelPeao).isMovimentoDuplo()) {
                                    Posicao posicaoPeaoElPassant = possivelPeao.getPosicao();
                                    posicaoPeaoElPassant.setPeca(null);
                                    possivelPeao.setPosicao(null);
                                    partida.getJogadorAdversario().pecas.remove(possivelPeao);
                                    pecasDoAdversario.add(possivelPeao);
                                }
                            }
                        }
                    }
                }
            }
        }

        Peca pecaAdversaria = posicao.getPeca();
        boolean valida = peca.mover(posicao, possiveisMovimentos);
        if (pecaAdversaria != null && valida) {
            partida.getJogadorAdversario().pecas.remove(pecaAdversaria);
            pecasDoAdversario.add(pecaAdversaria);
        }
        if (peca instanceof Peao peao) {
            if (peao.isPrimeiroMovimento()) {
                int indicePosicao = partida.getTabuleiro().getPosicoes().indexOf(posicao);
                if ((indicePosicao >= 24 && indicePosicao <= 31) || (indicePosicao >= 32 && indicePosicao <= 39)) {
                    peao.setMovimentoDuplo(true);
                }
                peao.setPrimeiroMovimento();
            } else {
                peao.setMovimentoDuplo(false);
            }
        }
        if (peca instanceof Rei rei && rei.isPrimeiroMovimento()) {
            rei.setPrimeiroMovimento();
        }
        if (peca instanceof Torre torre && torre.isPrimeiroMovimento()) {
            torre.setPrimeiroMovimento();
        }
        //se a peça jogada for um peão, é declaro que o primeiro movimento já é considerado falso
        return valida;
    }

    public boolean validaSenha(String senha) {
        if (senha.equals(this.senha)) {
            return true;
        }
        return false;
    }

    public void associarPecasAoJogador(String cor, Tabuleiro tabuleiro) {
        this.cor = cor;
        for (Posicao posicao : tabuleiro.getPosicoes()) {
            if (posicao.getPeca() != null) {
                if (posicao.getPeca().getCor().equals(this.cor)) {
                    this.pecas.add(posicao.getPeca());
                }
            }
        }
    }


    public void setCheque(boolean set) {
        this.cheque = set;
    }

    public boolean isCheque() {
        return cheque;
    }

    public String getNome() {
        return this.nome;
    }

    public String getCor() {
        return this.cor;
    }

    public List<Peca> getPecas() {
        return pecas;
    }

    public List<Peca> getPecasAdversarioAbatidas() {
        return pecasDoAdversario;
    }

    public static List<Jogador> getListaJogadoresCadastrados() {
        return listaJogadoresCadastrados;
    }
}
