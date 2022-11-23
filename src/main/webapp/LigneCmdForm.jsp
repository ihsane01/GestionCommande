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
 
        <h1 class="brand-title">LigneCmd Management</h1>
      
        <h2>
            
            &nbsp;&nbsp;&nbsp;
            <a href="lignecmd?action=list">List All LigneCmds</a>
             
        </h2>
        
    
     <div class="inputs">
        <c:if test="${lignecmd != null}">
            <form action="commande?action=updatecmd" method="post">
        </c:if>
        <c:if test="${lignecmd == null}">
            <form action="commande?action=insertligncmd" method="post">
        </c:if>
        
         
                <h2>
                    <c:if test="${lignecmd != null}">
                        Edit lignecmd
                    </c:if>
                    <c:if test="${lignecmd == null}">
                        Add New lignecmd
                    </c:if>
                </h2>

                <c:if test="${lignecmd != null}">
                    <input type="hidden" name="id" value="<c:out value='${lignecmd.id}' />" />
                </c:if>           

			 <label>qt</label>
               
                   <input type="text" name="Qtecmd" size="45"
                            value="<c:out value='${lignecmd.qtecmd}' />"
                        />
               
			 <label>produit</label>
              
                    <select name="idproduit">
                              <c:forEach var="produit" items="${listProduit}">
                 
      <option value="${produit.id}">${produit.id}</option>
                 </c:forEach>

     </select>
             
            
          
			 
              
           
             
              			
           
                 <!--  <input type="submit" value="Save" /> -->  
             <button type="submit" class="button-62" > Save</button>
                
        </form>
    </div> 
    	</div>
    
</body>
</html>