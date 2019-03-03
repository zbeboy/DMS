/*
 ajax url
*/
function getAjaxUrl() {
    return {
        releases: web_path + '/web/quality/release/data',
        save: web_path + '/web/quality/release/save',
        update: web_path + '/web/quality/release/update'
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
                "name": "编辑",
                "css": "edit",
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

var add_param_id = {
    releaseTitle: '#addReleaseTitle',
    year: '#addYear',
    term: '#addTerm'
};

var add_param = {
    releaseTitle: '',
    year: '',
    term: ''
};

var add_error_id = {
    releaseTitle: '#add_release_title_error',
    year: '#add_year_error',
    term: '#add_term_error'
};

function initAddParam() {
    add_param.releaseTitle = $(add_param_id.releaseTitle).val();
    add_param.year = $(add_param_id.year).val();
    add_param.term = $(add_param_id.term).val();
}

function addRelease() {
    initAddParam();
    checkAddReleaseTitle();
}

function checkAddReleaseTitle() {
    var releaseTitle = add_param.releaseTitle;
    if (releaseTitle !== '') {
        validSuccessDom(add_error_id.releaseTitle);
        checkAddYear();
    } else {
        validErrorDom(add_error_id.releaseTitle, '标题不能为空');
    }
}

function checkAddYear() {
    var year = add_param.year;
    if (year !== '') {
        validSuccessDom(add_error_id.year);
        sendAddAjax();
    } else {
        validErrorDom(add_error_id.year, '年份不能为空');
    }
}

function sendAddAjax() {
    $.post(getAjaxUrl().save, add_param, function (data) {
        if (data.state) {
            $('#addModal').modal('hide');
            refreshTable();
        } else {
            validErrorDom(add_error_id.term, data.msg);
        }
    })
}

var edit_param_id = {
    qualityReleaseId: '#editQualityReleaseId',
    releaseTitle: '#editReleaseTitle',
    year: '#editYear',
    term: '#editTerm'
};

var edit_param = {
    qualityReleaseId: '',
    releaseTitle: '',
    year: '',
    term: ''
};

var edit_error_id = {
    releaseTitle: '#edit_release_title_error',
    year: '#edit_year_error',
    term: '#edit_term_error'
};

function initEditParam() {
    edit_param.qualityReleaseId = $(edit_param_id.qualityReleaseId).val();
    edit_param.releaseTitle = $(edit_param_id.releaseTitle).val();
    edit_param.year = $(edit_param_id.year).val();
    edit_param.term = $(edit_param_id.term).val();
}

function editRelease() {
    initEditParam();
    checkEditReleaseTitle();
}

function checkEditReleaseTitle() {
    var releaseTitle = edit_param.releaseTitle;
    if (releaseTitle !== '') {
        validSuccessDom(edit_error_id.releaseTitle);
        checkEditYear();
    } else {
        validErrorDom(edit_error_id.releaseTitle, '标题不能为空');
    }
}

function checkEditYear() {
    var year = edit_param.year;
    if (year !== '') {
        validSuccessDom(edit_error_id.year);
        sendEditAjax();
    } else {
        validErrorDom(edit_error_id.year, '年份不能为空');
    }
}

function sendEditAjax() {
    $.post(getAjaxUrl().update, edit_param, function (data) {
        if (data.state) {
            $('#editModal').modal('hide');
            refreshTable();
        } else {
            validErrorDom(edit_error_id.term, data.msg);
        }
    })
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
        addRelease();
    });

    dataTable.delegate('.edit', "click", function () {
        $(edit_param_id.qualityReleaseId).val($(this).attr('data-id'));
        $(edit_param_id.releaseTitle).val($(this).attr('data-title'));
        $(edit_param_id.year).val($(this).attr('data-year'));
        $(edit_param_id.term).val($(this).attr('data-term'));
        validSuccessDom(edit_error_id.releaseTitle);
        validSuccessDom(edit_error_id.year);
        validSuccessDom(edit_error_id.term);
        $('#editModal').modal('show');
    });

    $('#editSave').click(function () {
        editRelease();
    });
});
