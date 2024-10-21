package com.example.demo.javafx;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;


public class MainContralador extends Application {
    @Override
    public void start(Stage escenarioPrincipal) {
        try {
            VBox raiz = (VBox)FXMLLoader.load(getClass().getResource("Ejemplo.fxml"));
            Scene escena = new Scene(raiz);
            escenarioPrincipal.setScene(escena);
            escenarioPrincipal.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
