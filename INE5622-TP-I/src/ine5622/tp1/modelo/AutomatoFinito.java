package ine5622.tp1.modelo;

import java.util.ArrayList;
import java.util.Formatter;

/**
 *
 * Classe que define um automato finito
 */
public class AutomatoFinito {

    private ArrayList<Estado> estados; //estados do automato
    private ArrayList<Transicao> transicoes; //transicoes do automato
    private ArrayList<String> simbolos; //simbolos gerados/reconhecidos pelo automato

    public AutomatoFinito(ArrayList<Estado> estados, ArrayList<Transicao> transicoes) {
        this.estados = new ArrayList();
        this.transicoes = new ArrayList();
        this.estados = estados;
        this.transicoes = transicoes;
        this.simbolos = listaSimbolos();
    }

    /**
     * Retorna um array de estados do automato
     *
     * @return os estados do automato
     */
    public ArrayList<Estado> getEstados() {
        return estados;
    }

    /**
     * Define um array de estados do automato
     *
     * @param estados eh um array de estados do automato
     */
    public void setEstados(ArrayList<Estado> estados) {
        this.estados = estados;
    }

    /**
     * Retorna um array de transicoes do automato
     *
     * @return as transicoes do automato
     */
    public ArrayList<Transicao> getTransicoes() {
        return transicoes;
    }

    /**
     * Define um array de transicoes do automato
     *
     * @param transicoes eh um array de transicoes do automato
     */
    public void setTransicoes(ArrayList<Transicao> transicoes) {
        this.transicoes = transicoes;
    }

    /**
     * Adiciona um novo estado ao automato
     *
     * @param e eh um estado
     * @return true se a operacao foi executada com sucesso
     */
    public boolean adicionaEstado(Estado e) {
        if (this.estados.add(e)) {
            return true;
        }
        return false;
    }

    /**
     * Remove um estado existente do automato
     *
     * @param e eh um estado
     * @return true se a operacao foi executada com sucesso
     */
    public boolean removeEstado(Estado e) {
        if (this.estados.remove(e)) {
            return true;
        }
        return false;
    }

    /**
     * Verifica se existe um determinado estado no automato
     *
     * @param e eh um estado
     * @return true se o estado
     * @param e existe no automato
     */
    public boolean existeEstado(Estado e) {
        boolean existe = false;
        for (Estado est : this.estados) {
            if (est == e) {
                existe = true;
            }
        }
        return existe;
    }

    /**
     * Adiciona uma nova transicao ao automato
     *
     * @param t eh uma transicao
     * @return true se a operacao foi executada com sucesso
     */
    public boolean adicionaTransicao(Transicao t) {
        if (this.transicoes.add(t)) {
            return true;
        }
        return false;
    }

    /**
     * Remove uma transicao existente do automato
     *
     * @param t eh uma transicao
     * @return true se a operacao foi executada com sucesso
     */
    public boolean removeTransicao(Transicao t) {
        if (this.transicoes.remove(t)) {
            return true;
        }
        return false;
    }

    /**
     * Verifica e lista quais simbolos sao gerados/reconhecidos pelo automato
     *
     * @return uma lista de simbolos gerados/reconhecidos pelo automato
     */
    private ArrayList<String> listaSimbolos() {
        ArrayList<String> s = new ArrayList();
        for (Transicao trans : this.transicoes) {
            String simbolo = trans.getSimbolo();
            if (!s.contains(simbolo)) {
                s.add(simbolo);
            }
        }
        return s;
    }

    /**
     * Retorna uma representacao do automato finito em forma de String
     *
     * @return uma representacao do automato finito
     */
    @Override
    public String toString() {//AutomatoFinito af) {
        //cria o formatador da tabela
        Formatter formatter = new Formatter();

        //cria uma tabela (array bidimensional) para representar o automado em forma de tabela
        int linhas = estados.size() + 1;
        int colunas = simbolos.size() + 1;
        String[][] tabela = new String[linhas][colunas];
        
        //Simbolos indicativos de estado inicial ou estado final
        String estFinal = "*";
        String estInicial = "->";

        //define os rotulos das linhas e das colunas da tabela
        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {

                if (linha == 0 && coluna == 0) {
                    tabela[linha][coluna] = "q";
                }

                if (linha == 0 && coluna > 0) {
                    tabela[linha][coluna] = simbolos.get(coluna - 1);
                }

                if (linha > 0 && coluna == 0) {
                    tabela[linha][coluna] = estados.get(linha - 1).getId();
                    if(estados.get(linha - 1).isEstadoFinal()){
                        //se o estado é final, acrescenta '*' a sua representacao
                        tabela[linha][coluna]=estFinal.concat(tabela[linha][coluna]);
                    }
                    if(estados.get(linha - 1).isEstadoInicial()){
                        //se o estado é inicial, acrescenta '->' a sua representacao
                        tabela[linha][coluna]=estInicial.concat(tabela[linha][coluna]);
                    }
                }
            }
        }

        //define os estados na tabela
        for (int linha = 1; linha < linhas; linha++) {
            for (int coluna = 1; coluna < colunas; coluna++){
                for(Transicao t : transicoes){
                    if((tabela[linha][0]).contains(t.getEstadoOrigem().getId())){
                        if(t.getSimbolo().equals(tabela[0][coluna])){
                            if (tabela[linha][coluna] == null) {
                                tabela[linha][coluna]=t.getEstadoDestino().getId();
                            }
                            else{
                                tabela[linha][coluna] = tabela[linha][coluna].concat(t.getEstadoDestino().getId());
                            }                            
                        }                       
                    }
                }
            }            
        }
        
        //imprime a tabela do automato
        for (int linha = 0; linha < linhas; linha++) {
            boolean linhaMudou = false;
            while (!linhaMudou) {
                for (int coluna = 0; coluna < colunas; coluna++) {
                    formatter = new Formatter();
                    String dado = "";
                    if (tabela[linha][coluna] == null) {
                        dado = "-";
                    } else {
                        dado = tabela[linha][coluna];
                    }
                    System.out.print(formatter.format("%5s", dado) + "\t");
                }
                linhaMudou = true;
                System.out.println();
            }            
        }        
        return null;
    }
}
