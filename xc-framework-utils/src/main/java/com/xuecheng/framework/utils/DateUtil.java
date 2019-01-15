package com.xuecheng.framework.utils;

import com.sun.istack.internal.Nullable;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;

public class DateUtil {
    private static final Logger logger = Logger.getLogger(DateUtil.class);
    public static final String DATE = "yyyy-MM-dd";
    public static final String DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String YEAR = "yyyy";
    public static final String US_DATE = "MMM dd, yyyy";
    public static final String US_DATETIME = "MMM dd, yyyy HH:mm:ss";
    public static final String DATETIMECOMPACT = "yyyyMMddHHmmss";
    private static final Tuple2<Pattern, String>[] SIMPLE_DATE_FORMATS = new Tuple2[]{Tuples.of(Pattern.compile("^\\d{4} \\d{2} \\d{2} \\d{2} \\d{2} \\d{2}$"), "yyyy MM dd HH mm ss"), Tuples.of(Pattern.compile("^\\d{4} \\d{2} \\d{2}$"), "yyyy MM dd"), Tuples.of(Pattern.compile("^\\d{2} \\d{2} \\d{4} \\d{2} \\d{2} \\d{2}$"), "MM dd yyyy HH mm ss"), Tuples.of(Pattern.compile("^\\d{2} \\d{2} \\d{4}$"), "MM dd yyyy"), Tuples.of(Pattern.compile("^\\d{4}$"), "yyyy")};
    private static final Tuple2<Pattern, String>[] LOCALE_DATE_FORMATS = new Tuple2[]{Tuples.of(Pattern.compile(".*\\d{2} \\d{4} \\d{2} \\d{2} \\d{2}$"), "MMMM dd yyyy HH mm ss"), Tuples.of(Pattern.compile(".*\\d{2} \\d{4} \\d{2} \\d{2} \\d{2}$"), "MMM dd yyyy HH mm ss"), Tuples.of(Pattern.compile(".*\\d{2} \\d{4}$"), "MMMM dd yyyy"), Tuples.of(Pattern.compile(".*\\d{2} \\d{4}$"), "MMM dd yyyy")};

    public DateUtil() {
    }

    @Nullable
    public static Date parse(@Nullable Object obj) {
        if(obj == null) {
            return null;
        } else if(obj instanceof String) {
            return parse((String)obj);
        } else if(obj instanceof Date) {
            return (Date)obj;
        } else if(obj instanceof Number) {
            Number n = (Number)obj;
            return new Date(n.longValue());
        } else {
            logger.warn("unsupported date type: " + obj.getClass());
            return null;
        }
    }

    @Nullable
    public static Date parse(@Nullable String str) {
        if(str == null) {
            return null;
        } else {
            str = str.replaceAll("[:\\- ,\\.]+", " ").trim();
            if(str.isEmpty()) {
                return null;
            } else {
                ParsePosition pos = new ParsePosition(0);
                Tuple2[] var2 = SIMPLE_DATE_FORMATS;
                int var3 = var2.length;

                int var4;
                for(var4 = 0; var4 < var3; ++var4) {
                    Tuple2<Pattern, String> fmt = var2[var4];
                    if(str.length() == ((String)fmt._1).length() && ((Pattern)fmt._0).matcher(str).matches()) {
                        Date d = (new SimpleDateFormat((String)fmt._1)).parse(str, pos);
                        if(d != null) {
                            return d;
                        }

                        pos.setIndex(0);
                    }
                }

                Locale[] locales = Locale.getAvailableLocales();
                Tuple2[] var14 = LOCALE_DATE_FORMATS;
                var4 = var14.length;

                for(int var15 = 0; var15 < var4; ++var15) {
                    Tuple2<Pattern, String> fmt = var14[var15];
                    if(((Pattern)fmt._0).matcher(str).matches()) {
                        SimpleDateFormat parser = new SimpleDateFormat((String)fmt._1);
                        Locale[] var8 = locales;
                        int var9 = locales.length;

                        for(int var10 = 0; var10 < var9; ++var10) {
                            Locale locale = var8[var10];
                            parser.setDateFormatSymbols(DateFormatSymbols.getInstance(locale));
                            Date d = parser.parse(str, pos);
                            if(d != null) {
                                return d;
                            }

                            pos.setIndex(0);
                        }
                    }
                }

                logger.warn("unknown date format: " + str);
                return null;
            }
        }
    }

    public static String format(Object date) {
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String format(Object date, String pattern) {
        return format(date, pattern, Locale.getDefault());
    }

    public static String format(@Nullable Object date, String pattern, Locale local) {
        Date d = parse(date);
        return d == null?"":(new SimpleDateFormat(pattern, local)).format(d);
    }

    public static String getPrevOrNextNDay(int offset, @Nullable String pattern) {
        if(pattern == null) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }

        Calendar now = Calendar.getInstance();
        now.add(5, offset);
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(now.getTime());
    }

