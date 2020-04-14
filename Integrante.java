public class Integrante {
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

    protected void mutar() {
        int posicion = (int) Math.floor((Math.random() * (0 - longitud) + (longitud)));
        double p = Math.random();
        char[] aux = new char[longitud];

        for(int i = 0; i < longitud; i++)
            aux[i] = this.binario.charAt(i);

        if(p > PROB_MUTACION) {
            if(aux[posicion] == '1')
                aux[posicion] = '0';
            else 
                aux[posicion] = '1';

            this.binario = new String(aux);

            this.setBinario(binario);
        }
    }

    protected double valorDecimalX(int limiteMenorX, int limiteMayorX, int mjx) {
        double exp = Math.pow(2, mjx);
        double fraccion = ((limiteMayorX - limiteMenorX) / (exp - 1));
        long decimal = binarioADecimal(bitsX, 0);

        double res = limiteMenorX + (decimal * fraccion);

        return res;
    }

    protected double valorDecimalY(int limiteMenorY, int limiteMayorY, int mjy) {
        double exp = Math.pow(2, mjy);
        double fraccion = ((limiteMayorY - limiteMenorY) / (exp - 1));
        long decimal = binarioADecimal(bitsY, bitsX);

        double res = limiteMenorY + (decimal * fraccion);

        return res;
    }

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