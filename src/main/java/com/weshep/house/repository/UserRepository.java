package com.weshep.house.repository;

import com.weshep.house.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @title user jpa操作类
 * @author yycli
 */
public interface UserRepository extends CrudRepository<User, Long> {

    User findByName(String userName);
}
