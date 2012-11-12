package ine5622.tp1.teste;

import ine5622.tp1.modelo.AutomatoFinito;
import ine5622.tp1.modelo.Estado;
import ine5622.tp1.modelo.Transicao;
import ine5622.tp1.modelo.util.AFUtil;
import ine5622.tp1.modelo.util.GRUtil;
import java.util.ArrayList;

/**
 * Classe de teste da aplicacao. Por ser uma classe de teste, não segue nenhuma
 * regra. Eu instancio o que eu quiser aqui!
 */
public class Teste {

    public static void main(String[] args) {
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

        //define os estados alcancaveis a partir do estado q
        ArrayList<Transicao> tq0 = new ArrayList();
        tq0.add(t1);
        tq0.add(t2);
        tq0.add(t3);
        q0.setEstadosAlcancaveis(tq0);        
        
        ArrayList<Transicao> tq1 = new ArrayList();
        tq1.add(t4);
        q1.setEstadosAlcancaveis(tq1);        
        
        ArrayList<Transicao> tq2 = new ArrayList();
        tq2.add(t5);
        q2.setEstadosAlcancaveis(tq2);
        //System.out.println(tq2.toString());
        
        //q3.setEstadosAlcancaveis(transicoes);

        //define os simbolos gerados/reconhecidos a partir do estado q
        q0.setSimbolos(tq0);
        q1.setSimbolos(tq1);
        q2.setSimbolos(tq2);        
        //q3.setSimbolos(transicoes);

        //Cria objetos necessarios para criar o AF
        ArrayList<Estado> estados = new ArrayList(); //estados do automato
        estados.add(q0);
        estados.add(q1);
        estados.add(q2);
        estados.add(q3);
        ArrayList<Transicao> transicoes = new ArrayList(); //transicoes do automato
        transicoes.add(t1);
        transicoes.add(t2);
        transicoes.add(t3);
        transicoes.add(t4);
        transicoes.add(t5);    
        
        //Cria o AFND
        //AutomatoFinito(ArrayList<Estado> estados, ArrayList<Transicao> transicoes)
        AutomatoFinito afnd = new AutomatoFinito(estados, transicoes);

        //Imprime o automato de forma tabular
        afnd.toString();

        //Instancia um objeto da classe que contem metodos de manipulacao de AF
        AFUtil afutil = new AFUtil();

        GRUtil grutil = new GRUtil();

        //Converte o AFND em AF
        afutil.determinizarAF(afnd);//IMPLEMENTAR!

        //Minimiza o AF
        afutil.minimizarAF(afnd);//IMPLEMENTAR!

        //Converte o AF em GR
        afutil.converteAF2GR(afnd);//IMPLEMENTAR!

        for (Transicao t : transicoes) {
            System.out.println(t.toString());
        }
    }
}
