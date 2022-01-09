package com.hit.logres.consumer.panel;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import com.hit.logres.api.entity.*;
import com.jfoenix.controls.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Tab;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main_Controller implements Initializable{

//成员__________________________________________________________________

	public Object control;		//新控件的控制类实例，用于初始化

	public int userID;		//员工ID

	public Map<VBox, List<Node>> listMap;	//VBox关联的成员列表

	public But_03_Controller controlCurrentSaleTicket;	//当前选中按钮的控制类
	public But_01_Controller controlCurrentOrder;
	public But_02_Controller controlCurrentPayment;
	public But_04_Controller controlCurrentCheck;

	public List<Object> controlSaleTicket, controlSaleTicketAbs,	//控制类实例，用于传参
						controlCheck, controlCheckAbs,
						controlOrder, controlOrderAbs,
						controlPayment, controlPaymentAbs,
						controlPOS,
						controlStorage,
						controlUser,
						controlClient,
						controlGood,
						controlAnalClient,
						controlAnalUser,
						controlAnalGood;

	public int warehouseID = -1;	//当前选中的仓库ID， -1为无效

	public Timestamp timestamp;		//当前时间

	public ObservableList<String> listwarehouse  =  FXCollections.observableArrayList(new ArrayList<String>());	//全部仓库信息
	public ObservableList<String> listclient  =  FXCollections.observableArrayList(new ArrayList<String>());	//全部顾客信息
	public ObservableList<String> listretail  =  FXCollections.observableArrayList(new ArrayList<String>());	//全部零售顾客信息

	private double xOffset = 0;	//无边框窗口位置
	private double yOffset = 0;

	private double thisXOffset = 0;
	private double thisYOffset = 0;

	private int clock = 1;	//排序循环变量

	public int weight0 = 1, weight1 = 1, weight2 = 1;	//销售业绩权重


//组件__________________________________________________________________

	@FXML
	public Pane pane, header;

	@FXML
	public ComboBox<String> cbbWarehouse, cbbSaleTicket, cbbPOS;

	@FXML
	public Tab tabStorage, tabClient, tabGood, tabTicket, tabPayment, tabLogIn, tabPOS, tabUser, tabAnalize,
			tabSaleTicket, tabOrder, tabCheck,
			tabAnalOverview, tabAnalClient, tabAnalGood, tabAnalUser;

	@FXML
	public JFXTabPane tabpaneMain, tabpaneTicket, tabpaneAnalyze;

	@FXML
	public JFXButton butAddSaleTicketAbs, butAddPaymentAbs;

	@FXML
	public JFXButton butAddWarehouse, butDeleteWarehouse, butExchangeWarehouse, butAddStorage,
					butAddClient,
					butSubmitSaleTicket,
					butRefundOrder, butDeleteOrder,
					butAddGood,
					butAddSaleTicket;

	@FXML
	public JFXButton butOrder0, butOrder1, butOrder2,	//权限控制用
					 butStorage0, butStorage1, butStorage2,
					 butGood0, butGood1,
					 butClient0, butClient1,
					 butUser0;

	@FXML
	public JFXTextField textSaleTicket1, textSaleTicket2, textSaleTicket3,
			textOrder0, textOrder1, textOrder2, textOrder3, textOrder4, textOrder5, textOrder6, textOrder7,
			textPayment0, textPayment1, textPayment2, textPayment3,
			textPOS0, textPOS1, textPOS2, textPOS3, textPOS4,
			textStorage0, textStorage1, textStorage2, textStorage3,
			textSaleTicketAbs, textCheckAbs, textOrderAbs, textPaymentAbs,
			textGood0, textGood1, textGood2, textGood3, textGood4,
			textClient0, textClient1, textClient2, textClient3,
			textUser0, textUser1, textUser2, textUser3,
			textCheck0, textCheck1, textCheck2,
			textAnal0, textAnal1, textAnal2, textAnal3, textAnal4, textAnal5, textAnal6, textAnal7;

	@FXML
	public VBox vboxStorage, vboxClient, vboxSaleTicket, vboxOrder, vboxPayment, vboxGood, vboxPOS, vboxUser, vboxCheck,		//VBox
				vboxSaleTicketAbs, vboxOrderAbs, vboxPaymentAbs, vboxCheckAbs,
				vboxAnalClient, vboxAnalGood, vboxAnalUser;

	@FXML
	public JFXDatePicker from, to;

	@FXML
	public Pane paneBarChart, panePieChart, paneSaleTicket, panePayment, paneOrder;

	//TODO 大界面实现权限管理， order未完成， 仓库， 客户，职员需要更细粒度权限


// 初始化__________________________________________________________________

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Starter.controllers.put("Main_Controller", this);		//控制器实例访问

		Window_13_Controller con = (Window_13_Controller)Starter.controllers.get("Window_13_Controller");
		userID = con.userID;		//获取用户ID

		controlCurrentSaleTicket = null;
		controlCurrentOrder = null;
		controlCurrentPayment = null;
		controlCurrentCheck = null;

		listMap = new HashMap<VBox, List<Node>>();
		listMap.put(vboxStorage, new ArrayList<Node>());
		listMap.put(vboxClient, new ArrayList<Node>());
		listMap.put(vboxSaleTicket, new ArrayList<Node>());
		listMap.put(vboxOrder, new ArrayList<Node>());
		listMap.put(vboxPayment, new ArrayList<Node>());
		listMap.put(vboxGood, new ArrayList<Node>());
		listMap.put(vboxSaleTicketAbs, new ArrayList<Node>());
		listMap.put(vboxOrderAbs, new ArrayList<Node>());
		listMap.put(vboxPaymentAbs, new ArrayList<Node>());
		listMap.put(vboxPOS, new ArrayList<Node>());
		//listMap.put(vboxAnalOverview, new ArrayList<Node>());
		listMap.put(vboxAnalClient, new ArrayList<Node>());
		listMap.put(vboxAnalGood, new ArrayList<Node>());
		listMap.put(vboxAnalUser, new ArrayList<Node>());
		listMap.put(vboxUser, new ArrayList<Node>());
		listMap.put(vboxCheck, new ArrayList<Node>());
		listMap.put(vboxCheckAbs, new ArrayList<Node>());

		setDisplaceSaleTicket();	//权限管理
		setDisplacePayment();
		setDisplaceClient();
		setDisplaceGood();
		setDisplaceOrder();
		setDisplaceStorage();

		User user = Starter.us.findUserById(userID);
		if(Integer.valueOf(user.getAuthority().charAt(0)) < 1 + '0'){         //权限管理
			tabpaneMain.getTabs().remove(tabPOS);
		}
		if(Integer.valueOf(user.getAuthority().charAt(1)) < 1 + '0'){
			tabpaneMain.getTabs().remove(tabOrder);
		}
		else if(Integer.valueOf(user.getAuthority().charAt(1)) < 3 + '0'){
			tabpaneTicket.getTabs().remove(tabCheck);
		}
		if(Integer.valueOf(user.getAuthority().charAt(2)) < 1 + '0'){
			tabpaneMain.getTabs().remove(tabTicket);
		}
		if(Integer.valueOf(user.getAuthority().charAt(3)) < 1 + '0'){
			tabpaneMain.getTabs().remove(tabStorage);
		}
		if(Integer.valueOf(user.getAuthority().charAt(4)) < 1 + '0'){
			tabpaneMain.getTabs().remove(tabGood);
		}
		if(Integer.valueOf(user.getAuthority().charAt(5)) < 1 + '0'){
			tabpaneMain.getTabs().remove(tabClient);
		}
		if(Integer.valueOf(user.getAuthority().charAt(6)) < 1 + '0'){
			tabpaneMain.getTabs().remove(tabUser);
		}
		if(Integer.valueOf(user.getAuthority().charAt(7)) < 1 + '0'){
			tabpaneMain.getTabs().remove(tabAnalize);
			butUser0.setDisable(true);
			butClient1.setDisable(true);
			butGood1.setDisable(true);
		}

		/*
		textPOS2.focusedProperty().addListener(new ChangeListener<Boolean>() {	//POS机输入
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(tabpaneMain.getSelectionModel().getSelectedItem() == tabPOS && newValue == false){

					//TODO

					textPOS2.requestFocus();
				}
			}
		});*/


		thisXOffset = 0;
		thisYOffset = 0;
		header.setOnMousePressed(new EventHandler<MouseEvent>() {		//设定无边框窗口拖动
			@Override
			public void handle(MouseEvent event) {
				thisXOffset = event.getSceneX();
				thisYOffset = event.getSceneY();
			}
		});
		header.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Stage stage =(Stage)pane.getScene().getWindow();
				stage.setX(event.getScreenX() - thisXOffset);
				stage.setY(event.getScreenY() - thisYOffset);
			}
		});

		textPOS2.setOnKeyPressed(event -> {	//POS机输入
			if (event.getCode() == KeyCode.ENTER) {

				String code = textPOS2.getText();
				textPOS2.clear();

				List<Good> good = Starter.gs.getAllGood();
				for(int i = 0; i < good.size(); i++){
					if(good.get(i).getBarcode().equals(code)){

						if(controlPOS == null){
							controlPOS = new ArrayList<>();
						}
						for(int j = 0; j < controlPOS.size(); j++){
							Info_08_Controller controller = (Info_08_Controller) controlPOS.get(j);
							if(Starter.gs.findGoodByName(controller.cbb.getSelectionModel().getSelectedItem()) != null &&
									Starter.gs.findGoodByName(controller.cbb.getSelectionModel().getSelectedItem()).get(0).getId() ==
									good.get(i).getId()){		//已经有，添加

								if(Tools.isNumber(controller.text2.getText())){
									controller.text2.setText(String.valueOf(Integer.valueOf(controller.text2.getText()) + 1));
								}
								else{
									controller.text2.setText("1");
								}

								initVbox(vboxPOS);
								calculatePOS();
								textPOS2.requestFocus();
								return;
							}
						}

						//没有，新增
						listMap.get(vboxPOS).add(loadNode("/Info_08_POS.fxml"));
						controlPOS.add(control);
						Info_08_Controller controller = (Info_08_Controller)control;
						controller.cbb.getSelectionModel().select(good.get(i).getName());
						controller.text0.setText(Tools.formatBigDecimal(good.get(i).getPriceA()));
						controller.text1.setText(Starter.ws.findWarehouseById(good.get(i).getDefaultWarehouseId()).getName());
						controller.text2.setText("1");

						initVbox(vboxPOS);
						calculatePOS();
						textPOS2.requestFocus();
						return;
					}
				}
			}
		});

		tabpaneAnalyze.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {	//Tab事件监听
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
				initListMap(vboxAnalClient);
				initListMap(vboxAnalGood);
				initListMap(vboxAnalUser);
				initVbox(vboxAnalClient);
				initVbox(vboxAnalGood);
				initVbox(vboxAnalUser);
			}
		});


		tabpaneTicket.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {	//Tab事件监听
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {

				initListclient();

				controlCurrentSaleTicket = null;
				controlCurrentOrder = null;
				controlCurrentCheck = null;

				initListMap(vboxSaleTicket);
				initListMap(vboxSaleTicketAbs);
				initVbox(vboxSaleTicketAbs);
				initVbox(vboxSaleTicket);

				initListMap(vboxCheck);
				initListMap(vboxCheckAbs);
				initVbox(vboxCheckAbs);
				initVbox(vboxCheck);

				initCheck();
				initSaleTicket();
				initOrder();

			}
		});

		tabpaneMain.getSelectionModel().selectFirst();
		tabpaneMain.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {	//Tab事件监听
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {

				if(newValue == tabStorage) {
					initListwarehouse();

					cbbWarehouse.getSelectionModel().selectFirst();
					initListMap(vboxStorage);
					initVbox(vboxStorage);
				}
				else if(newValue == tabClient) {
					initListMap(vboxClient);
					initVbox(vboxClient);
				}
				else if(newValue == tabGood) {
					initListMap(vboxGood);
					initVbox(vboxGood);
				}
				else if(newValue == tabUser){
					initListMap(vboxUser);
					initVbox(vboxUser);
				}
				else if(newValue == tabTicket) {
					initListclient();

					controlCurrentSaleTicket = null;
					controlCurrentOrder = null;

					initListMap(vboxSaleTicket);
					initListMap(vboxSaleTicketAbs);
					initVbox(vboxSaleTicketAbs);
					initVbox(vboxSaleTicket);

					initListMap(vboxCheck);
					initListMap(vboxCheckAbs);
					initVbox(vboxCheckAbs);
					initVbox(vboxCheck);

					initCheck();
					initSaleTicket();
					initOrder();

				}
				else if(newValue == tabPayment) {

					controlCurrentPayment = null;
					initPayment();

				}
				else if(newValue == tabLogIn) {

				}
				else if(newValue == tabPOS){
					initListretail();

					initPOS();
					initListMap(vboxPOS);
					initVbox(vboxPOS);
				}
				else if(newValue == tabAnalize){

					initAnalTime();
					initAnal(1);
				}

			}
		});

		cbbWarehouse.setItems(listwarehouse);	//仓库cbb
		ComboBoxListener cbbListener1 = new ComboBoxListener<>(cbbWarehouse);
		cbbWarehouse.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(newValue != null && !newValue.isEmpty() && Starter.ws.findWarehouseByName(newValue).size() != 0) {
					warehouseID = Starter.ws.findWarehouseByName(newValue).get(0).getId();
				}
				else {
					warehouseID = -1;
				}
				initListMap(vboxStorage);
				initVbox(vboxStorage);

			}
		});

		cbbSaleTicket.setItems(listclient);	//销售单cbb
		ComboBoxListener cbbListener2 = new ComboBoxListener<>(cbbSaleTicket);

		cbbPOS.setItems(listretail);		//POScbb
		ComboBoxListener cbbListener3 = new ComboBoxListener<>(cbbPOS);

	}

