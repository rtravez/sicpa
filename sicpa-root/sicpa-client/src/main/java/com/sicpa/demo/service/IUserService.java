package com.sicpa.demo.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.sicpa.demo.entity.User;
import com.sicpa.demo.exception.ExceptionManager;

/**
 * <b> Description de la clase, interface o enumeration. </b>
 * 
 * @author renetravez
 * @version $1.0$
 */
public interface IUserService extends IGenericService<User, Long>,UserDetailsService {

	Optional<User> findByUsername(String username) throws ExceptionManager;	
}
