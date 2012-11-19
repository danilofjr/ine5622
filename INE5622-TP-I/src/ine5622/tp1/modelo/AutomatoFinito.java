package ine5622.tp1.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Formatter;

/**
 *
 * Classe que define um automato finito
 */
public class AutomatoFinito implements Serializable {

    private ArrayList<Estado> estados; //estados do automato
    private ArrayList<Transicao> transicoes; //transicoes do automato
    private ArrayList<String> simbolos; //simbolos gerados/reconhecidos pelo automato
    private ArrayList<Estado> estadosAlcancaveis; //lista de estados alcancaveis do automato
    private ArrayList<Estado> estadosVivos; //lista de estados vivos do automato

    public AutomatoFinito(ArrayList<Estado> estados, ArrayList<Transicao> transicoes) {
        this.estados = new ArrayList();
        this.transicoes = new ArrayList();
        this.estados = estados;
        this.transicoes = transicoes;
        this.simbolos = listaSimbolos();
        this.estadosAlcancaveis = new ArrayList();
        this.estadosVivos = new ArrayList();
    }

    /**
     * Retorna um array de estados do automato
     *
     * @return os estados do automato
     */
    public ArrayList<Estado> getEstados() {
        return estados;
    }

    /**
     * Define um array de estados do automato
     *
     * @param estados eh um array de estados do automato
     */
    public void setEstados(ArrayList<Estado> estados) {
        this.estados = estados;
    }

    /**
     * Retorna um array de transicoes do automato
     *
     * @return as transicoes do automato
     */
    public ArrayList<Transicao> getTransicoes() {
        return transicoes;
    }

    /**
     * Define um array de transicoes do automato
     *
     * @param transicoes eh um array de transicoes do automato
     */
    public void setTransicoes(ArrayList<Transicao> transicoes) {
        this.transicoes = transicoes;
    }

    /**
     * Adiciona um novo estado ao automato
     *
     * @param e eh um estado
     * @return true se a operacao foi executada com sucesso
     */
    public boolean adicionaEstado(Estado e) {
        if (this.estados.add(e)) {
            return true;
        }
        return false;
    }

    /**
     * Remove um estado existente do automato
     *
     * @param e eh um estado
     * @return true se a operacao foi executada com sucesso
     */
    public boolean removeEstado(Estado e) {
        if (this.estados.remove(e)) {
            return true;
        }
        return false;
    }

    /**
     * Verifica se existe um determinado estado no automato
     *
     * @param e eh um estado
     * @return true se o estado
     * @param e existe no automato
     */
    public boolean existeEstado(Estado e) {
        boolean existe = false;
        for (Estado est : this.estados) {
            if (est == e) {
                existe = true;
            }
        }
        return existe;
    }

    /**
     * Adiciona uma nova transicao ao automato
     *
     * @param t eh uma transicao
     * @return true se a operacao foi executada com sucesso
     */
    public boolean adicionaTransicao(Transicao t) {
        if (this.transicoes.add(t)) {
            return true;
        }
        return false;
    }

    /**
     * Remove uma transicao existente do automato
     *
     * @param t eh uma transicao
     * @return true se a operacao foi executada com sucesso
     */
    public boolean removeTransicao(Transicao t) {
        if (this.transicoes.remove(t)) {
            return true;
        }
        return false;
    }