//仓库信息__________________________________________________________________

	@FXML
	void butSearchStorageHandler(ActionEvent event) {	//库存搜索事件

		List<Node> nodeList = new ArrayList<>(listMap.get(vboxStorage));
		List<Object> controllerList = new ArrayList<>(controlStorage);
		List<Node> newList = new ArrayList<>();
		List<Object> newControlList = new ArrayList<>();

		int count = 0;
		for(int i = 0; i < nodeList.size(); i++){
			Info_01_Controller controller = (Info_01_Controller)controllerList.get(i);

			if(controller.text0.getText().contains(textStorage0.getText()) &&
					controller.text1.getText().contains(textStorage1.getText()) &&
					controller.text2.getText().contains(textStorage2.getText())){

				newList.add(nodeList.get(i));
				newControlList.add(controllerList.get(i));
				count++;

			}
		}
		controlStorage = newControlList;
		listMap.get(vboxStorage).clear();
		listMap.get(vboxStorage).addAll(newList);
		initVbox(vboxStorage);
		showLog("共查找到:" + count + "项信息。");
	}

	@FXML
	void butSort0StorageHandler(ActionEvent event) {    //排序事件

		clock = -clock;

		List<Node> list = new ArrayList<>(listMap.get(vboxStorage));
		List<Object> controls = new ArrayList<>(controlStorage);
		List<Object> newControls = new ArrayList<>();
		listMap.get(vboxStorage).clear();

		while(controls.size() > 0){
			int index = 0;

			String str = ((Info_01_Controller)controls.get(0)).text0.getText();

			for (int i = 0; i < controls.size(); i++){
				String temp = ((Info_01_Controller)controls.get(i)).text0.getText();
				if((temp.compareTo(str) * clock) > 0){
					index = i;
					str = temp;
				}
			}
			listMap.get(vboxStorage).add(list.get(index));
			newControls.add(controls.get(index));
			list.remove(index);
			controls.remove(index);
		}
		controlStorage = newControls;
		initVbox(vboxStorage);
	}

	@FXML
	void butSort1StorageHandler(ActionEvent event) {    //排序事件

		clock = -clock;

		List<Node> list = new ArrayList<>(listMap.get(vboxStorage));
		List<Object> controls = new ArrayList<>(controlStorage);
		List<Object> newControls = new ArrayList<>();
		listMap.get(vboxStorage).clear();

		while(controls.size() > 0){
			int index = 0;

			int num = Integer.valueOf(((Info_01_Controller)controls.get(0)).text1.getText());

			for (int i = 0; i < controls.size(); i++){
				int temp = Integer.valueOf(((Info_01_Controller)controls.get(i)).text1.getText());
				if(((temp - num) * clock) > 0){
					index = i;
					num = temp;
				}
			}
			listMap.get(vboxStorage).add(list.get(index));
			newControls.add(controls.get(index));
			list.remove(index);
			controls.remove(index);
		}
		controlStorage = newControls;
		initVbox(vboxStorage);
	}

	@FXML
	void butSort2StorageHandler(ActionEvent event) {    //排序事件

		clock = -clock;

		List<Node> list = new ArrayList<>(listMap.get(vboxStorage));
		List<Object> controls = new ArrayList<>(controlStorage);
		List<Object> newControls = new ArrayList<>();
		listMap.get(vboxStorage).clear();

		while(controls.size() > 0){
			int index = 0;

			int num = Integer.valueOf(((Info_01_Controller)controls.get(0)).text2.getText());

			for (int i = 0; i < controls.size(); i++){
				int temp = Integer.valueOf(((Info_01_Controller)controls.get(i)).text2.getText());
				if(((temp - num) * clock) > 0){
					index = i;
					num = temp;
				}
			}
			listMap.get(vboxStorage).add(list.get(index));
			newControls.add(controls.get(index));
			list.remove(index);
			controls.remove(index);
		}
		controlStorage = newControls;
		initVbox(vboxStorage);
	}

	@FXML
	void butAddStorageHandler(ActionEvent event) {	//入库事件
		showNewWindow("/Window_04_AddStorage.fxml");

		((Window_04_Controller)control).butDefault.requestFocus();
	}

	@FXML
	void butAddWarehouseHandler(ActionEvent event) {	//新增仓库事件
		String warehouse = cbbWarehouse.getValue().toString();
		if(warehouse == null || warehouse.isEmpty()){
			showLog("请输入有效仓库名！");
			return;
		}

		if(Starter.ws.findWarehouseByName(warehouse).size() == 0) {

			Warehouse wh = new Warehouse(
					0,
					warehouse,
					"Default");
			Starter.ws.insertWarehouse(wh);
		}

		initListwarehouse();

		initListMap(vboxStorage);
		initVbox(vboxStorage);

		showLog("仓库添加成功！");
	}

	@FXML
	void butDeleteWarehouseHandler(ActionEvent event) {	//删除仓库事件
		String warehouse = cbbWarehouse.getValue().toString();

		if(Starter.ws.findWarehouseByName(warehouse).size() != 0) {
			if(Starter.ws.deleteWarehouse(Starter.ws.findWarehouseByName(warehouse).get(0).getId()) == false){
				showLog("数据依赖，仓库删除失败！");
			}
			else {
				showLog("仓库删除成功！");
			}
		}
		initListwarehouse();

		initListMap(vboxStorage);
		initVbox(vboxStorage);
	}

	@FXML
	void butExchangeWarehouseHandler(ActionEvent event) {	//仓库调度事件
		showNewWindow("/Window_07_ExchangeWarehouse.fxml");
		((Window_07_Controller)control).butDefault.requestFocus();
	}

//客户信息__________________________________________________________________

	@FXML
	void butSearchClientHandler(ActionEvent event) {    //查询事件
		List<Node> nodeList = new ArrayList<>(listMap.get(vboxClient));
		List<Object> controllerList = new ArrayList<>(controlClient);
		List<Node> newList = new ArrayList<>();
		List<Object> newControlList = new ArrayList<>();

		int count = 0;
		for(int i = 0; i < nodeList.size(); i++){
			Info_02_Controller controller = (Info_02_Controller)controllerList.get(i);

			if(controller.text0.getText().contains(textClient0.getText()) &&
					controller.text1.getText().contains(textClient1.getText()) &&
					controller.text2.getText().contains(textClient2.getText()) &&
					controller.text3.getText().contains(textClient3.getText())){

				newList.add(nodeList.get(i));
				newControlList.add(controllerList.get(i));
				count++;

			}
		}
		controlClient = newControlList;
		listMap.get(vboxClient).clear();
		listMap.get(vboxClient).addAll(newList);
		initVbox(vboxClient);
		showLog("共查找到:" + count + "项信息。");
	}

	@FXML
	void butSort0ClientHandler(ActionEvent event) {    //排序事件

		clock = -clock;

		List<Node> list = new ArrayList<>(listMap.get(vboxClient));
		List<Object> controls = new ArrayList<>(controlClient);
		List<Object> newControls = new ArrayList<>();
		listMap.get(vboxClient).clear();

		while(controls.size() > 0){
			int index = 0;

			String str = ((Info_02_Controller)controls.get(0)).text0.getText();

			for (int i = 0; i < controls.size(); i++){
				String temp = ((Info_02_Controller)controls.get(i)).text0.getText();
				if((temp.compareTo(str) * clock) > 0){
					index = i;
					str = temp;
				}
			}
			listMap.get(vboxClient).add(list.get(index));
			newControls.add(controls.get(index));
			list.remove(index);
			controls.remove(index);
		}
		controlClient = newControls;
		initVbox(vboxClient);
	}

	@FXML
	void butSort1ClientHandler(ActionEvent event) {    //排序事件

		clock = -clock;

		List<Node> list = new ArrayList<>(listMap.get(vboxClient));
		List<Object> controls = new ArrayList<>(controlClient);
		List<Object> newControls = new ArrayList<>();
		listMap.get(vboxClient).clear();

		while(controls.size() > 0){
			int index = 0;

			long num = Long.valueOf(((Info_02_Controller)controls.get(0)).text1.getText());

			for (int i = 0; i < controls.size(); i++){
				long temp = Long.valueOf(((Info_02_Controller)controls.get(i)).text1.getText());
				if(((temp - num) * clock) > 0){
					index = i;
					num = temp;
				}
			}
			listMap.get(vboxClient).add(list.get(index));
			newControls.add(controls.get(index));
			list.remove(index);
			controls.remove(index);
		}
		controlClient = newControls;
		initVbox(vboxClient);
	}

	@FXML
	void butSort2ClientHandler(ActionEvent event) {    //排序事件

		clock = -clock;

		List<Node> list = new ArrayList<>(listMap.get(vboxClient));
		List<Object> controls = new ArrayList<>(controlClient);
		List<Object> newControls = new ArrayList<>();
		listMap.get(vboxClient).clear();

		while(controls.size() > 0){
			int index = 0;

			String str = ((Info_02_Controller)controls.get(0)).text2.getText();

			for (int i = 0; i < controls.size(); i++){
				String temp = ((Info_02_Controller)controls.get(i)).text2.getText();
				if((temp.compareTo(str) * clock) > 0){
					index = i;
					str = temp;
				}
			}
			listMap.get(vboxClient).add(list.get(index));
			newControls.add(controls.get(index));
			list.remove(index);
			controls.remove(index);
		}
		controlClient = newControls;
		initVbox(vboxClient);
	}

	@FXML
	void butAddClientHandler(ActionEvent event) {	//新增用户事件
		showNewWindow("/Window_10_AddClientInfo.fxml");
		((Window_10_Controller)control).butDefault.requestFocus();
	}

//职员信息__________________________________________________________________

	@FXML
	void butSearchUserHandler(ActionEvent event) {    //查询事件
		List<Node> nodeList = new ArrayList<>(listMap.get(vboxUser));
		List<Object> controllerList = new ArrayList<>(controlUser);
		List<Node> newList = new ArrayList<>();
		List<Object> newControlList = new ArrayList<>();

		int count = 0;
		for(int i = 0; i < nodeList.size(); i++){
			Info_09_Controller controller = (Info_09_Controller)controllerList.get(i);

			if(controller.text0.getText().contains(textUser0.getText()) &&
					controller.text1.getText().contains(textUser1.getText()) &&
					controller.text2.getText().contains(textUser2.getText()) &&
					controller.text3.getText().contains(textUser3.getText())){

				newList.add(nodeList.get(i));
				newControlList.add(controllerList.get(i));
				count++;

			}
		}
		controlUser = newControlList;
		listMap.get(vboxUser).clear();
		listMap.get(vboxUser).addAll(newList);
		initVbox(vboxUser);
		showLog("共查找到:" + count + "项信息。");
	}

	@FXML
	void butSort0UserHandler(ActionEvent event) {    //排序事件

		clock = -clock;

		List<Node> list = new ArrayList<>(listMap.get(vboxUser));
		List<Object> controls = new ArrayList<>(controlUser);
		List<Object> newControls = new ArrayList<>();
		listMap.get(vboxUser).clear();

		while(controls.size() > 0){
			int index = 0;

			String str = ((Info_09_Controller)controls.get(0)).text0.getText();

			for (int i = 0; i < controls.size(); i++){
				String temp = ((Info_09_Controller)controls.get(i)).text0.getText();
				if((temp.compareTo(str) * clock) > 0){
					index = i;
					str = temp;
				}
			}
			listMap.get(vboxUser).add(list.get(index));
			newControls.add(controls.get(index));
			list.remove(index);
			controls.remove(index);
		}
		controlUser = newControls;
		initVbox(vboxUser);
	}

	@FXML
	void butSort1UserHandler(ActionEvent event) {    //排序事件

		clock = -clock;

		List<Node> list = new ArrayList<>(listMap.get(vboxUser));
		List<Object> controls = new ArrayList<>(controlUser);
		List<Object> newControls = new ArrayList<>();
		listMap.get(vboxUser).clear();

		while(controls.size() > 0){
			int index = 0;

			int num = Integer.valueOf(((Info_09_Controller)controls.get(0)).text1.getText());

			for (int i = 0; i < controls.size(); i++){
				int temp = Integer.valueOf(((Info_09_Controller)controls.get(i)).text1.getText());
				if(((temp - num) * clock) > 0){
					index = i;
					num = temp;
				}
			}
			listMap.get(vboxUser).add(list.get(index));
			newControls.add(controls.get(index));
			list.remove(index);
			controls.remove(index);
		}
		controlUser = newControls;
		initVbox(vboxUser);
	}

	@FXML
	void butSort2UserHandler(ActionEvent event) {    //排序事件

		clock = -clock;

		List<Node> list = new ArrayList<>(listMap.get(vboxUser));
		List<Object> controls = new ArrayList<>(controlUser);
		List<Object> newControls = new ArrayList<>();
		listMap.get(vboxUser).clear();

		while(controls.size() > 0){
			int index = 0;

			String str = ((Info_09_Controller)controls.get(0)).text2.getText();

			for (int i = 0; i < controls.size(); i++){
				String temp = ((Info_09_Controller)controls.get(i)).text2.getText();
				if((temp.compareTo(str) * clock) > 0){
					index = i;
					str = temp;
				}
			}
			listMap.get(vboxUser).add(list.get(index));
			newControls.add(controls.get(index));
			list.remove(index);
			controls.remove(index);
		}
		controlUser = newControls;
		initVbox(vboxUser);
	}

	@FXML
	void butSort3UserHandler(ActionEvent event) {    //排序事件

		clock = -clock;

		List<Node> list = new ArrayList<>(listMap.get(vboxUser));
		List<Object> controls = new ArrayList<>(controlUser);
		List<Object> newControls = new ArrayList<>();
		listMap.get(vboxUser).clear();

		while(controls.size() > 0){
			int index = 0;

			long num = Long.valueOf(((Info_09_Controller)controls.get(0)).text3.getText());

			for (int i = 0; i < controls.size(); i++){
				long temp = Long.valueOf(((Info_09_Controller)controls.get(i)).text3.getText());
				if(((temp - num) * clock) > 0){
					index = i;
					num = temp;
				}
			}
			listMap.get(vboxUser).add(list.get(index));
			newControls.add(controls.get(index));
			list.remove(index);
			controls.remove(index);
		}
		controlUser = newControls;
		initVbox(vboxUser);
	}

