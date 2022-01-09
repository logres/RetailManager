package com.hit.logres.consumer.panel;

import com.hit.logres.api.entity.Customer;
import com.hit.logres.api.entity.Good;
import com.hit.logres.api.entity.User;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Info_03_Controller  implements Initializable {

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
				List<Good> list = Starter.gs.findGoodByName(newValue);
					List<Customer> customer =  Starter.cs.findCustomerByName(controller.cbbSaleTicket.getSelectionModel().getSelectedItem());

				if(list != null && list.size() != 0){

					text1.setText(Starter.ws.findWarehouseById(list.get(0).getDefaultWarehouseId()).getName());

					if(customer != null && customer.size() != 0){

						if(customer.get(0).getType().equals("零售")){
							text0.setText(Tools.formatBigDecimal(list.get(0).getPriceA()));
						}
						else if(customer.get(0).getType().equals("批发")){
							text0.setText(Tools.formatBigDecimal(list.get(0).getPriceB()));
						}
						else{
							text0.setText(Tools.formatBigDecimal(list.get(0).getPriceC()));
						}
					}
					else {
						text0.setText("");
					}
				}
				else {
					text0.setText("");
					text1.setText("");
				}
			}
		});

	}

	public Integer ID;	//标识ID

	@FXML
	public Pane pane;

	@FXML
	public JFXComboBox<String> cbb;

	@FXML
	public JFXTextField text0, text1, text2;

	@FXML
	void butDeleteHandler(ActionEvent event) {	//删除销售单信息
		Main_Controller controller = (Main_Controller)Starter.controllers.get("Main_Controller");

		Starter.dls.deleteDraftLine(ID);
		controller.initListMap(controller.vboxSaleTicket);
		controller.initVbox(controller.vboxSaleTicket);

		controller.controlSaleTicket.remove(this);
	}
}
