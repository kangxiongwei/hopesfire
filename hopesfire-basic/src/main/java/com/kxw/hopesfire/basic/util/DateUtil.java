package com.kxw.hopesfire.basic.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author kangxiongwei
 * @date 2020/8/4 3:49 下午
 */
public class DateUtil {

    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    private static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    private static final String DEFAULT_DATETIME_FORMAT = DEFAULT_DATE_FORMAT + " " + DEFAULT_TIME_FORMAT;
    private static final ZoneId DEFAULT_ZONE = ZoneId.of("GMT+8"); //北京时间，东八区

    /**
     * 字符串格式的日期转为Date类型
     *
     * @param str
     * @return
     */
    public static Date parseDate(String str) {
        if (str == null || "".equals(str)) return null;
        LocalDateTime ldt = LocalDateTime.parse(str, DateTimeFormatter.ofPattern(DEFAULT_DATETIME_FORMAT));
        Instant instant = ldt.atZone(DEFAULT_ZONE).toInstant();
        return Date.from(instant);
    }

    /**
     * 字符串格式的日期转为LocalDateTime类型
     *
     * @param str
     * @return
     */
    public static LocalDateTime parseLocalDateTime(String str) {
        if (str == null || "".equals(str)) return null;
        return LocalDateTime.parse(str, DateTimeFormatter.ofPattern(DEFAULT_DATETIME_FORMAT));
    }

    /**
     * 字符串格式的日期转为LocalDate类型
     *
     * @param str
     * @return
     */
    public static LocalDate parseLocalDate(String str) {
        if (str == null || "".equals(str)) return null;
        return LocalDate.parse(str, DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
    }

    /**
     * 日期转换
     *
     * @param ldt
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime ldt) {
        Instant instant = ldt.atZone(DEFAULT_ZONE).toInstant();
        return Date.from(instant);
    }

    /**
     * 日期转换
     *
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), DEFAULT_ZONE);
    }

    /**
     * 将日期去掉时分秒后转为字符串格式
     *
     * @return
     */
    public static String formatDate(Date date) {
        if (date == null) return null;
        LocalDate ld = LocalDateTime.ofInstant(date.toInstant(), DEFAULT_ZONE).toLocalDate();
        return formatLocalDate(ld);
    }

    /**
     * 格式化日期为yyyy-MM-dd格式
     *
     * @param ld
     * @return
     */
    public static String formatLocalDate(LocalDate ld) {
        if (ld == null) return null;
        return ld.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
    }

    /**
     * 将日期转为字符串格式
     *
     * @param date
     * @return
     */
    public static String formatDateTime(Date date) {
        if (date == null) return null;
        Instant instant = date.toInstant();
        LocalDateTime ldt = LocalDateTime.ofInstant(instant, DEFAULT_ZONE);
        return ldt.format(DateTimeFormatter.ofPattern(DEFAULT_DATETIME_FORMAT));
    }

    /**
     * 将日期转为字符传格式
     *
     * @param ldt
     * @return
     */
    public static String formatLocalDateTime(LocalDateTime ldt) {
        if (ldt == null) return null;
        return ldt.format(DateTimeFormatter.ofPattern(DEFAULT_DATETIME_FORMAT));
    }

    /**
     * 将日期的分钟转为整半小时
     *
     * @param ldt
     * @return
     */
    public static Date dateToHalfHour(LocalDateTime ldt) {
        LocalDateTime date = ldt.toLocalDate().atTime(ldt.getHour(), ldt.getMinute() < 30 ? 0 : 30);
        return localDateTimeToDate(date);
    }

    /**
     * 给指定日期增加相应的分钟数
     *
     * @param date
     * @param minutes
     * @return
     */
    public static Date plusMinutes(Date date, Integer minutes) {
        LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), DEFAULT_ZONE);
        return localDateTimeToDate(ldt.plusMinutes(minutes));
    }

    /**
     * 给指定日期往前推days天
     *
     * @param date
     * @param days
     * @return
     */
    public static String minusDays(String date, Integer days) {
        if (date == null || "".equals(date)) return null;
        LocalDateTime ldt = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(DEFAULT_DATETIME_FORMAT));
        LocalDateTime localDateTime = ldt.minusDays(days);
        return formatLocalDateTime(localDateTime);
    }

    /**
     * 向下取整分
     *
     * @param ldt
     * @return
     */
    public static Date floorMinutes(LocalDateTime ldt) {
        LocalDateTime ld = ldt.toLocalDate().atTime(ldt.getHour(), ldt.getMinute());
        return localDateTimeToDate(ld);
    }

    /**
     * 比较两个时间的大小，返回较小的那个日期
     *
     * @param d1
     * @param d2
     * @return
     */
    public static Date minDate(Date d1, Date d2) {
        return d1.getTime() < d2.getTime() ? d1 : d2;
    }

    /**
     * 比较两个时间的大小，返回较大的那个日期
     *
     * @param d1
     * @param d2
     * @return
     */
    public static Date maxDate(Date d1, Date d2) {
        return d1.getTime() > d2.getTime() ? d1 : d2;
    }

}
