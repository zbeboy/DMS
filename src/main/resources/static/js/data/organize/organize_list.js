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
        organizes: web_path + '/web/data/organize/data',
        staffs: web_path + '/web/data/staff/all',
        organize: web_path + '/web/data/organize/one',
        check_add_organize: web_path + '/web/data/organize/check/add/name',
        check_update_organize: web_path + '/web/data/organize/check/update/name',
        save: web_path + '/web/data/organize/save',
        update: web_path + '/web/data/organize/update',
        status: web_path + '/web/data/organize/status'
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
        organizeName: '#organizeName'
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
    organizeName: ''
};

var dataTable = $('#dataTable');

dataTable.bootstrapTable('destroy')
    .bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: getAjaxUrl().organizes, //获取数据的Servlet地址
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

function formatterOrganizeIsDel(value, row, index, field) {
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
                "id": row.organizeId,
                "organize": row.organizeName
            },
            {
                "name": row.organizeIsDel ? "恢复" : "删除",
                "css": row.organizeIsDel ? "recovery" : "del",
                "type": row.organizeIsDel ? "warning" : "danger",
                "id": row.organizeId,
                "organize": row.organizeName
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
    initStaffs();
}

function initSchools() {
    $.get(getAjaxUrl().schools, function (data) {
        schoolsData(data);
    });
}

function initStaffs() {
    $.get(getAjaxUrl().staffs, function (data) {
        staffsData(data);
    });
}

function schoolsData(data) {
    $.each(data.schools, function (i, n) {
        $(add_param_id.schoolId).append('<option value="' + n.schoolId + '">' + n.schoolName + '</option>');
        $(edit_param_id.schoolId).append('<option value="' + n.schoolId + '">' + n.schoolName + '</option>');
    });
}