//POS机____________________________________________________________________

	@FXML
	void butSavePosHandler(ActionEvent event){

		if(cbbPOS.getSelectionModel().getSelectedItem() == null ||
				Starter.cs.findCustomerByName(cbbPOS.getSelectionModel().getSelectedItem()) == null ||
				Starter.cs.findCustomerByName(cbbPOS.getSelectionModel().getSelectedItem()).size() == 0){
			showLog("顾客名错误！");
			return;
		}
		Customer customer = Starter.cs.findCustomerByName(cbbPOS.getSelectionModel().getSelectedItem()).get(0);

		if(controlPOS == null){
			showLog("请添加商品！");
			return;
		}
		for(int i = 0; i < controlPOS.size(); i++){	//输入检测
			Info_08_Controller con = ((Info_08_Controller)controlPOS.get(i));

			if(Starter.gs.findGoodByName(con.cbb.getSelectionModel().getSelectedItem()) == null ||
					Starter.gs.findGoodByName(con.cbb.getSelectionModel().getSelectedItem()).size() == 0 ){
				showLog("商品名错误！");
				return;
			}
			Good good = Starter.gs.findGoodByName(con.cbb.getSelectionModel().getSelectedItem()).get(0);

			if(!Tools.isNumber(con.text0.getText()) || !Tools.isInteger(con.text2.getText())){
				showLog("数字错误！");
				return;
			}
			BigDecimal big = new BigDecimal(con.text0.getText());
			int num = Integer.valueOf(con.text2.getText());

			if(Starter.ws.findWarehouseByName(con.text1.getText()) == null ||
					Starter.ws.findWarehouseByName(con.text1.getText()).size() == 0){
				showLog("仓库错误！");
				return;
			}
			Warehouse war = Starter.ws.findWarehouseByName(con.text1.getText()).get(0);

			List<Inventory> inventory = Starter.its.findInventoryByWarehouseId(war.getId());

			boolean find = false;
			for(int j = 0; j < inventory.size(); j++){
				if(inventory.get(j).getGoodId() == good.getId()){
					if(inventory.get(j).getAmount() < num){
						showLog("商品库存不足！");
						return;
					}
				}
				else{
					find = true;
					break;
				}
			}
			if(!find){
				showLog("商品无库存！");
				return;
			}
		}

		Invoice invoice = new Invoice(	//添加新invoice
				0,
				customer.getId(),
				new Timestamp(System.currentTimeMillis()),
				new BigDecimal(textPOS3.getText()),
				"已付清",
				new BigDecimal(textPOS4.getText()),
				userID
		);
		int ID = Starter.is.insertInvoice(invoice);

		for(int i = 0; i < controlPOS.size(); i++){	//更新仓库
			Info_08_Controller con = ((Info_08_Controller)controlPOS.get(i));

			Good good = Starter.gs.findGoodByName(con.cbb.getSelectionModel().getSelectedItem()).get(0);
			BigDecimal big = new BigDecimal(con.text0.getText());
			int num = Integer.valueOf(con.text2.getText());
			Warehouse war = Starter.ws.findWarehouseByName(con.text1.getText()).get(0);

			List<Inventory> inventory = Starter.its.findInventoryByWarehouseId(war.getId());
			for(int j = 0; j < inventory.size(); j++) {
				if (inventory.get(j).getGoodId() == good.getId()) {
					if (inventory.get(j).getAmount() == num) {
						Starter.its.deleteInventory(inventory.get(j).getId());
					} else {
						inventory.get(j).setAmount(inventory.get(j).getAmount() - num);
						Starter.its.updateInventory(inventory.get(j));
					}
				}
			}

			InvoiceLine invoiceLine = new InvoiceLine(	//添加新invoiceLine
					0,
					ID,
					good.getId(),
					war.getId(),
					big,
					num
			);
			Starter.ils.insertInvoiceLine(invoiceLine);
		}

		Payment payment = new Payment(		//添加支付信息
				0,
				ID,
				new BigDecimal(textPOS3.getText()),
				timestamp
		);
		Starter.ps.insertPayment(payment);

		initListretail();

		initPOS();
		initListMap(vboxPOS);
		initVbox(vboxPOS);

		showLog("交易成功！");
	}

	@FXML
	void butCleanPosHandler(ActionEvent event){
		initListretail();
		initPOS();
		initListMap(vboxPOS);
		initVbox(vboxPOS);
	}

	@FXML
	void butAddPosHandler(ActionEvent event){
		if(controlPOS == null) {
			controlPOS = new ArrayList<>();
		}
		listMap.get(vboxPOS).add(loadNode("/Info_08_POS.fxml"));
		controlPOS.add(control);
		initVbox(vboxPOS);

		System.out.println(listMap.get(vboxPOS));
	}

