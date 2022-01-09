package com.hit.logres.consumer.panel;

import com.hit.logres.api.entity.Invoice;
import com.hit.logres.api.entity.InvoiceLine;
import com.hit.logres.api.entity.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Window_09_Controller  implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main_Controller controller = (Main_Controller)Starter.controllers.get("Main_Controller");
        controller.control = this;

        User user = Starter.us.findUserById(controller.userID);

        ID = controller.controlCurrentPayment.ID;

        Invoice invoice = Starter.is.findInvoiceById(ID);
        List<InvoiceLine> list = Starter.ils.findInvoiceLineByInvoiceId(invoice.getId());

        text0.setText(Starter.cs.findCustomerById(invoice.getCustomerId()).getName());
        text1.setText(invoice.getId().toString());
        text2.setText(Tools.formatTimestamp(invoice.getTime()));
        text3.setText(Tools.formatBigDecimal(invoice.getTotalPrice()));
        text4.setText(Starter.us.findUserById(invoice.getUserId()).getUsername());
        text5.setText(invoice.getStatus());
        text7.setText(Tools.formatBigDecimal(invoice.getGrossMargin()));

        controller.cleanVBox(vbox0);
        for(int i = 0; i < list.size(); i++) {

            Node node = controller.loadNode("/Info_04_Order.fxml");
            Info_04_Controller controller1 = (Info_04_Controller) controller.control;
            controller1.text0.setText(Starter.gs.findGoodById(list.get(i).getGoodId()).getName());
            controller1.text1.setText(Tools.formatBigDecimal(list.get(i).getUnitPrice()));
            controller1.text2.setText(list.get(i).getAmount().toString());
            controller1.text3.setText(Starter.ws.findWarehouseById(list.get(i).getWarehouseId()).getName());

            controller.addtoVBox(vbox0, node);
        }

    }

    public Integer ID;	//标识ID

    @FXML
    public Pane pane;

    @FXML
    public JFXTextField text0, text1, text2, text3, text4, text5, text7;

    @FXML
    public VBox vbox0;

    @FXML
    public JFXButton butDefault;

    @FXML
    void butCloseHandler(ActionEvent event) {	//关闭窗口
        Stage stage =(Stage)pane.getScene().getWindow();
        stage.close();
    }

}
