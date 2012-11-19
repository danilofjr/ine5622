package ine5622.tp1.modelo;

import java.util.ArrayList;

/**
 *
 * Classe que define uma producao de uma gramatica regular
 */
public class Producao {
    
    private boolean simboloInicial;
    private String simboloProducao;
    private ArrayList<String> producoes;

    public Producao(String nomeProducao, ArrayList<String> producoes) {
        this.simboloProducao = nomeProducao;
        this.producoes = producoes;
    }

    public Producao(String nomeProducao, ArrayList<String> producoes, boolean simboloInicial) {
        this.simboloInicial = simboloInicial;
        this.simboloProducao = nomeProducao;
        this.producoes = producoes;
    }

    public String getNomeProducao() {
        return simboloProducao;
    }

    public void setNomeProducao(String nomeProducao) {
        this.simboloProducao = nomeProducao;
    }

    public ArrayList<String> getProducoes() {
        return producoes;
    }

    public void setProducoes(ArrayList<String> producoes) {
        this.producoes = producoes;
    }

    public boolean isSimboloInicial() {
        return simboloInicial;
    }

    public void setSimboloInicial(boolean simboloInicial) {
        this.simboloInicial = simboloInicial;
    }
    
    
    
    
}
