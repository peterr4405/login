<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>welcome</title>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css">
        <link href='css/process.css' rel="stylesheet" type="text/css">
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

            function back() {
                window.location.href = '../login.jsp';
            }
        </script>

    </head>
    <body>

        <button style="margin-left: 40px; margin-top: 40px" class="pure-button pure-button-primary" onclick="back()">回到上一頁</button>
        <div id="piechart" style="width: 900px; height: 500px;"></div>
    </body>
</html>
