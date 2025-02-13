package com.system.Learning_system_springboot.model.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;
@Entity
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "user_code", nullable = false, unique = true, length = 50)
    private String userCode;
    @Column(name = "email", nullable = false, length = 200)
    private String email;
    @Column(name = "password", nullable = false, length = 200)
    private String password;
    @Column(name = "name", nullable = false, length = 200)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;
    @Enumerated(EnumType.STRING) // Map role as an enum
    @Column(name = "role", nullable = false)
    private Role role;
    @ManyToOne
    @JoinColumn(name = "create_admin_id", nullable = false)
    @JsonBackReference
    private User createAdmin;
    @Column(name = "create_date", nullable = false)
    private Date createDate;
    @Column(name = "update_date", nullable = false)
    private Date updateDate;
    @PrePersist
    public void prePersist() {
        if (this.createDate == null) {
            this.createDate = new Date(System.currentTimeMillis());
        }
        if (this.updateDate == null) {
            this.updateDate = new Date(System.currentTimeMillis());
        }
        if (this.status == null) {
            this.status = Status.ACTIVE;
        }
    }
    public User() {
    }

    // Parameterized constructor (optional)
    public User(String email, String password, String name, Role role, Status status, User createAdmin) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
        this.status = status;
        this.createAdmin = createAdmin;
    }
    public String getUserCode() {
        return userCode;
    }
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public User getCreateAdmin() {
        return createAdmin;
    }
    public void setCreateAdmin(User createAdmin) {
        this.createAdmin = createAdmin;
    }
    public Date getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
