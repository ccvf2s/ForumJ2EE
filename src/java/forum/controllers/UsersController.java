/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forum.controllers;

import forum.entities.Authorities;
import forum.entities.Users;
import forum.interfaces.AuthorityInterface;
import java.util.Map;
import javax.resource.spi.AuthenticationMechanism;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import forum.interfaces.UserInterface;
import forum.repositories.AuthorityRepository;
import forum.repositories.UserRepository;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * Controller qui gère la gestion des utilisateurs du site.
 * @author dominickmakome
 */
@Controller
public class UsersController {
    
    public UserInterface Iuser;
    public AuthorityInterface Iaut;
    public PasswordEncoder passwordEncoder;
    
    public static String ROLE_USER = new String("ROLE_USER");
    public static String ROLE_MANAGER = new String("ROLE_MANAGER");
    public static String ROLE_ADMIN = new String("ROLE_ADMIN");

    public UsersController() {
        this.Iuser = new UserRepository();
        this.Iaut  = new AuthorityRepository();
        this.passwordEncoder = new BCryptPasswordEncoder();
    }
    
    
    /**
     * Cette fonction envoie la page login lorsque la requête est en GET
     * 
     * @param map permet d'envoyer des variables à la vue 'users/login'
     * @return la vue de l'url
     */
    @RequestMapping(value = "/login.htm", method = RequestMethod.GET)
    public String loginAction(Map <String, Object> map)
    {
        return "users/login";
    }
    
    /**
     * Cette fonction envoie la page d'inscription lorsque la requête est en GET
     * 
     * @param map permet d'envoyer des variables à la vue 'users/signup'
     * @return la vue de l'url
     */
    @RequestMapping(value = "/register.htm", method = RequestMethod.GET)
    public String signupAction(Map <String, Object> map)
    {
        //On lui envoie une instance de l'utilisateur pour le formulaire.
        map.put("user",new Users());
        
        return "users/register";
    }
    
    /**
     * Renvoie la page d'interdiction.
     * 
     * @param map envoie des variables à la vue
     * @return 
     */
    @RequestMapping(value="/acces-interdit.htm", method=RequestMethod.GET)
    public String accessDeniedAction(Map <String, Object> map)
    {
        return "access_denied";
    }
    
    /**
     * Page d'administration des utilisateurs
     * 
     * @param map envoie des variables à la vue
     * @return la vue correspondante à l'url
     */
    @RequestMapping(value = "/admin-users.htm", method = RequestMethod.GET)
    public String adminUsersAction(Map <String, Object> map)
    {
        //On envoie une liste d'utilisateurs
        map.put("users",this.Iuser.findAll());
        return "users/admin_users";
    }
    
    /**
     * Renvoie l'url pour la modification de l'utilisateur
     * 
     * @param id id de l'utilisateur à modifier
     * @param map permet d'envoyer les variables.
     * @return la vue pour l'update de l'utilisateur
     */
    @RequestMapping(value="/update-authority.htm", method=RequestMethod.GET)
    public String updateAuthority(@RequestParam String id, Map <String, Object> map)
    {
        try
        {
            //Renvoie l'utilisateur
            Users u = this.Iuser.find(new Integer(id));
            //On met en place les status
            ArrayList<String> status = new ArrayList<String>();
            status.add(ROLE_ADMIN);
            status.add(ROLE_USER);
            status.add(ROLE_MANAGER);
            //On envoie l'utilisateur
            map.put("user",u);
            //On envoie les status
            map.put("status",status);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return "users/admin_update";
    }
    
    
    /**
     * Fonction qui permet d'enregistrer une page
     * 
     * @param user variable user qui sera enregistré dans la base de données.
     * @param result le binding de user
     * @param map variable qui nous permet d'envoyer des variables à la vue
     * @return la page d'enregistrement d'un utilisateur
     */
    @RequestMapping(value="/register.htm", method=RequestMethod.POST)
    public String submitFormRegister(@Valid Users user, BindingResult result, Map <String, Object> map) {
        //On lui envoie une instance de l'utilisateur
        map.put("user",new Users());
        
        if(result.hasErrors()) {
            map.put("messageError", "Tout les champs sont obligatoires.");
            return "users/register";
        }
        else
        {
            //On vérifit si l'utilisateur existe
            if(this.Iuser.findByUsername(user.getUsername()) != null || this.Iuser.findByEmail(user.getEmail()) != null)
            {
                map.put("messageError", "L'utilisateur existe déjà dans la base de données.");
                return "users/register";
            } 
            //On encode le mot de passe
            String hashedPassword = this.passwordEncoder.encode(user.getPassword());
            //On met le password encodé
            user.setPassword(hashedPassword);
            //On active l'utilisateur
            user.setEnabled(true);
            //On lui donne les autorités d'un simple utilisateur
            Authorities aut = new Authorities();
            //On donne l'utilisateur
            aut.setUsers(user);
            aut.setAuthority(UsersController.ROLE_USER);
            //On enregistre l'utilisateur dans la base de données
            this.Iuser.create(user);
            //On enregistre son autorité
            this.Iaut.create(aut);
        }
         
        map.put("messageSuccess", "Le compte utilisateur "+user.getUsername()+" a bien été créé.");
        return "users/register";
    }
    
    @RequestMapping(value="/update-authority.htm", method=RequestMethod.POST)
    public String submitUpdateAction(@RequestParam String id, @RequestParam String authority, Map <String, Object> map)
    {
        boolean alreadyAuth = false;
        try
        {
            //Renvoie l'utilisateur
            Users u = this.Iuser.find(new Integer(id));
            //On met en place les status
            ArrayList<String> status = new ArrayList<String>();
            status.add(ROLE_ADMIN);
            status.add(ROLE_USER);
            status.add(ROLE_MANAGER);
            
            //Si l'utilisateur a déjà cette autorité on envoie un message pour le dire
            for(Object auth : u.getAuthoritieses())
            {
                Authorities myAuth = (Authorities) auth;
                //Si l'autorité existe
                alreadyAuth = myAuth.getAuthority().equals(authority)?true:alreadyAuth;
            }
            
            //Si l'autorité existe déjà
            if(alreadyAuth)
            {
                //On envoie un message de satisfaction
                map.put("messageError", "L'autorité existe déjà pour cet utilisateur!!!");
            }
            else
            {
                //On crée une nouvelle instance de Autorité
                Authorities aut = new Authorities();
                aut.setAuthority(authority);
                aut.setUsers(u);
                //On enregistre l'autorité
                this.Iaut.create(aut);
                //On envoie un message de satisfaction
                map.put("messageSuccess", "La nouvelle autorité a bien été ajouté à l'utilisateur.");
            }
            
            //On envoie l'utilisateur
            map.put("user",u);
            //On envoie les status
            map.put("status",status);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }        
        return "users/admin_update";
    }
    
    /**
     * Suppression d'un utilisateur
     * 
     * @param id id de l'utilisateur à effacer
     * @param map envoie des variables.
     * @return l'envoie de l'url après suppression
     */
    @RequestMapping(value="/delete-user.htm", method=RequestMethod.GET)
    public String deleteAction(@RequestParam String id,Map <String, Object> map)
    {
        try
        {
            Users u = this.Iuser.find(new Integer(id));
            //On supprime l'utilisateur
            this.Iuser.delete(u);
            //On envoie le message de suppression
            map.put("messageSuccess", "Le compte utilisateur "+u.getUsername()+" a bien été supprimée.");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return "redirect:/admin-users.htm";
    }
}
