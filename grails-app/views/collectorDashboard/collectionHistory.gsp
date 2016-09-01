

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="collectorLayout">
    <title></title>
    <link rel="stylesheet" type="text/css" href="../css/table-data.css">
    <style type="text/css">
        button{
            margin-top: 15px;
            margin-left: 10px;
        }
    </style>
</head>

<body>
<div id="page-content-wrapper">

    <div id ="collectionHistory">
        <table>
            <thead>
            <tr>
                <th>Nome</th>
                <th>Data</th>
                <th>Quantidade</th>
            </tr>
            </thead>

            <tbody>
            <g:if test="${closedPickups.size() == 0}" >
                <tr>
                    <td colspan="3" style="text-align: center;">Você não realizou nenhuma coleta até agora</td>
                </tr>
            </g:if>
            <g:else>
                <g:each var="pickup" in="${closedPickups}">
                    <tr>

                        <td>${pickup.generator.name}</td>

                        <td>${pickup.date.dateTimeString}</td>

                        <td>${pickup.residueAmount} litros</td>

                    </tr>
                </g:each>

            </g:else>
            </tbody>
        </table>
        <g:if test="${closedPickups.size() > 0}" >
        <button class="btn btn-first">
            <g:link controller="collectorDashboard" action="downloadExcelHistory">Download Excel</g:link>
        </button>
        </g:if>
    </div>
</div>
</body>
</html>