import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ouvrir {
	
     public static void lancer(String filename) {
    	 
        File file = new File(filename);
        
        if (!file.exists() && file.length() < 0) {
            System.out.println("Specified file does not exist!");
            System.exit(0);
        }
        
        Desktop desktop = null;
        if (Desktop.isDesktopSupported()) {
            desktop = Desktop.getDesktop();
        }
        
        try {
            desktop.edit(file);
        } catch (IOException ex) {
            Logger.getLogger(Ouvrir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
