package com.hit.userinterface;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable{

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	//初始化
		
		Starter.controllers.put("Controller", this);		//控制器实例访问
		
		tabPane.getSelectionModel().select(tab2); 			//初始Tab页
		
		setDisable11(true);
		setDisable21(true);
		setDisable41(true);
		
		tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {	//Tab事件监听
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {	
	
				if(newValue == tab0){
					init11();
					setDisable11(true);
				}
				else if(newValue == tab1){					
					init21();
					setDisable21(true);
				}
				else if(newValue == tab2){
					init31();
				}
				else if(newValue == tab3) {					
					init41();
					setDisable41(true);
				}
				else if(newValue == tab4) {
					init51();
					setDisable51(true);
				}
			}	
			
		});
		
		tabPane3.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {	//Tab事件监听
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {	
					init31();
			}		
		});
		
	}
	
	public List<Button> but0s = new ArrayList<Button>();	//已保存but0
	public List<Pane> Info1s = new ArrayList<>();		//已保存info1
	public List<Pane> Info2s = new ArrayList<Pane>();		//已保存info2
	
	public Button currentAbs0, currentAbs1, currentAbs2;
	
	@FXML
 	private TabPane tabPane, tabPane3;
	
	@FXML
 	private TextField text31Username, text32Username;
	
	@FXML
 	private Tab tab0, tab1, tab2, tab3, tab4;
	
	@FXML
 	private Button but11AddInfo, but11Edit, but11Save, but11Search, but11Refresh, 
 				   but21AddInfo,but21Edit, but21Save,  
 				   but31AddInfo, but31AddAbs, but31Delete, but31Submit, but31Save,
 				   but32Delete, but32Refund,
 				   but41AddAbs, but41Delete, but41Edit, but41Save, but41Show,
 				   but51AddInfo, but51Edit, but51Save;
 	
	@FXML
 	private VBox vbox11Info, 
 				 vbox21Info, 
 				 vbox31Info, vbox31Abs, 
 				 vbox32Info, vbox32Abs, 
 				 vbox41Info, vbox41Abs,
 				 vbox51Info;
	
	@FXML
    void but11AddInfoHandler(ActionEvent event) {
		try {
			
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Starter.class.getResource("Info2.fxml"));
	        AnchorPane info;
			info = (AnchorPane) loader.load();
			vbox11Info.setPrefHeight(vbox11Info.getPrefHeight() + 30);
			vbox11Info.getChildren().add(vbox11Info.getChildren().size() - 1, info);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@FXML
    void but11EditHandler(ActionEvent event) {
		setDisable11(false);
	}
	
	@FXML
    void but11SaveHandler(ActionEvent event) {
		setDisable11(true);
		
		Info2s = new ArrayList<Pane>();
		for(int i = 0; i < vbox11Info.getChildren().size()-1; i++) {
			Info2s.add((Pane)vbox11Info.getChildren().get(i));
		}
		
		try {
			Stage secondStage = new Stage();
			secondStage.setTitle("仓库更新情况 ");
			
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Starter.class.getResource("Window1.fxml"));
	        Pane rootLayout;
			rootLayout = (AnchorPane) loader.load();
	        Scene scene = new Scene(rootLayout);
	        scene.setOnKeyPressed(event1 -> secondStage.close());
	        	    	        
	        secondStage.setScene(scene);
	        secondStage.initModality(Modality.APPLICATION_MODAL);
	        secondStage.setMaxWidth(475);
	        secondStage.setMinWidth(475);
	        secondStage.setWidth(475);
	        secondStage.show();
	                
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}
	
	@FXML
    void but11SearchHandler(ActionEvent event) {
		int infoSize = vbox11Info.getChildren().size();
		for(int i = 0; i < infoSize-1; i++) {
			vbox11Info.getChildren().remove(0);
		}
		log("","查询结果："+" 0条信息。");
	}
	
	@FXML
    void but11RefreshHandler(ActionEvent event) {
		init11();
		setDisable11(true);
	}
	
	@FXML
    void but21EditHandler(ActionEvent event) {
		setDisable21(false);
	}

	@FXML
    void but21SaveHandler(ActionEvent event) {
		setDisable21(true);
		
		Info1s = new ArrayList<Pane>();
		for(int i = 0; i < vbox21Info.getChildren().size()-1; i++) {
			Info1s.add((Pane)vbox21Info.getChildren().get(i));
		}
		
		log("","保存成功！");
	}
	
	@FXML
    void but21AddInfoHandler(ActionEvent event) {
		try {
			
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Starter.class.getResource("Info1.fxml"));
	        AnchorPane info;
			info = (AnchorPane) loader.load();
			vbox21Info.setPrefHeight(vbox21Info.getPrefHeight() + 30);
			vbox21Info.getChildren().add(vbox21Info.getChildren().size() - 1, info);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@FXML
    void but31AddInfoHandler(ActionEvent event) {
		try {
			
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Starter.class.getResource("Info0.fxml"));
	        AnchorPane info;
			info = (AnchorPane) loader.load();
			
			vbox31Info.setPrefHeight(vbox31Info.getPrefHeight() + 30);
			vbox31Info.getChildren().add(vbox31Info.getChildren().size() - 1, info);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@FXML
	void but31DeleteHandler(ActionEvent event) {
		vbox31Abs.getChildren().remove(currentAbs0);
		vbox31Abs.setPrefHeight(vbox31Abs.getPrefHeight() - 30);
		if(but0s.contains(currentAbs0)) 	but0s.remove(currentAbs0);
		currentAbs0 = null;		
		//TODO 显示信息
		
		log("","删除成功！");
	}
	
	@FXML
	void but31AddAbsHandler(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Starter.class.getResource("But0.fxml"));
	        Button info;
			info = (Button) loader.load();
			vbox31Abs.setPrefHeight(vbox31Abs.getPrefHeight() + 30);
			vbox31Abs.getChildren().add(vbox31Abs.getChildren().size() - 1, info);
			
			currentAbs0 = info;
			//TODO 显示信息
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void but31SubmitHandler(ActionEvent event) {
		try {
			if(currentAbs0 != null) {
				FXMLLoader loader = new FXMLLoader();
		        loader.setLocation(Starter.class.getResource("But1.fxml"));
		        Button info;
				info = (Button) loader.load();
				info.setText(currentAbs0.getText());
				vbox32Abs.setPrefHeight(vbox32Abs.getPrefHeight() + 30);
				vbox32Abs.getChildren().add(0, info);
				
				loader = new FXMLLoader();
		        loader.setLocation(Starter.class.getResource("But2.fxml"));
				info = (Button) loader.load();
				info.setText(currentAbs0.getText());
				vbox41Abs.setPrefHeight(vbox41Abs.getPrefHeight() + 30);
				vbox41Abs.getChildren().add(0, info);
				
				vbox31Abs.getChildren().remove(currentAbs0);
				vbox31Abs.setPrefHeight(vbox31Abs.getPrefHeight() - 30);
				if(but0s.contains(currentAbs0)) 	but0s.remove(currentAbs0);
				currentAbs0 = null;
				//TODO 显示信息
				
				log("","提交成功！");
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	void but31SaveHandler(ActionEvent event) {
		if(!but0s.contains(currentAbs0)) 	but0s.add(currentAbs0);	
		currentAbs0.setText(text31Username.getText());

	}
	
	@FXML
	void but32DeleteHandler(ActionEvent event) {
		if(currentAbs1 != null){
			vbox32Abs.getChildren().remove(currentAbs1);
			vbox32Abs.setPrefHeight(vbox32Abs.getPrefHeight() - 30);
			
			for(int i = 0; i < vbox41Abs.getChildren().size() - 1; i++) {
				if(((Button)vbox41Abs.getChildren().get(i)).getText().equals(currentAbs1.getText())) {
					vbox41Abs.getChildren().remove(i);
					break;
				}
			}
			
			currentAbs1 = null;
			
			log("","删除成功！");
		}
	}
	
	@FXML
	void but32RefundHandler(ActionEvent event) {
		if(currentAbs1 != null) {
			vbox32Abs.getChildren().remove(currentAbs1);
			vbox32Abs.setPrefHeight(vbox32Abs.getPrefHeight() - 30);
			
			for(int i = 0; i < vbox41Abs.getChildren().size(); i++) {
				if(((Button)vbox41Abs.getChildren().get(i)).getText().equals(currentAbs1.getText())) {
					vbox41Abs.getChildren().remove(i);
					break;
				}
			}
			
			currentAbs1 = null;	
			
			log("","退货成功！");
		}
	}
	
	@FXML
	void but41SaveHandler(ActionEvent event) {
		setDisable41(true);
		
		log("","保存成功！");
	}
	
	@FXML
	void but41EditHandler(ActionEvent event) {
		setDisable41(false);
	}
	
	@FXML
	void but41DeleteHandler(ActionEvent event) {
		if(currentAbs2 != null) {
			vbox41Abs.getChildren().remove(currentAbs2);
			vbox41Abs.setPrefHeight(vbox41Abs.getPrefHeight() - 30);
			
			log("","删除成功！");
			//TODO 显示信息
		}
	}
	
	@FXML
    void but41AddInfoHandler(ActionEvent event) {
		try {
			
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Starter.class.getResource("Info3.fxml"));
	        AnchorPane info;
			info = (AnchorPane) loader.load();
			
			vbox41Info.setPrefHeight(vbox41Info.getPrefHeight() + 30);
			vbox41Info.getChildren().add(vbox41Info.getChildren().size() - 1, info);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@FXML
	void but41ShowHandler(ActionEvent event) {
		try {
			if(currentAbs2 != null) {
				Stage secondStage = new Stage();
				secondStage.setTitle("订单信息 " + currentAbs2.getText());
				
				FXMLLoader loader = new FXMLLoader();
		        loader.setLocation(Starter.class.getResource("Window0.fxml"));
		        Pane rootLayout;
				rootLayout = (AnchorPane) loader.load();
		        Scene scene = new Scene(rootLayout);
		        scene.setOnKeyPressed(event1 -> secondStage.close());
    	        
		        secondStage.initModality(Modality.APPLICATION_MODAL);		        
		        secondStage.setScene(scene);
		        secondStage.setMaxWidth(475);
		        secondStage.setMinWidth(475);
		        secondStage.show();
			}
	                
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
	}
	
	@FXML
	void but51SaveHandler(ActionEvent event) {
		setDisable51(true);
		
		log("","保存成功！");
	}
	
	@FXML
	void but51EditHandler(ActionEvent event) {
		setDisable51(false);
	}
	
	@FXML
    void but51AddInfoHandler(ActionEvent event) {
		try {
			
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Starter.class.getResource("Info4.fxml"));
	        AnchorPane info;
			info = (AnchorPane) loader.load();
			
			vbox51Info.setPrefHeight(vbox51Info.getPrefHeight() + 30);
			vbox51Info.getChildren().add(vbox51Info.getChildren().size() - 1, info);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	private void setDisable11(boolean disable) {	//控制11可用
		for(int i = 0; i < vbox11Info.getChildren().size()-1; i++) {
			
			GridPane pane =  (GridPane)((AnchorPane)vbox11Info.getChildren().get(i)).getChildren().get(0);
			for(int j = 0; j < pane.getChildren().size()-1; j++) {
				((TextField)pane.getChildren().get(j)).setEditable(!disable);
			}
			((Button)pane.getChildren().get(pane.getChildren().size()-1)).setDisable(disable);
		}
		((Button)vbox11Info.getChildren().get(vbox11Info.getChildren().size()-1)).setDisable(disable);
		
	}
	
	private void setDisable21(boolean disable) {	//控制21可用
		for(int i = 0; i < vbox21Info.getChildren().size()-1; i++) {
			
			GridPane pane =  (GridPane)((AnchorPane)vbox21Info.getChildren().get(i)).getChildren().get(0);
			for(int j = 0; j < pane.getChildren().size()-1; j++) {
				((TextField)pane.getChildren().get(j)).setEditable(!disable);
			}
			((Button)pane.getChildren().get(pane.getChildren().size()-1)).setDisable(disable);
		}
		((Button)vbox21Info.getChildren().get(vbox21Info.getChildren().size()-1)).setDisable(disable);
		
	}
	
	private void setDisable41(boolean disable) {	//控制41可用
		for(int i = 0; i < vbox41Info.getChildren().size()-1; i++) {
			
			GridPane pane =  (GridPane)((AnchorPane)vbox41Info.getChildren().get(i)).getChildren().get(0);
			for(int j = 0; j < pane.getChildren().size()-1; j++) {
				((TextField)pane.getChildren().get(j)).setEditable(!disable);
			}
			((Button)pane.getChildren().get(pane.getChildren().size()-1)).setDisable(disable);
		}
		((Button)vbox41Info.getChildren().get(vbox41Info.getChildren().size()-1)).setDisable(disable);
		
	}
	
	private void setDisable51(boolean disable) {	//控制51可用
		for(int i = 0; i < vbox51Info.getChildren().size()-1; i++) {
			
			GridPane pane =  (GridPane)((AnchorPane)vbox51Info.getChildren().get(i)).getChildren().get(0);
			
			((ComboBox<?>)pane.getChildren().get(0)).setDisable(disable);
			
			for(int j = 1; j < pane.getChildren().size()-1; j++) {
				((TextField)pane.getChildren().get(j)).setEditable(!disable);
			}
			((Button)pane.getChildren().get(pane.getChildren().size()-1)).setDisable(disable);
		}
		((Button)vbox51Info.getChildren().get(vbox51Info.getChildren().size()-1)).setDisable(disable);
		
	}
	
	private void init11() {	//初始化11
		int infoSize = vbox11Info.getChildren().size();
		for(int i = 0; i < infoSize-1; i++) {
			vbox11Info.getChildren().remove(0);
		}
		for(int i = 0; i < Info2s.size(); i++) {
			vbox11Info.getChildren().add(0, Info2s.get(i));
		}
	}
	
	private void init21() {	//初始化21
		int infoSize = vbox21Info.getChildren().size();
		for(int i = 0; i < infoSize-1; i++) {
			vbox21Info.getChildren().remove(0);
		}
		for(int i = 0; i < Info1s.size(); i++) {
			vbox21Info.getChildren().add(0, Info1s.get(i));
		}
	}
	
	private void init31() {	//初始化31
		int absSize = vbox31Abs.getChildren().size();
		int infoSize = vbox31Info.getChildren().size();
		for(int i = 0; i < absSize-1; i++) {
			vbox31Abs.getChildren().remove(0);
		}
		for(int i = 0; i < infoSize-1; i++) {
			vbox31Info.getChildren().remove(0);
		}
		for(int i = 0; i < but0s.size(); i++) {
			vbox31Abs.getChildren().add(0, but0s.get(i));
		}
		
		//TODO显示
		if(currentAbs0 != null) {
			text31Username.setText(currentAbs0.getText());
		}
		else {
			text31Username.setText("");
		}
		
	}
	
	private void init41() {	//初始化41
		int infoSize = vbox41Info.getChildren().size();
		for(int i = 0; i < infoSize-1; i++) {
			vbox41Info.getChildren().remove(0);
		}
	}
	
	private void init51() {	//初始化51
		int infoSize = vbox51Info.getChildren().size();
		for(int i = 0; i < infoSize-1; i++) {
			vbox51Info.getChildren().remove(0);
		}
	}
	
	private void log(String title, String info) {
		try {
			Stage secondStage = new Stage();
			secondStage.setTitle(title);
			
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Starter.class.getResource("WindowLog.fxml"));
	        Pane rootLayout;
			rootLayout = (AnchorPane) loader.load();
			
			((TextArea)((GridPane)rootLayout.getChildren().get(0)).getChildren().get(0)).setText(info);
			
	        Scene scene = new Scene(rootLayout);
	        scene.setOnKeyPressed(event -> secondStage.close());
	        
	        secondStage.setScene(scene);
	        secondStage.setResizable(false);
	        secondStage.initModality(Modality.APPLICATION_MODAL);	    	        
	        secondStage.show();
	        
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
