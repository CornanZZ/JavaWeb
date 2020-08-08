package com.cornan.dao;

import com.cornan.entity.User;
import org.apache.ibatis.annotations.Mapper;
//import org.springframework.stereotype.Repository;

@Mapper
//@Repository
public interface UserDAO {
    void save(User user);

    User findByUserName(String username);
}
