package ine5622.tp1.modelo.util;

import ine5622.tp1.modelo.AutomatoFinito;
import ine5622.tp1.modelo.Estado;
import ine5622.tp1.modelo.GramaticaRegular;
import ine5622.tp1.modelo.Transicao;
import java.util.ArrayList;

/**
 *
 * Classe com metodos de manipulacao de GR
 */
public class GRUtil {

    /**
     * Le a gramatica regular passada como parametro
     *
     * @param gr eh uma gramatica regular
     * @return [verificar necessidade de retorno]
     */
    public void lerGR(GramaticaRegular gr) {
    }

    /**
     * Grava a gramatica regular passada como parametro
     *
     * @param gr eh uma gramatica regular
     * @return [verificar necessidade de retorno]
     */
    public void gravarGR(GramaticaRegular gr) {
    }

    /**
     * Converte a gramatica passada como parametro em sua representacao na forma
     * de automato finito
     *
     * @param gr eh uma gramatica regular
     * @return retorna um AF
     */
    public AutomatoFinito converteGR2AF(GramaticaRegular gr) {
        ArrayList<Estado> estados = null; //estados do automato INICIALIZAR
        ArrayList<Transicao> transicoes = null; //transicoes do automato INICIALIZAR
        ArrayList<String> simbolos; //lista auxiliar ->
        //uma lista de simbolos e gerada automaticamente a partir de transicoes ao criar o AF
        AutomatoFinito af = new AutomatoFinito(estados, transicoes);
        return af;
    }
}
