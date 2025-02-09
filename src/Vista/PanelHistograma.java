package Vista;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import Modelo.matriz;

public class PanelHistograma extends JPanel {
    private matriz m;

    public PanelHistograma(matriz ma) {
        this.m = ma;
        this.setBounds(50, 300, 925, 400);
        this.setVisible(true);
        // Le ponemos de fondo un color
        this.setBackground(Color.darkGray);
        // Le ponemos un borde invisible a todo el panel
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    public PanelHistograma() {
        this.setLayout(null);
        this.setBounds(50, 300, 840, 400);
        this.setVisible(true);
        // Le ponemos de fondo un color
        this.setBackground(Color.darkGray);
        // Le ponemos un borde invisible a todo el panel
        this.setBorder(new EmptyBorder(100, 100, 100, 100));
    }

    public void setMatriz(matriz m) {
        this.m = m;
        repaint(); // Redibuja el panel con los nuevos datos
    }

    public void pintaHistograma() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (m != null) {
            int[] histogramData = m.getHistogram();
            if (histogramData != null) {
                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth() - 40; // Ajustar el ancho para el borde
                int height = getHeight() - 30; // Ajustar la altura para el borde
                int barWidth = Math.max(1, width / histogramData.length); // Asegurarse de que el ancho de la barra sea
                                                                          // al menos 1
                int offsetX = 20; // Desplazamiento en X para el borde
                int offsetY = 15; // Desplazamiento en Y para el borde

                // Dibujar las barras del histograma
                for (int i = 0; i < histogramData.length; i++) {
                    int barHeight = (int) (((double) histogramData[i] / m.getMaxValue()) * (height - 50)); // Dejar
                                                                                                           // espacio
                                                                                                           // para las
                                                                                                           // etiquetas
                    int grayValue = (int) (((double) i / 255) * 255); // Calcular el valor de gris
                    g2d.setColor(new Color(grayValue, grayValue, grayValue)); // Establecer el color en escala de grises
                    g2d.fillRect(i * barWidth + offsetX + 20, height - barHeight - offsetY, barWidth, barHeight); // Dejar
                                                                                                                  // espacio
                                                                                                                  // para
                                                                                                                  // las
                                                                                                                  // etiquetas
                }

                // Dibujar los valores de 0 a 255 en el eje x
                g2d.setColor(Color.WHITE);
                for (int i = 0; i <= 255; i += 25) { // Mostrar cada 25 valores para evitar superposición
                    int x = i * barWidth + offsetX + 20;
                    g2d.drawString(String.valueOf(i), x, height + offsetY - 5);
                }

                // Dibujar los valores en el eje y
                int maxValue = m.getMaxValue();
                for (int i = 0; i <= maxValue; i += maxValue / 10) { // Mostrar 10 divisiones en el eje y
                    int y = height - offsetY - (int) (((double) i / maxValue) * (height - 50));
                    g2d.drawString(String.valueOf(i), offsetX - 10, y);
                }
            }
        }
    }

    public matriz getMatriz() {
        return m;
    }
}
