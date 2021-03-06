/*
 * This file is generated by jOOQ.
*/
package top.zbeboy.dms.domain.dms;


import javax.annotation.Generated;

import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;

import top.zbeboy.dms.domain.dms.tables.Authorities;
import top.zbeboy.dms.domain.dms.tables.College;
import top.zbeboy.dms.domain.dms.tables.Credit;
import top.zbeboy.dms.domain.dms.tables.Department;
import top.zbeboy.dms.domain.dms.tables.Diploma;
import top.zbeboy.dms.domain.dms.tables.Files;
import top.zbeboy.dms.domain.dms.tables.Grade;
import top.zbeboy.dms.domain.dms.tables.Organize;
import top.zbeboy.dms.domain.dms.tables.PersistentLogins;
import top.zbeboy.dms.domain.dms.tables.PoliticalLandscape;
import top.zbeboy.dms.domain.dms.tables.QualityApply;
import top.zbeboy.dms.domain.dms.tables.QualityRelease;
import top.zbeboy.dms.domain.dms.tables.School;
import top.zbeboy.dms.domain.dms.tables.Science;
import top.zbeboy.dms.domain.dms.tables.Staff;
import top.zbeboy.dms.domain.dms.tables.Student;
import top.zbeboy.dms.domain.dms.tables.SystemConfigure;
import top.zbeboy.dms.domain.dms.tables.SystemOperatorLog;
import top.zbeboy.dms.domain.dms.tables.Users;
import top.zbeboy.dms.domain.dms.tables.UsersType;
import top.zbeboy.dms.domain.dms.tables.Wining;
import top.zbeboy.dms.domain.dms.tables.records.AuthoritiesRecord;
import top.zbeboy.dms.domain.dms.tables.records.CollegeRecord;
import top.zbeboy.dms.domain.dms.tables.records.CreditRecord;
import top.zbeboy.dms.domain.dms.tables.records.DepartmentRecord;
import top.zbeboy.dms.domain.dms.tables.records.DiplomaRecord;
import top.zbeboy.dms.domain.dms.tables.records.FilesRecord;
import top.zbeboy.dms.domain.dms.tables.records.GradeRecord;
import top.zbeboy.dms.domain.dms.tables.records.OrganizeRecord;
import top.zbeboy.dms.domain.dms.tables.records.PersistentLoginsRecord;
import top.zbeboy.dms.domain.dms.tables.records.PoliticalLandscapeRecord;
import top.zbeboy.dms.domain.dms.tables.records.QualityApplyRecord;
import top.zbeboy.dms.domain.dms.tables.records.QualityReleaseRecord;
import top.zbeboy.dms.domain.dms.tables.records.SchoolRecord;
import top.zbeboy.dms.domain.dms.tables.records.ScienceRecord;
import top.zbeboy.dms.domain.dms.tables.records.StaffRecord;
import top.zbeboy.dms.domain.dms.tables.records.StudentRecord;
import top.zbeboy.dms.domain.dms.tables.records.SystemConfigureRecord;
import top.zbeboy.dms.domain.dms.tables.records.SystemOperatorLogRecord;
import top.zbeboy.dms.domain.dms.tables.records.UsersRecord;
import top.zbeboy.dms.domain.dms.tables.records.UsersTypeRecord;
import top.zbeboy.dms.domain.dms.tables.records.WiningRecord;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>DMS</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.8"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<CollegeRecord, Integer> IDENTITY_COLLEGE = Identities0.IDENTITY_COLLEGE;
    public static final Identity<CreditRecord, Integer> IDENTITY_CREDIT = Identities0.IDENTITY_CREDIT;
    public static final Identity<DepartmentRecord, Integer> IDENTITY_DEPARTMENT = Identities0.IDENTITY_DEPARTMENT;
    public static final Identity<DiplomaRecord, Integer> IDENTITY_DIPLOMA = Identities0.IDENTITY_DIPLOMA;
    public static final Identity<GradeRecord, Integer> IDENTITY_GRADE = Identities0.IDENTITY_GRADE;
    public static final Identity<OrganizeRecord, Integer> IDENTITY_ORGANIZE = Identities0.IDENTITY_ORGANIZE;
    public static final Identity<PoliticalLandscapeRecord, Integer> IDENTITY_POLITICAL_LANDSCAPE = Identities0.IDENTITY_POLITICAL_LANDSCAPE;
    public static final Identity<SchoolRecord, Integer> IDENTITY_SCHOOL = Identities0.IDENTITY_SCHOOL;
    public static final Identity<ScienceRecord, Integer> IDENTITY_SCIENCE = Identities0.IDENTITY_SCIENCE;
    public static final Identity<StaffRecord, Integer> IDENTITY_STAFF = Identities0.IDENTITY_STAFF;
    public static final Identity<StudentRecord, Integer> IDENTITY_STUDENT = Identities0.IDENTITY_STUDENT;
    public static final Identity<UsersTypeRecord, Integer> IDENTITY_USERS_TYPE = Identities0.IDENTITY_USERS_TYPE;
    public static final Identity<WiningRecord, Integer> IDENTITY_WINING = Identities0.IDENTITY_WINING;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<AuthoritiesRecord> CONSTRAINT_AB = UniqueKeys0.CONSTRAINT_AB;
    public static final UniqueKey<CollegeRecord> CONSTRAINT_6 = UniqueKeys0.CONSTRAINT_6;
    public static final UniqueKey<CreditRecord> CONSTRAINT_76 = UniqueKeys0.CONSTRAINT_76;
    public static final UniqueKey<DepartmentRecord> CONSTRAINT_4F = UniqueKeys0.CONSTRAINT_4F;
    public static final UniqueKey<DiplomaRecord> CONSTRAINT_8 = UniqueKeys0.CONSTRAINT_8;
    public static final UniqueKey<FilesRecord> CONSTRAINT_3 = UniqueKeys0.CONSTRAINT_3;
    public static final UniqueKey<GradeRecord> CONSTRAINT_40 = UniqueKeys0.CONSTRAINT_40;
    public static final UniqueKey<OrganizeRecord> CONSTRAINT_7 = UniqueKeys0.CONSTRAINT_7;
    public static final UniqueKey<PersistentLoginsRecord> CONSTRAINT_A3 = UniqueKeys0.CONSTRAINT_A3;
    public static final UniqueKey<PoliticalLandscapeRecord> CONSTRAINT_4E = UniqueKeys0.CONSTRAINT_4E;
    public static final UniqueKey<QualityApplyRecord> CONSTRAINT_C = UniqueKeys0.CONSTRAINT_C;
    public static final UniqueKey<QualityReleaseRecord> CONSTRAINT_66 = UniqueKeys0.CONSTRAINT_66;
    public static final UniqueKey<SchoolRecord> CONSTRAINT_9 = UniqueKeys0.CONSTRAINT_9;
    public static final UniqueKey<ScienceRecord> CONSTRAINT_9D = UniqueKeys0.CONSTRAINT_9D;
    public static final UniqueKey<StaffRecord> CONSTRAINT_4B = UniqueKeys0.CONSTRAINT_4B;
    public static final UniqueKey<StaffRecord> CONSTRAINT_4B8 = UniqueKeys0.CONSTRAINT_4B8;
    public static final UniqueKey<StudentRecord> CONSTRAINT_BA = UniqueKeys0.CONSTRAINT_BA;
    public static final UniqueKey<StudentRecord> CONSTRAINT_BAC = UniqueKeys0.CONSTRAINT_BAC;
    public static final UniqueKey<SystemConfigureRecord> CONSTRAINT_D = UniqueKeys0.CONSTRAINT_D;
    public static final UniqueKey<SystemOperatorLogRecord> CONSTRAINT_5 = UniqueKeys0.CONSTRAINT_5;
    public static final UniqueKey<UsersRecord> CONSTRAINT_4 = UniqueKeys0.CONSTRAINT_4;
    public static final UniqueKey<UsersTypeRecord> CONSTRAINT_B = UniqueKeys0.CONSTRAINT_B;
    public static final UniqueKey<UsersTypeRecord> CONSTRAINT_B3 = UniqueKeys0.CONSTRAINT_B3;
    public static final UniqueKey<WiningRecord> CONSTRAINT_98 = UniqueKeys0.CONSTRAINT_98;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<AuthoritiesRecord, UsersRecord> CONSTRAINT_A = ForeignKeys0.CONSTRAINT_A;
    public static final ForeignKey<CollegeRecord, SchoolRecord> CONSTRAINT_63 = ForeignKeys0.CONSTRAINT_63;
    public static final ForeignKey<DepartmentRecord, CollegeRecord> CONSTRAINT_4F7 = ForeignKeys0.CONSTRAINT_4F7;
    public static final ForeignKey<DiplomaRecord, CreditRecord> CONSTRAINT_8E = ForeignKeys0.CONSTRAINT_8E;
    public static final ForeignKey<GradeRecord, ScienceRecord> CONSTRAINT_40E = ForeignKeys0.CONSTRAINT_40E;
    public static final ForeignKey<OrganizeRecord, GradeRecord> CONSTRAINT_7D = ForeignKeys0.CONSTRAINT_7D;
    public static final ForeignKey<QualityApplyRecord, StudentRecord> CONSTRAINT_C0 = ForeignKeys0.CONSTRAINT_C0;
    public static final ForeignKey<QualityApplyRecord, QualityReleaseRecord> CONSTRAINT_C09 = ForeignKeys0.CONSTRAINT_C09;
    public static final ForeignKey<QualityReleaseRecord, UsersRecord> CONSTRAINT_668 = ForeignKeys0.CONSTRAINT_668;
    public static final ForeignKey<ScienceRecord, DepartmentRecord> CONSTRAINT_9D1 = ForeignKeys0.CONSTRAINT_9D1;
    public static final ForeignKey<StaffRecord, DepartmentRecord> CONSTRAINT_4B8C = ForeignKeys0.CONSTRAINT_4B8C;
    public static final ForeignKey<StaffRecord, UsersRecord> CONSTRAINT_4B8CA = ForeignKeys0.CONSTRAINT_4B8CA;
    public static final ForeignKey<StudentRecord, OrganizeRecord> CONSTRAINT_BACA = ForeignKeys0.CONSTRAINT_BACA;
    public static final ForeignKey<StudentRecord, UsersRecord> CONSTRAINT_BACA0 = ForeignKeys0.CONSTRAINT_BACA0;
    public static final ForeignKey<UsersRecord, UsersTypeRecord> CONSTRAINT_4D = ForeignKeys0.CONSTRAINT_4D;
    public static final ForeignKey<UsersRecord, FilesRecord> CONSTRAINT_4D4 = ForeignKeys0.CONSTRAINT_4D4;
    public static final ForeignKey<WiningRecord, CreditRecord> CONSTRAINT_989 = ForeignKeys0.CONSTRAINT_989;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {
        public static Identity<CollegeRecord, Integer> IDENTITY_COLLEGE = Internal.createIdentity(College.COLLEGE, College.COLLEGE.COLLEGE_ID);
        public static Identity<CreditRecord, Integer> IDENTITY_CREDIT = Internal.createIdentity(Credit.CREDIT, Credit.CREDIT.CREDIT_ID);
        public static Identity<DepartmentRecord, Integer> IDENTITY_DEPARTMENT = Internal.createIdentity(Department.DEPARTMENT, Department.DEPARTMENT.DEPARTMENT_ID);
        public static Identity<DiplomaRecord, Integer> IDENTITY_DIPLOMA = Internal.createIdentity(Diploma.DIPLOMA, Diploma.DIPLOMA.DIPLOMA_ID);
        public static Identity<GradeRecord, Integer> IDENTITY_GRADE = Internal.createIdentity(Grade.GRADE, Grade.GRADE.GRADE_ID);
        public static Identity<OrganizeRecord, Integer> IDENTITY_ORGANIZE = Internal.createIdentity(Organize.ORGANIZE, Organize.ORGANIZE.ORGANIZE_ID);
        public static Identity<PoliticalLandscapeRecord, Integer> IDENTITY_POLITICAL_LANDSCAPE = Internal.createIdentity(PoliticalLandscape.POLITICAL_LANDSCAPE, PoliticalLandscape.POLITICAL_LANDSCAPE.POLITICAL_LANDSCAPE_ID);
        public static Identity<SchoolRecord, Integer> IDENTITY_SCHOOL = Internal.createIdentity(School.SCHOOL, School.SCHOOL.SCHOOL_ID);
        public static Identity<ScienceRecord, Integer> IDENTITY_SCIENCE = Internal.createIdentity(Science.SCIENCE, Science.SCIENCE.SCIENCE_ID);
        public static Identity<StaffRecord, Integer> IDENTITY_STAFF = Internal.createIdentity(Staff.STAFF, Staff.STAFF.STAFF_ID);
        public static Identity<StudentRecord, Integer> IDENTITY_STUDENT = Internal.createIdentity(Student.STUDENT, Student.STUDENT.STUDENT_ID);
        public static Identity<UsersTypeRecord, Integer> IDENTITY_USERS_TYPE = Internal.createIdentity(UsersType.USERS_TYPE, UsersType.USERS_TYPE.USERS_TYPE_ID);
        public static Identity<WiningRecord, Integer> IDENTITY_WINING = Internal.createIdentity(Wining.WINING, Wining.WINING.WINING_ID);
    }

    private static class UniqueKeys0 {
        public static final UniqueKey<AuthoritiesRecord> CONSTRAINT_AB = Internal.createUniqueKey(Authorities.AUTHORITIES, "CONSTRAINT_AB", Authorities.AUTHORITIES.USERNAME, Authorities.AUTHORITIES.AUTHORITY);
        public static final UniqueKey<CollegeRecord> CONSTRAINT_6 = Internal.createUniqueKey(College.COLLEGE, "CONSTRAINT_6", College.COLLEGE.COLLEGE_ID);
        public static final UniqueKey<CreditRecord> CONSTRAINT_76 = Internal.createUniqueKey(Credit.CREDIT, "CONSTRAINT_76", Credit.CREDIT.CREDIT_ID);
        public static final UniqueKey<DepartmentRecord> CONSTRAINT_4F = Internal.createUniqueKey(Department.DEPARTMENT, "CONSTRAINT_4F", Department.DEPARTMENT.DEPARTMENT_ID);
        public static final UniqueKey<DiplomaRecord> CONSTRAINT_8 = Internal.createUniqueKey(Diploma.DIPLOMA, "CONSTRAINT_8", Diploma.DIPLOMA.DIPLOMA_ID);
        public static final UniqueKey<FilesRecord> CONSTRAINT_3 = Internal.createUniqueKey(Files.FILES, "CONSTRAINT_3", Files.FILES.FILE_ID);
        public static final UniqueKey<GradeRecord> CONSTRAINT_40 = Internal.createUniqueKey(Grade.GRADE, "CONSTRAINT_40", Grade.GRADE.GRADE_ID);
        public static final UniqueKey<OrganizeRecord> CONSTRAINT_7 = Internal.createUniqueKey(Organize.ORGANIZE, "CONSTRAINT_7", Organize.ORGANIZE.ORGANIZE_ID);
        public static final UniqueKey<PersistentLoginsRecord> CONSTRAINT_A3 = Internal.createUniqueKey(PersistentLogins.PERSISTENT_LOGINS, "CONSTRAINT_A3", PersistentLogins.PERSISTENT_LOGINS.SERIES);
        public static final UniqueKey<PoliticalLandscapeRecord> CONSTRAINT_4E = Internal.createUniqueKey(PoliticalLandscape.POLITICAL_LANDSCAPE, "CONSTRAINT_4E", PoliticalLandscape.POLITICAL_LANDSCAPE.POLITICAL_LANDSCAPE_ID);
        public static final UniqueKey<QualityApplyRecord> CONSTRAINT_C = Internal.createUniqueKey(QualityApply.QUALITY_APPLY, "CONSTRAINT_C", QualityApply.QUALITY_APPLY.QUALITY_APPLY_ID);
        public static final UniqueKey<QualityReleaseRecord> CONSTRAINT_66 = Internal.createUniqueKey(QualityRelease.QUALITY_RELEASE, "CONSTRAINT_66", QualityRelease.QUALITY_RELEASE.QUALITY_RELEASE_ID);
        public static final UniqueKey<SchoolRecord> CONSTRAINT_9 = Internal.createUniqueKey(School.SCHOOL, "CONSTRAINT_9", School.SCHOOL.SCHOOL_ID);
        public static final UniqueKey<ScienceRecord> CONSTRAINT_9D = Internal.createUniqueKey(Science.SCIENCE, "CONSTRAINT_9D", Science.SCIENCE.SCIENCE_ID);
        public static final UniqueKey<StaffRecord> CONSTRAINT_4B = Internal.createUniqueKey(Staff.STAFF, "CONSTRAINT_4B", Staff.STAFF.STAFF_ID);
        public static final UniqueKey<StaffRecord> CONSTRAINT_4B8 = Internal.createUniqueKey(Staff.STAFF, "CONSTRAINT_4B8", Staff.STAFF.STAFF_NUMBER);
        public static final UniqueKey<StudentRecord> CONSTRAINT_BA = Internal.createUniqueKey(Student.STUDENT, "CONSTRAINT_BA", Student.STUDENT.STUDENT_ID);
        public static final UniqueKey<StudentRecord> CONSTRAINT_BAC = Internal.createUniqueKey(Student.STUDENT, "CONSTRAINT_BAC", Student.STUDENT.STUDENT_NUMBER);
        public static final UniqueKey<SystemConfigureRecord> CONSTRAINT_D = Internal.createUniqueKey(SystemConfigure.SYSTEM_CONFIGURE, "CONSTRAINT_D", SystemConfigure.SYSTEM_CONFIGURE.DATA_KEY);
        public static final UniqueKey<SystemOperatorLogRecord> CONSTRAINT_5 = Internal.createUniqueKey(SystemOperatorLog.SYSTEM_OPERATOR_LOG, "CONSTRAINT_5", SystemOperatorLog.SYSTEM_OPERATOR_LOG.LOG_ID);
        public static final UniqueKey<UsersRecord> CONSTRAINT_4 = Internal.createUniqueKey(Users.USERS, "CONSTRAINT_4", Users.USERS.USERNAME);
        public static final UniqueKey<UsersTypeRecord> CONSTRAINT_B = Internal.createUniqueKey(UsersType.USERS_TYPE, "CONSTRAINT_B", UsersType.USERS_TYPE.USERS_TYPE_ID);
        public static final UniqueKey<UsersTypeRecord> CONSTRAINT_B3 = Internal.createUniqueKey(UsersType.USERS_TYPE, "CONSTRAINT_B3", UsersType.USERS_TYPE.USERS_TYPE_NAME);
        public static final UniqueKey<WiningRecord> CONSTRAINT_98 = Internal.createUniqueKey(Wining.WINING, "CONSTRAINT_98", Wining.WINING.WINING_ID);
    }

    private static class ForeignKeys0 {
        public static final ForeignKey<AuthoritiesRecord, UsersRecord> CONSTRAINT_A = Internal.createForeignKey(top.zbeboy.dms.domain.dms.Keys.CONSTRAINT_4, Authorities.AUTHORITIES, "CONSTRAINT_A", Authorities.AUTHORITIES.USERNAME);
        public static final ForeignKey<CollegeRecord, SchoolRecord> CONSTRAINT_63 = Internal.createForeignKey(top.zbeboy.dms.domain.dms.Keys.CONSTRAINT_9, College.COLLEGE, "CONSTRAINT_63", College.COLLEGE.SCHOOL_ID);
        public static final ForeignKey<DepartmentRecord, CollegeRecord> CONSTRAINT_4F7 = Internal.createForeignKey(top.zbeboy.dms.domain.dms.Keys.CONSTRAINT_6, Department.DEPARTMENT, "CONSTRAINT_4F7", Department.DEPARTMENT.COLLEGE_ID);
        public static final ForeignKey<DiplomaRecord, CreditRecord> CONSTRAINT_8E = Internal.createForeignKey(top.zbeboy.dms.domain.dms.Keys.CONSTRAINT_76, Diploma.DIPLOMA, "CONSTRAINT_8E", Diploma.DIPLOMA.CREDIT_ID);
        public static final ForeignKey<GradeRecord, ScienceRecord> CONSTRAINT_40E = Internal.createForeignKey(top.zbeboy.dms.domain.dms.Keys.CONSTRAINT_9D, Grade.GRADE, "CONSTRAINT_40E", Grade.GRADE.SCIENCE_ID);
        public static final ForeignKey<OrganizeRecord, GradeRecord> CONSTRAINT_7D = Internal.createForeignKey(top.zbeboy.dms.domain.dms.Keys.CONSTRAINT_40, Organize.ORGANIZE, "CONSTRAINT_7D", Organize.ORGANIZE.GRADE_ID);
        public static final ForeignKey<QualityApplyRecord, StudentRecord> CONSTRAINT_C0 = Internal.createForeignKey(top.zbeboy.dms.domain.dms.Keys.CONSTRAINT_BA, QualityApply.QUALITY_APPLY, "CONSTRAINT_C0", QualityApply.QUALITY_APPLY.STUDENT_ID);
        public static final ForeignKey<QualityApplyRecord, QualityReleaseRecord> CONSTRAINT_C09 = Internal.createForeignKey(top.zbeboy.dms.domain.dms.Keys.CONSTRAINT_66, QualityApply.QUALITY_APPLY, "CONSTRAINT_C09", QualityApply.QUALITY_APPLY.QUALITY_RELEASE_ID);
        public static final ForeignKey<QualityReleaseRecord, UsersRecord> CONSTRAINT_668 = Internal.createForeignKey(top.zbeboy.dms.domain.dms.Keys.CONSTRAINT_4, QualityRelease.QUALITY_RELEASE, "CONSTRAINT_668", QualityRelease.QUALITY_RELEASE.USERNAME);
        public static final ForeignKey<ScienceRecord, DepartmentRecord> CONSTRAINT_9D1 = Internal.createForeignKey(top.zbeboy.dms.domain.dms.Keys.CONSTRAINT_4F, Science.SCIENCE, "CONSTRAINT_9D1", Science.SCIENCE.DEPARTMENT_ID);
        public static final ForeignKey<StaffRecord, DepartmentRecord> CONSTRAINT_4B8C = Internal.createForeignKey(top.zbeboy.dms.domain.dms.Keys.CONSTRAINT_4F, Staff.STAFF, "CONSTRAINT_4B8C", Staff.STAFF.DEPARTMENT_ID);
        public static final ForeignKey<StaffRecord, UsersRecord> CONSTRAINT_4B8CA = Internal.createForeignKey(top.zbeboy.dms.domain.dms.Keys.CONSTRAINT_4, Staff.STAFF, "CONSTRAINT_4B8CA", Staff.STAFF.USERNAME);
        public static final ForeignKey<StudentRecord, OrganizeRecord> CONSTRAINT_BACA = Internal.createForeignKey(top.zbeboy.dms.domain.dms.Keys.CONSTRAINT_7, Student.STUDENT, "CONSTRAINT_BACA", Student.STUDENT.ORGANIZE_ID);
        public static final ForeignKey<StudentRecord, UsersRecord> CONSTRAINT_BACA0 = Internal.createForeignKey(top.zbeboy.dms.domain.dms.Keys.CONSTRAINT_4, Student.STUDENT, "CONSTRAINT_BACA0", Student.STUDENT.USERNAME);
        public static final ForeignKey<UsersRecord, UsersTypeRecord> CONSTRAINT_4D = Internal.createForeignKey(top.zbeboy.dms.domain.dms.Keys.CONSTRAINT_B, Users.USERS, "CONSTRAINT_4D", Users.USERS.USERS_TYPE_ID);
        public static final ForeignKey<UsersRecord, FilesRecord> CONSTRAINT_4D4 = Internal.createForeignKey(top.zbeboy.dms.domain.dms.Keys.CONSTRAINT_3, Users.USERS, "CONSTRAINT_4D4", Users.USERS.AVATAR);
        public static final ForeignKey<WiningRecord, CreditRecord> CONSTRAINT_989 = Internal.createForeignKey(top.zbeboy.dms.domain.dms.Keys.CONSTRAINT_76, Wining.WINING, "CONSTRAINT_989", Wining.WINING.CREDIT_ID);
    }
}
