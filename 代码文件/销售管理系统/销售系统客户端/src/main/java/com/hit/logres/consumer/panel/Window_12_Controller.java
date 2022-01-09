package com.hit.logres.consumer.panel;

import com.hit.logres.api.entity.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Window_12_Controller implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = (Window_13_Controller)Starter.controllers.get("Window_13_Controller");

        ObservableList<String> list  =  FXCollections.observableArrayList(new ArrayList<String>());
        list.add("男");
        list.add("女");
        cbb0.setItems(list);
    }

    Window_13_Controller controller;

    public Integer ID;	//标识ID

    @FXML
    public Pane pane;

    @FXML
    public JFXTextField text0, text1, text2, text3;

    @FXML
    public JFXComboBox<String> cbb0;

    @FXML
    public JFXButton butDefault;

    @FXML
    void butSaveHandler(ActionEvent event){   //注册

        if(Starter.us.findUserByName(text2.getText()) != null
           && Starter.us.findUserByName(text2.getText()).size() != 0){
            controller.showLog("账号已存在！");
            return;
        }


        User user = new User(
                0,
                "11111111",     //默认有全使用权限
                text3.getText(),
                text2.getText(),
                text0.getText(),
                text1.getText(),
                cbb0.getSelectionModel().getSelectedItem()
        );

        Starter.us.insertUser(user);

        controller.showLog("注册成功！");
        Stage stage =(Stage)pane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void butCloseHandler(ActionEvent event) {   //关闭
        Stage stage =(Stage)pane.getScene().getWindow();
        stage.close();
    }


}
