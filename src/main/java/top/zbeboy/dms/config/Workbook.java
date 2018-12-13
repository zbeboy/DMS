package top.zbeboy.dms.config;

/**
 * Application constants.
 * 开发环境配置常量
 *
 * @author zbeboy
 * @version 1.0
 * @since 1.0
 */
public final class Workbook {

    /*
    开发环境
     */
    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";

    /*
    生产环境
     */
    public static final String SPRING_PROFILE_PRODUCTION = "prod";

    /*
    注册类型
     */
    public static final String REGISTER_STUDENT = "student";
    public static final String REGISTER_STAFF = "staff";

    /*
    静态配置参数
     */
    public enum SystemConfigure {

    }

    /*
    目录分隔符
    */
    public static final String DIRECTORY_SPLIT = "/";

    /*
    用户默认头像
     */
    public static final String USERS_AVATAR = "1000";

    /*
    用户类型参数
    */
    public static final String SYSTEM_USERS_TYPE = "系统";
    public static final String STUDENT_USERS_TYPE = "学生";
    public static final String STAFF_USERS_TYPE = "教职工";
}
