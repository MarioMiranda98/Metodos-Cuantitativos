
public class Genetico {
    public Genetico(Configuracion conf, Restriccion[] restricciones) {
        this.conf = conf;
        this.restricciones = restricciones;
        cantidadRestricciones = conf.getNumeroRestricciones();
        tamX = conf.getTamX();
        tamY = conf.getTamY();
        nombreArchivo = conf.getNombreArchivo();
        tamPoblacion = conf.getNumeroPoblaion();
        errorMinimo = conf.getErrorMinimo();

        for(int i = 0; i < cantidadRestricciones; i++)
            restricciones[i].setErrorMinimo((int) (Math.pow(errorMinimo, 2)));
    }

    private void obtenerLimites(int tamX, int tamY) {
        limiteMayorX = tamX;
        limiteMenorX = (-1) * tamX;
        limiteMayorY = tamY;
        limiteMenorY = (-1) * tamY;
    }

    private int obtenerLongitudCadenaCromosomas() {
        int mjx, mjy;
        int limX, limY;
        double numerador, denominador;
        double p;

        p = Math.pow(10, BITS_PRESICION);
        denominador = Math.log10(2);

        limX = limiteMayorX - limiteMenorX;
        numerador = Math.log10(limX * p);
        
        mjx = (int) (Math.ceil(numerador / denominador));

        limY = limiteMayorY - limiteMenorY;
        numerador = Math.log10(limY * p);

        mjy = (int) (Math.ceil(numerador / denominador));

        return (mjx + mjy);
    }

    private Configuracion conf;
    private Restriccion[] restricciones;
    private final int MAX_ITERACIONES = 100;
    private int cantidadRestricciones;
    private final int BITS_PRESICION = 4;
    private int longitudCadenaCromosomas;
    private int tamX, tamY;
    private int limiteMayorX, limiteMenorX;
    private int limiteMayorY, limiteMenorY;
    private String nombreArchivo;
    private int tamPoblacion;
    private int errorMinimo;
}