package com.hit.logres.consumer.panel;

import com.hit.logres.api.entity.User;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Window_13_Controller implements Initializable {

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Starter.controllers.put("Window_13_Controller", this);        //控制器实例访问
    }

    public int userID;  //传参，用户ID

    public Object control;  //传参，控制器

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    public Pane pane;

    @FXML
    public JFXTextField textUserID;

    @FXML
    public JFXPasswordField textUserPassword;

    @FXML
    void butLogInHandler(ActionEvent event) {	//登录事件

        List<User> list = Starter.us.findUserByName(textUserID.getText());

        if(list == null || list.size() == 0 || !list.get(0).getPassword().equals(textUserPassword.getText())){
            showLog("用户名或密码错误！");
            return;
        }

        userID = list.get(0).getId();

        User user = Starter.us.findUserById(userID);

        showNewWindow("/Main.fxml");

        showLog("登陆成功！欢迎您, " + user.getName() + "！");

        Main_Controller controller = (Main_Controller)Starter.controllers.get("Main_Controller");

        Stage stage =(Stage)pane.getScene().getWindow();
        stage.close();
        return;

    }

    @FXML
    void butRegisterHandler(ActionEvent event) {	//注册事件
        showNewWindow("/Window_12_Register.fxml");
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

        if(!filename.equals("/Main.fxml")){
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
        }

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

    @FXML
    void butCloseHandler(ActionEvent event) {   //关闭
        Stage stage =(Stage)pane.getScene().getWindow();
        stage.close();
    }
}
