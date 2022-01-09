package com.hit.logres.consumer.panel;

import com.hit.logres.api.entity.Inventory;
import com.hit.logres.api.entity.User;
import com.jfoenix.controls.JFXButton;
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

public class Window_11_Controller implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Main_Controller controller = (Main_Controller)Starter.controllers.get("Main_Controller");
		controller.control = this;

		User user = Starter.us.findUserById(controller.userID);

		if(Integer.valueOf(user.getAuthority().charAt(4)) < 2 + '0'){
			butEdit.setVisible(false);
		}

	}

	public Integer ID;	//标识ID

	public boolean flag = false;

	@FXML
	public JFXButton butEdit, butDefault;

	@FXML
	public Pane pane;

	@FXML
	public JFXTextField text0, text1, text2, text3;

	@FXML
	public JFXTextArea textArea0;

	@FXML
	void butEditHandler(ActionEvent event) {	//编辑库存信息
		text3.setDisable(false);
		textArea0.setDisable(false);

		flag = true;
	}

	@FXML
	void butSaveHandler(ActionEvent event) {	//保存库存信息

		if(flag == true){
			Main_Controller controller = (Main_Controller)Starter.controllers.get("Main_Controller");

			if(!Tools.isNumber(text0.getText())){
				controller.showLog("数量信息错误！");
				return;
			}

			Inventory inventory = Starter.its.findInventoryById(ID);
			inventory.setAmount(Integer.valueOf(text3.getText()));
			inventory.setInfo(textArea0.getText());
			Starter.its.updateInventory(inventory);
			controller.showLog("更新库存信息成功！");

			controller.cbbWarehouse.getSelectionModel().selectFirst();
			controller.initListMap(controller.vboxStorage);
			controller.initVbox(controller.vboxStorage);

		}
		Stage stage =(Stage)pane.getScene().getWindow();
		stage.close();
	}
}
