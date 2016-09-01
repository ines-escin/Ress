<%--
  Created by IntelliJ IDEA.
  User: danielmaida
  Date: 09/10/15
  Time: 11:42
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="generatorLayout">
    <title></title>
    <link rel="stylesheet" type="text/css" href="../css/generatorDashboard/pickup-screen.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="../css/table-data.css">
    <script src="../js/homepage/jquery.js"></script>
    <script src="../js/mask/jquery.maskedinput.js"></script>
    <script type="text/javascript">
        jQuery(function($){
            $("#residueAmount").mask("9?99999",{placeholder:" "});
        });

        function submitForm()
        {
            $("#submitButton").click();
        }
    </script>
    <style type="text/css">
    .status {
        font-family: monospace;
        display: inline-block;
        float: right;
        height: 2em;
        line-height: 1.2em;
        text-indent: 0;
        margin: 15px 10px;

        font-weight: bold;
        text-transform: uppercase;
        font-size: 1rem;
        display: inline-block;
        border: 1px solid;
        border-radius: 0.25rem;
        padding: 1px 3px 3px 3px;
    }

    .green {
        color: green;
    }

    .red {
        color: red;
    }
    </style>
</head>

<body>
<div id="page-content-wrapper">
    <div id="header-warning" style="margin-left: 14px;">
        <label style="font-size: 21px;">Solicitação de coleta de resíduos</label>
    </div>
    <g:if test="${pickup == null}">
        <div id="form-content">
            <g:form controller="generatorDashboard" action="savePickup">
                <label id="icon" for="residueAmount"><i class="fa fa-trash-o"></i></label>
                <input type="text" name="residueAmount" id="residueAmount" placeholder="Quantidade de resíduo (L)" required/>
                <a onclick="submitForm()" class="button">Solicitar coleta</a>
                <input id="submitButton" type="submit" style="display: none"/>
            </g:form>
        </div>
    </g:if>
    <g:else>
        <table>
            <thead>
            <tr>
                <th>Data</th>
                <th>Quantidade</th>
                <th>Status</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${pickup.date.dateTimeString}</td>
                <td>${pickup.residueAmount + " litros"} </td>
                <td>
                    <g:if test="${!pickup.status}">
                        <span class="status red">pendente</span>
                    </g:if>
                    <g:else>
                        <span class="status green">coleta iminente</span>
                    </g:else>
                </td>
            </tr>
            </tbody>
        </table>
    </g:else>
</div>
</body>
</html>