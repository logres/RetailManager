package com.hit.logres.consumer.panel;

import com.hit.logres.api.entity.Customer;
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

public class Info_08_Controller implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main_Controller controller = (Main_Controller) Starter.controllers.get("Main_Controller");
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
                List<Good> list = Starter.gs.findGoodByName(newValue);

                if(list != null && list.size() != 0){
                    text1.setText(Starter.ws.findWarehouseById(list.get(0).getDefaultWarehouseId()).getName());
                    text0.setText(Tools.formatBigDecimal(list.get(0).getPriceA()));
                }
            }
        });
    }

    public Integer ID;	//标识ID

    @FXML
    public Pane pane;

    @FXML
    public JFXComboBox<String> cbb;

    @FXML
    public JFXTextField text0, text1, text2;

    @FXML
    void butDeleteHandler(ActionEvent event) {	//删除POS条目信息
        Main_Controller controller = (Main_Controller)Starter.controllers.get("Main_Controller");

        controller.listMap.get(controller.vboxPOS).remove(pane);
        controller.controlPOS.remove(this);
        controller.initVbox(controller.vboxPOS);
        controller.calculatePOS();
    }

}
