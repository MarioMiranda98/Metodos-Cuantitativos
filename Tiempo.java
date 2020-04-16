public class Tiempo implements Runnable{
    public Tiempo(Genetico g) {
        i = 0;
        this.g = g;
    }

    public int getTiempo() { return this.i; }
    public void setTiempo(int i) { this.i = i; }

    public void run() {
        while(g.getBandera()) {
            i += 1;

            try {
                Thread.sleep(1000);
            } catch(Exception e) { e.printStackTrace(); }

            if(i > g.MAX_TIEMPO)
                g.setBandera(false);
        }
    }

    private int i;
    private Genetico g;
}