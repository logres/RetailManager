package com.hit.logres.consumer.panel;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

import com.hit.logres.api.entity.Good;
import com.hit.logres.api.entity.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Window_02_Controller  implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Main_Controller controller = (Main_Controller)Starter.controllers.get("Main_Controller");
		controller.control = this;

		User user = Starter.us.findUserById(controller.userID);

		Tools.setGoodStatusCbb(cbb0);
		Tools.setWarehouseCbb(cbb1);
	}

	public Integer ID;	//标识ID

	@FXML
	private Pane pane;

	@FXML
	public JFXTextField text0, text1, text3, text5, text6, text7, text8;

	@FXML
	public JFXComboBox<String> cbb0, cbb1;

	@FXML
	public JFXTextArea textArea0;

	@FXML
	public JFXButton butDefault;

	@FXML
	void butAddHandler(ActionEvent event) {	//添加商品信息

		Main_Controller controller = (Main_Controller)Starter.controllers.get("Main_Controller");

		if(Tools.isNumber(text5.getText()) &&
				Tools.isNumber(text6.getText()) &&
				Tools.isNumber(text7.getText()) &&
				Tools.isNumber(text8.getText())) {


			if(Starter.ws.findWarehouseByName(cbb1.getSelectionModel().getSelectedItem()).size() == 0){
				controller.showLog("仓库错误！");
				return;
			}

			Good good = new Good(
					0,
					text1.getText(),
					cbb0.getSelectionModel().getSelectedItem(),
					BigDecimal.valueOf(Double.valueOf(text5.getText())),
					BigDecimal.valueOf(Double.valueOf(text6.getText())),
					BigDecimal.valueOf(Double.valueOf(text7.getText())),
					BigDecimal.valueOf(Double.valueOf(text8.getText())),
					Integer.valueOf(Starter.ws.findWarehouseByName(cbb1.getSelectionModel().getSelectedItem()).get(0).getId()),
					textArea0.getText(),
					text3.getText());

			Starter.gs.insertGood(good);
			controller.showLog("添加商品成功！");
		}
		else{
			controller.showLog("输入格式错误！");
			return;
		}

		controller.initListMap(controller.vboxGood);
		controller.initVbox(controller.vboxGood);
		Stage stage =(Stage)pane.getScene().getWindow();
		stage.close();

	}

	@FXML
	void butCloseHandler(ActionEvent event) {	//关闭窗口
		Stage stage =(Stage)pane.getScene().getWindow();
		stage.close();
	}
}
