package com.psu.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;
import javax.persistence.*;

// for admin
/*update t_user
set roles_id = 2
where id = 1

delete from t_client where id = 1
*/

@Entity
@Table(name = "t_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GenericGenerator(name="system-uuid", strategy = "uuid")
    private Long id;
    //@Size(min=2, message = "Не меньше 2 знаков")
    private String username;
   // @Size(min=2, message = "Не меньше 2 знаков")
    private String password;

    private String mail;
   // @Size(min=3, message = "Не меньше 3 знаков")
    private String fullname;

    @Transient
    private String passwordConfirm;

    //@Cascade(org.hibernate.annotations.CascadeType.ALL)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Role role;

    public User() {
    }

    public User(@Size(min = 2, message = "Не меньше 2 знаков") String username, @Size(min = 2, message = "Не меньше 2 знаков") String password, String mail, @Size(min = 3, message = "Не меньше 3 знаков") String fullname, String passwordConfirm, Role role) {
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.fullname = fullname;
        this.passwordConfirm = passwordConfirm;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }


   public Collection<? extends GrantedAuthority> getAuthorities() {
        return (Collection<? extends GrantedAuthority>) getRole();
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
