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
        <title>Acces interdit</title>
        <%@include file="/WEB-INF/views/includes/css.jsp" %>
    </head>
    <body>

        <!-- On inclut le header qui change selon le role -->
        <%@include file="/WEB-INF/views/includes/header.jsp" %>

        <div class="container">

            <div class="page-header">
                <div class="row">
                    <div class="col-lg-8 col-md-7 col-sm-6">
                        <h1>Acces interdit</h1>
                        <p class="lead">
                            Vous n'avez pas les droits requis
                            pour accéder à cette page.
                        </p>
                    </div>
                    <div class="col-lg-4 col-md-5 col-sm-6">
                        <div class="sponsor">
                        </div>
                    </div>
                </div>
            </div>
            <!-- Footer du site -->
            <%@include file="/WEB-INF/views/includes/footer.jsp" %>
        </div>
        <%@include file="/WEB-INF/views/includes/js.jsp" %>
    </body>
</html>
