import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InterfazResultados extends JFrame {
    private static final long serialVersionUID = 3L;
    public InterfazResultados(Restriccion[] restricciones, Integrante integrante, Configuracion conf) {
        this.restricciones = restricciones;
        this.integrante = integrante;
        this.conf = conf;
        this.cantidadRestricciones = conf.getNumeroRestricciones();
        interfaz();
        accionBotones();
    }

    private void interfaz() {
        setTitle("Resultados");
        setBounds(375, 100, 600, 200);
        setResizable(false);

        panelPrincipal = new JPanel();
        panelSuperior = new JPanel();
        panelCentral = new JPanel();
        panelInferior = new JPanel();
        panelResultados = new JPanel();
        panelArea = new JPanel();
        panelBotones = new JPanel();

        resultado = new JLabel("Resultado: ");
        xRes = new JLabel("X: ");
        yRes = new JLabel("Y: ");
        x = new JLabel[cantidadRestricciones];
        y = new JLabel[cantidadRestricciones];
        r = new JLabel[cantidadRestricciones];

        for(int i = 0; i < cantidadRestricciones; i++) {
            x[i] = new JLabel("");
            y[i] = new JLabel("");
            r[i] = new JLabel("");
        }

        otra = new JButton("Otra");

        panelPrincipal.setBackground(Color.decode("#009688"));
        panelCentral.setBackground(Color.decode("#009688"));
        panelInferior.setBackground(Color.decode("#009688"));
        panelSuperior.setBackground(Color.decode("#009688"));
        panelResultados.setBackground(Color.decode("#009688"));
        panelArea.setBackground(Color.decode("#009688"));
        panelBotones.setBackground(Color.decode("#009688"));

        resultado.setForeground(Color.decode("#FFFFFF"));
        resultado.setFont(new Font("Sans Regular", Font.BOLD, 14));

        xRes.setForeground(Color.decode("#FFFFFF"));
        xRes.setFont(new Font("Sans Regular", Font.BOLD, 14));
        xRes.setText("X: " + integrante.getValX());
        
        yRes.setForeground(Color.decode("#FFFFFF"));
        yRes.setFont(new Font("Sans Regular", Font.BOLD, 14));
        yRes.setText("Y: " + integrante.getValY());
    
        for(int i = 0; i < cantidadRestricciones; i++) {
            x[i].setForeground(Color.decode("#FFFFFF"));
            x[i].setFont(new Font("Sans Regular", Font.BOLD, 12));
            x[i].setText(xS[i] + restricciones[i].getXi());
        }

        for(int i = 0; i < cantidadRestricciones; i++) {
            y[i].setForeground(Color.decode("#FFFFFF"));
            y[i].setFont(new Font("Sans Regular", Font.BOLD, 12));
            y[i].setText(yS[i] + restricciones[i].getYi());
        }

        for(int i = 0; i < cantidadRestricciones; i++) {
            r[i].setForeground(Color.decode("#FFFFFF"));
            r[i].setFont(new Font("Sans Regular", Font.BOLD, 12));
            r[i].setText(rS[i] + restricciones[i].getRi());
        }

        panelPrincipal.setLayout(new BorderLayout());
        panelResultados.setLayout(new GridLayout((cantidadRestricciones + 1), 3, 5, 5));
        panelCentral.setLayout(new BoxLayout(this.panelCentral, BoxLayout.Y_AXIS));

        for(int i = 0; i < cantidadRestricciones; i++) {
            panelResultados.add(x[i]);
            panelResultados.add(y[i]);
            panelResultados.add(r[i]);
        }

        panelResultados.add(resultado);
        panelResultados.add(xRes);
        panelResultados.add(yRes);
        
        panelBotones.add(otra);
        panelCentral.add(panelResultados);
        panelCentral.add(panelArea);
        panelInferior.add(panelBotones);
        panelSuperior.add(new JLabel("Resultados"));
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        add(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void accionBotones() {
        otra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Genetico(conf, restricciones);
            }
        });
    }

    private Restriccion[] restricciones;
    private Integrante integrante;
    private int cantidadRestricciones;
    private Configuracion conf;

    private JPanel panelPrincipal;
    private JPanel panelSuperior;
    private JPanel panelCentral;
    private JPanel panelInferior;
    private JPanel panelResultados;
    private JPanel panelArea;
    private JPanel panelBotones;
    private JLabel[] x;
    private JLabel[] y;
    private JLabel[] r;
    private JLabel xRes, yRes;
    private JLabel resultado;

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

    private JButton otra;
}