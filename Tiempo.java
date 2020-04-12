public class Tiempo extends Thread {
    public Tiempo() {
        i = 0;
        b = true;
    }

    public int getTiempo() { return this.i; }

    public void run() {
        while(b) {
            i += 1;

            try {
                Thread.sleep(1000);
            } catch(Exception e) { e.printStackTrace(); }
        }
    }

    private int i;
    public boolean b;
}