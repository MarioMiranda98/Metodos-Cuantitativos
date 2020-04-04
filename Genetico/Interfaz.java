import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Interfaz extends JFrame{
    private static final long serialVersionUID = 1L;

    public Interfaz() {
        setTitle("Trilateracion");
        setBounds(550, 150, 250, 350);
        setResizable(false);

        panelPrincipal = new JPanel();
        panelCentral = new JPanel();
        panelInferior = new JPanel();
        panelCampo = new JPanel();
        panelRadioButton = new JPanel();
        panelAviso = new JPanel();
        panelErrorMinimo = new JPanel();
        panelNombreArchivo = new JPanel();
        panelTam = new JPanel();
        panelTamXY = new JPanel();
        tresRestricciones = new JRadioButton("3", true);
        cuatroRestricciones = new JRadioButton("4", false);
        cantidadRestricciones = new ButtonGroup();
        botonContinuar = new JButton("Continuar");
        etiquetaPoblacion = new JLabel("Numero de Poblacion");
        etiquetaCantidadRestricciones = new JLabel("Cantidad Restricciones");
        aviso = new JLabel("Se usara 4 bits de presicion");
        etiquetaTam = new JLabel("Tama\u00f1o del primer cuadrante");
        etiquetaTamX = new JLabel("X: ");
        etiquetaTamY = new JLabel("Y: ");
        etiquetaErrorMinimo = new JLabel("Ingrese Error Minimo");
        etiquetaNombreArchivo = new JLabel("Nombre Archivo de Salida:");
        campoPoblacion = new JTextField(6);
        campoTamX = new JTextField(3);
        campoTamY = new JTextField(3);
        campoErrorMinimo = new JTextField(3);
        campoNombreArchivo = new JTextField(20);

        botonContinuar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("A la escucha");
            }
        });

        panelPrincipal.setLayout(new BorderLayout());
        panelCentral.setLayout(new BoxLayout(this.panelCentral, BoxLayout.Y_AXIS));
        panelTam.setLayout(new BoxLayout(this.panelTam, BoxLayout.Y_AXIS));

        cantidadRestricciones.add(tresRestricciones);
        cantidadRestricciones.add(cuatroRestricciones);
        
        panelRadioButton.add(etiquetaCantidadRestricciones);
        panelRadioButton.add(tresRestricciones);
        panelRadioButton.add(cuatroRestricciones);
        
        panelCampo.add(etiquetaPoblacion);
        panelCampo.add(campoPoblacion);

        panelTamXY.add(etiquetaTamX);
        panelTamXY.add(campoTamX);
        panelTamXY.add(etiquetaTamY);
        panelTamXY.add(campoTamY);
        panelTam.add(etiquetaTam);
        panelTam.add(panelTamXY);

        panelErrorMinimo.add(etiquetaErrorMinimo);
        panelErrorMinimo.add(campoErrorMinimo);

        panelNombreArchivo.add(etiquetaNombreArchivo);
        panelNombreArchivo.add(campoNombreArchivo);

        panelAviso.add(aviso);

        panelCentral.add(panelRadioButton);
        panelCentral.add(panelCampo);
        panelCentral.add(panelTam);
        panelCentral.add(panelErrorMinimo);
        panelCentral.add(panelNombreArchivo);
        panelCentral.add(panelAviso);

        panelInferior.add(botonContinuar);

        panelCentral.setBackground(Color.decode("#009688"));
        tresRestricciones.setBackground(Color.decode("#009688"));
        cuatroRestricciones.setBackground(Color.decode("#009688"));
        panelRadioButton.setBackground(Color.decode("#009688"));
        panelCampo.setBackground(Color.decode("#009688"));
        panelTam.setBackground(Color.decode("#009688"));
        panelTamXY.setBackground(Color.decode("#009688"));
        panelErrorMinimo.setBackground(Color.decode("#009688"));
        panelNombreArchivo.setBackground(Color.decode("#009688"));
        panelAviso.setBackground(Color.decode("#009688"));
        panelInferior.setBackground(Color.decode("#009688"));

        campoPoblacion.setBackground(Color.decode("#BDBDBD"));
        campoPoblacion.setBorder(BorderFactory.createLineBorder(Color.decode("#757575")));
        campoPoblacion.setForeground(Color.decode("#212121"));

        campoErrorMinimo.setBackground(Color.decode("#BDBDBD"));
        campoErrorMinimo.setBorder(BorderFactory.createLineBorder(Color.decode("#757575")));
        campoErrorMinimo.setForeground(Color.decode("#212121"));

        campoNombreArchivo.setBackground(Color.decode("#BDBDBD"));
        campoNombreArchivo.setBorder(BorderFactory.createLineBorder(Color.decode("#757575")));
        campoNombreArchivo.setForeground(Color.decode("#212121"));

        campoTamX.setBackground(Color.decode("#BDBDBD"));
        campoTamX.setBorder(BorderFactory.createLineBorder(Color.decode("#757575")));
        campoTamX.setForeground(Color.decode("#212121"));
        
        campoTamY.setBackground(Color.decode("#BDBDBD"));
        campoTamY.setBorder(BorderFactory.createLineBorder(Color.decode("#757575")));
        campoTamY.setForeground(Color.decode("#212121"));

        etiquetaCantidadRestricciones.setForeground(Color.decode("#FFFFFF"));
        etiquetaErrorMinimo.setForeground(Color.decode("#FFFFFF"));
        etiquetaNombreArchivo.setForeground(Color.decode("#FFFFFF"));
        etiquetaPoblacion.setForeground(Color.decode("#FFFFFF"));
        etiquetaTam.setForeground(Color.decode("#FFFFFF"));
        etiquetaTamX.setForeground(Color.decode("#FFFFFF"));
        etiquetaTamY.setForeground(Color.decode("#FFFFFF"));
        aviso.setForeground(Color.decode("#FFFFFF"));
        cuatroRestricciones.setForeground(Color.decode("#FFFFFF"));
        tresRestricciones.setForeground(Color.decode("#FFFFFF"));

        botonContinuar.setBackground(Color.decode("#BDBDBD"));
        botonContinuar.setForeground(Color.decode("#212121"));
        
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);
        add(panelPrincipal);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JPanel panelPrincipal;
    private JPanel panelCentral;
    private JPanel panelInferior;
    private JPanel panelCampo;
    private JPanel panelRadioButton;
    private JPanel panelAviso;
    private JPanel panelTam;
    private JPanel panelTamXY;
    private JPanel panelErrorMinimo;
    private JPanel panelNombreArchivo;
    private JRadioButton tresRestricciones, cuatroRestricciones;
    private ButtonGroup cantidadRestricciones;
    private JButton botonContinuar;
    private JLabel etiquetaPoblacion, etiquetaCantidadRestricciones;
    private JTextField campoPoblacion, campoTamX, campoTamY;
    private JTextField campoErrorMinimo;
    private JTextField campoNombreArchivo;
    private JLabel aviso, etiquetaTamX, etiquetaTamY, etiquetaTam;
    private JLabel etiquetaErrorMinimo, etiquetaNombreArchivo;
}