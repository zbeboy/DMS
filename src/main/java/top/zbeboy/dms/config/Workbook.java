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
    静态配置参数
     */
    public enum SystemConfigure {

    }

    /*
    目录分隔符
    */
    public static final String DIRECTORY_SPLIT = "/";

    public static final String FILES_PORTFOLIOS = "files" + DIRECTORY_SPLIT;

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

    /*
    系统角色
     */
    public static final String ROLE_PREFIX = "ROLE_";
    public static final String SYSTEM = "SYSTEM";
    public static final String COLLEGE_YOUTH_LEAGUE_COMMITTEE = "COLLEGE_YOUTH_LEAGUE_COMMITTEE";
    public static final String COLLEGE_WORK_DEPARTMENT = "COLLEGE_WORK_DEPARTMENT";
    public static final String DEPARTMENT_INSTRUCTOR = "DEPARTMENT_INSTRUCTOR";
    public static final String HEADMASTER = "HEADMASTER";
    public static final String STUDENT = "STUDENT";
    public static final String STAFF = "STAFF";
    // 系统管理员
    public static final String ROLE_SYSTEM = ROLE_PREFIX + SYSTEM;
    // 学院团委
    public static final String ROLE_COLLEGE_YOUTH_LEAGUE_COMMITTEE = ROLE_PREFIX + COLLEGE_YOUTH_LEAGUE_COMMITTEE;
    // 学生工作部
    public static final String ROLE_COLLEGE_WORK_DEPARTMENT = ROLE_PREFIX + COLLEGE_WORK_DEPARTMENT;
    // 系部辅导员
    public static final String ROLE_DEPARTMENT_INSTRUCTOR = ROLE_PREFIX + DEPARTMENT_INSTRUCTOR;
    // 班主任
    public static final String ROLE_HEADMASTER = ROLE_PREFIX + HEADMASTER;
    // 学生
    public static final String ROLE_STUDENT = ROLE_PREFIX + STUDENT;
    // 教职工
    public static final String ROLE_STAFF = ROLE_PREFIX + STAFF;

    public static final String XLS_FILE = "xls";
    public static final String XLSX_FILE = "xlsx";

    public static String studentFilePath() {
        return FILES_PORTFOLIOS + "student" + DIRECTORY_SPLIT;
    }

    public static String studentImportPath() {
        return FILES_PORTFOLIOS + "student" + DIRECTORY_SPLIT + "import";
    }

    public static String staffFilePath() {
        return FILES_PORTFOLIOS + "staff" + DIRECTORY_SPLIT;
    }

    public static String staffImportPath() {
        return FILES_PORTFOLIOS + "staff" + DIRECTORY_SPLIT + "import";
    }

    public static String creditFilePath() {
        return FILES_PORTFOLIOS + "credit" + DIRECTORY_SPLIT;
    }

    public static String creditImportPath() {
        return FILES_PORTFOLIOS + "credit" + DIRECTORY_SPLIT + "import";
    }
}
