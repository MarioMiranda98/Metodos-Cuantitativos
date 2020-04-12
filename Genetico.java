
public class Genetico {
    public Genetico(Configuracion conf, Restriccion[] restricciones) {
        this.conf = conf;
        this.restricciones = restricciones;
        cantidadRestricciones = conf.getNumeroRestricciones();
        tamX = conf.getTamX();
        tamY = conf.getTamY();
        nombreArchivo = conf.getNombreArchivo();
        tamPoblacion = conf.getNumeroPoblaion();
        errorMinimo = conf.calcularError(restricciones);

        negX = negY = false;
        mjx = mjy = k = 0;
        escritor = new Escritor(nombreArchivo);
        tiempo = new Tiempo();
        
        obtenerLimites();
        longitudCadenaCromosomas = obtenerLongitudCadenaCromosomas();
        integrantes = new Integrante[tamPoblacion];
        
        for(int i = 0; i < cantidadRestricciones; i++)
            restricciones[i].setErrorMinimo(errorMinimo);
        
        for(int i = 0; i < tamPoblacion; i++)
            integrantes[i] = new Integrante(longitudCadenaCromosomas, 0, 0);
        
        z = new FuncionObjetivo(cantidadRestricciones, restricciones);
        generarIntegrantes();
        calcularFitness();
        new InterfazResultados(restricciones, integrantes[0], cantidadRestricciones);
    }

    private void obtenerLimites() {
        for(int i = 0; i < conf.getNumeroRestricciones(); i++) {
            if((restricciones[i].getXi()) < 0)
                negX = true;
            if((restricciones[i].getYi()) < 0)
                negY = true;
        }

        if(negX)
            limiteMenorX = (-1) * tamX;
        else 
            limiteMenorX = 0;

        if(negY)
            limiteMenorY = (-1) * tamY;
        else 
            limiteMenorX = 0;

        limiteMayorX = tamX; 
        limiteMayorY = tamY;
    }

    private int obtenerLongitudCadenaCromosomas() {
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

    private String generarBinario() {
        char[] aux = new char[longitudCadenaCromosomas];

        for(int i = 0; i < longitudCadenaCromosomas; i++) {
            if(Math.floor((Math.random() * 100) % 2) == 0)
                aux[i] = '0';
            else 
                aux[i] = '1'; 
        }

        return new String(aux);
    }

    private void generarIntegrantes() {
        double x, y;
        for(int i = 0; i < tamPoblacion; i++) {
            String binario = generarBinario();
            integrantes[i].setBinario(binario);
            integrantes[i].setBinarioPadre(binario);
            integrantes[i].setBitsX(mjx);
            integrantes[i].setBitsY(mjy);
            x = integrantes[i].valorDecimalX(limiteMenorX, limiteMayorX, mjx);
            y = integrantes[i].valorDecimalY(limiteMenorY, limiteMayorY, mjy);
            integrantes[i].setValX(x);
            integrantes[i].setValY(y);

            for(int j = 0; j < cantidadRestricciones; j++) {
                restricciones[j].setX(x);
                restricciones[j].setY(y);

                restricciones[j].evaluarRestriccion();
                if(!restricciones[j].getCumplida())
                    k++;
            }

            if(k > 0)
                i--;
        }
    }

    private void calcularFitness() {
        for(int i = 0; i < tamPoblacion; i++) {
            integrantes[i].setFitness(z.evaluarFuncionObjetivo(integrantes[i]));
        }
    }

    //TODO:Implementar Seleccion 

    private Configuracion conf;
    private Restriccion[] restricciones;
    private final int MAX_ITERACIONES = 100;
    private final int MAX_TIEMPO = 60;
    private int cantidadRestricciones;
    private final int BITS_PRESICION = 4;
    private int longitudCadenaCromosomas;
    private int tamX, tamY;
    private int limiteMayorX, limiteMenorX;
    private int limiteMayorY, limiteMenorY;
    private String nombreArchivo;
    private int tamPoblacion;
    private double errorMinimo;
    private boolean negX, negY;
    private Integrante[] integrantes;
    private int mjx, mjy, k;
    private FuncionObjetivo z;
    private Escritor escritor;
    private Tiempo tiempo;
}