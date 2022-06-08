package com.dpm.program.databaseModel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Program")
public class Program {
    private int Id;
    private String Name;
    private String Description;
    private String Location;
    private String Version;
    private Date UploadDate;
    private boolean UserUpload;
    private String Username;

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
    @Column(name = "Description", nullable = true, length = 1000, columnDefinition = "text")
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

    @Basic
    @Column(name = "Version", nullable = true)
    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }

    @Basic
    @Column(name = "UploadDate")
    public Date getUploadDate() {
        return UploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        UploadDate = uploadDate;
    }

    @Basic
    @Column
    public String getUsername() {
        return Username;
    }
    public void setUsername(String Username) {
        this.Username = Username;
    }
}
