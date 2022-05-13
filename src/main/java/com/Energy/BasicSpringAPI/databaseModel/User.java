package com.Energy.BasicSpringAPI.databaseModel;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Users", schema = "DPMDatabase")
public class User {

    private String Username;

    @Id
    @Column(name = "Username")
    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    @OneToMany(mappedBy = "User")
    private Set<Program> programs;
}
