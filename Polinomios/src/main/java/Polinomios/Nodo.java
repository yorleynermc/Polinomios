package Polinomios;

public class Nodo {
    private double coef;  // Coeficiente del término
    private int exp;      // Exponente del término
    private Nodo liga;    // Puntero al siguiente nodo

    // Constructor
    public Nodo(double co, int ex) {
        coef = co;
        exp = ex;
        liga = null;
    }

    // Método que retorna el coeficiente
    public double getCoef() {
        return coef;
    }

    // Método que asigna un valor al coeficiente
    public void setCoef(double co) {
        coef = co;
    }

    // Método que retorna el exponente
    public int getExp() {
        return exp;
    }

    // Método que asigna un valor al exponente
    public void setExp(int ex) {
        exp = ex;
    }

    // Método que retorna la liga (siguiente nodo)
    public Nodo getLiga() {
        return liga;
    }

    // Método que asigna un valor a la liga
    public void setLiga(Nodo lig) {
        liga = lig;
    }
}
