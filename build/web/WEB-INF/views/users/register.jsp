<%-- 
    Document   : register
    Created on : 14 mars 2015, 05:34:12
    Author     : dominickmakome
--%>

<%@include file="/WEB-INF/views/includes/includes.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Forum - Inscription</title>
        <%@include file="/WEB-INF/views/includes/css.jsp" %>
    </head>
    <body>
        <%@include file="/WEB-INF/views/includes/front/menu.jsp" %>

        <div class="container">

            <div class="page-header">
                <div class="row">
                    <div class="col-lg-8 col-md-7 col-sm-6">
                        <h1>Inscription Forum J2EE</h1>
                        <p class="lead">Inscription sur le forum</p>
                    </div>
                    <div class="col-lg-4 col-md-5 col-sm-6">
                        <div class="sponsor">
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="row">
                <div class="col-lg-4"></div>
                <div class="col-lg-4">
                    <!-- Message flash du site internet -->
                    <%@include file="/WEB-INF/views/includes/messageFlash.jsp" %>
                    
                    <!-- Formulaire d'inscription -->
                    <form:form method="POST" commandName="user" cssClass="bs-component">
                        <div class="form-group">
                            <form:label cssClass="control-label" path="username">
                                Nom d'utilisateur
                            </form:label>
                            <form:input cssClass="form-control" path="username" required="required"/>
                            <form:errors cssClass="alert-error" path="username" />
                        </div>
                        
                        <div class="form-group">
                            <form:label cssClass="control-label" path="email">
                                Email
                            </form:label>
                            <form:input cssClass="form-control" path="email" type="email" required="required"/>
                            <form:errors cssClass="alert-error" path="username" />
                        </div>
                        
                        <div class="form-group">
                            <form:label cssClass="control-label" path="password">
                                Mot de passe
                            </form:label>
                            <form:password cssClass="form-control" path="password" required="required"/>
                        </div>
                        <input type="submit" class="btn btn-default" value="S'inscrire" />
                    </form:form>
                </div>
                <div class="col-lg-4"></div>
            </div>
            <!-- Footer du site -->
            <%@include file="/WEB-INF/views/includes/footer.jsp" %>
        </div>
        <%@include file="/WEB-INF/views/includes/js.jsp" %>
    </body>
</html>
