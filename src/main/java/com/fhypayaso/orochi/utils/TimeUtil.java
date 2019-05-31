package com.fhypayaso.orochi.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {


    private TimeUtil() {

    }


    public static String stampToDate(long s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(s);
        res = simpleDateFormat.format(date);
        return res;
    }


}
