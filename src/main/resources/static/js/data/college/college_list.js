/**
 * Created by zbeboy 2018-04-13 .
 **/

/*
 ajax url
*/
function getAjaxUrl() {
    return {
        schools: web_path + '/web/data/school/all',
        colleges: web_path + '/web/data/college/data',
        college: web_path + '/web/data/college/one',
        check_add_college: web_path + '/web/data/college/check/add/name',
        check_update_college: web_path + '/web/data/college/check/update/name',
        save: web_path + '/web/data/college/save',
        update: web_path + '/web/data/college/update',
        status: web_path + '/web/data/college/status'
    };
}

/*
参数id
*/
function getParamId() {
    return {
        schoolName: '#schoolName',
        collegeName: '#collegeName'
    };
}

/*
参数
*/
var param = {
    schoolName: '',
    collegeName: ''
};

var dataTable = $('#dataTable');

dataTable.bootstrapTable('destroy')
    .bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: getAjaxUrl().colleges, //获取数据的Servlet地址
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

function formatterCollegeIsDel(value, row, index, field) {
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
                "id": row.collegeId,
                "college": row.collegeName
            },
            {
                "name": row.collegeIsDel ? "恢复" : "删除",
                "css": row.collegeIsDel ? "recovery" : "del",
                "type": row.collegeIsDel ? "warning" : "danger",
                "id": row.collegeId,
                "college": row.collegeName
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
}

/*
清空参数
*/
function cleanParam() {
    $(getParamId().schoolName).val('');
    $(getParamId().collegeName).val('');
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

var add_param_id = {
    schoolId: '#addSchoolId',
    collegeName: '#addCollegeName',
    collegeAddress: '#addCollegeAddress'
};

var add_param = {
    schoolId: '',
    collegeName: '',
    collegeAddress: ''
};

var add_error_id = {
    schoolId: "#add_school_id_error",
    collegeName: '#add_college_name_error',
    collegeAddress: '#add_college_address_error'
};

function initAddParam() {
    add_param.schoolId = $(add_param_id.schoolId).val();
    add_param.collegeName = $(add_param_id.collegeName).val();
    add_param.collegeAddress = $(add_param_id.collegeAddress).val();
}

function addCollege() {
    initAddParam();
    checkAddSchoolId();
}

function checkAddSchoolId() {
    var schoolId = add_param.schoolId;
    if (Number(schoolId) !== 0) {
        validSuccessDom(add_error_id.schoolId);
        checkAddCollegeName();
    } else {
        validErrorDom(add_error_id.schoolId, '请选择学校');
    }
}

function checkAddCollegeName() {
    var schoolId = add_param.schoolId;
    var collegeName = add_param.collegeName;
    if (collegeName !== '') {
        $.post(getAjaxUrl().check_add_college, {schoolId: schoolId, collegeName: collegeName}, function (data) {
            if (data.state) {
                validSuccessDom(add_error_id.collegeName);
                checkAddCollegeAddress();
            } else {
                validErrorDom(add_error_id.collegeName, data.msg);
            }
        });
    } else {
        validErrorDom(add_error_id.collegeName, '院名不能为空');
    }
}

function checkAddCollegeAddress() {
    var collegeAddress = add_param.collegeAddress;
    if (collegeAddress !== '') {
        validSuccessDom(add_error_id.collegeAddress);
        sendAddAjax();
    } else {
        validErrorDom(add_error_id.collegeAddress, '请填写地址');
    }
}

function sendAddAjax() {
    $.post(getAjaxUrl().save, add_param, function (data) {
        if (data.state) {
            $(add_param_id.collegeName).val('');
            $('#addModal').modal('hide');
            refreshTable();
        } else {
            validErrorDom(add_error_id.collegeName, data.msg);
        }
    })
}

var edit_param_id = {
    collegeId: '#editCollegeId',
    schoolId: '#editSchoolId',
    collegeName: '#editCollegeName',
    collegeAddress: '#editCollegeAddress'
};

var edit_param = {
    collegeId: '',
    schoolId: '',
    collegeName: '',
    collegeAddress: ''
};

var edit_error_id = {
    schoolId: '#edit_school_id_error',
    collegeName: '#edit_school_name_error',
    collegeAddress: '#edit_college_address_error'
};

function initEditParam() {
    edit_param.collegeId = $(edit_param_id.collegeId).val();
    edit_param.schoolId = $(edit_param_id.schoolId).val();
    edit_param.collegeName = $(edit_param_id.collegeName).val();
    edit_param.collegeAddress = $(edit_param_id.collegeAddress).val();
}

function editSchool() {
    initEditParam();
    checkEditSchoolId();
}

function checkEditSchoolId() {
    var schoolId = edit_param.schoolId;
    if (Number(schoolId) !== 0) {
        validSuccessDom(edit_error_id.schoolId);
        checkEditCollegeName();
    } else {
        validErrorDom(edit_error_id.schoolId, '请选择学校');
    }
}

function checkEditCollegeName() {
    var collegeId = edit_param.collegeId;
    var schoolId = edit_param.schoolId;
    var collegeName = edit_param.collegeName;
    if (collegeName !== '') {
        $.post(getAjaxUrl().check_update_college, {
            collegeId: collegeId,
            schoolId: schoolId,
            collegeName: collegeName
        }, function (data) {
            if (data.state) {
                validSuccessDom(edit_error_id.collegeName);
                checkEditCollegeAddress();
            } else {
                validErrorDom(edit_error_id.collegeName, data.msg);
            }
        });
    } else {
        validErrorDom(add_error_id.collegeName, '院名不能为空');
    }
}

function checkEditCollegeAddress() {
    var collegeAddress = edit_param.collegeAddress;
    if (collegeAddress !== '') {
        validSuccessDom(edit_error_id.collegeAddress);
        sendEditAjax();
    } else {
        validErrorDom(edit_error_id.collegeAddress, '请填写地址');
    }
}

function sendEditAjax() {
    $.post(getAjaxUrl().update, edit_param, function (data) {
        if (data.state) {
            $('#editModal').modal('hide');
            refreshTable();
        } else {
            validErrorDom(edit_error_id.collegeName, data.msg);
        }
    })
}

function delOrRecover(id, name, isDel, message) {
    var msg;
    msg = Messenger().post({
        message: "确定" + message + "院 '" + name + "'  吗?",
        actions: {
            retry: {
                label: '确定',
                phrase: 'Retrying TIME',
                action: function () {
                    msg.cancel();
                    $.post(getAjaxUrl().status, {collegeId: id, collegeIsDel: isDel}, function (data) {
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
        addCollege();
    });

    dataTable.delegate('.edit', "click", function () {
        var collegeId = $(this).attr('data-id');
        $.post(getAjaxUrl().college, {collegeId: collegeId}, function (data) {
            $(edit_param_id.collegeId).val(data.college.collegeId);
            $(edit_param_id.schoolId).val(data.college.schoolId);
            $(edit_param_id.collegeName).val(data.college.collegeName);
            $(edit_param_id.collegeAddress).val(data.college.collegeAddress);
            validSuccessDom(edit_error_id.schoolId);
            validSuccessDom(edit_error_id.collegeName);
            validSuccessDom(edit_error_id.collegeAddress);
            $('#editModal').modal('show');
        });

    });

    $('#editSave').click(function () {
        editSchool();
    });

    dataTable.delegate('.del', "click", function () {
        delOrRecover($(this).attr('data-id'), $(this).attr('data-college'), true, '删除');
    });

    dataTable.delegate('.recovery', "click", function () {
        delOrRecover($(this).attr('data-id'), $(this).attr('data-college'), false, '恢复');
    });
});
