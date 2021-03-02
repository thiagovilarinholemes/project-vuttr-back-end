package com.vuttr.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_permission")

@Repository
public class Permission implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String description;
    
//    @JsonIgnore
//    @ManyToMany(mappedBy = "permissions")
//    private Set<Role> roles = new HashSet<>();
//    
//    public Permission(Long id, String name, String description) {
//    	this.id=id;
//    	this.name=name;
//    	this.description=description;
//    }
}
    
    
    
    
    
    
    