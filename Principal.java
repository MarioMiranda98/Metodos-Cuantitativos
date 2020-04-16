/*
    Clase principal donde se inicia el programa.
*/

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

public class Principal {
    public static void main(String[] args) {
        //Hacemos que la JVM cargue un estilo mas estetico para los componentes graficos
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        new Interfaz();//Creamos el objeto de la interfaz principal
    }
}