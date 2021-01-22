package com.aaron.examen.util;

import javafx.concurrent.Task;

public class TareaTask extends Task<Void> {
    private boolean pausa;
    private int inicial, tfinal;

    public TareaTask(int inicial, int tfinal){
        this.inicial = inicial;
        this.tfinal = tfinal;
    }

    public boolean isPausa() {
        return pausa;
    }

    public void setPausa(boolean pausa) {
        this.pausa = pausa;
    }

    @Override
    protected Void call() throws Exception {
        while (tfinal>inicial){
            if (pausa){
                Thread.sleep(1000);
                continue;
            }
            Thread.sleep(1000);
            updateProgress((double)inicial/tfinal,1);
            updateMessage(String.valueOf(inicial));
            if (isCancelled()){
                return null;
            }
            inicial++;
        }
        updateMessage(String.valueOf(inicial));
        updateProgress(1,1);
        return null;
    }
}
