package com.target.service.dao.technologies;

import com.target.service.entity.project.tech.Technologies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TechnologiesDao extends JpaRepository<Technologies, Long> {

    List<Technologies> findByProjectId(Long projectId);

}
