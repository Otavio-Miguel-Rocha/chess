package Partida.Tabuleiro;

import Partida.Jogador;
import Partida.Tabuleiro.Pecas.*;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {
    private List<Posicao> listaPosicoes = new ArrayList<>(64);

    public Tabuleiro() {
        //EXECUTA UM FORI PARA CRIAR E DEFINIR AS 64 POSIÇÕES INICIAIS
        for (int i = 0; i < 64; i++) {
            //ADICIONA NA LISTA
            listaPosicoes.add(new Posicao());

            //CHAMA A POSIÇÃO PARA ATRIBUIR UMA COORDENADA
            atribuiPosicao(i, listaPosicoes.get(i));

            //Peão
            if (i >= 8 && i <= 15) {
                listaPosicoes.get(i).setPeca(new Peao(i, "Preto", listaPosicoes.get(i)));
            }
            if (i >= 48 && i <= 55) {
                listaPosicoes.get(i).setPeca(new Peao(i, "Branco", listaPosicoes.get(i)));
            }
            //
            //Torre
            if (i == 0 || i == 7) {
                listaPosicoes.get(i).setPeca(new Torre(i, "Preto", listaPosicoes.get(i)));
            }
            if (i == 56 || i == 63) {
                listaPosicoes.get(i).setPeca(new Torre(i, "Branco", listaPosicoes.get(i)));
            }
            //
            //Cavalo
            if (i == 1 || i == 6) {
                listaPosicoes.get(i).setPeca(new Cavalo(i, "Preto", listaPosicoes.get(i)));
            }
            if (i == 57 || i == 61) {
                listaPosicoes.get(i).setPeca(new Cavalo(i, "Branco", listaPosicoes.get(i)));
            }
            //
            //Bispo
            if (i == 2 || i == 5) {
                listaPosicoes.get(i).setPeca(new Bispo(i, "Preto", listaPosicoes.get(i)));
            }
            if (i == 58 || i == 62) {
                listaPosicoes.get(i).setPeca(new Bispo(i, "Branco", listaPosicoes.get(i)));
            }
            //
            //Rainha
            if (i == 3) {
                listaPosicoes.get(i).setPeca(new Rainha(i, "Preto", listaPosicoes.get(i)));
            }
            if (i == 59) {
                listaPosicoes.get(i).setPeca(new Rainha(i, "Branco", listaPosicoes.get(i)));
            }
            //
            //Rei
            if (i == 4) {
                listaPosicoes.get(i).setPeca(new Rei(i, "Preto", listaPosicoes.get(i)));
            }
            if (i == 60) {
                listaPosicoes.get(i).setPeca(new Rei(i, "Branco", listaPosicoes.get(i)));
            }
            //

        }
    }

    public void atribuiPosicao(int index, Posicao posicaoManipulada) {
        //ARMAZENA A POSIÇÃO
        String posicao = "";

        //DEFINE A LETRA DE ACORDO COM A LINHA
        if (index <= 7) {
            posicao = "H";
        } else if (index <= 15) {
            posicao = "G";
        } else if (index <= 23) {
            posicao = "F";
        } else if (index <= 31) {
            posicao = "E";
        } else if (index <= 39) {
            posicao = "D";
        } else if (index <= 47) {
            posicao = "C";
        } else if (index <= 55) {
            posicao = "B";
        } else if (index <= 63) {
            posicao = "A";
        }

        //DEFINE O NÚMERO DE ACORDO COM A COLUNA
        if (index % 8 == 0) {
            //1
            posicao = posicao + "1";
        } else if ((index + 1) % 8 == 0) {
            //8
            posicao = posicao + "8";
        } else if ((index + 2) % 8 == 0) {
            //7
            posicao = posicao + "7";
        } else if ((index + 3) % 8 == 0) {
            //6
            posicao = posicao + "6";
        } else if ((index + 4) % 8 == 0) {
            //5
            posicao = posicao + "5";
        } else if ((index + 5) % 8 == 0) {
            //4
            posicao = posicao + "4";
        } else if ((index + 6) % 8 == 0) {
            //3
            posicao = posicao + "3";
        } else if ((index + 7) % 8 == 0) {
            //2
            posicao = posicao + "2";
        }

        //SET O ID DA POSIÇÃO
        posicaoManipulada.setID(posicao);
    }

    public Posicao verificarPosicao(String coordenada) {
        //RECEBE A COORDENADA, PERCORRE A LISTA DE POSIÇÕES E VERIFICA SE A COORDENADA É VALIDA
        //RETORNANDO A POSIÇÃO OU NULL
        for (Posicao posicao : listaPosicoes) {
            if (posicao.getID().equals(coordenada)) {
                return posicao;
            }
        }
        return null;
    }

    public boolean simulaMovimento(Posicao posicao, Peca pecaDaPosicao, Jogador jogadorAdversario) {
        //ARMAZENA A PEÇA DA POSIÇÃO PARA O ROLLBACK
        Peca pecaTemp = posicao.getPeca();

        //ARMAZENA A POSIÇÃO ATUAL PARA O ROLL BACK
        Posicao posicaoAtual = pecaDaPosicao.getPosicao();

        //SIMULA O MOVIMENTO
        posicao.setPeca(pecaDaPosicao);
        pecaDaPosicao.getPosicao().setPeca(null);
        pecaDaPosicao.setPosicao(posicao);


        //PERCORRE A LISTA DE POSICOES DAS PEÇAS ADVERSÁRIAS, VERIFICANDO SE COM O POSSÍVEL MOVIMENTO
        //O REI ALIADO FICA EM CHEQUE!
        boolean cheque = false;
        for (Peca pecaFor : jogadorAdversario.getPecas()) {
            //VERIFICA SE NÃO É A PEÇA TEMPORÁRIA, POIS POR MAIS QUE NO TABULEIRO ELA TENHA SIDO ABATIDA
            //ELA SEGUE NA LISTA DE PEÇAS DO JOGADOR
            if (pecaFor != pecaTemp) {
                for (Posicao posicaoFor2 : pecaFor.possiveisMovimentos(this)) {
                    if (posicaoFor2.getPeca() != null) {
                        if (posicaoFor2.getPeca() instanceof Rei) {
                            cheque = true;
                        }
                    }
                }
            }
        }

        //EXECUTA O ROLL BACK DA SIMULAÇÃO DO MOVIMENTO
        //COM AS VARIÁVEIS ARMAZENADAS
        posicao.setPeca(pecaTemp);
        pecaDaPosicao.setPosicao(posicaoAtual);
        pecaDaPosicao.getPosicao().setPeca(pecaDaPosicao);


        //CASO DURANTE A SIMULAÇÃO, O FOR EACH ENCONTRAR UM POSSÍVEL MOVIMENTO
        //QUE COINCIDA COM O REI, ELE RETORNARÁ SE AQUELE POSSÍVEL MOVIMENTO CAUSARÁ UM CHEQUE OU NÃO
        if (cheque) {
            return true;
        } else
            return false;
    }


    public List<Posicao> filtrarPossiveisMovimentos(Peca pecaDaPosicao, Jogador jogadorAdversario) {
        //CRIA UMA LISTA QUE SERÁ O RETORNO DO MÉTODO
        List<Posicao> possiveisMovimentosVerificadosCheque = new ArrayList<>();

        //PERCORRE A LISTA DE POSSÍVEIS DA PEÇA
        for (Posicao posicao : pecaDaPosicao.possiveisMovimentos(this)) {

            //EXECUTA A SIMULAÇÃO DO MOVIMENTO
            //SE O MOVIMENTO CAUSAR UM CHEQUE PARA A EQUIPE, O METODO RETORNA TRUE
            //CASO NADA ACONTEÇA, O METODO RETORNA FALSE

            if (!this.simulaMovimento(posicao, pecaDaPosicao, jogadorAdversario)) {
                //RETORNANDO FALSE(NÃO CHEQUE), ELE ADICIONA NA LISTA
                possiveisMovimentosVerificadosCheque.add(posicao);
            }
        }

        //RETORNA A LISTA FILTRADA
        return possiveisMovimentosVerificadosCheque;
    }

    public void executarRoque(Peca rei, Posicao posicaoRei, Peca torre, Posicao posicaoTorre) {

        //REI
        //Atribui a peça para a nova posição no tabuleiro
        posicaoRei.setPeca(rei);
        // Remove a peça da posição anterior no tabuleiro
        rei.getPosicao().setPeca(null);
        // Troca a posição atual da peça na lógica
        rei.setPosicao(posicaoRei);
        ((Rei) rei).setPrimeiroMovimento();

        //TORRE
        //Atribui a peça para a nova posição no tabuleiro
        posicaoTorre.setPeca(torre);
        // Remove a peça da posição anterior no tabuleiro
        torre.getPosicao().setPeca(null);
        // Troca a posição atual da peça na lógica
        torre.setPosicao(posicaoTorre);
        ((Torre) torre).setPrimeiroMovimento();

    }

    public String imprimirTabuleiro(Jogador jogadorBrancas, Jogador jogadorPretas) {

        //VARIÁVEL PARA ARMAZENAR A STRING DO TABULEIRO
        String tabuleiro = "";

        //IMPRIME O JOGADOR DAS PRETAS
        tabuleiro += (jogadorPretas.getNome() + "\n");
        tabuleiro += (jogadorPretas.getPecasAdversarioAbatidas() + "\n");


        //IMPRIME AS COORDENADAS HORIZONTAIS
        tabuleiro += ("      1      ");
        tabuleiro += ("     2      ");
        tabuleiro += ("     3      ");
        tabuleiro += ("     4      ");
        tabuleiro += ("     5      ");
        tabuleiro += ("     6      ");
        tabuleiro += ("     7      ");
        tabuleiro += ("     8      ");
        tabuleiro += ("\n--------------------------------------------------------" +
                "----------------------------------------\n");

        //PERCORRE A LISTA DE 64 POSIÇÕES DO TABULEIRO
        for (Posicao posicao : listaPosicoes) {

            //RESGATA O INDICE DA POSIÇÃO PARA LÓGICA DE BORDAS E QUEBRAS DE LINHA
            int posicaoNoTabuleiro = listaPosicoes.indexOf(posicao);

            //FAZ A BORDA NA ESQUERDA
            if (posicaoNoTabuleiro % 8 == 0) {
                tabuleiro += ("|  ");
            }

            //CASO A PEÇA NÃO SEJA NULA
            if (posicao.getPeca() != null) {

                //NEM REI, NEM RAINHA, POIS ELES TEM 3 CARACTERES
                if (posicao.getPeca() instanceof Rei || posicao.getPeca() instanceof Rainha) {
                    //PARA DOIS CARACTERES A PEÇA
                    tabuleiro += ("  " + posicao.getPeca() + "  ");
                }
                //CASO SEJA, 3 CARACTERES
                else {
                    //PARA 3 CARACTERES
                    tabuleiro += ("   " + posicao.getPeca() + "  ");
                }
            }
            //CASO NÃO SEJA UMA PEÇA, ELE IMPRIME O ESPAÇO SEM PEÇA
            else {
                tabuleiro += ("       ");
            }

            //COORDENADAS HORIZONTAIS

            if ((posicaoNoTabuleiro + 1) % 8 == 0 || posicaoNoTabuleiro == 63) {
                if (posicaoNoTabuleiro == 7) {
                    tabuleiro += (" |  H ");
                }
                if (posicaoNoTabuleiro == 15) {
                    tabuleiro += (" |  G ");
                }
                if (posicaoNoTabuleiro == 23) {
                    tabuleiro += (" |  F ");
                }
                if (posicaoNoTabuleiro == 31) {
                    tabuleiro += (" |  E ");
                }
                if (posicaoNoTabuleiro == 39) {
                    tabuleiro += (" |  D ");
                }
                if (posicaoNoTabuleiro == 47) {
                    tabuleiro += (" |  C ");
                }
                if (posicaoNoTabuleiro == 55) {
                    tabuleiro += (" |  B ");
                }
                if (posicaoNoTabuleiro == 63) {
                    tabuleiro += (" |  A ");
                }
                //APLICA A QUEBRA DE LINHA
                if (posicaoNoTabuleiro != 1 || posicaoNoTabuleiro != 64) {
                    tabuleiro += ("\n--------------------------------------------------------" +
                            "----------------------------------------\n");
                }
            }
            //APLICA A DIVISÃO HORIZONTAL
            else {
                tabuleiro += ("  |  ");
            }
        }
        //IMPRIME O JOGADOR BRANCOS
        tabuleiro += (jogadorBrancas.getNome() + "\n");
        tabuleiro += (jogadorBrancas.getPecasAdversarioAbatidas() + "\n");

        //RETORNA A STRING PRONTA
        return tabuleiro;
    }

    public void executarPromocao(Peca novaPeca) {
        //ARMAZENA A POSIÇÃO DA NOVA PEÇA PARA DIMINUIR O CÓDIGO
        Posicao posicaoNovaPeca = novaPeca.getPosicao();

        //SETA A POSIÇÃO DA PEÇA QUE ESTÁ NA POSIÇÃO DE PEÇA
        posicaoNovaPeca.getPeca().setPosicao(null);

        //SETÁ A PEÇA DA POSIÇÃO QUE A PEÇA POSSUI
        posicaoNovaPeca.setPeca(novaPeca);

        //SETA A POSIÇÃO DA NOVA PEÇA
        novaPeca.setPosicao(posicaoNovaPeca);
    }

    public List<Posicao> getPosicoes() {
        return listaPosicoes;
    }
}
