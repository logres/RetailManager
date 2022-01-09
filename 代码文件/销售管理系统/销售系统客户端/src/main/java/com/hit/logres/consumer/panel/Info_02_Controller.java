package com.hit.logres.consumer.panel;

import com.hit.logres.api.entity.Customer;
import com.hit.logres.api.entity.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Info_02_Controller  implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Main_Controller controller = (Main_Controller)Starter.controllers.get("Main_Controller");
		controller.control = this;

		User user = Starter.us.findUserById(controller.userID);

		if(Integer.valueOf(user.getAuthority().charAt(5)) < 2 + '0'){
			butDelete.setDisable(true);
		}
	}

	public Integer ID;	//标识ID

	@FXML
	public JFXButton butDelete;

	@FXML
	public Pane pane;

	@FXML
	public JFXTextField text0, text1, text2, text3;

	@FXML
	void butInfoHandler(ActionEvent event) {	//显示客户信息
		Main_Controller controller = (Main_Controller)Starter.controllers.get("Main_Controller");
		controller.showNewWindow("/Window_06_ClientInfo.fxml");

		((Window_06_Controller)controller.control).ID = ID;

		Customer cus = Starter.cs.findCustomerById(ID);
		((Window_06_Controller)controller.control).text0.setText(cus.getName());
		((Window_06_Controller)controller.control).text1.setText(cus.getPhone());
		((Window_06_Controller)controller.control).cbb0.getSelectionModel().select(cus.getType());
		((Window_06_Controller)controller.control).text3.setText(cus.getAddress());
		((Window_06_Controller)controller.control).textArea0.setText(cus.getInfo());

		((Window_06_Controller)controller.control).butDefault.requestFocus();

	}

	@FXML
	void butDeleteHandler(ActionEvent event) {	//删除客户信息
		Main_Controller controller = (Main_Controller)Starter.controllers.get("Main_Controller");

		Starter.cs.deleteCustomer(ID);

		controller.initListMap(controller.vboxClient);
		controller.initVbox(controller.vboxClient);
	}
}
