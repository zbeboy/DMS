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
        departments: web_path + '/web/data/department/data',
        department: web_path + '/web/data/department/one',
        check_add_department: web_path + '/web/data/department/check/add/name',
        check_update_department: web_path + '/web/data/department/check/update/name',
        save: web_path + '/web/data/department/save',
        update: web_path + '/web/data/department/update',
        status: web_path + '/web/data/department/status'
    };
}

/*
参数id
*/
function getParamId() {
    return {
        schoolName: '#schoolName',
        collegeName: '#collegeName',
        departmentName: '#departmentName'
    };
}

/*
参数
*/
var param = {
    schoolName: '',
    collegeName: '',
    departmentName: ''
};

var dataTable = $('#dataTable');

dataTable.bootstrapTable('destroy')
    .bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: getAjaxUrl().departments, //获取数据的Servlet地址
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

function formatterDepartmentIsDel(value, row, index, field) {
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
                "id": row.departmentId,
                "department": row.departmentName
            },
            {
                "name": row.departmentIsDel ? "恢复" : "删除",
                "css": row.departmentIsDel ? "recovery" : "del",
                "type": row.departmentIsDel ? "warning" : "danger",
                "id": row.departmentId,
                "department": row.departmentName
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
}

/*
清空参数
*/
function cleanParam() {
    $(getParamId().schoolName).val('');
    $(getParamId().collegeName).val('');
    $(getParamId().departmentName).val('');
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
    $.get(getAjaxUrl().colleges, {schoolId: schoolId},
        function (data) {
            collegesData(data, targetId, collegeId);
        }
    );
}

function collegesData(data, targetId, collegeId) {
    $(targetId).html('<option value="">请选择院</option>');
    $.each(data.colleges, function (i, n) {
        $(targetId).append('<option value="' + n.collegeId + '">' + n.collegeName + '</option>');
    });

    if(Number(collegeId) > 0) {
        $(targetId).val(collegeId);
    }
}

var add_param_id = {
    schoolId: '#addSchoolId',
    collegeId: '#addCollegeId',
    departmentName: '#addDepartmentName'
};

var add_param = {
    schoolId: '',
    collegeId: '',
    departmentName: ''
};

var add_error_id = {
    schoolId: "#add_school_id_error",
    collegeId: '#add_college_id_error',
    departmentName: '#add_department_name_error'
};

function initAddParam() {
    add_param.schoolId = $(add_param_id.schoolId).val();
    add_param.collegeId = $(add_param_id.collegeId).val();
    add_param.departmentName = $(add_param_id.departmentName).val();
}

$(add_param_id.schoolId).change(function () {
    var schoolId = $(this).val();
    if (Number(schoolId) > 0) {
        initColleges(schoolId, add_param_id.collegeId, 0);
    } else {
        $(add_param_id.collegeId).html('<option value="">请选择院</option>');
    }
});

function addDepartment() {
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
        checkAddDepartmentName();
    } else {
        validErrorDom(add_error_id.collegeId, '请选择院');
    }
}

function checkAddDepartmentName() {
    var collegeId = add_param.collegeId;
    var departmentName = add_param.departmentName;
    if (departmentName !== '') {
        $.post(getAjaxUrl().check_add_department, {
            collegeId: collegeId,
            departmentName: departmentName
        }, function (data) {
            if (data.state) {
                validSuccessDom(add_error_id.departmentName);
                sendAddAjax();
            } else {
                validErrorDom(add_error_id.departmentName, data.msg);
            }
        });
    } else {
        validErrorDom(add_error_id.collegeName, '系名不能为空');
    }
}

function sendAddAjax() {
    $.post(getAjaxUrl().save, add_param, function (data) {
        if (data.state) {
            $(add_param_id.departmentName).val('');
            $('#addModal').modal('hide');
            refreshTable();
        } else {
            validErrorDom(add_error_id.departmentName, data.msg);
        }
    })
}

var edit_param_id = {
    departmentId: '#editDepartmentId',
    schoolId: '#editSchoolId',
    collegeId: '#editCollegeId',
    departmentName: '#editDepartmentName'
};

var edit_param = {
    departmentId: '',
    collegeId: '',
    schoolId: '',
    departmentName: ''
};

var edit_error_id = {
    schoolId: '#edit_school_id_error',
    collegeId: '#edit_college_id_error',
    departmentName: '#edit_department_name_error'
};

function initEditParam() {
    edit_param.departmentId = $(edit_param_id.departmentId).val();
    edit_param.schoolId = $(edit_param_id.schoolId).val();
    edit_param.collegeId = $(edit_param_id.collegeId).val();
    edit_param.departmentName = $(edit_param_id.departmentName).val();
}

$(edit_param_id.schoolId).change(function () {
    var schoolId = $(this).val();
    if (Number(schoolId) > 0) {
        initColleges(schoolId, edit_param_id.collegeId, 0);
    } else {
        $(edit_param_id.collegeId).html('<option value="">请选择院</option>');
    }
});

function editDepartment() {
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
        checkEditDepartmentName();
    } else {
        validErrorDom(edit_error_id.collegeId, '请选择院');
    }
}

function checkEditDepartmentName() {
    var departmentId = edit_param.departmentId;
    var collegeId = edit_param.collegeId;
    var departmentName = edit_param.departmentName;
    if (departmentName !== '') {
        $.post(getAjaxUrl().check_update_department, {
            departmentId: departmentId,
            collegeId: collegeId,
            departmentName: departmentName
        }, function (data) {
            if (data.state) {
                validSuccessDom(edit_error_id.departmentName);
                sendEditAjax();
            } else {
                validErrorDom(edit_error_id.departmentName, data.msg);
            }
        });
    } else {
        validErrorDom(add_error_id.departmentName, '系名不能为空');
    }
}

function sendEditAjax() {
    $.post(getAjaxUrl().update, edit_param, function (data) {
        if (data.state) {
            $('#editModal').modal('hide');
            refreshTable();
        } else {
            validErrorDom(edit_error_id.departmentName, data.msg);
        }
    })
}

function delOrRecover(id, name, isDel, message) {
    var msg;
    msg = Messenger().post({
        message: "确定" + message + "系 '" + name + "'  吗?",
        actions: {
            retry: {
                label: '确定',
                phrase: 'Retrying TIME',
                action: function () {
                    msg.cancel();
                    $.post(getAjaxUrl().status, {departmentId: id, departmentIsDel: isDel}, function (data) {
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
        addDepartment();
    });

    dataTable.delegate('.edit', "click", function () {
        var departmentId = $(this).attr('data-id');
        $.post(getAjaxUrl().department, {departmentId: departmentId}, function (data) {
            $(edit_param_id.departmentId).val(data.department.departmentId);
            $(edit_param_id.schoolId).val(data.department.schoolId);
            initColleges(data.department.schoolId, edit_param_id.collegeId, data.department.collegeId);
            $(edit_param_id.departmentName).val(data.department.departmentName);
            validSuccessDom(edit_error_id.schoolId);
            validSuccessDom(edit_error_id.collegeId);
            validSuccessDom(edit_error_id.departmentName);
            $('#editModal').modal('show');
        });

    });

    $('#editSave').click(function () {
        editDepartment();
    });

    dataTable.delegate('.del', "click", function () {
        delOrRecover($(this).attr('data-id'), $(this).attr('data-department'), true, '删除');
    });

    dataTable.delegate('.recovery', "click", function () {
        delOrRecover($(this).attr('data-id'), $(this).attr('data-department'), false, '恢复');
    });
});
