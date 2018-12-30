/**
 * Created by zbeboy 2018-04-13 .
 **/

/*
 ajax url
*/
function getAjaxUrl() {
    return {
        schools: web_path + '/web/data/school/all',
        colleges: web_path + '/web/data/college/all',
        departments: web_path + '/web/data/department/all',
        sciences: web_path + '/web/data/science/all',
        grades: web_path + '/web/data/grade/all',
        organizes: web_path + '/web/data/organize/all',
        politicalLandscapes: web_path + '/web/data/politicalLandscape/all',
        roles: web_path + '/web/data/student/roles',
        students: web_path + '/web/data/student/data',
        student: web_path + '/web/data/student/one',
        export: web_path + '/web/data/student/export',
        import_template: web_path + '/files/import_student.xlsx',
        check_add_student: web_path + '/web/data/student/check/add/number',
        check_update_student: web_path + '/web/data/student/check/update/number',
        save: web_path + '/web/data/student/save',
        save_role: web_path + '/web/data/student/role/save',
        update: web_path + '/web/data/student/update',
        status: web_path + '/web/data/student/status'
    };
}

/*
参数id
*/
function getParamId() {
    return {
        schoolName: '#schoolName',
        collegeName: '#collegeName',
        departmentName: '#departmentName',
        scienceName: '#scienceName',
        grade: '#grade',
        organizeName: '#organizeName',
        realName: '#realName',
        studentNumber: '#studentNumber',
        sex: '#sex'
    };
}

/*
参数
*/
var param = {
    schoolName: '',
    collegeName: '',
    departmentName: '',
    scienceName: '',
    grade: '',
    organizeName: '',
    realName: '',
    studentNumber: '',
    sex: ''
};

var dataTable = $('#dataTable');

dataTable.bootstrapTable('destroy')
    .bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: getAjaxUrl().students, //获取数据的Servlet地址
        pagination: true, //启动分页
        pageSize: 10,  //每页显示的记录数
        pageNumber: 1, //当前第几页
        sidePagination: "server", //表示服务端请求
        toolbar: "#toolbar",
        queryParamsType: "undefined",
        queryParams: function (params) {
            params.extraSearch = JSON.stringify(param);
            return params;
        },
        search: false,
        onLoadError: function () {  //加载失败时执行
            Messenger().post({
                message: '加载数据失败！',
                type: 'error',
                id: 'menuFail',
                showCloseButton: true
            });
        }
    });

function formatterEnabled(value, row, index, field) {
    var v = "已删除";
    if (value) {
        v = "正常";
    }
    return v;
}

// 预编译模板
var template = Handlebars.compile($("#operator_button").html());

function operation(value, row, index, field) {
    var context = {
        func: [
            {
                "name": "设置角色",
                "css": "auth",
                "type": "info",
                "id": row.username,
                "student": row.realNme
            },
            {
                "name": "编辑",
                "css": "edit",
                "type": "primary",
                "id": row.username,
                "student": row.realNme
            },
            {
                "name": row.enabled ? "注销" : "恢复",
                "css": row.enabled ? "del" : "recovery",
                "type": row.enabled ? "danger" : "warning",
                "id": row.username,
                "student": row.realNme
            }
        ]
    };
    return template(context);
}

/*
初始化参数
*/
function initParam() {
    param.schoolName = $(getParamId().schoolName).val();
    param.collegeName = $(getParamId().collegeName).val();
    param.departmentName = $(getParamId().departmentName).val();
    param.scienceName = $(getParamId().scienceName).val();
    param.grade = $(getParamId().grade).val();
    param.organizeName = $(getParamId().organizeName).val();
    param.realName = $(getParamId().realName).val();
    param.studentNumber = $(getParamId().studentNumber).val();
    param.sex = $(getParamId().sex).val();
}

