package ine5622.tp1.modelo;

import java.util.ArrayList;

/**
 *
 * Classe que define um automato finito
 */
public class AutomatoFinito {
    
    private ArrayList<Estado> estados; //estados do automato
    private ArrayList<Transicao> transicoes; //transicoes do automato
//    private ArrayList simbolos; //simbolos gerados/reconhecidos pelo automato
        
    public AutomatoFinito(ArrayList<Estado> estados, ArrayList<Transicao> transicoes){
        this.estados = new ArrayList();        
        this.transicoes = new ArrayList();
        this.estados = estados;
        this.transicoes = transicoes;
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
     * Retorna uma representacao do automato finito em forma de String
     * @return uma representacao do automato finito
     */
    @Override
    public String toString(){
        System.out.println
        return null;
    }    
}
