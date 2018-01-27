package com.weshep.house.service.user.impl;

import com.weshep.house.entity.Role;
import com.weshep.house.repository.RoleRepository;
import com.weshep.house.repository.UserRepository;
import com.weshep.house.entity.User;
import com.weshep.house.service.user.IUserServier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yycli
 * @title 自定认证策略实现
 */
@Service
public class IUserServiceImpl implements IUserServier{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User findUSerByName(String userName) {
        User user = userRepository.findByName(userName);
        if (user == null) {
            return null;
        }

        List<Role> roles = roleRepository.findRoleByUserId(user.getId());
        if (roles == null || roles.isEmpty()) {
            throw new DisabledException("权限非法");
        }

        /** 目标认定 */
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities
                .add(new SimpleGrantedAuthority("ROLE" + role.getName())));

        user.setAuthorities(authorities);
        return user;

    }
}
