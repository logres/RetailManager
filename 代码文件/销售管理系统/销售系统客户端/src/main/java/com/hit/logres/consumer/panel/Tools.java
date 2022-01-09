package com.hit.logres.consumer.panel;

import com.hit.logres.api.entity.Warehouse;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Pattern;

public class Tools {

	  public static boolean isInteger(String str) {		//是否为整数
	        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
	        return pattern.matcher(str).matches();  
	  }
	  
	  public static boolean isNumber(String str){		//是否为小数	
			String reg = "^[0-9]+(.[0-9]+)?$";
			return str.matches(reg);
	  }

	  public static String formatTimestamp(Timestamp timestamp){	//转换时间格式
		  //SimpleDateFormat dataFormat = new SimpleDateFormat("HH:mm, dd日");
		  SimpleDateFormat dataFormat = new SimpleDateFormat("YYYY/MM/dd, HH:mm");
		  return dataFormat.format(timestamp);
	  }

	  public static String formatBigDecimal(BigDecimal bigDecimal){		//转换BigDecimal格式
		  return String.valueOf(bigDecimal.floatValue());
	  }

	public static void setClientRankCbb(JFXComboBox<String> cbb){		//设置用户等级选择的cbb
		ObservableList<String> list = FXCollections.observableArrayList();
		list.add("零售");
		list.add("批发");
		list.add("其他");
		cbb.setItems(list);
	}

	public static void setGoodStatusCbb(JFXComboBox<String> cbb){		//设置商品状态选择的cbb
		ObservableList<String> list = FXCollections.observableArrayList();
		list.add("在售");
		list.add("其他");
		list.add("热销");
		cbb.setItems(list);
	}

	public static void setWarehouseCbb(JFXComboBox<String> cbb){		//设置仓库选择的cbb
		ObservableList<String> list = FXCollections.observableArrayList();

		List<Warehouse> warehouseList = Starter.ws.getAllWarehouse();

		if(warehouseList != null){
			for(int i = 0; i < warehouseList.size(); i++){
				list.add(warehouseList.get(i).getName());
			}
		}

		cbb.setItems(list);
	}
}
