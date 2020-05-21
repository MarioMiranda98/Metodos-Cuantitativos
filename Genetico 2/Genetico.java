/*
    Clase donde se lleva a cabo el algortimo genetico
*/
import java.util.Random;
import java.util.stream.IntStream;

public class Genetico {
    //Constructor de la clase
    //Recibe un objeto de tipo Configuracion con el planteamiento del problema
    //y un arreglo de objetos de Restriccion donde vienen las restricciones "armadas"
    public Genetico(Configuracion conf, Restriccion[] restricciones) {
        this.conf = conf;
        this.restricciones = restricciones;
        tamPoblacion = conf.getNumeroPoblaion();
        errorMinimo = conf.calcularError(restricciones);
        cantidadRestricciones = conf.CANTIDAD_RESTRICCIONES;

        bandera = true;
        mjx = mjy = k = 0;
        iteracion = 0;
        tiempo = new Tiempo(Genetico.this);
        mergeSort = new MergeSort();
        
        obtenerLimites();
        longitudCadenaCromosomas = obtenerLongitudCadenaCromosomas();
        integrantes = new Integrante[tamPoblacion];
        
        for(int i = 0; i < cantidadRestricciones; i++)
            restricciones[i].setErrorMinimo(errorMinimo);
        
        for(int i = 0; i < tamPoblacion; i++)
            integrantes[i] = new Integrante(longitudCadenaCromosomas, 0, 0);
        
        z = new FuncionObjetivo(cantidadRestricciones, restricciones);

        tiempo.setTiempo(0);
        iniciarAlgoritmo();
        new Resultados(restricciones, integrantesFinal[0], conf);//Creamos el objeto de la interfaz de resultados.
    }

    public boolean getBandera() { return this.bandera; }
    public void setBandera(boolean bandera) { this.bandera = bandera; }

    //Metodo donde se incia el algoritmo
    //No recibe parametros, tampoco retorna.
    private void iniciarAlgoritmo() {
        integrantesFinal = new Integrante[MAX_ITERACIONES];//Creamos un arreglo para almacenar los mejores integrantes de cada iteracion
        generarIntegrantes();//Generamos los integrantes de la primera generacion
        new Thread(tiempo).start();//Hacemos que el tiempo comience a correr.

        while((iteracion < MAX_ITERACIONES) && (this.getBandera())) {//La condicion de paro es que no sobrepase el numero de iteraciones maximas y tampoco el tiempo de ejecucion, ambas deben cumplirse.
            System.out.println("Iteracion: " + (iteracion + 1));
            calcularFitness();//Calculamos el fitness de cada individuo de la poblacion
            seleccion();//Seleccionamos los integrantes de cada poblacion (seleccion por torneo)
            mergeSort.ordenamiento(integrantes, 0, (tamPoblacion - 1));//Ordenamos y guardamos al mejor de cada iteracion (generacion)
            integrantesFinal[iteracion] = integrantes[0];
            iteracion += 1;
        }

        //Mostramos los mejores integrantes de cada generacion
        for(int i = 0; i < iteracion; i++) { 
            System.out.println("Mejor Integrante iteracion " + (i + 1));
            System.out.println("Binario: " + integrantesFinal[i].getBinario());
            System.out.println("X: " + integrantesFinal[i].getValX() + " Y: " + integrantesFinal[i].getValY());
            System.out.println("Fitness (Z): " + integrantesFinal[i].getFitness());
            System.out.println("");
        }

        //Los ordenamos y obtenemos el mejor integrante
        //Mostramos sus datos
        mergeSort.ordenamiento(integrantesFinal, 0, (iteracion - 1));

        System.out.println("Mejor Integrante");
        System.out.println("Binario: " + integrantesFinal[0].getBinario());
        System.out.println("X: " + integrantesFinal[0].getValX() + " Y: " + integrantesFinal[0].getValY());
        System.out.println("Fitness (Z): " + integrantesFinal[0].getFitness());
        System.out.println("");        
    }

    //Metodo que se encarga de obtener los limites que puede tomar cada variable
    //No recibe parametros, ni retorna.
    //Se verifica que en las restricciones la existencia de coordenadas negativas,
    //Sino las hay el limite inferior se toma como 0 automaticamente.
    private void obtenerLimites() {
        double mayorX = 0;
        double mayorY = 0;
        double menorX = 999999;
        double menorY = 999999;
        double tempX, tempY;

        for(int i = 0; i < conf.CANTIDAD_RESTRICCIONES; i++) {
            tempX = restricciones[i].getXi();
            tempY = restricciones[i].getYi();

            if(i == 0)
                mayorX = tempX;

            if(tempX > mayorX)
                mayorX = tempX;
            if(tempY > mayorY)
                mayorY = tempY;       
                
            if(tempX < menorX)
                menorX = tempX;
            if(tempY < menorY)
                menorY = tempY;
        }

        limiteMayorX = (int) mayorX;
        limiteMenorX = (int) menorX;
        limiteMayorY = (int) mayorY;
        limiteMenorY = (int) (menorY - 1);
    }

