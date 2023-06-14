import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;

public class PruebaCrearEnPendrive {
    public static void main(String[] args) {
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File[] roots = File.listRoots();

        for (File root : roots) {
            String type = fsv.getSystemTypeDescription(root);
            if (type.equals("Unidad de USB")) {
                File file = new File(root.getAbsolutePath()+"\\prueba");
                try{
                    //file.createNewFile();
                    file.mkdir();
                }catch(Exception e){
                    System.out.println("Pelotudo");
                }
                System.out.println(file.getAbsolutePath());
            }
        }
    }
}
