package com.target.service.controllers.project;

import com.target.service.request.project.ProjectRequest;
import com.target.service.service.project.ProjectService;
import com.target.service.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/project", method = RequestMethod.POST)
    private ResponseEntity<ResponseModel> createProject(@RequestBody ProjectRequest projectRequest) {
        ResponseModel rs = projectService.createProject(projectRequest);
        return new ResponseEntity<>(rs, rs.getHttpStatus());
    }

    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    private ResponseEntity<ResponseModel> findProjects(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                       @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                                       @RequestParam(value = "keyword", defaultValue = "") String keyword) {
        ResponseModel rs = projectService.findProjects(page, limit, keyword);
        return new ResponseEntity<>(rs, rs.getHttpStatus());
    }

    @RequestMapping(value = "/projects/{id}", method = RequestMethod.GET)
    private ResponseEntity<ResponseModel> findById(@PathVariable(value = "id") Long id) {
        ResponseModel rs = projectService.findById(id);
        return new ResponseEntity<>(rs, rs.getHttpStatus());
    }

}
