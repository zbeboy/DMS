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
    save: web_path + '/web/system/personal/admin/save',
    password: web_path + '/web/system/personal/password'
};

var param_id = {
    realName: '#realName',
    password: '#password'
};

var param = {
    realName: '',
    password: ''
};

var error_id = {
    realName: '#real_name_error',
    password: '#password_error'
};

function initParam() {
    param.realName = $(param_id.realName).val();
    param.password = $(param_id.password).val();
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
        sendPasswordAjax();
    } else {
        validErrorDom(error_id.password, '密码不能为空');
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