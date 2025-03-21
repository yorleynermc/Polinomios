package Polinomios;

import javax.swing.JOptionPane;

public class Polv2 {
    private int n; // Tamaño del vector
    private double[] vec;





    // Constructor
    public Polv2(int canTerm) {
        n = canTerm * 2 + 1; // Tamaño del vector
        vec = new double[n];
        vec[0] = canTerm; // Cantidad de términos
    }

    //  para redimensionar el vect

    public void redimensionarVector() {
        n = n + 2;
        double[] aux = new double[n];
        for (int k = 0; k < vec[0] * 2 + 1; k++) {
            aux[k] = vec[k];
        }
        vec = aux;
    }


    public double getDato(int pos) {
        return vec[pos];
    }


    public void setDato(double dato, int pos) {
        vec[pos] = dato;
    }


    public int getTamaño() {
        return n;
    }


    public String mostrar() {
        String resultado = "";
        for (int k = 1; k < vec[0] * 2 + 1; k += 2) {
            if (vec[k + 1] > 0 && k > 1) {
                resultado += "+" + vec[k + 1] + "X^" + (int) vec[k] + " ";
            } else {
                resultado += vec[k + 1] + "X^" + (int) vec[k] + " ";
            }
        }
        return resultado;
    }

    //evaluar el polinomio en un valor dado de x
    public double evaluar(double x) {
        double resultado = 0;
        for (int k = 1; k < vec[0] * 2 + 1; k += 2) {
            resultado += vec[k + 1] * Math.pow(x, vec[k]);
        }
        return resultado;
    }


