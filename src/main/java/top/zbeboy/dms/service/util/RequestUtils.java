package top.zbeboy.dms.service.util;

import top.zbeboy.dms.config.Workbook;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {

    public static String getRealPath(HttpServletRequest request) {
        return request.getSession().getServletContext().getRealPath(Workbook.DIRECTORY_SPLIT) + Workbook.DIRECTORY_SPLIT;
    }
}
