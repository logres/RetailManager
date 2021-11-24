package com.hit.userinterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class But0Controller{
	
	@FXML
    void butHandler(ActionEvent event) {	
		Controller con = (Controller)Starter.controllers.get("Controller");
		con.currentAbs0 = ((Button)event.getSource());
	}
}
