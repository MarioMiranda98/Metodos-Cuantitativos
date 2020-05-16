/*
    Clase donde se crea el modelo de una restriccion,
    esta hecha especialmente para restricciones pertenecientes
    al problema de trilateracion.
*/

public class Restriccion {
    public Restriccion() {}

    //Constructor de la clase
    //Recibimos los parametros para cada componente Ej: (x - 3.3) 
    //Donde xi, yi y ri, son las constantes
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
    public double getErrorMinimo() { return this.errorMinimo; }
    public boolean getCumplida() { return this.cumplida; }

    public void setXi(double xi) { this.xi = xi; }
    public void setYi(double yi) { this.yi = yi; }
    public void setRi(double ri) { this.ri = ri;}
    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
    public void setErrorMinimo(double errorMinimo) { this.errorMinimo = errorMinimo; }
    public void setCumplida(boolean cumplida) { this.cumplida = cumplida; }

    //Metodo donde se evalua si una restriccion se satisface
    //Si se cumple la bandera nombrada "cumplida" coloca su valor en verdadero
    //No devuelve ningun valor
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

    //Metodo donde se evalua un conjunto de restricciones 
    //Si la restriccion no lo cumple la variable k aumenta su valor
    //Devuelve un entero el cual indica el numero de restricciones no satisfechas - 1
    protected static int evaluarRestricciones(Restriccion[] restricciones, int cantidadRestricciones, double x, double y) {
        int k = 0;

        for(int j = 0; j < cantidadRestricciones; j++) {
            restricciones[j].setX(x);
            restricciones[j].setY(y);

            restricciones[j].evaluarRestriccion();
            if(!restricciones[j].getCumplida())
                k++;
        }

        return k;
    }

    private double xi;
    private double yi;
    private double ri;
    private double x;
    private double y;
    private double errorMinimo;
    private boolean cumplida;
}