    /**
     * Verifica quais os estados do automato sao alcancaveis a partir do estado inicial
     *
     * @param
     * @return
     */
    public void verificaEstadosAlcancaveis() {
        //ArrayList<Estado> estadosAlcancaveisTemp = new ArrayList();
        //procura o estado inicial e adiciona os estados alcancaveis a partir dele ao list de estados alcancaveis
        for (Transicao t : this.transicoes) {
            //verifica se o estado de origem 'e' da transicao 't' é inicial
            if (t.getEstadoOrigem().isEstadoInicial()) {
                //adiciona o estado de origem ao list de estados alcancaveis, se nao duplicado (um estado inicial eh sempre alcancavel)
                if (!this.estadosAlcancaveis.contains(t.getEstadoOrigem())) {
                    this.estadosAlcancaveis.add(t.getEstadoOrigem());
                }
                //adiciona o estado de destino ao list de estados alcancaveis, se nao duplicado
                if (!this.estadosAlcancaveis.contains(t.getEstadoDestino())) {
                    this.estadosAlcancaveis.add(t.getEstadoDestino());
                }
            }
        }

//        System.out.println("---");
//        for (Estado e : this.estadosAlcancaveis) {
//            System.out.println(e.toString());
//        }
//        System.out.println("---até aqui esta correto---");

        //passa 3 vezes para garantir que encontrou todos os estados alcancaveis
        int cont = 0;
        while (cont < 2) {
            //faz uma cópia de estadosAlcancaveis        
            //o codigo ArrayList<Estado> estadosAlcancaveisTemp=this.estadosAlcancaveis nao copia o array estadosAlcancaveis
            //para o array estadosAlcancaveisTemp, e sim vincula o primeiro ao segundo. Resulta em erro por acesso concorrente.
            ArrayList<Estado> estadosAlcancaveisTemp = new ArrayList();
            for (Estado e : this.estadosAlcancaveis) {
                estadosAlcancaveisTemp.add(e);
            }
            //procura novos estados alcancaveis a partir do list de estados alcancaveis
            for (Estado e : this.estadosAlcancaveis) {
                for (Transicao t : this.transicoes) {
                    if (t.getEstadoOrigem() == e) {
                        if (!this.estadosAlcancaveis.contains(t.getEstadoDestino())) {
                            estadosAlcancaveisTemp.add(t.getEstadoDestino());
                        }
                    }
                }
            }
            this.estadosAlcancaveis = estadosAlcancaveisTemp;
//            for (Estado e : this.estadosAlcancaveis) {
//                System.out.println(e.toString());
//            }
            cont++;
        }
//        for(Estado e : this.estadosAlcancaveis){
//            System.out.println(e.toString());
//        }

    }

    /**
     * Verifica quais os estados do automato alcancam estados finais
     *
     * @param
     * @return
     */
    public void verificaEstadosVivos() {
        //procura estados finais e os adiciona ao list de estados vivos
        for (Transicao t : this.transicoes) {
            //verifica se o estado de origem 'e' da transicao 't' é final
            if (t.getEstadoOrigem().isEstadoFinal()) {
                //adiciona o estado de origem ao list de estados finais, se nao duplicado (um estado final eh sempre vivo)
                if (!this.estadosVivos.contains(t.getEstadoOrigem())) {
                    this.estadosVivos.add(t.getEstadoOrigem());
                }
            }
        }
        //faz uma cópia de estadosVivos        
        //o codigo ArrayList<Estado> estadosVivosTemp=this.estadosVivos nao copia o array estadosVivos
        //para o array estadosVivosTemp, e sim vincula o primeiro ao segundo. Resulta em erro por acesso concorrente.
        ArrayList<Estado> estadosVivosTemp = new ArrayList();
        for (Estado e : this.estadosAlcancaveis) {
            estadosVivosTemp.add(e);
        }
        //procura novos estados vivos a partir do list de estados vivos
        for (Estado e : this.estadosVivos) {
            for (Transicao t : this.transicoes) {
                if (t.getEstadoDestino() == e) {
                    if (!this.estadosVivos.contains(t.getEstadoOrigem())) {
                        estadosVivosTemp.add(t.getEstadoOrigem());
                    }
                }
            }
        }
        this.estadosVivos = estadosVivosTemp;
//        for(Estado e : this.estadosVivos){
//            System.out.println(e.toString());
//        }
    }

    /**
     * Elimina os estados do automato que nao sao alcancaveis a partir do estado inicial
     *
     * @param
     * @return
     */
    public void eliminaEstadosInalcancaveis() {
        //faz a intersecção entre as lists 'estados' e 'estadosAlcancaveis'
        this.estados.retainAll(this.estadosAlcancaveis);

        //faz uma cópia de transicoes                
        ArrayList<Transicao> transicoesTemp = new ArrayList();
        for (Transicao t : this.transicoes) {
            transicoesTemp.add(t);
        }
        //elimina as transicoes de estados inalcancaveis
        for (Estado e : this.estados) {
            for (Transicao t : this.transicoes) {
                if (!this.estados.contains(t.getEstadoOrigem())) {
                    transicoesTemp.remove(t);
                }
            }
        }
        this.transicoes = transicoesTemp;
    }

