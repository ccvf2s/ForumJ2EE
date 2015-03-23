/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forum.interfaces;

import forum.entities.Comments;
import forum.entities.Threads;
import java.util.List;

/**
 *
 * @author dominickmakome
 */
public interface CommentInterface {
    
    public void create(Comments comment);
            
    public void update(Comments comment);
    
    public void delete(Comments comment);
    
    public Comments find(int id);
    
    public List<Comments> findAll();
    
    List<Comments> findByEnabled(boolean enabled);
    
    List<Comments> findByCommentsEnabledForThreads(Threads th);
}
