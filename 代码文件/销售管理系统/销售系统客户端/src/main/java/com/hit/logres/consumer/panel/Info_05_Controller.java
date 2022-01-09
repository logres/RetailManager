package com.hit.logres.consumer.panel;

import com.hit.logres.api.entity.Invoice;
import com.hit.logres.api.entity.InvoiceLine;
import com.hit.logres.api.entity.User;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.Timestamp;
import java.util.List;
import java.util.ResourceBundle;

public class Info_05_Controller  implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Main_Controller controller = (Main_Controller)Starter.controllers.get("Main_Controller");
		controller.control = this;

		timestamp = new Timestamp(System.currentTimeMillis());
		text0.setText(Tools.formatTimestamp(timestamp));
	}

	public Integer ID;	//标识ID

	public Timestamp timestamp;

	@FXML
	public Pane pane;

	@FXML
	public JFXTextField text0, text1, text2, text3;

	@FXML
	void butDeleteHandler(ActionEvent event) {	//删除支付信息
		Main_Controller controller = (Main_Controller)Starter.controllers.get("Main_Controller");

		User user = Starter.us.findUserById(controller.userID);

		Invoice invoice = Starter.is.findInvoiceById(Starter.ps.findPaymentById(ID).getInvoiceId());

		Starter.ps.deletePayment(ID);
		controller.initListMap(controller.vboxPayment);
		controller.initVbox(controller.vboxPayment);

		controller.updateInvoiceState(invoice);


		controller.controlPayment.remove(this);

	}
}
