package top.zbeboy.dms.service.util;

public class SQLQueryUtils {

    /**
     * 组装likeAll全匹配参数
     *
     * @param param 参数
     * @return like '%{param}%'
     */
    public static String likeAllParam(String param) {
        return "%" + param + "%";
    }
}
