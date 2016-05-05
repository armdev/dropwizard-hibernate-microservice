/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.dropwizard.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "person")
@NamedQueries({
    @NamedQuery(
            name = "Person.findAll",
            query = "SELECT p FROM Person p"
    )
})
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fullName", nullable = false)
    private String fullName;

    @Column(name = "jobTitle", nullable = false)
    private String jobTitle;

    public Person() {
    }

    public Person(String fullName, String jobTitle) {
        this.fullName = fullName;
        this.jobTitle = jobTitle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Person)) {
            return false;
        }

        final Person that = (Person) o;

        return Objects.equals(this.id, that.id)
                && Objects.equals(this.fullName, that.fullName)
                && Objects.equals(this.jobTitle, that.jobTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, jobTitle);
    }
}
