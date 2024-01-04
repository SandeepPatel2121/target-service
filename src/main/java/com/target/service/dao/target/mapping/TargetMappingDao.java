package com.target.service.dao.target.mapping;

import com.target.service.entity.target.TargetMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TargetMappingDao extends JpaRepository<TargetMapping, Long> {
    Optional<TargetMapping> findByNode(String node);

    List<TargetMapping> findByNodeIn(List<String> technologies);
}
