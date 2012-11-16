package ine5622.tp1.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Formatter;

/**
 *
 * Classe que define um automato finito
 */
public class AutomatoFinito implements Serializable {

    private ArrayList<Estado> estados; //estados do automato
    private ArrayList<Transicao> transicoes; //transicoes do automato
    private ArrayList<String> simbolos; //simbolos gerados/reconhecidos pelo automato
    private ArrayList<Estado> estadosAlcancaveis; //lista de estados alcancaveis do automato
    private ArrayList<Estado> estadosVivos; //lista de estados vivos do automato

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
     * Verifica quais os estados do automato sao alcancaveis a partir do estado inicial
     *
     * @param
     * @return
     */
    public void verificaEstadosAlcancaveis() {
        //procura o estado inicial e adiciona os estados alcancaveis a partir dele ao list de estados alcancaveis
        for (Transicao t : this.transicoes) {
            //verifica se o estado de origem 'e' da transicao 't' é inicial
            if (t.getEstadoOrigem().isEstadoInicial()) {
                //adiciona o estado de origem ao list de estados alcancaveis, se nao duplicado (um estado inicial eh sempre alcancavel)
                if (!this.estadosAlcancaveis.contains(t.getEstadoOrigem())) {
                    this.estadosAlcancaveis.add(t.getEstadoOrigem());
                }
                //adiciona o estado de destino ao list de estados alcancaveis, se nao duplicado
                if (!this.estadosAlcancaveis.contains(t.getEstadoDestino())) {
                    this.estadosAlcancaveis.add(t.getEstadoDestino());
                }
            }
        }
        //procura novos estados alcancaveis a partir do list de estados alcancaveis
        for (Estado e : this.estadosAlcancaveis) {
            for (Transicao t : this.transicoes) {
                if (t.getEstadoOrigem() == e) {
                    if (!this.estadosAlcancaveis.contains(t.getEstadoDestino())) {
                        this.estadosAlcancaveis.add(t.getEstadoDestino());
                    }
                }
            }
        }
    }

    /**
     * Verifica quais os estados do automato alcancam estados finais
     *
     * @param
     * @return
     */
    public void verificaEstadosVivos() {
        //procura estados finais e os adiciona ao list de estados vivos
        for (Transicao t : this.transicoes) {
            //verifica se o estado de origem 'e' da transicao 't' é final
            if (t.getEstadoOrigem().isEstadoFinal()) {
                //adiciona o estado de origem ao list de estados finais, se nao duplicado (um estado final eh sempre vivo)
                if (!this.estadosVivos.contains(t.getEstadoOrigem())) {
                    this.estadosVivos.add(t.getEstadoOrigem());
                }
            }
        }
        //procura novos estados vivos a partir do list de estados vivos
        for (Estado e : this.estadosVivos) {
            for (Transicao t : this.transicoes) {
                if (t.getEstadoDestino() == e) {
                    if (!this.estadosVivos.contains(t.getEstadoOrigem())) {
                        this.estadosVivos.add(t.getEstadoOrigem());
                    }
                }
            }
        }
    }

    /**
     * Elimina os estados do automato que nao sao alcancaveis a partir do estado inicial
     *
     * @param
     * @return
     */
    public void eliminaEstadosInalcancaveis() {
        //faz a intersecção entre as lists 'estados' e 'estadosAlcancaveis'
        this.estados.retainAll(this.estadosAlcancaveis);
        //elimina as transicoes de estados inalcancaveis
        for (Estado e : this.estados) {
            for (Transicao t : this.transicoes) {
                if (!this.estados.contains(t.getEstadoOrigem())) {
                    this.transicoes.remove(t);
                }
            }
        }
    }

