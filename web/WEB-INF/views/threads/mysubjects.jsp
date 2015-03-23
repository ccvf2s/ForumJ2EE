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
        <title>Forum - Liste de mes sujets</title>
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
                        <p class="lead">Liste de mes sujets</p>
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
                    <div class="bs-component">
                        <a href="creer-un-sujet.htm" title="Nouveau sujet" class="btn btn-primary">Nouveau sujet</a>
                        <table class="table table-striped table-hover ">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Title</th>
                                    <th>Options</th>
                                </tr>
                            </thead>
                            
                            <tbody>
                                <c:forEach items="${threads}" var="thread">
                                    <tr>
                                        <td><c:out value="${thread.id}"></c:out></td>
                                        <td><c:out value="${thread.title}"></c:out></td>
                                        <td>
                                            <a href="lire-sujet.htm?id=<c:out value="${thread.id}"></c:out>" class="glyphicon glyphicon-file">Lire</a>
                                            <a href="modifier-un-sujet.htm?id=<c:out value="${thread.id}"></c:out>" class="glyphicon glyphicon-pencil">Modifier</a>
                                            <a onclick="redirectDelete(<c:out value="${thread.id}"></c:out>,'thread')" href="#" class="glyphicon glyphicon-trash">Supprimer</a>
                                            <a href="manage-comments.htm?id=<c:out value="${thread.id}"></c:out>" class="glyphicon glyphicon-edit">Commentaires</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <!-- Footer du site -->
            <%@include file="/WEB-INF/views/includes/footer.jsp" %>
        </div>
        <%@include file="/WEB-INF/views/includes/js.jsp" %>
    </body>
</html>
