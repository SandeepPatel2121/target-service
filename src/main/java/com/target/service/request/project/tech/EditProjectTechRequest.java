package com.target.service.request.project.tech;

import com.target.service.enums.component.WebComponent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditProjectTechRequest {

    private Long id;
    private String node;
    private String description;
    private String target;
    private String recommendation;
    private String alternative;
    private WebComponent component;

}
