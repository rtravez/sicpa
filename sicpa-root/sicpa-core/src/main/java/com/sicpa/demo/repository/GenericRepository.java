package com.sicpa.demo.repository;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.querydsl.jpa.impl.JPAQueryFactory;

@NoRepositoryBean
public abstract class GenericRepository<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements IGenericRepository<T, ID> {

    @PersistenceContext
    protected EntityManager em;
    protected JPAQueryFactory queryFactory;

    protected GenericRepository(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }
}
