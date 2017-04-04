$(function () {
    $('#container_students').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: 'Monthly Attendance'
        },
        xAxis: {
            categories: [
                ''
            ],
            crosshair: true
        },
        yAxis: {
            min: 0,
            title: {
                text: 'Percent Attendance'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.1f} </b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: 'June',
            data: [50]

        }, {
            name: 'July',
            data: [30]

        }, {
            name: 'August',
            data: [20]

        }
        , {
            name: 'September',
            data: [20]

        }, {
            name: 'October',
            data: [20]

        }, {
            name: 'November',
            data: [20]

        }, {
            name: 'December',
            data: [20]

        }, {
            name: 'January',
            data: [20]

        }, {
            name: 'February',
            data: [20]

        }, {
            name: 'March',
            data: [20]

        }, {
            name: 'April',
            data: [20]

        }]
    });
    
    
    $('#container_teachers').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: 'Examinationwise Percentage'
        },
        xAxis: {
            categories: [
                ''
            ],
            crosshair: true
        },
        yAxis: {
            min: 0,
            title: {
                text: 'Percentage Marks'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.1f} </b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: 'Unit Test 1',
            data: [79]

        }, {
            name: 'Unit Test 2',
            data: [90]

        }, {
            name: 'Semester 1',
            data: [85]

        }
        , {
            name: 'Unit Test 3',
            data: [80]

        }, {
            name: 'Unit Test 4',
            data: [71]

        }, {
            name: 'Semester 2',
            data: [75]
        }]
    });


    $('#container_staff').highcharts({
            title: {
                text: 'Subject Growth Chart',
                x: -20 //center
            },
            xAxis: {
                categories: ['Unit Test 1','Unit Test 2','Semester 1','Unit Test 3','Unit Test 4','Semester 2']
            },
            yAxis: {
                title: {
                    text: 'Percentage Marks'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'middle',
                borderWidth: 0
            },
            series: [{
                name: 'Marathi',
                data: [60, 65, 70, 75, 80, 85]
            }, {
                name: 'Hindi',
                data: [70, 70,70, 70, 70,70]
            }, {
                name: 'English',
                data: [95, 90, 80, 60]
            }, {
                name: 'Mathematics',
                data: [45, 82, 65,70, 69, 95]
            }, {
                name: 'Science',
                data: [82, 65,70, 69, 95, 45]
            }, {
                name: 'Hostory',
                data: [70, 59, 90, 45, 82, 65]
            }]
            });
});