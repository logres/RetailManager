package com.example.market;

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
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy/MM/dd, HH:mm");
        return dataFormat.format(timestamp);
    }

    public static String formatBigDecimal(BigDecimal bigDecimal){		//转换BigDecimal格式
        return String.valueOf(bigDecimal.floatValue());
    }

}
