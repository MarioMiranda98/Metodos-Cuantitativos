/*
    Clase que modela la funcion objetivo y se encarga de evaluarla para los valores "x" 
    e "y" de un integrante "i" de la poblacion.
*/
public class FuncionObjetivo {
    public FuncionObjetivo(int cantidadRestricciones, Restriccion[] restricciones) {
        this.cantidadRestricciones = cantidadRestricciones;
        this.restricciones = restricciones;
        resultado = 0;
    }

    public int getCantidadRestricciones() { return this.cantidadRestricciones; }
    public Restriccion[] getRestricciones() { return this.restricciones; }

    public void setCantidadRestricciones(int cantidadRestricciones) {
        this.cantidadRestricciones = cantidadRestricciones;
    }

    public void setRestricciones(Restriccion[] restricciones) {
        this.restricciones = restricciones;
    }

    //Metodo que se encarga de evaluar la funcion objetivo de acuerdo a los 
    //valores "x" e "y" de un integrante.
    //Recibe como parametro un integrante de la poblacion
    //Retorna un numero decimal como el resultado de la evaluacion
    //Debido a la propiedad Min(Z) = Max(-Z) se multiplica por -1
    //Este valor sera tomado como el fitness (aptitud) del integrante recibido.
    protected double evaluarFuncionObjetivo(Integrante integrante) {
        double x, y;
        double xi, yi, ri;
        double resX, resY, resR, res;

        x = integrante.getValX();
        y = integrante.getValY();
        
        resultado = 0;
        for(int i = 0; i < cantidadRestricciones; i++) {
            xi = restricciones[i].getXi();
            yi = restricciones[i].getYi();
            ri = restricciones[i].getRi();

            resX = Math.pow((x - xi), 2);
            resY = Math.pow((y - yi), 2);
            resR = Math.pow(ri, 2);

            res = resX + resY - resR;

            resultado += (-1) * Math.pow(res, 2);
        }

        return resultado;
    }

    private int cantidadRestricciones;
    private Restriccion[] restricciones;
    private double resultado;
}