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
        politicalLandscapes: web_path + '/web/data/politicalLandscape/all',
        roles: web_path + '/web/data/staff/roles',
        auths: web_path + '/web/data/staff/auths',
        staffs: web_path + '/web/data/staff/data',
        staff: web_path + '/web/data/staff/one',
        export: web_path + '/web/data/staff/export',
        import_template: web_path + '/files/import_staff.xlsx',
        check_add_staff: web_path + '/web/data/staff/check/add/number',
        check_update_staff: web_path + '/web/data/staff/check/update/number',
        save: web_path + '/web/data/staff/save',
        save_role: web_path + '/web/data/staff/role/save',
        update: web_path + '/web/data/staff/update',
        status: web_path + '/web/data/staff/status',
        file_upload_url: '/web/data/staff/import'
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
        realName: '#realName',
        staffNumber: '#staffNumber',
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
    realName: '',
    staffNumber: '',
    sex: ''
};

var dataTable = $('#dataTable');

dataTable.bootstrapTable('destroy')
    .bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: getAjaxUrl().staffs, //获取数据的Servlet地址
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
    var v = "已注销";
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
                "staff": row.realName
            },
            {
                "name": "编辑",
                "css": "edit",
                "type": "primary",
                "id": row.username,
                "staff": row.realName
            },
            {
                "name": row.enabled ? "注销" : "恢复",
                "css": row.enabled ? "del" : "recovery",
                "type": row.enabled ? "danger" : "warning",
                "id": row.username,
                "staff": row.realName
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
    param.realName = $(getParamId().realName).val();
    param.staffNumber = $(getParamId().staffNumber).val();
    param.sex = $(getParamId().sex).val();
}

