package com.target.service.service.project;

import com.target.service.request.project.ProjectRequest;
import com.target.service.util.ResponseModel;

public interface ProjectService {

    ResponseModel createProject(ProjectRequest projectRequest);

    ResponseModel findProjects(Integer page, Integer limit, String keyword);

    ResponseModel findById(Long id);
}
