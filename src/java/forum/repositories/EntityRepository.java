/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forum.repositories;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * La class qui contient toutes les fonctions mutuelles aux repositories.
 * Celles ci permettent d'accéder facilement à la base de données.
 * @author dominickmakome
 */
public abstract class EntityRepository<T> {
    
    private final Class<T> entityClass;
    
    public abstract Session getSession();

    public EntityRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    /**
     * Sauvegarde d'une entité de type T
     * @param entityClass 
     */
    public void create(T entityClass)
    {
        Session session = this.getSession();
        Transaction t = session.beginTransaction();
        session.save(entityClass);
        t.commit();
        session.close();
    }
    
    /**
     * Modification d'une entité de type T
     * @param entityClass 
     */
    public void update(T entityClass)
    {
        Session session = this.getSession();
        Transaction t = session.beginTransaction();
        session.update(entityClass);
        t.commit();
        session.close();
    }
    
    /**
     * Suppression d'une entité de type T
     * @param entityClass 
     */
    public void delete(T entityClass)
    {
        Session session = this.getSession();
        Transaction t = session.beginTransaction();
        session.delete(entityClass);
        t.commit();
        session.close();
    }
    
    /**
     * Récuperation d'une entité de type T par id
     * @param id l'entier qui correspond à l'entité qui sera renvoyée.
     * @return l'entité correspondante à l'id
     */
    public T find(int id)
    {
        Session session = this.getSession();
        T obj = (T) session.get(this.entityClass, id);
        session.close();
        
        return obj;
    }
    
    /**
     * Récupération de la liste de toutes les entités correspondant
     * au type T.
     * 
     * @return return une liste d'entité de type T
     */
    public List<T> findAll()
    {
        Session session = this.getSession();
        Transaction t = session.beginTransaction();
        
        List<T> list = null;
        String hql = "from "+this.entityClass.getName();
        list = session.createQuery(hql).list();
        
        t.commit();
        session.close();
                
        return list;
    }
}
