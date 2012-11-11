package ine5622.tp1.modelo;

/**
 *
 * Classe que define um automato finito
 */
public class AutomatoFinito {
    
    private Estado[] estados; //estados do automato
    private String[] simbolos; //simbolos gerados/reconhecidos pelo automato
    private Transicao[] transicoes; //transicoes do automato
    
    public AutomatoFinito(){
        
    }
    
    /**
     * Adiciona um novo estado ao automato
     * @param e 
     * @return [verificar necessidade de retorno]
     */
    public void adicionaEstado(Estado e){
        
    }
    
    /**
     * Remove um estado existente do automato
     * @param e 
     * @return [verificar necessidade de retorno]
     */
    public void removeEstado(Estado e){
        
    }
    
    /**
     * Retorna uma representacao do automato finito em forma de String
     * @return uma representacao do automato finito
     */
    @Override
    public String toString(){
        return null;
    }
    
}
