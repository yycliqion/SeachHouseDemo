package com.weshep.house.entity;


import lombok.Data;

import javax.persistence.*;

/**
 * @author yycli
 */
@Entity
@Table(name = "role")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    private String name;
}
