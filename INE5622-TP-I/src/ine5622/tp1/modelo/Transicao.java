package ine5622.tp1.modelo;

/**
 *
 * @author Danilo
 */
public class Transicao {
    
    private Estado estadoOrigem; //estado de origem que deriva o simbolo
    private Estado estadoDestino; //estados de destino que deriva o simbolo
    private String simbolo; //simbolo gerado/reconhecido pela transicao
    
    public Transicao (String simbolo){
        this.simbolo=simbolo;        
    }

    public Estado getEstadoDestino() {
        return estadoDestino;
    }

    public void setEstadoDestino(Estado estadoDestino) {
        this.estadoDestino = estadoDestino;
    }

    public Estado getEstadoOrigem() {
        return estadoOrigem;
    }

    public void setEstadoOrigem(Estado estadoOrigem) {
        this.estadoOrigem = estadoOrigem;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }
    
    
}
