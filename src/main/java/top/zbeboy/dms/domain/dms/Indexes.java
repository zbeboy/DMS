/*
 * This file is generated by jOOQ.
*/
package top.zbeboy.dms.domain.dms;


import javax.annotation.Generated;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;

import top.zbeboy.dms.domain.dms.tables.Authorities;
import top.zbeboy.dms.domain.dms.tables.College;
import top.zbeboy.dms.domain.dms.tables.Credit;
import top.zbeboy.dms.domain.dms.tables.Department;
import top.zbeboy.dms.domain.dms.tables.Evaluate;
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
 * A class modelling indexes of tables of the <code>DMS</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.8"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index CONSTRAINT_INDEX_A = Indexes0.CONSTRAINT_INDEX_A;
    public static final Index PRIMARY_KEY_A = Indexes0.PRIMARY_KEY_A;
    public static final Index CONSTRAINT_INDEX_6 = Indexes0.CONSTRAINT_INDEX_6;
    public static final Index PRIMARY_KEY_63 = Indexes0.PRIMARY_KEY_63;
    public static final Index PRIMARY_KEY_76 = Indexes0.PRIMARY_KEY_76;
    public static final Index CONSTRAINT_INDEX_4F = Indexes0.CONSTRAINT_INDEX_4F;
    public static final Index PRIMARY_KEY_4F = Indexes0.PRIMARY_KEY_4F;
    public static final Index PRIMARY_KEY_38 = Indexes0.PRIMARY_KEY_38;
    public static final Index PRIMARY_KEY_3 = Indexes0.PRIMARY_KEY_3;
    public static final Index CONSTRAINT_INDEX_40 = Indexes0.CONSTRAINT_INDEX_40;
    public static final Index PRIMARY_KEY_40 = Indexes0.PRIMARY_KEY_40;
    public static final Index CONSTRAINT_INDEX_7 = Indexes0.CONSTRAINT_INDEX_7;
    public static final Index PRIMARY_KEY_7 = Indexes0.PRIMARY_KEY_7;
    public static final Index PRIMARY_KEY_A3 = Indexes0.PRIMARY_KEY_A3;
    public static final Index PRIMARY_KEY_4E = Indexes0.PRIMARY_KEY_4E;
    public static final Index PRIMARY_KEY_9 = Indexes0.PRIMARY_KEY_9;
    public static final Index CONSTRAINT_INDEX_9 = Indexes0.CONSTRAINT_INDEX_9;
    public static final Index PRIMARY_KEY_9D = Indexes0.PRIMARY_KEY_9D;
    public static final Index CONSTRAINT_INDEX_4B = Indexes0.CONSTRAINT_INDEX_4B;
    public static final Index CONSTRAINT_INDEX_4B8 = Indexes0.CONSTRAINT_INDEX_4B8;
    public static final Index CONSTRAINT_INDEX_4B8C = Indexes0.CONSTRAINT_INDEX_4B8C;
    public static final Index PRIMARY_KEY_4B = Indexes0.PRIMARY_KEY_4B;
    public static final Index CONSTRAINT_INDEX_BA = Indexes0.CONSTRAINT_INDEX_BA;
    public static final Index CONSTRAINT_INDEX_BAC = Indexes0.CONSTRAINT_INDEX_BAC;
    public static final Index CONSTRAINT_INDEX_BACA = Indexes0.CONSTRAINT_INDEX_BACA;
    public static final Index PRIMARY_KEY_BA = Indexes0.PRIMARY_KEY_BA;
    public static final Index PRIMARY_KEY_D = Indexes0.PRIMARY_KEY_D;
    public static final Index PRIMARY_KEY_5 = Indexes0.PRIMARY_KEY_5;
    public static final Index CONSTRAINT_INDEX_4 = Indexes0.CONSTRAINT_INDEX_4;
    public static final Index CONSTRAINT_INDEX_4D = Indexes0.CONSTRAINT_INDEX_4D;
    public static final Index PRIMARY_KEY_4 = Indexes0.PRIMARY_KEY_4;
    public static final Index CONSTRAINT_INDEX_B = Indexes0.CONSTRAINT_INDEX_B;
    public static final Index PRIMARY_KEY_B = Indexes0.PRIMARY_KEY_B;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index CONSTRAINT_INDEX_A = Internal.createIndex("CONSTRAINT_INDEX_A", Authorities.AUTHORITIES, new OrderField[] { Authorities.AUTHORITIES.USERNAME }, false);
        public static Index PRIMARY_KEY_A = Internal.createIndex("PRIMARY_KEY_A", Authorities.AUTHORITIES, new OrderField[] { Authorities.AUTHORITIES.USERNAME, Authorities.AUTHORITIES.AUTHORITY }, true);
        public static Index CONSTRAINT_INDEX_6 = Internal.createIndex("CONSTRAINT_INDEX_6", College.COLLEGE, new OrderField[] { College.COLLEGE.SCHOOL_ID }, false);
        public static Index PRIMARY_KEY_63 = Internal.createIndex("PRIMARY_KEY_63", College.COLLEGE, new OrderField[] { College.COLLEGE.COLLEGE_ID }, true);
        public static Index PRIMARY_KEY_76 = Internal.createIndex("PRIMARY_KEY_76", Credit.CREDIT, new OrderField[] { Credit.CREDIT.CREDIT_ID }, true);
        public static Index CONSTRAINT_INDEX_4F = Internal.createIndex("CONSTRAINT_INDEX_4F", Department.DEPARTMENT, new OrderField[] { Department.DEPARTMENT.COLLEGE_ID }, false);
        public static Index PRIMARY_KEY_4F = Internal.createIndex("PRIMARY_KEY_4F", Department.DEPARTMENT, new OrderField[] { Department.DEPARTMENT.DEPARTMENT_ID }, true);
        public static Index PRIMARY_KEY_38 = Internal.createIndex("PRIMARY_KEY_38", Evaluate.EVALUATE, new OrderField[] { Evaluate.EVALUATE.EVALUATE_ID }, true);
        public static Index PRIMARY_KEY_3 = Internal.createIndex("PRIMARY_KEY_3", Files.FILES, new OrderField[] { Files.FILES.FILE_ID }, true);
        public static Index CONSTRAINT_INDEX_40 = Internal.createIndex("CONSTRAINT_INDEX_40", Grade.GRADE, new OrderField[] { Grade.GRADE.SCIENCE_ID }, false);
        public static Index PRIMARY_KEY_40 = Internal.createIndex("PRIMARY_KEY_40", Grade.GRADE, new OrderField[] { Grade.GRADE.GRADE_ID }, true);
        public static Index CONSTRAINT_INDEX_7 = Internal.createIndex("CONSTRAINT_INDEX_7", Organize.ORGANIZE, new OrderField[] { Organize.ORGANIZE.GRADE_ID }, false);
        public static Index PRIMARY_KEY_7 = Internal.createIndex("PRIMARY_KEY_7", Organize.ORGANIZE, new OrderField[] { Organize.ORGANIZE.ORGANIZE_ID }, true);
        public static Index PRIMARY_KEY_A3 = Internal.createIndex("PRIMARY_KEY_A3", PersistentLogins.PERSISTENT_LOGINS, new OrderField[] { PersistentLogins.PERSISTENT_LOGINS.SERIES }, true);
        public static Index PRIMARY_KEY_4E = Internal.createIndex("PRIMARY_KEY_4E", PoliticalLandscape.POLITICAL_LANDSCAPE, new OrderField[] { PoliticalLandscape.POLITICAL_LANDSCAPE.POLITICAL_LANDSCAPE_ID }, true);
        public static Index PRIMARY_KEY_9 = Internal.createIndex("PRIMARY_KEY_9", School.SCHOOL, new OrderField[] { School.SCHOOL.SCHOOL_ID }, true);
        public static Index CONSTRAINT_INDEX_9 = Internal.createIndex("CONSTRAINT_INDEX_9", Science.SCIENCE, new OrderField[] { Science.SCIENCE.DEPARTMENT_ID }, false);
        public static Index PRIMARY_KEY_9D = Internal.createIndex("PRIMARY_KEY_9D", Science.SCIENCE, new OrderField[] { Science.SCIENCE.SCIENCE_ID }, true);
        public static Index CONSTRAINT_INDEX_4B = Internal.createIndex("CONSTRAINT_INDEX_4B", Staff.STAFF, new OrderField[] { Staff.STAFF.STAFF_NUMBER }, true);
        public static Index CONSTRAINT_INDEX_4B8 = Internal.createIndex("CONSTRAINT_INDEX_4B8", Staff.STAFF, new OrderField[] { Staff.STAFF.DEPARTMENT_ID }, false);
        public static Index CONSTRAINT_INDEX_4B8C = Internal.createIndex("CONSTRAINT_INDEX_4B8C", Staff.STAFF, new OrderField[] { Staff.STAFF.USERNAME }, false);
        public static Index PRIMARY_KEY_4B = Internal.createIndex("PRIMARY_KEY_4B", Staff.STAFF, new OrderField[] { Staff.STAFF.STAFF_ID }, true);
        public static Index CONSTRAINT_INDEX_BA = Internal.createIndex("CONSTRAINT_INDEX_BA", Student.STUDENT, new OrderField[] { Student.STUDENT.STUDENT_NUMBER }, true);
        public static Index CONSTRAINT_INDEX_BAC = Internal.createIndex("CONSTRAINT_INDEX_BAC", Student.STUDENT, new OrderField[] { Student.STUDENT.ORGANIZE_ID }, false);
        public static Index CONSTRAINT_INDEX_BACA = Internal.createIndex("CONSTRAINT_INDEX_BACA", Student.STUDENT, new OrderField[] { Student.STUDENT.USERNAME }, false);
        public static Index PRIMARY_KEY_BA = Internal.createIndex("PRIMARY_KEY_BA", Student.STUDENT, new OrderField[] { Student.STUDENT.STUDENT_ID }, true);
        public static Index PRIMARY_KEY_D = Internal.createIndex("PRIMARY_KEY_D", SystemConfigure.SYSTEM_CONFIGURE, new OrderField[] { SystemConfigure.SYSTEM_CONFIGURE.DATA_KEY }, true);
        public static Index PRIMARY_KEY_5 = Internal.createIndex("PRIMARY_KEY_5", SystemOperatorLog.SYSTEM_OPERATOR_LOG, new OrderField[] { SystemOperatorLog.SYSTEM_OPERATOR_LOG.LOG_ID }, true);
        public static Index CONSTRAINT_INDEX_4 = Internal.createIndex("CONSTRAINT_INDEX_4", Users.USERS, new OrderField[] { Users.USERS.USERS_TYPE_ID }, false);
        public static Index CONSTRAINT_INDEX_4D = Internal.createIndex("CONSTRAINT_INDEX_4D", Users.USERS, new OrderField[] { Users.USERS.AVATAR }, false);
        public static Index PRIMARY_KEY_4 = Internal.createIndex("PRIMARY_KEY_4", Users.USERS, new OrderField[] { Users.USERS.USERNAME }, true);
        public static Index CONSTRAINT_INDEX_B = Internal.createIndex("CONSTRAINT_INDEX_B", UsersType.USERS_TYPE, new OrderField[] { UsersType.USERS_TYPE.USERS_TYPE_NAME }, true);
        public static Index PRIMARY_KEY_B = Internal.createIndex("PRIMARY_KEY_B", UsersType.USERS_TYPE, new OrderField[] { UsersType.USERS_TYPE.USERS_TYPE_ID }, true);
    }
}
