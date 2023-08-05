package com.wangfuzeng.common.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateUtil {
    public static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
    public static DateTimeFormatter dateFormatter2 = DateTimeFormatter.ofPattern("yyyy年MM月");
    public static DateTimeFormatter dateFormatter3= DateTimeFormatter.ofPattern("yyyy-MM");
    public static DateTimeFormatter dateFormatter4= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String parseLocalDate(LocalDate date) {
        return date.format(dateFormatter);
    }
    /**
     * 转字符串
     * @param localDate
     * @return
     */
    public static String toString(LocalDate localDate){
        return dateFormatter.format(localDate);
    }


    public static String toString2(LocalDate localDate){
        return dateFormatter2.format(localDate);
    }

    /*
     * LocalDate 转 字符串
     * */
    public  static String toString3(LocalDate localDate){
        return  dateFormatter3.format(localDate);
    }
    /*
     * LocalDate 转 字符串
     * */
    public  static String toString4(LocalDate localDate){
        return  dateFormatter4.format(localDate);
    }

}
