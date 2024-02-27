package com.epicenergyservices.u5w4.payloads;

import org.aspectj.weaver.ast.Var;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;

import javax.management.relation.Role;
import java.util.UUID;

public class UserDTO {
    private UUID id;
    private String username;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String avatar;
    private Role role;

    public UserDTO(UUID id, String username, String email, String password, String name, String surname, String avatar, Role role){
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.avatar = avatar;
        this.role = role;
    }
    public enum Role {
        Admin,
        User
    }
}