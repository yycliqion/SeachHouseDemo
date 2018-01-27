package com.weshep.house.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author yycli
 * @title user数据实体类
 */

@Entity
/** 表映射 */
@Table(name = "user")
@Data
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    private String email;

    /**
     * 字段映射
     */
    @Column(name = "phone_number")
    private String phoneNumber;

    private int status;

    @Column(name = "create_time")
    private Date createTime;


    @Column(name = "last_update_time")
    private Date lastUpdateTime;

    private String avatar;

    public User() {
    }

    @Transient
    private List<GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
