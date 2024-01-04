package com.target.service.entity.project;

import com.target.service.enums.architecture.Architecture;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "PROJECT")
public class Project {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "ARCHITECTURE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Architecture architecture;

}