package com.example.demo.javafx;




import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class EjemploControlador {
    @FXML
    private Button btCerrar;

    @FXML
    private void cerrar() {
        System.exit(0);
    }
}

