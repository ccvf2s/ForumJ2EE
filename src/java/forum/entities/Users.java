package forum.entities;
// Generated 14 mars 2015 04:47:14 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Users generated by hbm2java
 * L'entité qui gère les utilisateurs.
 */
@Entity
@Table(name="users"
    ,catalog="forumdb"
)
public class Users  implements java.io.Serializable {


     private Integer id;
     
     @NotEmpty
     private String username;
     @NotEmpty
     private String password;
     @NotEmpty
     private String email;
     private boolean enabled;
     private Set authoritieses = new HashSet(0);
     private Set commentses = new HashSet(0);
     private Set threadses = new HashSet(0);

    public Users() {
    }

	
    public Users(boolean enabled) {
        this.enabled = enabled;
    }
    public Users(String username, String password, String email, boolean enabled, Set authoritieses, Set commentses, Set threadses) {
       this.username = username;
       this.password = password;
       this.email = email;
       this.enabled = enabled;
       this.authoritieses = authoritieses;
       this.commentses = commentses;
       this.threadses = threadses;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="username")
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    
    @Column(name="password")
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    
    @Column(name="email")
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="enabled", nullable=false)
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="users")
    public Set getAuthoritieses() {
        return this.authoritieses;
    }
    
    public void setAuthoritieses(Set authoritieses) {
        this.authoritieses = authoritieses;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="users")
    public Set getCommentses() {
        return this.commentses;
    }
    
    public void setCommentses(Set commentses) {
        this.commentses = commentses;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="users")
    public Set getThreadses() {
        return this.threadses;
    }
    
    public void setThreadses(Set threadses) {
        this.threadses = threadses;
    }




}

