<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>welcome</title>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
            google.charts.load('current', {'packages': ['corechart']});
            google.charts.setOnLoadCallback(drawChart);

            function drawChart() {

                var data = google.visualization.arrayToDataTable([
                    ['${user}', '資產'],
                    ['股票', ${stock}],
                    ['基金', ${fund}],
                    ['定存', ${fixed}],
                    ['活動資金', ${activity}]
                ]);

                var options = {
                    title: '${user}的帳戶明細'
                };

                var chart = new google.visualization.PieChart(document.getElementById('piechart'));

                chart.draw(data, options);
            }
        </script>

    </head>
    <body>
        <div id="piechart" style="width: 900px; height: 500px;"></div>
    </body>
</html>
