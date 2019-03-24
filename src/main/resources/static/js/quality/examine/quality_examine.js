/*
 ajax url
*/
function getAjaxUrl() {
    return {
        releases: web_path + '/web/quality/release/data',
        examine_edit: web_path + '/web/quality/examine/edit'
    };
}

/*
参数id
*/
function getParamId() {
    return {
        releaseTitle: '#releaseTitle'
    };
}

/*
参数
*/
var param = {
    releaseTitle: ''
};

var dataTable = $('#dataTable');

dataTable.bootstrapTable('destroy')
    .bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: getAjaxUrl().releases, //获取数据的Servlet地址
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

function formatterTerm(value, row, index, field) {
    return value === 1 ? '下学期' : '上学期';
}

// 预编译模板
var template = Handlebars.compile($("#operator_button").html());

function operation(value, row, index, field) {
    var context = {
        func: [
            {
                "name": "审核",
                "css": "examine",
                "type": "primary",
                "id": row.qualityReleaseId,
                "title": row.releaseTitle,
                "year": row.year,
                "term": row.term
            }
        ]
    };
    return template(context);
}

/*
初始化参数
*/
function initParam() {
    param.releaseTitle = $(getParamId().releaseTitle).val();
}

/*
清空参数
*/
function cleanParam() {
    $(getParamId().releaseTitle).val('');
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

    dataTable.delegate('.examine', "click", function () {
        window.location.href = getAjaxUrl().examine_edit + '/' + $(this).attr('data-id')
    });
});
