$(document).ready(function () {

    var ajax_url = {
        login: web_path + '/login'
    };

    var param_id = {
        username: '#username',
        password: '#password'
    };

    var param = {
        username: '',
        password: ''
    };

    function initParam() {
        param.username = $(param_id.username).val();
        param.password = $(param_id.password).val();
    }

    /*
     错误消息id
    */
    var errorMsgId = {
        username: '#username_error',
        password: '#password_error'
    };

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

    $(param_id.username).blur(function () {
        initParam();
        var username = param.username;
        if (username !== '') {
            validSuccessDom(errorMsgId.username);
        }
    });

    $(param_id.password).blur(function () {
        initParam();
        var password = param.password;
        if (password !== '') {
            validSuccessDom(errorMsgId.password);
        }
    });

    $('#login').click(function () {
        initParam();
        checkUsername();
    });

    function checkUsername() {
        var username = param.username;
        if (username === '') {
            validErrorDom(errorMsgId.username, '账号不能为空');
        } else {
            checkPassword();
        }
    }

    function checkPassword() {
        var password = param.password;
        if (password === '') {
            validErrorDom(errorMsgId.password, '密码不能为空');
        } else {
            sendAjax();
        }
    }

    function sendAjax() {
        $.post(ajax_url.login, param, function (data) {
            var errorMsg = $('#error_msg');
            switch (Number(data)) {
                case 8:
                    errorMsg.text("账号不能为空");
                    break;
                case 7:
                    errorMsg.text("密码不能为空");
                    break;
                case 5:
                    errorMsg.text("账号不存在");
                    break;
                case 10:
                    errorMsg.text("账号已被注销");
                    break;
                case 11:
                    errorMsg.text("账号已过期");
                    break;
                case 12:
                    errorMsg.text("账号凭证过期");
                    break;
                case 13:
                    errorMsg.text("账号已被锁");
                    break;
                case 1:
                    errorMsg.text("无权限访问");
                    break;
                case 3:
                    errorMsg.text("");
                    window.location.href = web_path + '/web/menu/backstage';
                    break;
                default:
                    errorMsg.text("验证异常");
            }
        });
    }


});
