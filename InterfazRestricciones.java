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
        setBounds(400, 100, 475, 400);
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
        campoX = new JTextField(12);
        campoY = new JTextField(12);
        campoRadio = new JTextField(12);
        botonRegresar = new JButton("Regresar");
        botonResolver = new JButton("Resolver");
        x = new JLabel[c.getNumeroRestricciones()];
        y = new JLabel[c.getNumeroRestricciones()];
        r = new JLabel[c.getNumeroRestricciones()];
        restricciones = new JLabel[c.getNumeroRestricciones()];
        res = new Restriccion[c.getNumeroRestricciones()];
        botonesRestricciones = new JButton[c.getNumeroRestricciones()];
        cont = 0;

        for(int i = 0; i < c.getNumeroRestricciones(); i++) {
            botonesRestricciones[i] = new JButton("Restriccion " + (i + 1));
            botonesRestricciones[i].setActionCommand("" + i);
        }

        for(int i = 0; i < c.getNumeroRestricciones(); i++) {
            x[i] = new JLabel("");
            y[i] = new JLabel("");
            r[i] = new JLabel("");
            restricciones[i] = new JLabel("");
        }

        for(int i = 0; i < c.getNumeroRestricciones(); i++) {
            res[i] = new Restriccion();
        }


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

        botonRegresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                i.setVisible(true);
                borrado();
            }
        });

        listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cont = Integer.parseInt(e.getActionCommand());
                if((!campoX.getText().equals("")) || (!campoY.getText().equals("")) || (!campoRadio.getText().equals(""))) {
                    res[cont].setXi(Double.parseDouble(campoX.getText()));
                    res[cont].setYi(Double.parseDouble(campoY.getText()));
                    res[cont].setRi(Double.parseDouble(campoRadio.getText()));

                    x[cont].setText("X: " + res[cont].getXi());
                    y[cont].setText("Y: " + res[cont].getYi());
                    r[cont].setText("R: " + res[cont].getRi());

                    campoX.setText("");
                    campoY.setText("");
                    campoRadio.setText("");
                } else {
                    JOptionPane.showMessageDialog(InterfazRestricciones.this, "Faltan datos");
                }
            }
        };

        botonResolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Genetico(conf, res);
                setVisible(false);
            }
        });


        for(int i = 0; i < c.getNumeroRestricciones(); i++) {
            panelSuperior.add(botonesRestricciones[i]);
            botonesRestricciones[i].addActionListener(listener);
        }

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

    private void borrado() {
        for(int i = 0; i < c.getNumeroRestricciones(); i++) {
            x[i].setText(xS[i]);
            y[i].setText(yS[i]);
            r[i].setText(rS[i]);
            restricciones[i].setText(restriccionesS[i]);

            res[i].setXi(0.0);
            res[i].setYi(0.0);
            res[i].setRi(0.0);
        } 
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
    private JLabel etiquetaX, etiquetaY, etiquetaRadio;
    private JLabel aviso1;
    private JLabel[] x;
    private JLabel[] y;
    private JLabel[] r;
    private JLabel[] restricciones;
    private JButton[] botonesRestricciones;
    
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
    private ActionListener listener;
    private Restriccion[] res;
}