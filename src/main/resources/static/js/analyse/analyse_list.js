/*
 ajax url
*/
function getAjaxUrl() {
    return {
        analyses: web_path + '/web/analyse/data',
        analyse: web_path + '/web/analyse/one',
        export: web_path + '/web/analyse/export',
        import_template: web_path + '/files/import_credit.xlsx',
        save: web_path + '/web/analyse/save',
        update: web_path + '/web/analyse/update',
        del: web_path + '/web/analyse/delete',
        file_upload_url: '/web/analyse/import',
        chart: web_path + '/web/analyse/chart',
        wining: web_path + '/web/analyse/wining/save',
        diploma: web_path + '/web/analyse/diploma/save'
    };
}

/*
参数id
*/
function getParamId() {
    return {
        realName: '#realName',
        studentNumber: '#studentNumber'
    };
}

/*
参数
*/
var param = {
    realName: '',
    studentNumber: ''
};

var dataTable = $('#dataTable');

dataTable.bootstrapTable('destroy')
    .bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: getAjaxUrl().analyses, //获取数据的Servlet地址
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

function tableStyle(value, row, index, field) {
    return {css: {'word-wrap': 'break-word'}};
}

function formatterTime(value, row, index, field) {
    return row.year + (row.term === 1 ? '下' : '上');
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
                "id": row.creditId,
                "credit": row.realName
            },
            {
                "name": "删除",
                "css": "del",
                "type": "danger",
                "id": row.creditId,
                "credit": row.realName
            },
            {
                "name": "曲线",
                "css": "chart",
                "type": "default",
                "id": row.creditId,
                "credit": row.realName
            },
            {
                "name": "获奖情况",
                "css": "wining",
                "type": "default",
                "id": row.creditId,
                "credit": row.realName
            },
            {
                "name": "证书",
                "css": "diploma",
                "type": "default",
                "id": row.creditId,
                "credit": row.realName
            }
        ]
    };
    return template(context);
}

/*
初始化参数
*/
function initParam() {
    param.realName = $(getParamId().realName).val();
    param.studentNumber = $(getParamId().studentNumber).val();
}

