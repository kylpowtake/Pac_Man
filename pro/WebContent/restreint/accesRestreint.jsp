<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        
       <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        
        
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css">
        
        
        <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
        <title>Accès restreint</title>
    </head>
    <body>
    	
    	<div class="row">
    	<div class="col-lg-8">
    	
    	</div>
    	<div class="col-lg-4">
    		<a href="<%=request.getContextPath()+"/deconnexion"%>">
  				<img src="${pageContext.servletContext.contextPath}/inc/index.png" style="width:30px;height:40px;">
			</a> 
    	</div>
    	</div>


    	<div class ="row">
    		<div class="col-lg-4">
    		image banner 
    		</div>
    		<div class="col-lg-4">
    			<div class="card">
	  					<div class="card-header text-center bg-secondary"><h3>Historique de mes parties</h3></div>
	  					<div class="card-body">
			                <table id="dataTable" class="table table-striped table-bordered text-center">
			                  <thead>
			                    <tr>
			                      <th>Pseudo</th>
			                      <th>Score</th>
			                      <th>Date</th>
			                    </tr>
			                  </thead>
			                  <tbody>

			                  </tbody>
			                </table>
			           	</div>
			       </div>
    		</div>
    		<div class="col-lg-4">
    			<div class="card">
  					<div class="card-header text-center bg-secondary">
  						modifier son compte 
  					</div>
  					<div class="card-body">

						
						
						<form method="post" action="connexion">
						
			                <label for="pseudo">Nouveau pseudo</label>
			                <input type="text" id="pseudo" name="pseudo" value="<c:out value="${utilisateur.pseudo}"/>" class="form-control form-control-user"/>
			                <span class="erreur">${form.erreurs['pseudo']}</span>
			        
			
			                <label for="motdepasse">Ancien mot de passe</label>
			                <input type="password" id="motdepasse" name="motdepasse" value="" class="form-control form-control-user"/>
			                <span class="erreur">${form.erreurs['motdepasse']}</span>
			   
			                
			                <label for="motdepasse">Nouveau mot de passe</label>
			                <input type="password" id="motdepasse" name="motdepasse" value="" class="form-control form-control-user"/>
			                <span class="erreur">${form.erreurs['motdepasse']}</span>
			           
			                
			                <label for="motdepasse">Confirmation du mot de passe</label>
			                <input type="password" id="motdepasse" name="motdepasse" value="" class="form-control form-control-user"/>
			                <span class="erreur">${form.erreurs['motdepasse']}</span>
			            
			
			                <input type="submit" value="Modifier" class="btn btn-primary btn-user btn-block" />
			               

			            
			                
			                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>  
			        	</form>
						
						

  					</div>
  					
  					<div class="card-footer text-center bg-danger">
  						supprimer son compte 
  					</div>
  					
  				</div>
    		</div>
    	</div>
    	
        <p>Vous êtes connecté(e) avec le pseudo ${sessionScope.sessionUtilisateur.pseudo} et le mot de passe ${sessionScope.sessionUtilisateur.motDePasse}, vous avez bien accès à l'espace restreint.</p>
        tableau historique parties jouees</br>
        graphe si le temps 

    </body>
</html>

