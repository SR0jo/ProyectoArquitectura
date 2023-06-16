import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class HiloDePrueba implements Runnable{
    private final FileSystemView fsv;
    private boolean bucle = true;
    private final AtomicBoolean continuar;

    public HiloDePrueba(FileSystemView fsv, AtomicBoolean continuar) {
        this.fsv = fsv;
        this.continuar = continuar;
    }

    @Override
    public void run() {
        while(bucle){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            continuar.set(false);
            File[] roots = File.listRoots();
            for (File root:roots) {
                String type = fsv.getSystemTypeDescription(root);
                if(type.equals("Unidad de USB")){
                    continuar.set(true);
                }
            }

            if(!continuar.get()){
                bucle = false;
                System.out.println("Error. Pendrive Extraido");
            }
        }
    }
}
