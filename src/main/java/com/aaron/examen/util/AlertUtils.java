package com.aaron.examen.util;

import javafx.scene.control.Alert;

public class AlertUtils {
    public static void mostrarAlerta(String mensaje){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(mensaje);
        alert.show();
    }
    public static void mostrarFinal(String mensaje){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Finalizado");
        alert.setContentText(mensaje);
        alert.show();
    }
}
