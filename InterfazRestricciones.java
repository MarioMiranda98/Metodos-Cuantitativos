import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InterfazRestricciones extends JFrame {
    private static final long serialVersionUID = 2L; 
    public InterfazRestricciones(Configuracion c, Interfaz i) {
        this.c = c;
        this.i = i;
        crearInterfaz(c);
    }

    private void crearInterfaz(Configuracion conf) {
        setTitle("Restricciones");
        setBounds(450, 100, 425, 400);
        setResizable(false);

        panelPrincipal = new JPanel();
        panelSuperior = new JPanel();
        panelCentral = new JPanel();
        panelInferior = new JPanel();
        panelRestricciones = new JPanel();
        panelCampoRestricciones = new JPanel();
        panelEtiquetasRestricciones = new JPanel();
        panelAvisos = new JPanel();
        etiquetaX = new JLabel("X");
        etiquetaY = new JLabel("Y");
        etiquetaRadio = new JLabel("Radio");
        aviso1 = new JLabel("Las unidades son tomadas en cm");
        campoX = new JTextField(10);
        campoY = new JTextField(10);
        campoRadio = new JTextField(10);
        botonRegresar = new JButton("Regresar");
        botonResolver = new JButton("Resolver");
        x = new JLabel[c.getNumeroRestricciones()];
        y = new JLabel[c.getNumeroRestricciones()];
        r = new JLabel[c.getNumeroRestricciones()];
        restricciones = new JLabel[c.getNumeroRestricciones()];
        res = new Restriccion[c.getNumeroRestricciones()];
        cont = 0;

        for(int i = 0; i < c.getNumeroRestricciones(); i++) {
            x[i] = new JLabel("");
            y[i] = new JLabel("");
            r[i] = new JLabel("");
            restricciones[i] = new JLabel("");
        }

        for(int i = 0; i < c.getNumeroRestricciones(); i++) {
            res[i] = new Restriccion();
        }

        comboRestricciones = new JComboBox<>();

        for(int i = 0; i < conf.getNumeroRestricciones(); i++)
            comboRestricciones.addItem(nombresRestricciones[i]);

        panelPrincipal.setLayout(new BorderLayout());
        panelCentral.setLayout(new BoxLayout(this.panelCentral, BoxLayout.Y_AXIS));
        panelRestricciones.setLayout(new GridLayout(1, 3, 5, 5));
        panelEtiquetasRestricciones.setLayout(new GridLayout(c.getNumeroRestricciones(), 4, 5, 5));

        panelPrincipal.setBackground(Color.decode("#009688"));
        panelSuperior.setBackground(Color.decode("#009688"));
        panelCentral.setBackground(Color.decode("#009688"));
        panelRestricciones.setBackground(Color.decode("#009688"));
        panelCampoRestricciones.setBackground(Color.decode("#009688"));
        panelEtiquetasRestricciones.setBackground(Color.decode("#009688"));
        panelInferior.setBackground(Color.decode("#009688"));
        comboRestricciones.setBackground(Color.decode("#009688"));
        panelAvisos.setBackground(Color.decode("#009688"));

        aviso1.setForeground(Color.decode("#FFFFFF"));
        etiquetaX.setForeground(Color.decode("#FFFFFF"));
        etiquetaY.setForeground(Color.decode("#FFFFFF"));
        etiquetaRadio.setForeground(Color.decode("#FFFFFF"));

        for(int i = 0; i < c.getNumeroRestricciones(); i++) {
            x[i].setForeground(Color.decode("#FFFFFF"));
            x[i].setFont(new Font("Sans Regular", Font.BOLD, 12));
            x[i].setText(xS[i]);
        }

        for(int i = 0; i < c.getNumeroRestricciones(); i++) {
            y[i].setForeground(Color.decode("#FFFFFF"));
            y[i].setFont(new Font("Sans Regular", Font.BOLD, 12));
            y[i].setText(yS[i]);
        }

        for(int i = 0; i < c.getNumeroRestricciones(); i++) {
            r[i].setForeground(Color.decode("#FFFFFF"));
            r[i].setFont(new Font("Sans Regular", Font.BOLD, 12));
            r[i].setText(rS[i]);
        }   

        for(int i = 0; i < c.getNumeroRestricciones(); i++) {
            restricciones[i].setForeground(Color.decode("#FFFFFF"));
            restricciones[i].setFont(new Font("Sans Regular", Font.BOLD, 12));
            restricciones[i].setText(restriccionesS[i]);
        } 

        comboRestricciones.setFont(new Font("Sans Regular", Font.BOLD, 16));
        aviso1.setFont(new Font("Sans Regular", Font.BOLD, 12));
        etiquetaX.setFont(new Font("Sans Regular", Font.BOLD, 12));
        etiquetaY.setFont(new Font("Sans Regular", Font.BOLD, 12));
        etiquetaRadio.setFont(new Font("Sans Regular", Font.BOLD, 12));    

        campoX.setBackground(Color.decode("#BDBDBD"));
        campoX.setBorder(BorderFactory.createLineBorder(Color.decode("#757575")));
        campoX.setForeground(Color.decode("#212121"));

        campoY.setBackground(Color.decode("#BDBDBD"));
        campoY.setBorder(BorderFactory.createLineBorder(Color.decode("#757575")));
        campoY.setForeground(Color.decode("#212121"));

        campoRadio.setBackground(Color.decode("#BDBDBD"));
        campoRadio.setBorder(BorderFactory.createLineBorder(Color.decode("#757575")));
        campoRadio.setForeground(Color.decode("#212121"));

        campoX.setFont(new Font("Sans Regular", Font.BOLD, 12));
        campoY.setFont(new Font("Sans Regular", Font.BOLD, 12));
        campoRadio.setFont(new Font("Sans Regular", Font.BOLD, 12));

        for(int i = 0; i < c.getNumeroRestricciones(); i++) {
            panelEtiquetasRestricciones.add(restricciones[i]);
            panelEtiquetasRestricciones.add(x[i]);
            panelEtiquetasRestricciones.add(y[i]);
            panelEtiquetasRestricciones.add(r[i]);
        }

        comboRestricciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                anterior = cont;
                if((!campoX.getText().equals("")) || (!campoY.getText().equals("")) || (!campoRadio.getText().equals(""))) {
                    res[anterior].setX(Double.parseDouble(campoX.getText()));
                    res[anterior].setY(Double.parseDouble(campoY.getText()));
                    res[anterior].setR(Double.parseDouble(campoRadio.getText()));

                    x[anterior].setText("X: " + res[anterior].getX());
                    y[anterior].setText("Y: " + res[anterior].getY());
                    r[anterior].setText("R: " + res[anterior].getR());

                    cont = comboRestricciones.getSelectedIndex();
                } else {
                    JOptionPane.showMessageDialog(InterfazRestricciones.this, "Faltan datos");
                }
            }
        });

        botonRegresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                i.setVisible(true);
            }
        });

        botonResolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < c.getNumeroRestricciones(); i++) {
                    System.out.println("X: " + res[i].getX());
                    System.out.println("Y: " + res[i].getY());
                    System.out.println("R: " + res[i].getR());
                }
            }
        });

        panelAvisos.add(aviso1);
        panelCampoRestricciones.add(campoX);
        panelCampoRestricciones.add(campoY);
        panelCampoRestricciones.add(campoRadio);
        panelRestricciones.add(etiquetaX);
        panelRestricciones.add(etiquetaY);
        panelRestricciones.add(etiquetaRadio);

        etiquetaX.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaY.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaRadio.setHorizontalAlignment(SwingConstants.CENTER);

        panelSuperior.add(comboRestricciones);
        panelCentral.add(panelRestricciones);
        panelCentral.add(panelCampoRestricciones);
        panelCentral.add(panelEtiquetasRestricciones);
        panelCentral.add(panelAvisos);
        panelInferior.add(botonRegresar);
        panelInferior.add(botonResolver);
        
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);
        add(panelPrincipal);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private Configuracion c;
    private JPanel panelPrincipal;
    private JPanel panelSuperior;
    private JPanel panelCentral;
    private JPanel panelRestricciones;
    private JPanel panelCampoRestricciones;
    private JPanel panelEtiquetasRestricciones;
    private JPanel panelAvisos;
    private JPanel panelInferior;
    private JComboBox<String> comboRestricciones;
    private String[] nombresRestricciones = {
        "Restriccion 1",
        "Restriccion 2",
        "Restriccion 3",
        "Restriccion 4"
    };

    private JLabel etiquetaX, etiquetaY, etiquetaRadio;
    private JLabel aviso1;
    private JLabel[] x;
    private JLabel[] y;
    private JLabel[] r;
    private JLabel[] restricciones;
    
    private String[] xS = {
        "x1: ",
        "x2: ",
        "x3: ",
        "x4: "
    };

    private String[] yS = {
        "y1: ",
        "y2: ",
        "y3: ",
        "y4: "
    };

    private String[] rS = {
        "r1: ",
        "r2: ",
        "r3: ",
        "r4: "
    };

    private String[] restriccionesS = {
        "Restriccion 1: ",
        "Restriccion 2: ",
        "Restriccion 3: ",
        "Restriccion 4: "
    };

    private JTextField campoX, campoY, campoRadio;
    private JButton botonResolver, botonRegresar;
    private Interfaz i;
    private int cont;
    private int anterior;
    private Restriccion[] res;
}