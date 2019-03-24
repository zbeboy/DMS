/*
 ajax url
*/
function getAjaxUrl() {
    return {
        applies: web_path + '/web/quality/apply/data',
        update_success: web_path + '/web/quality/examine/update/success',
        update_fail:web_path  + '/web/quality/examine/update/fail'
    };
}

/*
参数id
*/
function getParamId() {
    return {
        applyContent: '#applyContent',
        studentNumber: '#studentNumber'
    };
}

/*
参数
*/
var param = {
    applyContent: '',
    studentNumber: ''
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
    if (value === 0) {
        v = "申请中";
    } else if (value === 1) {
        v = "班主任通过";
    } else if (value === 2) {
        v = "辅导员通过";
    } else if (value === 3) {
        v = "管理员通过";
    } else if (value === 4) {
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
                "name": "通过",
                "css": "pass",
                "type": "primary",
                "id": row.qualityApplyId
            },
            {
                "name": "拒绝",
                "css": "fail",
                "type": "warning",
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
    param.studentNumber = $(getParamId().studentNumber).val();
}

/*
清空参数
*/
function cleanParam() {
    $(getParamId().applyContent).val('');
    $(getParamId().studentNumber).val('');
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

function updateSuccess(id) {
    var msg;
    msg = Messenger().post({
        message: "确定通过吗?",
        actions: {
            retry: {
                label: '确定',
                phrase: 'Retrying TIME',
                action: function () {
                    msg.cancel();
                    $.get(getAjaxUrl().update_success + '/' + id, function (data) {
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

function updateFail(id) {
    var msg;
    msg = Messenger().post({
        message: "确定不通过吗?",
        actions: {
            retry: {
                label: '确定',
                phrase: 'Retrying TIME',
                action: function () {
                    msg.cancel();
                    $.get(getAjaxUrl().update_fail + '/' + id, function (data) {
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

    dataTable.delegate('.pass', "click", function () {
        updateSuccess($(this).attr('data-id'));
    });

    dataTable.delegate('.fail', "click", function () {
        updateFail($(this).attr('data-id'));
    });
});
