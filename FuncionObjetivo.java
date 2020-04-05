public class FuncionObjetivo {
    public FuncionObjetivo(int cantidadRestricciones, Restriccion[] restricciones) {
        this.cantidadRestricciones = cantidadRestricciones;
        this.restricciones = restricciones;
    }

    public int getCantidadRestricciones() { return this.cantidadRestricciones; }
    public Restriccion[] getRestricciones() { return this.restricciones; }

    public void setCantidadRestricciones(int cantidadRestricciones) {
        this.cantidadRestricciones = cantidadRestricciones;
    }

    public void setRestricciones(Restriccion[] restricciones) {
        this.restricciones = restricciones;
    }

    private int cantidadRestricciones;
    private Restriccion[] restricciones;
    private double resultado;
}