//订单信息__________________________________________________________________

	@FXML
	void butSearchSaleTicketHandler(ActionEvent event) {    //查询事件

		List<Node> nodeList = new ArrayList<>(listMap.get(vboxSaleTicketAbs));
		List<Object> controllerList = new ArrayList<>(controlSaleTicketAbs);
		List<Node> newList = new ArrayList<>();
		List<Object> newControlList = new ArrayList<>();

		int count = 0;
		for(int i = 0; i < nodeList.size(); i++){
			But_03_Controller controller = (But_03_Controller)controllerList.get(i);

			if(controller.button.getText().contains(textSaleTicketAbs.getText())){

				newList.add(nodeList.get(i));
				newControlList.add(controllerList.get(i));
				count++;

			}
		}
		controlSaleTicketAbs = newControlList;
		listMap.get(vboxSaleTicketAbs).clear();
		listMap.get(vboxSaleTicketAbs).addAll(newList);
		initVbox(vboxSaleTicketAbs);
		showLog("共查找到:" + count + "项信息。");
	}

	@FXML
	void butAddSaleTicketHandler(ActionEvent event) {	//添加销售单事件

		initListclient();

		controlCurrentSaleTicket = null;
		initSaleTicket();

		initListMap(vboxSaleTicket);
		initVbox(vboxSaleTicket);
	}

	@FXML
	void butSaveSaleTicketHandler(ActionEvent event) {	//保存销售单事件

		if(controlSaleTicket == null ||controlSaleTicket.size() == 0){
			showLog("销售单内容为空！");
			return;
		}

		List<Customer> list = Starter.cs.findCustomerByName(cbbSaleTicket.getSelectionModel().getSelectedItem());
		if(list == null || list.size() == 0){
			showLog("未找到客户信息！");
			return;
		}

		for(int i = 0; i < controlSaleTicket.size(); i++){
			Info_03_Controller controller =  ((Info_03_Controller)controlSaleTicket.get(i));

			List<Good> list1 = Starter.gs.findGoodByName(controller.cbb.getSelectionModel().getSelectedItem());
			List<Warehouse> list2 = Starter.ws.findWarehouseByName(controller.text1.getText());

			if(list1 == null || list1.size() == 0 ||
				list2 == null || list2.size() == 0 ||
				!Tools.isNumber(controller.text0.getText()) ||
				!Tools.isInteger(controller.text2.getText())
			){
				showLog("表单项信息错误！");
				return;
			}
		}

		if(controlCurrentSaleTicket == null){	//新销售单

			Draft draft = new Draft(
					0,
					list.get(0).getId(),
					userID,
					"未审核"
					);

			int ID = Starter.ds.insertDraft(draft);

			initListMap(vboxSaleTicketAbs);
			initVbox(vboxSaleTicketAbs);

			for(int i = 0; i < controlSaleTicketAbs.size(); i++){
				But_03_Controller controller = (But_03_Controller) controlSaleTicketAbs.get(i);
				if(controller.ID == ID){
					controlCurrentSaleTicket = controller;
					break;
				}
			}
		}
		else {		//已有销售单
			Draft draft = Starter.ds.findDraftById(controlCurrentSaleTicket.ID);
			draft.setCustomerId(Starter.cs.findCustomerByName(cbbSaleTicket.getSelectionModel().getSelectedItem()).get(0).getId());
			Starter.ds.updateDraft(draft);
		}

		for(int i = 0; i < controlSaleTicket.size(); i++){
			Info_03_Controller controller =  ((Info_03_Controller)controlSaleTicket.get(i));

			List<Good> list1 = Starter.gs.findGoodByName(controller.cbb.getSelectionModel().getSelectedItem());
			List<Warehouse> list2 = Starter.ws.findWarehouseByName(controller.text1.getText());

			DraftLine dl = new DraftLine(
					controller.ID,
					controlCurrentSaleTicket.ID,
					list1.get(0).getId(),
					Integer.valueOf(controller.text2.getText()),
					list2.get(0).getId(),
					new BigDecimal(controller.text0.getText())
			);

			if(controller.ID == -1){	//新表项
				Starter.dls.insertDraftLine(dl);
			}
			else {
				Starter.dls.updateDraftLine(dl);
			}
		}

		controlCurrentSaleTicket = null;
		initListMap(vboxSaleTicket);
		initListMap(vboxSaleTicketAbs);
		initVbox(vboxSaleTicketAbs);
		initVbox(vboxSaleTicket);

		initSaleTicket();

		calculateSaleTicket();

		showLog("保存成功！");
	}

	@FXML
	void butDeleteSaleTicketHandler(ActionEvent event) {	//删除销售单事件
		if(controlCurrentSaleTicket != null) {

			List<DraftLine> list = Starter.dls.findDraftLineByDraftId(controlCurrentSaleTicket.ID);

			if(list != null){
				int size = list.size();
				for(int i = 0; i < size; i++){
					Starter.dls.deleteDraftLine(list.get(0).getId());
				}
			}

			Starter.ds.deleteDraft(controlCurrentSaleTicket.ID);

			controlCurrentSaleTicket = null;

			initListMap(vboxSaleTicket);
			initListMap(vboxSaleTicketAbs);
			initVbox(vboxSaleTicket);
			initVbox(vboxSaleTicketAbs);

			initSaleTicket();

			showLog("删除成功！");
		}
	}

	@FXML
	void butSubmitSaleTicketHandler(ActionEvent event) {	//提交销售单事件

		if(controlCurrentSaleTicket == null){
			showLog("请保存/选择销售单！");
			return;
		}

		if(Starter.cs.findCustomerByName(cbbSaleTicket.getSelectionModel().getSelectedItem()) == null){
			showLog("顾客名错误！");
			return;
		}
		Customer customer = Starter.cs.findCustomerByName(cbbSaleTicket.getSelectionModel().getSelectedItem()).get(0);

		if(controlSaleTicket == null || controlSaleTicket.size() == 0){
			showLog("请添加商品！");
			return;
		}

		for(int i = 0; i < controlSaleTicket.size(); i++){	//输入检测
			Info_03_Controller con = ((Info_03_Controller)controlSaleTicket.get(i));

			if(Starter.gs.findGoodByName(con.cbb.getSelectionModel().getSelectedItem()) == null){
				showLog("商品名错误！");
				return;
			}
			Good good = Starter.gs.findGoodByName(con.cbb.getSelectionModel().getSelectedItem()).get(0);

			if(!Tools.isNumber(con.text0.getText()) || !Tools.isInteger(con.text2.getText())){
				showLog("数字错误！");
				return;
			}
			BigDecimal big = new BigDecimal(con.text0.getText());
			int num = Integer.valueOf(con.text2.getText());

			if(Starter.ws.findWarehouseByName(con.text1.getText()) == null){
				showLog("仓库错误！");
				return;
			}
			Warehouse war = Starter.ws.findWarehouseByName(con.text1.getText()).get(0);

			List<Inventory> inventory = Starter.its.findInventoryByWarehouseId(war.getId());

			boolean find = false;
			for(int j = 0; j < inventory.size(); j++){
				if(inventory.get(j).getGoodId() == good.getId()){
					if(inventory.get(j).getAmount() < num){
						showLog("商品库存不足！");
						return;
					}
				}
				else{
					find = true;
					break;
				}
			}
			if(!find){
				showLog("商品无库存！");
				return;
			}
		}

		Draft draft = Starter.ds.findDraftById(controlCurrentSaleTicket.ID);
		draft.setStatus("待审核");
		Starter.ds.updateDraft(draft);

		controlCurrentSaleTicket = null;
		initSaleTicket();
		initListMap(vboxSaleTicket);
		initListMap(vboxSaleTicketAbs);
		initVbox(vboxSaleTicket);
		initVbox(vboxSaleTicketAbs);

		showLog("提交成功，请等待审核！");

	}

	@FXML
	void butAddSaleTicketEntryHandler(ActionEvent event) {	//添加销售单表项事件
		//TODO
		listMap.get(vboxSaleTicket).add(0, loadNode("/Info_03_SaleTicket.fxml"));	//只显示，不存储
		//
		((Info_03_Controller)control).ID = -1;	//标记未存储
		controlSaleTicket.add(control);

		initVbox(vboxSaleTicket);
	}


	@FXML
	void butSearchCheckHandler(ActionEvent event) {    //查询事件

		List<Node> nodeList = new ArrayList<>(listMap.get(vboxCheckAbs));
		List<Object> controllerList = new ArrayList<>(controlCheckAbs);
		List<Node> newList = new ArrayList<>();
		List<Object> newControlList = new ArrayList<>();

		int count = 0;
		for(int i = 0; i < nodeList.size(); i++){
			But_04_Controller controller = (But_04_Controller)controllerList.get(i);

			if(controller.button.getText().contains(textCheckAbs.getText())){

				newList.add(nodeList.get(i));
				newControlList.add(controllerList.get(i));
				count++;

			}
		}
		controlCheckAbs = newControlList;
		listMap.get(vboxCheckAbs).clear();
		listMap.get(vboxCheckAbs).addAll(newList);
		initVbox(vboxCheckAbs);
		showLog("共查找到:" + count + "项信息。");
	}

	@FXML
	void butCheckOKHandler(ActionEvent event) {    //审核通过事件

		if(controlCheck != null){
			Customer customer = Starter.cs.findCustomerById(Starter.ds.findDraftById(controlCurrentCheck.ID).getCustomerId());

			System.out.println(controlCurrentCheck.ID);
			System.out.println(customer);

			Invoice invoice = new Invoice(	//添加新invoice
					0,
					customer.getId(),
					new Timestamp(System.currentTimeMillis()),
					new BigDecimal(textCheck1.getText()),
					"未付款",
					new BigDecimal(textCheck2.getText()),
					userID
			);
			int ID = Starter.is.insertInvoice(invoice);

			for(int i = 0; i < controlCheck.size(); i++){	//更新仓库
				Info_04_Controller con = ((Info_04_Controller)controlCheck.get(i));

				Good good = Starter.gs.findGoodByName(con.text0.getText()).get(0);
				BigDecimal big = new BigDecimal(con.text1.getText());
				int num = Integer.valueOf(con.text3.getText());
				Warehouse war = Starter.ws.findWarehouseByName(con.text2.getText()).get(0);

				List<Inventory> inventory = Starter.its.findInventoryByWarehouseId(war.getId());
				for(int j = 0; j < inventory.size(); j++) {
					if (inventory.get(j).getGoodId() == good.getId()) {
						if (inventory.get(j).getAmount() == num) {
							Starter.its.deleteInventory(inventory.get(j).getId());
						} else {
							inventory.get(j).setAmount(inventory.get(j).getAmount() - num);
							Starter.its.updateInventory(inventory.get(j));
						}
					}
				}

				InvoiceLine invoiceLine = new InvoiceLine(	//添加新invoiceLine
						0,
						ID,
						good.getId(),
						war.getId(),
						big,
						num
				);
				Starter.ils.insertInvoiceLine(invoiceLine);
			}

			Draft draft = Starter.ds.findDraftById(controlCurrentCheck.ID);	//删除草稿信息
			List<DraftLine> draftLines = Starter.dls.findDraftLineByDraftId(draft.getId());
			int size = draftLines.size();
			for(int i = 0; i < size; i++){
				Starter.dls.deleteDraftLine(draftLines.get(0).getId());
			}
			Starter.ds.deleteDraft(draft.getId());

			showLog("审核完成，订单提交成功！");

			controlCurrentCheck = null;
			initListMap(vboxCheck);
			initVbox(vboxCheck);
			initListMap(vboxCheckAbs);
			initVbox(vboxCheckAbs);

			initCheck();
		}
		else{
			showLog("请选择待审核销售单！");
		}
	}

	@FXML
	void butCheckNOHandler(ActionEvent event) {    //审核不通过事件
		if(controlCurrentCheck != null) {

			Draft draft = Starter.ds.findDraftById(controlCurrentCheck.ID);
			draft.setStatus("审核未通过");
			Starter.ds.updateDraft(draft);

			controlCurrentCheck = null;
			initListMap(vboxCheck);
			initVbox(vboxCheck);
			initListMap(vboxCheckAbs);
			initVbox(vboxCheckAbs);

			initCheck();

			showLog("审核完成！");
		}
		else{
			showLog("请选择待审核销售单！");
		}
	}

	@FXML
	void butSearchOrderHandler(ActionEvent event) {    //查询事件
		List<Node> nodeList = new ArrayList<>(listMap.get(vboxOrderAbs));
		List<Object> controllerList = new ArrayList<>(controlOrderAbs);
		List<Node> newList = new ArrayList<>();
		List<Object> newControlList = new ArrayList<>();

		int count = 0;
		for(int i = 0; i < nodeList.size(); i++){
			But_01_Controller controller = (But_01_Controller)controllerList.get(i);

			if(controller.button.getText().contains(textOrderAbs.getText())){

				newList.add(nodeList.get(i));
				newControlList.add(controllerList.get(i));
				count++;

			}
		}
		controlOrderAbs = newControlList;
		listMap.get(vboxOrderAbs).clear();
		listMap.get(vboxOrderAbs).addAll(newList);
		initVbox(vboxOrderAbs);
		showLog("共查找到:" + count + "项信息。");
	}

	@FXML
	void butRefundOrderHandler(ActionEvent event) {	//订单退货事件
		if(controlCurrentOrder != null) {
			Invoice invoice = Starter.is.findInvoiceById(controlCurrentOrder.ID);
			List<InvoiceLine> list = Starter.ils.findInvoiceLineByInvoiceId(invoice.getId());

			if(invoice.getStatus().contains("已退货") || invoice.getStatus().contains("退货单")){	//已经退货
				showLog("已退货！");
				return;
			}

			invoice.setStatus("已退货," + invoice.getStatus());
			Starter.is.updateInvoice(invoice);

			Invoice refund = new Invoice(	//生成退货订单
					0,
					invoice.getCustomerId(),
					new Timestamp(System.currentTimeMillis()),
					invoice.getTotalPrice().multiply(new BigDecimal(-1)),
					"退货单",
					invoice.getGrossMargin().multiply(new BigDecimal(-1)),
					userID
					);

			int ID = Starter.is.insertInvoice(refund);

			for(int i = 0; i < list.size(); i++){	//生成退货订单项
				list.get(i).setAmount(0 - list.get(i).getAmount());
				list.get(i).setId(0);
				list.get(i).setInvoiceId(ID);
				Starter.ils.insertInvoiceLine(list.get(i));
			}

			Payment payment = new Payment(	//生成退货支付记录
					0,
					ID,
					invoice.getTotalPrice().multiply(new BigDecimal(-1)),
					new Timestamp(System.currentTimeMillis())
			);
			Starter.ps.insertPayment(payment);

			for(int i = 0; i < list.size(); i++){	//更新库存
				Warehouse warehouse = Starter.ws.findWarehouseById(list.get(i).getWarehouseId());
				List<Inventory> inventoryList = Starter.its.findInventoryByWarehouseId(warehouse.getId());

				int j = -1;
				if(inventoryList != null){
					for(j = 0; j < inventoryList.size(); j++){
						if(inventoryList.get(j).getGoodId() == list.get(i).getGoodId()){	//仓库中仍有该库存

							inventoryList.get(j).setAmount(inventoryList.get(j).getAmount() - list.get(i).getAmount());
							logInventory(inventoryList.get(j), "退货: " + String.valueOf(0- list.get(i).getAmount()) + " 件。");
							Starter.its.updateInventory(inventoryList.get(j));
							break;
						}
					}
				}
				if(j == -1 || j == inventoryList.size()){	//仓库未有该库存

					Inventory inventory = new Inventory(
							0,
							warehouse.getId(),
							list.get(i).getGoodId(),
							0- list.get(i).getAmount(),
							"退货: " + String.valueOf(0- list.get(i).getAmount()) + " 件。"
					);
					Starter.its.insertInventory(inventory);
				}
			}

			controlCurrentOrder = null;
			initOrder();
			initListMap(vboxOrder);
			initListMap(vboxOrderAbs);
			initVbox(vboxOrder);
			initVbox(vboxOrderAbs);

			showLog("订单退货成功！");
		}
	}

	@FXML
	void butDeleteOrderHandler(ActionEvent event) {	//删除订单事件
		if(controlCurrentOrder != null) {

			Invoice invoice = Starter.is.findInvoiceById(controlCurrentOrder.ID);
			List<InvoiceLine> invoiceLineList = Starter.ils.findInvoiceLineByInvoiceId(invoice.getId());
			List<Payment> paymentList = Starter.ps.findPaymentByInvoiceId(invoice.getId());

			if(invoiceLineList != null){
				for(int i = 0; i < invoiceLineList.size(); i++){
					Starter.ils.deleteInvoiceLine(invoiceLineList.get(i).getId());
				}
			}
			if(paymentList != null){
				for(int i = 0; i < paymentList.size(); i++){
					Starter.ps.deletePayment(paymentList.get(i).getId());
				}
			}
			Starter.is.deleteInvoice(invoice.getId());

			controlCurrentOrder = null;
			initOrder();
			initListMap(vboxOrder);
			initListMap(vboxOrderAbs);
			initVbox(vboxOrder);
			initVbox(vboxOrderAbs);

			showLog("订单及关联信息删除成功！");
		}
	}

	@FXML
	void butShowPaymentHandler(ActionEvent event) {	//显示支付信息事件
		if(controlCurrentOrder != null){

			Main_Controller controller = (Main_Controller) Starter.controllers.get("Main_Controller");

			List<Invoice> list = new ArrayList<>();
			list.add(Starter.is.findInvoiceById(controlCurrentOrder.ID));

			controller.tabpaneMain.getSelectionModel().select(controller.tabPayment);

			controller.controlPaymentAbs = new ArrayList<>();
			controller.listMap.get(controller.vboxPaymentAbs).clear();
			if(list != null){
				for(int i = 0; i < list.size(); i++) {
					controller.listMap.get(controller.vboxPaymentAbs).add(controller.loadNode("/But_02_Payment.fxml"));
					((But_02_Controller)controller.control).button.setText(Starter.cs.findCustomerById(list.get(i).getCustomerId()).getName()
							+ " " + list.get(i).getId());
					((But_02_Controller)controller.control).ID = list.get(i).getId();

					controller.controlPaymentAbs.add(controller.control);
				}
			}
			if(controller.controlPaymentAbs.size() == 0){
				controller.controlPaymentAbs = null;
			}

			controller.initVbox(controller.vboxPaymentAbs);
		}
	}

//付款信息__________________________________________________________________

	@FXML
	void butSearchPaymentHandler(ActionEvent event) {    //查询事件
		List<Node> nodeList = new ArrayList<>(listMap.get(vboxPaymentAbs));
		List<Object> controllerList = new ArrayList<>(controlPaymentAbs);
		List<Node> newList = new ArrayList<>();
		List<Object> newControlList = new ArrayList<>();

		int count = 0;
		for(int i = 0; i < nodeList.size(); i++){
			But_02_Controller controller = (But_02_Controller)controllerList.get(i);

			if(controller.button.getText().contains(textPaymentAbs.getText())){

				newList.add(nodeList.get(i));
				newControlList.add(controllerList.get(i));
				count++;

			}
		}
		controlPaymentAbs= newControlList;
		listMap.get(vboxPaymentAbs).clear();
		listMap.get(vboxPaymentAbs).addAll(newList);
		initVbox(vboxPaymentAbs);
		showLog("共查找到:" + count + "项信息。");
	}

	@FXML
	void butSavePaymentHandler(ActionEvent event) {	//保存付款单事件

		if(controlCurrentPayment != null){

			Invoice invoice = Starter.is.findInvoiceById(controlCurrentPayment.ID);
			Double count = Double.valueOf(0);

			if(invoice.getStatus().contains("退货单")){
				showLog("退货单无法修改！");
				return;
			}

			for(int i = 0; i < controlPayment.size(); i++){		//输入检测
				Info_05_Controller controller = (Info_05_Controller)controlPayment.get(i);
				if(controller.text1.getText() == null || controller.text1.getText().isEmpty() ||
						!Tools.isNumber(controller.text1.getText())){
					showLog("表单信息错误！");
					return;
				}
				if(Double.valueOf(controller.text1.getText()) < 0){
					showLog("支付金额不能为负！");
					return;
				}
				count += Double.valueOf(controller.text1.getText());
			}
			if(count > invoice.getTotalPrice().doubleValue()){
				showLog("支付金额超过总价！");
				return;
			}

			for(int i = 0; i < controlPayment.size(); i++){			//添加支付项
				Info_05_Controller controller = (Info_05_Controller)controlPayment.get(i);

				if(controller.ID == -1){		//新支付项
					Payment payment = new Payment(
							0,
							invoice.getId(),
							new BigDecimal(controller.text1.getText()),
							controller.timestamp
					);
					Starter.ps.insertPayment(payment);
				}
				else{		//已有支付项
					Payment payment = Starter.ps.findPaymentById(controller.ID);
					payment.setAmount(new BigDecimal(controller.text1.getText()));
					Starter.ps.updatePayment(payment);
				}

			}

			updateInvoiceState(invoice);		//更新invoice状态

			initListMap(vboxPayment);
			initVbox(vboxPayment);
			initPayment();

			showLog("保存支付信息成功！");
		}
	}

	@FXML
	void butAddPaymentEntryHandler(ActionEvent event) {	//添加付款单表项事件
		if(controlCurrentPayment != null){
			//TODO
			listMap.get(vboxPayment).add(0, loadNode("/Info_05_Payment.fxml"));	//只显示，未存储
			//

			((Info_05_Controller)control).ID = -1;	//标记未存储
			controlPayment.add(control);
			initVbox(vboxPayment);
		}
	}

	@FXML
	void butShowOrderHandler(ActionEvent event) {	//显示订单事件
		if(controlCurrentPayment != null){

			Main_Controller controller = (Main_Controller) Starter.controllers.get("Main_Controller");

			List<Invoice> list = new ArrayList<>();
			list.add(Starter.is.findInvoiceById(controlCurrentPayment.ID));

			controller.tabpaneMain.getSelectionModel().select(controller.tabTicket);
			controller.tabpaneTicket.getSelectionModel().select(controller.tabOrder);

			controller.controlOrderAbs = new ArrayList<>();
			controller.listMap.get(controller.vboxOrderAbs).clear();
			if(list != null){
				for(int i = 0; i < list.size(); i++) {
					controller.listMap.get(controller.vboxOrderAbs).add(controller.loadNode("/But_01_Order.fxml"));
					((But_01_Controller)controller.control).button.setText(Starter.cs.findCustomerById(list.get(i).getCustomerId()).getName()
							+ " " + list.get(i).getId());
					((But_01_Controller)controller.control).ID = list.get(i).getId();

					controller.controlOrderAbs.add(controller.control);
				}
			}
			if(controller.controlOrderAbs.size() == 0){
				controller.controlOrderAbs = null;
			}

			controller.initVbox(controller.vboxOrderAbs);
		}
	}

