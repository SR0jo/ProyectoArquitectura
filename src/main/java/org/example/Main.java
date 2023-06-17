package org.example;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import net.samuelcampos.usbdrivedetector.USBDeviceDetectorManager;

public class Main {
    public void PruebaLecturaDeArchivos(){
        File file = new File("D:\\prueba.txt");
        try{
            file.createNewFile();
        }catch(IOException e){
            System.out.println("Fallo");
        }
        System.out.println(file.getAbsolutePath());
    }
    public void PruebaDeteccionPendrive(){
        FileSystemView fsv = FileSystemView.getFileSystemView();
        boolean continuar=true;
        boolean detectado=false;
        while(continuar){
            File[] roots = File.listRoots();
            for (File root : roots) {
                String type = fsv.getSystemTypeDescription(root);
                System.out.println(type);
                if (type.equals("Unidad de USB")) {
                    JOptionPane.showMessageDialog(null, "Se ha detectado un pendrive en: "+root.getAbsolutePath());
                    detectado=true;
                    continuar=false;
                }
            }

            if(!detectado){
                // Opciones del menú
                Object[] opciones = {"Sí", "No"};

                // Mostrar el cuadro de diálogo con las opciones
                int seleccion = JOptionPane.showOptionDialog(null,
                        "No se detectó ningun Pendrive, desea intentar otra vez?",
                        "Error",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opciones,
                        opciones[0]);

                // Verificar la opción seleccionada y asignar un valor booleano
                continuar = seleccion == JOptionPane.YES_OPTION;
            }
        }
    }
    public void PruebaCrearEnPendrive(){
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
    public static void main(String[] args) {
        USBDeviceDetectorManager driveDetector = new USBDeviceDetectorManager();

// Mostrar todos los dispositivos de almacenamiento USB actualmente conectados
        driveDetector.getRemovableDevices().forEach(System.out::println);

// Agregar un oyente de eventos para ser notificado cuando un dispositivo de almacenamiento USB se conecte o se retire
        driveDetector.addDriveListener(System.out::println);

// Desmontar un dispositivo
        boolean uwu = true;
        while(uwu){
            try {
                if (!driveDetector.getRemovableDevices().isEmpty()) {
                    // Desmontar el primer dispositivo
                    driveDetector.unmountStorageDevice(driveDetector.getRemovableDevices().get(0));
                    System.out.println("hoaaaaa");
                    uwu = false;
                    driveDetector.close();
                }
            }catch (Exception e){
                System.out.println("hola");
            }
        }
        System.out.println("pelotudo");




    }
}