/*
清空参数
*/
function cleanParam() {
    $(getParamId().schoolName).val('');
    $(getParamId().collegeName).val('');
    $(getParamId().departmentName).val('');
    $(getParamId().scienceName).val('');
    $(getParamId().grade).val('');
    $(getParamId().organizeName).val('');
    $(getParamId().realName).val('');
    $(getParamId().studentNumber).val('');
    $(getParamId().sex).val('');
}

/*
刷新数据
 */
function refreshTable() {
    dataTable.bootstrapTable('refresh', {
        silent: true,
        query: {
            extraSearch: JSON.stringify(param)
        }
    });
}

/**
 * 检验成功
 * @param errorMsgId
 */
function validSuccessDom(errorMsgId) {
    $(errorMsgId).addClass('hidden').text('');
}

/**
 * 检验失败
 * @param errorMsgId
 * @param msg
 */
function validErrorDom(errorMsgId, msg) {
    $(errorMsgId).removeClass('hidden').text(msg);
}

init();

function init() {
    initSchools();
    initPoliticalLandscapes();
    initRoles();
}

function initPoliticalLandscapes() {
    $.get(getAjaxUrl().politicalLandscapes, function (data) {
        politicalLandscapesData(data);
    });
}

function politicalLandscapesData(data) {
    $.each(data.politicalLandscapes, function (i, n) {
        $(add_param_id.politicalLandscapeId).append('<option value="' + n.politicalLandscapeId + '">' + n.politicalLandscapeName + '</option>');
        $(edit_param_id.politicalLandscapeId).append('<option value="' + n.politicalLandscapeId + '">' + n.politicalLandscapeName + '</option>');
    });
}

function initRoles() {
    $.get(getAjaxUrl().roles, function (data) {
        rolesData(data);
    });
}

function rolesData(data) {
    var role_template = Handlebars.compile($("#role_template").html());
    $('#roleData').append(role_template(data));
}

function initSchools() {
    $.get(getAjaxUrl().schools, function (data) {
        schoolsData(data);
    });
}

function schoolsData(data) {
    $.each(data.schools, function (i, n) {
        $(add_param_id.schoolId).append('<option value="' + n.schoolId + '">' + n.schoolName + '</option>');
        $(edit_param_id.schoolId).append('<option value="' + n.schoolId + '">' + n.schoolName + '</option>');
    });
}

function initColleges(schoolId, targetId, collegeId) {
    if (Number(schoolId) > 0) {
        $.get(getAjaxUrl().colleges, {schoolId: schoolId},
            function (data) {
                collegesData(data, targetId, collegeId);
            }
        );
    } else {
        $(targetId).html('<option value="">请选择院</option>');
    }

}

function collegesData(data, targetId, collegeId) {
    $(targetId).html('<option value="">请选择院</option>');
    $.each(data.colleges, function (i, n) {
        $(targetId).append('<option value="' + n.collegeId + '">' + n.collegeName + '</option>');
    });

    if (Number(collegeId) > 0) {
        $(targetId).val(collegeId);
    }
}

function initDepartments(collegeId, targetId, departmentId) {
    if (Number(collegeId) > 0) {
        $.get(getAjaxUrl().departments, {collegeId: collegeId},
            function (data) {
                departmentsData(data, targetId, departmentId);
            }
        );
    } else {
        $(targetId).html('<option value="">请选择系</option>');
    }
}

function departmentsData(data, targetId, departmentId) {
    $(targetId).html('<option value="">请选择系</option>');
    $.each(data.departments, function (i, n) {
        $(targetId).append('<option value="' + n.departmentId + '">' + n.departmentName + '</option>');
    });

    if (Number(departmentId) > 0) {
        $(targetId).val(departmentId);
    }
}

function initSciences(departmentId, targetId, scienceId) {
    if (Number(departmentId) > 0) {
        $.get(getAjaxUrl().sciences, {departmentId: departmentId},
            function (data) {
                sciencesData(data, targetId, scienceId);
            }
        );
    } else {
        $(targetId).html('<option value="">请选择专业</option>');
    }
}

