package ine5622.tp1.teste;

import ine5622.tp1.modelo.AutomatoFinito;
import ine5622.tp1.modelo.Estado;
import ine5622.tp1.modelo.Transicao;
import ine5622.tp1.modelo.util.AutomatoUtil;
import ine5622.tp1.modelo.util.GramaticaUtil;
import java.util.ArrayList;

/**
 * Classe de teste da aplicacao. Por ser uma classe de teste, não segue nenhuma regra. Eu instancio o que eu
 * quiser aqui!
 */
public class Teste {

    /**
     * Cria um automato finito nao deterministico para teste e executa testes de determinizacao sobre o mesmo
     */
    public static void criaAFND() {
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
//        q0.setEstadosAlcancaveis(tq0);        

        ArrayList<Transicao> tq1 = new ArrayList();
        tq1.add(t4);
//        q1.setEstadosAlcancaveis(tq1);        

        ArrayList<Transicao> tq2 = new ArrayList();
        tq2.add(t5);
//        q2.setEstadosAlcancaveis(tq2);  

        //q3 não tem estados alcancaveis
        //q3.setEstadosAlcancaveis(transicoes);

        //define os simbolos gerados/reconhecidos a partir do estado q
//        q0.setSimbolos(tq0);
//        q1.setSimbolos(tq1);
//        q2.setSimbolos(tq2);   

        //q3 não tem transicoes
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
        //Imprime as transicoes do automato
        for (Transicao t : transicoes) {
            System.out.println(t.toString());
        }

        //Instancia um objeto da classe que contem metodos de manipulacao de AF
        AutomatoUtil afutil = new AutomatoUtil();

        //Instancia um objeto da classe que contem metodos de manipulacao de GR
        GramaticaUtil grutil = new GramaticaUtil();

        //Converte o AFND em AF
        afutil.determinizarAF(afnd);//IMPLEMENTAR!

        //Minimiza o AF
        afutil.minimizarAF(afnd);//IMPLEMENTAR!

        //Converte o AF em GR
        afutil.converteAF2GR(afnd);//IMPLEMENTAR!
    }
    
    /**
     * Cria um automato finito deterministico para teste e executa testes de minimizacao sobre o mesmo
     */
    public static void criaAF(){
        Estado q0 = new Estado("A", true, true);
        Estado q1 = new Estado("B", false, false);
        Estado q2 = new Estado("C", false, false);
        Estado q3 = new Estado("D", false, true);
        Estado q4 = new Estado("E", false, false);
        Estado q5 = new Estado("F", false, false);
        Estado q6 = new Estado("G", false, true);
        Estado q7 = new Estado("H", false, false);
               
        Transicao t0 = new Transicao(q0, q6, "a"); 
        Transicao t1 = new Transicao(q0, q1, "b");
        Transicao t2 = new Transicao(q1, q5, "a");
        Transicao t3 = new Transicao(q1, q4, "b");
        Transicao t4 = new Transicao(q2, q2, "a");
        Transicao t5 = new Transicao(q2, q6, "b");
        Transicao t6 = new Transicao(q3, q0, "a");
        Transicao t7 = new Transicao(q3, q7, "b");
        Transicao t8 = new Transicao(q4, q4, "a"); 
        Transicao t9 = new Transicao(q4, q0, "b");
        Transicao t10 = new Transicao(q5, q1, "a");
        Transicao t11 = new Transicao(q5, q2, "b");
        Transicao t12 = new Transicao(q6, q6, "a");
        Transicao t13 = new Transicao(q6, q5, "b");
        Transicao t14 = new Transicao(q7, q7, "a");
        Transicao t15 = new Transicao(q7, q3, "b");
        
        //define os estados alcancaveis a partir do estado q
        ArrayList<Transicao> tq0 = new ArrayList();
        tq0.add(t0);
        tq0.add(t1);        
        ArrayList<Transicao> tq1 = new ArrayList();
        tq1.add(t2);
        tq1.add(t3);        
        ArrayList<Transicao> tq2 = new ArrayList();
        tq2.add(t4);
        tq2.add(t5);        
        ArrayList<Transicao> tq3 = new ArrayList();
        tq3.add(t6);
        tq3.add(t7);        
        ArrayList<Transicao> tq4 = new ArrayList();
        tq4.add(t8);
        tq4.add(t9);        
        ArrayList<Transicao> tq5 = new ArrayList();
        tq5.add(t10);
        tq5.add(t11);        
        ArrayList<Transicao> tq6 = new ArrayList();
        tq6.add(t12);
        tq6.add(t13);        
        ArrayList<Transicao> tq7 = new ArrayList();
        tq7.add(t14);
        tq7.add(t15);        
        
        //Cria objetos necessarios para criar o AF
        ArrayList<Estado> estados = new ArrayList(); //estados do automato
        estados.add(q0);
        estados.add(q1);
        estados.add(q2);
        estados.add(q3);
        estados.add(q4);
        estados.add(q5);
        estados.add(q6);
        estados.add(q7);
        ArrayList<Transicao> transicoes = new ArrayList(); //transicoes do automato
        transicoes.add(t0);
        transicoes.add(t1);
        transicoes.add(t2);
        transicoes.add(t3);
        transicoes.add(t4);
        transicoes.add(t5);
        transicoes.add(t6);
        transicoes.add(t7);
        transicoes.add(t8);
        transicoes.add(t9);
        transicoes.add(t10);
        transicoes.add(t11);
        transicoes.add(t12);
        transicoes.add(t13);
        transicoes.add(t14);
        transicoes.add(t15);
        
        //Cria o AFND
        //AutomatoFinito(ArrayList<Estado> estados, ArrayList<Transicao> transicoes)
        AutomatoFinito afnd = new AutomatoFinito(estados, transicoes);

        //Imprime o automato de forma tabular
        afnd.toString();
        //Imprime as transicoes do automato
        for (Transicao t : transicoes) {
            System.out.println(t.toString());
        }
        
        afnd.verificaEstadosAlcancaveis();
        afnd.verificaEstadosVivos();
        afnd.eliminaEstadosInalcancaveis();
        afnd.eliminaEstadosMortos();
        
        afnd.toString();
        
        
    }

    public static void main(String[] args) {
        
        //testa determinizacao
        //criaAFND();
        
        //testa minimizacao
        criaAF();

    }
}
