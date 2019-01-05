/*
 * This file is generated by jOOQ.
*/
package top.zbeboy.dms.domain.dms;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;

import top.zbeboy.dms.domain.DefaultCatalog;
import top.zbeboy.dms.domain.dms.tables.Authorities;
import top.zbeboy.dms.domain.dms.tables.College;
import top.zbeboy.dms.domain.dms.tables.Credit;
import top.zbeboy.dms.domain.dms.tables.Department;
import top.zbeboy.dms.domain.dms.tables.Files;
import top.zbeboy.dms.domain.dms.tables.Grade;
import top.zbeboy.dms.domain.dms.tables.Organize;
import top.zbeboy.dms.domain.dms.tables.PersistentLogins;
import top.zbeboy.dms.domain.dms.tables.PoliticalLandscape;
import top.zbeboy.dms.domain.dms.tables.School;
import top.zbeboy.dms.domain.dms.tables.Science;
import top.zbeboy.dms.domain.dms.tables.Staff;
import top.zbeboy.dms.domain.dms.tables.Student;
import top.zbeboy.dms.domain.dms.tables.SystemConfigure;
import top.zbeboy.dms.domain.dms.tables.SystemOperatorLog;
import top.zbeboy.dms.domain.dms.tables.Users;
import top.zbeboy.dms.domain.dms.tables.UsersType;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.8"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Dms extends SchemaImpl {

    private static final long serialVersionUID = 444853416;

    /**
     * The reference instance of <code>DMS</code>
     */
    public static final Dms DMS = new Dms();

    /**
     * The table <code>DMS.AUTHORITIES</code>.
     */
    public final Authorities AUTHORITIES = top.zbeboy.dms.domain.dms.tables.Authorities.AUTHORITIES;

    /**
     * The table <code>DMS.COLLEGE</code>.
     */
    public final College COLLEGE = top.zbeboy.dms.domain.dms.tables.College.COLLEGE;

    /**
     * The table <code>DMS.CREDIT</code>.
     */
    public final Credit CREDIT = top.zbeboy.dms.domain.dms.tables.Credit.CREDIT;

    /**
     * The table <code>DMS.DEPARTMENT</code>.
     */
    public final Department DEPARTMENT = top.zbeboy.dms.domain.dms.tables.Department.DEPARTMENT;

    /**
     * The table <code>DMS.FILES</code>.
     */
    public final Files FILES = top.zbeboy.dms.domain.dms.tables.Files.FILES;

    /**
     * The table <code>DMS.GRADE</code>.
     */
    public final Grade GRADE = top.zbeboy.dms.domain.dms.tables.Grade.GRADE;

    /**
     * The table <code>DMS.ORGANIZE</code>.
     */
    public final Organize ORGANIZE = top.zbeboy.dms.domain.dms.tables.Organize.ORGANIZE;

    /**
     * The table <code>DMS.PERSISTENT_LOGINS</code>.
     */
    public final PersistentLogins PERSISTENT_LOGINS = top.zbeboy.dms.domain.dms.tables.PersistentLogins.PERSISTENT_LOGINS;

    /**
     * The table <code>DMS.POLITICAL_LANDSCAPE</code>.
     */
    public final PoliticalLandscape POLITICAL_LANDSCAPE = top.zbeboy.dms.domain.dms.tables.PoliticalLandscape.POLITICAL_LANDSCAPE;

    /**
     * The table <code>DMS.SCHOOL</code>.
     */
    public final School SCHOOL = top.zbeboy.dms.domain.dms.tables.School.SCHOOL;

    /**
     * The table <code>DMS.SCIENCE</code>.
     */
    public final Science SCIENCE = top.zbeboy.dms.domain.dms.tables.Science.SCIENCE;

    /**
     * The table <code>DMS.STAFF</code>.
     */
    public final Staff STAFF = top.zbeboy.dms.domain.dms.tables.Staff.STAFF;

    /**
     * The table <code>DMS.STUDENT</code>.
     */
    public final Student STUDENT = top.zbeboy.dms.domain.dms.tables.Student.STUDENT;

    /**
     * The table <code>DMS.SYSTEM_CONFIGURE</code>.
     */
    public final SystemConfigure SYSTEM_CONFIGURE = top.zbeboy.dms.domain.dms.tables.SystemConfigure.SYSTEM_CONFIGURE;

    /**
     * The table <code>DMS.SYSTEM_OPERATOR_LOG</code>.
     */
    public final SystemOperatorLog SYSTEM_OPERATOR_LOG = top.zbeboy.dms.domain.dms.tables.SystemOperatorLog.SYSTEM_OPERATOR_LOG;

    /**
     * The table <code>DMS.USERS</code>.
     */
    public final Users USERS = top.zbeboy.dms.domain.dms.tables.Users.USERS;

    /**
     * The table <code>DMS.USERS_TYPE</code>.
     */
    public final UsersType USERS_TYPE = top.zbeboy.dms.domain.dms.tables.UsersType.USERS_TYPE;

    /**
     * No further instances allowed
     */
    private Dms() {
        super("DMS", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            Authorities.AUTHORITIES,
            College.COLLEGE,
            Credit.CREDIT,
            Department.DEPARTMENT,
            Files.FILES,
            Grade.GRADE,
            Organize.ORGANIZE,
            PersistentLogins.PERSISTENT_LOGINS,
            PoliticalLandscape.POLITICAL_LANDSCAPE,
            School.SCHOOL,
            Science.SCIENCE,
            Staff.STAFF,
            Student.STUDENT,
            SystemConfigure.SYSTEM_CONFIGURE,
            SystemOperatorLog.SYSTEM_OPERATOR_LOG,
            Users.USERS,
            UsersType.USERS_TYPE);
    }
}
