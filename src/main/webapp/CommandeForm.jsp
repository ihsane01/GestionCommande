<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<LINK rel="STYLESHEET" type="text/css" href="form.css">
<LINK rel="STYLESHEET" type="text/css" href="form1.css">


<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">

        <h2>
            &nbsp;&nbsp;&nbsp;
            <a href="cmd/listcmd">List All Commandes</a>
             
        </h2>
   
     <div class="inputs">
        <c:if test="${commande != null}">
            <form action="commande?action=updatecmd" method="post">
        </c:if>
        <c:if test="${commande == null}">
            <form action="commande?action=insertcmd" method="post">
        </c:if>
       
                <h2>
                    <c:if test="${commande != null}">
                        Edit commande
                    </c:if>
                    <c:if test="${commande == null}">
                        Add New commande
                    </c:if>
                </h2>
       
                <c:if test="${commande != null}">
                    <input type="hidden" name="id" value="<c:out value='${commande.id}' />" />
                </c:if>           
          
           
             <label>Client</label>
                
               
                 <select name="idclient">
                              <c:forEach var="client" items="${listClient}">
                 
      <option value="${client.id}">${client.id}</option>
                 </c:forEach>

     </select>
     
                 
         
          
              <label>Qt</label>
                
              
                 		 
                    <input type="text" name="Qtecmd" size="45"
                            value="<c:out value='${commande.qtcmd}' />"
                        />
     
                 
              
                   
                <label>Produit</label>
                
            
                  <select name="idproduit">
                              <c:forEach var="produit" items="${listProduit}">
                 
      <option value="${produit.id}">${produit.id}</option>
                 </c:forEach>

     </select>
                 
                  
 <button type="submit" class="button-62" > Save</button>
            
        </form>
    </div> 
</body>
</html>