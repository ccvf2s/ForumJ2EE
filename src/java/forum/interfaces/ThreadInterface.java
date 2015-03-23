/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forum.interfaces;

import forum.entities.Threads;
import forum.entities.Users;
import java.util.List;

/**
 *
 * @author dominickmakome
 */
public interface ThreadInterface {
    
    public void create(Threads thread);
            
    public void update(Threads thread);
    
    public void delete(Threads thread);
    
    public Threads find(int id);
    
    public List<Threads> findAll();
    
    public List<Threads> findByUser(Users user);
}
