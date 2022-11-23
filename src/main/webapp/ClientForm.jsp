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
            <a href="client?action=list">List All Clients</a>
             
        </h2>
        
    
     <div class="inputs">
        <c:if test="${client != null}">
            <form action="client?action=update" method="post">
        </c:if>
        <c:if test="${client == null}">
            <form action="client?action=insert" method="post">
        </c:if>
        
         
                <h2>
                    <c:if test="${client != null}">
                        Edit client
                    </c:if>
                    <c:if test="${client == null}">
                        Add New client
                    </c:if>
                </h2>

                <c:if test="${client != null}">
                    <input type="hidden" name="id" value="<c:out value='${client.id}' />" />
                </c:if>           

			 <label>Nom</label>
               
                    <input type="text" name="nom" size="45"
                            value="<c:out value='${client.nom}' />"
                        />
               
			 <label>Prenom</label>
              
                    <input type="text" name="prenom" size="45"
                            value="<c:out value='${client.prenom}' />"
                    />
             
            
          
			 <label>Adress</label>
               
                    <input type="text" name="adress" size="5"
                            value="<c:out value='${client.adress}' />"
                    />
              
           
             
              			 <label>Tele</label>
              
                    <input type="text" name="tele" size="5"
                            value="<c:out value='${client.tele}' />"
                    />
           
                 <!--  <input type="submit" value="Save" /> -->  
             <button type="submit" class="button-62" > Save</button>
                
        </form>
    </div> 
    	</div>
    
</body>
</html>