    //Metodo que se encarga de calcular la longitud de la cadena de cromosomas
    //No recibe parametros, no tiene retorno.
    private int obtenerLongitudCadenaCromosomas() {
        int limX;
        double numerador, denominador;
        double p;

        p = Math.pow(10, BITS_PRESICION);
        denominador = Math.log10(2);

        limX = limiteMayorX - limiteMenorX;
        numerador = Math.log10(limX * p);
        
        mjx = (int) (Math.ceil(numerador / denominador));

        numerador = Math.log10(p);

        mjy = (int) (Math.ceil(numerador / denominador));

        return (mjx + mjy);
    }

    //Metodo que se encarga de generar la cadena binaria de cromosomas.
    //No recibe parametros.
    //Retorna una cadena binaraia.
    //La cadena se genera sacando el modulo de un numero aleatorio entre 0 y 500
    //Si el modulo es 0 se coloca "0" en la cadena, caso contrario se coloca un "1"
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

    //Metodo que se encarga de generar los integrantes de la poblacion
    //No recibe parametros, no tiene retorno
    //Una vez obtenido el binario se coloca en el objeto junto con sus bits
    //y su valor en decimal, posteriormente se procede a verificar que cumpla las restricciones
    //Si k es mayor que 0 significa que no cumple todas, por lo cual se repite el mismo proceso
    //hasta que k = 0.
    //(Para ver mas a detalle acerca de k, ver la clase Restriccion)
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

            System.out.println(binario);
            System.out.println("X: " + integrantes[i].getValX() + " Y: " + integrantes[i].getValY());
            
            k = Restriccion.evaluarRestricciones(restricciones, cantidadRestricciones, x, y);

