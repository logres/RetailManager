package com.hit.logres.consumer.panel;

import com.hit.logres.api.entity.Customer;
import com.hit.logres.api.entity.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Window_06_Controller  implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Main_Controller controller = (Main_Controller)Starter.controllers.get("Main_Controller");
		controller.control = this;

		User user = Starter.us.findUserById(controller.userID);

		if(Integer.valueOf(user.getAuthority().charAt(5)) < 2 + '0'){
			butEdit.setVisible(false);
		}

		Tools.setClientRankCbb(cbb0);
	}

	public Integer ID;	//标识ID

	public boolean flag = false;

	@FXML
	public JFXButton butEdit, butDefault;

	@FXML
	public Pane pane;

	@FXML
	public JFXTextField text0, text1, text3;

	@FXML
	public JFXComboBox<String> cbb0;

	@FXML
	public JFXTextArea textArea0;

	@FXML
	void butEditHandler(ActionEvent event) {	//编辑用户信息
		text0.setDisable(false);
		text1.setDisable(false);
		text3.setDisable(false);
		cbb0.setMouseTransparent(false);
		textArea0.setEditable(true);
		flag = true;
	}

	@FXML
	void butSaveHandler(ActionEvent event) {	//保存用户信息

		if(flag == true){
			Main_Controller controller = (Main_Controller)Starter.controllers.get("Main_Controller");

			if(Tools.isInteger(text1.getText())) {

				Customer cus = new Customer(
						ID,
						text0.getText(),
						text1.getText(),
						text3.getText(),
						cbb0.getSelectionModel().getSelectedItem(),
						textArea0.getText());

				Starter.cs.updateCustomer(cus);
				controller.showLog("更新客户信息成功！");

				controller.initListMap(controller.vboxClient);
				controller.initVbox(controller.vboxClient);

			}
			else{
				controller.showLog("输入格式错误！");
				return;
			}
		}

		Stage stage =(Stage)pane.getScene().getWindow();
		stage.close();
	}

}
