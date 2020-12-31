package Voz;

import calculadoravoz.*;
import java.io.*;
import javax.swing.*;
import java.util.Locale;
import javax.speech.Central;
import javax.speech.EngineModeDesc;
import javax.speech.recognition.*;


public class ReconocimientoVoz extends ResultAdapter implements Runnable {

    CalculadoraCientifica calculadora;
    static Recognizer reconocedorVoz;
    String palabra;
    

    public ReconocimientoVoz(CalculadoraCientifica calculadora) {
        this.calculadora = calculadora;
    }
    @Override
    public void resultAccepted(ResultEvent re) {
        try {
            Result res = (Result) (re.getSource());
            ResultToken tokens[] = res.getBestTokens();

            for (int i = 0; i < tokens.length; i++) {
                palabra = tokens[i].getSpokenText();
                System.out.println(palabra);
                if (palabra.equals("uno")) {
                    this.calculadora.escribirEnPantalla("1");
                }
                else if (palabra.equals("dos")) {
                    this.calculadora.escribirEnPantalla("2");
                }
                else if (palabra.equals("tres")) {
                    this.calculadora.escribirEnPantalla("3");
                }
                else if (palabra.equals("cuatro")) {
                    this.calculadora.escribirEnPantalla("4");
                }
                else if (palabra.equals("cinco")) {
                    this.calculadora.escribirEnPantalla("5");
                }
                else if (palabra.equals("seis")) {
                    this.calculadora.escribirEnPantalla("6");
                }
                else if (palabra.equals("siete")) {
                    this.calculadora.escribirEnPantalla("7");
                }
                else if (palabra.equals("ocho")) {
                    this.calculadora.escribirEnPantalla("8");
                }
                else if (palabra.equals("nueve")) {
                    this.calculadora.escribirEnPantalla("9");
                }
                else if (palabra.equals("cero")) {
                    this.calculadora.escribirEnPantalla("0");
                }
                else if (palabra.equals("coma")) {
                    this.calculadora.escribirEnPantalla(".");
                }
                if (palabra.equals("mas")) {
                    System.out.println("mas");
                    this.calculadora.undirBoton(1);
                }
                if (palabra.equals("menos")) {
                    System.out.println("menos");
                    this.calculadora.undirBoton(2);
                }
                if (palabra.equals("dividido")) {
                    System.out.println("dividido");
                    this.calculadora.undirBoton(4);
                }
                if (palabra.equals("por")) {
                    System.out.println("por");
                    this.calculadora.undirBoton(3);
                }
                if (palabra.equals("modulo")) {
                    System.out.println("modulo");
                    this.calculadora.undirBoton(5);
                }
                if (palabra.equals("potencia")) {
                    System.out.println("potencia");
                    this.calculadora.undirBoton(6);
                }
                if (palabra.equals("logaritmo")) {
                    System.out.println("logaritmo");
                    this.calculadora.undirBoton(7);
                }
                if (palabra.equals("cuadrado")) {
                    System.out.println("cuadrado");
                    this.calculadora.undirBoton(8);
                }
                if (palabra.equals("raiz")) {
                    System.out.println("raiz");
                    this.calculadora.undirBoton(9);
                }
                if (palabra.equals("inversa")) {
                    System.out.println("inversa");
                    this.calculadora.undirBoton(10);
                }
                if (palabra.equals("absoluto")) {
                    System.out.println("absoluto");
                    this.calculadora.undirBoton(11);
                }
                if (palabra.equals("factorial")) {
                    System.out.println("factorial");
                    this.calculadora.undirBoton(12);
                }
                if (palabra.equals("cubo")) {
                    System.out.println("cubo");
                    this.calculadora.undirBoton(13);
                }
                if (palabra.equals("pi")) {
                    System.out.println("pi");
                    double pi = Math.PI;
                    this.calculadora.escribirEnPantalla(Double.toString(pi));
                }
                if (palabra.equals("cambio")) {
                    System.out.println("NEGATIVO_POSITIVO");
                    this.calculadora.undirBoton(14);
                }
                if (palabra.equals("base")) {
                    System.out.println("base");
                    this.calculadora.undirBoton(15);
                }
                if (palabra.equals("igual")) {
                    this.calculadora.undirIgual();
                }
                if (palabra.equals("borrar")) {
                    this.calculadora.borrarTodo();
                }
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }


    public void reconocerVoz() {
        try {
           

            EngineModeDesc rec = new EngineModeDesc(Locale.ROOT);
            reconocedorVoz = Central.createRecognizer(rec);   
                        
            reconocedorVoz.allocate();
            
            FileReader gramatica = new FileReader("libreria\\diccionario.txt");

            RuleGrammar reglaGramatica = reconocedorVoz.loadJSGF(gramatica);
            reglaGramatica.setEnabled(true);
            
            reconocedorVoz.addResultListener(new ReconocimientoVoz(calculadora));
            reconocedorVoz.commitChanges();
            reconocedorVoz.requestFocus();
        } catch (Exception e) {
            System.out.println("Error en " + e.toString());
        }
    }
    @Override
    public void run() {
        this.reconocerVoz();
    }

}