function sciencesData(data, targetId, scienceId) {
    $(targetId).html('<option value="">请选择专业</option>');
    $.each(data.sciences, function (i, n) {
        $(targetId).append('<option value="' + n.scienceId + '">' + n.scienceName + '</option>');
    });

    if (Number(scienceId) > 0) {
        $(targetId).val(scienceId);
    }
}

function initGrades(scienceId, targetId, gradeId) {
    if (Number(scienceId) > 0) {
        $.get(getAjaxUrl().grades, {scienceId: scienceId},
            function (data) {
                gradesData(data, targetId, gradeId);
            }
        );
    } else {
        $(targetId).html('<option value="">请选择年级</option>');
    }
}

function gradesData(data, targetId, gradeId) {
    $(targetId).html('<option value="">请选择年级</option>');
    $.each(data.grades, function (i, n) {
        $(targetId).append('<option value="' + n.gradeId + '">' + n.grade + '</option>');
    });

    if (Number(gradeId) > 0) {
        $(targetId).val(gradeId);
    }
}

function initOrganizes(gradeId, targetId, organizeId) {
    if (Number(gradeId) > 0) {
        $.get(getAjaxUrl().organizes, {gradeId: gradeId},
            function (data) {
                organizesData(data, targetId, organizeId);
            }
        );
    } else {
        $(targetId).html('<option value="">请选择班级</option>');
    }
}

function organizesData(data, targetId, organizeId) {
    $(targetId).html('<option value="">请选择班级</option>');
    $.each(data.organizes, function (i, n) {
        $(targetId).append('<option value="' + n.organizeId + '">' + n.organizeName + '</option>');
    });

    if (Number(organizeId) > 0) {
        $(targetId).val(organizeId);
    }
}

var add_param_id = {
    schoolId: '#addSchoolId',
    collegeId: '#addCollegeId',
    departmentId: '#addDepartmentId',
    scienceId: '#addScienceId',
    gradeId: '#addGradeId',
    organizeId: '#addOrganizeId',
    realName: '#addRealName',
    studentNumber: '#addStudentNumber',
    sex: '#addSex',
    politicalLandscapeId: '#addPoliticalLandscapeId',
    placeOrigin: '#addPlaceOrigin'
};

var add_param = {
    schoolId: '',
    collegeId: '',
    departmentId: '',
    scienceId: '',
    gradeId: '',
    organizeId: '',
    realName: '',
    studentNumber: '',
    sex: '',
    politicalLandscapeId: '',
    placeOrigin: ''
};

var add_error_id = {
    schoolId: "#add_school_id_error",
    collegeId: '#add_college_id_error',
    departmentId: '#add_department_id_error',
    scienceId: '#add_science_id_error',
    gradeId: '#add_grade_id_error',
    organizeId: '#add_organize_id_error',
    realName: '#add_real_name_error',
    studentNumber: '#add_student_number_error',
    sex: '#add_sex_error',
    politicalLandscapeId: '#add_political_landscape_id_error',
    placeOrigin: '#add_place_origin_error'
};

function initAddParam() {
    add_param.schoolId = $(add_param_id.schoolId).val();
    add_param.collegeId = $(add_param_id.collegeId).val();
    add_param.departmentId = $(add_param_id.departmentId).val();
    add_param.scienceId = $(add_param_id.scienceId).val();
    add_param.gradeId = $(add_param_id.gradeId).val();
    add_param.organizeId = $(add_param_id.organizeId).val();
    add_param.realName = $(add_param_id.realName).val();
    add_param.studentNumber = $(add_param_id.studentNumber).val();
    add_param.sex = $(add_param_id.sex).val();
    add_param.politicalLandscapeId = $(add_param_id.politicalLandscapeId).val();
    add_param.placeOrigin = $(add_param_id.placeOrigin).val();
}

