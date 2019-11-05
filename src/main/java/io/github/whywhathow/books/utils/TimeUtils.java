package io.github.whywhathow.books.utils;

import java.util.Date;

public class TimeUtils {

    // 设置还书日期
    public static Date add30Days(Date date1) {
        Date date2 = new Date();
        long after = date1.getTime() / 1000 + 30 * 24 * 60 * 60;
        date2.setTime(after * 1000);
        System.out.println(date2.toString());
        return date2;
    }

    // 判断是否超期
    public static boolean checkOverTime(Date date, Date date1) {
        return date.getTime() - date1.getTime() > 0;
    }

    // 获取超期天数
    public static int getOverTime(Date date, Date date1) {
        int res = (int) ((date1.getTime() - date.getTime()) / 3600000 / 24 + 1);
        return Math.abs(res);
    }
}

