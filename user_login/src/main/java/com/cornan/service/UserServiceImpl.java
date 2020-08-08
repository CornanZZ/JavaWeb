package com.cornan.service;

import com.cornan.dao.UserDAO;
import com.cornan.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Date;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO userDAO;

    @Override
    public User login(User user){
        User userDB = userDAO.findByUserName(user.getUsername());
        if(!ObjectUtils.isEmpty(userDB)){
            if(userDB.getPassword().equals(user.getPassword())){
                return userDB;
            }
            else{
                throw new RuntimeException("密码输入不正确！");
            }
        }
        else{
            throw new RuntimeException("该用户不存在！");
        }
    }

    @Override
    public void register(User user) {
        User userDB = userDAO.findByUserName(user.getUsername());
        if(userDB == null){
            user.setRegisterTime(new Date());
            userDAO.save(user);
        }
        else{
            throw new RuntimeException("该用户已存在！");
        }
    }
}
