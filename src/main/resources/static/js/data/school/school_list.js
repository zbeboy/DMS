/**
 * Created by zbeboy 2018-04-13 .
 **/

/*
 ajax url
*/
function getAjaxUrl() {
    return {
        schools: web_path + '/web/data/school/data',
        check_add_school: web_path + '/web/data/school/check/add/name',
        check_update_school: web_path + '/web/data/school/check/update/name',
        save: web_path + '/web/data/school/save',
        update: web_path + '/web/data/school/update',
        status: web_path + '/web/data/school/status'
    };
}

/*
参数id
*/
function getParamId() {
    return {
        schoolName: '#schoolName'
    };
}

/*
参数
*/
var param = {
    schoolName: ''
};

var dataTable = $('#dataTable');

dataTable.bootstrapTable('destroy')
    .bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: getAjaxUrl().schools, //获取数据的Servlet地址
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

function formatterSchoolIsDel(value, row, index, field) {
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
                "id": row.schoolId,
                "school": row.schoolName
            },
            {
                "name": row.schoolIsDel ? "恢复" : "删除",
                "css": row.schoolIsDel ? "recovery" : "del",
                "type": row.schoolIsDel ? "warning" : "danger",
                "id": row.schoolId,
                "school": row.schoolName
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
}

/*
清空参数
*/
function cleanParam() {
    $(getParamId().schoolName).val('');
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

var add_param_id = {
    schoolName: '#addSchoolName'
};

var add_param = {
    schoolName: ''
};

var add_error_id = {
    schoolName: "#add_school_name_error"
};

function initAddParam() {
    add_param.schoolName = $(add_param_id.schoolName).val();
}

function addSchool() {
    initAddParam();
    checkAddSchoolName();
}

function checkAddSchoolName() {
    var schoolName = add_param.schoolName;
    if (schoolName !== '') {
        $.post(getAjaxUrl().check_add_school, {schoolName: schoolName}, function (data) {
            if (data.state) {
                validSuccessDom(add_error_id.schoolName);
                sendAddAjax();
            } else {
                validErrorDom(add_error_id.schoolName, data.msg);
            }
        });
    } else {
        validErrorDom(add_error_id.schoolName, '学校名不能为空');
    }
}

function sendAddAjax() {
    $.post(getAjaxUrl().save, add_param, function (data) {
        if (data.state) {
            $(add_param_id.schoolName).val('');
            $('#addModal').modal('hide');
            refreshTable();
        } else {
            validErrorDom(add_error_id.schoolName, data.msg);
        }
    })
}

var edit_param_id = {
    schoolId: '#editSchoolId',
    schoolName: '#editSchoolName'
};

var edit_param = {
    schoolId: '',
    schoolName: ''
};

var edit_error_id = {
    schoolName: "#edit_school_name_error"
};

function initEditParam() {
    edit_param.schoolId = $(edit_param_id.schoolId).val();
    edit_param.schoolName = $(edit_param_id.schoolName).val();
}

function editSchool() {
    initEditParam();
    checkEditSchoolName();
}

function checkEditSchoolName() {
    var schoolName = edit_param.schoolName;
    var schoolId = edit_param.schoolId;
    if (schoolName !== '') {
        $.post(getAjaxUrl().check_update_school, edit_param, function (data) {
            if (data.state) {
                validSuccessDom(edit_error_id.schoolName);
                sendEditAjax();
            } else {
                validErrorDom(edit_error_id.schoolName, data.msg);
            }
        });
    } else {
        validErrorDom(edit_error_id.schoolName, '学校名不能为空');
    }
}

function sendEditAjax() {
    $.post(getAjaxUrl().update, edit_param, function (data) {
        if (data.state) {
            $(edit_param_id.schoolName).val('');
            $('#editModal').modal('hide');
            refreshTable();
        } else {
            validErrorDom(edit_error_id.schoolName, data.msg);
        }
    })
}

function delOrRecover(id, name, isDel, message) {
    var msg;
    msg = Messenger().post({
        message: "确定" + message + "学校 '" + name + "'  吗?",
        actions: {
            retry: {
                label: '确定',
                phrase: 'Retrying TIME',
                action: function () {
                    msg.cancel();
                    $.post(getAjaxUrl().status, {schoolId: id, schoolIsDel: isDel}, function (data) {
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
        addSchool();
    });

    dataTable.delegate('.edit', "click", function () {
        $(edit_param_id.schoolName).val($(this).attr('data-school'));
        $(edit_param_id.schoolId).val($(this).attr('data-id'));
        validSuccessDom(edit_error_id.schoolName);
        $('#editModal').modal('show');
    });

    $('#editSave').click(function () {
        editSchool();
    });

    dataTable.delegate('.del', "click", function () {
        delOrRecover($(this).attr('data-id'), $(this).attr('data-school'), true, '删除');
    });

    dataTable.delegate('.recovery', "click", function () {
        delOrRecover($(this).attr('data-id'), $(this).attr('data-school'), false, '恢复');
    });
});
