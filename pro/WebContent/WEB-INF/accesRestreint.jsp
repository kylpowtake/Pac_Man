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
        
        <!-- script pour le tableau de scores -->
        <script type="text/javascript">
		    $(document).ready(function() {
		        $('#dataTable').DataTable( {
		        	"responsive" : true,
		        	"bLengthChange":false,
		        	"iDisplayLength" : 5
		        } );
		    } );
	    </script>
	    <script>
	    	function ValidateForm() {
	    		if (confirm("voulez vous vraiment supprimer votre compte ?")) {
	    		    return true;
	    		  } else {
	    		    return false;
	    		  }
	    	}
	    </script>

        
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
			                      <th>Score</th>
			                      <th>Date</th>
			                    </tr>
			                  </thead>
			                  <tbody>
									<c:forEach var="name"  items="${requestScope['partie']}" >
	    							<tr>
	          							<td><c:out value="${name['score']}" /></td>
	          							<td><c:out value="${name['date']}" /></td>
	          						</tr>
								</c:forEach>
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

						
						
						<form method="post" action="gestionCompte">
						
							<label for="ancienMotDePasse">Mot de passe<span> *</span></label>
			                <input type="password" id="ancienpMotDePasse" name="ancienpMotDePasse" value="" class="form-control form-control-user"/>
			                <span class="erreur">${form.erreurs['ancienMotDePasse']}</span>
			                
			                <hr />
						
			                <label for="pseudo">Nouveau pseudo</label>
			                <input type="text" id="pseudo" name="pseudo" value="<c:out value="${utilisateur.pseudo}"/>" class="form-control form-control-user"/>
			                <span class="erreur">${form.erreurs['pseudo']}</span>
			   
			                
			                <label for="nouveauMotDePasse">Nouveau mot de passe</label>
			                <input type="password" id="nouveauMotDePasse" name="nouveauMotDePasse" value="" class="form-control form-control-user"/>
			                <span class="erreur">${form.erreurs['nouveauMotDePasse']}</span>
			           		<br />
			                
			                <label for="confMotDePasse">Confirmation du mot de passe</label>
			                <input type="password" id="confMotDePasse" name="confMotDePasse" value="" class="form-control form-control-user"/>
			                <span class="erreur">${form.erreurs['confMotDePasse']}</span>
			            
			
			                <input type="submit" value="Modifier" class="btn btn-primary btn-user btn-block" />
			               

			            
			                
			                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>  
			        	</form>
						
						

  					</div>
  					
  					<div class="card-footer text-center bg-danger">
  						<form method="get" action="gestionCompte">
  							 <input type="hidden" name="supprimer">
			                 <input type="submit" value="Supprimer son compte" class="btn btn-danger btn-user btn-block"  onclick="return ValidateForm();" />
			        	</form>
  					</div>
  					
  				</div>
    		</div>
    	</div>

    </body>
</html>


