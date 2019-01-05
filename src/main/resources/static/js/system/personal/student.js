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
    save: web_path + '/web/system/personal/student/save',
    password: web_path + '/web/system/personal/password'
};

var param_id = {
    realName: '#realName',
    password: '#password',
    okPassword: '#okPassword',
    sex: '#sex',
    placeOrigin: '#placeOrigin',
    politicalLandscapeId: '#politicalLandscapeId'
};

var param = {
    realName: '',
    password: '',
    okPassword: '',
    sex: '',
    placeOrigin: '',
    politicalLandscapeId: ''
};

var error_id = {
    realName: '#real_name_error',
    password: '#password_error',
    okPassword:'#ok_password_error'
};

function initParam() {
    param.realName = $(param_id.realName).val();
    param.sex = $(param_id.sex).val();
    param.placeOrigin = $(param_id.placeOrigin).val();
    param.politicalLandscapeId = $(param_id.politicalLandscapeId).val();
    param.password = $(param_id.password).val();
    param.okPassword = $(param_id.okPassword).val();
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

$('#savePassword').click(function () {
    initParam();
    checkPassword();
});

function checkPassword() {
    var password = param.password;
    if (password !== '') {
        validSuccessDom(error_id.password);
        checkOkPassword();
    } else {
        validErrorDom(error_id.password, '密码不能为空');
    }
}

function checkOkPassword() {
    var password = param.password;
    var okPassword = param.okPassword;
    if (password === okPassword) {
        validSuccessDom(error_id.okPassword);
        sendPasswordAjax();
    } else {
        validErrorDom(error_id.okPassword, '密码不一致');
    }
}

function sendPasswordAjax() {
    $.post(ajaxUrl.password, param, function (data) {
        $('#passwordModal').modal('hide');
        Messenger().post({
            message: data.msg,
            type: data.state ? 'info' : 'error',
            showCloseButton: true
        });
    })
}