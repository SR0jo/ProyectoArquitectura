package org.example;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class PruebaPendriveConHilo {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        AtomicBoolean continuar = new AtomicBoolean(true);
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File usb = null;

        boolean conectado = false;
        boolean bucle = true;
        char opcion=' ';
        int primero = 0;
        int accion = 0;

        do{
            File[] roots = File.listRoots();
            for (File root:roots) {
                String type = fsv.getSystemTypeDescription(root);
                //System.out.println(type);
                if(type.equals("Unidad de USB")){
                    conectado = true;
                    bucle = false;
                    usb = new File(root.getAbsolutePath());
                }
            }

            if(!conectado) {
                System.out.println("No se encontró ninguna Unidad de USB");
                System.out.println("Ingrese 'S' para continuar");
                opcion = in.next().toUpperCase().charAt(0);

                if(opcion != 'S'){
                    bucle = false;
                }
            }

        }while(bucle);

        if(conectado) {

            Thread hilo = new Thread(new HiloDePrueba(fsv, continuar));
            hilo.start();

            do {
                if (primero == 0) {
                    System.out.println("Pendrive encontrado en ruta: " + usb.getAbsolutePath());
                    primero = 1;
                }
                System.out.println("Que desea hacer? Elija una opcion");
                System.out.println("1. Ver el contenido del Pendrive");
                System.out.println("2. Crear un Archivo de texto");
                System.out.println("3. Crear una carpeta");
                System.out.println("4. Salir");
                accion = in.nextInt();

                if (continuar.get()) {
                    switch (accion) {
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
                            continuar.set(false);
                            break;
                        default:
                            System.out.println("Opcion no Valida");
                            break;
                    }
                }
            } while (continuar.get());
        }
    }
}