    public static Date getPrevOrNextNDayDate(int offset, String pattern) {
        String formattedDate = null;
        long var3 = 0L;

        try {
            Calendar now = Calendar.getInstance();
            now.add(5, offset);
            now.set(11, 0);
            now.set(12, 0);
            now.set(13, 0);
            return now.getTime();
        } catch (Exception var6) {
            logger.error("getPrevOrNextNDayDate failed, pattern is null!", var6);
            return null;
        }
    }

    public static String now() {
        return format(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    public static String today() {
        return format(new Date(), "yyyy-MM-dd");
    }

    public static String thisYear() {
        return format(new Date(), "yyyy");
    }

    public static String getDayInWeekName() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(7);
        if(calendar.getFirstDayOfWeek() == 1) {
            --dayOfWeek;
            if(dayOfWeek == 0) {
                dayOfWeek = 7;
            }
        }

        return StringUtil.toUpperCaseFirstOne(DateUtil.DAY_IN_WEEK_ENUM.values()[dayOfWeek - 1].name().toLowerCase());
    }

    public static Date getZoneTime(int zone) {
        TimeZone defaultTimeZone;
        if(zone > 0) {
            defaultTimeZone = TimeZone.getTimeZone("GMT+" + zone);
        } else {
            defaultTimeZone = TimeZone.getTimeZone("GMT-" + Math.abs(zone));
        }

        TimeZone.setDefault(defaultTimeZone);
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    public static String localTime(int zone, String simpleDateFormat) {
        SimpleDateFormat format;
        if(simpleDateFormat != null && !"".equals(simpleDateFormat)) {
            format = new SimpleDateFormat(simpleDateFormat);
        } else {
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }

        Date date = getZoneTime(zone);
        new String();

        String dateStr;
        try {
            dateStr = format.format(date);
        } catch (Exception var6) {
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateStr = format.format(date);
        }

        return dateStr;
    }

    public static XMLGregorianCalendar toXmlDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        DatatypeFactory dtFactory = null;

        try {
            dtFactory = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException var4) {
            logger.error("create DatatypeFactory instance error!", var4);
            return null;
        }

        XMLGregorianCalendar xmlDate = dtFactory.newXMLGregorianCalendar();
        xmlDate.setYear(cal.get(1));
        xmlDate.setMonth(cal.get(2) + 1);
        xmlDate.setDay(cal.get(5));
        xmlDate.setHour(cal.get(11));
        xmlDate.setMinute(cal.get(12));
        xmlDate.setSecond(cal.get(13));
        return xmlDate;
    }

    public static Timestamp toTimestampDate(Date date) {
        return new Timestamp(date.getTime());
    }

    public static Date toDate(Timestamp ts) {
        return ts == null?null:ts;
    }

    /** @deprecated */
    @Deprecated
    public static Date addTime(Object date, int schedule, int timeType) {
        Date d = parse(date);
        if(d == null) {
            return null;
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(d);
            switch(timeType) {
            case 0:
                calendar.add(10, schedule);
                break;
            case 1:
                calendar.add(5, schedule);
                break;
            case 2:
                calendar.add(5, schedule * 7);
                break;
            case 3:
                calendar.add(2, schedule);
                break;
            case 4:
                calendar.add(2, schedule * 3);
                break;
            case 5:
                calendar.add(1, schedule);
                break;
            case 6:
                calendar.add(12, schedule);
                break;
            case 7:
                calendar.add(13, schedule);
            }

            return calendar.getTime();
        }
    }

    public static Date calculateTime(Object date, int schedule, int timeType) {
        Date d = parse(date);
        if(d == null) {
            return null;
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(d);
            if(timeType == TimeUnitEnum.HOUR.getCode()) {
                calendar.add(10, schedule);
            } else if(timeType == TimeUnitEnum.DAY.getCode()) {
                calendar.add(5, schedule);
            } else if(timeType == TimeUnitEnum.WEEK.getCode()) {
                calendar.add(5, schedule * 7);
            } else if(timeType == TimeUnitEnum.MONTH.getCode()) {
                calendar.add(2, schedule);
            } else if(timeType == TimeUnitEnum.QUARTER.getCode()) {
                calendar.add(2, schedule * 3);
            } else if(timeType == TimeUnitEnum.YEAR.getCode()) {
                calendar.add(1, schedule);
            } else if(timeType == TimeUnitEnum.MINUTE.getCode()) {
                calendar.add(12, schedule);
            } else if(timeType == TimeUnitEnum.SECOND.getCode()) {
                calendar.add(13, schedule);
            }

            return calendar.getTime();
        }
    }

    public static long compileDate(Object date1, Object date2) {
        Date d1 = parse(format(parse(date1), "yyyy-MM-dd"));
        Date d2 = parse(format(parse(date2), "yyyy-MM-dd"));
        return d1.getTime() - d2.getTime();
    }

    public static long compileDateTime(Object date1, Object date2) {
        Date d1 = parse(format(parse(date1), "yyyy-MM-dd HH:mm:ss"));
        Date d2 = parse(format(parse(date2), "yyyy-MM-dd HH:mm:ss"));
        return d1.getTime() - d2.getTime();
    }

    public static boolean containDate(Object date1, Object date2, int dayBefore, int dayAfter) {
        Date d1 = parse(format(parse(date1), "yyyy-MM-dd"));
        Date d2 = parse(format(parse(date2), "yyyy-MM-dd"));
        return addTime(d1, -dayBefore, 1).getTime() - d2.getTime() <= 0L && addTime(d1, dayAfter, 1).getTime() - d2.getTime() >= 0L;
    }

    public static String substract(String date_1, String date_2) {
        if(!StringUtils.isEmpty(date_1) && !StringUtils.isEmpty(date_2)) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            try {
                Date d1 = df.parse(date_1);
                Date d2 = df.parse(date_2);
                return substract(d1, d2);
            } catch (ParseException var5) {
                logger.error("format date string failed!");
                var5.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public static Integer compare(String date_1, String date_2) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date d1 = df.parse(date_1);
            Date d2 = df.parse(date_2);
            long delta = d1.getTime() - d2.getTime();
            return delta > 0L?Integer.valueOf(1):(delta == 0L?Integer.valueOf(0):Integer.valueOf(-1));
        } catch (ParseException var7) {
            logger.error("format date string failed!");
            var7.printStackTrace();
            return Integer.valueOf(9);
        }
    }

    public static String substract(Date date_1, Date date_2) {
        if(date_1 != null && date_2 != null) {
            long delta = Math.abs(date_1.getTime() - date_2.getTime());
            long delta_days = delta / 86400000L;
            long delta_hours = (delta - delta_days * 86400000L) / 3600000L;
            long delta_minutes = (delta - delta_days * 86400000L - delta_hours * 3600000L) / 60000L;
            long delta_seconds = (delta - delta_days * 86400000L - delta_hours * 3600000L - delta_minutes * 60000L) / 1000L;
            long delta_milliseconds = (delta - delta_days * 86400000L - delta_hours * 3600000L - delta_minutes * 60000L - delta_seconds * 1000L) / 1L;
            return delta_days + "天 " + delta_hours + "小时 " + delta_minutes + "分 " + delta_seconds + "秒 " + delta_milliseconds + "毫秒";
        } else {
            return null;
        }
    }

    public static String substractMinutes(String date_1, String date_2) {
        if(!StringUtils.isEmpty(date_1) && !StringUtils.isEmpty(date_2)) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            try {
                Date d1 = df.parse(date_1);
                Date d2 = df.parse(date_2);
                long delta = Math.abs(d1.getTime() - d2.getTime());
                return delta < 1800000L?"false":"true";
            } catch (ParseException var7) {
                logger.error("format date string failed!");
                var7.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public static String date2View(String time) {
        if(StringUtil.isNullorEmpty(time)) {
            return time;
        } else {
            String ff = time;
            int i = time.indexOf("Z");
            if(i != -1) {
                time = time.replace("Z", " UTC");
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");

                try {
                    Date f = format.parse(time);
                    ff = format(f, "yyyy-MM-dd HH:mm:ss");
                } catch (ParseException var5) {
                    var5.printStackTrace();
                }
            }

            return ff;
        }
    }

    public static String nowCompat() {
        return format(new Date(), "yyyyMMddHHmmss");
    }

    public static String beforeStartDate() {
        Date now = new Date();
        new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(5, -1);
        Date before = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String beforeStartDate = sdf.format(before);
        beforeStartDate = beforeStartDate + " 00:00:00";
        return beforeStartDate;
    }

    public static String beforeEndDate() {
        Date now = new Date();
        new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(5, -1);
        Date before = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String beforeEndDate = sdf.format(before);
        beforeEndDate = beforeEndDate.substring(0, 10) + " 23:59:59";
        return beforeEndDate;
    }

    public static String getZeroTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        return format(cal.getTime(), "yyyy-MM-dd HH:mm:ss");
    }

    public static String getLastTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(11, 23);
        cal.set(12, 59);
        cal.set(13, 59);
        cal.set(14, 59);
        return format(cal.getTime(), "yyyy-MM-dd HH:mm:ss");
    }

    public static boolean isIllegalDateTime(String year, Integer month, Integer day, String time) {
        if(year != null && month != null && day != null) {
            String _triggerMonth = month.intValue() < 10?"0" + month:month + "";
            String _triggerDay = day.intValue() < 10?"0" + day:day + "";
            String _time = StringUtil.isNullorEmpty(time)?"00:00:00":time;
            String dateStr = year + "-" + _triggerMonth + "-" + _triggerDay + " " + _time;
            String dateFormatStr = format(parse(dateStr));
            return dateFormatStr == null?true:!dateStr.equals(dateFormatStr);
        } else {
            return true;
        }
    }

    public static boolean isIllegalTime(String time) {
        if(StringUtil.isNullorEmpty(time)) {
            return true;
        } else {
            String todayTime = today() + " " + time;
            String dateFormatStr = format(parse(todayTime));
            return dateFormatStr == null?true:!todayTime.equals(dateFormatStr);
        }
    }

    public static String getFormatMonth(String month) {
        if(StringUtils.isEmpty(month)) {
            return "null";
        } else {
            String m = month + "";
            int mSize = m.length();
            if(mSize > 1) {
                return m;
            } else {
                m = "0" + m;
                return m;
            }
        }
    }

    public static String getFormatDay(String day) {
        if(StringUtils.isEmpty(day)) {
            return "null";
        } else {
            String d = day + "";
            int dSize = d.length();
            if(dSize > 1) {
                return d;
            } else {
                d = "0" + d;
                return d;
            }
        }
    }

    public static int getActualMaxDayInMonth(int year, int month) {
        if(year > 0 && month >= 0 && month <= 11) {
            Calendar minimumCalendar = Calendar.getInstance();
            minimumCalendar.set(1, year);
            minimumCalendar.set(2, month);
            System.out.println(format(minimumCalendar.getTime()));
            int max = minimumCalendar.getActualMaximum(5);
            return max;
        } else {
            return -1;
        }
    }

    public static boolean isDayExist(int year, int month, int day) {
        if(year <= 0) {
            return false;
        } else if(month >= 1 && month <= 12) {
            if(day >= 1 && day <= 31) {
                int maxDays = getActualMaxDayInMonth(year, month - 1);
                return day <= maxDays;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean isDayValid(int year, int month, int day) {
        if(year >= 1 && year <= 9999 && month >= 1 && month <= 12 && day >= 1 && day <= 31) {
            StringBuilder sb = new StringBuilder();
            if((year + "").length() < 4) {
                for(int i = 0; i < 4 - (year + "").length(); ++i) {
                    sb.append("0");
                }
            }

            sb.append(year).append("-");
            if((month + "").length() < 2) {
                sb.append("0");
            }

            sb.append(month).append("-");
            if((day + "").length() < 2) {
                sb.append("0");
            }

            sb.append(day);
            return format(sb.toString()).startsWith(sb.toString());
        } else {
            return false;
        }
    }

    public static String getFormatMonthDay(String month, String day) {
        if(!StringUtils.isEmpty(month) && !StringUtils.isEmpty(day)) {
            String M = getFormatMonth(month);
            String D = getFormatMonth(day);
            return M + "-" + D;
        } else {
            return "null";
        }
    }

    public static String getCronDate(String t) {
        if(StringUtils.isEmpty(t)) {
            return "null";
        } else {
            String[] strs = t.trim().split(":");
            String tmp = "0";
            String[] var3 = strs;
            int var4 = strs.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String s = var3[var5];
                if(Integer.parseInt(s.substring(0, 1)) > 0) {
                    tmp = tmp + " ";
                    tmp = tmp + s;
                } else {
                    tmp = tmp + " ";
                    tmp = tmp + s.substring(1, 2);
                }
            }

            tmp = tmp + " * * ?";
            return tmp;
        }
    }

    public static boolean containTime(Object date1, Object date2, Object targetTime) {
        boolean t1 = comTime(date1, targetTime);
        boolean t2 = comTime(date2, targetTime);
        return !t1 && t2;
    }

    public static boolean comTime(Object date1, Object date2) {
        Date d1 = parse(date1);
        Date d2 = parse(date2);
        long t = d1.getTime() - d2.getTime();
        return t >= 0L;
    }

    public static String addYear(String originDate, int n) {
        Date originYearDate = parse(originDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(originYearDate);
        cal.add(1, n);
        Date nextDate = cal.getTime();
        return format(nextDate, "yyyy");
    }

    public static void main(String[] args) throws IOException {
        System.out.println(addYear(now(), 3));
        System.out.println(parse("2018"));
    }

    public static enum DAY_IN_WEEK_ENUM {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY;

        private DAY_IN_WEEK_ENUM() {
        }
    }
}
