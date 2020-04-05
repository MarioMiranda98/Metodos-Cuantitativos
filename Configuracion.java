public class Configuracion {
    public Configuracion(int numeroRestricciones, int tamX, int tamY, int numeroPoblacion, int errorMinimo, String archivo) {
        this.numeroRestricciones = numeroRestricciones;
        this.numeroPoblacion = numeroPoblacion;
        this.tamX = tamX;
        this.tamY = tamY;
        this.errorMinimo = errorMinimo;
        this.archivo = archivo;
    }

    public int getNumeroRestricciones() { return this.numeroRestricciones; }
    public int getTamX() { return this.tamX; }
    public int getTamY() { return this.tamY; }
    public int getNumeroPoblaion() { return this.numeroPoblacion; }
    public int getErrorMinimo() { return this.errorMinimo; }
    public String getNombreArchivo() { return this.archivo; }

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

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public void setErrorMinimo(int errorMinimo) {
        this.errorMinimo = errorMinimo;
    }

    private int numeroRestricciones;
    private int tamX, tamY;
    private int numeroPoblacion;
    private int errorMinimo;
    private String archivo;
}