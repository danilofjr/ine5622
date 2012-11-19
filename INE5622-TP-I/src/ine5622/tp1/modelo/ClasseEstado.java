/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ine5622.tp1.modelo;

///////CLASSE CRIADA PARA TESTE COM UM ALGORITMO DE MINIMIZAÇÃO

class ClasseEstado
{
  public String nomeEstado;
  public String nomeClasseAnterior;
  public String nomeClasseAtual;
  public boolean verificada;

  public ClasseEstado(String est, String nomeDaClasseAnterior, String nomeDaClasseAtual)
  {
    this.nomeEstado = est;
    this.nomeClasseAnterior = nomeDaClasseAnterior;
    this.nomeClasseAtual = nomeDaClasseAtual;
    this.verificada = false;
  }
}