            if(k > 0)
                i--;
        }
    }

    //Metodo que se encarga de calcular el fitness de los integrantes de la poblacion
    //No recibe parametros, No tiene retorno
    private void calcularFitness() {
        for(int i = 0; i < tamPoblacion; i++) {
            integrantes[i].setFitness(z.evaluarFuncionObjetivo(integrantes[i]));
            System.out.println("Binario: " + integrantes[i].getBinario());
            System.out.println("X: " + integrantes[i].getValX() + " Y: " +integrantes[i].getValY());
            System.out.println("Fitness (Z): " + integrantes[i].getFitness());
        }
    }

    //Metodo que se encarga de hacer la seleccion 
    //La seleccion se hace mediante torneo
    //No recibe parametros, No tiene retorno
    //El tamaño del torneo es de 5, ya que con 3
    //la presion selectiva era mucha y se caia en el elitismo
    //con mucha mas frecuencia.
    private void seleccion() {
        Integrante[] tmpInt = new Integrante[tamPoblacion];
        int[] numeros = new int[tamPoblacion];
        Integrante[] torneo = new Integrante[TAM_TORNEO];
        int numTorneos = tamPoblacion / TAM_TORNEO;
		
        numeros = numerosAleatorios(0, tamPoblacion);//Obtenemos un arreglo con numeros aleatorios sin repeticion

        //Armamos el torneo 
        for(int i = 0; i < numTorneos; i++) {
            for(int j = 0; j < TAM_TORNEO; j++) {
                torneo[j] = integrantes[numeros[j]];
            }
            mergeSort.ordenamiento(torneo, 0, (TAM_TORNEO - 1));//Obtenemos el mejor miembro

            tmpInt[i] = torneo[0];//Lo guardamos en un arreglo temporal
        }

        cruzar(tmpInt, numTorneos);
        mutacion();
    }

    //Metodo que se encarga de cruzar los cromosomas para dar un nuevo hijo
    //Recibe como parametros una poblacion temporal donde estan guardados los mejores padres
    //y el numero de padres.
    //No tiene retorno.
    private void cruzar(Integrante[] tmp, int numPadres) {
        char[] aux1 = new char[longitudCadenaCromosomas];
        char[] aux2 = new char[longitudCadenaCromosomas];
        char[] nuevoBinario = new char[longitudCadenaCromosomas];
        Integrante hijo = new Integrante(longitudCadenaCromosomas, mjx, mjy);
        int[] numeros = new int[numPadres];
        
        numeros = numerosAleatorios(0, numPadres);//Obtenemos un arreglo con numeros aleatorios sin repeticion

        System.out.println("Cruce");
        System.out.println("");
        for(int i = 0; i < (tamPoblacion - numPadres); i++) {
            //Obtenemos los padres que deseamos cruzar
            int a1 = (int) (Math.floor(Math.random() * (0 - numPadres) + numPadres));
            int a2 = (int) (Math.floor(Math.random() * (0 - numPadres) + numPadres));

            for(int l = 0; l < longitudCadenaCromosomas; l++) {
                aux1[l] = tmp[numeros[a1]].getBinario().charAt(l);
                aux2[l] = tmp[numeros[a2]].getBinario().charAt(l);
            }

            //Obtenemos el punto de cruce
            int puntoCruce = (int) (Math.floor(Math.random() * (3 - (longitudCadenaCromosomas - 4)) + (longitudCadenaCromosomas - 4)));
            
            //Generamos el cruce y establecemos los parametros al hijo
            for(int j = 0; j < puntoCruce; j++) 
                nuevoBinario[j] = aux1[j];
            
            for(int j = puntoCruce; j < longitudCadenaCromosomas; j++)
                nuevoBinario[j] = aux2[j];

            hijo.setBinario(new String(nuevoBinario));
            double x = hijo.valorDecimalX(limiteMenorX, limiteMayorX, mjx);
            double y = hijo.valorDecimalY(limiteMenorY, limiteMayorY, mjy);
            hijo.setValX(x);            
            hijo.setValY(y);            

            //Verificamos que cumpla las restricciones, si lo hace mostramos su informacion sino
            //repetimos el proceso.
            int k = Restriccion.evaluarRestricciones(restricciones, cantidadRestricciones, x, y);

            if(k > 0)
                i--;
            else {
                tmp[i + (numPadres)] = hijo;
                tmp[i + (numPadres)].setFitness(z.evaluarFuncionObjetivo(tmp[i + (numPadres)]));
                System.out.println("Nuevo hijo");
                System.out.println("Binario: " + hijo.getBinario());
                System.out.println("X: " + hijo.getValX() + " Y: " + hijo.getValY());
                System.out.println("Fitness: " + integrantes[i].getFitness());
            }
        }

        integrantes = tmp;//Asignamos la poblacion temporal a la poblacion oficial.
    }

    //Metodo que se encarga de mutar los integrantes de la poblacion 
    //No recibe parametros, No tiene retorno.
    private void mutacion() {
        for(int i = 0; i < tamPoblacion; i++) {
            int m = integrantes[i].mutar();

            if(m == 1) { //Significa que muto, por lo cual establecemos sus nuevos valores
                double x = integrantes[i].valorDecimalX(limiteMenorX, limiteMayorX, mjx);
                double y = integrantes[i].valorDecimalY(limiteMenorX, limiteMayorX, mjx);
            
                integrantes[i].setValX(x);
                integrantes[i].setValY(y);
                
                //Evaluamos las restricciones, si las cumple mostramos la informacion sino
                //se repite el proceso.
                int k = Restriccion.evaluarRestricciones(restricciones, cantidadRestricciones, x, y);

                if(k > 0)
                    i--;
                else {
                    integrantes[i].setFitness(z.evaluarFuncionObjetivo(integrantes[i]));
                    System.out.println("");
                    System.out.println("Integrante mutado");
                    System.out.println("Binario: " + integrantes[i].getBinario());
                    System.out.println("X: " + integrantes[i].getValX() + " Y: " + integrantes[i].getValY());
                    System.out.println("Fitness: " + integrantes[i].getFitness());
                }
            }
        }
    }

    //Metodo que se encarga de generar numeros aleatorios siguiendo 
    //el algoritmo de Fisher-Yates.
    //Recibe dos enteros que son los limites de los numeros aleatorios.
    //Regresa un arreglo de numeros desordenados entre los limites establecidos
    private int[] numerosAleatorios(int inicio, int fin) {
        int[] numerosAleatorios = new int[fin];
		
		numerosAleatorios = IntStream.rangeClosed(inicio, (fin - 1)).toArray();

		Random r = new Random();

		for(int i = numerosAleatorios.length; i > 0; i--) {
			int posicion = r.nextInt(i);
			int tmp = numerosAleatorios[i - 1];
			numerosAleatorios[i - 1] = numerosAleatorios[posicion];
			numerosAleatorios[posicion] = tmp;
        }
        
        return numerosAleatorios;
    }

    private Configuracion conf;
    private Restriccion[] restricciones;
    private final int MAX_ITERACIONES = 100;
    public final int MAX_TIEMPO = 120;
    private int cantidadRestricciones;
    private final int BITS_PRESICION = 4;
    private int longitudCadenaCromosomas;
    private int limiteMayorX, limiteMenorX;
    private int limiteMayorY, limiteMenorY;
    private int tamPoblacion;
    private double errorMinimo;
    private Integrante[] integrantes;
    private int mjx, mjy, k;
    private FuncionObjetivo z;
    private Tiempo tiempo;
    private MergeSort mergeSort;
    private final int TAM_TORNEO = 5;
    private Integrante[] integrantesFinal;
    private boolean bandera;
    private int iteracion;
}