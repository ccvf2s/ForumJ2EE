<%-- 
    Document   : index
    Created on : 14 mars 2015, 04:58:37
    Author     : dominickmakome
--%>
<%@include file="/WEB-INF/views/includes/includes.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Accueil du forum</title>
        <%@include file="/WEB-INF/views/includes/css.jsp" %>
    </head>
    <body>
        
        <!-- On inclut le header qui change selon le role -->
        <%@include file="/WEB-INF/views/includes/header.jsp" %>

        <div class="container">

            <div class="page-header">
                <div class="row">
                    <div class="col-lg-8 col-md-7 col-sm-6">
                        <h1>Forum J2EE</h1>
                        <p class="lead">Création d'un sujet</p>
                    </div>
                    <div class="col-lg-4 col-md-5 col-sm-6">
                        <div class="sponsor">
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="row">
                <div class="col-lg-3"></div>
                
                <div class="col-lg-6">
                    <!-- Message flash du site internet -->
                    <%@include file="/WEB-INF/views/includes/messageFlash.jsp" %>
                    <!-- Formulaire d'inscription -->
                    <form:form method="POST" commandName="thread" cssClass="bs-component">
                        <div class="form-group">
                            <form:label cssClass="control-label" path="title">
                                Titre du sujet
                            </form:label>
                            <form:input cssClass="form-control" path="title" required="required"/>
                            <form:errors cssClass="alert-error" path="title" />
                        </div>
                        
                        <div class="form-group">
                            <form:label cssClass="control-label" path="content">
                                Contenu du sujet
                            </form:label>
                            <form:textarea cssClass="form-control" path="content" required="required"/>
                            <form:errors cssClass="alert-error" path="content" />
                        </div>
                        
                        <div class="checkbox">
                            <form:label cssClass="control-label" path="enabled">
                                <form:checkbox path="enabled"/> Publique/Privé
                            </form:label>
                        </div>
                        <input type="submit" class="btn btn-default" value="Créer un sujet" />
                    </form:form>
                </div>
                
                <div class="col-lg-3"></div>
            </div>
            <!-- Footer du site -->
            <%@include file="/WEB-INF/views/includes/footer.jsp" %>
        </div>
        <%@include file="/WEB-INF/views/includes/js.jsp" %>
    </body>
</html>
