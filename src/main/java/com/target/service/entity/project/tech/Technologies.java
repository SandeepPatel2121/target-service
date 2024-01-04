package com.target.service.entity.project.tech;

import com.target.service.entity.project.Project;
import com.target.service.enums.component.WebComponent;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "TECHNOLOGIES")
public class Technologies {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NODE", nullable = false)
    private String node;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "TARGET")
    private String target;

    @Column(name = "RECOMMENDATION")
    private String recommendation;

    @Column(name = "ALTERNATIVE")
    private String alternative;

    @Column(name = "COMPONENT")
    @Enumerated(value = EnumType.STRING)
    private WebComponent component;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "PROJECT_ID", referencedColumnName = "ID", nullable = false)
    private Project project;

}
