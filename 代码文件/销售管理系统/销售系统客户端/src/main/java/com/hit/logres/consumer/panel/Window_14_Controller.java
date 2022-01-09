package com.hit.logres.consumer.panel;

import com.hit.logres.api.entity.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Window_14_Controller implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main_Controller controller = (Main_Controller) Starter.controllers.get("Main_Controller");
        controller.control = this;

    }

    public Integer ID;	//标识ID

    @FXML
    public Pane pane;

    @FXML
    public JFXTextField text0, text1, text2, text3;

    @FXML
    public JFXToggleButton but00, but10, but11, but12, but20, but21, but30, but31, but40, but41, but50, but51, but60, but70;

    @FXML
    public JFXButton butDefault;

    @FXML
    void butSaveHandler(ActionEvent event) {    //保存事件
        User user = Starter.us.findUserById(ID);
        String authority = "";

        if(but00.isSelected())
            authority = authority.concat("1");
        else
            authority = authority.concat("0");

        if(but12.isSelected())
            authority = authority.concat("3");
        else if(but11.isSelected())
            authority = authority.concat("2");
        else if(but10.isSelected())
            authority = authority.concat("1");
        else
            authority = authority.concat("0");

        if(but21.isSelected())
            authority = authority.concat("2");
        else if(but20.isSelected())
            authority = authority.concat("1");
        else
            authority = authority.concat("0");

        if(but31.isSelected())
            authority = authority.concat("2");
        else if(but30.isSelected())
            authority = authority.concat("1");
        else
            authority = authority.concat("0");

        if(but41.isSelected())
            authority = authority.concat("2");
        else if(but40.isSelected())
            authority = authority.concat("1");
        else
            authority = authority.concat("0");

        if(but51.isSelected())
            authority = authority.concat("2");
        else if(but50.isSelected())
            authority = authority.concat("1");
        else
            authority = authority.concat("0");

        if(but60.isSelected())
            authority = authority.concat("1");
        else
            authority = authority.concat("0");

        if(but70.isSelected())
            authority = authority.concat("1");
        else
            authority = authority.concat("0");

        user.setAuthority(authority);

        Starter.us.updateUser(user);

        Stage stage =(Stage)pane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void butToggleHandler(ActionEvent event) {
        if(!but10.isSelected()){
            but11.setSelected(false);
            but12.setSelected(false);
        }
        if(!but11.isSelected()){
            but12.setSelected(false);
        }
        if(!but20.isSelected()){
            but21.setSelected(false);
        }
        if(!but30.isSelected()){
            but31.setSelected(false);
        }
        if(!but40.isSelected()){
            but41.setSelected(false);
        }
        if(!but50.isSelected()){
            but51.setSelected(false);
        }
    }
}
