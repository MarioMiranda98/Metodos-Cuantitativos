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
    public String getBinarioPadre() { return this.binarioPadre; }
    public int getIdGeneracion() { return this.idGeneracion; }
    public double getFitness() { return this.fitness; }

    public void setLongitud(int longitud) { this.longitud = longitud; }
    public void setBitsX(int bitsX) { this.bitsX = bitsX; }
    public void setBitsY(int bitsY) { this.bitsY = bitsY; }
    public void setBinario(String binario) { this.binario = binario; } 
    public void setBinarioPadre(String binarioPadre) { this.binarioPadre = binarioPadre; }
    public void setIdGeneracion(int idGeneracion) { this.idGeneracion = idGeneracion; }
    public void setFitness(double fitness) { this.fitness = fitness; }

    protected String mutar() {
        int posicion = (int) Math.floor((Math.random() * (0 - longitud) + (longitud)));
        char[] aux = new char[longitud];

        for(int i = 0; i < longitud; i++)
            aux[i] = this.binario.charAt(i);

        if(aux[posicion] == '1')
            aux[posicion] = '0';
        else 
            aux[posicion] = '1';

        this.binario = new String(aux);

        return binario;
    }

    protected double valorDecimalX(int limiteMenorX, int limiteMayorX, int mjx) {
        double exp = Math.pow(2, mjx);
        double fraccion = ((limiteMayorX - limiteMenorX) / (exp - 1));
        int decimal = binarioADecimal(this.bitsX, 0);

        double res = limiteMenorX + (decimal * fraccion);

        return res;
    }

    protected double valorDecimalY(int limiteMenorY, int limiteMayorY, int mjy) {
        double exp = Math.pow(2, mjy);
        double fraccion = ((limiteMayorY - limiteMenorY) / (exp - 1));
        int decimal = binarioADecimal(this.bitsY, this.bitsX);

        double res = limiteMenorY + (decimal * fraccion);

        return res;
    }

    private int binarioADecimal(int bits, int inicio) {
        char[] aux = new char[bits];
        int suma = 0;

        for(int i = bits - 1, j = 0; i >= 0; i--)
            aux[i] = this.binario.charAt(j++);

        for(int i = 0; i < bits; i++) {
            int bin = (int) (aux[i]);
            suma += bin * (int) (Math.pow(2, i)); 
        }

        return suma;
    }

    private int longitud;
    private int bitsX;
    private int bitsY;
    private String binario;
    private String binarioPadre;
    private int idGeneracion;
    private double fitness;
}