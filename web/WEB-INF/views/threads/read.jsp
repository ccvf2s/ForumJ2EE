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
        <title>
            <c:out value="${thread.title}"></c:out>
        </title>
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
                        <p class="lead">
                            <c:out value="${thread.title}"></c:out>
                        </p>
                    </div>
                    <div class="col-lg-4 col-md-5 col-sm-6">
                        <div class="sponsor">
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="row">
                <div class="col-lg-12">
                    <!-- Message flash du site internet -->
                    <%@include file="/WEB-INF/views/includes/messageFlash.jsp" %>
                    <!-- Titre du fil -->
                    <h2 class="label label-success"><c:out value="${thread.title}"></c:out></h2>
                    <p>
                        <c:out value="${thread.content}"></c:out>
                    </p>
                    
                    <div class="row">
                        <div class="col-lg-4"></div>
                        
                        <div class="col-lg-4">
                            <c:forEach items="${comments}" var="comment">
                                <hr>
                                <div class="media">
                                    <div class="media-left">
                                      <a href="#">
                                          <i class="media-object glyphicon glyphicon-user" style="font-size:4em;"></i>
                                      </a>
                                    </div>
                                    <div class="media-body">
                                        <h4 class="media-heading">
                                            <c:out value="${comment.users.username}"></c:out>
                                        </h4>
                                        <c:out value="${comment.content}"></c:out>
                                    </div>
                                </div>
                            </c:forEach>
                            <hr>
                            <!-- Formulaire de modification pour les commentaires -->
                            <sec:authorize ifNotGranted="ROLE_ANONYMOUS">
                                <form:form method="POST" commandName="comment" cssClass="bs-component">
                                    <div class="form-group">
                                        <form:label cssClass="control-label" path="content">
                                            Commentaire
                                        </form:label>
                                        <form:textarea cssClass="form-control" path="content" required="required"/>
                                        <form:errors cssClass="alert-error" path="content" />
                                    </div>
                                    <input type="submit" class="btn btn-default" value="Commenter" />
                                </form:form>
                            </sec:authorize>
                        </div>
                        
                        <div class="col-lg-4"></div>
                    </div>
                </div>
            </div>
            <!-- Footer du site -->
            <%@include file="/WEB-INF/views/includes/footer.jsp" %>
        </div>
        <%@include file="/WEB-INF/views/includes/js.jsp" %>
    </body>
</html>
