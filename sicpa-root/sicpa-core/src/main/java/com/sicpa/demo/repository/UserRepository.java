package com.sicpa.demo.repository;

import static com.sicpa.demo.entity.QEmployee.employee;
import static com.sicpa.demo.entity.QUser.user;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.sicpa.demo.entity.User;
import com.sicpa.demo.exception.ExceptionManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class UserRepository extends GenericRepository<User, Long> implements IUserRepository {


    public UserRepository(EntityManager em) {
        super(User.class, em);
    }

    @Override
    public Optional<User> findUserByUsername(String username) throws ExceptionManager {
        try {
            return Optional
                    .ofNullable(queryFactory.selectFrom(user).innerJoin(user.employee, employee).fetchJoin().
                            where(user.username.eq(username).and(user.status.isTrue())).fetchFirst());
        } catch (Exception e) {
            log.error("findUserByUsername: ", e);
            throw new ExceptionManager().new FindingException("Error al buscar el registro");
        }
    }
}
