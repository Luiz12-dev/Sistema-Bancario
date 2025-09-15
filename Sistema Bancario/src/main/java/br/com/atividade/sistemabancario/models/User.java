package br.com.atividade.sistemabancario.models;

import br.com.atividade.sistemabancario.Enum.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name= "id",nullable = false, unique = true, updatable = false)
    private UUID id;

    @NotBlank(message = "The username must be declared")
    @Column(name= "username", unique = true, nullable = false, length = 20)
    private String username;

    @JsonIgnore
    @NotBlank(message = "The password must be declared")
    @Column(name="password", nullable = false, length = 50)
    private String password;

    @Email
    @NotBlank(message = "The email must be declared")
    @Column(name= "email", unique = true, nullable = false, length = 250)
    private String email;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "The role must be delcared")
    @Column(name = "role",length = 20, nullable = false)
    private Role role;

    @OneToOne(mappedBy = "userOwner",cascade = CascadeType.ALL)
    private Account account;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("Role_"+role.name()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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



}