function staffsData(data) {
    $.each(data.staffs, function (i, n) {
        $(add_param_id.staffId).append('<option value="' + n.staffId + '">' + n.realName + '</option>');
        $(edit_param_id.staffId).append('<option value="' + n.staffId + '">' + n.realName + '</option>');
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

var add_param_id = {
    schoolId: '#addSchoolId',
    collegeId: '#addCollegeId',
    departmentId: '#addDepartmentId',
    scienceId: '#addScienceId',
    gradeId: '#addGradeId',
    staffId: '#addStaffId',
    organizeName: '#addOrganizeName'
};

var add_param = {
    schoolId: '',
    collegeId: '',
    departmentId: '',
    scienceId: '',
    gradeId: '',
    staffId: 0,
    organizeName: ''
};

var add_error_id = {
    schoolId: "#add_school_id_error",
    collegeId: '#add_college_id_error',
    departmentId: '#add_department_id_error',
    scienceId: '#add_science_id_error',
    gradeId: '#add_grade_id_error',
    staffId: '#add_staff_id_error',
    organizeName: '#add_organize_name_error'
};

function initAddParam() {
    add_param.schoolId = $(add_param_id.schoolId).val();
    add_param.collegeId = $(add_param_id.collegeId).val();
    add_param.departmentId = $(add_param_id.departmentId).val();
    add_param.scienceId = $(add_param_id.scienceId).val();
    add_param.gradeId = $(add_param_id.gradeId).val();
    add_param.staffId = $(add_param_id.staffId).val();
    add_param.organizeName = $(add_param_id.organizeName).val();
}

$(add_param_id.schoolId).change(function () {
    var schoolId = $(this).val();
    initColleges(schoolId, add_param_id.collegeId, 0);
    initDepartments(0, add_param_id.departmentId, 0);
    initSciences(0, add_param_id.scienceId, 0);
    initGrades(0, add_param_id.gradeId, 0);
});

$(add_param_id.collegeId).change(function () {
    var collegeId = $(this).val();
    initDepartments(collegeId, add_param_id.departmentId, 0);
    initSciences(0, add_param_id.scienceId, 0);
    initGrades(0, add_param_id.gradeId, 0);
});

$(add_param_id.departmentId).change(function () {
    var departmentId = $(this).val();
    initSciences(departmentId, add_param_id.scienceId, 0);
    initGrades(0, add_param_id.gradeId, 0);
});

$(add_param_id.scienceId).change(function () {
    var scienceId = $(this).val();
    initGrades(scienceId, add_param_id.gradeId, 0);
});

function addOrganize() {
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
        checkAddOrganizeName();
    } else {
        validErrorDom(add_error_id.gradeId, '请选择年级');
    }
}

function checkAddOrganizeName() {
    var gradeId = add_param.gradeId;
    var organizeName = add_param.organizeName;
    if (organizeName !== '') {
        $.post(getAjaxUrl().check_add_organize, {
            gradeId: gradeId,
            organizeName: organizeName
        }, function (data) {
            if (data.state) {
                validSuccessDom(add_error_id.organizeName);
                sendAddAjax();
            } else {
                validErrorDom(add_error_id.organizeName, data.msg);
            }
        });
    } else {
        validErrorDom(add_error_id.organizeName, '班级名不能为空');
    }
}

function sendAddAjax() {
    $.post(getAjaxUrl().save, add_param, function (data) {
        if (data.state) {
            $(add_param_id.organizeName).val('');
            $('#addModal').modal('hide');
            refreshTable();
        } else {
            validErrorDom(add_error_id.organizeName, data.msg);
        }
    })
}

var edit_param_id = {
    organizeId: '#editOrganizeId',
    schoolId: '#editSchoolId',
    collegeId: '#editCollegeId',
    departmentId: '#editDepartmentId',
    scienceId: '#editScienceId',
    gradeId: '#editGradeId',
    staffId: '#editStaffId',
    organizeName: '#editOrganizeName'
};

var edit_param = {
    organizeId: '',
    collegeId: '',
    schoolId: '',
    departmentId: '',
    scienceId: '',
    gradeId: '',
    staffId: 0,
    organizeName: ''
};

var edit_error_id = {
    schoolId: '#edit_school_id_error',
    collegeId: '#edit_college_id_error',
    departmentId: '#edit_department_id_error',
    scienceId: '#edit_science_id_error',
    gradeId: '#edit_grade_id_error',
    staffId: 'edit_staff_id_error',
    organizeName: '#edit_organize_name_error'
};

function initEditParam() {
    edit_param.organizeId = $(edit_param_id.organizeId).val();
    edit_param.schoolId = $(edit_param_id.schoolId).val();
    edit_param.collegeId = $(edit_param_id.collegeId).val();
    edit_param.departmentId = $(edit_param_id.departmentId).val();
    edit_param.scienceId = $(edit_param_id.scienceId).val();
    edit_param.gradeId = $(edit_param_id.gradeId).val();
    edit_param.staffId = $(edit_param_id.staffId).val();
    edit_param.organizeName = $(edit_param_id.organizeName).val();
}

$(edit_param_id.schoolId).change(function () {
    var schoolId = $(this).val();
    initColleges(schoolId, edit_param_id.collegeId, 0);
    initDepartments(0, edit_param_id.departmentId, 0);
    initSciences(0, edit_param_id.scienceId, 0);
    initGrades(0, edit_param_id.gradeId, 0);
});

$(edit_param_id.collegeId).change(function () {
    var collegeId = $(this).val();
    initDepartments(collegeId, edit_param_id.departmentId, 0);
    initSciences(0, edit_param_id.scienceId, 0);
    initGrades(0, edit_param_id.gradeId, 0);
});

$(edit_param_id.departmentId).change(function () {
    var departmentId = $(this).val();
    initSciences(departmentId, edit_param_id.scienceId, 0);
    initGrades(0, edit_param_id.gradeId, 0);
});

$(edit_param_id.scienceId).change(function () {
    var scienceId = $(this).val();
    initGrades(scienceId, edit_param_id.gradeId, 0);
});

function editOrganize() {
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
        checkEditOrganizeName();
    } else {
        validErrorDom(edit_error_id.gradeId, '请选择年级');
    }
}

function checkEditOrganizeName() {
    var organizeId = edit_param.organizeId;
    var gradeId = edit_param.gradeId;
    var organizeName = edit_param.organizeName;
    if (organizeName !== '') {
        $.post(getAjaxUrl().check_update_organize, {
            organizeId: organizeId,
            gradeId: gradeId,
            organizeName: organizeName
        }, function (data) {
            if (data.state) {
                validSuccessDom(edit_error_id.organizeName);
                sendEditAjax();
            } else {
                validErrorDom(edit_error_id.organizeName, data.msg);
            }
        });
    } else {
        validErrorDom(add_error_id.organizeName, '班级名不能为空');
    }
}

function sendEditAjax() {
    $.post(getAjaxUrl().update, edit_param, function (data) {
        if (data.state) {
            $('#editModal').modal('hide');
            refreshTable();
        } else {
            validErrorDom(edit_error_id.organizeName, data.msg);
        }
    })
}

function delOrRecover(id, name, isDel, message) {
    var msg;
    msg = Messenger().post({
        message: "确定" + message + "班级 '" + name + "'  吗?",
        actions: {
            retry: {
                label: '确定',
                phrase: 'Retrying TIME',
                action: function () {
                    msg.cancel();
                    $.post(getAjaxUrl().status, {organizeId: id, organizeIsDel: isDel}, function (data) {
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
        addOrganize();
    });

    dataTable.delegate('.edit', "click", function () {
        var organizeId = $(this).attr('data-id');
        $.post(getAjaxUrl().organize, {organizeId: organizeId}, function (data) {
            $(edit_param_id.organizeId).val(data.organize.organizeId);
            $(edit_param_id.schoolId).val(data.organize.schoolId);
            $(edit_param_id.staffId).val(data.organize.staffId);
            initColleges(data.organize.schoolId, edit_param_id.collegeId, data.organize.collegeId);
            initDepartments(data.organize.collegeId, edit_param_id.departmentId, data.organize.departmentId);
            initSciences(data.organize.departmentId, edit_param_id.scienceId, data.organize.scienceId);
            initGrades(data.organize.scienceId, edit_param_id.gradeId, data.organize.gradeId);
            $(edit_param_id.organizeName).val(data.organize.organizeName);
            validSuccessDom(edit_error_id.schoolId);
            validSuccessDom(edit_error_id.collegeId);
            validSuccessDom(edit_error_id.departmentId);
            validSuccessDom(edit_error_id.scienceId);
            validSuccessDom(edit_error_id.gradeId);
            validSuccessDom(edit_error_id.organizeName);
            $('#editModal').modal('show');
        });

    });

    $('#editSave').click(function () {
        editOrganize();
    });

    dataTable.delegate('.del', "click", function () {
        delOrRecover($(this).attr('data-id'), $(this).attr('data-organize'), true, '删除');
    });

    dataTable.delegate('.recovery', "click", function () {
        delOrRecover($(this).attr('data-id'), $(this).attr('data-organize'), false, '恢复');
    });
});
