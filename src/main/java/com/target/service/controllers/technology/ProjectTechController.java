package com.target.service.controllers.technology;

import com.target.service.request.project.tech.EditProjectTechRequest;
import com.target.service.request.project.tech.ProjectTechRequest;
import com.target.service.service.technologies.TechnologiesService;
import com.target.service.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "project")
public class ProjectTechController {

    @Autowired
    private TechnologiesService technologiesService;

    @RequestMapping(value = "/tech", method = RequestMethod.POST)
    private ResponseEntity<ResponseModel> createProject(@RequestBody ProjectTechRequest projectTechRequest) {
        ResponseModel rs = technologiesService.assignTechnology(projectTechRequest);
        return new ResponseEntity<>(rs, rs.getHttpStatus());
    }

    @RequestMapping(value = "/technologies/{id}", method = RequestMethod.PUT)
    private ResponseEntity<ResponseModel> updateProjectTech(@RequestBody EditProjectTechRequest projectTechRequest) {
        ResponseModel rs = technologiesService.updateProjectTech(projectTechRequest);
        return new ResponseEntity<>(rs, rs.getHttpStatus());
    }

}
