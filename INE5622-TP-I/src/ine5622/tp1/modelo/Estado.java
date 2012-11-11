package ine5622.tp1.modelo;

/**
 *
 * Classe que define o estado de um automato e suas propriedades
 */
public class Estado {
    
    private String id; //identificador do estado    
    private boolean estadoInicial; //identifica se o estado eh inicial
    private boolean estadoFinal; //identifica se o estado eh final
    private String[] simbolos; //simbolos gerados a partir do estado ??necessario?? ja que tem transicoes[]?
    private Estado[] estadosAlcancaveis; //estados alcancaveis a partir deste ??necessario?? ja que tem transicoes[]?
    private Transicao[] transicoes; //transicoes de um estado
    //private boolean estadoEpsilon; //necessario?

    public Estado(String id, boolean estadoInicial, boolean estadoFinal, String[] simbolos, String[] estadosAlcancaveis) {
        this.id = id;
        this.estadoInicial = estadoInicial;
        this.estadoFinal = estadoFinal;
        this.simbolos = simbolos;
        this.estadosAlcancaveis = new Estado[estadosAlcancaveis.length];
        for(Estado e : estadosAlcancaveis){
           this.estadosAlcancaveis[] 
        }
        
        this.estadosAlcancaveis = estadosAlcancaveis;
    }
            
    
    
}
