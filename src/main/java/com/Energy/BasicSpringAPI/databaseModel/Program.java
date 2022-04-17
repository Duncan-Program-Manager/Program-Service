package com.Energy.BasicSpringAPI.databaseModel;

import javax.persistence.*;

@Entity
@Table(name = "Program", schema = "DPMDatabase")
public class Program {
    private int Id;
    private String Name;
    private String Description;
    private String Location;
    private boolean UserUpload;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() { return Id; }
    public void setId(int id) {
        Id = id;
    }

    @Basic
    @Column(name = "Name", nullable = false)
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }

    @Basic
    @Column(name = "Description", nullable = true)
    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }

    @Basic
    @Column(name = "Location", nullable = false)
    public String getLocation() {
        return Location;
    }
    public void setLocation(String location) {
        Location = location;
    }

    @Basic
    @Column(name = "UserUpload", nullable = false)
    public boolean isUserUpload() {
        return UserUpload;
    }
    public void setUserUpload(boolean userUpload) {
        UserUpload = userUpload;
    }
}
