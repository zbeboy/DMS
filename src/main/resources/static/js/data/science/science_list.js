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
        sciences: web_path + '/web/data/science/data',
        science: web_path + '/web/data/science/one',
        check_add_science: web_path + '/web/data/science/check/add/name',
        check_update_science: web_path + '/web/data/science/check/update/name',
        save: web_path + '/web/data/science/save',
        update: web_path + '/web/data/science/update',
        status: web_path + '/web/data/science/status'
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
        scienceName: '#scienceName'
    };
}

/*
参数
*/
var param = {
    schoolName: '',
    collegeName: '',
    departmentName: '',
    scienceName: ''
};

var dataTable = $('#dataTable');

dataTable.bootstrapTable('destroy')
    .bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: getAjaxUrl().sciences, //获取数据的Servlet地址
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

function formatterScienceIsDel(value, row, index, field) {
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
                "id": row.scienceId,
                "science": row.scienceName
            },
            {
                "name": row.scienceIsDel ? "恢复" : "删除",
                "css": row.scienceIsDel ? "recovery" : "del",
                "type": row.scienceIsDel ? "warning" : "danger",
                "id": row.scienceId,
                "science": row.scienceName
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
}

/*
清空参数
*/
function cleanParam() {
    $(getParamId().schoolName).val('');
    $(getParamId().collegeName).val('');
    $(getParamId().departmentName).val('');
    $(getParamId().scienceName).val('');
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

var add_param_id = {
    schoolId: '#addSchoolId',
    collegeId: '#addCollegeId',
    departmentId: '#addDepartmentId',
    scienceName: '#addScienceName'
};

var add_param = {
    schoolId: '',
    collegeId: '',
    departmentId: '',
    scienceName: ''
};

var add_error_id = {
    schoolId: "#add_school_id_error",
    collegeId: '#add_college_id_error',
    departmentId: '#add_department_id_error',
    scienceName: '#add_science_name_error'
};

function initAddParam() {
    add_param.schoolId = $(add_param_id.schoolId).val();
    add_param.collegeId = $(add_param_id.collegeId).val();
    add_param.departmentId = $(add_param_id.departmentId).val();
    add_param.scienceName = $(add_param_id.scienceName).val();
}

$(add_param_id.schoolId).change(function () {
    var schoolId = $(this).val();
    initColleges(schoolId, add_param_id.collegeId, 0);
    initDepartments(0, add_param_id.departmentId, 0);
});

$(add_param_id.collegeId).change(function () {
    var collegeId = $(this).val();
    initDepartments(collegeId, add_param_id.departmentId, 0);
});

function addScience() {
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
        checkAddScienceName();
    } else {
        validErrorDom(add_error_id.departmentId, '请选择系');
    }
}

function checkAddScienceName() {
    var departmentId = add_param.departmentId;
    var scienceName = add_param.scienceName;
    if (scienceName !== '') {
        $.post(getAjaxUrl().check_add_science, {
            departmentId: departmentId,
            scienceName: scienceName
        }, function (data) {
            if (data.state) {
                validSuccessDom(add_error_id.scienceName);
                sendAddAjax();
            } else {
                validErrorDom(add_error_id.scienceName, data.msg);
            }
        });
    } else {
        validErrorDom(add_error_id.scienceName, '专业名不能为空');
    }
}

function sendAddAjax() {
    $.post(getAjaxUrl().save, add_param, function (data) {
        if (data.state) {
            $(add_param_id.scienceName).val('');
            $('#addModal').modal('hide');
            refreshTable();
        } else {
            validErrorDom(add_error_id.scienceName, data.msg);
        }
    })
}

var edit_param_id = {
    scienceId: '#editScienceId',
    schoolId: '#editSchoolId',
    collegeId: '#editCollegeId',
    departmentId: '#editDepartmentId',
    scienceName: '#editScienceName'
};

var edit_param = {
    scienceId: '',
    collegeId: '',
    schoolId: '',
    departmentId: '',
    scienceName: ''
};

var edit_error_id = {
    schoolId: '#edit_school_id_error',
    collegeId: '#edit_college_id_error',
    departmentId: '#edit_department_id_error',
    scienceName: '#edit_science_name_error'
};

function initEditParam() {
    edit_param.scienceId = $(edit_param_id.scienceId).val();
    edit_param.schoolId = $(edit_param_id.schoolId).val();
    edit_param.collegeId = $(edit_param_id.collegeId).val();
    edit_param.departmentId = $(edit_param_id.departmentId).val();
    edit_param.scienceName = $(edit_param_id.scienceName).val();
}

$(edit_param_id.schoolId).change(function () {
    var schoolId = $(this).val();
    initColleges(schoolId, edit_param_id.collegeId, 0);
    initDepartments(0, edit_param_id.departmentId, 0);
});

$(edit_param_id.collegeId).change(function () {
    var collegeId = $(this).val();
    initDepartments(collegeId, edit_param_id.departmentId, 0);
});

function editScience() {
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
        checkEditScienceName();
    } else {
        validErrorDom(edit_error_id.departmentId, '请选择系');
    }
}

function checkEditScienceName() {
    var scienceId = edit_param.scienceId;
    var departmentId = edit_param.departmentId;
    var scienceName = edit_param.scienceName;
    if (scienceName !== '') {
        $.post(getAjaxUrl().check_update_science, {
            scienceId: scienceId,
            departmentId: departmentId,
            scienceName: scienceName
        }, function (data) {
            if (data.state) {
                validSuccessDom(edit_error_id.scienceName);
                sendEditAjax();
            } else {
                validErrorDom(edit_error_id.scienceName, data.msg);
            }
        });
    } else {
        validErrorDom(add_error_id.scienceName, '专业名不能为空');
    }
}

function sendEditAjax() {
    $.post(getAjaxUrl().update, edit_param, function (data) {
        if (data.state) {
            $('#editModal').modal('hide');
            refreshTable();
        } else {
            validErrorDom(edit_error_id.scienceName, data.msg);
        }
    })
}

function delOrRecover(id, name, isDel, message) {
    var msg;
    msg = Messenger().post({
        message: "确定" + message + "专业 '" + name + "'  吗?",
        actions: {
            retry: {
                label: '确定',
                phrase: 'Retrying TIME',
                action: function () {
                    msg.cancel();
                    $.post(getAjaxUrl().status, {scienceId: id, scienceIsDel: isDel}, function (data) {
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
        addScience();
    });

    dataTable.delegate('.edit', "click", function () {
        var scienceId = $(this).attr('data-id');
        $.post(getAjaxUrl().science, {scienceId: scienceId}, function (data) {
            $(edit_param_id.scienceId).val(data.science.scienceId);
            $(edit_param_id.schoolId).val(data.science.schoolId);
            initColleges(data.science.schoolId, edit_param_id.collegeId, data.science.collegeId);
            initDepartments(data.science.collegeId, edit_param_id.departmentId, data.science.departmentId);
            $(edit_param_id.scienceName).val(data.science.scienceName);
            validSuccessDom(edit_error_id.schoolId);
            validSuccessDom(edit_error_id.collegeId);
            validSuccessDom(edit_error_id.departmentId);
            validSuccessDom(edit_error_id.scienceName);
            $('#editModal').modal('show');
        });

    });

    $('#editSave').click(function () {
        editScience();
    });

    dataTable.delegate('.del', "click", function () {
        delOrRecover($(this).attr('data-id'), $(this).attr('data-science'), true, '删除');
    });

    dataTable.delegate('.recovery', "click", function () {
        delOrRecover($(this).attr('data-id'), $(this).attr('data-science'), false, '恢复');
    });
});
