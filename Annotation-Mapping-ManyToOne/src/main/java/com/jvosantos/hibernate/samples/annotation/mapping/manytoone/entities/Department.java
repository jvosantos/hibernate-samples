package com.jvosantos.hibernate.samples.annotation.mapping.manytoone.entities;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "department")
public class Department {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "university_id")
    private University university;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "foundation_date", nullable = true)
    private Date foundationDate;
    
    public Department() {
        
    }
    
    public Department(String name, Date foundationDate) {
        this.name = name;
        this.foundationDate = foundationDate;
    }
    
    public Department(String name, Date foundationDate, University university) {
        this.name = name;
        this.foundationDate = foundationDate;
        this.university = university;
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
    
    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    @Override
    public String toString() {
        return "Department{" + "id=" + id + ", name=" + name + ", university=" + university + ", foundationDate=" + foundationDate + '}';
    }
    
    
}
