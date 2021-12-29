package com.example.home;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class DeviceTracker extends Application{
    @Override
    public void start(Stage stage) throws IOException {
        Parent HomeWindow = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        stage.setTitle("Device tracker");
        stage.setScene(new Scene(HomeWindow, 376,503));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}