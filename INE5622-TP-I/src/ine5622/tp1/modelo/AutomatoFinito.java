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
        
    public AutomatoFinito(ArrayList<Estado> estados, ArrayList<Transicao> transicoes){
        this.estados = new ArrayList();        
        this.transicoes = new ArrayList();
        this.estados = estados;
        this.transicoes = transicoes;
        this.simbolos = listaSimbolos();
    }
    
    /**
     *  Retorna um array de estados do automato     
     * @return os estados do automato
     */
    public ArrayList<Estado> getEstados() {
        return estados;
    }

    /**
     *  Define um array de estados do automato     
     * @param estados eh um array de estados do automato
     */
    public void setEstados(ArrayList<Estado> estados) {
        this.estados = estados;
    }

    /**
     *  Retorna um array de transicoes do automato     
     * @return as transicoes do automato
     */
    public ArrayList<Transicao> getTransicoes() {
        return transicoes;
    }

    /**
     *  Define um array de transicoes do automato     
     * @param transicoes eh um array de transicoes do automato
     */
    public void setTransicoes(ArrayList<Transicao> transicoes) {
        this.transicoes = transicoes;
    }        
    
    /**
     * Adiciona um novo estado ao automato
     * @param e eh um estado
     * @return true se a operacao foi executada com sucesso
     */
    public boolean adicionaEstado(Estado e){
        if(this.estados.add(e))
            return true;
        return false;
    }
    
    /**
     * Remove um estado existente do automato
     * @param e eh um estado
     * @return true se a operacao foi executada com sucesso
     */
    public boolean removeEstado(Estado e){
        if(this.estados.remove(e))
            return true;
        return false;
    }
    
    /**
     * Verifica se existe um determinado estado no automato
     * @param e eh um estado
     * @return true se o estado @param e existe no automato
     */
    public boolean existeEstado(Estado e){
        boolean existe=false;
        for(Estado est : this.estados){
            if (est == e)
                existe=true;
        }
        return existe;
    }
    
    /**
     * Adiciona uma nova transicao ao automato
     * @param t eh uma transicao
     * @return true se a operacao foi executada com sucesso
     */
    public boolean adicionaTransicao(Transicao t){
        if(this.transicoes.add(t))
            return true;
        return false;
    }
    
    /**
     * Remove uma transicao existente do automato
     * @param t eh uma transicao
     * @return true se a operacao foi executada com sucesso
     */
    public boolean removeTransicao(Transicao t){
        if(this.transicoes.remove(t))
            return true;
        return false;
    }
    
    /**
     * Verifica e lista quais simbolos sao gerados/reconhecidos pelo automato
     * @return uma lista de simbolos gerados/reconhecidos pelo automato
     */
    private ArrayList<String> listaSimbolos(){
        ArrayList<String> s = new ArrayList();
        for(Transicao trans : this.transicoes){
            String simbolo = trans.getSimbolo();
            if(!s.contains(simbolo))
                s.add(simbolo);
        }                              
        return s;
    }
    
    /**
     * Retorna uma representacao do automato finito em forma de String
     * @return uma representacao do automato finito
     */
    //@Override
    public String toString(AutomatoFinito af){
        int nc = 1 + af.simbolos.size();//numero de colunas da tabela
        String tabulacao = "";
        String titulos = "";
        for(int i=0; i<af.simbolos.size(); i++){
            if(i==0){
                tabulacao.concat("%5s");
                titulos.concat("Title*");
            }          
            else{
                tabulacao.concat(" %5s");
                titulos.concat("Title*");
            }          
        }  
        
        Formatter formatter = new Formatter();
        System.out.println(formatter.format("%5s %5s %5s", "q", "a", "b"));
        
        //criar uma tabela (array bidimensional) para representar o automado em forma de tabela
        int linhas = af.estados.size();
        int colunas = af.simbolos.size()+1;
        String[][] tabela = new String[linhas][colunas];
        for(int linha = 0; linha < linhas; linha++){
            for(int coluna = 0; coluna < colunas; coluna++){
                if(linha==0){
                    tabela[linha][coluna]="q";
                }
                
            }
        }
        
        for (int j = 0; j < af.estados.size()-1; j++) {
            formatter = new Formatter();
            String rowLabel = af.estados.get(j).getId();//"info" + j;    
            //af.transicoes.
            //String rowData1 = ;
            //String rowData2 = ;
            System.out.println(formatter.format("%5s %5s %5s", rowLabel, rowData, rowData));
        
//        Formatter fmt = new Formatter();        
//        System.out.println(fmt.format(tabulacao, "Title*", "Title*"));
//        for(int j=0; j<this.estados.size(); j++){
//            fmt = new Formatter();
//            String rowData = "info" + j;
//            System.out.println(fmt.format(tabulacao, rowData, rowData));
        }                        
        return null;
    }    
}
