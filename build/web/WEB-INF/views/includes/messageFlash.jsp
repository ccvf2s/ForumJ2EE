<%-- 
    Document   : messageFlash
    Created on : 14 mars 2015, 16:29:32
    Author     : dominickmakome

    Message flash du site internet
--%>
<!-- Si le message d'erreur existe -->
<c:if test="${not empty messageError}">
    <div class="alert alert-danger">
        <a href="#" class="close" onclick="$(this).parent().slideUp()">x</a>
        <c:out value="${messageError}"></c:out>
    </div>
</c:if>

<c:if test="${not empty messageSuccess}">
    <div class="alert alert-success">
        <a href="#" class="close" onclick="$(this).parent().slideUp()">x</a>
        <c:out value="${messageSuccess}"></c:out>
    </div>
</c:if>
    
