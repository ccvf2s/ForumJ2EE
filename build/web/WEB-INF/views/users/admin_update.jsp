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
            Modification de <c:out value="${user.username}"></c:out>
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
                            Modification de "<strong><c:out value="${user.username}"></c:out></strong>"
                        </p>
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
                    <!-- Formulaire de modification -->
                    <form method="POST" class="bs-component">
                        <div class="form-group">
                            <label for="username" class="control-label">
                                Username
                            </label>
                            <input id="username" name="username" class="form-control" disabled="disabled" type="text" value="<c:out value="${user.username}"></c:out>">
                        </div>
                        
                        <div class="form-group">
                            <label for="email" class="control-label">
                                Email
                            </label>
                            <input id="email" name="email" class="form-control" disabled="disabled" type="email" value="<c:out value="${user.email}"></c:out>">
                        </div>
                        
                        <div class="form-group">
                            <select name="authority" class=form-control">
                                <c:forEach items="${status}" var="statu">
                                    <option value="<c:out value="${statu}"></c:out>">
                                        <c:out value="${statu}"></c:out>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <input type="submit" class="btn btn-default" value="Modifier" />
                    </form>
                </div>
                
                <div class="col-lg-3"></div>
            </div>
            <!-- Footer du site -->
            <%@include file="/WEB-INF/views/includes/footer.jsp" %>
        </div>
        <%@include file="/WEB-INF/views/includes/js.jsp" %>
    </body>
</html>
