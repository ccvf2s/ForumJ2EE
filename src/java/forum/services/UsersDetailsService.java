/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forum.services;

import forum.entities.Authorities;
import forum.entities.Users;
import forum.interfaces.UserInterface;
import forum.repositories.UserRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Service qui permet de mettre en place l'objet de connection
 * pour un utilisateur.
 * @author dominickmakome
 */
public class UsersDetailsService implements UserDetailsService {
    
    private UserInterface Iuser;

    public UsersDetailsService() {
        this.Iuser = new UserRepository();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Users user = this.Iuser.findByUsername(username);
        List<GrantedAuthority> authorities = buildUserAuthority(user.getAuthoritieses());
        
        return buildUserForAuthentication(user, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<Authorities> authorities) {
        
        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
        
        for(Authorities a: authorities)
        {
            setAuths.add(new SimpleGrantedAuthority(a.getAuthority()));
            System.out.println("----------- Authority: "+a.getAuthority());
        }
        
        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
        
        return Result;
    }

    //Utilisateur pour l'authentification
    private User buildUserForAuthentication(Users user, List<GrantedAuthority> authorities) {
        //On retourne l'utilisateur    
        return new User(user.getUsername(),user.getPassword(),user.isEnabled(),
                    true,true,true,authorities
            );
    }
    
    
    
}
