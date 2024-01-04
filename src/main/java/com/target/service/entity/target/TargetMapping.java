package com.target.service.entity.target;

import com.target.service.enums.component.WebComponent;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "NODE_TARGET_MAPPING")
public class TargetMapping {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NODE", nullable = false, unique = true)
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

}