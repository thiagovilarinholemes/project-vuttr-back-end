package com.vuttr.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_permission")
public class Permission implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @Size(max = 30)
    private String name;
    
    @Column(name = "description", nullable = false)
    @Size(max = 100)
    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "permissions", cascade = CascadeType.ALL)
    private Set<Role> roles = new HashSet<>();
}
    
    
    
    
    
    
    