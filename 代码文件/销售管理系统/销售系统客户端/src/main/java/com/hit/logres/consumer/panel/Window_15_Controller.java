package com.hit.logres.consumer.panel;

import com.hit.logres.api.entity.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Window_15_Controller implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main_Controller controller = (Main_Controller) Starter.controllers.get("Main_Controller");
        controller.control = this;

    }

    public Integer ID;	//标识ID

    @FXML
    public Pane pane;

    @FXML
    public JFXTextField text0, text1, text2;

    @FXML
    public JFXButton butDefault;

    @FXML
    void butCloseHandler(ActionEvent event) {    //保存事件
        Main_Controller controller = (Main_Controller) Starter.controllers.get("Main_Controller");

        if(Tools.isNumber(text0.getText()) &&
           Tools.isNumber(text1.getText()) &&
           Tools.isNumber(text2.getText())){

            controller.weight0 = Integer.valueOf(text0.getText());
            controller.weight1 = Integer.valueOf(text1.getText());
            controller.weight2 = Integer.valueOf(text2.getText());

            Stage stage =(Stage)pane.getScene().getWindow();
            stage.close();
        }
        else {
           controller.showLog("权重需为有效整数！");
        }

    }
}
