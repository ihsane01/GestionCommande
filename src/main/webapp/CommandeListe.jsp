<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<LINK rel="STYLESHEET" type="text/css" href="table.css">
<LINK rel="STYLESHEET" type="text/css" href="form1.css">

<head>
<meta charset="ISO-8859-1">
<title>Commandes Store Application</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
<jsp:include page="Home.jsp" />

        <h2>
            <a href="commande?action=newcmd" class="button-62">Add New Commande</a>
            &nbsp;&nbsp;&nbsp;
            <a href="commande?action=listcmd"  class="button-62">List All Commandes</a>
             
        </h2>
   
 
        <table class="rwd-table mt-2" >
        	 <tbody>
     
            <tr>
                <th>ID</th>
                <th>id</th>
                <th>client</th>
         
                
                <th>Actions</th>
            </tr>
            <c:forEach var="commande" items="${listCommande}">
                <tr>
                    <td><c:out value="${commande.id}" /></td>
                    <td><c:out value="${commande.date}" /></td>
                    <td><c:out value="${commande.idclient}" /></td>
            
                    
                    <td>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="commande?action=deletecmd?id=<c:out value='${commande.id}' />">Delete</a>   
                       <a href="commande?action=detailscmd?id=<c:out value='${commande.id}' />">details</a>                     
                                          
                    </td>
                </tr>
            </c:forEach>
              </tbody>
        </table>
    </div>   
</body>
</html>