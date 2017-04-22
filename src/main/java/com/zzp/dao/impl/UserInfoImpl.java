package com.zzp.dao.impl;

import com.zzp.dao.UserInfoMapper;
import com.zzp.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhuzhengping on 2017/4/22.
 */
@Repository
public class UserInfoImpl implements UserInfoMapper {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void createUser(String tel,String pwd) {
        jdbcTemplate.update("INSERT INTO blog.tp_user(tel,password,nickname,secret) VALUES (?,?,?,'')",tel,pwd,tel);
    }

    @Override
    public UserInfo getUser(Integer id) {
        List<UserInfo> userList = jdbcTemplate.query("select tel,nickname,password FROM blog.tp_user WHERE user_id = ?",new Object[]{id},new BeanPropertyRowMapper(UserInfo.class));
        if(userList != null && userList.size() > 0){
            UserInfo user = userList.get(0);
            return user;
        }else {
            return null;
        }
    }
}