/*
清空参数
*/
function cleanParam() {
    $(getParamId().schoolName).val('');
    $(getParamId().collegeName).val('');
    $(getParamId().departmentName).val('');
    $(getParamId().realName).val('');
    $(getParamId().staffNumber).val('');
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

var add_param_id = {
    schoolId: '#addSchoolId',
    collegeId: '#addCollegeId',
    departmentId: '#addDepartmentId',
    realName: '#addRealName',
    staffNumber: '#addStaffNumber',
    sex: '#addSex',
    politicalLandscapeId: '#addPoliticalLandscapeId',
    placeOrigin: '#addPlaceOrigin'
};

var add_param = {
    schoolId: '',
    collegeId: '',
    departmentId: '',
    realName: '',
    staffNumber: '',
    sex: '',
    politicalLandscapeId: '',
    placeOrigin: ''
};

var add_error_id = {
    schoolId: "#add_school_id_error",
    collegeId: '#add_college_id_error',
    departmentId: '#add_department_id_error',
    realName: '#add_real_name_error',
    staffNumber: '#add_staff_number_error',
    sex: '#add_sex_error',
    politicalLandscapeId: '#add_political_landscape_id_error',
    placeOrigin: '#add_place_origin_error'
};

function initAddParam() {
    add_param.schoolId = $(add_param_id.schoolId).val();
    add_param.collegeId = $(add_param_id.collegeId).val();
    add_param.departmentId = $(add_param_id.departmentId).val();
    add_param.realName = $(add_param_id.realName).val();
    add_param.staffNumber = $(add_param_id.staffNumber).val();
    add_param.sex = $(add_param_id.sex).val();
    add_param.politicalLandscapeId = $(add_param_id.politicalLandscapeId).val();
    add_param.placeOrigin = $(add_param_id.placeOrigin).val();
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

function addStaff() {
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
        checkAddRealName();
    } else {
        validErrorDom(add_error_id.departmentId, '请选择系');
    }
}

function checkAddRealName() {
    var realName = add_param.realName;
    if (realName !== '') {
        validSuccessDom(add_error_id.realName);
        checkAddStaffNumber();
    } else {
        validErrorDom(add_error_id.realName, '姓名不能为空');
    }
}

function checkAddStaffNumber() {
    var staffNumber = add_param.staffNumber;
    if (staffNumber !== '') {
        $.post(getAjaxUrl().check_add_staff, {
            staffNumber: staffNumber
        }, function (data) {
            if (data.state) {
                validSuccessDom(add_error_id.staffNumber);
                sendAddAjax();
            } else {
                validErrorDom(add_error_id.staffNumber, data.msg);
            }
        });
    } else {
        validErrorDom(add_error_id.staffNumber, '工号不能为空');
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
    staffId: '#staffId',
    schoolId: '#editSchoolId',
    collegeId: '#editCollegeId',
    departmentId: '#editDepartmentId',
    realName: '#editRealName',
    staffNumber: '#editStaffNumber',
    sex: '#editSex',
    politicalLandscapeId: '#editPoliticalLandscapeId',
    placeOrigin: '#editPlaceOrigin'
};

var edit_param = {
    username: '',
    staffId: '',
    collegeId: '',
    schoolId: '',
    departmentId: '',
    realName: '',
    staffNumber: '',
    sex: '',
    politicalLandscapeId: '',
    placeOrigin: ''
};

var edit_error_id = {
    schoolId: '#edit_school_id_error',
    collegeId: '#edit_college_id_error',
    departmentId: '#edit_department_id_error',
    realName: '#edit_real_name_error',
    staffNumber: '#edit_staff_number_error',
    sex: '#edit_sex_error',
    politicalLandscapeId: '#edit_political_landscape_id_error',
    placeOrigin: '#edit_place_origin_error'
};

function initEditParam() {
    edit_param.username = $(edit_param_id.username).val();
    edit_param.staffId = $(edit_param_id.staffId).val();
    edit_param.schoolId = $(edit_param_id.schoolId).val();
    edit_param.collegeId = $(edit_param_id.collegeId).val();
    edit_param.departmentId = $(edit_param_id.departmentId).val();
    edit_param.realName = $(edit_param_id.realName).val();
    edit_param.staffNumber = $(edit_param_id.staffNumber).val();
    edit_param.sex = $(edit_param_id.sex).val();
    edit_param.politicalLandscapeId = $(edit_param_id.politicalLandscapeId).val();
    edit_param.placeOrigin = $(edit_param_id.placeOrigin).val();
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

function editStaff() {
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
        checkEditRealName();
    } else {
        validErrorDom(edit_error_id.departmentId, '请选择系');
    }
}

function checkEditRealName() {
    var realName = edit_param.realName;
    if (realName !== '') {
        validSuccessDom(edit_error_id.realName);
        checkEditStaffNumber();
    } else {
        validErrorDom(add_error_id.realName, '姓名不能为空');
    }
}

function checkEditStaffNumber() {
    var staffNumber = edit_param.staffNumber;
    var staffId = edit_param.staffId;
    if (staffNumber !== '') {
        $.post(getAjaxUrl().check_update_staff, {
            staffId: staffId,
            staffNumber: staffNumber
        }, function (data) {
            if (data.state) {
                validSuccessDom(edit_error_id.staffNumber);
                sendEditAjax();
            } else {
                validErrorDom(edit_error_id.staffNumber, data.msg);
            }
        });
    } else {
        validErrorDom(add_error_id.staffNumber, '工号不能为空');
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
        message: "确定" + message + "教师 '" + name + "'  吗?",
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

function authStaff() {
    $.post(getAjaxUrl().save_role, $('#roleData').serialize(), function (data) {
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
        addStaff();
    });

    dataTable.delegate('.edit', "click", function () {
        var username = $(this).attr('data-id');
        $.post(getAjaxUrl().staff, {username: username}, function (data) {
            $(edit_param_id.username).val(data.staff.username);
            $(edit_param_id.staffId).val(data.staff.staffId);
            $(edit_param_id.schoolId).val(data.staff.schoolId);
            initColleges(data.staff.schoolId, edit_param_id.collegeId, data.staff.collegeId);
            initDepartments(data.staff.collegeId, edit_param_id.departmentId, data.staff.departmentId);
            $(edit_param_id.realName).val(data.staff.realName);
            $(edit_param_id.staffNumber).val(data.staff.staffNumber);
            $(edit_param_id.sex).val(data.staff.sex);
            $(edit_param_id.politicalLandscapeId).val(data.staff.politicalLandscapeId);
            $(edit_param_id.placeOrigin).val(data.staff.placeOrigin);
            validSuccessDom(edit_error_id.schoolId);
            validSuccessDom(edit_error_id.collegeId);
            validSuccessDom(edit_error_id.departmentId);
            validSuccessDom(edit_error_id.realName);
            validSuccessDom(edit_error_id.staffNumber);
            validSuccessDom(edit_error_id.sex);
            validSuccessDom(edit_error_id.politicalLandscapeId);
            validSuccessDom(edit_error_id.placeOrigin);
            $('#editModal').modal('show');
        });

    });

    $('#editSave').click(function () {
        editStaff();
    });

    $('#authSave').click(function () {
        authStaff();
    });

    dataTable.delegate('.del', "click", function () {
        delOrRecover($(this).attr('data-id'), $(this).attr('data-staff'), false, '注销');
    });

    dataTable.delegate('.recovery', "click", function () {
        delOrRecover($(this).attr('data-id'), $(this).attr('data-staff'), true, '恢复');
    });

    dataTable.delegate('.auth', "click", function () {
        var username = $(this).attr('data-id');
        $.get(getAjaxUrl().auths, {username: username}, function (data) {
            $('#authUsername').val(username);
            $('#authModal').modal('show');
            Messenger().post({
                message: data.msg,
                type: data.state ? 'info' : 'error',
                showCloseButton: true
            });

            var roles = $("input[name='role']");
            roles.prop("checked", false);
            $.each(data.auths, function (i, n) {
                for (var j = 0; j < roles.length; j++) {
                    if ($(roles[j]).val() === n.authority) {
                        $(roles[j]).prop("checked", true);
                    }
                }
            });
        });
    });

    // 上传组件
    $('#fileupload').fileupload({
        url: getAjaxUrl().file_upload_url,
        dataType: 'json',
        maxFileSize: 100000000,// 100MB
        acceptFileTypes: /([.\/])(xlsx)$/i,
        formAcceptCharset: 'utf-8',
        maxNumberOfFiles: 1,
        messages: {
            maxNumberOfFiles: '最大支持上传1个文件',
            acceptFileTypes: '仅支持上传xlsx等类型文件',
            maxFileSize: '单文件上传仅允许100MB大小'
        },
        done: function (e, data) {
            Messenger().post({
                message: data.result.msg,
                type: data.result.state ? 'success' : 'error',
                showCloseButton: true
            });
        }
    }).on('fileuploadadd', function (evt, data) {
        var isOk = true;
        var $this = $(this);
        var validation = data.process(function () {
            return $this.fileupload('process', data);
        });
        validation.fail(function (data) {
            isOk = false;
            Messenger().post({
                message: '上传失败: ' + data.files[0].error,
                type: 'error',
                showCloseButton: true
            });
        });
        return isOk;
    });
});