//商品信息__________________________________________________________________

	@FXML
	void butSearchGoodHandler(ActionEvent event) {    //查询事件
		List<Node> nodeList = new ArrayList<>(listMap.get(vboxGood));
		List<Object> controllerList = new ArrayList<>(controlGood);
		List<Node> newList = new ArrayList<>();
		List<Object> newControlList = new ArrayList<>();

		int count = 0;
		for(int i = 0; i < nodeList.size(); i++){
			Info_06_Controller controller = (Info_06_Controller)controllerList.get(i);

			if(controller.text0.getText().contains(textGood0.getText()) &&
					controller.text1.getText().contains(textGood1.getText()) &&
					controller.text2.getText().contains(textGood2.getText()) &&
					controller.text3.getText().contains(textGood3.getText()) &&
					controller.text4.getText().contains(textGood4.getText())){

				newList.add(nodeList.get(i));
				newControlList.add(controllerList.get(i));
				count++;

			}
		}
		controlGood = newControlList;
		listMap.get(vboxGood).clear();
		listMap.get(vboxGood).addAll(newList);
		initVbox(vboxGood);
		showLog("共查找到:" + count + "项信息。");
	}

	@FXML
	void butSort0GoodHandler(ActionEvent event) {    //排序事件

		clock = -clock;

		List<Node> list = new ArrayList<>(listMap.get(vboxGood));
		List<Object> controls = new ArrayList<>(controlGood);
		List<Object> newControls = new ArrayList<>();
		listMap.get(vboxGood).clear();

		while(controls.size() > 0){
			int index = 0;

			String str = ((Info_06_Controller)controls.get(0)).text0.getText();

			for (int i = 0; i < controls.size(); i++){
				String temp = ((Info_06_Controller)controls.get(i)).text0.getText();
				if((temp.compareTo(str) * clock) > 0){
					index = i;
					str = temp;
				}
			}
			listMap.get(vboxGood).add(list.get(index));
			newControls.add(controls.get(index));
			list.remove(index);
			controls.remove(index);
		}
		controlGood = newControls;
		initVbox(vboxGood);
	}

	@FXML
	void butSort1GoodHandler(ActionEvent event) {    //排序事件

		clock = -clock;

		List<Node> list = new ArrayList<>(listMap.get(vboxGood));
		List<Object> controls = new ArrayList<>(controlGood);
		List<Object> newControls = new ArrayList<>();
		listMap.get(vboxGood).clear();

		while(controls.size() > 0){
			int index = 0;

			double num = Double.valueOf(((Info_06_Controller)controls.get(0)).text1.getText());

			for (int i = 0; i < controls.size(); i++){
				double temp = Double.valueOf(((Info_06_Controller)controls.get(i)).text1.getText());
				if(((temp - num) * clock) > 0){
					index = i;
					num = temp;
				}
			}
			listMap.get(vboxGood).add(list.get(index));
			newControls.add(controls.get(index));
			list.remove(index);
			controls.remove(index);
		}
		controlGood = newControls;
		initVbox(vboxGood);
	}

	@FXML
	void butSort2GoodHandler(ActionEvent event) {    //排序事件

		clock = -clock;

		List<Node> list = new ArrayList<>(listMap.get(vboxGood));
		List<Object> controls = new ArrayList<>(controlGood);
		List<Object> newControls = new ArrayList<>();
		listMap.get(vboxGood).clear();

		while(controls.size() > 0){
			int index = 0;

			double num = Double.valueOf(((Info_06_Controller)controls.get(0)).text2.getText());

			for (int i = 0; i < controls.size(); i++){
				double temp = Double.valueOf(((Info_06_Controller)controls.get(i)).text2.getText());
				if(((temp - num) * clock) > 0){
					index = i;
					num = temp;
				}
			}
			listMap.get(vboxGood).add(list.get(index));
			newControls.add(controls.get(index));
			list.remove(index);
			controls.remove(index);
		}
		controlGood = newControls;
		initVbox(vboxGood);
	}

	@FXML
	void butSort3GoodHandler(ActionEvent event) {    //排序事件

		clock = -clock;

		List<Node> list = new ArrayList<>(listMap.get(vboxGood));
		List<Object> controls = new ArrayList<>(controlGood);
		List<Object> newControls = new ArrayList<>();
		listMap.get(vboxGood).clear();

		while(controls.size() > 0){
			int index = 0;

			double num = Double.valueOf(((Info_06_Controller)controls.get(0)).text3.getText());

			for (int i = 0; i < controls.size(); i++){
				double temp = Double.valueOf(((Info_06_Controller)controls.get(i)).text3.getText());
				if(((temp - num) * clock) > 0){
					index = i;
					num = temp;
				}
			}
			listMap.get(vboxGood).add(list.get(index));
			newControls.add(controls.get(index));
			list.remove(index);
			controls.remove(index);
		}
		controlGood = newControls;
		initVbox(vboxGood);
	}


	@FXML
	void butAddGoodHandler(ActionEvent event) {	//添加商品事件
		showNewWindow("/Window_02_AddGoodInfo.fxml");
		((Window_02_Controller)control).butDefault.requestFocus();
	}

//统计信息__________________________________________________________________

	@FXML
	void butSort0AnalClientHandler(ActionEvent event) {    //排序事件

		clock = -clock;

		List<Node> list = new ArrayList<>(listMap.get(vboxAnalClient));
		List<Object> controls = new ArrayList<>(controlAnalClient);
		List<Object> newControls = new ArrayList<>();
		listMap.get(vboxAnalClient).clear();

		while(controls.size() > 0){
			int index = 0;

			String str = ((Info_10_Controller)controls.get(0)).text0.getText();

			for (int i = 0; i < controls.size(); i++){
				String temp = ((Info_10_Controller)controls.get(i)).text0.getText();
				if((temp.compareTo(str) * clock) > 0){
					index = i;
					str = temp;
				}
			}
			listMap.get(vboxAnalClient).add(list.get(index));
			newControls.add(controls.get(index));
			list.remove(index);
			controls.remove(index);
		}
		controlAnalClient = newControls;
		initVbox(vboxAnalClient);
	}

	@FXML
	void butSort1AnalClientHandler(ActionEvent event) {    //排序事件

		clock = -clock;

		List<Node> list = new ArrayList<>(listMap.get(vboxAnalClient));
		List<Object> controls = new ArrayList<>(controlAnalClient);
		List<Object> newControls = new ArrayList<>();
		listMap.get(vboxAnalClient).clear();

		while(controls.size() > 0){
			int index = 0;

			double num = Double.valueOf(((Info_10_Controller)controls.get(0)).text1.getText());

			for (int i = 0; i < controls.size(); i++){
				double temp = Double.valueOf(((Info_10_Controller)controls.get(i)).text1.getText());
				if(((num - temp) * clock) > 0){
					index = i;
					num = temp;
				}
			}
			listMap.get(vboxAnalClient).add(list.get(index));
			newControls.add(controls.get(index));
			list.remove(index);
			controls.remove(index);
		}
		controlAnalClient = newControls;
		initVbox(vboxAnalClient);
	}

	@FXML
	void butSort2AnalClientHandler(ActionEvent event) {    //排序事件

		clock = -clock;

		List<Node> list = new ArrayList<>(listMap.get(vboxAnalClient));
		List<Object> controls = new ArrayList<>(controlAnalClient);
		List<Object> newControls = new ArrayList<>();
		listMap.get(vboxAnalClient).clear();

		while(controls.size() > 0){
			int index = 0;

			double num = Double.valueOf(((Info_10_Controller)controls.get(0)).text2.getText());

			for (int i = 0; i < controls.size(); i++){
				double temp = Double.valueOf(((Info_10_Controller)controls.get(i)).text2.getText());
				if(((num - temp) * clock) > 0){
					index = i;
					num = temp;
				}
			}
			listMap.get(vboxAnalClient).add(list.get(index));
			newControls.add(controls.get(index));
			list.remove(index);
			controls.remove(index);
		}
		controlAnalClient = newControls;
		initVbox(vboxAnalClient);
	}

	@FXML
	void butSort3AnalClientHandler(ActionEvent event) {    //排序事件

		clock = -clock;

		List<Node> list = new ArrayList<>(listMap.get(vboxAnalClient));
		List<Object> controls = new ArrayList<>(controlAnalClient);
		List<Object> newControls = new ArrayList<>();
		listMap.get(vboxAnalClient).clear();

		while(controls.size() > 0){
			int index = 0;

			String str = ((Info_10_Controller)controls.get(0)).text3.getText();

			for (int i = 0; i < controls.size(); i++){
				String temp = ((Info_10_Controller)controls.get(i)).text3.getText();
				if((temp.compareTo(str) * clock) > 0){
					index = i;
					str = temp;
				}
			}
			listMap.get(vboxAnalClient).add(list.get(index));
			newControls.add(controls.get(index));
			list.remove(index);
			controls.remove(index);
		}
		controlAnalClient = newControls;
		initVbox(vboxAnalClient);
	}

	@FXML
	void butSort0AnalGoodHandler(ActionEvent event) {    //排序事件

		clock = -clock;

		List<Node> list = new ArrayList<>(listMap.get(vboxAnalGood));
		List<Object> controls = new ArrayList<>(controlAnalGood);
		List<Object> newControls = new ArrayList<>();
		listMap.get(vboxAnalGood).clear();

		while(controls.size() > 0){
			int index = 0;

			String str = ((Info_11_Controller)controls.get(0)).text0.getText();

			for (int i = 0; i < controls.size(); i++){
				String temp = ((Info_11_Controller)controls.get(i)).text0.getText();
				if((temp.compareTo(str) * clock) > 0){
					index = i;
					str = temp;
				}
			}
			listMap.get(vboxAnalGood).add(list.get(index));
			newControls.add(controls.get(index));
			list.remove(index);
			controls.remove(index);
		}
		controlAnalGood = newControls;
		initVbox(vboxAnalGood);
	}

	@FXML
	void butSort1AnalGoodHandler(ActionEvent event) {    //排序事件

		clock = -clock;

		List<Node> list = new ArrayList<>(listMap.get(vboxAnalGood));
		List<Object> controls = new ArrayList<>(controlAnalGood);
		List<Object> newControls = new ArrayList<>();
		listMap.get(vboxAnalGood).clear();

		while(controls.size() > 0){
			int index = 0;

			double num = Double.valueOf(((Info_11_Controller)controls.get(0)).text1.getText());

			for (int i = 0; i < controls.size(); i++){
				double temp = Double.valueOf(((Info_11_Controller)controls.get(i)).text1.getText());
				if(((num - temp) * clock) > 0){
					index = i;
					num = temp;
				}
			}
			listMap.get(vboxAnalGood).add(list.get(index));
			newControls.add(controls.get(index));
			list.remove(index);
			controls.remove(index);
		}
		controlAnalGood = newControls;
		initVbox(vboxAnalGood);
	}

	@FXML
	void butSort2AnalGoodHandler(ActionEvent event) {    //排序事件

		clock = -clock;

		List<Node> list = new ArrayList<>(listMap.get(vboxAnalGood));
		List<Object> controls = new ArrayList<>(controlAnalGood);
		List<Object> newControls = new ArrayList<>();
		listMap.get(vboxAnalGood).clear();

		while(controls.size() > 0){
			int index = 0;

			double num = Double.valueOf(((Info_11_Controller)controls.get(0)).text2.getText());

			for (int i = 0; i < controls.size(); i++){
				double temp = Double.valueOf(((Info_11_Controller)controls.get(i)).text2.getText());
				if(((num - temp) * clock) > 0){
					index = i;
					num = temp;
				}
			}
			listMap.get(vboxAnalGood).add(list.get(index));
			newControls.add(controls.get(index));
			list.remove(index);
			controls.remove(index);
		}
		controlAnalGood = newControls;
		initVbox(vboxAnalGood);
	}

	@FXML
	void butSort3AnalGoodHandler(ActionEvent event) {    //排序事件

		clock = -clock;

		List<Node> list = new ArrayList<>(listMap.get(vboxAnalGood));
		List<Object> controls = new ArrayList<>(controlAnalGood);
		List<Object> newControls = new ArrayList<>();
		listMap.get(vboxAnalGood).clear();

		while(controls.size() > 0){
			int index = 0;

			double num = Double.valueOf(((Info_11_Controller)controls.get(0)).text3.getText());

			for (int i = 0; i < controls.size(); i++){
				double temp = Double.valueOf(((Info_11_Controller)controls.get(i)).text3.getText());
				if(((num - temp) * clock) > 0){
					index = i;
					num = temp;
				}
			}
			listMap.get(vboxAnalGood).add(list.get(index));
			newControls.add(controls.get(index));
			list.remove(index);
			controls.remove(index);
		}
		controlAnalGood = newControls;
		initVbox(vboxAnalGood);
	}

	@FXML
	void butSort0AnalUserHandler(ActionEvent event) {    //排序事件

		clock = -clock;

		List<Node> list = new ArrayList<>(listMap.get(vboxAnalUser));
		List<Object> controls = new ArrayList<>(controlAnalUser);
		List<Object> newControls = new ArrayList<>();
		listMap.get(vboxAnalUser).clear();

		while(controls.size() > 0){
			int index = 0;

			String str = ((Info_12_Controller)controls.get(0)).text0.getText();

			for (int i = 0; i < controls.size(); i++){
				String temp = ((Info_12_Controller)controls.get(i)).text0.getText();
				if((temp.compareTo(str) * clock) > 0){
					index = i;
					str = temp;
				}
			}
			listMap.get(vboxAnalUser).add(list.get(index));
			newControls.add(controls.get(index));
			list.remove(index);
			controls.remove(index);
		}
		controlAnalUser = newControls;
		initVbox(vboxAnalUser);
	}

	@FXML
	void butSort1AnalUserHandler(ActionEvent event) {    //排序事件

		clock = -clock;

		List<Node> list = new ArrayList<>(listMap.get(vboxAnalUser));
		List<Object> controls = new ArrayList<>(controlAnalUser);
		List<Object> newControls = new ArrayList<>();
		listMap.get(vboxAnalUser).clear();

		while(controls.size() > 0){
			int index = 0;

			double num = Double.valueOf(((Info_12_Controller)controls.get(0)).text1.getText());

			for (int i = 0; i < controls.size(); i++){
				double temp = Double.valueOf(((Info_12_Controller)controls.get(i)).text1.getText());
				if(((num - temp) * clock) > 0){
					index = i;
					num = temp;
				}
			}
			listMap.get(vboxAnalUser).add(list.get(index));
			newControls.add(controls.get(index));
			list.remove(index);
			controls.remove(index);
		}
		controlAnalUser = newControls;
		initVbox(vboxAnalUser);
	}

	@FXML
	void butSort2AnalUserHandler(ActionEvent event) {    //排序事件

		clock = -clock;

		List<Node> list = new ArrayList<>(listMap.get(vboxAnalUser));
		List<Object> controls = new ArrayList<>(controlAnalUser);
		List<Object> newControls = new ArrayList<>();
		listMap.get(vboxAnalUser).clear();

		while(controls.size() > 0){
			int index = 0;

			double num = Double.valueOf(((Info_12_Controller)controls.get(0)).text2.getText());

			for (int i = 0; i < controls.size(); i++){
				double temp = Double.valueOf(((Info_12_Controller)controls.get(i)).text2.getText());
				if(((num - temp) * clock) > 0){
					index = i;
					num = temp;
				}
			}
			listMap.get(vboxAnalUser).add(list.get(index));
			newControls.add(controls.get(index));
			list.remove(index);
			controls.remove(index);
		}
		controlAnalUser = newControls;
		initVbox(vboxAnalUser);
	}

	@FXML
	void butSort3AnalUserHandler(ActionEvent event) {    //排序事件

		clock = -clock;

		List<Node> list = new ArrayList<>(listMap.get(vboxAnalUser));
		List<Object> controls = new ArrayList<>(controlAnalUser);
		List<Object> newControls = new ArrayList<>();
		listMap.get(vboxAnalUser).clear();

		while(controls.size() > 0){
			int index = 0;

			double num = Double.valueOf(((Info_12_Controller)controls.get(0)).text3.getText());

			for (int i = 0; i < controls.size(); i++){
				double temp = Double.valueOf(((Info_12_Controller)controls.get(i)).text3.getText());
				if(((num - temp) * clock) > 0){
					index = i;
					num = temp;
				}
			}
			listMap.get(vboxAnalUser).add(list.get(index));
			newControls.add(controls.get(index));
			list.remove(index);
			controls.remove(index);
		}
		controlAnalUser = newControls;
		initVbox(vboxAnalUser);
	}

	@FXML
	void butSort4AnalUserHandler(ActionEvent event) {    //排序事件

		clock = -clock;

		List<Node> list = new ArrayList<>(listMap.get(vboxAnalUser));
		List<Object> controls = new ArrayList<>(controlAnalUser);
		List<Object> newControls = new ArrayList<>();
		listMap.get(vboxAnalUser).clear();

		while(controls.size() > 0){
			int index = 0;

			double num = Double.valueOf(((Info_12_Controller)controls.get(0)).text4.getText());

			for (int i = 0; i < controls.size(); i++){
				double temp = Double.valueOf(((Info_12_Controller)controls.get(i)).text4.getText());
				if(((num - temp) * clock) > 0){
					index = i;
					num = temp;
				}
			}
			listMap.get(vboxAnalUser).add(list.get(index));
			newControls.add(controls.get(index));
			list.remove(index);
			controls.remove(index);
		}
		controlAnalUser = newControls;
		initVbox(vboxAnalUser);
	}








	@FXML
	void butAnalOverviewHandler(ActionEvent event){	//总览信息
		tabpaneAnalyze.getSelectionModel().select(0);

		initAnalTime();
		initAnal(1);
	}

	@FXML
	void butAnalClientHandler(ActionEvent event){	//客户信息
		tabpaneAnalyze.getSelectionModel().select(1);
		initListMap(vboxAnalClient);
		initVbox(vboxAnalClient);
	}

	@FXML
	void butAnalGoodHandler(ActionEvent event){	//商品信息
		tabpaneAnalyze.getSelectionModel().select(2);
		initListMap(vboxAnalGood);
		initVbox(vboxAnalGood);
	}

	@FXML
	void butAnalUserHandler(ActionEvent event){	//职员信息
		tabpaneAnalyze.getSelectionModel().select(3);
		initListMap(vboxAnalUser);
		initVbox(vboxAnalUser);
	}

	@FXML
	void butSetWeightHandler(ActionEvent event) {    //权重设置
		showNewWindow("/Window_15_AnalWeight.fxml");
		((Window_15_Controller)control).text0.setText(String.valueOf(weight0));
		((Window_15_Controller)control).text1.setText(String.valueOf(weight1));
		((Window_15_Controller)control).text2.setText(String.valueOf(weight2));

		((Window_15_Controller)control).butDefault.requestFocus();
	}

	@FXML
	void butAnalHandler(ActionEvent event) {    //查询
		initAnal(1);
	}

	@FXML
	void butChart1Handler(ActionEvent event) {    //查询
		initChart(1);
	}

	@FXML
	void butChart2Handler(ActionEvent event) {    //查询
		initChart(2);
	}

	@FXML
	void butChart3Handler(ActionEvent event) {    //查询
		initChart(3);
	}

	@FXML
	void butJump1Handler(ActionEvent event) {    //跳转
		tabpaneMain.getSelectionModel().select(tabAnalize);
		tabpaneAnalyze.getSelectionModel().select(tabAnalClient);
		initListMap(vboxAnalClient);
		initVbox(vboxAnalClient);
	}

	@FXML
	void butJump2Handler(ActionEvent event) {    //跳转
		tabpaneMain.getSelectionModel().select(tabAnalize);
		tabpaneAnalyze.getSelectionModel().select(tabAnalGood);
		initListMap(vboxAnalGood);
		initVbox(vboxAnalGood);
	}

	@FXML
	void butJump3Handler(ActionEvent event) {    //跳转
		tabpaneMain.getSelectionModel().select(tabAnalize);
		tabpaneAnalyze.getSelectionModel().select(tabAnalUser);
		initListMap(vboxAnalUser);
		initVbox(vboxAnalUser);
	}


