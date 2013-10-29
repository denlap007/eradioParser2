package gui;

import java.util.Random;
import javax.swing.SwingWorker;


public class Tasker extends SwingWorker<Void, Void>{

    @Override
    protected Void doInBackground(){
        int progress = 0;
        Random random = new Random();
        //Initialize progress property.
        setProgress(0);
            
        //Sleep for at least one second to simulate "startup".
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignore) {}
        
        while (progress < 100) {
            //Sleep for up to one second.
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException ignore) {}
            //Make random progress.
            progress += random.nextInt(10);
            setProgress(Math.min(progress, 100));
        }
        return null;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
