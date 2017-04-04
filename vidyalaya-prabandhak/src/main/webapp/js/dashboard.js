$(function () {

	var totalStudents = document.getElementById("total_students").value;
	var totalBoys = document.getElementById("total_boys").value;
	var totalGirls = document.getElementById("total_girls").value;


	var totalTeachers = document.getElementById("total_teachers").value;
	var totalMaleTeachers = document.getElementById("total_male_teachers").value;
	var totalFemaleTeachers = document.getElementById("total_female_teachers").value;

	var totalStaff = document.getElementById("total_staff").value;
	var totalMaleStaff = document.getElementById("total_male_staff").value;
	var totalFemaleStaff = document.getElementById("total_female_staff").value;

	$('#container_students').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: 'Students'
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
                text: 'Number Of Students'
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
            name: 'Total',
            data: [parseInt(totalStudents)]

        }, {
            name: 'Boys',
            data: [parseInt(totalBoys)]

        }, {
            name: 'Girls',
            data: [parseInt(totalGirls)]

        }]
    });

    
    $('#container_teachers').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: 'Teachers'
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
                text: 'Number Of Teachers'
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
            name: 'Total',
            data: [parseInt(totalTeachers)]

        }, {
            name: 'Male',
            data: [parseInt(totalMaleTeachers)]

        }, {
            name: 'Female',
            data: [parseInt(totalFemaleTeachers)]

        }]
    });


    $('#container_staff').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: 'Support Staff'
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
                text: 'Number Of Supporting staff members'
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
            name: 'Total',
            data: [parseInt(totalStaff)]

        }, {
            name: 'Male',
            data: [parseInt(totalMaleStaff)]

        }, {
            name: 'Female',
            data: [parseInt(totalFemaleStaff)]
        }]
    });    
});