/*
    Clase donde se modela un integrante de la poblacion.
*/
public class Integrante {
    //Constructor de la clase, donde recibe como parametros 
    //La longitud de la cadena de cromosomas y la cantidad 
    //de bits "X" y "Y" que conforman la cadena.
    public Integrante(int longitud, int bitsX, int bitsY) {
        this.longitud = longitud;
        this.bitsX = bitsX;
        this.bitsY = bitsY;
    }

    public int getLongitud() { return this.longitud; }
    public int getBitsX() { return this.bitsX; }
    public int getBitsY() { return this.bitsY; }
    public String getBinario() { return this.binario; }
    public double getFitness() { return this.fitness; }
    public double getValX() { return this.valX; }
    public double getValY() { return this.valY; }

    public void setLongitud(int longitud) { this.longitud = longitud; }
    public void setBitsX(int bitsX) { this.bitsX = bitsX; }
    public void setBitsY(int bitsY) { this.bitsY = bitsY; }
    public void setBinario(String binario) { this.binario = binario; } 
    public void setFitness(double fitness) { this.fitness = fitness; }
    public void setValX(double valX) { this.valX = valX; }
    public void setValY(double valY) { this.valY = valY; }

    //Metodo que se encarga de mutar el cromosoma.
    //Retorna un entero 1 si muto, 0 si no existio cambio
    protected int mutar() {
        int muto = 0;
        int posicion = (int) Math.floor((Math.random() * (0 - longitud) + (longitud))); //Calculamos una posicion del bit a cambiar
        double p = Math.random();//Obtenemos un numero entre 0.0 y 1 que servira para ver si pasa la probabilidad de mutacion
        char[] aux = new char[longitud];

        for(int i = 0; i < longitud; i++)
            aux[i] = this.binario.charAt(i);

        if(p > PROB_MUTACION) {//Checamos si el integrante es apto para mutar
            if(aux[posicion] == '1')//Checamos el valor actual de la posicion para ver como mutarlo
                aux[posicion] = '0';
            else 
                aux[posicion] = '1';

            this.binario = new String(aux);

            this.setBinario(binario);//Colocamos el nuevo binario
            muto = 1;//Cambiamos el valor que indica la mutacion
        }

        return muto;//Regresamos el indicador
    }

    //Metodo que se encarga de convertir a valor decimal la parte de la cadena 
    //correspondiente a los bits de X.
    //Recibe los limites como parametros y la cantidad de bits de X.
    //Retorna el valor decimal.
    protected double valorDecimalX(int limiteMenorX, int limiteMayorX, int mjx) {
        double exp = Math.pow(2, mjx);
        double fraccion = ((limiteMayorX - limiteMenorX) / (exp - 1));
        long decimal = binarioADecimal(bitsX, 0);

        double res = limiteMenorX + (decimal * fraccion);

        return res;
    }

    //Metodo que se encarga de convertir a valor decimal la parte de la cadena 
    //correspondiente a los bits de Y.
    //Recibe los limites como parametros y la cantidad de bits de Y.
    //Retorna el valor decimal.
    protected double valorDecimalY(int limiteMenorY, int limiteMayorY, int mjy) {
        double exp = Math.pow(2, mjy);
        double fraccion = ((limiteMayorY - limiteMenorY) / (exp - 1));
        long decimal = binarioADecimal(bitsY, bitsX);

        double res = limiteMenorY + (decimal * fraccion);

        return res;
    }

    //Metodo que se encarga de convertir una cadena binaria a decimal
    //Recibe como parametros el inicio y fin de la cadena
    //Retorna el valor decimal de su binario correspondiente.
    private long binarioADecimal(int bits, int inicio) {
        char[] aux = new char[bits];
        int suma = 0;

        for(int i = bits - 1, j = inicio; i >= 0; i--)
            aux[i] = binario.charAt(j++);

        for(int i = 0; i < bits; i++) {
            int bin = (int) (aux[i] - 48);
            if(bin == 0)
                suma += 0;
            else
                suma += bin * (int) (Math.pow(2, i)); 
        }

        return suma;
    }

    private int longitud;
    private int bitsX;
    private int bitsY;
    private String binario;
    private double fitness;
    private double valX, valY;
    private final double PROB_MUTACION = 0.4;
}