    /**
     * Elimina os estados do automato que nao alcancam estados finais
     *
     * @param
     * @return
     */
    public void eliminaEstadosMortos() {
        //faz a intersecção entre as lists 'estados' e 'estadosVivos'
        this.estados.retainAll(this.estadosVivos);

        //faz uma cópia de transicoes                
        ArrayList<Transicao> transicoesTemp = new ArrayList();
        for (Transicao t : this.transicoes) {
            transicoesTemp.add(t);
        }
        //elimina as transicoes de estados vivos
        for (Estado e : this.estados) {
            for (Transicao t : this.transicoes) {
                if (!this.estados.contains(t.getEstadoOrigem())) {
                    transicoesTemp.remove(t);
                }
            }
        }
        this.transicoes = transicoesTemp;
    }

    public ArrayList<ArrayList> classesDeEquivalencia() {
        //list que armazena lists que representam P de equivalencia (CE)
        ArrayList<ArrayList> classes = new ArrayList();
        //list de estados finais
        ArrayList<Estado> finais = new ArrayList();
        //list de estados nao-finais
        ArrayList<Estado> naoFinais = new ArrayList();

//        P.add(finais);
//        P.add(naoFinais);        

        //separa os estados finais dos nao finais, adicionandos nas respectivas lists
        for (Estado e : this.estados) {
            if (e.isEstadoFinal()) {
                finais.add(e);
            } else {
                naoFinais.add(e);
            }
        }

        ArrayList<Estado> temp = new ArrayList();
        //int contSimbolos=0;       
        if (finais.size() > 1) {
            for (int i = 0; i < finais.size(); i++) {
                Estado e0 = finais.get(0);
                Estado e1 = finais.get(i);
                //String s = this.simbolos.get(contSimbolos);                                

                //percorre os simbolos do alfabeto
                for (int j = 0; j < this.simbolos.size(); j++) {
                    //percorre as transicoes do automato
                    for (Transicao t : this.transicoes) {
                        //compara se o estado de origem de uma transicao é igual ao primeiro estado do par de estados para comparacao
                        if (t.getEstadoOrigem() == e0 && t.getSimbolo().equals(this.simbolos.get(j))) {
                            temp.add(t.getEstadoDestino());
                        }
                        if (t.getEstadoOrigem() == e1 && t.getSimbolo().equals(this.simbolos.get(j))) {
                            temp.add(t.getEstadoDestino());
                        }
                    }
                    //se o list de finais nao contem ambos, cria uma nova CE e adiciona ao array de CE
                    if (!finais.containsAll(temp)) {
                        finais.remove(e1);
                        if (!naoFinais.contains(e1)) {
                            boolean contem = false;
                            for (int m = 0; m < classes.size(); m++) {
                                for (int n = 0; n < classes.get(m).size(); n++) {
                                    if (classes.get(m).get(n) == e1) {
                                        contem = true;
                                    }
                                }
                            }
                            if (!contem) {
                                ArrayList<Estado> novaClasse = new ArrayList();
                                novaClasse.add(e1);
                                classes.add(novaClasse);
                            }
                        }
                    }
                    temp.clear();
                }
            }

        }
        if (naoFinais.size() > 0) {
            for (int i = 0; i < naoFinais.size(); i++) {
                Estado e0 = naoFinais.get(0);
                Estado e1 = naoFinais.get(i);
                //String s = this.simbolos.get(contSimbolos);                                

                //percorre os simbolos do alfabeto
                for (int j = 0; j < this.simbolos.size(); j++) {
                    //percorre as transicoes do automato
                    for (Transicao t : this.transicoes) {
                        //compara se o estado de origem de uma transicao é igual ao primeiro estado do par de estados para comparacao
                        if (t.getEstadoOrigem() == e0 && t.getSimbolo().equals(this.simbolos.get(j))) {
                            temp.add(t.getEstadoDestino());
                        }
                        if (t.getEstadoOrigem() == e1 && t.getSimbolo().equals(this.simbolos.get(j))) {
                            temp.add(t.getEstadoDestino());
                        }
                    }
                    //se o list de finais nao contem ambos, cria uma nova CE e adiciona ao array de CE
                    if (!naoFinais.containsAll(temp)) {
                        naoFinais.remove(e1);
                        if (!finais.contains(e1)) {
                            boolean contem = false;
                            for (int m = 0; m < classes.size(); m++) {
                                for (int n = 0; n < classes.get(m).size(); n++) {
                                    if (classes.get(m).get(n) == e1) {
                                        contem = true;
                                    }
                                }
                            }
                            if (!contem) {
                                ArrayList<Estado> novaClasse = new ArrayList();
                                novaClasse.add(e1);
                                classes.add(novaClasse);
                            }
                        }
                    }
                    temp.clear();
                }
            }
        }
        printTEMP(classes);
        return classes;
    }
    
//    public ArrayList<ArrayList> classesDeEquivalencia2() {
//        //list que armazena lists que representam P de equivalencia (CE)
//        ArrayList<ArrayList> P = new ArrayList();
//        //list de estados finais
//        ArrayList<Estado> finais = new ArrayList();
//        //list de estados nao-finais
//        ArrayList<Estado> naoFinais = new ArrayList();
//        //copia da list de estados finais
//        ArrayList<ArrayList> W = new ArrayList();
//
//        //separa os estados finais dos nao finais, adicionandos nas respectivas lists
//        for (Estado e : this.estados) {
//            if (e.isEstadoFinal()) {
//                finais.add(e);
//            } else {
//                naoFinais.add(e);
//            }
//        }
//        P.add(finais);
//        P.add(naoFinais);
//        //faz a copia da list de estados finais        
//        W.add(finais);
//
//        while (!W.isEmpty()) {
//            ArrayList<Estado> A = W.get(0); 
////            for(ArrayList<Estado> array : W){
////                A.add(array.get(0));
////            }
//            Estado e0 = (Estado) W.get(0).get(0);
//            Estado e1 = (Estado) W.get(0).get(1);
//            //A.add(e0);
//            //A.add(e1);            
//            W.remove(A);
//            
//            for (String c : this.simbolos) {
//                ArrayList<Estado> X = new ArrayList();
//                //let X é o conjunto de estados para o qual uma transição sobre c leva a um estado em A
//                for (Transicao t : this.transicoes) {
//                    if (t.getSimbolo().equals(c) && (t.getEstadoDestino() == A.get(0) || t.getEstadoDestino() == A.get(1))) {
//                        X.add(t.getEstadoDestino());
//                    }
//                }
//                //for each conjunto Y in P para o qual X ∩ Y é não-vazio do
//                for (int i = 0; i < P.size(); i++) {
//                    ArrayList<Estado> Y = P.get(i);
//                    ArrayList<Estado> XintersecY = new ArrayList();
//                    ArrayList<Estado> YdifX = new ArrayList();
//                    for (Estado e : X) {
//                        XintersecY.add(e);
//                        YdifX.add(e);
//                    }
//                    if (XintersecY.retainAll(Y)) {
//                        P.set(P.indexOf(Y), XintersecY);
//                        YdifX.removeAll(Y);
//                        P.add(YdifX);
//                        if (W.containsAll(Y)) {                            
//                            W.set(W.indexOf(Y), XintersecY);
//                            W.add(YdifX);
//                        } else {
//                            if (XintersecY.size() < YdifX.size()) {
//                                W.add(XintersecY);
//                            } else {
//                                W.add(YdifX);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return P;
//    }
    
