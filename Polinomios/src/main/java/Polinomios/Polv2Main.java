package Polinomios;

import javax.swing.JOptionPane;

public class Polv2Main {
    public static void main(String[] args) {
        // Solicitar al usuario el número de términos del primer polinomio
        int numTerminosA = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de términos del primer polinomio:"));
        Polv2 polinomioA = new Polv2(numTerminosA);
        polinomioA.ingresarTerminos(numTerminosA);

        // Solicitar al usuario el número de términos del segundo polinomio
        int numTerminosB = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de términos del segundo polinomio:"));
        Polv2 polinomioB = new Polv2(numTerminosB);
        polinomioB.ingresarTerminos(numTerminosB);

        // Mostrar ambos polinomios
        JOptionPane.showMessageDialog(null, "Primer polinomio: " + polinomioA.mostrar(), "Polinomio A", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null, "Segundo polinomio: " + polinomioB.mostrar(), "Polinomio B", JOptionPane.INFORMATION_MESSAGE);

        // Sumar ambos polinomios
        Polv2 resultado = polinomioA.sumar(polinomioB);
        JOptionPane.showMessageDialog(null, "Resultado de la suma: " + resultado.mostrar(), "Polinomio Resultante", JOptionPane.INFORMATION_MESSAGE);

        // Evaluar el polinomio resultante en un valor de x
        double x = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el valor de x para evaluar el polinomio resultante:"));
        double resultadoEvaluado = resultado.evaluar(x);
        JOptionPane.showMessageDialog(null, "El resultado de evaluar el polinomio en x = " + x + " es: " + resultadoEvaluado, "Resultado Evaluación", JOptionPane.INFORMATION_MESSAGE);
    }
}

