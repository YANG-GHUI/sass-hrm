package com.ygh.ihrm.common.service;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author YangGh
 * @version 1.0
 */
public class BaseService<T> {

    protected Specification<T> getSpec(String companyId) {
        return (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("companyId").as(String.class), companyId);
    }
}
