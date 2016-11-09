package controle;

import java.util.ArrayList;
import java.util.Scanner;

import modelo.Estado;
import modelo.No;
import modelo.Pilha;

public class Controle implements modelo.FuncoesControle {

    private ArrayList<Estado> estadosTestados = new ArrayList<Estado>();
    boolean solucaoEncontrada = false;
    private int capacidadeCanoa;
    Pilha pilha = new Pilha();

    public Controle(int capacidadeCanoa) {
        this.capacidadeCanoa = capacidadeCanoa;
    }

    //verifica se um estado j� foi testado
    private boolean isTestado(Estado e) {
        for (int i = 0; i < estadosTestados.size(); i++) {
            if (estadosTestados.get(i).getMargemCanoa() == e.getMargemCanoa()) {
                if (estadosTestados.get(i).getQuantCanibaisEsquerda() == e.getQuantCanibaisEsquerda()) {
                    if (estadosTestados.get(i).getQuantMissionariosDireita() == e.getQuantMissionariosDireita()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //testa se um estado � a solu��o
    private boolean isSolucao(Estado e) {
        if (e.getQuantMissionariosEsquerda() == 0 && e.getQuantCanibaisEsquerda() == 0) {
            return true;
        } else {
            return false;
        }
    }

    //testa se um estado � v�lido
    private boolean isValido(Estado e) {
        if ((e.getQuantCanibaisDireita() <= e.getQuantMissionariosDireita() || e.getQuantMissionariosDireita() == 0) && (e.getQuantCanibaisEsquerda() <= e.getQuantMissionariosEsquerda() || e.getQuantMissionariosEsquerda() == 0)) {
            return true;
        }
        return false;
    }

    //testa a possibilidade de transportar um canibal e um mission�rio e retorna o estado resultante
    private No transportaUmDeCada(No noInicial) {
        No newNo = null;

        Estado e;
        //true = direita; false = esquerda
        boolean ladoCanoa = noInicial.getEstado().getMargemCanoa();
        int quantCanibais = 0;
        int quantMissionarios = 0;

        //Descobrindo em que margem est� a canoa
        if (ladoCanoa == true) {
            quantCanibais = noInicial.getEstado().getQuantCanibaisDireita();
            quantMissionarios = noInicial.getEstado().getQuantMissionariosDireita();
        } else {
            quantCanibais = noInicial.getEstado().getQuantCanibaisEsquerda();
            quantMissionarios = noInicial.getEstado().getQuantMissionariosEsquerda();
        }

        if (quantCanibais >= 1 && quantMissionarios >= 1) {
            if (ladoCanoa == true) {
                e = new Estado(!ladoCanoa, noInicial.getEstado().getQuantMissionariosEsquerda() + 1, quantMissionarios - 1, noInicial.getEstado().getQuantCanibaisEsquerda() + 1, quantCanibais - 1);
            } else {
                e = new Estado(!ladoCanoa, quantMissionarios - 1, noInicial.getEstado().getQuantMissionariosDireita() + 1, quantCanibais - 1, noInicial.getEstado().getQuantCanibaisDireita() + 1);
            }

            newNo = new No(noInicial, e);
        }

        return newNo;
    }

    //testa a possibilidade de transportar apenas canibais e retorna o estado resultante
    private No transportaCanibais(No noInicial, int numero) {
        No newNo = null;
        if (numero >= 1 && numero <= capacidadeCanoa) {
            Estado e;
            //true = direita; false = esquerda
            boolean ladoCanoa = noInicial.getEstado().getMargemCanoa();
            int quantCanibais = 0;

            //Descobrindo em que margem est� a canoa
            if (ladoCanoa == true) {
                quantCanibais = noInicial.getEstado().getQuantCanibaisDireita();
            } else {
                quantCanibais = noInicial.getEstado().getQuantCanibaisEsquerda();
            }

            if (quantCanibais >= numero) {
                if (ladoCanoa == true) {
                    e = new Estado(!ladoCanoa, noInicial.getEstado().getQuantMissionariosEsquerda(), noInicial.getEstado().getQuantMissionariosDireita(), noInicial.getEstado().getQuantCanibaisEsquerda() + numero, quantCanibais - numero);
                } else {
                    e = new Estado(!ladoCanoa, noInicial.getEstado().getQuantMissionariosEsquerda(), noInicial.getEstado().getQuantMissionariosDireita(), quantCanibais - numero, noInicial.getEstado().getQuantCanibaisDireita() + numero);
                }

                newNo = new No(noInicial, e);
            }
        }

        return newNo;
    }

    //testa a possibilidade de transportar apenas mission�rios e retorna o estado resultante
    private No transportaMissionarios(No noInicial, int numero) {
        No newNo = null;
        if (numero >= 1 && numero <= capacidadeCanoa) {
            Estado e;
            //true = direita; false = esquerda
            boolean ladoCanoa = noInicial.getEstado().getMargemCanoa();
            int quantMissionarios = 0;

            //Descobrindo em que margem est� a canoa
            if (ladoCanoa == true) {
                quantMissionarios = noInicial.getEstado().getQuantMissionariosDireita();
            } else {
                quantMissionarios = noInicial.getEstado().getQuantMissionariosEsquerda();
            }

            if (quantMissionarios >= numero) {
                if (ladoCanoa == true) {
                    e = new Estado(!ladoCanoa, noInicial.getEstado().getQuantMissionariosEsquerda() + numero, quantMissionarios - numero, noInicial.getEstado().getQuantCanibaisEsquerda(), noInicial.getEstado().getQuantCanibaisDireita());
                } else {
                    e = new Estado(!ladoCanoa, quantMissionarios - numero, noInicial.getEstado().getQuantMissionariosDireita() + numero, noInicial.getEstado().getQuantCanibaisEsquerda(), noInicial.getEstado().getQuantCanibaisDireita());
                }

                newNo = new No(noInicial, e);
            }
        }

        return newNo;
    }

    private boolean testaTudo(No novoNo, No noInicial) {
        if (isValido(novoNo.getEstado())) {
            if (!isTestado(novoNo.getEstado())) {
                //um novo filho � adicionado ao n� incial
                noInicial.addFilho(novoNo);
                //Exibe informa��o de n� expandido
                System.out.println("Novo No: " + novoNo.getEstado().getMargemCanoa() + ", " + novoNo.getEstado().getQuantMissionariosEsquerda() + ", "
                        + novoNo.getEstado().getQuantMissionariosDireita() + ", " + novoNo.getEstado().getQuantCanibaisEsquerda() + ", " + novoNo.getEstado().getQuantCanibaisDireita());
                //Expande o n�
                Scanner input = new Scanner(System.in);
                String pausa = input.next();
                return true;
            } else {
                //Estado j� foi testado
            }
        } else {
            //Exibir estado inv�lido
        }
        return false;
    }

    @Override
    public void buscaEmLargura(No noInicial) {
        // TODO Auto-generated method stub

    }

    @Override
    public void buscaEmProfundidade(No noInicial) {
        System.out.println("Fronteira de Estados: ");
        pilha.imprimir();
        if (!isSolucao(noInicial.getEstado())) {
            pilha.insere(noInicial);
            estadosTestados.add(noInicial.getEstado());
            No novoNo;

            //In�cio de testes de possibilidades de Transporte
            //Teste de possibilidade de transportar um de cada
            novoNo = transportaUmDeCada(noInicial);
            if (novoNo != null) {
                if (testaTudo(novoNo, noInicial)) {
                    pilha.remove();
                    pilha.insere(novoNo);
                    noInicial.addFilho(novoNo);
                    buscaEmProfundidade(novoNo);
                }

            } else {
                //Impossibilidade de transportar um de cada
            }
            //Fim da primeira possibilidade

            //Teste de possibilidade de transportar apenas canibais
            for (int i = capacidadeCanoa; i > 0; i--) {
                if (!solucaoEncontrada) {
                    novoNo = transportaCanibais(noInicial, i);
                    if (novoNo != null) {
                        if (testaTudo(novoNo, noInicial)) {
                            pilha.remove();
                            pilha.insere(novoNo);
                            noInicial.addFilho(novoNo);
                            buscaEmProfundidade(novoNo);
                        }
                    } else {
                        ////Impossibilidade de transportar apenas canibais
                    }
                } else {
                    break;
                }
            }
            //Fim da segunda possibilidade

            //Teste de possibilidade de transportar apenas mission�rios
            for (int i = capacidadeCanoa; i > 0; i--) {
                if (!solucaoEncontrada) {
                    novoNo = transportaMissionarios(noInicial, i);
                    if (novoNo != null) {
                        if (testaTudo(novoNo, noInicial)) {
                            pilha.remove();
                            pilha.insere(novoNo);
                            noInicial.addFilho(novoNo);
                            buscaEmProfundidade(novoNo);
                        }
                    } else {
                        ////Impossibilidade de transportar apenas mission�rios
                    }
                } else {
                    break;
                }
            }
            //Fim da terceira possibilidade

            //Fim do teste de todas as possibilidades
            if (!solucaoEncontrada) {
                //-------FIM DO RAMO--------
                System.out.println("-----------FIM DO RAMO---------\n");
                estadosTestados.remove(estadosTestados.size() - 1);
                 pilha.remove();
//				Scanner input = new Scanner(System.in);
//				String pausa = input.next();
            }
        } else {
            solucaoEncontrada = true;
        }
//		pilha.imprimir();
    }

    @Override
    public void buscaGulosa(No noInicial) {
        // TODO Auto-generated method stub

    }

    @Override
    public void buscaA(No noInicial) {
        // TODO Auto-generated method stub

    }

}
