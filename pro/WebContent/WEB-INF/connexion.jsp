<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Connexion</title>
        <link type="text/css" rel="stylesheet" href="inc/form.css" />
    </head>
    <body>
        <form method="post" action="connexion">
            <fieldset>
                <legend>Connexion</legend>
                <p>Vous pouvez vous connecter via ce formulaire.</p>
                
                <c:if test="${empty sessionScope.sessionUtilisateur && !empty requestScope.intervalleConnexions}">
                    <p class="info">(Vous ne vous êtes pas connecté(e) depuis ce navigateur depuis ${requestScope.intervalleConnexions})</p>
                </c:if>

                <label for="nom">Pseudo <span class="requis">*</span></label>
                <input type="text" id="pseudo" name="pseudo" value="<c:out value="${utilisateur.pseudo}"/>" size="20" maxlength="60" />
                <span class="erreur">${form.erreurs['pseudo']}</span>
                <br />

                <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
                <input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['motdepasse']}</span>
                <br />

                <input type="submit" value="Connexion" class="sansLabel" />
                <br />
                
                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
                
                <%-- Vérification de la présence d'un objet utilisateur en session --%>

                <c:if test="${!empty sessionScope.sessionUtilisateur}">

                    <%-- Si l'utilisateur existe en session, alors on affiche son pseudo. --%>

                    <p class="succes">Vous êtes connecté(e) avec le pseudo : ${sessionScope.sessionUtilisateur.pseudo}</p>

                </c:if>
            </fieldset>
        </form>
        
    </body>
</html>