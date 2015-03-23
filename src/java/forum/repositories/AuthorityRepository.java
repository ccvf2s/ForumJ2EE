/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forum.repositories;

import forum.entities.Authorities;
import forum.factories.HibernateUtil;
import forum.interfaces.AuthorityInterface;
import org.hibernate.Session;

/**
 * Repository correspondant au Model Authorities.
 * @author dominickmakome
 */
public class AuthorityRepository extends EntityRepository<Authorities> implements AuthorityInterface {

    public AuthorityRepository(Class<Authorities> entityClass) {
        super(entityClass);
    }
    
    public AuthorityRepository(){
        super(Authorities.class);
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
    
}