    public ArrayList<ArrayList> classesDeEquivalencia3() {
        //list que armazena lists que representam P de equivalencia (CE)
        ArrayList<ArrayList> P = new ArrayList();
        //list de estados finais
        ArrayList<Estado> finais = new ArrayList();
        //list de estados nao-finais
        ArrayList<Estado> naoFinais = new ArrayList();
        //copia da list de estados finais
        ArrayList<ArrayList> W = new ArrayList();

        //separa os estados finais dos nao finais, adicionandos nas respectivas lists
        for (Estado e : this.estados) {
            if (e.isEstadoFinal()) {
                finais.add(e);
            } else {
                naoFinais.add(e);
            }
        }
        P.add(finais);
        P.add(naoFinais);
        //faz a copia da list de estados finais        
        W.add(finais);

        while (!W.isEmpty()) {
            ArrayList<Estado> A = W.get(0);             
            W.remove(A);
            
            for (String c : this.simbolos) {
                ArrayList<Estado> X = new ArrayList();
                //let X é o conjunto de estados para o qual uma transição sobre c leva a um estado em A
                for (Transicao t : this.transicoes) {
                    if (t.getSimbolo().equals(c) && (t.getEstadoDestino() == A.get(0) || t.getEstadoDestino() == A.get(1))) {
                        X.add(t.getEstadoDestino());
                    }
                }
                //for each conjunto Y in P para o qual X ∩ Y é não-vazio do
                for (int i = 0; i < P.size(); i++) {
                    ArrayList<Estado> Y = P.get(i);
                    ArrayList<Estado> XintersecY = new ArrayList();
                    ArrayList<Estado> YdifX = new ArrayList();
                    for (Estado e : X) {
                        XintersecY.add(e);
                        YdifX.add(e);
                    }
                    if (XintersecY.retainAll(Y)) {
                        P.set(P.indexOf(Y), XintersecY);
                        YdifX.removeAll(Y);
                        P.add(YdifX);
                        if (W.containsAll(Y)) {                            
                            W.set(W.indexOf(Y), XintersecY);
                            W.add(YdifX);
                        } else {
                            if (XintersecY.size() <= YdifX.size()) {
                                W.add(XintersecY);
                            } else {
                                W.add(YdifX);
                            }
                        }
                    }
                }
            }
        }
        return P;
    }

//    //############################
//    public void minimiza() {
//        ArrayList classesEquivalencia = new ArrayList();
//
//        eliminaEstadosMortos();
//        eliminaEstadosInalcancaveis();
//
////        int retAfd = transformaEmAFD(null, 0).nrEstadosAlterados;
////        if (retAfd > 0) {
////            eliminaEstadosMortos();
////            eliminaEstadosInalcancaveis();
////        }
//
//        //ArrayList transicoes = this.automatoAtual.getMatrizTransicoes().getTransicoes();
//        //ArrayList estados = this.automatoAtual.getEstados();
//
//        for (int i = 0; i < this.estados.size(); i++) {
//            String estadoAtual = this.estados.get(i).getId();
//            ClasseEstado classeAtual;
//            if (estados.get(i).isEstadoFinal()) {
//                classeAtual = new ClasseEstado(estadoAtual, "r", "r");
//            } else {
//                classeAtual = new ClasseEstado(estadoAtual, "n", "n");
//            }
//            classesEquivalencia.add(classeAtual);
//        }
//
//        boolean modificado = true;
//        while (modificado) {
//            modificado = false;
//
//            for (int i = 0; i < classesEquivalencia.size(); i++) {
//                ClasseEstado classeEstadoAtual = (ClasseEstado) classesEquivalencia.get(i);
//                classeEstadoAtual.verificada = false;
//                classesEquivalencia.set(i, classeEstadoAtual);
//            }
//
//            for (int i = 0; i < classesEquivalencia.size(); i++) {
//                ClasseEstado primClasseEstado = (ClasseEstado) classesEquivalencia.get(i);
//
//                if (primClasseEstado.verificada) {
//                    continue;
//                }
//                ArrayList transicoesPrimEst = retornaTransicoesEstado(transicoes, primClasseEstado.nomeEstado, null);
//
//                String classeAntEstadoAtual = primClasseEstado.nomeClasseAtual;
//                primClasseEstado.nomeClasseAnterior = primClasseEstado.nomeClasseAtual;
//                primClasseEstado.nomeClasseAtual = primClasseEstado.nomeEstado;
//                primClasseEstado.verificada = true;
//                classesEquivalencia.set(i, primClasseEstado);
//
//                for (int j = i + 1; j < classesEquivalencia.size(); j++) {
//                    ClasseEstado segClasseEstado = (ClasseEstado) classesEquivalencia.get(j);
//
//                    if (segClasseEstado.verificada) {
//                        continue;
//                    }
//                    ArrayList transicoesSegEst = retornaTransicoesEstado(transicoes, segClasseEstado.nomeEstado, null);
//
//                    if (!primClasseEstado.nomeClasseAnterior.equals(segClasseEstado.nomeClasseAtual)) {
//                        continue;
//                    }
//
//                    if (transicoesSegEst.size() == transicoesPrimEst.size()) {
//                        boolean classesIguais = true;
//
//                        for (int t1 = 0; t1 < transicoesPrimEst.size(); t1++) {
//                            Transicao tr1 = (Transicao) transicoesPrimEst.get(t1);
//                            Transicao tr2 = null;
//                            boolean transicao_encontrada = false;
//                            int indT2 = 0;
//                            while ((!transicao_encontrada) && (indT2 < transicoesSegEst.size())) {
//                                tr2 = (Transicao) transicoesSegEst.get(indT2);
//                                if (tr2.getEstadoOrigem().equals(tr1.getSimbolo())) {
//                                    transicao_encontrada = true;
//                                } else {
//                                    indT2++;
//                                }
//                            }
//
//                            if (transicao_encontrada) {
//                                String classeEq1;
//                                if (isAlocadoEmClasse(classesEquivalencia, tr1.getEstadoDestino().getId(), primClasseEstado.nomeClasseAtual)) {
//                                    classeEq1 = retornaClasseEquivalenciaAnterior(classesEquivalencia, tr1.getEstadoDestino().getId());
//                                } else {
//                                    classeEq1 = retornaClasseEquivalenciaAtual(classesEquivalencia, tr1.getEstadoDestino().getId());
//                                }
//                                String classeEq2;
//                                if (isAlocadoEmClasse(classesEquivalencia, tr2.getEstadoDestino().getId(), primClasseEstado.nomeClasseAtual)) {
//                                    classeEq2 = retornaClasseEquivalenciaAnterior(classesEquivalencia, tr2.getEstadoDestino().getId());
//                                } else {
//                                    classeEq2 = retornaClasseEquivalenciaAtual(classesEquivalencia, tr2.getEstadoDestino().getId());
//                                }
//
//                                if (!classeEq2.equals(classeEq1)) {
//                                    classesIguais = false;
//                                }
//                            } else {
//                                classesIguais = false;
//                            }
//
//                        }
//
//                        if (classesIguais) {
//                            if (!segClasseEstado.nomeClasseAtual.equals(primClasseEstado.nomeClasseAtual)) {
//                                segClasseEstado.nomeClasseAnterior = segClasseEstado.nomeClasseAtual;
//
//                                segClasseEstado.nomeClasseAtual = primClasseEstado.nomeClasseAtual;
//                                segClasseEstado.verificada = true;
//                                modificado = true;
//                            } else {
//                                segClasseEstado.nomeClasseAnterior = segClasseEstado.nomeClasseAtual;
//                                segClasseEstado.verificada = true;
//                            }
//
//                            classesEquivalencia.set(j, segClasseEstado);
//                        } else {
//                            modificado = true;
//                        }
//
//                    } else {
//                        modificado = true;
//                    }
//
//                }
//
//                primClasseEstado.nomeClasseAnterior = classeAntEstadoAtual;
//                classesEquivalencia.set(i, primClasseEstado);
//            }
//
//        }
//        System.out.println(classesEquivalencia);
//
//        int nrEliminados = reduzEstadosEquivalentes(classesEquivalencia, transicoes);
//        //renomeiaEstados(this);
//        //return nrEliminados;
//    }
//    
//    public static ArrayList retornaTransicoesEstado(ArrayList trans, String estadoAtual, String valorEnt) {
//        ArrayList transicoesEstadoAtual = new ArrayList();
//
//        for (int i = 0; i < trans.size(); i++) {
//            Transicao tr = (Transicao) trans.get(i);
//
//            if (!tr.getEstadoOrigem().equals(estadoAtual)) {
//                continue;
//            }
//            if (valorEnt != null) {
//                if (tr.getSimbolo().equals(valorEnt)) {
//                    transicoesEstadoAtual.add(tr);
//                }
//            } else {
//                transicoesEstadoAtual.add(tr);
//            }
//        }
//
//        return transicoesEstadoAtual;
//    }
//    
//    private boolean isAlocadoEmClasse(ArrayList vetorClasses, String estadoAtual, String nomeClasseAlocado) {
//        for (int i = 0; i < vetorClasses.size(); i++) {
//            ClasseEstado ca = (ClasseEstado) vetorClasses.get(i);
//            if (ca.nomeEstado.equals(estadoAtual)) {
//                return ca.verificada;
//            }
//
//        }
//        return false;
//    }
//    
//    private String retornaClasseEquivalenciaAnterior(ArrayList vetorClasses, String estadoAtual) {
//        for (int i = 0; i < vetorClasses.size(); i++) {
//            ClasseEstado ca = (ClasseEstado) vetorClasses.get(i);
//            if (ca.nomeEstado.equals(estadoAtual)) {
//                return ca.nomeClasseAnterior;
//            }
//        }
//        return null;
//    }
//    
//    private String retornaClasseEquivalenciaAtual(ArrayList vetorClasses, String estadoAtual) {
//        for (int i = 0; i < vetorClasses.size(); i++) {
//            ClasseEstado ca = (ClasseEstado) vetorClasses.get(i);
//            if (ca.nomeEstado.equals(estadoAtual)) {
//                return ca.nomeClasseAtual;
//            }
//        }
//        return null;
//    }
//    
//    private int reduzEstadosEquivalentes(ArrayList classesEquivalencia, ArrayList transicoes) {
//        int nrEliminados = 0;
//
//        for (int i = 0; i < classesEquivalencia.size(); i++) {
//            ClasseEstado classeEstadoAtual = (ClasseEstado) classesEquivalencia.get(i);
//            classeEstadoAtual.verificada = false;
//            classesEquivalencia.set(i, classeEstadoAtual);
//        }
//
//        for (int i = 0; i < classesEquivalencia.size(); i++) {
//            ClasseEstado ce1 = (ClasseEstado) classesEquivalencia.get(i);
//
//            if (ce1.verificada) {
//                continue;
//            }
//            for (int j = i + 1; j < classesEquivalencia.size(); j++) {
//                ClasseEstado ce2 = (ClasseEstado) classesEquivalencia.get(j);
//
//                if (!ce2.nomeClasseAtual.equals(ce1.nomeClasseAtual)) {
//                    continue;
//                }
//                for (int t = 0; t < transicoes.size(); t++) {
//                    Transicao tr = (Transicao) transicoes.get(t);
//                    Estado orig = tr.getEstadoOrigem();
//                    Estado dest = tr.getEstadoDestino();
//
//                    if (ce2.nomeEstado.equals(tr.getEstadoOrigem())) {
//                        for(Estado e : this.estados){
//                            if(e.getId().equals(ce1.nomeClasseAtual)){
//                                orig=e;
//                            }
//                        }                        
//                    }
//
//                    if (ce2.nomeEstado.equals(tr.getEstadoDestino())) {
//                        for(Estado e : this.estados){
//                            if(e.getId().equals(ce1.nomeClasseAtual)){
//                                dest=e;
//                            }
//                        }                       
//                    }
//                    Transicao tr1= new Transicao(orig, dest, tr.getSimbolo());
//                    //this.automatoAtual.criaTransicao(orig, , dest);
//                }
//
//                ce2.verificada = true;
//                classesEquivalencia.set(j, ce2);
//                
//                ArrayList<Estado> A= new ArrayList();
//                for(Estado e : this.estados){
//                    A.add(e);
//                }
//                
//                for(Estado e : A){
//                    if(e.getId().equals(ce2.nomeEstado)){
//                       this.removeEstado(e);
//                        nrEliminados++; 
//                    }
//                }
//                
//            }
//
//        }
//
//        return nrEliminados;
//    }
//
//    //############################

