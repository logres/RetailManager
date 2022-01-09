package com.hit.logres.consumer.panel;

import com.hit.logres.api.entity.Customer;
import com.hit.logres.api.entity.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Info_09_Controller implements Initializable {

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
    void butInfoHandler(ActionEvent event) {	//显示职员信息
        Main_Controller controller = (Main_Controller)Starter.controllers.get("Main_Controller");
        controller.showNewWindow("/Window_14_UserInfo.fxml");

        Window_14_Controller control = (Window_14_Controller)controller.control;
        User user = Starter.us.findUserById(ID);
        control.ID = ID;
        control.text0.setText(user.getName());
        control.text1.setText(user.getUsername());
        control.text2.setText(user.getGender());
        control.text3.setText(user.getPhoneNumber());

        String authority = user.getAuthority();




        if(Integer.valueOf(authority.charAt(0)) >= 1 + '0'){
            control.but00.setSelected(true);
        }

        if(Integer.valueOf(authority.charAt(1)) >= 1 + '0'){
            control.but10.setSelected(true);
        }
        if(Integer.valueOf(authority.charAt(1)) >= 2 + '0'){
            control.but11.setSelected(true);
        }
        if(Integer.valueOf(authority.charAt(1)) >= 3 + '0'){
            control.but12.setSelected(true);
        }

        if(Integer.valueOf(authority.charAt(2)) >= 1 + '0'){
            control.but20.setSelected(true);
        }
        if(Integer.valueOf(authority.charAt(2)) >= 2 + '0'){
            control.but21.setSelected(true);
        }

        if(Integer.valueOf(authority.charAt(3)) >= 1 + '0'){
            control.but30.setSelected(true);
        }
        if(Integer.valueOf(authority.charAt(3)) >= 2 + '0'){
            control.but31.setSelected(true);
        }

        if(Integer.valueOf(authority.charAt(4)) >= 1 + '0'){
            control.but40.setSelected(true);
        }
        if(Integer.valueOf(authority.charAt(4)) >= 2 + '0'){
            control.but41.setSelected(true);
        }

        if(Integer.valueOf(authority.charAt(5)) >= 1 + '0'){
            control.but50.setSelected(true);
        }
        if(Integer.valueOf(authority.charAt(5)) >= 2 + '0'){
            control.but51.setSelected(true);
        }

        if(Integer.valueOf(authority.charAt(6)) >= 1 + '0'){
            control.but60.setSelected(true);
        }

        if(Integer.valueOf(authority.charAt(7)) >= 1 + '0'){
            control.but70.setSelected(true);
        }

    }

    @FXML
    void butDeleteHandler(ActionEvent event) {	//删除职员信息
        Main_Controller controller = (Main_Controller)Starter.controllers.get("Main_Controller");

        Starter.us.deleteUser(ID);

        controller.listMap.get(controller.vboxUser).remove(pane);
        controller.initVbox(controller.vboxUser);
    }

}
