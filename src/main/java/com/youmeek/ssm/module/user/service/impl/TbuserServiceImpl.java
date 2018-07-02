package com.youmeek.ssm.module.user.service.impl;

import com.youmeek.ssm.module.user.mapper.TbUserMapper;
import com.youmeek.ssm.module.user.pojo.TbUser;
import com.youmeek.ssm.module.user.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbuserServiceImpl implements TbUserService {

    @Autowired
    TbUserMapper tbUserMapper;

    @Override
    public List<TbUser> getUsers() {
        return tbUserMapper.selectByCondition(null);
    }

    @Override
    public List<TbUser> getUserByCondition(TbUser tbUser) {
        return tbUserMapper.selectByCondition(tbUser);
    }

    @Override
    public List<TbUser> getUserByPageCondition(int startPage, int pageSize) {
        return tbUserMapper.selectByPageCondition(startPage,pageSize);
    }

    @Override
    public int selectTotalPage() {

        return tbUserMapper.selectTotalPage();
    }

    @Override
    public int selectTotalPageByUserName(String userName) {
        return tbUserMapper.selectTotalPageByUserName(userName);
    }

    @Override
    public TbUser userLogin(TbUser tbUser) {
        List<TbUser> users = tbUserMapper.selectByCondition(tbUser);
        if(!users.isEmpty()){

            return tbUserMapper.selectByCondition(tbUser).get(0);
        }else{
            System.out.println("null!");
            return null;
        }

    }

    @Override
    public int insertUser(TbUser tbUser) {
        return tbUserMapper.insert(tbUser);
    }

    @Override
    public int deleteUser(int userId) {
        return tbUserMapper.deleteByPrimaryKey((long)userId);
    }

    @Override
    public int updateUser(TbUser tbUser) {
        return tbUserMapper.updateByPrimaryKeySelective(tbUser);
    }

    @Override
    public int updateUserImgByName(String userName, String userImg) {
        return tbUserMapper.updateImgByName(userName,userImg);
    }
}
