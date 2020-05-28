package com.sss.as.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class NiceASUtil {
    public static String getDateTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        return sdf.format(new Date());
    }
}
