/*
    Interfaz donde se muestran los resultados del algoritmos genetico
*/
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class Resultados extends JFrame {
    private static final long serialVersionUID = 2L;
    //Constructor de la clase, recibe un arreglo de restricciones, un integrante y la configuración.
    public Resultados(Restriccion[] restricciones, Integrante integrante, Configuracion conf) {
        this.restricciones = restricciones;
        this.integrante = integrante;
        this.conf = conf;
        latitudR = longitudR = 0;

        convertirAGPS();
        init();
        crearInterfaz();
        botonesAlaEscucha();
    }

    //Funcion donde se convierte de cartesianas a coordenadas a GPS.
    //Convierte las coordenadas cartesianas a GPS, tomando las coordenadas
    //del integrante con mejor aptitud.
    //No recibe parametros.
    //No tiene retorno.
    private void convertirAGPS() {
        double xT, yT;
        double iSexagecimal;
        double _2PI;

        xT = yT = 0;
        iSexagecimal = 0;
        _2PI = 0;

        _2PI = 2 * Math.PI;
        iSexagecimal = 360 / _2PI;

        xT = integrante.getValX();
        yT = integrante.getValY();

        latitudR = iSexagecimal * Math.acos(yT * (360 / PERIMETRO_TIERRA));
        longitudR = xT / yT;
    }
    
    private void crearInterfaz() {
        setTitle("Resultados");
        setBounds(450, 150, 600, 200);
        setResizable(false);
        
        panelPrincipal.setLayout(new BorderLayout(5, 5));
        panelIzquierdo.setLayout(new BorderLayout(5, 5));
        panelDerecho.setLayout(new BoxLayout(this.panelDerecho, BoxLayout.Y_AXIS));
        panelCartesianas.setLayout(new BoxLayout(this.panelCartesianas, BoxLayout.Y_AXIS));
        panelGPS.setLayout(new BoxLayout(this.panelGPS, BoxLayout.Y_AXIS));
        panelRestricciones.setLayout(new GridLayout(5, 4, 5, 5));

        personalizarEtiquetas(etiquetaLatitud);
        personalizarEtiquetas(etiquetaLongitud);
        personalizarEtiquetas(etiquetaDistancia);
        personalizarEtiquetas(etiquetaId);
        personalizarEtiquetas(xRes);
        personalizarEtiquetas(yRes);
        personalizarEtiquetas(longitudRes);
        personalizarEtiquetas(latitudRes);
        
        for(int i = 0; i < TOTAL_RESTRICCIONES; i++) {
            personalizarEtiquetas(id[i]);
            personalizarEtiquetas(latitud[i]);
            personalizarEtiquetas(longitud[i]);
            personalizarEtiquetas(distancia[i]);
        }
        
        for(int i = 0; i < TOTAL_RESTRICCIONES; i++) {
            latitud[i].setText(Double.toString(restricciones[i].getLatitud()));
            longitud[i].setText(Double.toString(restricciones[i].getLongitud()));
            distancia[i].setText(Double.toString(restricciones[i].getRi()));

            panelRestricciones.add(id[i]);
            panelRestricciones.add(latitud[i]);
            panelRestricciones.add(longitud[i]);
            panelRestricciones.add(distancia[i]);
        }

        colorearPanel(panelPrincipal);
        colorearPanel(panelIzquierdo);
        colorearPanel(panelDerecho);
        colorearPanel(panelGPS);
        colorearPanel(panelCartesianas);
        colorearPanel(panelRestricciones);
        colorearPanel(panelBoton);

        colocarBorde(panelIzquierdo);
        colocarBorde(panelDerecho);
        colocarBorde(panelRestricciones);
        colocarBorde(panelBoton);

        otraVez.setFont(new Font("Sans Regular", Font.BOLD, 12));

        xRes.setText("X: " + integrante.getValX());
        yRes.setText("Y: " + integrante.getValY());
        latitudRes.setText("Latitud: " + latitudR);
        longitudRes.setText("Longitud: " + longitudR);

        panelBoton.add(otraVez);
        panelCartesianas.add(xRes);
        panelCartesianas.add(yRes);
        panelGPS.add(latitudRes);
        panelGPS.add(longitudRes);
        panelBoton.add(otraVez);

        panelIzquierdo.add(panelCartesianas, BorderLayout.CENTER);
        panelIzquierdo.add(panelGPS, BorderLayout.SOUTH);
        panelDerecho.add(panelRestricciones);

        panelPrincipal.add(panelIzquierdo, BorderLayout.WEST);
        panelPrincipal.add(panelDerecho, BorderLayout.CENTER);
        panelPrincipal.add(panelBoton, BorderLayout.SOUTH);

        add(panelPrincipal);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void init() {
        panelPrincipal = new JPanel();
        panelIzquierdo = new JPanel();
        panelDerecho = new JPanel();
        panelCartesianas = new JPanel();
        panelGPS = new JPanel();
        panelRestricciones = new JPanel();
        panelBoton = new JPanel();
        otraVez = new JButton("Otra");
        xRes = new JLabel("X: ");
        yRes = new JLabel("Y: ");
        latitudRes = new JLabel("Latitud: ");
        longitudRes = new JLabel("Longitud: ");
        etiquetaLatitud = new JLabel("Latitud");
        etiquetaLongitud = new JLabel("Longitud");
        etiquetaDistancia = new JLabel("Distancia");
        etiquetaId = new JLabel("Id");
        id = new JLabel[TOTAL_RESTRICCIONES];
        longitud = new JLabel[TOTAL_RESTRICCIONES];
        latitud = new JLabel[TOTAL_RESTRICCIONES];
        distancia = new JLabel[TOTAL_RESTRICCIONES];

        for(int i = 0; i < TOTAL_RESTRICCIONES; i++) {
            id[i] = new JLabel(idS[i]);
            latitud[i] = new JLabel(latitudS[i]);
            longitud[i] = new JLabel(longitudS[i]);
            distancia[i] = new JLabel(distanciaS[i]);
        }
    }

    private void botonesAlaEscucha() {
        otraVez.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Genetico(conf, restricciones);//LLamada de nuevo al algoritmo para realizarse de nueva cuenta con el mismo planteamiento.
            }
        });
    }

    private void colorearPanel(JPanel panel) {
        panel.setBackground(Color.decode(GRIS));
    }

    private void personalizarEtiquetas(JLabel etiqueta) {
        etiqueta.setForeground(Color.decode(BLANCO));
        etiqueta.setFont(new Font("Sans Regular", Font.BOLD, 12));
    }

    private void colocarBorde(JPanel p) {
        p.setBorder(new LineBorder(Color.decode(BLANCO)));
    }

    private Restriccion[] restricciones;
    private Integrante integrante;
    private Configuracion conf;
    private double latitudR, longitudR;
    private JPanel panelPrincipal;
    private JPanel panelIzquierdo;
    private JPanel panelDerecho;
    private JPanel panelCartesianas;
    private JPanel panelGPS;
    private JPanel panelRestricciones;
    private JPanel panelBoton;
    private JButton otraVez;
    private JLabel xRes, yRes;
    private JLabel latitudRes, longitudRes;
    private JLabel etiquetaLatitud;
    private JLabel etiquetaLongitud;
    private JLabel etiquetaDistancia;
    private JLabel etiquetaId;
    private final String GRIS = "#9E9E9E";
    private final String BLANCO = "#FFFFFF";
    private final int TOTAL_RESTRICCIONES = 4;
    private JLabel[] id;
    private JLabel[] longitud;
    private JLabel[] latitud;
    private JLabel[] distancia;
    private final double PERIMETRO_TIERRA = 40030.174;

    private String[] idS = {
        "R1",
        "R2",
        "R3",
        "R4"
    };

    private String[] latitudS = {
        "",
        "",
        "",
        ""
    };

    private String[] longitudS = {
        "",
        "",
        "",
        ""
    };
    
    private String[] distanciaS = {
        "",
        "",
        "",
        ""
    };
}