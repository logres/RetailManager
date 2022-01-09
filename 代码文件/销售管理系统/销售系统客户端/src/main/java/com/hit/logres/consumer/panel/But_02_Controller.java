package com.hit.logres.consumer.panel;

import com.hit.logres.api.entity.Invoice;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class But_02_Controller  implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Main_Controller controller = (Main_Controller)Starter.controllers.get("Main_Controller");
		controller.control = this;
	}

	public Integer ID;	//标识ID

	@FXML
	public JFXButton button;

	@FXML
	void butHandler(ActionEvent event) {
		Main_Controller controller = (Main_Controller)Starter.controllers.get("Main_Controller");
		controller.controlCurrentPayment = this;

		controller.initPayment();
		controller.setDisplacePayment();
	}

}
