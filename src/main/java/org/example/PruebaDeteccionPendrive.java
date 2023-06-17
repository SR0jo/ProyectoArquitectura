package org.example;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class PruebaDeteccionPendrive {
    public static void main(String[] args) {
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
}
