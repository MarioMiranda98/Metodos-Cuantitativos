/*
    Clase donde guardamos el planteamiento incial del problema, 
    numero de restricciones, tamaño del primer cuadrante y el 
    tamaño de la poblacion.
*/
public class Configuracion {
    public Configuracion(int numeroPoblacion) {
        this.numeroPoblacion = numeroPoblacion;
    }

    public int getNumeroPoblaion() { return this.numeroPoblacion; }

    public void setNumeroPoblacion(int numeroPoblacion) {
        this.numeroPoblacion = numeroPoblacion;
    }

    //Metodo donde calculamos el error absoluto para las restricciones
    //Recibe como parametro las restricciones, ya que como sabemos 
    //la funcion objetivo es la suma al cuadrado de cada restriccion
    //Retorna un double con el error calculado.
    public double calcularError(Restriccion[] r) {
        double xi, yi, ri, fo, op;
        xi = yi = ri = fo = 0;
        for(int i = 0; i < this.CANTIDAD_RESTRICCIONES; i++) {
            xi = Math.pow(r[i].getXi(), 2);
            yi = Math.pow(r[i].getYi(), 2);
            ri = Math.pow(r[i].getRi(), 2);

            op = xi + yi - ri;
            fo += Math.pow(op, 2);
        }
        
        fo = Math.sqrt(fo);
    
        errorAbsoulto = (fo / this.CANTIDAD_RESTRICCIONES) * ERROR;
        errorAbsoulto /= CORRECTOR;

        return errorAbsoulto;
    }

    protected final int CANTIDAD_RESTRICCIONES = 4;
    private final double CORRECTOR = 1000000;
    private int numeroPoblacion;
    private double errorAbsoulto;
    private final double ERROR = 0.01;
}