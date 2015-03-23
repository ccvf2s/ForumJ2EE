package forum.controllers;

import forum.entities.Authorities;
import forum.entities.Comments;
import forum.entities.Users;
import forum.interfaces.AuthorityInterface;
import forum.interfaces.CommentInterface;
import forum.interfaces.ThreadInterface;
import forum.interfaces.UserInterface;
import forum.repositories.AuthorityRepository;
import forum.repositories.CommentRepository;
import forum.repositories.ThreadRepository;
import forum.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller qui gère les commentaires.
 * @author dominickmakome
 */
@Controller
public class CommentsController {
    
    public ThreadInterface iThread;
    public UserInterface iUser;
    public AuthorityInterface iAuth;
    public CommentInterface iComment;
    
    public CommentsController() {
        this.iThread = new ThreadRepository();
        this.iUser = new UserRepository();
        this.iAuth = new AuthorityRepository();
        this.iComment = new CommentRepository();
    }
    
    /**
     * Cette fonction éffectue la suppression du commentaire dont l'id
     * est donné en paramètre.
     * 
     * @param id id du commentaire à supprimer
     * @return L'url de retour
     */
    @RequestMapping(value = "/delete-comment.htm", method = RequestMethod.GET)
    public String doDeleteAction(@RequestParam String id)
    {
        int id_thread = 0;
        boolean authority = false;
        try
        {
            Comments c = this.iComment.find(new Integer(id));
            //On récupère l'id du thread
            id_thread = c.getThreads().getId();
            //On vérifit que le commentaire existe
            if(!c.equals(null))
            {
                //On récupère l'utilisateur pour être sur qu'il est le bon
                Users u = c.getUsers();
                //On récupère l'utilisateur connecté
                User userCurrent = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                //On récupère l'utilisateur
                Users user = this.iUser.findByUsername(userCurrent.getUsername());
                
                //Si c'est le bon utilisateur on le supprime
                if(u.getUsername().equals(user.getUsername()))
                {
                    //On supprime le commentaire
                    this.iComment.delete(c);
                }
                else
                {
                    //L'autorité
                    Authorities au = new Authorities();
                    for(Object a: user.getAuthoritieses())
                    {
                        au = (Authorities)a;
                        //Si l'autorité est = à manager ou admin
                        if(au.getAuthority().equals(UsersController.ROLE_ADMIN) ||
                           au.getAuthority().equals(UsersController.ROLE_MANAGER))
                        {
                            authority = true;
                        }
                    }
                    
                    //Si il a la bonne autorité
                    if(authority)
                    {
                        //On applique la suppréssion
                        this.iComment.delete(c);
                    }
                    else
                    {
                        return "redirect:/acces-interdit.htm";
                    }
                }                
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return "redirect:/manage-comments.htm?id="+id_thread;
    }
    
}
