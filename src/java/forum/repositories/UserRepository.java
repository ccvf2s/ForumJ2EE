/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forum.repositories;

import forum.entities.Authorities;
import forum.entities.Threads;
import forum.entities.Users;
import forum.factories.HibernateUtil;
import forum.interfaces.AuthorityInterface;
import forum.interfaces.ThreadInterface;
import forum.interfaces.UserInterface;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Repository correspondant au Model Users.
 * @author dominickmakome
 */
public class UserRepository extends EntityRepository<Users> implements UserInterface{

    public ThreadInterface iThread;
    public AuthorityInterface iAut;
    
    public UserRepository(Class<Users> entityClass) {
        super(entityClass);
    }
    
    public UserRepository() {
        super(Users.class);
        this.iThread = new ThreadRepository();
        this.iAut = new AuthorityRepository();
    }
    
    
    /**
     * Renvoie la session qui permettra d'accéder à la base de données.
     * @return une session
     */
    @Override
    public Session getSession() {
        //Session = null au depart
        Session session = null;
        try
        {
            session = HibernateUtil.getSessionFactory().openSession();
            return session;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return session;
    }
    
    /**
     * Renvoie l'utilisateur correspond au username passé en paramètre.
     * 
     * @param username nom d'utilisateur à retrouver
     * @return un objet de type Users
     */
    @Override
    public Users findByUsername(String username) {
        Session session = this.getSession();
        //On récupère la liste d'utilisateurs ayant ce username
        List<Users> users = session.createQuery("from Users where username= ?")
                .setParameter(0,username).list();
        
        if(users.size() > 0)
            return users.get(0);
        return null;
        
    }
    
    /**
     * Renvoie un utilisateur correspondant à l'email
     * 
     * @param email Email de l'utilisateur à trouver
     * @return Renvoie un objet de type Users
     */
    @Override
    public Users findByEmail(String email) {
        Session session = this.getSession();
        //On récupère la liste d'utilisateurs ayant ce username
        List<Users> users = session.createQuery("from Users where email= ?")
                .setParameter(0,email).list();
        
        if(users.size() > 0)
            return users.get(0);
        return null;
        
    }
    
    
    /**
     * On supprime l'utilisateur courant.
     * 
     * @param u utilisateur à supprimer.
     */
    @Override
    public void delete(Users u)
    {
        //On supprime d'abord les fils
        for(Object t: u.getThreadses())
        {
            //On supprime le fil
            this.iThread.delete((Threads)t);
        }
        
        //On supprime les autorités
        for(Object a: u.getAuthoritieses())
        {
            //On supprime l'autorité
            this.iAut.delete((Authorities)a);
        }
        
        Session session = this.getSession();
        Transaction t = session.beginTransaction();        
        //On supprime l'utilisateur
        session.delete(u);
        t.commit();
        session.close();
    }
    
}
