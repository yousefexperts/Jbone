package cn.jbone.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil extends DateUtils {


	public final static String DATE_FORMAT = "yyyy-MM-dd";

    public final static String DATEPICKER_FORMAT = "MM/dd/yyyy";

    public final static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";


    public static Date getDate(String pattern){
        if(pattern == null){
            return null;
        }
        SimpleDateFormat dateFormat = null;
        if(pattern.indexOf("/") != -1){
            dateFormat = new SimpleDateFormat(DATEPICKER_FORMAT);
        }else if(pattern.indexOf(":") != -1){
            dateFormat = new SimpleDateFormat(TIME_FORMAT);
        }else{
            dateFormat = new SimpleDateFormat(DATE_FORMAT);
        }
        try {
            return dateFormat.parse(pattern);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     *
     * @param date
     * @param pattern
     * @return
     */

    public static String formateDate(Date date, String pattern) {
        if (null == date) {
            return StringUtils.EMPTY;
        }
        SimpleDateFormat dtFormatdB = null;
        try {
            dtFormatdB = new SimpleDateFormat(pattern);
            return dtFormatdB.format(date);
        } catch (Exception e) {
            dtFormatdB = new SimpleDateFormat(DATE_FORMAT);
            try {
                return dtFormatdB.format(date);
            } catch (Exception ex) {
            }
        }
        return null;
    }



}
