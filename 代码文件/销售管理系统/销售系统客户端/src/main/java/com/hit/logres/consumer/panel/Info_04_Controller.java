package com.hit.logres.consumer.panel;

import com.hit.logres.api.entity.User;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Info_04_Controller  implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Main_Controller controller = (Main_Controller)Starter.controllers.get("Main_Controller");
		controller.control = this;

		User user = Starter.us.findUserById(controller.userID);

	}

	public Integer ID;	//标识ID

	@FXML
	public Pane pane;

	@FXML
	public JFXTextField text0, text1, text2, text3;
}
