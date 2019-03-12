<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Inscription</title>
        <link type="text/css" rel="stylesheet" href="inc/css/inscription.css" />       
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    </head>
    <body>
    
    	<div class="container">
			<div class="row">
				<div class="panel panel-primary">
					<div class="panel-body">
						<form method="post" action="inscription">
							<div class="form-group">
								<h2>Créer un compte</h2>
							</div>
							
							
							<div class="form-group">
								<label for="nom" class="control-label">Pseudo d'utilisateur <span class="requis">*</span></label>
		                		<input type="text" id="nom" name="pseudo" value="<c:out value="${utilisateur.pseudo}"/>" maxlength="50" class="form-control" />
		                		<span class="erreur">${form.erreurs['pseudo']}</span>
	                		</div>
							
							
							
							
							<div class="form-group">
							<label for="motdepasse" class="control-label">Mot de passe <span class="requis">*</span></label>
			                <input type="password" id="motdepasse" name="motdepasse" value="" maxlength="25" class="form-control"/>
			                <span class="erreur">${form.erreurs['motdepasse']}</span>
							</div>
			
							<div class="form-group">
			                <label for="confirmation" class="control-label">Confirmation du mot de passe <span class="requis">*</span></label>
			                <input type="password" id="confirmation" name="confirmation" value=""  maxlength="25" class="form-control" />
			                <span class="erreur">${form.erreurs['confirmation']}</span>
							</div>
							
							<div class="form-group">
							 <input type="submit" value="Inscription" class="btn btn-info btn-block" />
							</div>
			
				
							<hr>
							<p>Vous avez déjà un compte? <a href="<%=request.getContextPath()+"/connexion"%>">Connectez vous</a></p>
						</form>
					</div>
				</div>
			</div>
		</div>
    </body>
</html>