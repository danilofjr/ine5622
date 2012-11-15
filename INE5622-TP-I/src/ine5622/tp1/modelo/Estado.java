package ine5622.tp1.modelo;

import java.util.ArrayList;

/**
 *
 * Classe que define o estado de um automato e suas propriedades
 */
public class Estado {

    private String id; //identificador do estado    
    private boolean estadoInicial; //identifica se o estado eh inicial
    private boolean estadoFinal; //identifica se o estado eh final
    //private ArrayList<String> simbolos; //simbolos gerados a partir do estado ??necessario?? ja que tem transicoes[]?
    //private ArrayList<Estado> estadosAlcancaveis; //estados alcancaveis a partir deste ??necessario?? ja que tem transicoes[]?
    //private Transicao[] transicoes; //transicoes de um estado
    //private boolean estadoEpsilon; //necessario?   

    public Estado(String id, boolean estadoInicial, boolean estadoFinal) {
        this.id = id;
        this.estadoInicial = estadoInicial;
        this.estadoFinal = estadoFinal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(boolean estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public boolean isEstadoFinal() {
        return estadoFinal;
    }

    public void setEstadoFinal(boolean estadoFinal) {
        this.estadoFinal = estadoFinal;
    }

//    public ArrayList<Estado> getEstadosAlcancaveis() {
//        return estadosAlcancaveis;
//    }
//
//    public void setEstadosAlcancaveis(ArrayList<Transicao> transicoes) {
//        this.estadosAlcancaveis = new ArrayList();
//        for (Transicao t : transicoes) {
//            if (t.getEstadoOrigem() == this) {
//                this.estadosAlcancaveis.add(t.getEstadoDestino());
//            }
//        }
//    }
//
//    public ArrayList<String> getSimbolos() {
//        return simbolos;
//    }
//
//    public void setSimbolos(ArrayList<Transicao> transicoes) {
//        this.simbolos = new ArrayList();
//        for (Transicao t : transicoes) {
//            if (t.getEstadoOrigem() == this) {
//                this.simbolos.add(t.getSimbolo());
//            }
//        }
//        this.simbolos = simbolos;
//    }

    public String toString() {
        String resultado = "Estado " + this.getId();
        return resultado;
    }
}
