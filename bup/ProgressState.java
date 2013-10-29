package gui;

import java.util.Observable;

public class ProgressState extends Observable{
    private int curProgress =0;
    //<editor-fold defaultstate="collapsed" desc="this is the class that holds the current progress so that the Progress Bars is updated!">
    

    /**
     * @return the curProgress
     */
    public int getCurProgress() {
        return curProgress;
    }

    /**
     * @param curProgress the curProgress to set
     */
    public void setCurProgress(int curProgress) {
        this.curProgress = curProgress;
    }
    
    
      //</editor-fold>  
}
