package com.epicenergyservices.u5w4.entities;

import com.epicenergyservices.u5w4.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@JsonIgnoreProperties({"password", "authorities", "accountNonExpired", "enabled", "accountNonLocked", "credentialsNonExpired"})
@Table(name = "users")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Setter(AccessLevel.NONE)
  private UUID id;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column
  private String name;

  @Column
  private String surname;

  @Column
  private String avatar;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Role role;

  @OneToOne
  @JoinColumn(name = "address_id", referencedColumnName = "id")
  private Address address;

  @OneToMany(mappedBy = "user")
  private Set<Client> clients;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(this.role.name()));
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
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
  public String getUsername() {
    return this.email;
  }
}