$(add_param_id.schoolId).change(function () {
    var schoolId = $(this).val();
    initColleges(schoolId, add_param_id.collegeId, 0);
    initDepartments(0, add_param_id.departmentId, 0);
    initSciences(0, add_param_id.scienceId, 0);
    initGrades(0, add_param_id.gradeId, 0);
    initOrganizes(0, add_param_id.organizeId, 0);
});

$(add_param_id.collegeId).change(function () {
    var collegeId = $(this).val();
    initDepartments(collegeId, add_param_id.departmentId, 0);
    initSciences(0, add_param_id.scienceId, 0);
    initGrades(0, add_param_id.gradeId, 0);
    initOrganizes(0, add_param_id.organizeId, 0);
});

$(add_param_id.departmentId).change(function () {
    var departmentId = $(this).val();
    initSciences(departmentId, add_param_id.scienceId, 0);
    initGrades(0, add_param_id.gradeId, 0);
    initOrganizes(0, add_param_id.organizeId, 0);
});

$(add_param_id.scienceId).change(function () {
    var scienceId = $(this).val();
    initGrades(scienceId, add_param_id.gradeId, 0);
    initOrganizes(0, add_param_id.organizeId, 0);
});

$(add_param_id.gradeId).change(function () {
    var gradeId = $(this).val();
    initOrganizes(gradeId, add_param_id.organizeId, 0);
});

function addStudent() {
    initAddParam();
    checkAddSchoolId();
}

function checkAddSchoolId() {
    var schoolId = add_param.schoolId;
    if (Number(schoolId) !== 0) {
        validSuccessDom(add_error_id.schoolId);
        checkAddCollegeId();
    } else {
        validErrorDom(add_error_id.schoolId, '请选择学校');
    }
}

function checkAddCollegeId() {
    var collegeId = add_param.collegeId;
    if (Number(collegeId) !== 0) {
        validSuccessDom(add_error_id.collegeId);
        checkAddDepartmentId();
    } else {
        validErrorDom(add_error_id.collegeId, '请选择院');
    }
}

function checkAddDepartmentId() {
    var departmentId = add_param.departmentId;
    if (Number(departmentId) !== 0) {
        validSuccessDom(add_error_id.departmentId);
        checkAddScienceId();
    } else {
        validErrorDom(add_error_id.departmentId, '请选择系');
    }
}

function checkAddScienceId() {
    var scienceId = add_param.scienceId;
    if (Number(scienceId) !== 0) {
        validSuccessDom(add_error_id.scienceId);
        checkAddGradeId();
    } else {
        validErrorDom(add_error_id.scienceId, '请选择专业');
    }
}

function checkAddGradeId() {
    var gradeId = add_param.gradeId;
    if (Number(gradeId) !== 0) {
        validSuccessDom(add_error_id.gradeId);
        checkAddOrganizeId();
    } else {
        validErrorDom(add_error_id.gradeId, '请选择年级');
    }
}

function checkAddOrganizeId() {
    var organizeId = add_param.organizeId;
    if (Number(organizeId) !== 0) {
        validSuccessDom(add_error_id.organizeId);
        checkAddRealName();
    } else {
        validErrorDom(add_error_id.organizeId, '请选择班级');
    }
}

function checkAddRealName() {
    var realName = add_param.realName;
    if (realName !== '') {
        validSuccessDom(add_error_id.realName);
        checkAddStudentNumber();
    } else {
        validErrorDom(add_error_id.realName, '姓名不能为空');
    }
}

function checkAddStudentNumber() {
    var studentNumber = add_param.studentNumber;
    if (studentNumber !== '') {
        $.post(getAjaxUrl().check_add_student, {
            studentNumber: studentNumber
        }, function (data) {
            if (data.state) {
                validSuccessDom(add_error_id.studentNumber);
                sendAddAjax();
            } else {
                validErrorDom(add_error_id.studentNumber, data.msg);
            }
        });
    } else {
        validErrorDom(add_error_id.studentNumber, '学号不能为空');
    }
}

