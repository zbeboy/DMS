package top.zbeboy.dms.service.data;

import com.alibaba.fastjson.JSONObject;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import top.zbeboy.dms.config.Workbook;
import top.zbeboy.dms.domain.dms.tables.daos.StudentDao;
import top.zbeboy.dms.domain.dms.tables.pojos.Student;
import top.zbeboy.dms.domain.dms.tables.pojos.Users;
import top.zbeboy.dms.domain.dms.tables.records.StudentRecord;
import top.zbeboy.dms.service.platform.UsersService;
import top.zbeboy.dms.service.platform.UsersTypeService;
import top.zbeboy.dms.service.plugin.BootstrapTablesPlugin;
import top.zbeboy.dms.service.system.AuthoritiesService;
import top.zbeboy.dms.service.util.BCryptUtils;
import top.zbeboy.dms.service.util.DateTimeUtils;
import top.zbeboy.dms.service.util.SQLQueryUtils;
import top.zbeboy.dms.web.bean.data.student.StudentBean;
import top.zbeboy.dms.web.util.BootstrapTableUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static top.zbeboy.dms.domain.dms.Tables.*;

@Service("studentService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class StudentServiceImpl extends BootstrapTablesPlugin<StudentBean> implements StudentService {

    private final DSLContext create;

    @Resource
    private StudentDao studentDao;

    @Resource
    private UsersTypeService usersTypeService;

    @Resource
    private UsersService usersService;

    @Resource
    private AuthoritiesService authoritiesService;

    @Autowired
    StudentServiceImpl(DSLContext dslContext) {
        create = dslContext;
    }

    @Override
    public Student findByStudentId(int studentId) {
        return studentDao.findById(studentId);
    }

    @Override
    public Optional<Record> findByUsernameRelation(String username) {
        return create.select()
                .from(STUDENT)
                .join(USERS)
                .on(STUDENT.USERNAME.eq(USERS.USERNAME))
                .join(POLITICAL_LANDSCAPE)
                .on(STUDENT.POLITICAL_LANDSCAPE_ID.eq(POLITICAL_LANDSCAPE.POLITICAL_LANDSCAPE_ID))
                .join(ORGANIZE)
                .on(STUDENT.ORGANIZE_ID.eq(ORGANIZE.ORGANIZE_ID))
                .join(GRADE)
                .on(ORGANIZE.GRADE_ID.eq(GRADE.GRADE_ID))
                .join(SCIENCE)
                .on(GRADE.SCIENCE_ID.eq(SCIENCE.SCIENCE_ID))
                .join(DEPARTMENT)
                .on(SCIENCE.DEPARTMENT_ID.eq(DEPARTMENT.DEPARTMENT_ID))
                .join(COLLEGE)
                .on(DEPARTMENT.COLLEGE_ID.eq(COLLEGE.COLLEGE_ID))
                .where(STUDENT.USERNAME.eq(username))
                .fetchOptional();
    }

    @Override
    public Student findByStudentNumber(String studentNumber) {
        return studentDao.fetchOneByStudentNumber(studentNumber);
    }

    @Override
    public List<Student> findByUsername(String username) {
        return studentDao.fetchByUsername(username);
    }

    @Override
    public Result<StudentRecord> findByStudentNumberNeStudentId(int studentId, String studentNumber) {
        return create.selectFrom(STUDENT)
                .where(STUDENT.STUDENT_NUMBER.eq(studentNumber).and(STUDENT.STUDENT_ID.ne(studentId)))
                .fetch();
    }

    @Override
    public Result<Record> findAllByPage(BootstrapTableUtils<StudentBean> bootstrapTableUtils) {
        Result<Record> records;
        Condition a = searchCondition(bootstrapTableUtils);
        a = buildCondition(a);
        if (ObjectUtils.isEmpty(a)) {
            SelectJoinStep<Record> selectJoinStep = create.select()
                    .from(STUDENT)
                    .join(USERS)
                    .on(STUDENT.USERNAME.eq(USERS.USERNAME))
                    .join(POLITICAL_LANDSCAPE)
                    .on(STUDENT.POLITICAL_LANDSCAPE_ID.eq(POLITICAL_LANDSCAPE.POLITICAL_LANDSCAPE_ID))
                    .join(ORGANIZE)
                    .on(STUDENT.ORGANIZE_ID.eq(ORGANIZE.ORGANIZE_ID))
                    .join(GRADE)
                    .on(ORGANIZE.GRADE_ID.eq(GRADE.GRADE_ID))
                    .join(SCIENCE)
                    .on(GRADE.SCIENCE_ID.eq(SCIENCE.SCIENCE_ID))
                    .join(DEPARTMENT)
                    .on(SCIENCE.DEPARTMENT_ID.eq(DEPARTMENT.DEPARTMENT_ID))
                    .join(COLLEGE)
                    .on(DEPARTMENT.COLLEGE_ID.eq(COLLEGE.COLLEGE_ID))
                    .join(SCHOOL)
                    .on(COLLEGE.SCHOOL_ID.eq(SCHOOL.SCHOOL_ID));
            sortCondition(bootstrapTableUtils, null, selectJoinStep, JOIN_TYPE);
            pagination(bootstrapTableUtils, null, selectJoinStep, JOIN_TYPE);
            records = selectJoinStep.fetch();
        } else {
            SelectConditionStep<Record> selectConditionStep = create.select()
                    .from(STUDENT)
                    .join(USERS)
                    .on(STUDENT.USERNAME.eq(USERS.USERNAME))
                    .join(POLITICAL_LANDSCAPE)
                    .on(STUDENT.POLITICAL_LANDSCAPE_ID.eq(POLITICAL_LANDSCAPE.POLITICAL_LANDSCAPE_ID))
                    .join(ORGANIZE)
                    .on(STUDENT.ORGANIZE_ID.eq(ORGANIZE.ORGANIZE_ID))
                    .join(GRADE)
                    .on(ORGANIZE.GRADE_ID.eq(GRADE.GRADE_ID))
                    .join(SCIENCE)
                    .on(GRADE.SCIENCE_ID.eq(SCIENCE.SCIENCE_ID))
                    .join(DEPARTMENT)
                    .on(SCIENCE.DEPARTMENT_ID.eq(DEPARTMENT.DEPARTMENT_ID))
                    .join(COLLEGE)
                    .on(DEPARTMENT.COLLEGE_ID.eq(COLLEGE.COLLEGE_ID))
                    .join(SCHOOL)
                    .on(COLLEGE.SCHOOL_ID.eq(SCHOOL.SCHOOL_ID))
                    .where(a);
            sortCondition(bootstrapTableUtils, selectConditionStep, null, CONDITION_TYPE);
            pagination(bootstrapTableUtils, selectConditionStep, null, CONDITION_TYPE);
            records = selectConditionStep.fetch();
        }
        return records;
    }

    @Override
    public int countByCondition(BootstrapTableUtils<StudentBean> bootstrapTableUtils) {
        Record1<Integer> count;
        Condition a = searchCondition(bootstrapTableUtils);
        a = buildCondition(a);
        if (ObjectUtils.isEmpty(a)) {
            SelectJoinStep<Record1<Integer>> selectJoinStep = create.selectCount()
                    .from(STUDENT)
                    .join(USERS)
                    .on(STUDENT.USERNAME.eq(USERS.USERNAME))
                    .join(POLITICAL_LANDSCAPE)
                    .on(STUDENT.POLITICAL_LANDSCAPE_ID.eq(POLITICAL_LANDSCAPE.POLITICAL_LANDSCAPE_ID))
                    .join(ORGANIZE)
                    .on(STUDENT.ORGANIZE_ID.eq(ORGANIZE.ORGANIZE_ID))
                    .join(GRADE)
                    .on(ORGANIZE.GRADE_ID.eq(GRADE.GRADE_ID))
                    .join(SCIENCE)
                    .on(GRADE.SCIENCE_ID.eq(SCIENCE.SCIENCE_ID))
                    .join(DEPARTMENT)
                    .on(SCIENCE.DEPARTMENT_ID.eq(DEPARTMENT.DEPARTMENT_ID))
                    .join(COLLEGE)
                    .on(DEPARTMENT.COLLEGE_ID.eq(COLLEGE.COLLEGE_ID))
                    .join(SCHOOL)
                    .on(COLLEGE.COLLEGE_ID.eq(SCHOOL.SCHOOL_ID));
            count = selectJoinStep.fetchOne();
        } else {
            SelectConditionStep<Record1<Integer>> selectConditionStep = create.selectCount()
                    .from(STUDENT)
                    .join(USERS)
                    .on(STUDENT.USERNAME.eq(USERS.USERNAME))
                    .join(POLITICAL_LANDSCAPE)
                    .on(STUDENT.POLITICAL_LANDSCAPE_ID.eq(POLITICAL_LANDSCAPE.POLITICAL_LANDSCAPE_ID))
                    .join(ORGANIZE)
                    .on(STUDENT.ORGANIZE_ID.eq(ORGANIZE.ORGANIZE_ID))
                    .join(GRADE)
                    .on(ORGANIZE.GRADE_ID.eq(GRADE.GRADE_ID))
                    .join(SCIENCE)
                    .on(GRADE.SCIENCE_ID.eq(SCIENCE.SCIENCE_ID))
                    .join(DEPARTMENT)
                    .on(SCIENCE.DEPARTMENT_ID.eq(DEPARTMENT.DEPARTMENT_ID))
                    .join(COLLEGE)
                    .on(DEPARTMENT.COLLEGE_ID.eq(COLLEGE.COLLEGE_ID))
                    .join(SCHOOL)
                    .on(COLLEGE.COLLEGE_ID.eq(SCHOOL.SCHOOL_ID))
                    .where(a);
            count = selectConditionStep.fetchOne();
        }
        return count.value1();
    }

    @Override
    public Result<Record> export(BootstrapTableUtils<StudentBean> bootstrapTableUtils) {
        Result<Record> records;
        Condition a = searchCondition(bootstrapTableUtils);
        a = buildCondition(a);
        if (ObjectUtils.isEmpty(a)) {
            SelectJoinStep<Record> selectJoinStep = create.select()
                    .from(STUDENT)
                    .join(USERS)
                    .on(STUDENT.USERNAME.eq(USERS.USERNAME))
                    .join(POLITICAL_LANDSCAPE)
                    .on(STUDENT.POLITICAL_LANDSCAPE_ID.eq(POLITICAL_LANDSCAPE.POLITICAL_LANDSCAPE_ID))
                    .join(ORGANIZE)
                    .on(STUDENT.ORGANIZE_ID.eq(ORGANIZE.ORGANIZE_ID))
                    .join(GRADE)
                    .on(ORGANIZE.GRADE_ID.eq(GRADE.GRADE_ID))
                    .join(SCIENCE)
                    .on(GRADE.SCIENCE_ID.eq(SCIENCE.SCIENCE_ID))
                    .join(DEPARTMENT)
                    .on(SCIENCE.DEPARTMENT_ID.eq(DEPARTMENT.DEPARTMENT_ID))
                    .join(COLLEGE)
                    .on(DEPARTMENT.COLLEGE_ID.eq(COLLEGE.COLLEGE_ID))
                    .join(SCHOOL)
                    .on(COLLEGE.SCHOOL_ID.eq(SCHOOL.SCHOOL_ID));
            sortCondition(bootstrapTableUtils, null, selectJoinStep, JOIN_TYPE);
            records = selectJoinStep.fetch();
        } else {
            SelectConditionStep<Record> selectConditionStep = create.select()
                    .from(STUDENT)
                    .join(USERS)
                    .on(STUDENT.USERNAME.eq(USERS.USERNAME))
                    .join(POLITICAL_LANDSCAPE)
                    .on(STUDENT.POLITICAL_LANDSCAPE_ID.eq(POLITICAL_LANDSCAPE.POLITICAL_LANDSCAPE_ID))
                    .join(ORGANIZE)
                    .on(STUDENT.ORGANIZE_ID.eq(ORGANIZE.ORGANIZE_ID))
                    .join(GRADE)
                    .on(ORGANIZE.GRADE_ID.eq(GRADE.GRADE_ID))
                    .join(SCIENCE)
                    .on(GRADE.SCIENCE_ID.eq(SCIENCE.SCIENCE_ID))
                    .join(DEPARTMENT)
                    .on(SCIENCE.DEPARTMENT_ID.eq(DEPARTMENT.DEPARTMENT_ID))
                    .join(COLLEGE)
                    .on(DEPARTMENT.COLLEGE_ID.eq(COLLEGE.COLLEGE_ID))
                    .join(SCHOOL)
                    .on(COLLEGE.SCHOOL_ID.eq(SCHOOL.SCHOOL_ID))
                    .where(a);
            sortCondition(bootstrapTableUtils, selectConditionStep, null, CONDITION_TYPE);
            records = selectConditionStep.fetch();
        }
        return records;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void save(Student student) {
        studentDao.insert(student);
    }

    @Override
    public void saveWithUsers(StudentBean studentBean) {
        create.transaction(configuration -> {
            DSL.using(configuration)
                    .insertInto(USERS, USERS.USERNAME, USERS.PASSWORD, USERS.USERS_TYPE_ID, USERS.REAL_NAME,
                            USERS.AVATAR, USERS.JOIN_DATE, USERS.ENABLED, USERS.ACCOUNT_NON_EXPIRED,
                            USERS.ACCOUNT_NON_LOCKED, USERS.CREDENTIALS_NON_EXPIRED)
                    .values(studentBean.getStudentNumber(), BCryptUtils.bCryptPassword(studentBean.getStudentNumber()),
                            usersTypeService.findByUsersTypeName(Workbook.STUDENT_USERS_TYPE).getUsersTypeId(),
                            studentBean.getRealName(), Workbook.USERS_AVATAR, DateTimeUtils.getNowSqlDate(),
                            true, true, true, true)
                    .execute();
            DSL.using(configuration)
                    .insertInto(STUDENT, STUDENT.USERNAME, STUDENT.STUDENT_NUMBER, STUDENT.ORGANIZE_ID,
                            STUDENT.SEX, STUDENT.POLITICAL_LANDSCAPE_ID, STUDENT.PLACE_ORIGIN)
                    .values(studentBean.getStudentNumber(), studentBean.getStudentNumber(), studentBean.getOrganizeId(),
                            studentBean.getSex(), studentBean.getPoliticalLandscapeId(), studentBean.getPlaceOrigin())
                    .execute();
        });
    }

    @Override
    public void update(Student student) {
        studentDao.update(student);
    }

    /**
     * 全局搜索条件
     *
     * @param bootstrapTableUtils datatables工具类
     * @return 搜索条件
     */
    @Override
    public Condition searchCondition(BootstrapTableUtils<StudentBean> bootstrapTableUtils) {
        Condition a = null;
        JSONObject search = bootstrapTableUtils.getSearch();
        if (!ObjectUtils.isEmpty(search)) {
            String schoolName = StringUtils.trimWhitespace(search.getString("schoolName"));
            String collegeName = StringUtils.trimWhitespace(search.getString("collegeName"));
            String departmentName = StringUtils.trimWhitespace(search.getString("departmentName"));
            String scienceName = StringUtils.trimWhitespace(search.getString("scienceName"));
            String grade = StringUtils.trimWhitespace(search.getString("grade"));
            String organizeName = StringUtils.trimWhitespace(search.getString("organizeName"));
            String realName = StringUtils.trimWhitespace(search.getString("realName"));
            String studentNumber = StringUtils.trimWhitespace(search.getString("studentNumber"));
            String sex = StringUtils.trimWhitespace(search.getString("sex"));
            if (StringUtils.hasLength(schoolName)) {
                a = SCHOOL.SCHOOL_NAME.like(SQLQueryUtils.likeAllParam(schoolName));
            }

            if (StringUtils.hasLength(collegeName)) {
                if (ObjectUtils.isEmpty(a)) {
                    a = COLLEGE.COLLEGE_NAME.like(SQLQueryUtils.likeAllParam(collegeName));
                } else {
                    a = a.and(COLLEGE.COLLEGE_NAME.like(SQLQueryUtils.likeAllParam(collegeName)));
                }
            }

            if (StringUtils.hasLength(departmentName)) {
                if (ObjectUtils.isEmpty(a)) {
                    a = DEPARTMENT.DEPARTMENT_NAME.like(SQLQueryUtils.likeAllParam(departmentName));
                } else {
                    a = a.and(DEPARTMENT.DEPARTMENT_NAME.like(SQLQueryUtils.likeAllParam(departmentName)));
                }
            }

            if (StringUtils.hasLength(scienceName)) {
                if (ObjectUtils.isEmpty(a)) {
                    a = SCIENCE.SCIENCE_NAME.like(SQLQueryUtils.likeAllParam(scienceName));
                } else {
                    a = a.and(SCIENCE.SCIENCE_NAME.like(SQLQueryUtils.likeAllParam(scienceName)));
                }
            }

            if (StringUtils.hasLength(grade)) {
                if (ObjectUtils.isEmpty(a)) {
                    a = GRADE.GRADE_.like(SQLQueryUtils.likeAllParam(grade));
                } else {
                    a = a.and(GRADE.GRADE_.like(SQLQueryUtils.likeAllParam(grade)));
                }
            }

            if (StringUtils.hasLength(organizeName)) {
                if (ObjectUtils.isEmpty(a)) {
                    a = ORGANIZE.ORGANIZE_NAME.like(SQLQueryUtils.likeAllParam(organizeName));
                } else {
                    a = a.and(ORGANIZE.ORGANIZE_NAME.like(SQLQueryUtils.likeAllParam(organizeName)));
                }
            }

            if (StringUtils.hasLength(realName)) {
                if (ObjectUtils.isEmpty(a)) {
                    a = USERS.REAL_NAME.like(SQLQueryUtils.likeAllParam(realName));
                } else {
                    a = a.and(USERS.REAL_NAME.like(SQLQueryUtils.likeAllParam(realName)));
                }
            }

            if (StringUtils.hasLength(studentNumber)) {
                if (ObjectUtils.isEmpty(a)) {
                    a = STUDENT.STUDENT_NUMBER.like(SQLQueryUtils.likeAllParam(studentNumber));
                } else {
                    a = a.and(STUDENT.STUDENT_NUMBER.like(SQLQueryUtils.likeAllParam(studentNumber)));
                }
            }

            if (StringUtils.hasLength(sex)) {
                if (ObjectUtils.isEmpty(a)) {
                    a = STUDENT.SEX.like(SQLQueryUtils.likeAllParam(sex));
                } else {
                    a = a.and(STUDENT.SEX.like(SQLQueryUtils.likeAllParam(sex)));
                }
            }
        }
        return a;
    }

    /**
     * 数据排序
     *
     * @param bootstrapTableUtils datatables工具类
     * @param selectConditionStep 条件
     */
    @Override
    public void sortCondition(BootstrapTableUtils<StudentBean> bootstrapTableUtils, SelectConditionStep<Record> selectConditionStep, SelectJoinStep<Record> selectJoinStep, int type) {
        String orderColumnName = bootstrapTableUtils.getSortName();
        String orderDir = bootstrapTableUtils.getSortOrder();
        boolean isAsc = "asc".equalsIgnoreCase(orderDir);
        SortField[] sortField = null;
        if (StringUtils.hasLength(orderColumnName)) {
            if ("schoolName".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = SCHOOL.SCHOOL_NAME.asc();
                    sortField[1] = STUDENT.STUDENT_NUMBER.asc();
                } else {
                    sortField[0] = SCHOOL.SCHOOL_NAME.desc();
                    sortField[1] = STUDENT.STUDENT_NUMBER.desc();
                }
            }

            if ("collegeName".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = COLLEGE.COLLEGE_NAME.asc();
                    sortField[1] = STUDENT.STUDENT_NUMBER.asc();
                } else {
                    sortField[0] = COLLEGE.COLLEGE_NAME.desc();
                    sortField[1] = STUDENT.STUDENT_NUMBER.desc();
                }
            }

            if ("departmentName".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = DEPARTMENT.DEPARTMENT_NAME.asc();
                    sortField[1] = STUDENT.STUDENT_NUMBER.asc();
                } else {
                    sortField[0] = DEPARTMENT.DEPARTMENT_NAME.desc();
                    sortField[1] = STUDENT.STUDENT_NUMBER.desc();
                }
            }

            if ("scienceName".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = SCIENCE.SCIENCE_NAME.asc();
                    sortField[1] = STUDENT.STUDENT_NUMBER.asc();
                } else {
                    sortField[0] = SCIENCE.SCIENCE_NAME.desc();
                    sortField[1] = STUDENT.STUDENT_NUMBER.desc();
                }
            }

            if ("grade".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = GRADE.GRADE_.asc();
                    sortField[1] = STUDENT.STUDENT_NUMBER.asc();
                } else {
                    sortField[0] = GRADE.GRADE_.desc();
                    sortField[1] = STUDENT.STUDENT_NUMBER.desc();
                }
            }

            if ("organizeName".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = ORGANIZE.ORGANIZE_NAME.asc();
                    sortField[1] = STUDENT.STUDENT_NUMBER.asc();
                } else {
                    sortField[0] = ORGANIZE.ORGANIZE_NAME.desc();
                    sortField[1] = STUDENT.STUDENT_NUMBER.desc();
                }
            }

            if ("realName".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = USERS.REAL_NAME.asc();
                    sortField[1] = STUDENT.STUDENT_NUMBER.asc();
                } else {
                    sortField[0] = USERS.REAL_NAME.desc();
                    sortField[1] = STUDENT.STUDENT_NUMBER.desc();
                }
            }

            if ("studentNumber".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = STUDENT.STUDENT_NUMBER.asc();
                    sortField[1] = STUDENT.STUDENT_NUMBER.asc();
                } else {
                    sortField[0] = STUDENT.STUDENT_NUMBER.desc();
                    sortField[1] = STUDENT.STUDENT_NUMBER.desc();
                }
            }

            if ("sex".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = STUDENT.SEX.asc();
                    sortField[1] = STUDENT.STUDENT_NUMBER.asc();
                } else {
                    sortField[0] = STUDENT.SEX.desc();
                    sortField[1] = STUDENT.STUDENT_NUMBER.desc();
                }
            }

            if ("politicalLandscapeName".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = POLITICAL_LANDSCAPE.POLITICAL_LANDSCAPE_NAME.asc();
                    sortField[1] = STUDENT.STUDENT_NUMBER.asc();
                } else {
                    sortField[0] = POLITICAL_LANDSCAPE.POLITICAL_LANDSCAPE_NAME.desc();
                    sortField[1] = STUDENT.STUDENT_NUMBER.desc();
                }
            }

            if ("placeOrigin".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = STUDENT.PLACE_ORIGIN.asc();
                    sortField[1] = STUDENT.STUDENT_NUMBER.asc();
                } else {
                    sortField[0] = STUDENT.PLACE_ORIGIN.desc();
                    sortField[1] = STUDENT.STUDENT_NUMBER.desc();
                }
            }

            if ("enabled".equalsIgnoreCase(orderColumnName)) {
                sortField = new SortField[2];
                if (isAsc) {
                    sortField[0] = USERS.ENABLED.asc();
                    sortField[1] = STUDENT.STUDENT_NUMBER.asc();
                } else {
                    sortField[0] = USERS.ENABLED.desc();
                    sortField[1] = STUDENT.STUDENT_NUMBER.desc();
                }
            }
        }
        sortToFinish(selectConditionStep, selectJoinStep, type, sortField);
    }

    private Condition buildCondition(Condition condition) {
        Users users = usersService.getUserFromSession();
        if (Objects.nonNull(condition)) {
            condition = condition.and(USERS.USERNAME.ne(users.getUsername()));
        } else {
            condition = USERS.USERNAME.ne(users.getUsername());
        }

        if (authoritiesService.isCurrentUserInRole(Workbook.ROLE_SYSTEM)) {
            condition = condition.andNotExists(create.selectFrom(AUTHORITIES)
                    .where(AUTHORITIES.USERNAME.eq(USERS.USERNAME)
                            .and(AUTHORITIES.AUTHORITY.in(Workbook.ROLE_SYSTEM))));
        } else if (authoritiesService.isCurrentUserInRole(Workbook.ROLE_COLLEGE_YOUTH_LEAGUE_COMMITTEE)) {
            condition = condition.andNotExists(create.selectFrom(AUTHORITIES)
                    .where(AUTHORITIES.USERNAME.eq(USERS.USERNAME)
                            .and(AUTHORITIES.AUTHORITY.in(
                                    Workbook.ROLE_SYSTEM,
                                    Workbook.ROLE_COLLEGE_YOUTH_LEAGUE_COMMITTEE
                            ))));
        } else if (authoritiesService.isCurrentUserInRole(Workbook.ROLE_COLLEGE_WORK_DEPARTMENT)) {
            condition = condition.andNotExists(create.selectFrom(AUTHORITIES)
                    .where(AUTHORITIES.USERNAME.eq(USERS.USERNAME)
                            .and(AUTHORITIES.AUTHORITY.in(
                                    Workbook.ROLE_SYSTEM,
                                    Workbook.ROLE_COLLEGE_YOUTH_LEAGUE_COMMITTEE,
                                    Workbook.ROLE_COLLEGE_WORK_DEPARTMENT
                            ))));
        } else if (authoritiesService.isCurrentUserInRole(Workbook.ROLE_DEPARTMENT_INSTRUCTOR)) {
            condition = condition.andNotExists(create.selectFrom(AUTHORITIES)
                    .where(AUTHORITIES.USERNAME.eq(USERS.USERNAME)
                            .and(AUTHORITIES.AUTHORITY.in(
                                    Workbook.ROLE_SYSTEM,
                                    Workbook.ROLE_COLLEGE_YOUTH_LEAGUE_COMMITTEE,
                                    Workbook.ROLE_COLLEGE_WORK_DEPARTMENT,
                                    Workbook.ROLE_DEPARTMENT_INSTRUCTOR
                            ))));
        } else if (authoritiesService.isCurrentUserInRole(Workbook.ROLE_HEADMASTER)) {
            condition = condition.andNotExists(create.selectFrom(AUTHORITIES)
                    .where(AUTHORITIES.USERNAME.eq(USERS.USERNAME)
                            .and(AUTHORITIES.AUTHORITY.in(
                                    Workbook.ROLE_SYSTEM,
                                    Workbook.ROLE_COLLEGE_YOUTH_LEAGUE_COMMITTEE,
                                    Workbook.ROLE_COLLEGE_WORK_DEPARTMENT,
                                    Workbook.ROLE_DEPARTMENT_INSTRUCTOR,
                                    Workbook.ROLE_HEADMASTER
                            ))));
        }
        return condition;
    }
}
