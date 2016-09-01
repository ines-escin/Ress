<%--
  Created by IntelliJ IDEA.
  User: danielmaida
  Date: 08/10/15
  Time: 11:43
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="generatorLayout">
    <title></title>
    <link rel="stylesheet" type="text/css" href="../css/table-data.css">
</head>

<body>
<div id="page-content-wrapper">

    <div id ="collectionHistory">
        <table>
            <thead>
                <tr>
                    <th>Data</th>
                    <th>Quantidade</th>
                </tr>
            </thead>
            <tbody>
                <g:if test="${history.size() == 0}" >
                    <tr>
                        <td colspan="2" style="text-align: center;">Nenhuma coleta foi realizada em seu estabelecimento até o momento</td>
                    </tr>
                </g:if>
                <g:else>
                    <g:each var="pickup" in="${history}">
                        <tr>
                            <td>
                                ${pickup.date.dateTimeString}
                            </td>
                            <td>
                                ${pickup.residueAmount + " litros"}
                            </td>
                        </tr>
                    </g:each>
                </g:else>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>

