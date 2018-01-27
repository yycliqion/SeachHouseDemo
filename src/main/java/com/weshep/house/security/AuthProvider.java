package com.weshep.house.security;


import com.weshep.house.service.user.IUserServier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import com.weshep.house.entity.User;


/**
 * 自定义认证实现
 *
 * @author yycli
 */
public class AuthProvider implements AuthenticationProvider {
    @Autowired
    private IUserServier userServier;

    private final Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String inputPassword = (String) authentication.getCredentials();

        //验证
        User user = userServier.findUSerByName(userName);

        if (user == null) {
            throw new AuthenticationCredentialsNotFoundException("Auth Error");
        }
        if(this.passwordEncoder.isPasswordValid(user.getPassword(), inputPassword,user.getId())){
            return new UsernamePasswordAuthenticationToken(null,user.getAuthorities());

        }
        throw new BadCredentialsException("Auth Error");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
