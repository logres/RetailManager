package com.hit.logres.consumer.panel;

import com.hit.logres.api.entity.Inventory;
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

public class Info_01_Controller  implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Main_Controller controller = (Main_Controller)Starter.controllers.get("Main_Controller");
		controller.control = this;

		User user = Starter.us.findUserById(controller.userID);

		if(Integer.valueOf(user.getAuthority().charAt(3)) < 2 + '0'){
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
	void butInfoHandler(ActionEvent event) {	//显示仓库信息
		Main_Controller controller = (Main_Controller)Starter.controllers.get("Main_Controller");
		controller.showNewWindow("/Window_11_StorageInfo.fxml");

		Inventory it = Starter.its.findInventoryById(ID);

		((Window_11_Controller)controller.control).ID = ID;
		((Window_11_Controller)controller.control).text0.setText(it.getGoodId().toString());
		((Window_11_Controller)controller.control).text1.setText(Starter.gs.findGoodById(it.getGoodId()).getName());
		((Window_11_Controller)controller.control).text2.setText(Starter.ws.findWarehouseById(it.getWarehouseId()).getName());
		((Window_11_Controller)controller.control).text3.setText(it.getAmount().toString());
		((Window_11_Controller)controller.control).textArea0.setText(it.getInfo());

		((Window_11_Controller)controller.control).butDefault.requestFocus();

	}

	@FXML
	void butDeleteHandler(ActionEvent event) {	//删除仓库信息
		Main_Controller controller = (Main_Controller)Starter.controllers.get("Main_Controller");

		Starter.its.deleteInventory(ID);

		controller.initListMap(controller.vboxStorage);
		controller.initVbox(controller.vboxStorage);
	}

}
