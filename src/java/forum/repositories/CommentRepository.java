/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forum.repositories;

import forum.entities.Comments;
import forum.entities.Threads;
import forum.factories.HibernateUtil;
import forum.interfaces.CommentInterface;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 * Repository correspondant au Model Comments.
 * @author dominickmakome
 */
public class CommentRepository extends EntityRepository<Comments> implements CommentInterface{

    public CommentRepository(Class<Comments> entityClass) {
        super(entityClass);
    }
    
    public CommentRepository() {
        super(Comments.class);
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
     * Permet de renvoyer une liste de commentaires activés.
     * @param enabled true ou false pour les commentaires actifs ou inactifs.
     * @return Renvoie une liste de commentaires
     */
    public List<Comments> findByEnabled(boolean enabled)
    {
        Session session = this.getSession();
        //On récupère la liste des commentaires actifs
        List<Comments> comments = session.createQuery("from Comments where enabled = ?")
                .setParameter(0,enabled).list();
        
        System.out.println("----------------"+comments.size());
        if(comments.size() > 0)
            return comments;
        return null;
    }
    
    /**
     * Permet de renvoyer la liste des commentaires activés d'un fil(sujet)
     * @param th
     * @return 
     */
    public List<Comments> findByCommentsEnabledForThreads(Threads th)
    {
        List<Comments> allComments = this.findByEnabled(true);
        System.out.println("----------------2:"+allComments.size());
        //On crée une nouvelle liste de commentaires
        List<Comments> comments = new ArrayList<Comments>();
        //On boucle sur tout les commentaires
        for(Comments c : allComments)
        {
            //Si c'est le bon thread
            if(c.getThreads().getId().equals(th.getId()))
            {
                //On l'ajoute à notre liste de commentaires
                comments.add(c);
            }
        }
        return comments;
    }
    
}