function sendAddAjax() {
    $.post(getAjaxUrl().save, add_param, function (data) {
        if (data.state) {
            $('#addModal').modal('hide');
            refreshTable();
        } else {
            validErrorDom(add_error_id.placeOrigin, data.msg);
        }
    })
}

var edit_param_id = {
    username: '#username',
    studentId: '#studentId',
    schoolId: '#editSchoolId',
    collegeId: '#editCollegeId',
    departmentId: '#editDepartmentId',
    scienceId: '#editScienceId',
    gradeId: '#editGradeId',
    organizeId: '#editOrganizeId',
    realName: '#editRealName',
    studentNumber: '#editStudentNumber',
    sex: '#editSex',
    politicalLandscapeId: '#editPoliticalLandscapeId',
    placeOrigin: '#editPlaceOrigin'
};

var edit_param = {
    username: '',
    studentId: '',
    collegeId: '',
    schoolId: '',
    departmentId: '',
    scienceId: '',
    gradeId: '',
    organizeId: '',
    realName: '',
    studentNumber: '',
    sex: '',
    politicalLandscapeId: '',
    placeOrigin: ''
};

var edit_error_id = {
    schoolId: '#edit_school_id_error',
    collegeId: '#edit_college_id_error',
    departmentId: '#edit_department_id_error',
    scienceId: '#edit_science_id_error',
    gradeId: '#edit_grade_id_error',
    organizeId: '#edit_organize_id_error',
    realName: '#edit_real_name_error',
    studentNumber: '#edit_student_number_error',
    sex: '#edit_sex_error',
    politicalLandscapeId: '#edit_political_landscape_id_error',
    placeOrigin: '#edit_place_origin_error'
};

function initEditParam() {
    edit_param.username = $(edit_param_id.username).val();
    edit_param.studentId = $(edit_param_id.studentId).val();
    edit_param.schoolId = $(edit_param_id.schoolId).val();
    edit_param.collegeId = $(edit_param_id.collegeId).val();
    edit_param.departmentId = $(edit_param_id.departmentId).val();
    edit_param.scienceId = $(edit_param_id.scienceId).val();
    edit_param.gradeId = $(edit_param_id.gradeId).val();
    edit_param.organizeId = $(edit_param_id.organizeId).val();
    edit_param.realName = $(edit_param_id.realName).val();
    edit_param.studentNumber = $(edit_param_id.studentNumber).val();
    edit_param.sex = $(edit_param_id.sex).val();
    edit_param.politicalLandscapeId = $(edit_param_id.politicalLandscapeId).val();
    edit_param.placeOrigin = $(edit_param_id.placeOrigin).val();
}

$(edit_param_id.schoolId).change(function () {
    var schoolId = $(this).val();
    initColleges(schoolId, edit_param_id.collegeId, 0);
    initDepartments(0, edit_param_id.departmentId, 0);
    initSciences(0, edit_param_id.scienceId, 0);
    initGrades(0, edit_param_id.gradeId, 0);
    initOrganizes(0, edit_param_id.organizeId, 0);
});

$(edit_param_id.collegeId).change(function () {
    var collegeId = $(this).val();
    initDepartments(collegeId, edit_param_id.departmentId, 0);
    initSciences(0, edit_param_id.scienceId, 0);
    initGrades(0, edit_param_id.gradeId, 0);
    initOrganizes(0, edit_param_id.organizeId, 0);
});

$(edit_param_id.departmentId).change(function () {
    var departmentId = $(this).val();
    initSciences(departmentId, edit_param_id.scienceId, 0);
    initGrades(0, edit_param_id.gradeId, 0);
    initOrganizes(0, edit_param_id.organizeId, 0);
});

