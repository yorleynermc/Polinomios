package Polinomios;
import javax.swing.JOptionPane;

public class Polinomio {
    private int n; // Cantidad de elementos en el arreglo
    private float[] vec; // Almacena el grado y los coeficientes del polinomio

    public Polinomio(int grado) {
        n = grado + 2;
        vec = new float[n];
        vec[0] = grado; // El primer elemento almacena el grado del polinomio
    }

    public void mostrar() {
        String resultado = "";  // Usamos una variable simple para concatenar el resultado
        for (int k = 1; k < vec.length; k++) {
            if (vec[k] != 0) {
                int exponente = (int) (vec[0] + 1 - k);
                if (vec[k] > 0 && k > 1) {
                    resultado += "+"; // Concatenamos el signo + cuando corresponde
                }
                resultado += vec[k] + "x^" + exponente + " "; // Concatenamos el término
            }
        }
        JOptionPane.showMessageDialog(null, "Polinomio: " + resultado);
    }

    public void ingresarTerminos() {
        String rpa;
        do {
            rpa = JOptionPane.showInputDialog("¿Desea ingresar un término? (S/N)").toUpperCase();
            if (rpa.equals("S")) {
                float coef = Float.parseFloat(JOptionPane.showInputDialog("Ingrese coeficiente del término:"));
                int exp = Integer.parseInt(JOptionPane.showInputDialog("Ingrese exponente del término:"));

                if (exp <= vec[0] && exp >= 0) {
                    int pos = (int) (vec[0] + 1 - exp);
                    if (vec[pos] == 0) {
                        vec[pos] = coef;
                    } else {
                        JOptionPane.showMessageDialog(null, "Ya existe un término con el mismo exponente.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El exponente no corresponde al polinomio.");
                }
            }
        } while (rpa.equals("S"));

        if (vec[1] == 0) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un término con el grado del polinomio.");
        }
    }


    public void evaluar(int x) {
        double r = 0; // Resultados del polinomio evaluado
        for (int k = 1; k < vec[0] + 2; k++) {
            int exponente = (int)(vec[0] + 1 - k);
            r += vec[k] * Math.pow(x, exponente); // Sumamos el término correspondiente
        }
        JOptionPane.showMessageDialog(null, "El valor del polinomio en x = " + x + " es: " + r);
    }

    // eliminando los terminos nulos
    public void ajustar() {
        int k, j = 1, cont = 0;

        // Buscamos los términos nulos (cero) y contamos cuantos
        while (j < vec[0] + 2 && vec[j] == 0) {
            cont++;
            j++;
        }

        // Desplazamos los términos no nulos hacia el inicio del arreglo
        for (k = j; k < vec[0] + 2; k++) {
            vec[k - cont] = vec[k];
        }

        // Ajustamos el grado del polinomio
        vec[0] = vec[0] - cont;
    }
    public float getDato(int index) {
        return vec[index];
    }

    public void setDato(int index, float value) {
        vec[index] = value;
    }

    public int getGrado() {
        return (int) vec[0];
    }

    public Polinomio sumar(Polinomio B) {
        int my;
        int k = 1, j = 1;
        int expA, expB, posR;

        // Determinamos el grado máximo entre los dos polinomios
        if (this.getGrado() > B.getGrado()) {
            my = this.getGrado();
        } else {
            my = B.getGrado();
        }

        // Creamos el polinomio resultado con el grado máximo
        Polinomio R = new Polinomio(my);

        // Sumamos los términos de ambos polinomios
        while (k < this.getGrado() + 2 && j < B.getGrado() + 2) {
            expA = this.getGrado() + 1 - k;
            expB = B.getGrado() + 1 - j;

            if (expA > expB) {
                // El exponente de A es mayor
                posR = R.getGrado() + 1 - expA;
                R.setDato(posR, this.getDato(k)); // Copiamos el valor de A
                k++;
            } else if (expB > expA) {
                // El exponente de B es mayor
                posR = R.getGrado() + 1 - expB;
                R.setDato(posR, B.getDato(j)); // Copiamos el valor de B
                j++;
            } else {
                // Los exponentes son iguales, sumamos los coeficientes
                posR = R.getGrado() + 1 - expA;
                R.setDato(posR, this.getDato(k) + B.getDato(j)); // Suma de los coeficientes
                k++;
                j++;
            }
        }

        // Si quedan términos en A que no han sido procesados
        while (k < this.getGrado() + 2) {
            expA = this.getGrado() + 1 - k;
            posR = R.getGrado() + 1 - expA;
            R.setDato(posR, this.getDato(k)); // Copiamos el valor de A
            k++;
        }

        // Si quedan términos en B que no han sido procesados
        while (j < B.getGrado() + 2) {
            expB = B.getGrado() + 1 - j;
            posR = R.getGrado() + 1 - expB;
            R.setDato(posR, B.getDato(j)); // Copiamos el valor de B
            j++;
        }

        // Ajustamos el polinomio para eliminar los términos nulos
        R.ajustar();

        return R; // Retornamos el polinomio resultado
    }
    
    public Polinomio multiplicar(Polinomio B) {
        int k, j;
        int expA, expB, expR, posR;
        float coefR;

        // Creamos un polinomio resultado con grado máximo posible (suma de los grados de los polinomios)
        Polinomio R = new Polinomio(this.getGrado() + B.getGrado());

        // Multiplicamos cada término de A con cada término de B
        for (j = 1; j < B.getGrado() + 2; j++) {
            expB = B.getGrado() + 1 - j;

            for (k = 1; k < this.getGrado() + 2; k++) {
                expA = this.getGrado() + 1 - k;
                expR = expA + expB; // Suma de los exponentes
                coefR = this.getDato(k) * B.getDato(j); // Producto de los coeficientes
                posR = R.getGrado() + 1 - expR; // Posición en el arreglo del resultado
                R.setDato(posR, R.getDato(posR) + coefR); // Suma acumulativa de coeficientes en la posición
            }
        }

        // Ajustamos el resultado para eliminar los términos nulos
        R.ajustar();

        return R; // Retornamos el polinomio resultado
    }
    public Polinomio restar(Polinomio B) {
        Polinomio R = new Polinomio(Math.max(this.getGrado(), B.getGrado()));

        for (int i = 1; i < R.getGrado() + 2; i++) {
            float coefA = (i < this.getGrado() + 2) ? this.getDato(i) : 0;
            float coefB = (i < B.getGrado() + 2) ? B.getDato(i) : 0;
            R.setDato(i, coefA - coefB);
        }

        R.ajustar();
        return R;
    }
    public boolean comparar(Polinomio B) {
        if (this.getGrado() != B.getGrado()) return false;

        for (int i = 1; i < this.getGrado() + 2; i++) {
            if (this.getDato(i) != B.getDato(i)) return false;
        }

        return true;
    }

}

