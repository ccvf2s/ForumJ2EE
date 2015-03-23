<%-- 
    Document   : header
    Created on : 14 mars 2015, 18:54:20
    Author     : dominickmakome
    Header du site internet
--%>
<!-- Si on est pas connecté -->
<sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
    <%@include file="/WEB-INF/views/includes/front/menu.jsp" %>
</sec:authorize>
<!-- Si on est connecté en tant que utilisateur -->
<sec:authorize ifAnyGranted="ROLE_USER">
    <%@include file="/WEB-INF/views/includes/back/menu.jsp" %>
</sec:authorize>
<!-- Si il est connecté en tant que admin -->
<sec:authorize ifAnyGranted="ROLE_ADMIN">
    <%@include file="/WEB-INF/views/includes/back/menu_admin.jsp" %>
</sec:authorize>