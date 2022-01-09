package com.hit.logres.consumer.panel;

import com.hit.logres.api.entity.User;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Window_01_Controller implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main_Controller controller = (Main_Controller)Starter.controllers.get("Main_Controller");
        if(controller != null){
            controller.control = this;
        }

        Window_13_Controller controller1 = (Window_13_Controller)Starter.controllers.get("Window_13_Controller");
        if(controller1 != null){
            controller1.control = this;
        }

    }

    @FXML
    public Pane pane;

    @FXML
    public JFXTextArea info;

    @FXML
    void butCloseHandler(ActionEvent event) {	//关闭窗口
        Stage stage =(Stage)pane.getScene().getWindow();
        stage.close();
    }
}
