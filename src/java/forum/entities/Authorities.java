package forum.entities;
// Generated 14 mars 2015 04:47:14 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Authorities generated by hbm2java
 * L'entité qui gère les autorités des utilisateurs.
 */
@Entity
@Table(name="authorities"
    ,catalog="forumdb"
)
public class Authorities  implements java.io.Serializable {


     private Integer id;
     private Users users;
     private String authority;

    public Authorities() {
    }

	
    public Authorities(Users users) {
        this.users = users;
    }
    public Authorities(Users users, String authority) {
       this.users = users;
       this.authority = authority;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    public Users getUsers() {
        return this.users;
    }
    
    public void setUsers(Users users) {
        this.users = users;
    }

    
    @Column(name="authority")
    public String getAuthority() {
        return this.authority;
    }
    
    public void setAuthority(String authority) {
        this.authority = authority;
    }




}


