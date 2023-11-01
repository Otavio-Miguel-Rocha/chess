package Partida;

import Partida.Tabuleiro.Pecas.Peca;
import Partida.Tabuleiro.Pecas.Rei;
import Partida.Tabuleiro.Posicao;
import Partida.Tabuleiro.Tabuleiro;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Partida {

    private Tabuleiro tabuleiro;
    private Jogador jogadorBrancas;
    private Jogador jogadorPretas;
    private Jogador vencedor;
    private boolean empate = false;
    private boolean afogamento = false;
    private boolean chequeMate = false;
    private int rodada = 0;

    public Partida(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        definicoesDaPartida();
    }

    private void definicoesDaPartida() {
        Random random = new Random();
        int numeroSorteio = random.nextInt(2);
        List<Jogador> listaJogadores = Jogador.getListaJogadoresCadastrados();
        if (numeroSorteio % 2 == 0) {
            jogadorBrancas = listaJogadores.get(0);
            jogadorPretas = listaJogadores.get(1);
        } else {
            jogadorBrancas = listaJogadores.get(1);
            jogadorPretas = listaJogadores.get(0);
        }
        jogadorBrancas.associarPecasAoJogador("Branco", tabuleiro);
        jogadorPretas.associarPecasAoJogador("Preto", tabuleiro);
    }

    public String imprimirTabuleiro() {
        return tabuleiro.imprimirTabuleiro(jogadorBrancas, jogadorPretas);
    }

    public void verificaCheque(Jogador jogadorDaVez, Jogador jogadorAdversario) {
        jogadorAdversario.setCheque(false);
        for (Peca peca : jogadorDaVez.getPecas()) {
            for (Posicao posicao : peca.possiveisMovimentos(tabuleiro)) {
                if (posicao.getPeca() instanceof Rei) {
                    jogadorAdversario.setCheque(true);
                    List<Boolean> verificacaoCheque = new ArrayList<>();
                    for (Peca pecaAdversaria : jogadorAdversario.getPecas()) {
                        for (Posicao posicaoForCheque : pecaAdversaria.possiveisMovimentos(tabuleiro)) {
                            verificacaoCheque.add(tabuleiro.simulaMovimento(posicaoForCheque, pecaAdversaria, jogadorDaVez));
                        }
                    }
                    boolean verificacaoChequeMate = true;
                    for (int i = 0; i < verificacaoCheque.size(); i++) {
                        if (!verificacaoCheque.get(i)) {
                            verificacaoChequeMate = false;
                        }
                    }
                    if (verificacaoChequeMate) {
                        setChequeMate(jogadorDaVez);
                    }
                    break;
                }
            }
        }
    }

    public boolean validarFimDeJogo() {
        return this.chequeMate || this.vencedor != null || this.empate || this.afogamento;
    }

    public void verificaAfogamento() {
        List<Boolean> verificacaoAfogamento = new ArrayList<>();
        for (Peca peca : getJogadorRodada().getPecas()) {
            for (Posicao posicao : peca.possiveisMovimentos(getTabuleiro())) {
                verificacaoAfogamento.add(getTabuleiro().simulaMovimento
                        (posicao, peca, getJogadorAdversario()));
            }
        }
        boolean afogamento = true;
        for (boolean posicao : verificacaoAfogamento) {
            if (!posicao) {
                afogamento = false;
            }
        }
        this.afogamento = afogamento;
    }

    public String listarPossiveisMovimentos(List<Posicao> possiveisMovimentosLista) {
        String possiveisMovimentos = "";
        for (Posicao posicao : possiveisMovimentosLista) {
            if (posicao.getPeca() != null) {
                possiveisMovimentos += posicao.getPeca().toString2() + " - " + posicao.getID() + "\n";
            } else {
                if (posicao.isRoque()) {
                    possiveisMovimentos += "Roque - " + posicao.getID() + "\n";
                } else {
                    possiveisMovimentos += "Espaço Vazio - " + posicao.getID() + "\n";
                }
            }
        }
        return possiveisMovimentos;
    }

    public void desistir(Jogador jogadorVencedor) {
        this.vencedor = jogadorVencedor;
    }

    public Tabuleiro getTabuleiro() {
        return this.tabuleiro;
    }

    public void acrescentarContadorRodada() {
        this.rodada++;
        for (Posicao posicao : this.getTabuleiro().getPosicoes()) {
            posicao.setRoque(false);
        }
    }

    public Jogador getJogadorRodada() {
        if (rodada % 2 == 0) {
            return jogadorBrancas;
        } else {
            return jogadorPretas;
        }
    }

    public Jogador getJogadorAdversario() {
        if (rodada % 2 == 1) {
            return jogadorBrancas;
        } else {
            return jogadorPretas;
        }
    }

    public Jogador getVencedor() {
        return this.vencedor;
    }

    private void setChequeMate(Jogador jogadorDaVez) {
        this.vencedor = jogadorDaVez;
        this.chequeMate = true;
    }

    public void setEmpate(boolean empate) {
        this.empate = empate;
    }

    public boolean executaRoque(Peca pecaSelecionada, Posicao posicaoMovida) {
        Peca torre;
        int indicePosicaoMovida = tabuleiro.getPosicoes().indexOf(posicaoMovida);
        if (indicePosicaoMovida == 62 || indicePosicaoMovida == 6) {
            torre = tabuleiro.getPosicoes().get(indicePosicaoMovida + 1).getPeca();
            tabuleiro.executarRoque(pecaSelecionada, posicaoMovida, torre, tabuleiro.getPosicoes().get(indicePosicaoMovida - 1));
        } else if (indicePosicaoMovida == 58 || indicePosicaoMovida == 2) {
            torre = tabuleiro.getPosicoes().get(indicePosicaoMovida - 2).getPeca();
            tabuleiro.executarRoque(pecaSelecionada, posicaoMovida, torre, tabuleiro.getPosicoes().get(indicePosicaoMovida + 1));
        }
        return true;
    }

    public boolean isAfogamento() {
        return afogamento;
    }
}

