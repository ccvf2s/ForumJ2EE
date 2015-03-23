package forum.controllers;

import forum.interfaces.AuthorityInterface;
import forum.interfaces.CommentInterface;
import forum.interfaces.ThreadInterface;
import forum.interfaces.UserInterface;
import forum.repositories.AuthorityRepository;
import forum.repositories.CommentRepository;
import forum.repositories.ThreadRepository;
import forum.repositories.UserRepository;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller Core gère les actions simples et basiques du ForumJ2EE
 * @author dominickmakome
 */
@Controller
public class CoreController {
    
    public ThreadInterface iThread;
    public UserInterface iUser;
    public AuthorityInterface iAuth;
    public CommentInterface iComment;

    public CoreController() {
        
        this.iThread = new ThreadRepository();
        this.iUser = new UserRepository();
        this.iComment = new CommentRepository();
        this.iAuth = new AuthorityRepository();
    }
    
    /**
     * Accueil du forum.
     * 
     * @param map envoie des variables à la vue
     * @return le nom de la vue
     */
    @RequestMapping(value = "/index.htm", method = RequestMethod.GET)
    public String indexAction(Map <String, Object> map)
    {
        try
        {
            map.put("threads",this.iThread.findAll());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return "index";
    }
}
