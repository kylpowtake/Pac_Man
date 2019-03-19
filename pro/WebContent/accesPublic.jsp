<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Accès public</title>
       	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    </head>
    <body>
    
    
    
    <!--
    style="background-image:url(${pageContext.servletContext.contextPath}/inc/youshallnotpass.jpg); background-repeat:space;"
                    <p>Vous n'avez pas accès à l'espace restreint : vous devez vous <a href="connexion">connecter</a> d'abord. </p>	
    
    -->
                            <p>Vous n'avez pas accès à l'espace restreint : vous devez vous <a href="connexion">connecter</a> d'abord. </p>	
   
    <div class="row align-items-center" style="background-image:url(${pageContext.servletContext.contextPath}/inc/youshallnotpass.jpg); background-repeat:space">
    	<img src="/pro/inc/youshallnotpass.jpg"  >
	</div>	
</body>
</html>