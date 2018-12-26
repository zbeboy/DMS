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
        grades: web_path + '/web/data/grade/data',
        grade: web_path + '/web/data/grade/one',
        check_add_grade: web_path + '/web/data/grade/check/add/name',
        check_update_grade: web_path + '/web/data/grade/check/update/name',
        save: web_path + '/web/data/grade/save',
        update: web_path + '/web/data/grade/update',
        status: web_path + '/web/data/grade/status'
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
        grade: '#grade'
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
    grade: ''
};

var dataTable = $('#dataTable');

dataTable.bootstrapTable('destroy')
    .bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: getAjaxUrl().grades, //获取数据的Servlet地址
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

function formatterGradeIsDel(value, row, index, field) {
    var v = "已删除";
    if (!value) {
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
                "name": "编辑",
                "css": "edit",
                "type": "primary",
                "id": row.gradeId,
                "grade": row.grade
            },
            {
                "name": row.gradeIsDel ? "恢复" : "删除",
                "css": row.gradeIsDel ? "recovery" : "del",
                "type": row.gradeIsDel ? "warning" : "danger",
                "id": row.gradeId,
                "grade": row.grade
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

var add_param_id = {
    schoolId: '#addSchoolId',
    collegeId: '#addCollegeId',
    departmentId: '#addDepartmentId',
    scienceId: '#addScienceId',
    grade: '#addGrade'
};

var add_param = {
    schoolId: '',
    collegeId: '',
    departmentId: '',
    scienceId: '',
    grade: ''
};

var add_error_id = {
    schoolId: "#add_school_id_error",
    collegeId: '#add_college_id_error',
    departmentId: '#add_department_id_error',
    scienceId: '#add_science_id_error',
    grade: '#add_grade_error'
};

function initAddParam() {
    add_param.schoolId = $(add_param_id.schoolId).val();
    add_param.collegeId = $(add_param_id.collegeId).val();
    add_param.departmentId = $(add_param_id.departmentId).val();
    add_param.scienceId = $(add_param_id.scienceId).val();
    add_param.grade = $(add_param_id.grade).val();
}

$(add_param_id.schoolId).change(function () {
    var schoolId = $(this).val();
    initColleges(schoolId, add_param_id.collegeId, 0);
    initDepartments(0, add_param_id.departmentId, 0);
    initSciences(0, add_param_id.scienceId, 0);
});

$(add_param_id.collegeId).change(function () {
    var collegeId = $(this).val();
    initDepartments(collegeId, add_param_id.departmentId, 0);
    initSciences(0, add_param_id.scienceId, 0);
});

$(add_param_id.departmentId).change(function () {
    var departmentId = $(this).val();
    initSciences(departmentId, add_param_id.scienceId, 0);
});

function addGrade() {
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
        checkAddGrade();
    } else {
        validErrorDom(add_error_id.scienceId, '请选择专业');
    }
}

function checkAddGrade() {
    var scienceId = add_param.scienceId;
    var grade = add_param.grade;
    if (grade !== '') {
        $.post(getAjaxUrl().check_add_grade, {
            scienceId: scienceId,
            grade: grade
        }, function (data) {
            if (data.state) {
                validSuccessDom(add_error_id.grade);
                sendAddAjax();
            } else {
                validErrorDom(add_error_id.grade, data.msg);
            }
        });
    } else {
        validErrorDom(add_error_id.grade, '年级不能为空');
    }
}

function sendAddAjax() {
    $.post(getAjaxUrl().save, add_param, function (data) {
        if (data.state) {
            $(add_param_id.grade).val('');
            $('#addModal').modal('hide');
            refreshTable();
        } else {
            validErrorDom(add_error_id.grade, data.msg);
        }
    })
}

var edit_param_id = {
    gradeId: '#editGradeId',
    schoolId: '#editSchoolId',
    collegeId: '#editCollegeId',
    departmentId: '#editDepartmentId',
    scienceId: '#editScienceId',
    grade: '#editGrade'
};

var edit_param = {
    gradeId: '',
    collegeId: '',
    schoolId: '',
    departmentId: '',
    scienceId: '',
    grade: ''
};

var edit_error_id = {
    schoolId: '#edit_school_id_error',
    collegeId: '#edit_college_id_error',
    departmentId: '#edit_department_id_error',
    scienceId: '#edit_science_id_error',
    grade: '#editGradeId'
};

function initEditParam() {
    edit_param.gradeId = $(edit_param_id.gradeId).val();
    edit_param.schoolId = $(edit_param_id.schoolId).val();
    edit_param.collegeId = $(edit_param_id.collegeId).val();
    edit_param.departmentId = $(edit_param_id.departmentId).val();
    edit_param.scienceId = $(edit_param_id.scienceId).val();
    edit_param.grade = $(edit_param_id.grade).val();
}

$(edit_param_id.schoolId).change(function () {
    var schoolId = $(this).val();
    initColleges(schoolId, edit_param_id.collegeId, 0);
    initDepartments(0, edit_param_id.departmentId, 0);
    initSciences(0, edit_param_id.scienceId, 0);
});

$(edit_param_id.collegeId).change(function () {
    var collegeId = $(this).val();
    initDepartments(collegeId, edit_param_id.departmentId, 0);
    initSciences(0, edit_param_id.scienceId, 0);
});

$(edit_param_id.departmentId).change(function () {
    var departmentId = $(this).val();
    initSciences(departmentId, edit_param_id.scienceId, 0);
});

function editGrade() {
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
        checkEditGrade();
    } else {
        validErrorDom(edit_error_id.scienceId, '请选择专业');
    }
}

