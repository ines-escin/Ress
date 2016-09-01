<%--
  Created by IntelliJ IDEA.
  User: danielmaida
  Date: 08/10/15
  Time: 11:42
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="collectorLayout">
    <title></title>
    <link rel="stylesheet" type="text/css" href="../css/table-data.css">
</head>

<body>
<div id="page-content-wrapper">

    <div id="collectionHistory">
        <table>
            <thead>
            <tr>
                <th>Data</th>
                <th>Quantidade</th>
                <th>Ponto</th>
                <th>Ações</th>
            </tr>
            </thead>
            <tbody>
            <g:if test="${openPickupList.size() == 0}">
                <tr>
                    <td colspan="4" style="text-align: center;">Nenhum pedido de coleta está aberto no momento</td>
                </tr>
            </g:if>
            <g:else>
                <g:each var="pickup" in="${openPickupList}">
                    <tr>
                        <td>
                            ${pickup.date.dateTimeString}
                        </td>
                        <td>
                            ${pickup.residueAmount + " litros"}
                        </td>
                        <td>
                            ${pickup.generator.name}
                        </td>
                        <td>
                            <g:form controller="collectorDashboard">
                                <g:hiddenField name="id" value="${pickup.id}"/>
                                <g:actionSubmit value="Confirmar coleta" action="collect"/>
                            </g:form>
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