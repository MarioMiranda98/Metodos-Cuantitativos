/*
    Clase donde se toma el timepo de ejecucion.
    
    Debido a que los programas normales son secuenciales y queremos 
    que el tiempo se cuente en paralelo a la ejecucion.

    Por eso en esta clase lo que hacemos es implementar la interfaz Runnable
    que nos permitira hacer el paralelismo que deseamos.
*/
public class Tiempo implements Runnable{
    public Tiempo(Genetico g) {
        i = 0;
        this.g = g;
    }

    public int getTiempo() { return this.i; }
    public void setTiempo(int i) { this.i = i; }

    //Metodo que la interfaz nos obliga a implementar
    //Aqui es donde se coloca el codigo a ejecutarse en "paralelo"
    public void run() {
        while(g.getBandera()) { //Obtenemos el valor de la bandera de ejecuciones
            i += 1;//Incrementamos el valor del contador

            try {
                Thread.sleep(1000);//Hacemos que la ejecucion "duerma" (se detenga) 1 segundo
            } catch(Exception e) { e.printStackTrace(); }

            if(i > g.MAX_TIEMPO)//Verificamos que el contador no pase el tiempo maximo establecido
                g.setBandera(false);
        }
    }

    private int i;
    private Genetico g;
}