    public void ingresarTerminos(int canTerm) {
        for (int cont = 0; cont < canTerm; cont++) {
            // Solicitar coeficiente
            double coef = Double.parseDouble(JOptionPane.showInputDialog("Ingrese coeficiente:"));

            // Solicitar exponente
            int exp = Integer.parseInt(JOptionPane.showInputDialog("Ingrese exponente:"));

            if (exp >= 0) {
                int k = 1;
                while (k < vec[0] * 2 + 1 && vec[k] > exp) {
                    k += 2;
                }

                if (k < vec[0] * 2 + 1 && vec[k] == exp) {
                    JOptionPane.showMessageDialog(null, "Ya existe un término con el mismo exponente", "Error", JOptionPane.ERROR_MESSAGE);
                    cont--;
                } else {


                    for (int j = (int) vec[0] * 2 - 1; j > k; j--) {
                        vec[j + 1] = vec[j - 1];
                    }
                    vec[k] = exp;
                    vec[k + 1] = coef;
                }
            } else {
                JOptionPane.showMessageDialog(null, "El exponente no es válido", "Error", JOptionPane.ERROR_MESSAGE);
                cont--;
            }
        }
    }
    public void sumarTerm(double coef, int exp) {
        if (coef != 0) {
            int k = 1, j;

            // Buscar la posición donde insertar el término
            while ((k < vec[0] * 2 + 1) && (vec[k] > exp)) {
                k += 2;
            }

            if (k < vec[0] * 2 + 1 && vec[k] == exp) {
                // Si el término ya existe, sumamos los coeficientes
                if ((vec[k + 1] + coef) != 0) {
                    vec[k + 1] += coef;
                } else {
                    // Si la suma da 0, eliminamos el término
                    for (j = k; j < (vec[0] * 2 - 1); j += 2) {
                        vec[j] = vec[j + 2];
                        vec[j + 1] = vec[j + 3];
                    }
                    vec[0] -= 1; // Disminuir el número de términos
                }
            } else {
                // Si el vector está lleno, lo redimensionamos
                if (vec[0] * 2 + 1 >= n) {
                    redimensionarVector();
                }

                // Hacer espacio para el nuevo término
                for (j = (int) vec[0] * 2 ; j >= k; j--) {
                    vec[j + 2] = vec[j];
                }

                // Insertar el nuevo término
                vec[k] = exp;
                vec[k + 1] = coef;
                vec[0] += 1; // Aumentar la cantidad de términos
            }
        }
    }
    public Polv2 sumar(Polv2 B) {
        int k = 1, j = 1, expA, expB;
        double coeA, coeB;

        // Crear el polinomio resultado con el tamaño inicial basado en el polinomio actual
        Polv2 R = new Polv2((int) (vec[0] + B.getDato(0))); // Asegurar espacio suficiente


        // Recorrer ambos polinomios y sumarlos
        while ((k < vec[0] * 2 + 1) && (j < B.getDato(0) * 2 + 1)) {
            expA = (int) vec[k];
            expB = (int) B.getDato(j);
            coeA = vec[k + 1];
            coeB = B.getDato(j + 1);

            if (expA == expB) { // Si los exponentes son iguales
                if ((coeA + coeB) != 0) {
                    R.sumarTerm(coeA + coeB, expA);
                }
                k += 2;
                j += 2;
            } else if (expA > expB) { // Si expA es mayor, se agrega directamente a R
                R.sumarTerm(coeA, expA);
                k += 2;
            } else { // Si expB es mayor, se agrega directamente a R
                R.sumarTerm(coeB, expB);
                j += 2;
            }
        }

        // Si quedan términos en A, los agregamos al resultado
        while (k < vec[0] * 2 + 1) {
            R.sumarTerm(vec[k + 1], (int) vec[k]);
            k += 2;
        }

        // Si quedan términos en B, los agregamos al resultado
        while (j < B.getDato(0) * 2 + 1) {
            R.sumarTerm(B.getDato(j + 1), (int) B.getDato(j));
            j += 2;
        }

        return R;
    }
    // Metodo para multiplicar polinomios
    public Polv2 multiplicar(Polv2 B) {
        Polv2 R = new Polv2(0); // Inicializamos con 0 términos
        for (int k = 1; k < vec[0] * 2 + 1; k += 2) {
            for (int j = 1; j < B.getDato(0) * 2 + 1; j += 2) {
                R.sumarTerm(vec[k + 1] * B.getDato(j + 1), (int) (vec[k] + B.getDato(j)));
            }
        }
        return R;
    }
    public Polv2 restar(Polv2 B) {
        int k = 1, j = 1, expA, expB;
        double coeA, coeB;
        Polv2 R = new Polv2((int) (vec[0] + B.getDato(0))); // Crear el polinomio resultado

        while ((k < vec[0] * 2 + 1) && (j < B.getDato(0) * 2 + 1)) {
            expA = (int) vec[k];
            expB = (int) B.getDato(j);
            coeA = vec[k + 1];
            coeB = B.getDato(j + 1);

            if (expA == expB) { // Si los exponentes son iguales
                if ((coeA - coeB) != 0) {
                    R.sumarTerm(coeA - coeB, expA);
                }
                k += 2;
                j += 2;
            } else if (expA > expB) { // Si expA es mayor, se agrega directamente a R
                R.sumarTerm(coeA, expA);
                k += 2;
            } else { // Si expB es mayor, se resta el coeficiente de B (lo agregamos con signo negativo)
                R.sumarTerm(-coeB, expB);
                j += 2;
            }
        }

        // Si quedan términos en A, los agregamos al resultado
        while (k < vec[0] * 2 + 1) {
            R.sumarTerm(vec[k + 1], (int) vec[k]);
            k += 2;
        }

        // Si quedan términos en B, los agregamos al resultado con signo negativo
        while (j < B.getDato(0) * 2 + 1) {
            R.sumarTerm(-B.getDato(j + 1), (int) B.getDato(j));
            j += 2;
        }

        return R;
    }
    public Polv2 hacerCopia() {
        Polv2 copia = new Polv2((int) vec[0]); // Crear un nuevo polinomio con la misma cantidad de términos
        for (int k = 0; k < vec[0] * 2 + 1; k++) { // Copiar todos los elementos del vector
            copia.setDato(vec[k], k);
        }
        return copia;
    }
    public Polv2 dividir(Polv2 B) {
        int k, expt;
        double coet;
        Polv2 R = new Polv2(0);

        while (vec[1] >= B.getDato(1)) {
            coet = vec[2] / B.getDato(2);
            expt = (int) (vec[1] - B.getDato(1));
            R.sumarTerm(coet, expt);

            for (k = 1; k < B.getDato(0) * 2 + 1; k += 2) {
                this.sumarTerm(-coet * B.getDato(k + 1), (int) (expt + B.getDato(k)));
            }
        }

        return R;
    }
    public boolean comparar(Polv2 B) {
        if (this.getDato(0) != B.getDato(0)) return false; // Comparar la cantidad de términos

        for (int i = 1; i < this.getDato(0) * 2 + 1; i++) {
            if (this.getDato(i) != B.getDato(i)) return false; // Comparar cada término
        }

        return true; // Si no hubo diferencias, son iguales
    }



}