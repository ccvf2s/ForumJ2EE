/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forum.interfaces;

import forum.entities.Users;
import java.util.List;

/**
 *
 * @author dominickmakome
 */
public interface UserInterface {
    
    public void create(Users user);
            
    public void update(Users user);
    
    public void delete(Users user);
    
    public Users find(int id);
    
    public Users findByUsername(String Username);
    
    public Users findByEmail(String Email);
    
    public List<Users> findAll();
}
