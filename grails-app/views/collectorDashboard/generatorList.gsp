<%--
  Created by IntelliJ IDEA.
  User: danielmaida
  Date: 19/10/15
  Time: 16:28
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="collectorLayout">
    <title></title>
    <link rel="stylesheet" type="text/css" href="../css/table-data.css">
    <style type="text/css">
        th
        {
            width: 30%;
        }

        .address
        {
            width: 100% !important;
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
                <th>Email</th>
                <th class="address">Endereco</th>
            </tr>
            </thead>
            <tbody>
            <g:if test="${userList.size() == 0}" >
                <tr>
                    <td colspan="3" style="text-align: center;">Nenhum gerador de resíduo cadastrado no sistema</td>
                </tr>
            </g:if>
            <g:else>
                <g:each var="generator" in="${userList}">
                    <tr>
                        <td>
                            ${generator.name}
                        </td>
                        <td>
                            ${generator.email}
                        </td>
                        <td>
                            ${generator.address.street + ", " + generator.address.streetNumber + ", " + generator.address.neighborhood + ", " + generator.address.city + ", " + generator.address.state + "\n" + generator.address.cep}
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