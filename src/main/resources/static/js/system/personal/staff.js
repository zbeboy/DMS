$(document).ready(function () {
    /*
   init message.
   */
    Messenger.options = {
        extraClasses: 'messenger-fixed messenger-on-bottom messenger-on-right',
        theme: 'air'
    };
});

var ajaxUrl = {
    politicalLandscapes: web_path + '/web/data/politicalLandscape/all',
    save: web_path + '/web/system/personal/staff/save'
};

var param_id = {
    realName: '#realName',
    sex: '#sex',
    politicalLandscapeId: '#politicalLandscapeId'
};

var param = {
    realName: '',
    sex: '',
    politicalLandscapeId: ''
};

var error_id = {
    realName: '#real_name_error'
};

function initParam() {
    param.realName = $(param_id.realName).val();
    param.sex = $(param_id.sex).val();
    param.politicalLandscapeId = $(param_id.politicalLandscapeId).val();
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
    initSex();
    initPoliticalLandscapes();
}

function initSex() {
    $(param_id.sex).val($('#sexData').val());
}

function initPoliticalLandscapes() {
    $.get(ajaxUrl.politicalLandscapes, function (data) {
        politicalLandscapesData(data);
    });
}

function politicalLandscapesData(data) {
    $.each(data.politicalLandscapes, function (i, n) {
        $(param_id.politicalLandscapeId).append('<option value="' + n.politicalLandscapeId + '">' + n.politicalLandscapeName + '</option>');
    });
    $(param_id.politicalLandscapeId).val($('#politicalLandscapeIdData').val());
}

$('#save').click(function () {
    initParam();
    checkRealName();
});

function checkRealName() {
    var realName = param.realName;
    if (realName !== '') {
        validSuccessDom(error_id.realName);
        sendAjax();
    } else {
        validErrorDom(error_id.realName, '姓名不能为空');
    }
}

function sendAjax() {
    $.post(ajaxUrl.save, param, function (data) {
        $('#myModal').modal('hide');
        Messenger().post({
            message: data.msg,
            type: data.state ? 'info' : 'error',
            showCloseButton: true
        });
    })
}