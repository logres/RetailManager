package com.hit.logres.consumer.panel;

import com.hit.logres.api.entity.Customer;
import com.hit.logres.api.entity.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Window_10_Controller  implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Main_Controller controller = (Main_Controller)Starter.controllers.get("Main_Controller");
		controller.control = this;

		User user = Starter.us.findUserById(controller.userID);

		Tools.setClientRankCbb(cbb0);
	}

	public Integer ID;	//标识ID

	@FXML
	private Pane pane;

	@FXML
	public TextField text0, text1, text3;

	@FXML
	public JFXComboBox<String> cbb0;

	@FXML
	public TextArea textArea0;

	@FXML
	public JFXButton butDefault;

	@FXML
	void butAddHandler(ActionEvent event) {	//添加客户信息

		Main_Controller controller = (Main_Controller)Starter.controllers.get("Main_Controller");

		if(Tools.isInteger(text1.getText())) {


			Customer cus = new Customer(
					0,
					text0.getText(),
					text1.getText(),
					text3.getText(),
					cbb0.getSelectionModel().getSelectedItem(),
					textArea0.getText());

			Starter.cs.insertCustomer(cus);
			controller.showLog("添加客户成功！");

		}
		else{
			controller.showLog("输入格式错误！");
		}

		controller.initListMap(controller.vboxClient);
		controller.initVbox(controller.vboxClient);
		Stage stage =(Stage)pane.getScene().getWindow();
		stage.close();
	}

	@FXML
	void butCloseHandler(ActionEvent event) {	//关闭窗口
		Stage stage =(Stage)pane.getScene().getWindow();
		stage.close();
	}
}
