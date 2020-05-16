import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Interfaz extends JFrame {
    private static final long serialVersionUID = 1L;
    
    public Interfaz() {
        setTitle("Trilateración");
        setBounds(300, 250, 800, 250);
        setResizable(false);

        crearInterfaz();
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
        panelCombo = new JPanel();
        panelBoton = new JPanel();
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

        comboRestricciones = new JComboBox<>();
        botonRestriccion = new JButton("A\u00f1adir");
        botonContinuar = new JButton("Resolver");

        comboRestricciones.addItem("Restriccion 1");
        comboRestricciones.addItem("Restriccion 2");
        comboRestricciones.addItem("Restriccion 3");
        comboRestricciones.addItem("Restriccion 4");

        id = new JLabel[TOTAL_RESTRICCIONES];
        latitud = new JLabel[TOTAL_RESTRICCIONES];
        longitud = new JLabel[TOTAL_RESTRICCIONES];
        distancia = new JLabel[TOTAL_RESTRICCIONES];

        for(int i = 0; i < TOTAL_RESTRICCIONES; i++) {
            id[i] = new JLabel(idS[i]);
            latitud[i] = new JLabel(latitudS[i]);
            longitud[i] = new JLabel(longitudS[i]);
            distancia[i] = new JLabel(distanciaS[i]);
        }

        panelPrincipal.setLayout(new BorderLayout(5, 5));
        panelDerecho.setLayout(new BorderLayout());
        panelIzquierda.setLayout(new BoxLayout(this.panelIzquierda, BoxLayout.Y_AXIS));
        panelLatitud.setLayout(new BoxLayout(this.panelLatitud, BoxLayout.Y_AXIS));
        panelLongitud.setLayout(new BoxLayout(this.panelLongitud, BoxLayout.Y_AXIS));
        panelPoblacion.setLayout(new BoxLayout(this.panelPoblacion, BoxLayout.Y_AXIS));
        panelDistancia.setLayout(new BoxLayout(this.panelDistancia, BoxLayout.Y_AXIS));
        panelCombo.setLayout(new BoxLayout(this.panelCombo, BoxLayout.Y_AXIS));
        panelBoton.setLayout(new BoxLayout(this.panelBoton, BoxLayout.Y_AXIS));
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
        colorearPanel(panelBoton);
        colorearPanel(panelSuperiorDerecha);
        colorearPanel(panelRestricciones);
        colorearPanel(panelDistancia);
        colorearPanel(panelCombo);

        fuenteTexto(campoLatitud);
        fuenteTexto(campoLongitud);
        fuenteTexto(campoDistancia);
        fuenteTexto(campoPoblacion);

        botonRestriccion.setFont(new Font("Sans Regular", Font.BOLD, 12));
        botonContinuar.setFont(new Font("Sans Regular", Font.BOLD, 12));

        panelLatitud.add(etiquetaLatitud);
        panelLatitud.add(campoLatitud);

        panelLongitud.add(etiquetaLongitud);
        panelLongitud.add(campoLongitud);

        panelPoblacion.add(etiquetaPoblacion);
        panelPoblacion.add(campoPoblacion);
        
        panelDistancia.add(etiquetaDistancia);
        panelDistancia.add(campoDistancia);
        
        panelCombo.add(comboRestricciones);
        panelBoton.add(botonRestriccion);

        panelIzquierda.add(panelLatitud);
        panelIzquierda.add(panelLongitud);
        panelIzquierda.add(panelDistancia);
        panelIzquierda.add(panelPoblacion);
        panelIzquierda.add(panelCombo);
        panelIzquierda.add(panelBoton);

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
        botonRestriccion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int pos = comboRestricciones.getSelectedIndex();
                latitud[pos].setText(campoLatitud.getText());
                longitud[pos].setText(campoLongitud.getText());
                distancia[pos].setText(campoDistancia.getText());
            }
        });

        botonContinuar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("A la escucha");
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

    private JPanel panelPrincipal;
    private JPanel panelIzquierda;
    private JPanel panelDerecho;
    private JPanel panelLatitud;
    private JPanel panelLongitud;
    private JPanel panelPoblacion;
    private JPanel panelDistancia;
    private JPanel panelCombo;
    private JPanel panelBoton;
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
    private JComboBox<String> comboRestricciones;
    private JButton botonRestriccion;
    private JButton botonContinuar;
    private final String GRIS = "#9E9E9E";
    private final String BLANCO = "#FFFFFF";
    private final int TOTAL_RESTRICCIONES = 4;
    private JLabel[] id;
    private JLabel[] longitud;
    private JLabel[] latitud;
    private JLabel[] distancia;

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