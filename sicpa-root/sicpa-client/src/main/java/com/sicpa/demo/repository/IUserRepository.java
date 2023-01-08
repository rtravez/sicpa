package com.sicpa.demo.repository;

import java.util.Optional;

import com.sicpa.demo.entity.User;
import com.sicpa.demo.exception.ExceptionManager;

/**
 * <b> Description de la clase, interface o enumeration. </b>
 *
 * @author renetravez
 * @version $1.0$
 */
public interface IUserRepository extends IGenericRepository<User, Long> {

	Optional<User> findUserByUsername(String username) throws ExceptionManager;

}
