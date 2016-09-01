<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 16/10/2015
  Time: 09:28
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
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

</head>

<body>
<div id="page-content-wrapper">
       <table>
       <thead>
       <tr>
           <th>Nome</th>
           <th>Username</th>
           <th>Password</th>
           <th>Email</th>
           <th>Endereço</th>
       </tr>
       </thead>
       <tbody>
        <tr>
            <td>
                ${user.name}
            </td>
            <td>
                ${user.username}
            </td>
            <td>
                *************
            </td>
            <td>
                ${user.email}
            </td>
            <td>
                ${user.address.street + ", " + user.address.streetNumber + ", " + user.address.neighborhood + ", " + user.address.city + ", " + user.address.state + "\n" + user.address.cep}
            </td>
        </tr>
       </tbody>
   </table>
        <g:link controller="collectorDashboard" action="editAccountConfig"> <button class="btn btn-first"> Editar </button> </g:link>
</div>
</body>
</html>