$(edit_param_id.scienceId).change(function () {
    var scienceId = $(this).val();
    initGrades(scienceId, edit_param_id.gradeId, 0);
    initOrganizes(0, edit_param_id.organizeId, 0);
});

$(edit_param_id.organizeId).change(function () {
    var organizeId = $(this).val();
    initOrganizes(organizeId, edit_param_id.organizeId, 0);
});

function editStudent() {
    initEditParam();
    checkEditSchoolId();
}

function checkEditSchoolId() {
    var schoolId = edit_param.schoolId;
    if (Number(schoolId) !== 0) {
        validSuccessDom(edit_error_id.schoolId);
        checkEditCollegeId();
    } else {
        validErrorDom(edit_error_id.schoolId, '请选择学校');
    }
}

function checkEditCollegeId() {
    var collegeId = edit_param.collegeId;
    if (Number(collegeId) !== 0) {
        validSuccessDom(edit_error_id.collegeId);
        checkEditDepartmentId();
    } else {
        validErrorDom(edit_error_id.collegeId, '请选择院');
    }
}

function checkEditDepartmentId() {
    var departmentId = edit_param.departmentId;
    if (Number(departmentId) !== 0) {
        validSuccessDom(edit_error_id.departmentId);
        checkEditScienceId();
    } else {
        validErrorDom(edit_error_id.departmentId, '请选择系');
    }
}

function checkEditScienceId() {
    var scienceId = edit_param.scienceId;
    if (Number(scienceId) !== 0) {
        validSuccessDom(edit_error_id.scienceId);
        checkEditGradeId();
    } else {
        validErrorDom(edit_error_id.scienceId, '请选择专业');
    }
}

function checkEditGradeId() {
    var gradeId = edit_param.gradeId;
    if (Number(gradeId) !== 0) {
        validSuccessDom(edit_error_id.gradeId);
        checkEditOrganizeId();
    } else {
        validErrorDom(edit_error_id.gradeId, '请选择年级');
    }
}

function checkEditOrganizeId() {
    var organizeId = edit_param.organizeId;
    if (Number(organizeId) !== 0) {
        validSuccessDom(edit_error_id.organizeId);
        checkEditRealName();
    } else {
        validErrorDom(edit_error_id.organizeId, '请选择班级');
    }
}

function checkEditRealName() {
    var realName = edit_param.realName;
    if (realName !== '') {
        validSuccessDom(edit_error_id.realName);
        checkEditStudentNumber();
    } else {
        validErrorDom(add_error_id.realName, '姓名不能为空');
    }
}

function checkEditStudentNumber() {
    var studentNumber = edit_param.studentNumber;
    var studentId = edit_param.studentId;
    if (studentNumber !== '') {
        $.post(getAjaxUrl().check_update_student, {
            studentId: studentId,
            studentNumber: studentNumber
        }, function (data) {
            if (data.state) {
                validSuccessDom(edit_error_id.studentNumber);
                sendEditAjax();
            } else {
                validErrorDom(edit_error_id.studentNumber, data.msg);
            }
        });
    } else {
        validErrorDom(add_error_id.studentNumber, '学号不能为空');
    }
}

function sendEditAjax() {
    $.post(getAjaxUrl().update, edit_param, function (data) {
        if (data.state) {
            $('#editModal').modal('hide');
            refreshTable();
        } else {
            validErrorDom(edit_error_id.placeOrigin, data.msg);
        }
    })
}

function delOrRecover(id, name, isDel, message) {
    var msg;
    msg = Messenger().post({
        message: "确定" + message + "学生 '" + name + "'  吗?",
        actions: {
            retry: {
                label: '确定',
                phrase: 'Retrying TIME',
                action: function () {
                    msg.cancel();
                    $.post(getAjaxUrl().status, {username: id, enabled: isDel}, function (data) {
                        refreshTable();
                        Messenger().post({
                            message: data.msg,
                            type: data.state ? 'info' : 'error',
                            showCloseButton: true
                        });
                    });
                }
            },
            cancel: {
                label: '取消',
                action: function () {
                    return msg.cancel();
                }
            }
        }
    });
}

