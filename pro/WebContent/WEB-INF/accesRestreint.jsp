<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>


    <head>
        <title>Accès restreint</title>
        
    	<link type="text/css" rel="stylesheet" href="inc/css/compte.css" />
       	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
       	<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css">
        
        <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
        
       	<script src="https://cdn.anychart.com/releases/v8/js/anychart-base.min.js?hcode=be5162d915534272a57d0bb781d27f2b"></script>
		<script src="https://cdn.anychart.com/releases/v8/js/anychart-ui.min.js?hcode=be5162d915534272a57d0bb781d27f2b"></script>
		<script src="https://cdn.anychart.com/releases/v8/js/anychart-exports.min.js?hcode=be5162d915534272a57d0bb781d27f2b"></script>
		<link href="https://cdn.anychart.com/releases/v8/css/anychart-ui.min.css?hcode=be5162d915534272a57d0bb781d27f2b" type="text/css" rel="stylesheet">
		<link href="https://cdn.anychart.com/releases/v8/fonts/css/anychart-font.min.css?hcode=be5162d915534272a57d0bb781d27f2b" type="text/css" rel="stylesheet">
		        
        <!-- script pour le tableau de scores -->
        <script type="text/javascript">
		    $(document).ready(function() {
		        $('#dataTable').DataTable( {
		        	"responsive" : true,
		        	"bLengthChange":false,
		        	"iDisplayLength" : 4
		        } );
		    } );
	    </script>
	 
	    <!--  script pour validation du formulaire  -->
	    <script>
	    	function ValidateForm() {
	    		if (confirm("voulez vous vraiment supprimer votre compte ?")) {
	    		    return true;
	    		  } else {
	    		    return false;
	    		  }
	    	}
	    </script>
    </head>
    
    
    <body>
    	<div class="contain">
    	
    	
	    	<div class="row head" style="background-color:red;">
		    	<div class="col-sm"></div>
				<div class="col-sm text-center">
					<p class="pseudo">Bienvenue ${sessionScope.sessionUtilisateur.pseudo}</p>
				</div>
				<div class="col-sm text-right">
					<a href="<%=request.getContextPath()+"/deconnexion"%>">
			  			<img src="${pageContext.servletContext.contextPath}/inc/iconeDeconnexion.png" title="Déconnexion"  style="width:50px;height:65px;">
			  		</a>
			  	</div>
		    </div>
		    	

	    	<div class ="row">
	    		<div class="col-lg-8">
	    			<div class="card">
		  					<div class="card-header text-center bg-secondary"><h3>Historique de mes parties</h3></div>
		  					<div class="card-body">
				                <table id="dataTable" class="table table-striped table-bordered text-center">
				                  <thead>
				                    <tr>
				                      <th>Score</th>
				                      <th>fantomes mangés</th>
				                      <th>pac-gommes mangés</th>
				                      <th>capsules mangés</th>
				                      <th>map effectués</th>
				                      <th>nombre de tours joués</th>
				                      <th>Date</th>
				                    </tr>
				                  </thead>
				                  <tbody>
										<c:forEach var="name"  items="${requestScope['partie']}" >
		    							<tr>
		          							<td><c:out value="${name['score']}" /></td>
		          							<td><c:out value="${name['fantomesManges']}" /></td>
		          							<td><c:out value="${name['capsulesMangees']}" /></td>
		          							<td><c:out value="${name['pacGommesMangees']}" /></td>
		          							<td><c:out value="${name['mapsEffectuees']}" /></td>
		          							<td><c:out value="${name['pasEffectues']}" /></td>
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
	  						<h3>Modifier son compte</h3>
	  					</div>
	  					<div class="card-body">
		
							<form method="post" action="gestionCompte">
							
								<label for="ancienMotDePasse">Mot de passe<span class="requis"> *</span></label>
				                <input type="password" id="ancienpMotDePasse" name="ancienpMotDePasse" value="" class="form-control form-control-user"/>
				                <span class="erreur">${form.erreurs['ancienMotDePasse']}</span>
				                
				                <hr style="border: 0;height: 30px;background-image: url(${pageContext.servletContext.contextPath}/inc/hr.png);background-repeat: round;"/>
							
				                <label for="pseudo">Nouveau pseudo</label>
				                <input type="text" id="pseudo" name="pseudo" value="<c:out value="${utilisateur.pseudo}"/>" class="form-control form-control-user"/>
				                <span class="erreur">${form.erreurs['pseudo']}</span>
			
				   
				                <label for="nouveauMotDePasse">Nouveau mot de passe</label>
				                <input type="password" id="nouveauMotDePasse" name="nouveauMotDePasse" value="" class="form-control form-control-user"/>
				                <span class="erreur">${form.erreurs['nouveauMotDePasse']}</span>
				            
				           
				                
				                <label for="confMotDePasse">Confirmation du mot de passe</label>
				                <input type="password" id="confMotDePasse" name="confMotDePasse" value="" class="form-control form-control-user"/>
				                <span class="erreur">${form.erreurs['confMotDePasse']}</span>
				            	<br />
				
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
	    	
    	
    	
    		<div id="container" style="height:220px;"></div>
    	
    	<script>
			anychart.onDocumentReady(function () {
				
			    // create data set on our data
			    var dataSet = anychart.data.set(${requestScope['graph']});

			    // map data for the first series, take x from the zero column and value from the first column of data set
			    var seriesData = dataSet.mapAs({'x': 0, 'value': 1});

			    // create line chart
			    var chart = anychart.line();

			    // turn on chart animation
			    chart.animation(true);

			    // set chart padding
				chart.padding(10, 10, 0, 10);

			    // turn on the crosshair
			    chart.crosshair().enabled(true)
			    				 .displayMode('sticky');


			    // set chart title text settings
			    chart.title('Evolution du score');

			    // create first series with mapped data
			    var series = chart.line(seriesData);
			    

			    // set container id for the chart
			    chart.container('container');
			    // initiate chart drawing
			    chart.draw();
			});
		</script>

</div>

    </body>
</html>


