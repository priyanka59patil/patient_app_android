package com.werq.patient.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateHelper {

    public static String  dateFormatmmddyyyy(Date date) throws ParseException {

        String outputPattern = "MMMM dd',' yyyy";
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        //Date date1=outputFormat.parse(dateString);
        String cdate = outputFormat.format(date);
        return cdate;
    }
}