//辅助方法__________________________________________________________________

	@FXML
	void butMinHandler(ActionEvent event) {	//最小化窗口
		Stage stage =(Stage)pane.getScene().getWindow();
		stage.setIconified(true);
	}

	@FXML
	void butCloseHandler(ActionEvent event) {	//关闭窗口
		Stage stage =(Stage)pane.getScene().getWindow();
		stage.close();
	}

	public Node loadNode(String filename) {	//获取Node实例
		try {

			FXMLLoader loader = new FXMLLoader();
			URL location = Starter.class.getResource(filename);
			loader.setLocation(location);

			Node node = (Node) loader.load();

			return node;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Node showNewWindow(String filename) {	//创建新界面

		Stage secondStage = new Stage();
		Node node = loadNode(filename);

		secondStage.initStyle(StageStyle.TRANSPARENT);//设定窗口无边框

		xOffset = 0;
		yOffset = 0;
		node.setOnMousePressed(new EventHandler<MouseEvent>() {		//设定无边框窗口拖动
			@Override
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
		});
		node.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				secondStage.setX(event.getScreenX() - xOffset);
				secondStage.setY(event.getScreenY() - yOffset);
			}
		});

		node.getStyleClass().add("window");		//添加弹窗样式

		Scene scene = new Scene((Pane)node);

		scene.setFill(null);

		scene.getStylesheets().add("/GUICSS.css");
		//scene.setOnKeyPressed(event1 -> secondStage.close());

		secondStage.setScene(scene);
		secondStage.initModality(Modality.APPLICATION_MODAL);
		secondStage.setResizable(false);    //设置窗口大小不可调
		secondStage.show();
		return node;
	}

	public void showLog(String info) {	//创建提示信息
		Node node = showNewWindow("/Window_01_Log.fxml");
		((Window_01_Controller)control).info.setText(info);

		((Pane)node).getScene().setOnKeyPressed(event1 -> ((Stage)((Pane)node).getScene().getWindow()).close());
	}

	public void cleanVBox(VBox vbox) {	//清空VBox中内容
		int size = vbox.getChildren().size();
		for(int i = 0; i < size; i++) {
			vbox.getChildren().remove(0);
		}
		vbox.setPrefHeight(50);
	}

	public Node addtoVBox(VBox vbox, Node node) {	//向VBox添加内容
		vbox.getChildren().add(0, node);
		vbox.setPrefHeight(vbox.getPrefHeight() + 30);
		return node;
	}

	public void initVbox(VBox vbox) {	//更新VBox
		cleanVBox(vbox);
		for(int i = 0; i < listMap.get(vbox).size(); i++) {
			addtoVBox(vbox, listMap.get(vbox).get(i));
		}
		vbox.setDisable(false);
	}

	public void initListMap(VBox vbox) {	//根据VBox更新对应的控件列表 TODO

		listMap.get(vbox).clear();

		if(vbox == vboxStorage) {
			if(warehouseID != -1) {

				controlStorage = new ArrayList<>();

				List<Inventory> list = Starter.its.findInventoryByWarehouseId(warehouseID);
				for(int i = 0; i < list.size(); i++) {
					listMap.get(vbox).add(loadNode("/Info_01_Storage.fxml"));
					((Info_01_Controller)control).text0.setText(Starter.gs.findGoodById(list.get(i).getGoodId()).getName());
					((Info_01_Controller)control).text1.setText(list.get(i).getGoodId().toString());
					((Info_01_Controller)control).text2.setText(list.get(i).getAmount().toString());
					((Info_01_Controller)control).ID = list.get(i).getId();

					controlStorage.add(control);
				}
			}
		}
		else if(vbox == vboxClient) {

			controlClient = new ArrayList<>();

			List<Customer> list = Starter.cs.getAllCustomer();
			for(int i = 0; i < list.size(); i++) {

				listMap.get(vbox).add(loadNode("/Info_02_Client.fxml"));
				((Info_02_Controller)control).text0.setText(list.get(i).getName());
				((Info_02_Controller)control).text1.setText(list.get(i).getPhone());
				((Info_02_Controller)control).text2.setText(list.get(i).getType());
				((Info_02_Controller)control).text3.setText(list.get(i).getAddress());
				((Info_02_Controller)control).ID = list.get(i).getId();

				controlClient.add(control);
			}
		}
		else if(vbox == vboxSaleTicket) {

			controlSaleTicket = new ArrayList<Object>();

			if(controlCurrentSaleTicket != null) {

				List<DraftLine> list = Starter.dls.findDraftLineByDraftId(controlCurrentSaleTicket.ID);
				if(list != null){
					for(int i = 0; i < list.size(); i++){
						listMap.get(vbox).add(loadNode("/Info_03_SaleTicket.fxml"));

						((Info_03_Controller)control).cbb.getSelectionModel().select(Starter.gs.findGoodById(list.get(i).getGoodId()).getName());
						((Info_03_Controller)control).text0.setText(Tools.formatBigDecimal(list.get(i).getUnitPrice()));
						((Info_03_Controller)control).text1.setText(Starter.ws.findWarehouseById(list.get(i).getWarehouseId()).getName());
						((Info_03_Controller)control).text2.setText(list.get(i).getAmount().toString());
						((Info_03_Controller)control).ID = list.get(i).getId();

						controlSaleTicket.add(control);
					}
				}
			}
		}
		else if(vbox == vboxCheck) {

			controlCheck = new ArrayList<Object>();

			if(controlCurrentCheck != null) {

				List<DraftLine> list = Starter.dls.findDraftLineByDraftId(controlCurrentCheck.ID);
				if(list != null){
					for(int i = 0; i < list.size(); i++){
						listMap.get(vbox).add(loadNode("/Info_04_Order.fxml"));

						((Info_04_Controller)control).text0.setText(Starter.gs.findGoodById(list.get(i).getGoodId()).getName());
						((Info_04_Controller)control).text1.setText(Tools.formatBigDecimal(list.get(i).getUnitPrice()));
						((Info_04_Controller)control).text2.setText(Starter.ws.findWarehouseById(list.get(i).getWarehouseId()).getName());
						((Info_04_Controller)control).text3.setText(list.get(i).getAmount().toString());
						((Info_04_Controller)control).ID = list.get(i).getId();

						controlCheck.add(control);
					}
				}
			}
		}
		else if(vbox == vboxOrder) {

			controlOrder = new ArrayList<>();

			if(controlCurrentOrder != null) {
				List<InvoiceLine> list = Starter.ils.findInvoiceLineByInvoiceId(controlCurrentOrder.ID);
				for(int i = 0; i < list.size(); i++) {
					listMap.get(vbox).add(loadNode("/Info_04_Order.fxml"));
					((Info_04_Controller)control).text0.setText(Starter.gs.findGoodById(list.get(i).getGoodId()).getName());
					((Info_04_Controller)control).text1.setText(Tools.formatBigDecimal(list.get(i).getUnitPrice()));
					((Info_04_Controller)control).text2.setText(Starter.ws.findWarehouseById(list.get(i).getWarehouseId()).getName());
					((Info_04_Controller)control).text3.setText(list.get(i).getAmount().toString());
					((Info_04_Controller)control).ID = list.get(i).getId();

					controlOrder.add(control);
				}
			}
		}
		else if(vbox == vboxPayment) {

			controlPayment = new ArrayList<Object>();

			if(controlCurrentPayment != null) {
				List<Payment> list = Starter.ps.findPaymentByInvoiceId(controlCurrentPayment.ID);
				for(int i = 0; i < list.size(); i++) {
					listMap.get(vbox).add(loadNode("/Info_05_Payment.fxml"));
					((Info_05_Controller)control).text0.setText(Tools.formatTimestamp(list.get(i).getTime()));
					((Info_05_Controller)control).text1.setText(Tools.formatBigDecimal(list.get(i).getAmount()));
					((Info_05_Controller)control).ID = list.get(i).getId();

					controlPayment.add(control);
				}
			}
		}
		else if(vbox == vboxGood) {

			controlGood = new ArrayList<>();

			List<Good> list = Starter.gs.getAllGood();
			for(int i = 0; i < list.size(); i++) {
				listMap.get(vbox).add(loadNode("/Info_06_Good.fxml"));
				((Info_06_Controller)control).text0.setText(list.get(i).getName());
				((Info_06_Controller)control).text1.setText(list.get(i).getId().toString());
				((Info_06_Controller)control).text2.setText(Tools.formatBigDecimal(list.get(i).getPriceA()));
				((Info_06_Controller)control).text3.setText(Tools.formatBigDecimal(list.get(i).getPriceB()));
				((Info_06_Controller)control).text4.setText(list.get(i).getState());
				((Info_06_Controller)control).ID = list.get(i).getId();

				controlGood.add(control);
			}
		}
		else if(vbox == vboxSaleTicketAbs) {

			controlSaleTicketAbs = new ArrayList<>();

			List<Draft> list = Starter.ds.getAllDraft();
			if(list != null){
				for(int i = 0; i < list.size(); i++) {

					if(list.get(i).getStatus().contains("未审核") ||
					   list.get(i).getStatus().contains("审核未通过")){

						listMap.get(vbox).add(loadNode("/But_03_SaleTicket.fxml"));
						((But_03_Controller)control).button.setText(Starter.cs.findCustomerById(list.get(i).getCustomerId()).getName()
								+ " " + list.get(i).getId());
						((But_03_Controller)control).ID = list.get(i).getId();

						controlSaleTicketAbs.add(control);
					}
				}
			}
			if(controlSaleTicketAbs.size() == 0){
				controlSaleTicketAbs = null;
			}

		}
		else if(vbox == vboxCheckAbs) {

			controlCheckAbs = new ArrayList<>();

			List<Draft> list = Starter.ds.getAllDraft();
			if(list != null){
				for(int i = 0; i < list.size(); i++) {

					if(list.get(i).getStatus().contains("待审核")){

						listMap.get(vbox).add(loadNode("/But_04_Check.fxml"));
						((But_04_Controller)control).button.setText(Starter.cs.findCustomerById(list.get(i).getCustomerId()).getName()
								+ " " + list.get(i).getId());
						((But_04_Controller)control).ID = list.get(i).getId();

						controlCheckAbs.add(control);
					}
				}
			}
			if(controlCheckAbs.size() == 0){
				controlCheckAbs = null;
			}

		}
		else if(vbox == vboxOrderAbs) {

			 controlOrderAbs = new ArrayList<>();

			List<Invoice> list = Starter.is.getAllInvoice();
			if(list != null){
				for(int i = 0; i < list.size(); i++) {
					listMap.get(vbox).add(loadNode("/But_01_Order.fxml"));
					((But_01_Controller)control).button.setText(Starter.cs.findCustomerById(list.get(i).getCustomerId()).getName()
							+ " " + list.get(i).getId());
					((But_01_Controller)control).ID = list.get(i).getId();

					controlOrderAbs.add(control);
				}
			}
			if(controlOrderAbs.size() == 0){
				controlOrderAbs = null;
			}
		}
		else if(vbox == vboxPaymentAbs) {

			controlPaymentAbs = new ArrayList<>();

			List<Invoice> list = Starter.is.getAllInvoice();
			if(list != null){
				for(int i = 0; i < list.size(); i++) {
					listMap.get(vbox).add(loadNode("/But_02_Payment.fxml"));
					((But_02_Controller)control).button.setText(Starter.cs.findCustomerById(list.get(i).getCustomerId()).getName()
							+ " " + list.get(i).getId());
					((But_02_Controller)control).ID = list.get(i).getId();

					controlPaymentAbs.add(control);
				}
			}
			if(controlPaymentAbs.size() == 0){
				controlPaymentAbs = null;
			}
		}
		else if(vbox == vboxPOS) {
			controlPOS = null;
		}
		else if(vbox == vboxUser) {
			List<User> list = Starter.us.getAllUser();

			controlUser = new ArrayList<>();

			if(list != null){
				for(int i = 0; i < list.size(); i++){

					User user = list.get(i);
					listMap.get(vbox).add(loadNode("/Info_09_User.fxml"));
					((Info_09_Controller)control).text0.setText(user.getName());
					((Info_09_Controller)control).text1.setText(user.getUsername());
					((Info_09_Controller)control).text2.setText(user.getGender());
					((Info_09_Controller)control).text3.setText(user.getPhoneNumber());
					((Info_09_Controller)control).ID = list.get(i).getId();

					controlUser.add(control);
				}
			}
		}
		else if(vbox == vboxAnalClient) {
			List<Customer> customerList = Starter.cs.getAllCustomer();

			controlAnalClient = new ArrayList<>();

			for(int i = 0; i < customerList.size(); i++){
				Customer customer = customerList.get(i);
				List<Invoice> list = Starter.is.findInvoiceByCustomerId(customer.getId());

				listMap.get(vbox).add(loadNode("/Info_10_AnalClient.fxml"));

				double price = 0;
				double profit = 0;
				String status = "无订单";

				if(list != null && list.size() != 0){
					status = "已结清";
					for(int j = 0; j < list.size(); j++){
						Invoice invoice = list.get(j);
						price += invoice.getTotalPrice().doubleValue();
						profit += invoice.getGrossMargin().doubleValue();
						if(invoice.getStatus().contains("未付款") ||
						   invoice.getStatus().contains("未结清")){
							status = "未结清";
						}
					}
				}

				((Info_10_Controller)control).text0.setText(customer.getName());
				((Info_10_Controller)control).text1.setText(String.valueOf(price));
				((Info_10_Controller)control).text2.setText(String.valueOf(profit));
				((Info_10_Controller)control).text3.setText(status);
				((Info_10_Controller)control).ID = customer.getId();

				controlAnalClient.add(control);
			}
		}
		else if(vbox == vboxAnalGood){
			List<Good> goodList = Starter.gs.getAllGood();

			controlAnalGood = new ArrayList<>();

			for(int i = 0; i < goodList.size(); i++){
				Good good = goodList.get(i);
				List<InvoiceLine> list = Starter.ils.findInvoiceLineByGoodId(good.getId());

				listMap.get(vbox).add(loadNode("/Info_11_AnalGood.fxml"));

				double price = 0;
				int num = 0;
				int refund = 0;

				if(list != null && list.size() != 0){
					for(int j = 0; j < list.size(); j++){
						InvoiceLine invoiceLine = list.get(j);
						num += invoiceLine.getAmount();
						price = invoiceLine.getUnitPrice().doubleValue() * invoiceLine.getAmount();

						Invoice invoice = Starter.is.findInvoiceById(invoiceLine.getInvoiceId());
						if(invoice.getStatus().contains("已退货")){
							refund += invoiceLine.getAmount();
						}
					}
				}

				((Info_11_Controller)control).text0.setText(good.getName());
				((Info_11_Controller)control).text1.setText(String.valueOf(price));
				((Info_11_Controller)control).text2.setText(String.valueOf(num));
				((Info_11_Controller)control).text3.setText(String.valueOf(refund));
				((Info_11_Controller)control).ID = good.getId();

				controlAnalGood.add(control);
			}
		}
		else if(vbox == vboxAnalUser){
			List<User> userList = Starter.us.getAllUser();

			controlAnalUser = new ArrayList<>();

			for(int i = 0; i < userList.size(); i++){
				User user = userList.get(i);
				List<Invoice> list = Starter.is.findInvoiceByUserId(user.getId());

				listMap.get(vbox).add(loadNode("/Info_12_AnalUser.fxml"));

				double price = 0;
				int clientNum = 0;
				int orderNum = 0;
				double weightedSum = 0;

				if(list != null && list.size() != 0){
					List<Customer> customerList = new ArrayList<>();
					for(int j = 0; j < list.size(); j++){
						Invoice invoice = list.get(j);
						Customer customer = Starter.cs.findCustomerById(invoice.getCustomerId());

						price += invoice.getTotalPrice().doubleValue();
						if(!customerList.contains(customer)){
							customerList.add(customer);
							clientNum++;
						}
						orderNum++;
					}
				}
				weightedSum = price * weight0 + clientNum * weight1 + orderNum * weight2;

				((Info_12_Controller)control).text0.setText(user.getName());
				((Info_12_Controller)control).text1.setText(String.valueOf(price));
				((Info_12_Controller)control).text2.setText(String.valueOf(clientNum));
				((Info_12_Controller)control).text3.setText(String.valueOf(orderNum));
				((Info_12_Controller)control).text4.setText(String.valueOf(weightedSum));
				((Info_12_Controller)control).ID = user.getId();

				controlAnalUser.add(control);
			}
		}
	}

	public void initSaleTicket(){	//初始化销售单界面

		if(controlCurrentSaleTicket != null){
			Draft draft = Starter.ds.findDraftById(controlCurrentSaleTicket.ID);
			textSaleTicket1.setText(draft.getStatus());
			cbbSaleTicket.getSelectionModel().select(Starter.cs.findCustomerById(draft.getCustomerId()).getName());
			calculateSaleTicket();
		}
		else{
			cbbSaleTicket.getSelectionModel().clearSelection();
			textSaleTicket2.setText("");
			textSaleTicket3.setText("");
			textSaleTicket1.setText("");

		}

		initListMap(vboxSaleTicket);
		initVbox(vboxSaleTicket);
		initListMap(vboxSaleTicketAbs);
		initVbox(vboxSaleTicketAbs);
	}

	public void initOrder(){	//初始化订单界面

		if(controlCurrentOrder != null){
			Invoice invoice = Starter.is.findInvoiceById(controlCurrentOrder.ID);

			textOrder0.setText(Starter.cs.findCustomerById(invoice.getCustomerId()).getName());
			textOrder1.setText(controlCurrentOrder.ID.toString());
			textOrder2.setText(Tools.formatTimestamp(invoice.getTime()));
			textOrder3.setText(Tools.formatBigDecimal(invoice.getTotalPrice()));
			textOrder4.setText(Starter.us.findUserById(invoice.getUserId()).getName());
			textOrder5.setText(invoice.getStatus());
			textOrder7.setText(Tools.formatBigDecimal(invoice.getGrossMargin()));
		}
		else{
			textOrder0.setText("");
			textOrder1.setText("");
			textOrder2.setText("");
			textOrder3.setText("");
			textOrder4.setText("");
			textOrder5.setText("");
			textOrder7.setText("");

			initListMap(vboxOrderAbs);
			initVbox(vboxOrderAbs);
		}
		initListMap(vboxOrder);
		initVbox(vboxOrder);
	}

	public void initCheck(){	//初始化订单界面

		if(controlCurrentCheck != null){
			Draft draft = Starter.ds.findDraftById(controlCurrentCheck.ID);
			textCheck0.setText(Starter.cs.findCustomerById(draft.getCustomerId()).getName());
			calculateCheck();
		}
		else{
			textCheck0.setText("");
			textCheck1.setText("");
			textCheck2.setText("");
		}

		initListMap(vboxCheck);
		initVbox(vboxCheck);
		initListMap(vboxCheckAbs);
		initVbox(vboxCheckAbs);

	}

	public void initPayment(){	//初始化支付界面

		if(controlCurrentPayment != null){
			Invoice invoice = Starter.is.findInvoiceById(controlCurrentPayment.ID);

			textPayment0.setText(Starter.cs.findCustomerById(invoice.getCustomerId()).getName());
			textPayment1.setText(controlCurrentPayment.ID.toString());
			textPayment2.setText(Tools.formatBigDecimal(invoice.getTotalPrice()));
			textPayment3.setText(invoice.getStatus());
		}
		else{
			textPayment0.setText("");
			textPayment1.setText("");
			textPayment2.setText("");
			textPayment3.setText("");

			initListMap(vboxPaymentAbs);
			initVbox(vboxPaymentAbs);
		}

		initListMap(vboxPayment);
		initVbox(vboxPayment);
	}

	public void initAnal(int mode){	//初始化统计界面

		LocalDate date = from.getValue();		//获取时间范围
		Timestamp timestamp0 = new Timestamp(date.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());
		date = to.getValue();
		Timestamp timestamp1 = new Timestamp(date.atStartOfDay(ZoneId.systemDefault()).plusDays(1).toInstant().toEpochMilli());

		List<Invoice> list =  Starter.is.findInvoiceByTime(timestamp0, timestamp1);

		double price = Double.valueOf(0);
		double profit = Double.valueOf(0);
		double unPay = Double.valueOf(0);
		int count = 0;
		if(list != null){
			for(int i = 0; i < list.size(); i++){
				price += list.get(i).getTotalPrice().doubleValue();
				profit += list.get(i).getGrossMargin().doubleValue();

				List<Payment> paymentList = Starter.ps.findPaymentByInvoiceId(list.get(i).getId());
				unPay += list.get(i).getTotalPrice().doubleValue();
				if(paymentList != null && paymentList.size() != 0){
					for(int j = 0; j < paymentList.size(); j++){
						unPay -= paymentList.get(j).getAmount().doubleValue();
					}
				}
				count++;
			}
		}

		int clientNum = Starter.cs.getAllCustomer().size();
		int userNum = Starter.us.getAllUser().size();

		double goodNum =  Double.valueOf(0);
		List<Inventory> inventoryList = Starter.its.getAllInventory();
		for(int i = 0; i < inventoryList.size(); i++){
			goodNum += Starter.gs.findGoodById(inventoryList.get(i).getGoodId()).getPurchasePrice().doubleValue() * inventoryList.get(i).getAmount();
		}

		textAnal0.setText(String.valueOf(price));
		textAnal1.setText(String.valueOf(profit));
		textAnal2.setText(String.valueOf(unPay));
		textAnal3.setText(String.valueOf(count));
		textAnal4.setText(String.valueOf(goodNum));
		textAnal5.setText(String.valueOf(clientNum));
		textAnal6.setText(String.valueOf(userNum));

		initChart(mode);
	}

	public void initPOS(){	//初始化POS
		cbbPOS.getSelectionModel().select("Default");

		timestamp = new Timestamp(System.currentTimeMillis());
		textPOS0.setText(Tools.formatTimestamp(timestamp));
		textPOS1.setText(Starter.us.findUserById(userID).getName());
		textPOS2.setText("");
		textPOS3.setText("");
		textPOS4.setText("");

		textPOS2.requestFocus();

	}

	public void setDisplaceSaleTicket() {		//根据权限设置销售单显示

		User user = Starter.us.findUserById(userID);
		String authority = user.getAuthority();
		if(authority.charAt(1) > '1'){
			paneSaleTicket.setDisable(false);
			butAddSaleTicket.setDisable(false);
		}
		else {
			paneSaleTicket.setDisable(true);
			butAddSaleTicket.setDisable(true);
		}
	}

	public void setDisplaceOrder(){		//根据权限设置订单显示
		User user = Starter.us.findUserById(userID);
		String authority = user.getAuthority();
		if(authority.charAt(1) > '1'){
			butOrder0.setDisable(false);
			butOrder1.setDisable(false);
			butOrder2.setDisable(false);
		}
		else {
			butOrder0.setDisable(true);
			butOrder1.setDisable(true);
			butOrder2.setDisable(true);
		}
	}

	public void setDisplacePayment() {		//根据权限设置付款显示
		User user = Starter.us.findUserById(userID);
		String authority = user.getAuthority();
		if(authority.charAt(2) > '1'){
			panePayment.setDisable(false);
		}
		else {
			panePayment.setDisable(true);
		}

	}

	public void setDisplaceStorage(){		//根据权限设置库存显示
		User user = Starter.us.findUserById(userID);
		String authority = user.getAuthority();
		if(authority.charAt(3) > '1'){
			butStorage0.setDisable(false);
			butStorage1.setDisable(false);
			butStorage2.setDisable(false);
			butAddStorage.setDisable(false);
		}
		else {
			butStorage0.setDisable(true);
			butStorage1.setDisable(true);
			butStorage2.setDisable(true);
			butAddStorage.setDisable(true);
		}
	}

	public void setDisplaceGood(){		//根据权限设置商品显示
		User user = Starter.us.findUserById(userID);
		String authority = user.getAuthority();
		if(authority.charAt(4) > '1'){
			butGood0.setDisable(false);
		}
		else {
			butGood0.setDisable(true);
		}
	}

	public void setDisplaceClient(){		//根据权限设置客户显示
		User user = Starter.us.findUserById(userID);
		String authority = user.getAuthority();
		if(authority.charAt(5) > '1'){
			butClient0.setDisable(false);
		}
		else {
			butClient0.setDisable(true);
		}
	}

	public void initListwarehouse(){		//更新仓库cbb
		List<Warehouse> list = Starter.ws.getAllWarehouse();
		listwarehouse.clear();
		for(int i = 0; i < list.size(); i++){
			listwarehouse.add(list.get(i).getName());
		}
	}

	public void initListclient(){		//更新销售单cbb
		List<Customer> list = Starter.cs.getAllCustomer();
		listclient.clear();
		for(int i = 0; i < list.size(); i++){
			listclient.add(list.get(i).getName());
		}
	}

	public void initListretail(){		//更新POScbb

		List<Customer> list = Starter.cs.getAllCustomer();
		listretail.clear();
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).getType().equals("零售") && !list.get(i).getName().equals("Default")){
				listretail.add(list.get(i).getName());
			}
		}
		listretail.add(0, "Default");
	}

	public void calculateSaleTicket(){	//计算利润和总价
		if(controlSaleTicket != null && controlCurrentSaleTicket != null){
			double count = 0;
			double cost = 0;
			for(int i = 0; i < controlSaleTicket.size(); i++){
				Info_03_Controller con = (Info_03_Controller)controlSaleTicket.get(i);

				count += Double.parseDouble(con.text0.getText()) *
						Integer.parseInt(con.text2.getText());

				cost += Starter.gs.findGoodByName(con.cbb.getSelectionModel().getSelectedItem()).get(0).getPurchasePrice().doubleValue() *
						Integer.parseInt(con.text2.getText());
			}

			textSaleTicket2.setText(String.valueOf(count));
			textSaleTicket3.setText(String.valueOf(count-cost));
		}
	}

	public void calculateCheck(){	//计算利润和总价
		if(controlCheck != null && controlCurrentCheck != null){
			double count = 0;
			double cost = 0;
			for(int i = 0; i < controlCheck.size(); i++){
				Info_04_Controller con = (Info_04_Controller)controlCheck.get(i);

				count += Double.parseDouble(con.text1.getText()) *
						Integer.parseInt(con.text3.getText());

				cost += Starter.gs.findGoodByName(con.text0.getText()).get(0).getPurchasePrice().doubleValue() *
						Integer.parseInt(con.text3.getText());
			}

			textCheck1.setText(String.valueOf(count));
			textCheck2.setText(String.valueOf(count-cost));
		}
	}

	public void calculatePOS(){	//计算利润和总价

		if(controlPOS == null)	return;

		double count = 0;
		double cost = 0;
		for(int i = 0; i < controlPOS.size(); i++){
			Info_08_Controller con = (Info_08_Controller)controlPOS.get(i);

			count += Double.parseDouble(con.text0.getText()) *
					Integer.parseInt(con.text2.getText());

			cost += Starter.gs.findGoodByName(con.cbb.getSelectionModel().getSelectedItem()).get(0).getPurchasePrice().doubleValue() *
					Integer.parseInt(con.text2.getText());
		}

		textPOS3.setText(String.valueOf(count));
		textPOS4.setText(String.valueOf(count-cost));
	}

	public void updateInvoiceState(Invoice invoice){	//更新invoice状态

		List<Payment> list = Starter.ps.findPaymentByInvoiceId(invoice.getId());

		boolean refund = false;
		if(invoice.getStatus().contains("已退货")){
			refund = true;
		}

		if (list == null){
			if(refund == true){
				invoice.setStatus("已退货,未付款");
			}
			else{
				invoice.setStatus("未付款");
			}
			Starter.is.updateInvoice(invoice);
			return;
		}

		Double count = Double.valueOf(0);
		for(int i = 0; i < list.size(); i++){
			count += list.get(i).getAmount().doubleValue();
		}
		if(count < invoice.getTotalPrice().doubleValue()){
			if(refund == true){
				invoice.setStatus("已退货,未结清");
			}
			else{
				invoice.setStatus("未结清");
			}
			Starter.is.updateInvoice(invoice);
		}
		else{
			if(refund == true){
				invoice.setStatus("已退货,已结清");
			}
			else{
				invoice.setStatus("已结清");
			}
			Starter.is.updateInvoice(invoice);
		}
	}

	public void logInventory(Inventory inventory, String info){		//为库存添加信息
		if(inventory.getInfo() == null || inventory.getInfo().isEmpty()){
			inventory.setInfo(info);
		}
		else{
			inventory.setInfo(info + "\n----------------\n" + inventory.getInfo());
		}
	}

	public void initChart(int mode){

		LocalDate date = from.getValue();		//获取时间范围
		Timestamp timestamp0 = new Timestamp(date.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());
		date = to.getValue();
		Timestamp timestamp1 = new Timestamp(date.atStartOfDay(ZoneId.systemDefault()).plusDays(1).toInstant().toEpochMilli());

		List<Invoice> list =  Starter.is.findInvoiceByTime(timestamp0, timestamp1);

		CategoryAxis x = new CategoryAxis();	//x轴
		NumberAxis y = new NumberAxis();    //y轴
		y.setLabel("销售额");

		ObservableList<PieChart.Data> pieList = FXCollections.observableArrayList();	//饼图数据
		Double price = Double.valueOf(0);	//最高前五销售额之和

		ObservableList<XYChart.Series<String, Number>> datas = FXCollections.observableArrayList();	//全部数据
		XYChart.Series<String, Number> xy = new XYChart.Series<String, Number>();	//一类数据
		xy.setName("销售额");

		ObservableList<XYChart.Data<String, Number>> dataList = FXCollections.observableArrayList();	//每类数据的单个xy取值列表
		if(mode == 1 && list != null){		//客户图表

			x.setLabel("客户");

			List<Integer> IDs = new ArrayList<>();
			List<Double> num = new ArrayList<>();

			for(int i = 0; i < list.size(); i++){
				Invoice invoice = list.get(i);
				Integer ID = invoice.getCustomerId();
				if(!IDs.contains(ID)){
					IDs.add(ID);
					num.add(Double.valueOf(0));
				}

				int index = IDs.indexOf(ID);
				num.set(index, num.get(index) + invoice.getTotalPrice().doubleValue());

				for(; index >= 1 && num.get(index-1) < num.get(index); index--){
					num.add(index+1, num.get(index-1));
					IDs.add(index+1, IDs.get(index-1));
					num.remove(index-1);
					IDs.remove(index-1);
				}
			}
			for(int i = 0; i < 5 && i < IDs.size(); i++){
				XYChart.Data<String, Number> data = new XYChart.Data<String, Number>(Starter.cs.findCustomerById(IDs.get(i)).getName(), num.get(i));
				dataList.add(data);

				pieList.add(new PieChart.Data(Starter.cs.findCustomerById(IDs.get(i)).getName(), num.get(i)));
				price += num.get(i);
			}
		}
		else if(mode == 2){		//商品图表

			x.setLabel("商品");

			List<Integer> IDs = new ArrayList<>();
			List<Double> num = new ArrayList<>();

			List<InvoiceLine> invoiceLineList = new ArrayList<>();
			for(int i = 0; i < list.size(); i++){
				if(Starter.ils.findInvoiceLineByInvoiceId(list.get(i).getId()) != null){
					invoiceLineList.addAll(Starter.ils.findInvoiceLineByInvoiceId(list.get(i).getId()));
				}
			}

			for(int i = 0; i < invoiceLineList.size(); i++){
				InvoiceLine invoiceLine = invoiceLineList.get(i);
				Integer ID = invoiceLine.getGoodId();
				if(!IDs.contains(ID)){
					IDs.add(ID);
					num.add(Double.valueOf(0));
				}

				int index = IDs.indexOf(ID);
				num.set(index, num.get(index) + invoiceLine.getUnitPrice().doubleValue() * invoiceLine.getAmount());

				for(; index >= 1 && num.get(index-1) < num.get(index); index--){
					num.add(index+1, num.get(index-1));
					IDs.add(index+1, IDs.get(index-1));
					num.remove(index-1);
					IDs.remove(index-1);
				}
			}
			for(int i = 0; i < 5 && i < IDs.size(); i++){
				XYChart.Data<String, Number> data = new XYChart.Data<String, Number>(Starter.gs.findGoodById(IDs.get(i)).getName(), num.get(i));
				dataList.add(data);

				pieList.add(new PieChart.Data(Starter.gs.findGoodById(IDs.get(i)).getName(), num.get(i)));
				price += num.get(i);
			}
		}
		else if(mode == 3){		//职员图表

			x.setLabel("职员");

			List<Integer> IDs = new ArrayList<>();
			List<Double> num = new ArrayList<>();

			for(int i = 0; i < list.size(); i++){
				Invoice invoice = list.get(i);
				Integer ID = invoice.getUserId();
				if(!IDs.contains(ID)){
					IDs.add(ID);
					num.add(Double.valueOf(0));
				}

				int index = IDs.indexOf(ID);
				num.set(index, num.get(index) + invoice.getTotalPrice().doubleValue());

				for(; index >= 1 && num.get(index-1) < num.get(index); index--){
					num.add(index+1, num.get(index-1));
					IDs.add(index+1, IDs.get(index-1));
					num.remove(index-1);
					IDs.remove(index-1);
				}
			}
			for(int i = 0; i < 5 && i < IDs.size(); i++){
				XYChart.Data<String, Number> data = new XYChart.Data<String, Number>(Starter.us.findUserById(IDs.get(i)).getName(), num.get(i));
				dataList.add(data);

				pieList.add(new PieChart.Data(Starter.us.findUserById(IDs.get(i)).getName(), num.get(i)));
				price += num.get(i);
			}
		}

		xy.setData(dataList);
		datas.add(xy);

		BarChart<String, Number> barChart = new BarChart<String, Number>(x, y, datas);
		barChart.setMaxSize(378.5, 270);
		barChart.setMinSize(378.5, 270);
		barChart.setAnimated(false);
		barChart.setLegendVisible(false);

		paneBarChart.getChildren().clear();
		paneBarChart.getChildren().add(barChart);

		if(list != null && list.size() != 0){
			Double sum = Double.valueOf(0);
			for(int i = 0; i < list.size(); i++){
				sum += list.get(i).getTotalPrice().doubleValue();
			}
			if(sum - price > 0){
				pieList.add(new PieChart.Data("其他",sum - price));
			}
		}
		else {
			pieList.clear();
			pieList.add(new PieChart.Data("无数据",1));
		}

		PieChart pieChart = new PieChart(pieList);
		pieChart.setMaxSize(378.5, 270);
		pieChart.setMinSize(378.5, 270);
		pieChart.setLegendVisible(false);

		panePieChart.getChildren().clear();
		panePieChart.getChildren().add(pieChart);

	}

	public void initAnalTime(){		//设置查询初始化时间
		from.setValue(LocalDate.now(ZoneId.systemDefault()).minusDays(3));
		to.setValue(LocalDate.now(ZoneId.systemDefault()));
	}
}

