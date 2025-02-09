package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle.Control;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Control.controlPrincipal;
import Modelo.matriz;

public class VistaPrincipal extends JFrame implements ActionListener {
    JButton boton = new JButton("Generar matriz");
    controlPrincipal ctrl;
    PanelHistograma panel;
    ImagePane Imagen_Grises;
    ImagePane Imagen_BN;
    ImagePane Imagen_Cromatico;

    public void setCtrl(controlPrincipal ctrol) {
        this.ctrl = ctrol;
    }

    // Creamos un boton para generar la matriz
    public VistaPrincipal(controlPrincipal ctrl) {
        this.setTitle("Matriz de pixeles");
        this.setLayout(null);
        // añadimos el boton a la ventana
        this.add(boton);
        boton.setBounds(300, 80, 290, 100);
        boton.setFont(new Font("Arial", 0, 25));
        boton.setBackground(Color.gray);
        boton.setForeground(Color.white);
        boton.setFocusPainted(false);
        boton.addActionListener(this);
        // Creamos un label para mostrar el histograma
        JLabel label = new JLabel("Histograma de la matriz");
        this.add(label);
        label.setBounds(310, 180, 290, 100);
        label.setFont(new Font("Arial", 0, 25));
        // Creamos un panel para mostrar la matriz

        panel = new PanelHistograma();
        // Creamos un boton para guardar imagen
        JButton guardar = new JButton("Guardar imagen");
        guardar.setBackground(Color.gray);
        guardar.setForeground(Color.white);
        guardar.setFocusPainted(false);
        guardar.setFont(new Font("Arial", 0, 25));
        this.add(guardar);
        guardar.setBounds(300, 700, 290, 100);

        guardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrl.generarImagen("grises");
                ctrl.generarImagen("byn");
                ctrl.generarImagen("cromatico");
            }
        });
        this.add(panel);
        panel.setBounds(50, 260, panel.getWidth(), panel.getHeight());
        panel.setVisible(true);

        //Agregamos los paneles para las imagenes
        Imagen_Grises = new ImagePane("");
        Imagen_Grises.setBounds(900, 30, 400, 400);
        this.add(Imagen_Grises);
        
        Imagen_BN = new ImagePane("");
        Imagen_BN.setBounds(1350, 30, 400, 400);
        this.add(Imagen_BN);

        Imagen_Cromatico = new ImagePane("");
        Imagen_Cromatico.setBounds(1125, 450, 400, 400);
        this.add(Imagen_Cromatico);


        this.setSize(1800, 900);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        // Centramos la ventana
        this.setLocationRelativeTo(null);
        // Le ponemos a toda la ventana un color de fondo
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ctrl.generarMatriz();

    }

    public void setMatriz(matriz m) {
        this.panel.setMatriz(m);
    }

    

    public void pintaHistograma() {

        panel.pintaHistograma();
    }

    public void setImagen(String path) {
        if(path.contains("EscalaGrises")){
            Imagen_Grises.setPath(path);
            Imagen_Grises.repaintImage();
       
    }
        if(path.contains("BlancoNegro")){
            Imagen_BN.setPath(path);
            Imagen_BN.repaintImage();
        }
        if(path.contains("Cromatico")){
            Imagen_Cromatico.setPath(path);
            Imagen_Cromatico.repaintImage();
        }
    }

}