    public void printTEMP(ArrayList<ArrayList> temp) {
        for (int i = 0; i < temp.size(); i++) {
            System.out.println("---classe inicio---");
            for (int j = 0; j < temp.get(i).size(); j++) {
                System.out.println(temp.get(i).get(j).toString());
            }
            System.out.println("---classe fim---");
        }
    }

    /**
     * Verifica e lista quais simbolos sao gerados/reconhecidos pelo automato
     *
     * @return uma lista de simbolos gerados/reconhecidos pelo automato
     */
    private ArrayList<String> listaSimbolos() {
        ArrayList<String> s = new ArrayList();
        for (Transicao trans : this.transicoes) {
            String simbolo = trans.getSimbolo();
            if (!s.contains(simbolo)) {
                s.add(simbolo);
            }
        }
        return s;
    }

    /**
     * Cria e retorna um array bidimensional do automato
     *
     * @return um array bidimensional de Strings que representa o automato
     */
    public String[][] af2BiDimArray() {
        //cria uma tabela (array bidimensional) para representar o automado em forma de tabela
        int linhas = estados.size() + 1;
        int colunas = simbolos.size() + 1;
        String[][] tabela = new String[linhas][colunas];

        //Simbolos indicativos de estado inicial ou estado final
        String estFinal = "*";
        String estInicial = "->";

        //define os rotulos das linhas e das colunas da tabela
        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {

                if (linha == 0 && coluna == 0) {
                    tabela[linha][coluna] = "q";
                }

                if (linha == 0 && coluna > 0) {
                    tabela[linha][coluna] = simbolos.get(coluna - 1);
                }

                if (linha > 0 && coluna == 0) {
                    tabela[linha][coluna] = estados.get(linha - 1).getId();
                    if (estados.get(linha - 1).isEstadoFinal()) {
                        //se o estado é final, acrescenta '*' a sua representacao
                        tabela[linha][coluna] = estFinal.concat(tabela[linha][coluna]);
                    }
                    if (estados.get(linha - 1).isEstadoInicial()) {
                        //se o estado é inicial, acrescenta '->' a sua representacao
                        tabela[linha][coluna] = estInicial.concat(tabela[linha][coluna]);
                    }
                }
            }
        }

