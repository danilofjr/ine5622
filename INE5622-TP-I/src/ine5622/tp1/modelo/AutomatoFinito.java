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
        this.estadosAlcancaveis = new ArrayList();
        this.estadosVivos = new ArrayList();
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
        //ArrayList<Estado> estadosAlcancaveisTemp = new ArrayList();
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

        System.out.println("---");
        for (Estado e : this.estadosAlcancaveis) {
            System.out.println(e.toString());
        }
        System.out.println("---até aqui esta correto---");

        //passa 3 vezes para garantir que encontrou todos os estados alcancaveis
        int cont = 0;
        while (cont < 2) {
            //faz uma cópia de estadosAlcancaveis        
            //o codigo ArrayList<Estado> estadosAlcancaveisTemp=this.estadosAlcancaveis nao copia o array estadosAlcancaveis
            //para o array estadosAlcancaveisTemp, e sim vincula o primeiro ao segundo. Resulta em erro por acesso concorrente.
            ArrayList<Estado> estadosAlcancaveisTemp = new ArrayList();
            for (Estado e : this.estadosAlcancaveis) {
                estadosAlcancaveisTemp.add(e);
            }
            //procura novos estados alcancaveis a partir do list de estados alcancaveis
            for (Estado e : this.estadosAlcancaveis) {
                for (Transicao t : this.transicoes) {
                    if (t.getEstadoOrigem() == e) {
                        if (!this.estadosAlcancaveis.contains(t.getEstadoDestino())) {
                            estadosAlcancaveisTemp.add(t.getEstadoDestino());
                        }
                    }
                }
            }
            this.estadosAlcancaveis = estadosAlcancaveisTemp;
            for (Estado e : this.estadosAlcancaveis) {
                System.out.println(e.toString());
            }
            cont++;
        }
//        for(Estado e : this.estadosAlcancaveis){
//            System.out.println(e.toString());
//        }

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
        //faz uma cópia de estadosVivos        
        //o codigo ArrayList<Estado> estadosVivosTemp=this.estadosVivos nao copia o array estadosVivos
        //para o array estadosVivosTemp, e sim vincula o primeiro ao segundo. Resulta em erro por acesso concorrente.
        ArrayList<Estado> estadosVivosTemp=new ArrayList();
        for (Estado e : this.estadosAlcancaveis){
            estadosVivosTemp.add(e);
        }
        //procura novos estados vivos a partir do list de estados vivos
        for (Estado e : this.estadosVivos) {
            for (Transicao t : this.transicoes) {
                if (t.getEstadoDestino() == e) {
                    if (!this.estadosVivos.contains(t.getEstadoOrigem())) {
                        estadosVivosTemp.add(t.getEstadoOrigem());
                    }
                }
            }
        }
        this.estadosVivos=estadosVivosTemp;
//        for(Estado e : this.estadosVivos){
//            System.out.println(e.toString());
//        }
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
        
        //faz uma cópia de transicoes                
        ArrayList<Transicao> transicoesTemp=new ArrayList();
        for (Transicao t : this.transicoes){
            transicoesTemp.add(t);
        }
        //elimina as transicoes de estados inalcancaveis
        for (Estado e : this.estados) {
            for (Transicao t : this.transicoes) {
                if (!this.estados.contains(t.getEstadoOrigem())) {
                    transicoesTemp.remove(t);
                }
            }
        }
        this.transicoes=transicoesTemp;        
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
        
        //faz uma cópia de transicoes                
        ArrayList<Transicao> transicoesTemp=new ArrayList();
        for (Transicao t : this.transicoes){
            transicoesTemp.add(t);
        }
        //elimina as transicoes de estados vivos
        for (Estado e : this.estados) {
            for (Transicao t : this.transicoes) {
                if (!this.estados.contains(t.getEstadoOrigem())) {
                    transicoesTemp.remove(t);
                }
            }
        }
        this.transicoes=transicoesTemp;  
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

        //variaveis para controlar a iteracao nos arrays this.estados, this.transicoes, this.simbolos e um dos arrays de CE
        int contEstados = 0;
        int contTransicoes = 0;
        int contSimbolos = 0;
        int contK = 0;

        //transicoes temporarias para comparacao
        Transicao ta = null;
        Transicao tb = null;

        //armazena temporariamente transicoes de dois estados para comparacao       
        ArrayList<Transicao> trans = new ArrayList();


        if (f.size() > 1) {
            //iteracao para comparacao com estado de origem das transicoes
            for (int i = 0; i < this.transicoes.size(); i++) {
                Transicao t = transicoes.get(i);
                if (f.get(contK) == t.getEstadoOrigem() && t.getSimbolo().equals(this.simbolos.get(contSimbolos))) {
                    trans.add(t);
                    contK++;
                }
            }

            for (int i = 0; i < this.transicoes.size(); i++) {
                Transicao t = transicoes.get(i);
                if (f.get(contK) == t.getEstadoOrigem() && t.getSimbolo().equals(this.simbolos.get(contSimbolos))) {
                    trans.add(t);
                    contK++;
                }
            }

        } else if (nf.size() > 1) {
        } else {
        }

        //if(ta.)



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
