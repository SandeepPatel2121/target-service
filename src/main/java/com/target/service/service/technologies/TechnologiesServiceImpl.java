package com.target.service.service.technologies;

import com.target.service.custom.exception.ResourceNotAvailableException;
import com.target.service.dao.project.ProjectDao;
import com.target.service.dao.target.mapping.TargetMappingDao;
import com.target.service.dao.technologies.TechnologiesDao;
import com.target.service.entity.project.Project;
import com.target.service.entity.project.tech.Technologies;
import com.target.service.entity.target.TargetMapping;
import com.target.service.request.project.tech.EditProjectTechRequest;
import com.target.service.request.project.tech.ProjectTechRequest;
import com.target.service.util.ResponseMessage;
import com.target.service.util.ResponseModel;
import com.target.service.util.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TechnologiesServiceImpl implements TechnologiesService {

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private TargetMappingDao targetMappingDao;

    @Autowired
    private TechnologiesDao technologiesDao;

    @Override
    public ResponseModel assignTechnology(ProjectTechRequest projectTechRequest) {
        ResponseModel rs = null;
        try {
            List<Technologies> technologiesList = null;
            Project project = projectDao.findById(projectTechRequest.getProjectId())
                    .orElseThrow(() -> new ResourceNotAvailableException("Project", "ID", projectTechRequest.getProjectId()));

            List<TargetMapping> targetMappingList = targetMappingDao.findByNodeIn(projectTechRequest.getTechnologies());
            if (!targetMappingList.isEmpty()) {
                technologiesList = new ArrayList<>();
                for (String node : projectTechRequest.getTechnologies()) {
                    Technologies technologies = new Technologies();
                    Optional<TargetMapping> targetOpt = targetMappingList.stream().filter(n -> n.getNode().equalsIgnoreCase(node)).findFirst();
                    if (targetOpt.isPresent()) {
                        TargetMapping targetNode = targetOpt.get();

                        technologies.setNode(targetNode.getNode());
                        technologies.setDescription(targetNode.getDescription());
                        technologies.setTarget(targetNode.getTarget());
                        technologies.setRecommendation(targetNode.getRecommendation());
                        technologies.setAlternative(targetNode.getAlternative());
                        technologies.setComponent(targetNode.getComponent());
                        technologies.setProject(project);
                    } else {
                        technologies.setNode(node);
                        technologies.setProject(project);
                    }

                    technologiesList.add(technologies);
                }
                technologiesList = technologiesDao.saveAll(technologiesList);
            }
            rs = ResponseStatus.success(ResponseMessage.PROJECT_CREATED_SUCCESS,
                    technologiesList,
                    HttpStatus.OK);
        } catch (Exception ex) {
            rs = ResponseStatus.error(ResponseMessage.PROJECT_CREATED_SUCCESS,
                    ex.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
        return rs;
    }

    @Override
    public ResponseModel updateProjectTech(EditProjectTechRequest projectTechRequest) {
        ResponseModel rs = null;
        try {
            Technologies technologies = technologiesDao.findById(projectTechRequest.getId())
                    .orElseThrow(() -> new ResourceNotAvailableException("Technologies", "ID", projectTechRequest.getId()));

            technologies.setNode(projectTechRequest.getNode());
            technologies.setDescription(projectTechRequest.getDescription());
            technologies.setTarget(projectTechRequest.getTarget());
            technologies.setRecommendation(projectTechRequest.getRecommendation());
            technologies.setAlternative(projectTechRequest.getAlternative());
            technologies.setComponent(projectTechRequest.getComponent());

            rs = ResponseStatus.success(ResponseMessage.TECH_CREATED_SUCCESS,
                    technologiesDao.save(technologies),
                    HttpStatus.OK);
        } catch (Exception ex) {
            rs = ResponseStatus.error(ResponseMessage.TECH_CREATED_FAILED,
                    ex.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
        return rs;
    }
}
