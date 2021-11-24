package com.hit.userinterface;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Starter extends Application {

	private Stage primaryStage;
    private AnchorPane rootLayout;
    
    public static Map<String, Object> controllers = new HashMap<String, Object>();
	
	@Override
	public void start(Stage primaryStage) {
		
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle(" ");

        initRootLayout();
	}

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            System.out.println("Hey");
            System.out.println(System.getProperty("user.dir"));
            loader.setLocation(Starter.class.getResource("/GUI.fxml"));
            rootLayout = (AnchorPane) loader.load();
            
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            //scene.getStylesheets().add("GUICSS.css");
            primaryStage.setScene(scene);
            //primaryStage.setResizable(false);
            primaryStage.setMaxWidth(617);
            primaryStage.setMinWidth(617);
            primaryStage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
