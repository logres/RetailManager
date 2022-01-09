package com.hit.logres.consumer.panel;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.hit.logres.api.entity.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Window_04_Controller  implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Main_Controller controller = (Main_Controller)Starter.controllers.get("Main_Controller");
		controller.control = this;

		User user = Starter.us.findUserById(controller.userID);

		List<Good> good = Starter.gs.getAllGood();
		ObservableList<String> list  =  FXCollections.observableArrayList(new ArrayList<String>());
		for(int i = 0; i < good.size(); i++){
			list.add(good.get(i).getName());
		}
		cbb.setItems(list);
		ComboBoxListener cbbListener1 = new ComboBoxListener<>(cbb);
		cbb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				List<Good> find = Starter.gs.findGoodByName(newValue);
				if(find != null && find.size() != 0){
					text1.setText(find.get(0).getId().toString());

					if(Tools.isInteger(text3.getText()) && !text3.getText().equals("")){
						text4.setText(String.valueOf(find.get(0).getPurchasePrice().doubleValue() * Integer.valueOf(text3.getText())));
					}

				}
				else {
					text1.setText("");
				}
			}
		});

		text3.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				List<Good> find = Starter.gs.findGoodByName(cbb.getSelectionModel().getSelectedItem());
				if(Tools.isInteger(newValue) && find != null && find.size() != 0 ){
					text4.setText(String.valueOf(find.get(0).getPurchasePrice().doubleValue() * Integer.valueOf(text3.getText())));
				}
			}
		});
		Tools.setWarehouseCbb(cbb1);
	}

	public Integer ID;	//标识ID

	@FXML
	private Pane pane;

	@FXML
	public JFXComboBox<String> cbb, cbb1;

	@FXML
	public JFXTextField text1, text3, text4, text5;

	@FXML
	public JFXTextArea textArea0;

	@FXML
	public JFXButton butDefault;

	@FXML
	void butAddHandler(ActionEvent event) {	//添加库存信息

		Main_Controller controller = (Main_Controller)Starter.controllers.get("Main_Controller");

		if(Starter.ws.findWarehouseByName(cbb1.getSelectionModel().getSelectedItem()) == null){
			controller.showLog("无此仓库！");
			return;
		}

		if(text1.getText() != null && !text1.getText().isEmpty() &&
				Tools.isNumber(text4.getText()) &&
				Tools.isInteger(text3.getText())) {

			//入库日志，暂时未用到
			Purchase pur = new Purchase(
					0,
					Integer.valueOf(text1.getText()),
					Starter.ws.findWarehouseByName(cbb1.getSelectionModel().getSelectedItem()).get(0).getId(),
					new BigDecimal(text4.getText()),
					Integer.valueOf(text3.getText()),
					text5.getText(),
					new Timestamp(System.currentTimeMillis()));

			Starter.pcs.insertPurchase(pur);
			//

			List<Inventory> list = Starter.its.getAllInventory();
			for(int i = 0; i < list.size(); i++){
				if(list.get(i).getGoodId() == Integer.valueOf(text1.getText()) &&		//库存中已经存有
						list.get(i).getWarehouseId() == Starter.ws.findWarehouseByName(cbb1.getSelectionModel().getSelectedItem()).get(0).getId()){

					Inventory inventory = list.get(i);
					inventory.setAmount(inventory.getAmount() + Integer.valueOf(text3.getText()));
					controller.logInventory(inventory, textArea0.getText());
					controller.logInventory(inventory, "入库: " + Integer.valueOf(text3.getText()) + "件。");
					Starter.its.updateInventory(inventory);

					controller.showLog("添加已有库存成功！");
					controller.initListMap(controller.vboxStorage);
					controller.initVbox(controller.vboxStorage);
					Stage stage =(Stage)pane.getScene().getWindow();
					stage.close();
					return;
				}
			}

			Inventory inventory = new Inventory(
					0,
					Starter.ws.findWarehouseByName(cbb1.getSelectionModel().getSelectedItem()).get(0).getId(),
					Integer.valueOf(text1.getText()),
					Integer.valueOf(text3.getText()),
					textArea0.getText()
			);
			controller.logInventory(inventory, "入库: " + Integer.valueOf(text3.getText()) + "件。");
			Starter.its.insertInventory(inventory);

			controller.showLog("添加新库存成功！");

			controller.initListMap(controller.vboxStorage);
			controller.initVbox(controller.vboxStorage);
			Stage stage =(Stage)pane.getScene().getWindow();
			stage.close();
		}
		else{
			controller.showLog("输入格式错误！");
		}
	}

	@FXML
	void butCloseHandler(ActionEvent event) {	//关闭窗口
		Stage stage =(Stage)pane.getScene().getWindow();
		stage.close();
	}
}
