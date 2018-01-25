package com.weshep.house.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author yycli
 * @title user数据实体类
 */

@Entity
/** 表映射 */
@Table(name = "user")
@Data
public class User {
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
}
