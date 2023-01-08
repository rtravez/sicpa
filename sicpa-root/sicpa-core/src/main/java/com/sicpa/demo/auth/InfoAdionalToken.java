package com.sicpa.demo.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.sicpa.demo.entity.User;
import com.sicpa.demo.service.IUserService;

/**
 * <b> Descripcion de la clase, interface o enumeracion. </b>
 *
 * @author renetravez
 * @version $1.0$
 */
@Component
public class InfoAdionalToken implements TokenEnhancer {

    @Autowired
    private IUserService userService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        User user = userService.findByUsername(authentication.getName()).orElse(null);
        if (user != null) {
            Map<String, Object> additionalInformation = new HashMap<>();
            additionalInformation.put("status", user.getStatus());
            additionalInformation.put("name", user.getEmployee().getName());
            additionalInformation.put("surname", user.getEmployee().getSurname());
            additionalInformation.put("email", user.getEmployee().getEmail());
            additionalInformation.put("username", user.getUsername());

            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
        }
        return accessToken;
    }
}
