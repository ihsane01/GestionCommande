<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<LINK rel="STYLESHEET" type="text/css" href="table.css">
<LINK rel="STYLESHEET" type="text/css" href="form1.css">

<head>
<meta charset="ISO-8859-1">
<title>>Produit Store Application</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
<jsp:include page="Home.jsp" />

        <h2>
            <a href="produit?action=newprd" class="button-62">Add New Produit</a>
            &nbsp;&nbsp;&nbsp;
            <a href="produit?action=listprd"  class="button-62">List All Produits</a>
             
        </h2>
   
 
        <table class="rwd-table mt-2" >
        	 <tbody>
     <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>qtstock</th>
                <th>prix</th>
                
                <th>Actions</th>
            </tr>
            <c:forEach var="produit" items="${listProduit}">
                <tr>
                    <td><c:out value="${produit.id}" /></td>
                    <td><c:out value="${produit.nom}" /></td>
                    <td><c:out value="${produit.qtstock}" /></td>
                    <td><c:out value="${produit.prix}" /></td>
                
                    
                    <td>
                        <a href="produit?action=editprd?id=<c:out value='${produit.id}' />"  class="button-62">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="produit?action=deleteprd?id=<c:out value='${produit.id}' />"  class="button-62">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
              </tbody>
        </table>
    </div>   
</body>
</html>