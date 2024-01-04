package com.target.service.dao.project;

import com.target.service.entity.project.Project;
import com.target.service.model.filters.FilterModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectDaoImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Project> searchProjects(FilterModel filter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Project> criteriaQuery = criteriaBuilder.createQuery(Project.class);
        Root<Project> from = criteriaQuery.from(Project.class);

        Predicate namePre = criteriaBuilder.like(from.get("name"), "%" + filter.getKeyword() + "%");
        Predicate descPre = criteriaBuilder.like(from.get("description"), "%" + filter.getKeyword() + "%");

        Predicate likePredicate = criteriaBuilder.or(namePre, descPre);

        criteriaQuery.select(from).where(likePredicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public Integer findCount(FilterModel filter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Project> from = criteriaQuery.from(Project.class);

        Predicate namePre = criteriaBuilder.like(from.get("name"), "%" + filter.getKeyword() + "%");
        Predicate descPre = criteriaBuilder.like(from.get("description"), "%" + filter.getKeyword() + "%");

        Predicate likePredicate = criteriaBuilder.or(namePre, descPre);

        criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(Project.class))).where(likePredicate);
        return entityManager.createQuery(criteriaQuery).getSingleResult().intValue();
    }

}
