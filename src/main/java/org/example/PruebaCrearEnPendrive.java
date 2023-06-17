package org.example;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.Objects;
import java.util.Scanner;

public class PruebaCrearEnPendrive {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File usb = null;

        boolean continuar = true;
        boolean conectado = false;
        char opcion = ' ';
        int accion = 0;
        int primero = 0;

        do{
            conectado = false;
            File[] roots = File.listRoots();
            for (File root:roots) {
                String type = fsv.getSystemTypeDescription(root);
                if(type.equals("Unidad de USB")){
                    conectado = true;
                    usb = new File(root.getAbsolutePath());
                }
            }

            if(!conectado){
                System.out.println("No se encontró ninguna Unidad de USB");
                System.out.println("Desea continuar? (S/N)");
                opcion = in.next().toUpperCase().charAt(0);
                switch (opcion){
                    case 'S':
                        continuar = true;
                        break;
                    case 'N':
                        continuar = false;
                        break;
                    default:
                        continuar = false;
                        System.out.println("Opcion Invalida. Finalizando");
                        break;
                }
            }else{
                if(primero==0){
                    System.out.println("Pendrive encontrado en ruta: "+usb.getAbsolutePath());
                    primero=1;
                }
                System.out.println("Que desea hacer? Elija una opcion");
                System.out.println("1. Ver el contenido del Pendrive");
                System.out.println("2. Crear un Archivo de texto");
                System.out.println("3. Crear una carpeta");
                System.out.println("4. Salir");
                accion = in.nextInt();

                switch (accion){
                    case 1:
                        String[] dir = usb.list();
                        for (int i = 0; i < Objects.requireNonNull(dir).length; i++) {
                            System.out.println(dir[i]);
                        }
                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opcion no Valida");
                        break;
                }
            }

        }while(continuar);
    }
}
