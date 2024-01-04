package com.target.service.request.project.tech;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProjectTechRequest {

    private Long projectId;
    private List<String> technologies;

}
