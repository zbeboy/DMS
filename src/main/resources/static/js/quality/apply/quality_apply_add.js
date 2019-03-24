/*
 ajax url
*/
function getAjaxUrl() {
    return {
        applies: web_path + '/web/quality/apply/data',
        save: web_path + '/web/quality/apply/save',
        del: web_path + '/web/quality/apply/delete'
    };
}

/*
参数id
*/
function getParamId() {
    return {
        applyContent: '#applyContent'
    };
}

/*
参数
*/
var param = {
    applyContent: ''
};

var dataTable = $('#dataTable');

dataTable.bootstrapTable('destroy')
    .bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: getAjaxUrl().applies, //获取数据的Servlet地址
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

function formatterApplyState(value, row, index, field) {
    var v = "";
    if(value === 0){
        v = "申请中";
    } else if(value === 1){
        v = "班主任通过";
    } else if(value === 2){
        v = "辅导员通过";
    } else if(value === 3){
        v = "管理员通过";
    } else if(value === 4){
        v = "失败";
    }
    return v;
}

// 预编译模板
var template = Handlebars.compile($("#operator_button").html());

function operation(value, row, index, field) {
    var context = {
        func: [
            {
                "name": "删除",
                "css": "del",
                "type": "danger",
                "id": row.qualityApplyId
            }
        ]
    };
    return template(context);
}

/*
初始化参数
*/
function initParam() {
    param.applyContent = $(getParamId().applyContent).val();
}

/*
清空参数
*/
function cleanParam() {
    $(getParamId().applyContent).val('');
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
    applyContent: '#addApplyContent',
    qualityReleaseId: '#addQualityReleaseId',
    studentId: '#addStudentId'
};

var add_param = {
    applyContent: '',
    qualityReleaseId: $(add_param_id.qualityReleaseId).val(),
    studentId: $(add_param_id.studentId).val()
};

var add_error_id = {
    applyContent: '#add_apply_content_error'
};

function initAddParam() {
    add_param.applyContent = $(add_param_id.applyContent).val();
}

function addApply() {
    initAddParam();
    checkAddApplyContent();
}

function checkAddApplyContent() {
    var applyContent = add_param.applyContent;
    if (applyContent !== '') {
        validSuccessDom(add_error_id.applyContent);
        sendAddAjax();
    } else {
        validErrorDom(add_error_id.applyContent, '内容不能为空');
    }
}

function sendAddAjax() {
    $.post(getAjaxUrl().save, add_param, function (data) {
        if (data.state) {
            $('#addModal').modal('hide');
            refreshTable();
        } else {
            validErrorDom(add_error_id.applyContent, data.msg);
        }
    })
}

function del(id) {
    var msg;
    msg = Messenger().post({
        message: "确定删除吗?",
        actions: {
            retry: {
                label: '确定',
                phrase: 'Retrying TIME',
                action: function () {
                    msg.cancel();
                    $.get(getAjaxUrl().del + '/' + id, function (data) {
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
        addApply();
    });

    dataTable.delegate('.del', "click", function () {
        del($(this).attr('data-id'));
    });
});