/*
清空参数
*/
function cleanParam() {
    $(getParamId().realName).val('');
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

var add_param_id = {
    studentNumber: '#addStudentNumber',
    year: '#addYear',
    term: '#addTerm',
    sports: '#addSports',
    skills: '#addSkills',
    voluntary: '#addVoluntary',
    technological: '#addTechnological',
    post: '#addPost',
    ideological: '#addIdeological',
    practical: '#addPractical',
    work: '#addWork',
    achievement: '#addAchievement',
    intellectual: '#addIntellectual'
};

var add_param = {
    studentNumber: '',
    year: '',
    term: '',
    sports: '',
    skills: '',
    voluntary: '',
    technological: '',
    post: '',
    ideological: '',
    practical: '',
    work: '',
    achievement: '',
    intellectual: ''
};

var add_error_id = {
    studentNumber: '#add_student_number_error',
    year: '#add_year_error',
    term: '#add_term_error',
    sports: '#add_sports_error',
    skills: '#add_skills_error',
    voluntary: '#add_voluntary_error',
    technological: '#add_technological_error',
    post: '#add_post_error',
    ideological: '#add_ideological_error',
    practical: '#add_practical_error',
    work: '#add_work_error',
    achievement: '#add_achievement_error',
    intellectual: '#add_intellectual_error'
};

function initAddParam() {
    add_param.studentNumber = $(add_param_id.studentNumber).val();
    add_param.year = $(add_param_id.year).val();
    add_param.term = $(add_param_id.term).val();
    add_param.sports = $(add_param_id.sports).val();
    add_param.skills = $(add_param_id.skills).val();
    add_param.voluntary = $(add_param_id.voluntary).val();
    add_param.technological = $(add_param_id.technological).val();
    add_param.post = $(add_param_id.post).val();
    add_param.ideological = $(add_param_id.ideological).val();
    add_param.practical = $(add_param_id.practical).val();
    add_param.work = $(add_param_id.work).val();
    add_param.achievement = $(add_param_id.achievement).val();
    add_param.intellectual = $(add_param_id.intellectual).val();
}

function addCredit() {
    initAddParam();
    checkAddStudentNumber();
}

function checkAddStudentNumber() {
    var studentNumber = add_param.studentNumber;
    if (studentNumber !== '') {
        validSuccessDom(add_error_id.studentNumber);
        checkAddYear();
    } else {
        validErrorDom(add_error_id.studentNumber, '学号不能为空');
    }
}

function checkAddYear() {
    var year = add_param.year;
    if (year !== '') {
        validSuccessDom(add_error_id.year);
        checkAddTerm();
    } else {
        validErrorDom(add_error_id.year, '年份不能为空');
    }
}

function checkAddTerm() {
    var term = add_param.term;
    if (term !== '') {
        validSuccessDom(add_error_id.term);
        sendAddAjax();
    } else {
        validErrorDom(add_error_id.term, '学期不能为空');
    }
}

function sendAddAjax() {
    $.post(getAjaxUrl().save, add_param, function (data) {
        if (data.state) {
            $('#addModal').modal('hide');
            refreshTable();
        } else {
            validErrorDom(add_error_id.achievement, data.msg);
        }
    })
}

var edit_param_id = {
    creditId: '#editCreditId',
    studentNumber: '#editStudentNumber',
    year: '#editYear',
    term: '#editTerm',
    sports: '#editSports',
    skills: '#editSkills',
    voluntary: '#editVoluntary',
    technological: '#editTechnological',
    post: '#editPost',
    ideological: '#editIdeological',
    practical: '#editPractical',
    work: '#editWork',
    achievement: '#editAchievement',
    intellectual: '#editIntellectual'
};

var edit_param = {
    creditId: '',
    studentNumber: '',
    year: '',
    term: '',
    sports: '',
    skills: '',
    voluntary: '',
    technological: '',
    post: '',
    ideological: '',
    practical: '',
    work: '',
    achievement: '',
    intellectual: ''
};

var edit_error_id = {
    studentNumber: '#edit_student_number_error',
    year: '#edit_year_error',
    term: '#edit_term_error',
    sports: '#edit_sports_error',
    skills: '#edit_skills_error',
    voluntary: '#edit_voluntary_error',
    technological: '#edit_technological_error',
    post: '#edit_post_error',
    ideological: '#edit_ideological_error',
    practical: '#edit_practical_error',
    work: '#edit_work_error',
    achievement: '#edit_achievement_error',
    intellectual: '#edit_intellectual_error'
};

function initEditParam() {
    edit_param.creditId = $(edit_param_id.creditId).val();
    edit_param.studentNumber = $(edit_param_id.studentNumber).val();
    edit_param.year = $(edit_param_id.year).val();
    edit_param.term = $(edit_param_id.term).val();
    edit_param.sports = $(edit_param_id.sports).val();
    edit_param.skills = $(edit_param_id.skills).val();
    edit_param.voluntary = $(edit_param_id.voluntary).val();
    edit_param.technological = $(edit_param_id.technological).val();
    edit_param.post = $(edit_param_id.post).val();
    edit_param.ideological = $(edit_param_id.ideological).val();
    edit_param.practical = $(edit_param_id.practical).val();
    edit_param.work = $(edit_param_id.work).val();
    edit_param.achievement = $(edit_param_id.achievement).val();
    edit_param.intellectual = $(edit_param_id.intellectual).val();
}

function editCredit() {
    initEditParam();
    checkEditStudentNumber();
}

function checkEditStudentNumber() {
    var studentNumber = edit_param.studentNumber;
    if (studentNumber !== '') {
        validSuccessDom(edit_error_id.studentNumber);
        checkEditYear();
    } else {
        validErrorDom(edit_error_id.studentNumber, '学号不能为空');
    }
}

function checkEditYear() {
    var year = edit_param.year;
    if (year !== '') {
        validSuccessDom(edit_error_id.year);
        checkEditTerm();
    } else {
        validErrorDom(edit_error_id.year, '年份不能为空');
    }
}

function checkEditTerm() {
    var term = edit_param.term;
    if (term !== '') {
        validSuccessDom(edit_error_id.term);
        sendEditAjax();
    } else {
        validErrorDom(edit_error_id.term, '学期不能为空');
    }
}

function sendEditAjax() {
    $.post(getAjaxUrl().update, edit_param, function (data) {
        if (data.state) {
            $('#editModal').modal('hide');
            refreshTable();
        } else {
            validErrorDom(edit_error_id.achievement, data.msg);
        }
    })
}

function delOrRecover(id, name, message) {
    var msg;
    msg = Messenger().post({
        message: "确定" + message + "数据 '" + name + "'  吗?",
        actions: {
            retry: {
                label: '确定',
                phrase: 'Retrying TIME',
                action: function () {
                    msg.cancel();
                    $.post(getAjaxUrl().del, {creditId: id}, function (data) {
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

    $('#export').click(function () {
        var exportConfig = dataTable.bootstrapTable('getOptions');
        window.location.href = encodeURI(getAjaxUrl().export + '?' +
            'sortName=' + exportConfig.sortName + '&sortOrder=' + exportConfig.sortOrder +
            '&extraSearch=' + JSON.stringify(param));
    });

    $('#importTemplate').click(function () {
        window.location.href = getAjaxUrl().import_template;
    });

    $('#add').click(function () {
        $('#addModal').modal('show');
    });

    $('#addSave').click(function () {
        addCredit();
    });

    dataTable.delegate('.edit', "click", function () {
        var creditId = $(this).attr('data-id');
        $.post(getAjaxUrl().analyse, {creditId: creditId}, function (data) {
            $(edit_param_id.studentNumber).val(data.analyse.studentNumber);
            $(edit_param_id.creditId).val(data.analyse.creditId);
            $(edit_param_id.year).val(data.analyse.year);
            $(edit_param_id.term).val(data.analyse.term);
            $(edit_param_id.sports).val(data.analyse.sports);
            $(edit_param_id.skills).val(data.analyse.skills);
            $(edit_param_id.voluntary).val(data.analyse.voluntary);
            $(edit_param_id.technological).val(data.analyse.technological);
            $(edit_param_id.post).val(data.analyse.post);
            $(edit_param_id.ideological).val(data.analyse.ideological);
            $(edit_param_id.practical).val(data.analyse.practical);
            $(edit_param_id.work).val(data.analyse.work);
            $(edit_param_id.achievement).val(data.analyse.achievement);
            $(edit_param_id.intellectual).val(data.analyse.intellectual);

            validSuccessDom(edit_error_id.studentNumber);
            validSuccessDom(edit_error_id.year);
            $('#editModal').modal('show');
        });
    });

    $('#editSave').click(function () {
        editCredit();
    });

    $('#winingSave').click(function () {
        $.post(getAjaxUrl().wining, $('#winingForm').serialize(), function (data) {
            if(data.state){
                $('#winingModal').modal('hide');
            }
            Messenger().post({
                message: data.msg,
                type: data.state ? 'info' : 'error',
                showCloseButton: true
            });
        });
    });

    $('#diplomaSave').click(function () {
        $.post(getAjaxUrl().diploma, $('#diplomaForm').serialize(), function (data) {
            if(data.state){
                $('#diplomaModal').modal('hide');
            }
            Messenger().post({
                message: data.msg,
                type: data.state ? 'info' : 'error',
                showCloseButton: true
            });
        });
    });

    dataTable.delegate('.del', "click", function () {
        delOrRecover($(this).attr('data-id'), $(this).attr('data-credit'), '删除');
    });

    dataTable.delegate('.chart', "click", function () {
        window.location.href = getAjaxUrl().chart + "/" + $(this).attr('data-id');
    });

    dataTable.delegate('.wining', "click", function () {
        $('#winingCreditId').val($(this).attr('data-id'));
        $('#winingModal').modal('show');
    });

    dataTable.delegate('.diploma', "click", function () {
        $('#diplomaCreditId').val($(this).attr('data-id'));
        $('#diplomaModal').modal('show');
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
