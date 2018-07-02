package com.youmeek.ssm.module.user.service;

import com.youmeek.ssm.module.user.pojo.TbUser;

import java.util.List;

public interface TbUserService {
    List<TbUser> getUsers();

    List<TbUser> getUserByCondition(TbUser tbUser);

    List<TbUser> getUserByPageCondition(int startPage,int pageSize);

    int selectTotalPage();

    int selectTotalPageByUserName(String userName);

    TbUser userLogin(TbUser tbUser);

    int insertUser(TbUser tbUser);

    int deleteUser(int userId);

    int updateUser(TbUser tbUser);

    int updateUserImgByName(String userName,String userImg);
}
