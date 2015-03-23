/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forum.interfaces;

import forum.entities.Authorities;
import java.util.List;

/**
 *
 * @author dominickmakome
 */
public interface AuthorityInterface {
    
    public void create(Authorities authority);
            
    public void update(Authorities authority);
    
    public void delete(Authorities authority);
    
    public Authorities find(int id);
    
    public List<Authorities> findAll();
}
