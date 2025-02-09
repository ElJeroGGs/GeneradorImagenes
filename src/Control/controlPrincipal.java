package Control;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Modelo.Image;
import Modelo.matriz;
import Vista.VistaPrincipal;

public class controlPrincipal {

   private VistaPrincipal vista;
   private matriz m;
   private int imageCounterGrises;
   private int imageCounterByN;
   private int imageCounterCromatico;
   private Image img;

    public controlPrincipal() {
        
    }

    public matriz getMatriz() {
        if (m == null) {
            m = new matriz(0);
        }
        return m;
    }


    public void generarHistograma(){
        vista.pintaHistograma();
    }

    public void setVista(VistaPrincipal vista) {
        this.vista = vista;
        vista.setCtrl(this);
    }
    public void setMatriz(matriz m){
        this.m = m;
    }


    public void generarMatriz() {
        matriz ma = new matriz();
        this.m = ma;
        vista.setMatriz(m);
    }

   
    //Metodo para leer el contador de imagenes generadas
    private int readCounter(String COUNTER_FILE) {
        try (BufferedReader reader = new BufferedReader(new FileReader(COUNTER_FILE))) {
            return Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return 0; // Si no se puede leer el archivo, empezamos desde 0
        }
    }
    //Metodo para escribir el contador de imagenes generadas

    public void writeCounter(int counter, String COUNTER_FILE) {
        try (FileWriter writer = new FileWriter(COUNTER_FILE)) {
            writer.write(Integer.toString(counter));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void generarImagen(String tipo){

        int width = m.getOrden();
        int height = m.getOrden();
        img = new Image(width, height);
        img.setMatriz(m);
        int imageCounter = -1;
        String tipoImagen = "error";


        switch (tipo) {
            case "grises":
            tipoImagen = "EscalaGrises";
            //Guardamos el contador actual
            imageCounter = readCounter("src\\imagenes_EscalaGrises\\counter.txt");
            //Generamos la imagen en escala de grises
            img.generarImagenGrises(imageCounter);
            
                break;

            case "byn":
            tipoImagen = "BlancoNegro";
            //Guardamos el contador actual
            imageCounter = readCounter("src\\imagenes_BlancoNegro\\counter.txt");
            //Generamos la imagen en blando y negro (binario)
            img.generarImagenByN(imageCounter);
            break;

            case "cromatico":
            tipoImagen = "Cromatico";
            //Guardamos el contador actual
            imageCounter = readCounter("src\\imagenes_Cromatico\\counter.txt");
            //Generamos la imagen en cromatico
            img.generarImagenCromatico(imageCounter);
            break;

        
            default:
                break;
        }

        //Incrementamos el nuevo contador
            imageCounter++;
            writeCounter(imageCounter, "src\\imagenes_"+tipoImagen+"\\counter.txt");

            imageCounter--;
            String fileName = "src\\imagenes_"+tipoImagen+"\\imagen" + imageCounter + ".jpg";

            //Colocar imagen en su respectivo ImagePane
            vista.setImagen(fileName);

            //Abrir imagen
            //AbrirImagen(fileName);
            
        
    }

    public void AbrirImagen(String path){
        try {
            Desktop.getDesktop().open(new File(path));
        } catch (IOException e) {
            System.out.println("No se pudo abrir el archivo de la imagen en " + path);
            e.printStackTrace();
        }
    }
    

  

}
