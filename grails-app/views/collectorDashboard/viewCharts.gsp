<html>
<head>
    <meta name="layout" content="collectorLayout">
    <title></title>
    <link rel="stylesheet" type="text/css" href="../css/table-data.css">
    <link rel="stylesheet" type="text/css" href="../css/generatorDashboard/button.css"/>
    <style type="text/css">
    th
    {
        width: 16% !important;
    }

    button{
        margin-top: 15px;
        margin-left: 10px;
    }
    </style>
    <!--Load the AJAX API-->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">

        google.charts.load('current', {'packages':['corechart']});

        google.charts.setOnLoadCallback(drawChart);

        function drawChart() {

            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Datas');
            data.addColumn('number', 'Coletas');
            <g:each in="${datas}" status="i" var="dat">
                var da = '${dat?.encodeAsJavaScript()}'
            data.addRows([
                    [da, 1]
            ]);
            </g:each>
            // Set chart options
            var options = {'title':'Coletas',
                'width':700,
                'height':600};

            // Instantiate and draw our chart, passing in some options.
            var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
            chart.draw(data, options);
        }
    </script>


</head>
</html>
<body>
<div id="chart_div"></div>

</body>