public class Restriccion {
    public Restriccion() {}

    public Restriccion(double xi, double yi, double ri) {
        this.xi = xi;
        this.yi = yi;
        this.ri = ri;
        cumplida = false;
    }

    public double getXi() { return this.xi; }
    public double getYi() { return this.yi; }
    public double getRi() { return this.ri; }
    public double getX() { return this.x; }
    public double getY() {return this.y; }
    public int getErrorMinimo() { return this.errorMinimo; }
    public boolean getCumplida() { return this.cumplida; }

    public void setXi(double xi) { this.xi = xi; }
    public void setYi(double yi) { this.yi = yi; }
    public void setRi(double ri) { this.ri = ri;}
    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
    public void setErrorMinimo(int errorMinimo) { this.errorMinimo = errorMinimo; }
    public void setCumplida(boolean cumplida) { this.cumplida = cumplida; }

    protected void evaluarRestriccion() {
        double resX, resY, resR;
        double res;

        resX = Math.pow((x - xi), 2);
        resY = Math.pow((y - yi), 2);
        resR = Math.pow(ri, 2);

        res = resX + resY - resR;

        if(res <= errorMinimo) 
            cumplida = true;
    }

    private double xi;
    private double yi;
    private double ri;
    private double x;
    private double y;
    private int errorMinimo;
    private boolean cumplida;
}