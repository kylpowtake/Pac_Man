<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Accès restreint 2</title>
    </head>
    <body>
    	<a href="<%=request.getContextPath()+"/deconnexion"%>">Deconnexion</a>
    	<a href="<%=request.getContextPath()+"/restreint/accesRestreint.jsp"%>">retour</a>
        <p>Vous êtes connecté(e) avec l'adresse ${sessionScope.sessionUtilisateur.pseudo}, vous avez bien accès à l'espace restreint 2.</p>
    </body>
</html>