package Polinomios;

import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        menuppal();
    }

    // Menú principal
    public static void menuppal() {
        int opcion;
        String menu = "**** MENÚ prueba****\n" +
                "1 - Polinomios en vector forma 1\n" +
                "2 - Polinomios en vector forma 2\n" +
                "0 - Salir\n" +
                "Digite la opción:";

        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
            switch (opcion) {
                case 1:
                    menuforma1();
                    break;
                case 2:
                    menuforma2();
                    break;
                case 0:
                    System.exit(0);
                    break;
            }
        } while (opcion != 0);
    }

    // Menú para Polinomios en Vector Forma 1
    public static void menuforma1() {
        int grado, opcion;
        float x;
        Polinomio A, B, C;

        String menu = "**** MENÚ ****\n\n" +
                "1 – Mostrar\n" +
                "2 – Evaluar\n" +
                "3 – Sumar\n" +
                "4 – Restar\n" +
                "5 – Multiplicar\n" +
                "6 – Comparar\n" +
                "7 – Regresar al menú principal\n" +
                "0 – Salir\n\n" +
                "Digite la opción:";

        grado = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el grado del polinomio 1"));
        A = new Polinomio(grado);
        A.ingresarTerminos();

        grado = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el grado del polinomio 2"));
        B = new Polinomio(grado);
        B.ingresarTerminos();

        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));

            switch (opcion) {
                case 1:
                    A.mostrar();
                    B.mostrar();
                    break;
                case 2:
                    x = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el valor de x"));
                    A.evaluar((int) x);
                    B.evaluar((int) x);
                    break;
                case 3:
                    C = A.sumar(B);
                    C.mostrar();
                    break;
                case 4:
                    C = A.restar(B);
                    C.mostrar();
                    break;
                case 5:
                    C = A.multiplicar(B);
                    C.mostrar();
                    break;
                case 6:
                    boolean iguales = A.comparar(B);
                    JOptionPane.showMessageDialog(null, iguales ? "Los polinomios son iguales." : "Los polinomios son diferentes.");
                    break;
                case 7:
                    menuppal();
                    return;
                case 0:
                    System.exit(0);
                    break;
            }
        } while (opcion != 0);
    }

    // Menú para Polinomios en Vector Forma 2
    public static void menuforma2() {
        int canterm, opcion;
        float x;
        Polv2 A, B, C, copia;

        String menu = "*** MENÚ ***\n\n" +
                "1 – Mostrar\n" +
                "2 – Evaluar\n" +
                "3 – Sumar\n" +
                "4 - Restar\n" +
                "5 – Multiplicar\n" +
                "6 – Dividir\n" +
                "7 – Comparar\n" +
                "8 – Regresar al menú principal\n" +
                "0 – Salir\n\n" +
                "Digite la opción:";

        canterm = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de términos del polinomio 1"));
        A = new Polv2(canterm);
        A.ingresarTerminos(canterm);

        canterm = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de términos del polinomio 2"));
        B = new Polv2(canterm);
        B.ingresarTerminos(canterm);

        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));

            switch (opcion) {
                case 1:
                    JOptionPane.showMessageDialog(null, "<html>Polinomio 1<br>" + A.mostrar() + "<br>Polinomio 2<br>" + B.mostrar() + "</html>");
                    break;
                case 2:
                    x = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el valor de x"));
                    JOptionPane.showMessageDialog(null, x + " en polinomio 1\n" + A.evaluar(x) + "\n\n" + x + " en polinomio 2\n" + B.evaluar(x));
                    break;
                case 3:
                    C = A.sumar(B);
                    JOptionPane.showMessageDialog(null, "<html>Polinomio 1<br>" + A.mostrar() +
                            "<br>+<br>Polinomio 2<br>" + B.mostrar() +
                            "<br>=<br>Resultado<br>" + C.mostrar() + "</html>");
                    break;
                case 4:
                    C = A.restar(B);
                    JOptionPane.showMessageDialog(null, "<html>Polinomio 1<br>" + A.mostrar() +
                            "<br>-<br>Polinomio 2<br>" + B.mostrar() +
                            "<br>=<br>Resultado<br>" + C.mostrar() + "</html>");


                    break;

                case 5:
                    C = A.multiplicar(B);
                    JOptionPane.showMessageDialog(null, "<html>Polinomio 1<br>" + A.mostrar() +
                            "<br>*<br>Polinomio 2<br>" + B.mostrar() +
                            "<br>=<br>Resultado<br>" + C.mostrar() + "</html>");
                    break;
                case 6:
                    if (A.getDato(1) >= B.getDato(1)) {
                        copia = A.hacerCopia();
                        C = copia.dividir(B);
                        JOptionPane.showMessageDialog(null, "<html>Polinomio 1<br>" + A.mostrar() +
                                "<br>Polinomio 2<br>" + B.mostrar() +
                                "<br>División<br>" + C.mostrar() +
                                "<br>Residuo<br>" + copia.mostrar() + "</html>");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se puede dividir");
                    }
                    break;
                case 7:
                    boolean comparacion = A.comparar(B);
                    JOptionPane.showMessageDialog(null, comparacion ? "Los polinomios son iguales." : "Los polinomios son diferentes.");
                    break;
                case 8:
                    menuppal();
                    return;
                case 0:
                    System.exit(0);
                    break;
            }
        } while (opcion != 0);
    }
}