    /**
     * Elimina os estados do automato que nao alcancam estados finais
     *
     * @param
     * @return
     */
    public void eliminaEstadosMortos() {
        //faz a intersecção entre as lists 'estados' e 'estadosVivos'
        this.estados.retainAll(this.estadosVivos);
        //elimina as transicoes de estados vivos
        for (Estado e : this.estados) {
            for (Transicao t : this.transicoes) {
                if (!this.estados.contains(t.getEstadoOrigem())) {
                    this.transicoes.remove(t);
                }
            }
        }
    }

    
    //###teste criacao de CE com objetos
    public void classesDeEquivalencia() {
        //list que armazena lists que representam classes de equivalencia (CE)
        ArrayList<ArrayList> classes = new ArrayList();

        //list de estados finais
        ArrayList<Estado> f = new ArrayList();

        //list de estados nao-finais
        ArrayList<Estado> nf = new ArrayList();

        //separa os estados finais dos nao finais, adicionandos nas respectivas lists
        for (Estado e : this.estados) {
            if (e.isEstadoFinal()) {
                f.add(e);
            } else {
                nf.add(e);
            }
        }

        Estado eOrigem0 = f.get(0);
        Estado eOrigem1 = f.get(1);

        Estado eDestino0 = null;
        Estado eDestino1 = null;

//        Transicao t0=null;
//        Transicao t1=null;

        //list temporario para guardar os estados destino de dois estados de uma CE para
        //auxiliar a comparacao entre os estados destino e verificar se eles pertencem a mesma CE
        ArrayList<Estado> temp = new ArrayList();

        for (int i = 0; i < this.transicoes.size(); i++) {
            Transicao t = this.transicoes.get(i);
            if (t.getEstadoOrigem() == eOrigem0 && t.getSimbolo().equals(this.simbolos.get(0))) {
                temp.add(t.getEstadoDestino());
            }
        }        
    }
    
    //###teste criacao de CE com array bidimensional
    public void criaClassesDeEquivalencia(){
        //cria uma tabela (array bidimensional) para representar o automado em forma de tabela
        int linhas = estados.size() + 1;
        int colunas = simbolos.size() + 1;
        String[][] tabela = this.af2BiDimArray();
        
        //list que armazena lists que representam classes de equivalencia (CE)
        ArrayList<ArrayList> classes = new ArrayList();

        //list de estados finais
        ArrayList<Estado> f = new ArrayList();

        //list de estados nao-finais
        ArrayList<Estado> nf = new ArrayList();

        //separa os estados finais dos nao finais, adicionandos nas respectivas lists
        for (Estado e : this.estados) {
            if (e.isEstadoFinal()) {
                f.add(e);
            } else {
                nf.add(e);
            }
        }

        Estado eOrigem0 = f.get(0);
        Estado eOrigem1 = f.get(1);

        Estado eDestino0 = null;
        Estado eDestino1 = null;
        
        //cria Strings temporarias para comparar se transicoes sao da mesma classe
        String qa=null;
        String qb=null;
        for(int i=0; i<this.estados.size(); i++)
            //comeca comparando elementos da CE dos estados finais se houver mais de 1 elemento
            if(f.size()>1){
                
                if(tabela[i+1][0].equals(f.get(0).getId())){
                    qa=tabela[i+1][1];
                }
                
            }
            //se a CE dos estados finais só contem 1 elemento, compara os elementos da CE dos nao finais
            //apenas se houver mais de 1 elemento
            else{
                
            }
        
        
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
     * Cria e retorna um array bidimensional do automato
     *
     * @return um array bidimensional de Strings que representa o automato
     */
    public String[][] af2BiDimArray() {
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
                    if (estados.get(linha - 1).isEstadoFinal()) {
                        //se o estado é final, acrescenta '*' a sua representacao
                        tabela[linha][coluna] = estFinal.concat(tabela[linha][coluna]);
                    }
                    if (estados.get(linha - 1).isEstadoInicial()) {
                        //se o estado é inicial, acrescenta '->' a sua representacao
                        tabela[linha][coluna] = estInicial.concat(tabela[linha][coluna]);
                    }
                }
            }
        }

        //define os estados na tabela
        for (int linha = 1; linha < linhas; linha++) {
            for (int coluna = 1; coluna < colunas; coluna++) {
                for (Transicao t : transicoes) {
                    if ((tabela[linha][0]).contains(t.getEstadoOrigem().getId())) {
                        if (t.getSimbolo().equals(tabela[0][coluna])) {
                            if (tabela[linha][coluna] == null) {
                                tabela[linha][coluna] = t.getEstadoDestino().getId();
                            } else {
                                tabela[linha][coluna] = tabela[linha][coluna].concat(t.getEstadoDestino().getId());
                            }
                        }
                    }
                }
            }
        }
        return tabela;
    }

    /**
     * Imprime uma representacao do automato finito em forma de String
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
        String[][] tabela = this.af2BiDimArray();

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
