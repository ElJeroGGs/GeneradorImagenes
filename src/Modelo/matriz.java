package Modelo;

public class matriz {
    int orden = 400;
    int[][] pixels = new int[orden][orden];
    int[] histogram = new int[256]; // Suponiendo que los valores de la matriz están en el rango [0, 255]

    public matriz(){
        //llenamos la matriz con valores al azar entre 0 y 255
        for(int i = 0; i < orden; i++){
            for(int j = 0; j < orden; j++){
                pixels[i][j] = (int)(Math.random()*255);
            }
        }
        //llenamos el histograma
        setHistogram();
    }

    public matriz(int valor) {
        //Llenamos la matriz con el mismo valor
        //llenamos la matriz con valores al azar entre 0 y 255
        for(int i = 0; i < orden; i++){
            for(int j = 0; j < orden; j++){
                pixels[i][j] = valor;
            }
        }
        //llenamos el histograma
        setHistogram();
    }

    public int[][] getMatriz(){
        return pixels;
    }

    public int[][] getMatrizBinarizada(){
        int[][] matrizBinarizada = new int[orden][orden];
        for(int i = 0; i < orden; i++){
            for(int j = 0; j < orden; j++){
                if(pixels[i][j] >= 127){
                    matrizBinarizada[i][j] = 255;
                }else{
                    matrizBinarizada[i][j] = 0;
                }
            }
        }
        return matrizBinarizada;
    }

    public void setMatriz(int[][] pixels){
        this.pixels = pixels;
    }

    public void setPixel(int x, int y, int value){
        pixels[x][y] = value;
    }

    public int getPixel(int x, int y){
        return pixels[x][y];
    }

    public void setHistogram(){
    
        for(int i = 0; i < orden; i++){
            for(int j = 0; j < orden; j++){
                histogram[pixels[i][j]]++;
            }
        }
        
    }

    public int[] getHistogram(){
        return histogram;
    }

    public int getMaxValue(){
        int max = Integer.MIN_VALUE;
        for (int value : histogram) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public int getOrden() {
        return orden;
    }

}