function authStudent() {
    $.post(getAjaxUrl().save_role,$('#roleData').serialize(),function (data) {
        $('#authModal').modal('hide');
        refreshTable();
        Messenger().post({
            message: data.msg,
            type: data.state ? 'info' : 'error',
            showCloseButton: true
        });
    });
}

$(document).ready(function () {
    /*
    init message.
    */
    Messenger.options = {
        extraClasses: 'messenger-fixed messenger-on-bottom messenger-on-right',
        theme: 'air'
    };

    $('#search').click(function () {
        initParam();
        refreshTable();
    });

    $('#reset_search').click(function () {
        cleanParam();
        initParam();
        refreshTable();
    });

    $('#add').click(function () {
        $('#addModal').modal('show');
    });

    $('#export').click(function () {
        var exportConfig = dataTable.bootstrapTable('getOptions');
        window.location.href = encodeURI(getAjaxUrl().export + '?' +
            'sortName=' + exportConfig.sortName + '&sortOrder=' + exportConfig.sortOrder +
            '&extraSearch=' + JSON.stringify(param));
    });

    $('#importTemplate').click(function () {
        window.location.href = getAjaxUrl().import_template;
    });

    $('#addSave').click(function () {
        addStudent();
    });

    dataTable.delegate('.edit', "click", function () {
        var username = $(this).attr('data-id');
        $.post(getAjaxUrl().student, {username: username}, function (data) {
            $(edit_param_id.username).val(data.student.username);
            $(edit_param_id.studentId).val(data.student.studentId);
            $(edit_param_id.schoolId).val(data.student.schoolId);
            initColleges(data.student.schoolId, edit_param_id.collegeId, data.student.collegeId);
            initDepartments(data.student.collegeId, edit_param_id.departmentId, data.student.departmentId);
            initSciences(data.student.departmentId, edit_param_id.scienceId, data.student.scienceId);
            initGrades(data.student.scienceId, edit_param_id.gradeId, data.student.gradeId);
            initOrganizes(data.student.gradeId, edit_param_id.organizeId, data.student.organizeId);
            $(edit_param_id.realName).val(data.student.realName);
            $(edit_param_id.studentNumber).val(data.student.studentNumber);
            $(edit_param_id.sex).val(data.student.sex);
            $(edit_param_id.politicalLandscapeId).val(data.student.politicalLandscapeId);
            $(edit_param_id.placeOrigin).val(data.student.placeOrigin);
            validSuccessDom(edit_error_id.schoolId);
            validSuccessDom(edit_error_id.collegeId);
            validSuccessDom(edit_error_id.departmentId);
            validSuccessDom(edit_error_id.scienceId);
            validSuccessDom(edit_error_id.gradeId);
            validSuccessDom(edit_error_id.realName);
            validSuccessDom(edit_error_id.studentNumber);
            validSuccessDom(edit_error_id.sex);
            validSuccessDom(edit_error_id.politicalLandscapeId);
            validSuccessDom(edit_error_id.placeOrigin);
            $('#editModal').modal('show');
        });

    });

    $('#editSave').click(function () {
        editStudent();
    });

    $('#authSave').click(function () {
        authStudent();
    });

    dataTable.delegate('.del', "click", function () {
        delOrRecover($(this).attr('data-id'), $(this).attr('data-student'), true, '注销');
    });

    dataTable.delegate('.recovery', "click", function () {
        delOrRecover($(this).attr('data-id'), $(this).attr('data-student'), false, '恢复');
    });

    dataTable.delegate('.auth', "click", function () {
        var username = $(this).attr('data-id');
        $('#authUsername').val(username);
        $('#authModal').modal('show');
    });
});
