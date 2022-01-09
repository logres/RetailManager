package com.hit.logres.consumer.panel;

import com.hit.logres.api.entity.Customer;
import com.hit.logres.api.service.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Starter extends Application {

	private Stage primaryStage;
    private AnchorPane rootLayout;
    
    public static Map<String, Object> controllers = new HashMap<String, Object>();

    public static CustomerService cs;

    public static GoodService gs;

    public static InventoryService its;

    public static InvoiceService is;

    public static InvoiceLineService ils;

    public static PaymentService ps;

    public static PurchaseService pcs;

    public static UserService us;

    public static DraftService ds;

    public static DraftLineService dls;

    public static WarehouseService ws;

    public static double xOffset = 0;
    public static double yOffset = 0;


	@Override
	public void start(Stage primaryStage) {
		
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle(" ");

        initRootLayout();
	}

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Starter.class.getResource("/Window_13_LogIn.fxml"));
            rootLayout = (AnchorPane) loader.load();
            
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);

            //scene.getStylesheets().add(getClass().getResource("/GUICSS.css").toExternalForm());
            //rootLayout.getStylesheets().add(getClass().getResource("GUICSS.css").toExternalForm());
            //scene.getStylesheets().add("GUICSS.css");

            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.initStyle(StageStyle.TRANSPARENT);//设定窗口无边框
            primaryStage.show();

            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static void main(String[] args) {
        launch(args);
	}
}
