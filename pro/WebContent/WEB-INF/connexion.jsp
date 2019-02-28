<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Connexion</title>
        <link type="text/css" rel="stylesheet" href="inc/form.css" />
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    </head>
    <body>
    
    	<div class="contain">
    	
    		<!-- header -->
    		<div class="row" style="background-color:black;">
    			<!-- banner -->
    			<div class="col">
    				<img src="${pageContext.servletContext.contextPath}/inc/banner.gif" alt="" style="width:inherit;height:300px;">
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
    	
    		
    		<hr />
    		
    		<!-- table -->
			<div class="row">
				 <div class="card shadow mb-4">
		            <div class="card-header py-3">
		              <h6 class="m-0 font-weight-bold text-primary text-center">Classement</h6>
		            </div>
		            <div class="card-body">
		              <div class="table-responsive">
		                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
		                  <thead>
		                    <tr>
		                      <th>Position</th>
		                      <th>Pseudo</th>
		                      <th>Score</th>
		                    </tr>
		                  </thead>
		                  <tbody>
		                    <tr>
		                      <td><img src="${pageContext.servletContext.contextPath}/inc/medals.jpg" alt="" style="width:inherit;height:20px;"></td>
		                      <td>Customer Support</td>
		                      <td>New York</td>
		                    </tr>
		                    <tr>
		                      <td><img src="${pageContext.servletContext.contextPath}/inc/medals.jpg" alt="" style="width:inherit;height:20px;"></td>
		                      <td>Customer Support</td>
		                      <td>New York</td>
		                    </tr>
		                    <tr>
		                      <td><img src="${pageContext.servletContext.contextPath}/inc/medals.jpg" alt="" style="width:inherit;height:20px;"></td>
		                      <td>Customer Support</td>
		                      <td>New York</td>
		                    </tr>
		                    <tr>
		                      <td>4</td>
		                      <td>Customer Support</td>
		                      <td>New York</td>
		                    </tr>
		                    <tr>
		                      <td>5</td>
		                      <td>Customer Support</td>
		                      <td>New York</td>
		                    </tr>
	
		                  </tbody>
		                </table>
		              </div>
		            </div>
		          </div>
			</div>	
		</div>
    </body>
</html>