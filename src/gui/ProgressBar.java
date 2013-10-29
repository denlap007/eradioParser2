package gui;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JProgressBar;

public class ProgressBar implements Observer {

    private JProgressBar progressBar;
    private int latestProgress = 0;

    public ProgressBar(JProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    @Override
    public void update(Observable o, Object arg) {
        progressBar.setIndeterminate(false);
        progressBar.setStringPainted(true);
        if (arg != latestProgress) {
            progressBar.setValue((int) arg);
            progressBar.setStringPainted(true);
            //System.out.println("Current progress updated to : " + arg);
            latestProgress = (int) arg;
        }

        //System.out.println("Current progress: " + arg);

    }
}
