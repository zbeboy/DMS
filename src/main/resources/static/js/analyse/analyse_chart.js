var ajax_url = {
    analyse: web_path + '/web/analyse/one',
    delWining: web_path + '/web/analyse/wining/delete',
    delDiploma: web_path + '/web/analyse/diploma/delete'
};

function initData() {
    $.post(ajax_url.analyse, {creditId: init_page_param.creditId}, function (data) {
        var chart = {
            type: 'pie',
            options3d: {
                enabled: true,
                alpha: 45,
                beta: 0
            }
        };
        var title = {
            text: '城市学院决策分析图'
        };
        var tooltip = {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        };

        var plotOptions = {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                depth: 35,
                dataLabels: {
                    enabled: true,
                    format: '{point.name}'
                }
            }
        };

        var series = [{
            type: 'pie',
            name: '综合分析',
            data: [
                ['文体活动', data.analyse.sports / 100],
                ['技能特长', data.analyse.skills / 100],
                {
                    name: '志愿公益',
                    y: data.analyse.voluntary / 100,
                    sliced: true,
                    selected: true
                },
                ['科技创新', data.analyse.technological / 100],
                ['任职经历', data.analyse.post / 100],
                ['思想成长', data.analyse.ideological / 100],
                ['实践实习', data.analyse.practical / 100],
                ['工作履历', data.analyse.work / 100],
                ['学习成绩', data.analyse.achievement / 100],
                ['智育成绩', data.analyse.intellectual / 100]
            ]
        }];

        var json = {};
        json.chart = chart;
        json.title = title;
        json.tooltip = tooltip;
        json.plotOptions = plotOptions;
        json.series = series;
        $('#container').highcharts(json);

        $('#comprehensive').text(data.analyse.ideological * 0.34 +
            data.analyse.voluntary * 0.1179 + data.analyse.post * 0.0817 +
            data.analyse.intellectual * 0.1055 * 0.1 +
            data.analyse.skills * 0.1055 +
            data.analyse.practical * 0.0332 +
            data.analyse.sports * 0.1634
        );

        $('#evaluate').text(data.evaluate);

        $('#realName').text(data.student.realName);
        $('#studentNumber').text(data.student.studentNumber);
        $('#wining').empty();
        $.each(data.winings, function (i, n) {
            $('#wining').append($('<p>').append($('<span>').text(n.winingContent))
                .append($('<span>').text("  得分:" + n.winingScore))
                .append($('<a href="javascript:;" class="delWining" data-id="' + n.winingId + '">').text('  删除')));
        });

        $('#diploma').empty();
        $.each(data.diplomas, function (i, n) {
            $('#diploma').append($('<p>').append($('<span>').text(n.diplomaName))
                .append($('<a href="javascript:;" class="delDiploma" data-id="' + n.diplomaId + '">').text('  删除')));
        });

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

    initData();

    $('#wining').delegate('.delWining', "click", function () {
        $.post(ajax_url.delWining, {winingId: $(this).attr('data-id')}, function (data) {
            if(data.state){
                initData();
            }
            Messenger().post({
                message: data.msg,
                type: data.state ? 'info' : 'error',
                showCloseButton: true
            });
        });
    });

    $('#diploma').delegate('.delDiploma', "click", function () {
        $.post(ajax_url.delDiploma, {diplomaId: $(this).attr('data-id')}, function (data) {
            if(data.state){
                initData();
            }
            Messenger().post({
                message: data.msg,
                type: data.state ? 'info' : 'error',
                showCloseButton: true
            });
        });
    });
});

var chart = {
    type: 'pie',
    options3d: {
        enabled: true,     //显示图表是否设置为3D， 我们将其设置为 true
        alpha: 15,         //图表视图旋转角度
        beta: 15,          //图表视图旋转角度
        depth: 50,         //图表的合计深度，默认为100
        viewDistance: 25   //定义图表的浏览长度
    }
};