<%-- 
    Document   : js
    Created on : 14 mars 2015, 06:49:40
    Author     : dominickmakome

    Cette page contient la majorit� des fichiers js qui seront ins�r�s
    dans toutes les pages.
--%>
<script src="public/js/jquery-1.10.2.min.js"></script>
<script src="public/js/bootstrap.min.js"></script>
<script src="public/js/bootswatch.js"></script>

<script type="text/javascript">
    //Redirection pour la suppression(d'un commentaire, d'un sujet ou d'un user)
    function redirectDelete(id,action)
    {
        
        if(confirm("�tes vous s�r(e) de vouloir supprimer?(Cette action est irr�versible!!!)"))
        {
            switch(action)
            {
                case "comment":
                    document.location.href = "delete-comment.htm?id="+id;
                break;
                
                case "thread":
                    document.location.href = "supprimer-sujet.htm?id="+id;
                break;
                
                case "user":
                    document.location.href = "delete-user.htm?id="+id;
                break;
                
                default:
                    return false;
                  
            }
        }
        return false;
    }
</script>