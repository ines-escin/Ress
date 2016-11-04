<%--
  Created by IntelliJ IDEA.
  User: Leonardo
  Date: 15/10/2016
  Time: 21:44
--%>


<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="mapsLayout">
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
    <g:if test = "${flash.error}">
        <div class="alert-error" style="display: block"{flash.error}</div>
    </g:if>

</div>
</body>
</html>