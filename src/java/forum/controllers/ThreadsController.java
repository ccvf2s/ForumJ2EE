package forum.controllers;

import forum.entities.Authorities;
import forum.entities.Threads;
import forum.entities.Users;
import forum.entities.Comments;
import forum.interfaces.AuthorityInterface;
import forum.interfaces.CommentInterface;
import forum.interfaces.ThreadInterface;
import forum.interfaces.UserInterface;
import forum.repositories.AuthorityRepository;
import forum.repositories.CommentRepository;
import forum.repositories.ThreadRepository;
import forum.repositories.UserRepository;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Gère les fils(sujets) correspondants pour le Forum.
 * @author dominickmakome
 */
@Controller
public class ThreadsController {
    
    public ThreadInterface iThread;
    public UserInterface iUser;
    public AuthorityInterface iAuth;
    public CommentInterface iComment;
    

    public ThreadsController() {
        this.iThread = new ThreadRepository();
        this.iUser = new UserRepository();
        this.iAuth = new AuthorityRepository();
        this.iComment = new CommentRepository();
    }
    
    /**
     * Cette fonction envoie la page pour la creation d'un nouveau sujet.
     * 
     * @param map permet d'envoyer des variables à la vue 'threads/create'
     * @return la vue de l'url
     */
    @RequestMapping(value = "/creer-un-sujet.htm", method = RequestMethod.GET)
    public String createSubjectAction(Map <String, Object> map)
    {
        try
        {
            //On envoie l'entité pour la création de notre formulaire.
            map.put("thread",new Threads());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return "threads/create";
    }
    
    /**
     * Cette fonction envoie la page pour la modification d'un sujet
     * 
     * @param id L'id du sujet à éditer
     * @param map permet d'envoyer des variables à la vue 'threads/update'
     * @return la vue de l'url
     */
    @RequestMapping(value = "/modifier-un-sujet.htm", method = RequestMethod.GET)
    public String updateSubjectAction(@RequestParam String id,Map <String, Object> map)
    {
        try
        {
            //On envoie l'entité pour la création de notre formulaire.
            map.put("thread",this.iThread.find(new Integer(id)));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return "threads/update";
    }
    
    /**
     * On manage les commentaires.
     * 
     * @param id id du thread à administrer.
     * @param map on envoie les variables.
     * @return la vue pour manager les commentaires.
     */
    @RequestMapping(value = "/manage-comments.htm", method = RequestMethod.GET)
    public String manageComments(@RequestParam String id,Map <String, Object> map)
    {
        boolean authority = false;
        try
        {
            //On récupère le fil
            Threads th = this.iThread.find(new Integer(id));
            //Si l'utilisateur n'est pas le bon on le redirigera
            Users u = th.getUsers();
            //On récupère l'utilisateur
            User userCurrent = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            //On récupère l'utilisateur
            Users user = this.iUser.findByUsername(userCurrent.getUsername());
            //Si ce n'est pas le bon on le redirige vers la page mes sujets
            if(!u.getUsername().equals(user.getUsername()))
            {
                //L'autorité
                Authorities au = new Authorities();
                for (Object a : user.getAuthoritieses())
                {
                    au = (Authorities) a;
                    //Si l'autorité est = à manager ou admin on peut éditer
                    if (au.getAuthority().equals(UsersController.ROLE_ADMIN)
                            || au.getAuthority().equals(UsersController.ROLE_MANAGER)) 
                    {
                        authority = true;
                    }
                }
                
                //Si l'autorité est true
                if(authority == true)
                {
                    //On envoie l'entité correspondant au sujet à lire.
                    map.put("thread",th);
                }
                else
                {
                    return "redirect:/acces-interdit.htm";
                }
            }
            else
            {
                //On envoie l'entité correspondant au sujet à lire.
                map.put("thread",th);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return "threads/manage_comments";
    }
    
    /**
     * Cette fonction envoie la page qui nous permet de lire le sujet
     * 
     * @param id L'id du sujet à lire
     * @param map permet d'envoyer des variables à la vue 'threads/read'
     * @return la vue de l'url
     */
    @RequestMapping(value = "/lire-sujet.htm", method = RequestMethod.GET)
    public String showAction(@RequestParam String id,Map <String, Object> map)
    {
        try{
            //On récupère le fil
            Threads th = this.iThread.find(new Integer(id));
            
            //On envoie l'entité correspondant au sujet à lire.
            map.put("thread",th);
            map.put("comment", new Comments());
            map.put("comments",this.iComment.findByCommentsEnabledForThreads(th));
            //Si c'est un fil privé.
            if(th.isEnabled())
            {
                //L'utilisateur
                Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                //Si il est connecté on fait rien
                if(!principal.toString().equals("anonymousUser"))
                {
                } else {
                    //S'il ne l'est pas on redirige vers la page de login
                    return "redirect:/login.htm";
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return "threads/read";
    }
    
    /**
     * Cette fonction envoie la page pour la récupération des sujets d'un connecté
     * 
     * @param map permet d'envoyer des variables à la vue 'threads/mysubjects'
     * @return la vue de l'url
     */
    @RequestMapping(value = "/mes-sujets.htm", method = RequestMethod.GET)
    public String mySubjectsAction(Map <String, Object> map)
    {
        try
        {
            //L'utilisateur
            User userCurrent = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            //On récupère l'utilisateur
            Users user = this.iUser.findByUsername(userCurrent.getUsername());
                
            //On envoie l'entité pour la création de notre formulaire.
            map.put("threads",this.iThread.findByUser(user));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return "threads/mysubjects";
    }
    
    
    /**
     * Cette fonction envoie la page d'administration des sujets.
     * 
     * @param map permet d'envoyer des variables à la vue 'threads/admin_subjects'
     * @return la vue de l'url
     */
    @RequestMapping(value = "/liste-des-sujets.htm", method = RequestMethod.GET)
    public String adminSubjectsAction(Map <String, Object> map)
    {
        try
        {
            //On envoie tout les sujets à administrer.
            map.put("threads",this.iThread.findAll());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return "threads/admin_subjects";
    }
    
        
    /**
     * Applique l'ajout de la création d'un fil.
     * 
     * @param thread le Threads qui sera ajouté dans le formulaire.
     * @param result Bindind du threads passé en paramètre.
     * @param map permet d'envoyer des variables à la vue 'threads/create'
     * @return la page de création du fil
     */
    @RequestMapping(value="/creer-un-sujet.htm", method=RequestMethod.POST)
    public String doCreateSubjectAction(@Valid Threads thread, BindingResult result, Map <String, Object> map)
    {
        try
        {
            //L'utilisateur
            User userCurrent = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            //On récupère l'utilisateur
            Users user = this.iUser.findByUsername(userCurrent.getUsername());
            //On envoie l'entité pour la création de notre formulaire.
            map.put("thread", new Threads());

            if (result.hasErrors()) {
                map.put("messageError", "Les champs titre et contenu sont obligatoires.");
                return "threads/create";
            } else {
                //On ajoute le sujet maintenant
                thread.setUsers(user);
                //On crée le sujet
                this.iThread.create(thread);
            }

            map.put("messageSuccess", "Le sujet " + thread.getTitle() + " a bien été ajouté.");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
                
        return "threads/create";
    }    
    
    /**
     * Modifie le sujet grâce au formulaire
     * 
     * @param thread le fil qu'on va modifier
     * @param result le result du binding de thread
     * @param id l'id du thread
     * @param map ce qui nous permet d'envoyer à notre vue des données.
     * @return la vue ou l'url qui recevra le tout.
     */
    @RequestMapping(value="/modifier-un-sujet.htm", method=RequestMethod.POST)
    public String doUpdateSubjectAction(@Valid Threads thread, BindingResult result, @RequestParam String id, Map <String, Object> map)
    {
        try
        {
            //threadId
            Threads threadId = this.iThread.find(new Integer(id));
            //On envoie l'entité pour la création de notre formulaire.
            map.put("thread",thread);
            boolean authority = false;

            if(result.hasErrors()) {
                map.put("messageError", "Les champs titre et contenu sont obligatoires.");
                return "threads/update";
            }
            else
            {
                //Si l'utilisateur n'est pas le bon on le redirigera
                Users u = threadId.getUsers();
                //On récupère l'utilisateur
                User userCurrent = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                //On récupère l'utilisateur
                Users user = this.iUser.findByUsername(userCurrent.getUsername());
                //Si c'est l'utilisateur connecté est le bon alors il peut modifier
                if (u.getUsername().equals(user.getUsername()))
                {
                    //L'utilisateur est remis en place
                    thread.setUsers(threadId.getUsers());
                    //On modifie le fil de ce sujet
                    this.iThread.update(thread);
                    map.put("messageSuccess", "Le sujet " + thread.getTitle() + " a bien été modifié.");
                } 
                else
                {
                    //L'autorité
                    Authorities au = new Authorities();
                    for (Object a : user.getAuthoritieses())
                    {
                        au = (Authorities) a;
                        //Si l'autorité est = à manager ou admin on peut éditer
                        if (au.getAuthority().equals(UsersController.ROLE_ADMIN)
                                || au.getAuthority().equals(UsersController.ROLE_MANAGER)) 
                        {
                            authority = true;
                        }
                    }

                    //Si il a les bonnes autorités
                    if(authority)
                    {
                        //L'utilisateur est remis en place
                        thread.setUsers(threadId.getUsers());
                        //On modifie le fil de ce sujet
                        this.iThread.update(thread);
                        map.put("messageSuccess", "Le sujet " + thread.getTitle() + " a bien été modifié.");
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
        return "threads/update";
    }
    
    /**
     * Ajout du commentaire 
     * 
     * @param comment commentaire à ajouter
     * @param result binding de comment
     * @param id id du fil
     * @param map envoie des variables
     * @return la vue pour lire le sujet
     */
    @RequestMapping(value="/lire-sujet.htm", method=RequestMethod.POST)
    public String doShowAction(@Valid Comments comment, BindingResult result, @RequestParam String id, Map <String, Object> map)
    {
        try
        {
            Threads th = this.iThread.find(new Integer(id));
            //On récupère l'utilisateur
            User userCurrent = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            //On récupère l'utilisateur
            Users user = this.iUser.findByUsername(userCurrent.getUsername());
            //On envoie le fil
            map.put("thread",th);
            //On renvoie une entité de commentaire
            map.put("comment",new Comments());
            //On met le thread dans le commentaire
            comment.setThreads(th);
            comment.setUsers(user);
            //On enregistre le commentaire
            this.iComment.create(comment);
            //Liste des commentaires
            map.put("comments",this.iComment.findByCommentsEnabledForThreads(th));
            //Success de l'ajout du commentaire
            map.put("messageSuccess", "Le commentaire a bien été ajouté.");
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return "threads/read";
    }
    
    /**
     * Suppression d'un sujet
     * 
     * @param id id du sujet à supprimer
     * @param map envoie des variables
     * @return la page après la suppréssion.
     */
    @RequestMapping(value="/supprimer-sujet.htm", method=RequestMethod.GET)
    public String doDeleteAction(@RequestParam String id, Map <String, Object> map)
    {
        //L'autorité est à false au départ
        boolean autority = false;
        try
        {
            Threads th;
            th = this.iThread.find(new Integer(id));
            //Si le fil n'existe pas
            if(th.equals(null))
            {
                map.put("messageError", "Ce sujet n'existe pas dans la base de données.");
            }
            else
            {
                //Si l'utilisateur n'est pas le bon on le redirige
                Users u = th.getUsers();
                //On récupère l'utilisateur
                User userCurrent = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                //On récupère l'utilisateur
                Users user = this.iUser.findByUsername(userCurrent.getUsername());
                //Si c'est pas le bon utilisateur
                if(u.getUsername().equals(user.getUsername()))
                {
                    //On applique la suppréssion
                    this.iThread.delete(this.iThread.find(new Integer(id)));
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
                            autority = true;
                            
                        }
                    }
                    
                    //Si il a la bonne autorité
                    if(autority)
                    {
                        //On applique la suppréssion
                        this.iThread.delete(this.iThread.find(new Integer(id)));
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
        
        map.put("messageSuccess", "Le sujet a bien été supprimé.");
        return "redirect:/mes-sujets.htm";
    }
}
