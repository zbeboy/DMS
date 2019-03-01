$(document).ready(function () {
    var ajax_url = {
        analyse: web_path + '/web/analyse/one'
    };

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