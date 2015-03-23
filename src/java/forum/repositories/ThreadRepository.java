/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forum.repositories;

import forum.entities.Comments;
import forum.entities.Threads;
import forum.entities.Users;
import forum.factories.HibernateUtil;
import forum.interfaces.CommentInterface;
import forum.interfaces.ThreadInterface;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Repository correspondant au Model Threads.
 * @author dominickmakome
 */
public class ThreadRepository extends EntityRepository<Threads> implements ThreadInterface{

    public CommentInterface iComment;
    
    public ThreadRepository(Class<Threads> entityClass) {
        super(entityClass);
    }
    
    public ThreadRepository() {
        super(Threads.class);
        this.iComment = new CommentRepository();
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
    
    @Override
    public List<Threads> findByUser(Users user) {
        Session session = this.getSession();
        //On récupère la liste des sujets d'un utilisateur
        List<Threads> threads = session.createQuery("from Threads where user_id = ?")
                .setParameter(0,user.getId()).list();
        
        if(threads.size() > 0)
            return threads;
        return null;
        
    }
    
    @Override
    public void delete(Threads th)
    {
        //On supprime d'abord les commentaires
        for(Object c: th.getCommentses())
        {
            //On supprime
            this.iComment.delete((Comments) c);
        }
        //Cette fois ci on supprime le fil
        Session session = this.getSession();
        Transaction t = session.beginTransaction();
        session.delete(th);
        t.commit();
        session.close();
    }
    
}
