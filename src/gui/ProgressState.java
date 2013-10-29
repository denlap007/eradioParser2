package gui;

import java.util.Observable;

public class ProgressState extends Observable {

    private final int WEIGHT_IN_COMPUTATION1 = 30;
    private final int WEIGHT_IN_COMPUTATION2 = 35;
    private int curProgressPart1 = 0;
    private int curProgressPart2 = 0;
    //<editor-fold defaultstate="collapsed" desc="this is the class that holds the current progress so that the Progress Bars is updated!">

    /**
     * @return the curProgress
     */
    public int getCurProgressPart1() {
        return curProgressPart1;
    }

    /**
     * @param curProgress the curProgress to set
     */
    public void setCurProgress(int curProgress) {
        if (curProgress <= WEIGHT_IN_COMPUTATION1) {
            curProgressPart1 = curProgress;
        } else if (curProgress <= (WEIGHT_IN_COMPUTATION1 + WEIGHT_IN_COMPUTATION2)) {
            curProgressPart2 = curProgress;
        }

        setChanged();
        notifyObservers(curProgress);
    }
    //</editor-fold>  

    /**
     * @return the curProgressPart2
     */
    public int getCurProgressPart2() {
        return curProgressPart2;
    }
}
