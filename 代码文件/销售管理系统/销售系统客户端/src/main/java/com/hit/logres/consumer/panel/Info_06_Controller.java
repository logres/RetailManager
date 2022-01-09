package com.hit.logres.consumer.panel;

import com.hit.logres.api.entity.Good;
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

public class Info_06_Controller  implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Main_Controller controller = (Main_Controller)Starter.controllers.get("Main_Controller");
		controller.control = this;

		User user = Starter.us.findUserById(controller.userID);

		if(Integer.valueOf(user.getAuthority().charAt(4)) < 2 + '0'){
			butDelete.setDisable(true);
		}
	}

	public Integer ID;	//标识ID

	@FXML
	public JFXButton butDelete;

	@FXML
	public Pane pane;

	@FXML
	public JFXTextField text0, text1, text2, text3, text4, text5, text6, text7, text8;

	@FXML
	void butInfoHandler(ActionEvent event) {	//显示商品信息
		Main_Controller controller = (Main_Controller)Starter.controllers.get("Main_Controller");
		controller.showNewWindow("/Window_08_GoodInfo.fxml");

		Good good = Starter.gs.findGoodById(ID);

		((Window_08_Controller)controller.control).ID = ID;
		((Window_08_Controller)controller.control).text0.setText(ID.toString());
		((Window_08_Controller)controller.control).text1.setText(good.getName());
		((Window_08_Controller)controller.control).cbb0.getSelectionModel().select(good.getState());
		((Window_08_Controller)controller.control).text3.setText(good.getBarcode());
		((Window_08_Controller)controller.control).cbb1.getSelectionModel().select(Starter.ws.findWarehouseById(good.getDefaultWarehouseId()).getName());
		((Window_08_Controller)controller.control).text5.setText(Tools.formatBigDecimal(good.getPriceA()));
		((Window_08_Controller)controller.control).text6.setText(Tools.formatBigDecimal(good.getPriceB()));
		((Window_08_Controller)controller.control).text7.setText(Tools.formatBigDecimal(good.getPriceC()));
		((Window_08_Controller)controller.control).text8.setText(Tools.formatBigDecimal(good.getPurchasePrice()));
		((Window_08_Controller)controller.control).textArea0.setText(good.getInfo());

		((Window_08_Controller)controller.control).butDefault.requestFocus();

	}

	@FXML
	void butDeleteHandler(ActionEvent event) {	//删除商品信息
		Main_Controller controller = (Main_Controller)Starter.controllers.get("Main_Controller");

		if(Starter.gs.deleteGood(ID) == false){
			controller.showLog("数据依赖，商品删除失败！");
		}
		else {
			controller.showLog("商品删除成功！");
		}

		controller.initListMap(controller.vboxGood);
		controller.initVbox(controller.vboxGood);
	}
}
