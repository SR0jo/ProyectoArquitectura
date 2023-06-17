package org.example;

import java.io.File;
import java.io.IOException;


public class  PruebaLecturaDeArchivos {
    public static void main(String[] args){
        File file = new File("D:\\prueba.txt");
        try{
            file.createNewFile();
        }catch(IOException e){
            System.out.println("Fallo");
        }
        System.out.println(file.getAbsolutePath());
    }
}
