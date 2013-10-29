
package gui;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JProgressBar;


public class ProgressBar implements Observer {
    JProgressBar progressBar;
    
    public ProgressBar(JProgressBar progressBar){
        this.progressBar = progressBar;
    }

    @Override
    public void update(Observable o, Object arg) {
        progressBar.setValue((int)arg);
        System.out.println("Current progress: "+arg);
    
    }
    
    
    
}
