import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class Interfaz extends JFrame {
    private static final long serialVersionUID = 1L;
    
    public Interfaz() {
        setTitle("Trilateración");
        setBounds(300, 250, 800, 250);
        setResizable(false);

        crearInterfaz();

        listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int pos = Integer.parseInt(e.getActionCommand());
                double tLatitud = Double.parseDouble(campoLatitud.getText());
                double tLongitud = Double.parseDouble(campoLongitud.getText());
                double tDistancia = Double.parseDouble(campoDistancia.getText());

                latitud[pos].setText(Double.toString(tLatitud));
                longitud[pos].setText(Double.toString(tLongitud));
                distancia[pos].setText(Double.toString(tDistancia));

                restricciones[pos].setLatitud(tLatitud);
                restricciones[pos].setLongitud(tLongitud);
                restricciones[pos].setRi(tDistancia);

                restricciones[pos].convertirACartesianas();

                borrado();
            }
        };
        
        componentesEscucha();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void crearInterfaz() {
        panelPrincipal = new JPanel();
        panelIzquierda = new JPanel();
        panelDerecho = new JPanel();
        panelLatitud = new JPanel();
        panelLongitud = new JPanel();
        panelPoblacion = new JPanel();
        panelDistancia = new JPanel();
        panelBotonRestriccion = new JPanel();
        panelBotonRestriccion2 = new JPanel();
        panelSuperiorDerecha = new JPanel();
        panelRestricciones = new JPanel();
        panelBotonC = new JPanel();

        etiquetaLatitud = new JLabel("Latitud");
        etiquetaLongitud = new JLabel("Longitud");
        etiquetaPoblacion = new JLabel("Tama\u00f1o Poblacion");
        etiquetaDistancia = new JLabel("Distancia (Km)");
        etiquetaId = new JLabel("Id");

        etiquetaLatitud2 = new JLabel("Latitud");
        etiquetaLongitud2 = new JLabel("Longitud");
        etiquetaDistancia2 = new JLabel("Distancia (Km)");

        campoLatitud = new JTextField(12);
        campoLongitud = new JTextField(12);
        campoPoblacion = new JTextField(12);
        campoDistancia = new JTextField(12);

        botonContinuar = new JButton("Resolver");
        botonRestriccion1 = new JButton("Restriccion 1");
        botonRestriccion2 = new JButton("Restriccion 2");
        botonRestriccion3 = new JButton("Restriccion 3");
        botonRestriccion4 = new JButton("Restriccion 4");

        botonRestriccion1.setActionCommand("0");
        botonRestriccion2.setActionCommand("1");
        botonRestriccion3.setActionCommand("2");
        botonRestriccion4.setActionCommand("3");

        id = new JLabel[TOTAL_RESTRICCIONES];
        latitud = new JLabel[TOTAL_RESTRICCIONES];
        longitud = new JLabel[TOTAL_RESTRICCIONES];
        distancia = new JLabel[TOTAL_RESTRICCIONES];
        restricciones = new Restriccion[TOTAL_RESTRICCIONES];

        for(int i = 0; i < TOTAL_RESTRICCIONES; i++) {
            id[i] = new JLabel(idS[i]);
            latitud[i] = new JLabel(latitudS[i]);
            longitud[i] = new JLabel(longitudS[i]);
            distancia[i] = new JLabel(distanciaS[i]);
            restricciones[i] = new Restriccion();
        }

        panelPrincipal.setLayout(new BorderLayout(5, 5));
        panelDerecho.setLayout(new BorderLayout());
        panelIzquierda.setLayout(new BoxLayout(this.panelIzquierda, BoxLayout.Y_AXIS));
        panelLatitud.setLayout(new BoxLayout(this.panelLatitud, BoxLayout.Y_AXIS));
        panelLongitud.setLayout(new BoxLayout(this.panelLongitud, BoxLayout.Y_AXIS));
        panelPoblacion.setLayout(new BoxLayout(this.panelPoblacion, BoxLayout.Y_AXIS));
        panelDistancia.setLayout(new BoxLayout(this.panelDistancia, BoxLayout.Y_AXIS));
        panelBotonRestriccion.setLayout(new BoxLayout(this.panelBotonRestriccion, BoxLayout.X_AXIS));
        panelBotonRestriccion2.setLayout(new BoxLayout(this.panelBotonRestriccion2, BoxLayout.X_AXIS));
        panelRestricciones.setLayout(new GridLayout(5, 4, 5, 5));

        personalizarEtiquetas(etiquetaLatitud);
        personalizarEtiquetas(etiquetaLongitud);
        personalizarEtiquetas(etiquetaDistancia);
        personalizarEtiquetas(etiquetaPoblacion);

        personalizarEtiquetas(etiquetaId);
        personalizarEtiquetas(etiquetaLatitud2);
        personalizarEtiquetas(etiquetaLongitud2);
        personalizarEtiquetas(etiquetaDistancia2);
        
        for(int i = 0; i < TOTAL_RESTRICCIONES; i++) {
            personalizarEtiquetas(id[i]);
            personalizarEtiquetas(latitud[i]);
            personalizarEtiquetas(longitud[i]);
            personalizarEtiquetas(distancia[i]);
        }
        
        panelRestricciones.add(etiquetaId);
        panelRestricciones.add(etiquetaLatitud2);
        panelRestricciones.add(etiquetaLongitud2);
        panelRestricciones.add(etiquetaDistancia2);

        for(int i = 0; i < TOTAL_RESTRICCIONES; i++) {
            panelRestricciones.add(id[i]);
            panelRestricciones.add(latitud[i]);
            panelRestricciones.add(longitud[i]);
            panelRestricciones.add(distancia[i]);
        }

        colorearPanel(panelPrincipal);
        colorearPanel(panelIzquierda);
        colorearPanel(panelDerecho);
        colorearPanel(panelLongitud);
        colorearPanel(panelLatitud);
        colorearPanel(panelPoblacion);
        colorearPanel(panelBotonC);
        colorearPanel(panelBotonRestriccion2);
        colorearPanel(panelSuperiorDerecha);
        colorearPanel(panelRestricciones);
        colorearPanel(panelDistancia);
        colorearPanel(panelBotonRestriccion);

        fuenteTexto(campoLatitud);
        fuenteTexto(campoLongitud);
        fuenteTexto(campoDistancia);
        fuenteTexto(campoPoblacion);

        colocarBorde(panelIzquierda);
        colocarBorde(panelDerecho);
        colocarBorde(panelPoblacion);
        colocarBorde(panelRestricciones);

        botonRestriccion1.setFont(new Font("Sans Regular", Font.BOLD, 12));
        botonRestriccion2.setFont(new Font("Sans Regular", Font.BOLD, 12));
        botonRestriccion3.setFont(new Font("Sans Regular", Font.BOLD, 12));
        botonRestriccion4.setFont(new Font("Sans Regular", Font.BOLD, 12));
        botonContinuar.setFont(new Font("Sans Regular", Font.BOLD, 12));

        panelLatitud.add(etiquetaLatitud);
        panelLatitud.add(campoLatitud);

        panelLongitud.add(etiquetaLongitud);
        panelLongitud.add(campoLongitud);

        panelPoblacion.add(etiquetaPoblacion);
        panelPoblacion.add(campoPoblacion);
        
        panelDistancia.add(etiquetaDistancia);
        panelDistancia.add(campoDistancia);

        panelBotonRestriccion.add(botonRestriccion1);
        panelBotonRestriccion.add(botonRestriccion2);
        panelBotonRestriccion2.add(botonRestriccion3);
        panelBotonRestriccion2.add(botonRestriccion4);

        panelIzquierda.add(panelLatitud);
        panelIzquierda.add(panelLongitud);
        panelIzquierda.add(panelDistancia);
        panelIzquierda.add(panelBotonRestriccion);
        panelIzquierda.add(panelBotonRestriccion2);
        panelIzquierda.add(panelPoblacion);

        panelSuperiorDerecha.add(new JLabel("Restricciones"));
        panelBotonC.add(botonContinuar);

        panelDerecho.add(panelSuperiorDerecha, BorderLayout.NORTH);
        panelDerecho.add(panelRestricciones, BorderLayout.CENTER);
        panelDerecho.add(panelBotonC, BorderLayout.SOUTH);

        panelPrincipal.add(panelDerecho, BorderLayout.CENTER);
        panelPrincipal.add(panelIzquierda, BorderLayout.WEST);
        add(panelPrincipal);
    }

    private void componentesEscucha() {
        botonRestriccion1.addActionListener(listener);
        botonRestriccion2.addActionListener(listener);
        botonRestriccion3.addActionListener(listener);
        botonRestriccion4.addActionListener(listener);

        botonContinuar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                configuracion = new Configuracion(Integer.parseInt(campoPoblacion.getText()));
                campoPoblacion.setText("");

                new Genetico(configuracion, restricciones);
                setVisible(false);
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

    private void fuenteTexto(JTextField campo) {
        campo.setFont(new Font("Sans Regular", Font.BOLD, 12));
    }

    private void colocarBorde(JPanel p) {
        p.setBorder(new LineBorder(Color.decode(BLANCO)));
    }
    
    private void borrado() {
        campoLatitud.setText("");
        campoLongitud.setText("");
        campoDistancia.setText("");
    }

    private JPanel panelPrincipal;
    private JPanel panelIzquierda;
    private JPanel panelDerecho;
    private JPanel panelLatitud;
    private JPanel panelLongitud;
    private JPanel panelPoblacion;
    private JPanel panelDistancia;
    private JPanel panelBotonRestriccion;
    private JPanel panelBotonRestriccion2;
    private JPanel panelSuperiorDerecha;
    private JPanel panelRestricciones;
    private JPanel panelBotonC;
    private JLabel etiquetaLatitud;
    private JLabel etiquetaLongitud;
    private JLabel etiquetaPoblacion;
    private JLabel etiquetaDistancia;
    private JLabel etiquetaLatitud2;
    private JLabel etiquetaLongitud2;
    private JLabel etiquetaDistancia2;
    private JLabel etiquetaId;
    private JTextField campoLatitud;
    private JTextField campoLongitud;
    private JTextField campoPoblacion;
    private JTextField campoDistancia;
    private JButton botonContinuar;
    private JButton botonRestriccion1;
    private JButton botonRestriccion2;
    private JButton botonRestriccion3;
    private JButton botonRestriccion4;
    private final String GRIS = "#9E9E9E";
    private final String BLANCO = "#FFFFFF";
    private final int TOTAL_RESTRICCIONES = 4;
    private JLabel[] id;
    private JLabel[] longitud;
    private JLabel[] latitud;
    private JLabel[] distancia;
    private Restriccion[] restricciones;
    private Configuracion configuracion;
    private ActionListener listener;

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