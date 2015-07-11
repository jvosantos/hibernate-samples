/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jvosantos.hibernate.samples.annotation.mapping.manytoone.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Vasco Santos
 */
@Entity
@Table(name = "university")
public class University {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "foundation_date", nullable = false)
    private Date foundationDate;
    
    @OneToMany(mappedBy = "university", cascade = CascadeType.PERSIST)
    private Set<Department> departments;

    public University() {
        this.departments = new HashSet<>();
    }
    
    public University(String name, Date foundationDate) {
        this.name = name;
        this.foundationDate = foundationDate;
        this.departments = new HashSet<>();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFoundationDate() {
        return foundationDate;
    }

    public void setFoundationDate(Date foundationDate) {
        this.foundationDate = foundationDate;
    }
    
    public Set<Department> getDepartments() {
        return departments;
    }
    
    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }
    
    public void addDepartment(Department department) {
        departments.add(department);
        department.setUniversity(this);
    }

    @Override
    public String toString() {
        return "University{" + "id=" + id + ", name=" + name + ", foundationDate=" + foundationDate + '}';
    }
    
}
