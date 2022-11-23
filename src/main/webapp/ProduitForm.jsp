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
            <a href="produit?action=listprd">List All Produits</a>
             
        </h2>
        
    
     <div class="inputs">
        <c:if test="${produit != null}">
            <form action="produit?action=updateprd" method="post">
        </c:if>
        <c:if test="${produit == null}">
            <form action="produit?action=insertprd" method="post">
        </c:if>
        
         
                <h2>
                    <c:if test="${produit != null}">
                        Edit produit
                    </c:if>
                    <c:if test="${produit == null}">
                        Add New produit
                    </c:if>
                </h2>

                <c:if test="${produit != null}">
                    <input type="hidden" name="id" value="<c:out value='${produit.id}' />" />
                </c:if>           

			 <label>Nom</label>
               
                    <input type="text" name="nom" size="45"
                            value="<c:out value='${produit.nom}' />"
                        />
               
			 <label>qtstock</label>
              
                    <input type="number" name="qtstock" size="45"
                            value="<c:out value='${produit.qtstock}' />"
                    />
             
            
          
			 <label>prix</label>
               
                    <input type="number" name="prix" size="5"
                            value="<c:out value='${produit.prix}' />"
                    />
              
           
             
              			 
           
                 <!--  <input type="submit" value="Save" /> -->  
             <button type="submit" class="button-62" > Save</button>
                
        </form>
    </div> 
    	</div>
    
</body>
</html>