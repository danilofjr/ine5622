
package ine5622.tp1.teste;

import ine5622.tp1.modelo.AutomatoFinito;
import ine5622.tp1.modelo.Estado;
import ine5622.tp1.modelo.Transicao;
import java.util.ArrayList;

/**
 * Classe de teste da aplicacao
 * @author Danilo
 */
public class Teste {
    
    public static void main (String[] args){
        //Cria os estados do automato de teste
        //Estado (String id, boolean estadoInicial, boolean estadoFinal)
        Estado q0 = new Estado("q0", true, false);
        Estado q1 = new Estado("q1", false, false);
        Estado q2 = new Estado("q2", false, false);
        Estado q3 = new Estado("q3", false, true);
        
        //Cria as transicoes do automato de teste
        //Transicao (Estado estadoOrigem, Estado estadoDestino, String simbolo)
        Transicao t1 = new Transicao(q0, q0, "a");
        Transicao t2 = new Transicao(q0, q0, "b");
        Transicao t3 = new Transicao(q0, q1, "a");
        Transicao t4 = new Transicao(q1, q2, "b");
        Transicao t5 = new Transicao(q2, q3, "b");
        
        //Cria objetos necessarios para criar o AF
        ArrayList<Estado> estados = new ArrayList(); //estados do automato
        ArrayList<Transicao> transicoes = new ArrayList(); //transicoes do automato
        
        //Cria o AFND
        //AutomatoFinito(ArrayList<Estado> estados, ArrayList<Transicao> transicoes)
        AutomatoFinito afnd = new AutomatoFinito(estados, transicoes);
        
        System.out.println(afnd.toString());
        
        //determinizar o AFND
        
    }
    
}
