package com.hit.logres.consumer.panel;

import com.hit.logres.api.entity.Invoice;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Info_11_Controller  implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main_Controller controller = (Main_Controller) Starter.controllers.get("Main_Controller");
        controller.control = this;
    }

    public Integer ID;	//标识ID

    @FXML
    public JFXTextField text0, text1, text2, text3;

    @FXML
    void butHandler(ActionEvent event) {    //显示订单信息

        Main_Controller controller = (Main_Controller) Starter.controllers.get("Main_Controller");

        List<Invoice> list = Starter.is.findInvoiceByGoodId(ID);

        controller.tabpaneMain.getSelectionModel().select(controller.tabTicket);
        controller.tabpaneTicket.getSelectionModel().select(controller.tabOrder);

        controller.controlOrderAbs = new ArrayList<>();
        controller.listMap.get(controller.vboxOrderAbs).clear();
        if(list != null){
            for(int i = 0; i < list.size(); i++) {
                controller.listMap.get(controller.vboxOrderAbs).add(controller.loadNode("/But_01_Order.fxml"));
                ((But_01_Controller)controller.control).button.setText(Starter.cs.findCustomerById(list.get(i).getCustomerId()).getName()
                        + " " + list.get(i).getId());
                ((But_01_Controller)controller.control).ID = list.get(i).getId();

                controller.controlOrderAbs.add(controller.control);
            }
        }
        if(controller.controlOrderAbs.size() == 0){
            controller.controlOrderAbs = null;
        }

        controller.initVbox(controller.vboxOrderAbs);

    }

}
