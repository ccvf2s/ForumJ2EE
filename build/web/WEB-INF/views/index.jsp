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
                        <p class="lead">Forum développé en J2EE</p>
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
                    <table class="table table-striped table-hover ">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Title</th>
                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach items="${threads}" var="thread">
                                <tr>
                                    <td><c:out value="${thread.id}"></c:out></td>
                                        <td>
                                            <a href="lire-sujet.htm?id=<c:out value="${thread.id}"></c:out>" class="glyphicon glyphicon-file">
                                            <c:out value="${thread.title}"></c:out>
                                            </a>

                                            <i>Par <c:out value="${thread.users.username}"></c:out></i>
                                        </td>
                                    </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                
                <div class="col-lg-3"></div>
            </div>
            <!-- Footer du site -->
            <%@include file="/WEB-INF/views/includes/footer.jsp" %>
        </div>
        <%@include file="/WEB-INF/views/includes/js.jsp" %>
    </body>
</html>
