package com.aaron.examen;

import com.aaron.examen.util.AlertUtils;
import com.aaron.examen.util.TareaTask;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

public class AppController {
    public TextField tfInicial, tfFinal;
    public Button btIniciar,btPausar,btDetener;
    public ProgressBar pbProgreso;
    public Label lbTarea, lbPorcentaje,lbEstado;
    private boolean pausado = false;
    private TareaTask tareaTask;

    @FXML
    public void iniciar(ActionEvent event){
        int inicial = Integer.parseInt(tfInicial.getText());
        int tfinal = Integer.parseInt(tfFinal.getText());

        if (inicial>=tfinal){
            AlertUtils.mostrarAlerta("El tiempo final tiene que ser mayor que el inicial");
            return;
        }
        tareaTask = new TareaTask(inicial,tfinal);
        pbProgreso.progressProperty().unbind();
        pbProgreso.progressProperty().bind(tareaTask.progressProperty());

        tareaTask.messageProperty().addListener((observableValue, valorAntiguo, valorNuevo) -> {
            int porc=Integer.valueOf(valorNuevo)*100/tfinal;
            if (porc == 50){
                lbEstado.setText("Completado el 50%");
            }
            if (porc == 75){
                lbEstado.setText("Completado el 75%");
            }
            lbPorcentaje.setText(porc+"% Completado");
        });
        tareaTask.messageProperty().addListener((observableValue, valorAntiguo, valorNuevo) ->{
            lbTarea.setText(valorNuevo+" de "+tfinal);
        });
        tareaTask.stateProperty().addListener((observableValue, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED){
                AlertUtils.mostrarFinal("Tarea terminada");
            }
        });
        new Thread(tareaTask).start();
    }

    @FXML
    public void pausar(ActionEvent event){
        if(!pausado){
            tareaTask.setPausa(true);
            pausado = !pausado;
            btPausar.setText("Continuar");
            lbEstado.setText("Tarea pausada");
        }else {
            tareaTask.setPausa(false);
            pausado = !pausado;
            btPausar.setText("Pausar");
            lbEstado.setText("");
        }
    }

    @FXML
    public void detener(ActionEvent event){
        tareaTask.cancel();
    }
}
