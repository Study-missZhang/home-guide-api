package com.guide.homeguideapi.service.impl;

import com.guide.homeguideapi.dao.UserInfoDao;
import com.guide.homeguideapi.entity.UserInfo;
import com.guide.homeguideapi.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 用户信息 Service 实现类
 *
 * @author zky
 */
@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

    /** 用户信息 DAO */
    private final UserInfoDao userInfoDao;

    /**
     * 保存用户信息
     *
     * @param userInfo 用户信息实体
     * @return 影响行数
     */
    @Override
    public int save(UserInfo userInfo) {
        return userInfoDao.insert(userInfo);
    }

    /**
     * 更新用户信息
     *
     * @param userInfo 用户信息实体（需包含 id）
     * @return 影响行数
     */
    @Override
    public int update(UserInfo userInfo) {
        return userInfoDao.update(userInfo);
    }

    /**
     * 根据主键删除用户
     *
     * @param id 主键ID
     * @return 影响行数
     */
    @Override
    public int deleteById(Long id) {
        return userInfoDao.deleteById(id);
    }

    /**
     * 根据主键查询用户
     *
     * @param id 主键ID
     * @return 用户信息（Optional 包装）
     */
    @Override
    public Optional<UserInfo> getById(Long id) {
        return userInfoDao.findById(id);
    }

    /**
     * 根据微信 openid 查询用户
     *
     * @param openid 微信用户唯一标识
     * @return 用户信息（Optional 包装）
     */
    @Override
    public Optional<UserInfo> getByOpenid(String openid) {
        return userInfoDao.findByOpenid(openid);
    }

    /**
     * 查询所有用户
     *
     * @return 用户列表
     */
    @Override
    public List<UserInfo> listAll() {
        return userInfoDao.findAll();
    }
}
