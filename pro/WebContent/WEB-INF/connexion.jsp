<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Connexion</title>
        <link rel="icon" type="image/png" href="${pageContext.servletContext.contextPath}/inc/favicon.png"  />
        <link type="text/css" rel="stylesheet" href="inc/form.css" />
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        
        
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css">
        
        
        <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
    </head>
    <body>
    
    	<div class="contain">
    	
    	
    	
    		<!-- header -->
    		<div class="row" style="background-color:black;">
    			<!-- banner -->
    			<div class="col">
    				<img src="${pageContext.servletContext.contextPath}/inc/banner.gif" alt="" style="width:inherit;height:320px;">
    			</div>
    			<!-- connexion form -->
    			<div class="col">
    				<fieldset class="scheduler-border">
    					<legend class="scheduler-border">Connexion</legend>
    					<form method="post" action="connexion">
			                <label for="pseudo">Pseudo <span class="requis">*</span></label>
			                <input type="text" id="pseudo" name="pseudo" value="<c:out value="${utilisateur.pseudo}"/>" class="form-control form-control-user"/>
			                <span class="erreur">${form.erreurs['pseudo']}</span>
			        
			
			                <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
			                <input type="password" id="motdepasse" name="motdepasse" value="" class="form-control form-control-user"/>
			                <span class="erreur">${form.erreurs['motdepasse']}</span>
			                <br />
			
			                <input type="submit" value="Connexion" class="btn btn-primary btn-user btn-block" />
			               
			                <hr />
							<p>Pas encore de compte ? <a href="<%=request.getContextPath()+"/inscription"%>">Inscrivez vous</a></p>
			            
			                
			                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>  
			        	</form>
    				</fieldset>	
    			</div>
    		</div>
    	
    		
    		<hr/>
    		
    		<!-- tables -->
			<div class="row">
				
				<div class="col-lg-4">
					<div class="card">
  					<div class="card-header text-center bg-secondary">
  						<img src="${pageContext.servletContext.contextPath}/inc/pic1.png" alt="" style="width:200px;height:inherit;">
  					</div>
  					<div class="card-body">
		                <table class="table table-striped table-bordered text-center">
		                  <thead>
		                    <tr>
		                      <th>Position</th>
		                      <th>Pseudo</th>
		                      <th>Score</th>
		                    </tr>
		                  </thead>
		                  <tbody>
		                    <tr>
		                      <td><img src="${pageContext.servletContext.contextPath}/inc/gold.jpg" alt="" style="width:inherit;height:50px;"></td>
		                      <td>Personne1</td>
		                      <td>9001</td>
		                    </tr>
		                    <tr>
		                      <td><img src="${pageContext.servletContext.contextPath}/inc/silver.jpg" alt="" style="width:inherit;height:50px;"></td>
		                      <td>Personne2</td>
		                      <td>300</td>
		                    </tr>
		                    <tr>
		                      <td><img src="${pageContext.servletContext.contextPath}/inc/bronze.jpg" alt="" style="width:inherit;height:50px;"></td>
		                      <td>Personne3</td>
		                      <td>200</td>
		                    </tr>
		                    <tr>
		                      <td>4</td>
		                      <td>Personne4</td>
		                      <td>100</td>
		                    </tr>
		                    <tr>
		                      <td>5</td>
		                      <td>Personne5</td>
		                      <td>50</td>
		                    </tr>
		                  </tbody>
		                </table>
		           	</div>
		       		</div>
				</div>
				
				
				<div class="col-lg-8">
					<div class="card">
	  					<div class="card-header text-center bg-secondary"><h3>Tableau des scores</h3></div>
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
			                  
			                  	<c:forEach var="name"  items="${requestScope['partie']}" >
	    							<tr>
	    								<td><c:out value="${name['pseudoUtilisateur']}" /></td>
	          							<td><c:out value="${name['score']}" /></td>
	          							<td><c:out value="${name['date']}" /></td>
	          						</tr>
								</c:forEach>
			                  </tbody>
			                </table>
			           	</div>
			       </div>
			   </div>
		    </div>
		 </div>
    </body>
    
    
    
    <script type="text/javascript">
    $(document).ready(function() {
        $('#dataTable').DataTable( {
        	"responsive" : true,
        	"bLengthChange":false,
        	"iDisplayLength" : 5
        } );
    } );
    </script>
    
</html>