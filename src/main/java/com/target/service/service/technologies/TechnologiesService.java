package com.target.service.service.technologies;

import com.target.service.request.project.tech.EditProjectTechRequest;
import com.target.service.request.project.tech.ProjectTechRequest;
import com.target.service.util.ResponseModel;

public interface TechnologiesService {

    ResponseModel assignTechnology(ProjectTechRequest projectTechRequest);

    ResponseModel updateProjectTech(EditProjectTechRequest projectTechRequest);
}
