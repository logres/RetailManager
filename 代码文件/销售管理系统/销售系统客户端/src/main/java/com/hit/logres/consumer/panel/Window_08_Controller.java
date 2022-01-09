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
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Window_08_Controller  implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Main_Controller controller = (Main_Controller)Starter.controllers.get("Main_Controller");
		controller.control = this;

		User user = Starter.us.findUserById(controller.userID);

		if(Integer.valueOf(user.getAuthority().charAt(4)) < 2 + '0'){
			butEdit.setVisible(false);
		}

		Tools.setGoodStatusCbb(cbb0);
		Tools.setWarehouseCbb(cbb1);
	}

	public Integer ID;	//标识ID

	public boolean flag = false;

	@FXML
	public JFXButton butDefault, butEdit;

	@FXML
	public Pane pane;

	@FXML
	public JFXTextField text0, text1, text3,
			text5, text6, text7, text8;

	@FXML
	public JFXComboBox<String> cbb0, cbb1;

	@FXML
	public JFXTextArea textArea0;

	@FXML
	void butEditHandler(ActionEvent event) {	//编辑用户信息
		text0.setDisable(false);
		text1.setDisable(false);
		text3.setDisable(false);
		text5.setDisable(false);
		text6.setDisable(false);
		text7.setDisable(false);
		text8.setDisable(false);

		cbb0.setMouseTransparent(false);
		cbb1.setMouseTransparent(false);
		textArea0.setDisable(false);
		flag = true;
	}

	@FXML
	void butSaveHandler(ActionEvent event) {	//保存用户信息

		if(flag == true){
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
						ID,
						text1.getText(),
						cbb0.getSelectionModel().getSelectedItem(),
						BigDecimal.valueOf(Double.valueOf(text5.getText())),
						BigDecimal.valueOf(Double.valueOf(text6.getText())),
						BigDecimal.valueOf(Double.valueOf(text7.getText())),
						BigDecimal.valueOf(Double.valueOf(text8.getText())),
						Integer.valueOf(Starter.ws.findWarehouseByName(cbb1.getSelectionModel().getSelectedItem()).get(0).getId()),
						textArea0.getText(),
						text3.getText());

				Starter.gs.updateGood(good);
				controller.showLog("更新商品成功！");

				controller.initListMap(controller.vboxGood);
				controller.initVbox(controller.vboxGood);
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
