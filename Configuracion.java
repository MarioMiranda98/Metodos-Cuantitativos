public class Configuracion {
    public Configuracion(int numeroRestricciones, int tamX, int tamY, int numeroPoblacion) {
        this.numeroRestricciones = numeroRestricciones;
        this.numeroPoblacion = numeroPoblacion;
        this.tamX = tamX;
        this.tamY = tamY;
    }

    public int getNumeroRestricciones() { return this.numeroRestricciones; }
    public int getTamX() { return this.tamX; }
    public int getTamY() { return this.tamY; }
    public int getNumeroPoblaion() { return this.numeroPoblacion; }

    public void setNumeroRestricciones(int numeroRestricciones) {
        this.numeroRestricciones = numeroRestricciones;
    }

    public void setTamX(int tamX) {
        this.tamX = tamX;
    }

    public void setTamY(int tamY) {
        this.tamY = tamY;
    }

    public void setNumeroPoblacion(int numeroPoblacion) {
        this.numeroPoblacion = numeroPoblacion;
    }

    public double calcularError(Restriccion[] r) {
        double xi, yi, ri, fo, op;
        xi = yi = ri = fo = 0;
        for(int i = 0; i < this.numeroRestricciones; i++) {
            xi = Math.pow(r[i].getXi(), 2);
            yi = Math.pow(r[i].getYi(), 2);
            ri = Math.pow(r[i].getRi(), 2);

            op = xi + yi - ri;
            fo += Math.pow(op, 2);
        }
    
        errorRelativo = (fo / this.numeroRestricciones) * ERROR;

        return errorRelativo;
    }

    private int numeroRestricciones;
    private int tamX, tamY;
    private int numeroPoblacion;
    private double errorRelativo;
    private final double ERROR = 0.025;
}