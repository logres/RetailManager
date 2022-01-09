package com.hit.logres.consumer.panel;

import com.hit.logres.api.entity.Good;
import com.hit.logres.api.entity.User;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Info_07_Controller implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main_Controller controller = (Main_Controller)Starter.controllers.get("Main_Controller");
        controller.control = this;

        User user = Starter.us.findUserById(controller.userID);

        List<Good> good = Starter.gs.getAllGood();
        ObservableList<String> list  =  FXCollections.observableArrayList(new ArrayList<String>());
        for(int i = 0; i < good.size(); i++){
            list.add(good.get(i).getName());
        }
        cbb.setItems(list);
        ComboBoxListener cbbListener1 = new ComboBoxListener<>(cbb);
        cbb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                List<Good> find = Starter.gs.findGoodByName(newValue);
                if(find != null && find.size() != 0){
                    text1.setText(find.get(0).getId().toString());
                }
                else {
                    text1.setText("");
                }
            }
        });
    }

    Window_07_Controller control;

    @FXML
    public Pane pane;

    @FXML
    public JFXComboBox<String> cbb;

    @FXML
    public JFXTextField text1, text2, text3;

    @FXML
    public void butDeleteHandler(ActionEvent event){ //删除
        control.list.remove(this);
        control.vbox.getChildren().remove(pane);
        control.vbox.setPrefHeight(control.vbox.getPrefHeight() - 30);

    }

}
