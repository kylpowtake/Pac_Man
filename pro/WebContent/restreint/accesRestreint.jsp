<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Accès restreint</title>
    </head>
    <body>
    	<a href="<%=request.getContextPath()+"/deconnexion"%>">Deconnexion</a>
    	<a href="<%=request.getContextPath()+"/restreint/accesRestreint2.jsp"%>">modifier le compte</a>
        <p>Vous êtes connecté(e) avec l'adresse ${sessionScope.sessionUtilisateur.pseudo}, vous avez bien accès à l'espace restreint.</p>
        tableau historique parties jouees</br>
        graphe si le temps 

    </body>
</html>