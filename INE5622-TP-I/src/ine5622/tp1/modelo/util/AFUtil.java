package ine5622.tp1.modelo.util;

import ine5622.tp1.modelo.AutomatoFinito;
import ine5622.tp1.modelo.GramaticaRegular;

/**
 *
 * Classe com metodos de manipulacao de AF
 */
public class AFUtil {

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
        GramaticaRegular gr = new GramaticaRegular();
        return gr;
    }
}
