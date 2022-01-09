package com.hit.logres.consumer.panel;

import com.hit.logres.api.entity.Inventory;
import com.hit.logres.api.entity.User;
import com.hit.logres.api.entity.Warehouse;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Window_07_Controller implements Initializable {

    public List<Info_07_Controller> list = new ArrayList<Info_07_Controller>();     //表项控制器列表

    @FXML
    public Pane pane;

    @FXML
    public VBox vbox;

    @FXML
    public JFXComboBox<String> from, to;

    @FXML
    public JFXButton butDefault;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Main_Controller controller = (Main_Controller)Starter.controllers.get("Main_Controller");
        controller.control = this;

        User user = Starter.us.findUserById(controller.userID);

        controller.initListwarehouse();

        from.setItems(controller.listwarehouse);
        to.setItems(controller.listwarehouse);
        ComboBoxListener cbbListener1 = new ComboBoxListener<>(from);
        ComboBoxListener cbbListener2 = new ComboBoxListener<>(to);

        vbox.getChildren().remove(0);
        vbox.setPrefHeight(vbox.getPrefHeight() - 30);

    }

    @FXML
    public void butAddHandler(ActionEvent event){   //添加转库表项

        Main_Controller controller = (Main_Controller)Starter.controllers.get("Main_Controller");

        controller.control = this;

        Pane pane = (Pane)controller.loadNode("/Info_07_ExchangeStorage.fxml");
        list.add((Info_07_Controller)controller.control);
        ((Info_07_Controller)controller.control).control = this;

        vbox.getChildren().add(0, pane);
        vbox.setPrefHeight(vbox.getPrefHeight() + 30);
    }

    @FXML
    public void butSaveHandler(ActionEvent event){  //确认转库

        Main_Controller controller = (Main_Controller)Starter.controllers.get("Main_Controller");

        if(from.getSelectionModel().getSelectedItem() == null || from.getSelectionModel().getSelectedItem().isEmpty() ||
                to.getSelectionModel().getSelectedItem() == null || to.getSelectionModel().getSelectedItem().isEmpty()){

            controller.showLog("请选择仓库！");
            return;
        }

        Warehouse warehouse1 = Starter.ws.findWarehouseByName(from.getSelectionModel().getSelectedItem()).get(0);
        List<Inventory> inventoryList1 = Starter.its.findInventoryByWarehouseId(warehouse1.getId());

        Warehouse warehouse2 = Starter.ws.findWarehouseByName(to.getSelectionModel().getSelectedItem()).get(0);
        List<Inventory> inventoryList2 = Starter.its.findInventoryByWarehouseId(warehouse2.getId());

        for(int i = 0; i < list.size(); i++){   //输入检测
            if(list.get(i).text1.getText() == null || list.get(i).text1.getText().isEmpty() ||
                    list.get(i).text2.getText() == null || list.get(i).text2.getText().isEmpty() ||
                    !Tools.isNumber(list.get(i).text2.getText())){

                controller.showLog("输入错误！");
                return;
            }

            boolean find = false;
            for(int j = 0; j < inventoryList1.size(); j++){
                if(inventoryList1.get(j).getGoodId() == Integer.valueOf(list.get(i).text1.getText())){
                    find = true;
                    if(inventoryList1.get(j).getAmount() < Integer.valueOf(list.get(i).text2.getText())){

                        controller.showLog("库存量错误！");
                        return;
                    }
                }
            }
            if(find == false){
                controller.showLog("查无库存！");
                return;
            }
        }

        if(list.size() == 0){
            controller.showLog("请添加库存项！");
            return;
        }
        for(int i = 0; i < list.size(); i++){

            int index1 = -1;
            for(int j = 0; j < inventoryList1.size(); j++){
                if(Integer.valueOf(list.get(i).text1.getText()) == inventoryList1.get(j).getGoodId()){
                    index1 = j;
                    break;
                }
            }

            int index2 = -1;
            for(int j = 0; j < inventoryList2.size(); j++){
                if(Integer.valueOf(list.get(i).text1.getText()) == inventoryList2.get(j).getGoodId()){
                    index2 = j;
                    break;
                }
            }

            if(index2 != -1){        //已有该库存

                Inventory in = inventoryList2.get(index2);
                in.setAmount(in.getAmount() + Integer.valueOf(list.get(i).text2.getText()));
                controller.logInventory(in, "转入: " + Integer.valueOf(list.get(i).text2.getText()) + " 件。");
                Starter.its.updateInventory(in);

            }
            else{
                Inventory in = new Inventory(
                        0,
                        warehouse2.getId(),
                        Integer.valueOf(list.get(i).text1.getText()),
                        Integer.valueOf(list.get(i).text2.getText()),
                        "转入: " + Integer.valueOf(list.get(i).text2.getText()) + " 件。"
                );
                Starter.its.insertInventory(in);

            }

            Inventory in = inventoryList1.get(index1);
            if(in.getAmount() == Integer.valueOf(list.get(i).text2.getText())){
                Starter.its.deleteInventory(in.getId());
            }
            else{
                in.setAmount(in.getAmount() - Integer.valueOf(list.get(i).text2.getText()));
                controller.logInventory(in, "转出: " + Integer.valueOf(list.get(i).text2.getText()) + " 件。");
                Starter.its.updateInventory(in);
            }


            controller.initListMap(controller.vboxStorage);
            controller.initVbox(controller.vboxStorage);
            controller.showLog("仓库调度成功！");

            Stage stage =(Stage)pane.getScene().getWindow();
            stage.close();

        }

    }

    @FXML
    void butCloseHandler(ActionEvent event) {	//关闭窗口
        Stage stage =(Stage)pane.getScene().getWindow();
        stage.close();
    }

}
