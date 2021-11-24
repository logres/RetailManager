package com.hit.userinterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class Info4Controller {
	
	@FXML
	private AnchorPane pane;
	
	@FXML
    void butDeleteHandler(ActionEvent event) {
		((VBox)pane.getParent()).setPrefHeight(((VBox)pane.getParent()).getPrefHeight() - 30);
		((VBox)pane.getParent()).getChildren().remove(pane);
	}
	
}
