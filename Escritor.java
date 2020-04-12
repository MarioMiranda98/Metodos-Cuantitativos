import java.io.*;

public class Escritor {
    public Escritor(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
        pw = null;
        archivo = null;
        f = new File("./Resultados/" + nombreArchivo + ".txt");
    }

    public void escribir(String linea) {
        try {
            archivo = new FileWriter("./Resultados/" + nombreArchivo + ".txt", f.exists());
            pw = new PrintWriter(archivo);
            pw.println(linea);
            archivo.close();
        }catch(Exception e) { e.printStackTrace(); }
    }

    private String nombreArchivo;
    private PrintWriter pw;
    private FileWriter archivo;
    private File f;
}