package top.zbeboy.dms.service.util;

import org.apache.commons.lang3.StringUtils;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
    /**
     * timestamp
     *
     * @param timestamp java.sql.timestamp
     * @return java.util.date
     */
    public static java.util.Date sqlTimestampToUtilDate(java.sql.Timestamp timestamp) {
        return new java.util.Date(timestamp.getTime());
    }

    /**
     * timestamp to string
     *
     * @param timestamp sql
     * @param format    格式
     * @return string
     */
    public static String formatSqlTimestamp(java.sql.Timestamp timestamp, String format) {
        return timestamp.toLocalDateTime().format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * util date to sql date
     *
     * @param date util date
     * @return sql date
     */
    public static java.sql.Date utilDateToSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    /**
     * 格式化date
     *
     * @param date   日期
     * @param format 格式
     * @return 格式化后的时间
     */
    public static String formatUtilDate(java.util.Date date, String format) {
        return date.toInstant().atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 格式化date
     *
     * @param date   日期
     * @param format 格式
     * @return 格式化后的时间
     */
    public static String formatSqlDate(java.sql.Date date, String format) {
        return date.toLocalDate().format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 格式化date
     *
     * @param date 日期
     * @return 格式化后的时间
     */
    public static String defaultFormatUtilDate(java.util.Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 格式化Timestamp
     *
     * @param timestamp 日期
     * @return 格式化后的时间
     */
    public static String defaultFormatSqlTimestamp(java.sql.Timestamp timestamp) {
        return timestamp.toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 格式化成sql date
     *
     * @param date 日期
     * @return sql date
     */
    public static java.sql.Date defaultParseSqlDate(String date) {
        return new java.sql.Date(java.sql.Date.from(LocalDate.parse(StringUtils.trim(date), DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime());
    }

    /**
     * 格式化成sql date
     *
     * @param date   date 日期
     * @param format 格式
     * @return sql date
     */
    public static java.sql.Date parseSqlDate(String date, String format) {
        return new java.sql.Date(java.sql.Date.from(LocalDate.parse(StringUtils.trim(date), DateTimeFormatter.ofPattern(format)).atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime());
    }

    /**
     * 格式化成util date
     *
     * @param date   date 日期
     * @param format 格式
     * @return util date
     */
    public static java.util.Date parseUtilDate(String date, String format) {
        return new java.util.Date(java.sql.Date.from(LocalDateTime.parse(StringUtils.trim(date), DateTimeFormatter.ofPattern(format)).atZone(ZoneId.systemDefault()).toInstant()).getTime());
    }

    /**
     * 格式化成sql date
     *
     * @param date   date 日期
     * @param format 格式
     * @return sql date
     */
    public static java.sql.Timestamp parseSqlTimestamp(String date, String format) {
        return new java.sql.Timestamp(java.sql.Timestamp.from(LocalDateTime.parse(StringUtils.trim(date), DateTimeFormatter.ofPattern(format)).atZone(ZoneId.systemDefault()).toInstant()).getTime());
    }

    /**
     * 当前时间是否在时间范围
     *
     * @param after  之前时间
     * @param before 之后时间
     * @return true or false
     */
    public static Boolean nowRangeSqlTimestamp(java.sql.Timestamp after, java.sql.Timestamp before) {
        java.sql.Timestamp now = new java.sql.Timestamp(Clock.systemDefaultZone().millis());
        return now.after(after) && now.before(before);
    }

    /**
     * 当前时间大于某一时间
     *
     * @param after 某一时间
     * @return true or false
     */
    public static Boolean nowAfterSqlTimestamp(java.sql.Timestamp after) {
        java.sql.Timestamp now = new java.sql.Timestamp(Clock.systemDefaultZone().millis());
        return now.after(after);
    }

    /**
     * 当前时间小于某一时间
     *
     * @param before 某一时间
     * @return true or false
     */
    public static Boolean nowBeforeSqlTimestamp(java.sql.Timestamp before) {
        java.sql.Timestamp now = new java.sql.Timestamp(Clock.systemDefaultZone().millis());
        return now.before(before);
    }

    /**
     * 得到当前时间
     *
     * @return 当前时间
     */
    public static java.sql.Timestamp getNowSqlTimestamp() {
        return new java.sql.Timestamp(Clock.systemDefaultZone().millis());
    }

    /**
     * 得到当前时间
     *
     * @return 当前时间
     */
    public static java.sql.Date getNowSqlDate() {
        return new java.sql.Date(Clock.systemDefaultZone().millis());
    }

    /**
     * 得到当前时间
     *
     * @return 当前时间
     */
    public static String getLocalDateTime(String format) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
    }
}
