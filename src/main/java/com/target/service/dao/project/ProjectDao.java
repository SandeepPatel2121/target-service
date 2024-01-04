package com.target.service.dao.project;

import com.target.service.entity.project.Project;
import com.target.service.model.filters.FilterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectDao extends JpaRepository<Project, Long> {

    List<Project> searchProjects(FilterModel filterModel);

    Integer findCount(FilterModel filterModel);

}