function checkEditGrade() {
    var gradeId = edit_param.gradeId;
    var scienceId = edit_param.scienceId;
    var grade = edit_param.grade;
    if (grade !== '') {
        $.post(getAjaxUrl().check_update_grade, {
            gradeId: gradeId,
            scienceId: scienceId,
            grade: grade
        }, function (data) {
            if (data.state) {
                validSuccessDom(edit_error_id.grade);
                sendEditAjax();
            } else {
                validErrorDom(edit_error_id.grade, data.msg);
            }
        });
    } else {
        validErrorDom(add_error_id.grade, '年级不能为空');
    }
}

function sendEditAjax() {
    $.post(getAjaxUrl().update, edit_param, function (data) {
        if (data.state) {
            $('#editModal').modal('hide');
            refreshTable();
        } else {
            validErrorDom(edit_error_id.grade, data.msg);
        }
    })
}

function delOrRecover(id, name, isDel, message) {
    var msg;
    msg = Messenger().post({
        message: "确定" + message + "年级 '" + name + "'  吗?",
        actions: {
            retry: {
                label: '确定',
                phrase: 'Retrying TIME',
                action: function () {
                    msg.cancel();
                    $.post(getAjaxUrl().status, {gradeId: id, gradeIsDel: isDel}, function (data) {
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

    $('#addSave').click(function () {
        addGrade();
    });

    dataTable.delegate('.edit', "click", function () {
        var gradeId = $(this).attr('data-id');
        $.post(getAjaxUrl().grade, {gradeId: gradeId}, function (data) {
            $(edit_param_id.gradeId).val(data.grade.gradeId);
            $(edit_param_id.schoolId).val(data.grade.schoolId);
            initColleges(data.grade.schoolId, edit_param_id.collegeId, data.grade.collegeId);
            initDepartments(data.grade.collegeId, edit_param_id.departmentId, data.grade.departmentId);
            initSciences(data.grade.departmentId, edit_param_id.scienceId, data.grade.scienceId);
            $(edit_param_id.grade).val(data.grade.grade);
            validSuccessDom(edit_error_id.schoolId);
            validSuccessDom(edit_error_id.collegeId);
            validSuccessDom(edit_error_id.departmentId);
            validSuccessDom(edit_error_id.scienceId);
            validSuccessDom(edit_error_id.grade);
            $('#editModal').modal('show');
        });

    });

    $('#editSave').click(function () {
        editGrade();
    });

    dataTable.delegate('.del', "click", function () {
        delOrRecover($(this).attr('data-id'), $(this).attr('data-grade'), true, '删除');
    });

    dataTable.delegate('.recovery', "click", function () {
        delOrRecover($(this).attr('data-id'), $(this).attr('data-grade'), false, '恢复');
    });
});
