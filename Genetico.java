import java.util.Random;
import java.util.stream.IntStream;

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
        mergeSort = new MergeSort();
        
        obtenerLimites();
        longitudCadenaCromosomas = obtenerLongitudCadenaCromosomas();
        integrantes = new Integrante[tamPoblacion];
        
        for(int i = 0; i < cantidadRestricciones; i++)
            restricciones[i].setErrorMinimo(errorMinimo);
        
        for(int i = 0; i < tamPoblacion; i++)
            integrantes[i] = new Integrante(longitudCadenaCromosomas, 0, 0);
        
        z = new FuncionObjetivo(cantidadRestricciones, restricciones);

        iniciarAlgoritmo();
        new InterfazResultados(restricciones, integrantesFinal[MAX_ITERACIONES - 1], conf);
    }

    private void iniciarAlgoritmo() {
        integrantesFinal = new Integrante[MAX_ITERACIONES];
        generarIntegrantes();
        for(int i = 0; i < MAX_ITERACIONES; i++) {
            calcularFitness();
            seleccion();
            mergeSort.ordenamiento(integrantes, 0, (tamPoblacion - 1));
            integrantesFinal[i] = integrantes[tamPoblacion - 1];
        }

        mergeSort.ordenamiento(integrantesFinal, 0, (MAX_ITERACIONES - 1));
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
            if(Math.floor((Math.random() * 500) % 2) == 0)
                aux[i] = '0';
            else 
                aux[i] = '1'; 
        }

        return new String(aux);
    }

    private void generarIntegrantes() {
        double x, y;
        for(int i = 0; i < tamPoblacion; i++) {
            k = 0;
            String binario = generarBinario();
            integrantes[i].setBinario(binario);
            integrantes[i].setBitsX(mjx);
            integrantes[i].setBitsY(mjy);
            x = integrantes[i].valorDecimalX(limiteMenorX, limiteMayorX, mjx);
            y = integrantes[i].valorDecimalY(limiteMenorY, limiteMayorY, mjy);
            integrantes[i].setValX(x);
            integrantes[i].setValY(y);

            k = Restriccion.evaluarRestricciones(restricciones, cantidadRestricciones, x, y);

            if(k > 0)
                i--;
        }
    }

    private void calcularFitness() {
        for(int i = 0; i < tamPoblacion; i++) {
            integrantes[i].setFitness(z.evaluarFuncionObjetivo(integrantes[i]));
        }
    }

    private void seleccion() {
        Integrante[] tmpInt = new Integrante[tamPoblacion];
        int[] numerosAleatorios = new int[tamPoblacion];
        Integrante[] torneo = new Integrante[TAM_TORNEO];
        int numTorneos = tamPoblacion / TAM_TORNEO;
		
		numerosAleatorios = IntStream.rangeClosed(0, (tamPoblacion - 1)).toArray();

		Random r = new Random();

		for(int i = numerosAleatorios.length; i > 0; i--) {
			int posicion = r.nextInt(i);
			int tmp = numerosAleatorios[i - 1];
			numerosAleatorios[i - 1] = numerosAleatorios[posicion];
			numerosAleatorios[posicion] = tmp;
		}

        for(int i = 0; i < numTorneos; i++) {
            for(int j = 0; j < TAM_TORNEO; j++)
                torneo[j] = integrantes[numerosAleatorios[j]];
            mergeSort.ordenamiento(torneo, 0, (TAM_TORNEO - 1));

            tmpInt[i] = torneo[0];
        }

        cruzar(tmpInt, numTorneos);
        mutacion();
    }

    private void cruzar(Integrante[] tmp, int numPadres) {
        char[] aux1 = new char[longitudCadenaCromosomas];
        char[] aux2 = new char[longitudCadenaCromosomas];
        char[] nuevoBinario = new char[longitudCadenaCromosomas];
        Integrante hijo = new Integrante(longitudCadenaCromosomas, mjx, mjy);
        int[] numerosAleatorios = new int[numPadres];
		
		numerosAleatorios = IntStream.rangeClosed(0, (numPadres - 1)).toArray();

		Random r = new Random();

		for(int i = numerosAleatorios.length; i > 0; i--) {
			int posicion = r.nextInt(i);
			int tmp2 = numerosAleatorios[i - 1];
			numerosAleatorios[i - 1] = numerosAleatorios[posicion];
			numerosAleatorios[posicion] = tmp2;
		}


        for(int i = 0; i < (tamPoblacion - numPadres); i++) {
            int a1 = (int) (Math.floor(Math.random() * (0 - numPadres) + numPadres));
            int a2 = (int) (Math.floor(Math.random() * (0 - numPadres) + numPadres));

            for(int l = 0; l < longitudCadenaCromosomas; l++) {
                aux1[l] = tmp[numerosAleatorios[a1]].getBinario().charAt(l);
                aux2[l] = tmp[numerosAleatorios[a2]].getBinario().charAt(l);
            }

            int puntoCruce = (int) (Math.floor(Math.random() * (3 - (longitudCadenaCromosomas - 4)) + (longitudCadenaCromosomas - 4)));
            
            for(int j = 0; j < puntoCruce; j++) 
                nuevoBinario[j] = aux1[j];
            
            for(int j = puntoCruce; j < longitudCadenaCromosomas; j++)
                nuevoBinario[j] = aux2[j];

            hijo.setBinario(new String(nuevoBinario));
            double x = hijo.valorDecimalX(limiteMenorX, limiteMayorX, mjx);
            double y = hijo.valorDecimalY(limiteMenorY, limiteMayorY, mjy);
            hijo.setValX(x);            
            hijo.setValY(y);            

            int k = Restriccion.evaluarRestricciones(restricciones, cantidadRestricciones, x, y);

            if(k > 0)
                i--;
            else 
                tmp[i + (numPadres)] = hijo;
        }

        integrantes = tmp;
    }

    private void mutacion() {
        for(int i = 0; i < tamPoblacion; i++) {
            integrantes[i].mutar();
            
            double x = integrantes[i].valorDecimalX(limiteMenorX, limiteMayorX, mjx);
            double y = integrantes[i].valorDecimalY(limiteMenorX, limiteMayorX, mjx);
            
            integrantes[i].setValX(x);
            integrantes[i].setValY(y);

            int k = Restriccion.evaluarRestricciones(restricciones, cantidadRestricciones, x, y);

            if(k > 0)
                i--;
        }
    }

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
    private MergeSort mergeSort;
    private final int TAM_TORNEO = 5;
    private Integrante[] integrantesFinal;
}