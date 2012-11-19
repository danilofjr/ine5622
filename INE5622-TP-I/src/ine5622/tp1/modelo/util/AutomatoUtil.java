package ine5622.tp1.modelo.util;

import ine5622.tp1.modelo.AutomatoFinito;
import ine5622.tp1.modelo.GramaticaRegular;
import ine5622.tp1.modelo.Producao;
import java.util.ArrayList;

/**
 *
 * Classe com metodos de manipulacao de AF
 */
public class AutomatoUtil {

    /**
     * Le o automato finito passado como parametro
     *
     * @param af eh um automato finito
     * @return [verificar necessidade de retorno]
     */
    public void lerAF(AutomatoFinito af) {
    }

    /**
     * Grava o automato finito passado como parametro
     *
     * @param af eh um automato finito
     * @return [verificar necessidade de retorno]
     */
    public void gravarAF(AutomatoFinito af) {
    }

    /**
     * Converte o automato finito nao-deterministico (AFND) passado como
     * parametro em um automato finito deterministico (AFD)
     *
     * @param af eh um automato finito
     * @return [verificar necessidade de retorno]
     */
    public void determinizarAF(AutomatoFinito af) {
        //
        
        
    }

    /**
     * Minimiza o automato finito passado como parametro
     *
     * @param af eh um automato finito
     * @return [verificar necessidade de retorno]
     */
    public void minimizarAF(AutomatoFinito af) {
    }

    /**
     * Converte o automato finito passado como parametro em sua representacao na
     * forma de gramatica
     *
     * @param af eh um automato finito
     * @return uma gramatica regular
     */
    public GramaticaRegular converteAF2GR(AutomatoFinito af) {
        //list que contem as producoes de uma gramatica
        ArrayList<Producao>  producoes = new ArrayList();
        
        //array bidimensional que representa o automato
        String[][] automato = af.af2BiDimArray();
        for(int linha=1; linha<automato.length; linha++){
            for(int coluna=0; coluna<automato.length; coluna++){ 
                //variavel que indica se o simbolo atual eh inicial
                boolean simboloInicial=false;
                String simboloProducao = automato[linha][coluna];
                if(simboloProducao.contains("->")){
                    simboloInicial=true; //indica que eh o simbolo inicial
                    simboloProducao.substring(2); //exclui '->' da string
                }
                
                ArrayList<String> p=new ArrayList();
                for(int i=1; i<automato.length; i++){
                    p.add(automato[linha][i]);
                }
                //IMCOMPLETO.... TERMINAR
                
                
            }
        }
        
        
        
        GramaticaRegular gr = new GramaticaRegular();
        return gr;
    }
}
