package io.github.whywhathow.books;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.util.Date;

public class DateTest {


    public static void main(String[] args) {
//        add30Days();
        Date date = new Date();
        Date date1 = add30Days(date);
        System.out.println(date.toString() + " " + date1.toString());
        if (date.getTime() - date1.getTime() > 0) {
            System.out.println("date win");
        } else {
            System.out.println("date1 win");
        }
        System.out.println(getOverTime(date, date1));
    }

    protected static Date add30Days(Date date1) {
        Date date2 = new Date();
        long after = date1.getTime() / 1000 + 30 * 24 * 60 * 60;
        date2.setTime(after * 1000);
        System.out.println(date2.toString());
        return date2;
    }

    protected static boolean checkOverTime(Date date, Date date1) {
        return date.getTime() - date1.getTime() > 0;
    }

    protected static long getOverTime(Date date, Date date1) {
        return (date1.getTime() - date.getTime()) / 3600000 / 24 + 1;
    }

}
