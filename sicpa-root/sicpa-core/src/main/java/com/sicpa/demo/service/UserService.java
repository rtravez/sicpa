package com.sicpa.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sicpa.demo.exception.ExceptionManager;
import com.sicpa.demo.repository.IUserRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * <b> Description de la clase, interface o enumeration. </b>
 *
 * @author renetravez
 * @version $1.0$
 */
@Service
@Slf4j
public class UserService extends GenericService<com.sicpa.demo.entity.User, Long> implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<com.sicpa.demo.entity.User> user = findByUsername(username);

        if (user.isEmpty()) {
            log.error("Error en el Login: no existe el usuario '" + username + "' en el sistema!");
            throw new UsernameNotFoundException("Username: " + username + " no existe en el sistema!");
        }

        List<SimpleGrantedAuthority> authorities = user.get().getRoleUsers().stream().map(role -> new SimpleGrantedAuthority(role.getRole().getName())).collect(Collectors.toList());
        authorities.forEach(authority -> log.info("Role: ".concat(authority.getAuthority())));

        if (authorities.isEmpty()) {
            log.error("Error en el Login: Usuario " + username + " no tiene roles asignados!");
            throw new UsernameNotFoundException("Error en el Login: usuario " + username + " no tiene roles asignados!");
        }
        return new User(user.get().getUsername(), user.get().getPassword(), user.get().getStatus(), true, true, true, authorities);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<com.sicpa.demo.entity.User> findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = ExceptionManager.class)
    public com.sicpa.demo.entity.User save(com.sicpa.demo.entity.User user) throws ExceptionManager {
        try {
            return super.save(user);
        } catch (Exception e) {
            log.error("save: ", e);
            throw new ExceptionManager().new GettingException("Error al guardar el registro");
        }
    }
}
