<%-- 
    Document   : login
    Created on : 14 mars 2015, 18:24:29
    Author     : dominickmakome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <link rel="stylesheet" href="public/css/login.css" media="screen">
        <title>Connectez vous au forum</title>
    </head>
    <body>
        
        <div class="container">
            <div class="login-container">
                <div id="output"></div>
                <div class="avatar"></div>
                <div class="form-box">
                    <form action="j_spring_security_check" method="post">
                        <input name="j_username" type="text" placeholder="Nom d'utilisateur">
                        <input name="j_password" type="password" placeholder="Mot de passe">
                        <button class="btn btn-info btn-block login" type="submit">Connexion</button>
                    </form>
                    <p>
                        <a href="index.htm">
                            Accueil du forum
                        </a>
                    </p>
                </div>
            </div>
        </div>
        
    </body>
</html>
