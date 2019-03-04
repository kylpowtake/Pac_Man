<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <title>Accès restreint</title>
    </head>
    <body>
    
    
    
    	<div class="row">
    		 <a href="<%=request.getContextPath()+"/deconnexion"%>">
  				<img src="${pageContext.servletContext.contextPath}/inc/index.png" style="width:30px;height:40px;">
			</a> 
    	</div>
    	
    	<div class ="row">
    	</div>
    	
        <p>Vous êtes connecté(e) avec l'adresse ${sessionScope.sessionUtilisateur.pseudo}, vous avez bien accès à l'espace restreint.</p>
        tableau historique parties jouees</br>
        graphe si le temps 

    </body>
</html>