        //define os estados na tabela
        for (int linha = 1; linha < linhas; linha++) {
            for (int coluna = 1; coluna < colunas; coluna++) {
                for (Transicao t : transicoes) {
                    if ((tabela[linha][0]).contains(t.getEstadoOrigem().getId())) {
                        if (t.getSimbolo().equals(tabela[0][coluna])) {
                            if (tabela[linha][coluna] == null) {
                                tabela[linha][coluna] = t.getEstadoDestino().getId();
                            } else {
                                tabela[linha][coluna] = tabela[linha][coluna].concat(t.getEstadoDestino().getId());
                            }
                        }
                    }
                }
            }
        }
        return tabela;
    }

    /**
     * Imprime uma representacao do automato finito em forma de String
     *
     * @return uma representacao do automato finito
     */
    @Override
    public String toString() {//AutomatoFinito af) {
        //cria o formatador da tabela
        Formatter formatter = new Formatter();

        //cria uma tabela (array bidimensional) para representar o automado em forma de tabela
        int linhas = estados.size() + 1;
        int colunas = simbolos.size() + 1;
        String[][] tabela = this.af2BiDimArray();

        //imprime a tabela do automato
        for (int linha = 0; linha < linhas; linha++) {
            boolean linhaMudou = false;
            while (!linhaMudou) {
                for (int coluna = 0; coluna < colunas; coluna++) {
                    formatter = new Formatter();
                    String dado = "";
                    if (tabela[linha][coluna] == null) {
                        dado = "&";
                    } else {
                        dado = tabela[linha][coluna];
                    }
                    System.out.print(formatter.format("%5s", dado) + "\t");
                }
                linhaMudou = true;
                System.out.println();
            }
        }
        return null;
    }
}
