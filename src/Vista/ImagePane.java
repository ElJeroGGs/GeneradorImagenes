package Vista;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePane extends JPanel {
    private BufferedImage image;

    public ImagePane(String path) {
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (image == null) {
            // Si no se pudo cargar la imagen, ponemos una leyenda (centrada) que diga "imagen no cargada"
            image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
            Graphics g = image.getGraphics();
            g.drawString("Imagen no cargada", 140, 195);

        

        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }


    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setPath(String path) {
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void repaintImage() {
        repaint();
    }
}
