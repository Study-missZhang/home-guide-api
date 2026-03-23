package com.guide.homeguideapi.service;

import com.guide.homeguideapi.entity.UserInfo;
import java.util.List;
import java.util.Optional;

/**
 * 用户信息 Service 接口，定义用户相关业务逻辑
 *
 * @author zky
 */
public interface UserInfoService {

    /**
     * 保存用户信息
     *
     * @param userInfo 用户信息实体
     * @return 影响行数
     */
    int save(UserInfo userInfo);

    /**
     * 更新用户信息
     *
     * @param userInfo 用户信息实体（需包含 id）
     * @return 影响行数
     */
    int update(UserInfo userInfo);

    /**
     * 根据主键删除用户
     *
     * @param id 主键ID
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 根据主键查询用户
     *
     * @param id 主键ID
     * @return 用户信息（Optional 包装）
     */
    Optional<UserInfo> getById(Long id);

    /**
     * 根据微信 openid 查询用户
     *
     * @param openid 微信用户唯一标识
     * @return 用户信息（Optional 包装）
     */
    Optional<UserInfo> getByOpenid(String openid);

    /**
     * 查询所有用户
     *
     * @return 用户列表
     */
    List<UserInfo> listAll();
}
