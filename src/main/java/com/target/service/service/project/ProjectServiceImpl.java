package com.target.service.service.project;

import com.target.service.custom.exception.ResourceNotAvailableException;
import com.target.service.dao.project.ProjectDao;
import com.target.service.entity.project.Project;
import com.target.service.enums.architecture.Architecture;
import com.target.service.model.filters.FilterModel;
import com.target.service.model.filters.FilterResponse;
import com.target.service.request.project.ProjectRequest;
import com.target.service.util.ResponseMessage;
import com.target.service.util.ResponseModel;
import com.target.service.util.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDao projectDao;

    @Override
    public ResponseModel createProject(ProjectRequest projectRequest) {
        ResponseModel rs = null;
        try {
            Project project = new Project();

            project.setName(projectRequest.getName());
            project.setDescription(projectRequest.getDescription());
            project.setArchitecture(Architecture.valueOf(projectRequest.getArchitecture()));

            rs = ResponseStatus.success(ResponseMessage.PROJECT_CREATED_SUCCESS,
                    projectDao.save(project),
                    HttpStatus.OK);
        } catch (Exception ex) {
            rs = ResponseStatus.error(ResponseMessage.PROJECT_CREATED_SUCCESS,
                    ex.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
        return rs;
    }

    @Override
    public ResponseModel findProjects(Integer page, Integer limit, String keyword) {
        ResponseModel rs = null;
        try {
            FilterResponse filterResponse = new FilterResponse();
            filterResponse.setPage(page);
            filterResponse.setLimit(limit);

            List<Project> projectList = projectDao.searchProjects(new FilterModel(page, limit, keyword));
            filterResponse.setData(projectList);

            Integer total = projectDao.findCount(new FilterModel(page, limit, keyword));
            filterResponse.setTotal(total);

            rs = ResponseStatus.success(ResponseMessage.PROJECTS_LOADED_SUCCESS,
                    filterResponse,
                    HttpStatus.OK);
        } catch (Exception ex) {
            rs = ResponseStatus.error(ResponseMessage.PROJECTS_LOADED_FAILED,
                    ex.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
        return rs;
    }

    @Override
    public ResponseModel findById(Long id) {
        ResponseModel rs = null;
        try {
            Project project = projectDao.findById(id)
                    .orElseThrow(() -> new ResourceNotAvailableException("Project", "Id", id));

            rs = ResponseStatus.success(ResponseMessage.PROJECT_FOUND_SUCCESS,
                    project,
                    HttpStatus.OK);
        } catch (ResourceNotAvailableException ex) {
            rs = ResponseStatus.error(ResponseMessage.PROJECT_NOT_FOUND,
                    ex.getMessage(),
                    HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            rs = ResponseStatus.error(ResponseMessage.PROJECTS_LOADED_FAILED,
                    ex.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
        return rs;
    }
}
