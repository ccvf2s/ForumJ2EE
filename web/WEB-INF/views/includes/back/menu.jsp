<%-- 
    Document   : menu
    Created on : 14 mars 2015, 06:59:56
    Author     : dominickmakome
    
    Menu backoffice
--%>
<div class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a href="index.htm" class="navbar-brand">Forum J2EE</a>
            <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div class="navbar-collapse collapse" id="navbar-main">
            <ul class="nav navbar-nav">
                <li>
                    <a href="index.htm">Accueil</a>
                </li>
                <li>
                    <a href="mes-sujets.htm">Liste de mes sujets</a>
                </li>
                <li>
                    <a href="creer-un-sujet.htm">Nouveau sujet</a>
                </li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <!-- On définie la variable pour l'affichage du username -->
                <sec:authentication var="principal" property="principal" />
                
                <li><a href="#">Bienvenue <c:out value="${principal.username}"></c:out></a></li>
                <li><a href="logout">Déconnexion</a></li>
            </ul>
        </div>
    </div>
</div>