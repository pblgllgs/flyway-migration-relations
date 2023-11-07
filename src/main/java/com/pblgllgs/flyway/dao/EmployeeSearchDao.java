package com.pblgllgs.flyway.dao;

import com.pblgllgs.flyway.models.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pblgl
 * Created on 06-11-2023
 */

@Repository
@RequiredArgsConstructor
public class EmployeeSearchDao {

    private final EntityManager entityManager;

    public List<Employee> findAllBySimpleQuery(
            String firstname,
            String lastname,
            String email
    ) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);

        Root<Employee> root = criteriaQuery.from(Employee.class);
        Predicate firstnamePredicate = criteriaBuilder.like(root.get("firstname"), "%" + firstname + "%");
        Predicate lastnamePredicate = criteriaBuilder.like(root.get("lastname"), "%" + lastname + "%");
        Predicate emailPredicate = criteriaBuilder.like(root.get("email"), "%" + email + "%");
        Predicate firstnameOrLastnamePredicate = criteriaBuilder.or(firstnamePredicate, lastnamePredicate);
        Predicate andEmailPredicate = criteriaBuilder.and(firstnameOrLastnamePredicate, emailPredicate);
        criteriaQuery.where(andEmailPredicate);
        TypedQuery<Employee> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public List<Employee> findAllByCriteria(
            SearchRequest request
    ) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<Employee> root = criteriaQuery.from(Employee.class);
        if (request.getFirstname() != null) {
            Predicate firstnamePredicate = criteriaBuilder.like(root.get("firstname"), "%" + request.getFirstname() + "%");
            predicates.add(firstnamePredicate);
        }
        if (request.getFirstname() != null) {
            Predicate lastnamePredicate = criteriaBuilder.like(root.get("lastname"), "%" + request.getLastname() + "%");
            predicates.add(lastnamePredicate);
        }
        if (request.getFirstname() != null) {
            Predicate emailPredicate = criteriaBuilder.like(root.get("email"), "%" + request.getEmail() + "%");
            predicates.add(emailPredicate);
        }
        criteriaQuery.where(criteriaBuilder.or(predicates.toArray(new Predicate[0])));
        TypedQuery<Employee> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}

