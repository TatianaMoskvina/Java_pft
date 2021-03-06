package ru.stqa.pft.mantis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "mantis_user_table")
public class UserData {

    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;


    public int getId() { return id; }

    public String getUsername() { return username; }

    public String getEmail() { return email; }

    public void setId(int id) { this.id = id; }

    public void setUsername(String username) { this.username = username; }

    public void setEmail(String email) { this.email = email; }
}
