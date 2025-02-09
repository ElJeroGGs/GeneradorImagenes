package Modelo;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Control.controlPrincipal;

public class Image {
    private int width;
    private int height;
    private int[][] pixels;
    private String tipo;
    private matriz m;
    private controlPrincipal ctrl;  


    public Image(int width, int height, int[][] pixels, String tipo) {
        this.width = width;
        this.height = height;
        this.pixels = pixels;
        this.tipo = tipo;
    }

    public void setCtrl (controlPrincipal ctrle){
        this.ctrl = ctrle;
    }

    public void setMatriz(matriz matrix){

        this.m = matrix;
    }

    public Image(int width, int height) {
        this.width = width;
        this.height = height;

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void generarImagenGrises(int imageCounter) {

      
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      

        // Dibujar dependiendo de los valores de la matriz (tono de gris en escala de 0 a 255)
        int[][] ma = m.getMatriz();
        for (int i = 0; i < ma.length; i++) {
            for (int j = 0; j < ma[i].length; j++) {
                int value = ma[i][j];
                int color = new Color(value, value, value).getRGB();
                bufferedImage.setRGB(i, j, color);
            }
        }

        

         // Guardar la imagen como archivo .jpg con nombre secuencial
        String fileName = "src\\imagenes_EscalaGrises\\imagen" + imageCounter + ".jpg";
        File file = new File(fileName);
        try {
            ImageIO.write(bufferedImage, "jpg", file);
            System.out.println("Imagen guardada como " + fileName);
            

            
        } catch (IOException e) {
            e.printStackTrace();
        }


    } 
    public void generarImagenByN(int imageCounter) {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int[][] ma = m.getMatrizBinarizada();
        for (int i = 0; i < ma.length; i++) {
            for (int j = 0; j < ma[i].length; j++) {
                //Si el valor del pixel es mayor a 127 (mitad de 255) se pone en blanco, de lo contrario es negro
                int value = ma[i][j];
                int color = new Color(value, value, value).getRGB();
                bufferedImage.setRGB(i, j, color);
            }
        }

     // Guardar la imagen como archivo .jpg con nombre secuencial
     String fileName = "src\\imagenes_BlancoNegro\\imagen" + imageCounter + ".jpg";
     File file = new File(fileName);
     try {
         ImageIO.write(bufferedImage, "jpg", file);
         System.out.println("Imagen guardada como " + fileName);
         

         
     } catch (IOException e) {
         e.printStackTrace();
     }




    }
    public void generarImagenCromatico(int imageCounter){
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int[][] ma = m.getMatrizBinarizada();
        int p;
        int r;
        int t;

        for (int i = 0; i < ma.length; i++) {
            for (int j = 0; j < ma[i].length; j++) {
               
                //Verificamos 6 reglas para definir el color del pixel
                p = ma[i][j]; 
                if(i == 0)
                {
                    r=255;
                } else {
                    r = ma[i-1][j];
                }
                if(j == 0)
                {
                    t=255;
                } else {
                    t = ma[i][j-1];
                }
                int value = regla4Conexo(p, r, t);

                switch (value) {
                    case 1:
                    //Pasar al siguiente pixel ya que el actual es blanco
                    bufferedImage.setRGB(i, j, Color.WHITE.getRGB());
                        break;  
                    case 2:
                        //Generamos un nuevo color (una nueva etiqueta)
                        int[] color = generarColor();
                        int colorRGB = new Color(color[0], color[1], color[2]).getRGB();
                        bufferedImage.setRGB(i, j, colorRGB);
                        break;
                    case 3:
                    //Tomamos el color del pixel de arriba (r)
                        bufferedImage.setRGB(i, j, bufferedImage.getRGB(i-1, j));
                        break;
                    case 4:
                    //Tomamos el color del pixel de la izquierda (t)
                        bufferedImage.setRGB(i, j, bufferedImage.getRGB(i, j-1));
                        break;
                    case 5:
                    //Tomamos el color del pixel de arriba (r) 
                        bufferedImage.setRGB(i, j, bufferedImage.getRGB(i-1, j));
                        break;
                    case 6:
                    //Tomamos el color del pixel que se visito primero (r) 
                        bufferedImage.setRGB(i, j, bufferedImage.getRGB(i-1, j));
                        break;
                    default:
                    //Si no se cumple ninguna regla, se pasa al siguiente pixel
                  
                        break;
                }


              
            }
        }
        // Guardar la imagen como archivo .jpg con nombre secuencial
        String fileName = "src\\imagenes_Cromatico\\imagen" +imageCounter+ ".jpg";
        File file = new File(fileName);
        try {
            ImageIO.write(bufferedImage, "jpg", file);
            System.out.println("Imagen guardada como " + fileName);
            

            
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public int[] generarColor(){
        int[] color = new int[3];
        //Generamos un valor aleatorio entre 0 y 3
        int random = (int)(Math.random()*3);
        //Generamos un segundo valor aleatorio entre 0 y 3
        int random2 = (int)(Math.random()*3);
        //Si los valores son iguales, generamos un nuevo valor aleatorio
        while(random == random2){
            random2 = (int)(Math.random()*3);
        }
        
        
        for(int i = 0; i < 3 ; i++){
            if (i != random && i != random2){
                //Generamos un valor aleatorio entre 0 y 255
                color[i] = (int)(Math.random()*255);
            }

        }
        color[random] = 255;
        color[random2] = 0;

        
        return color;
    }

    public int regla4Conexo(int p, int r, int t){

        if(p == 255){
            return 1;
        }
        if(p == 0 && r == 255 && t == 255){
            return 2;
        }
        if(p == 0 && r != 255 && t == 255){
            return 3;
        }
        if(p == 0 && r == 255 && t != 255){
            return 4;
        }
        if(p == 0 && r != 255 && t != 255 && r == t){
            return 5;
        }
        if(p == 0 && r != 255 && t != 255 && r != t){
            return 6;
        }
        return 0;
    }

}
