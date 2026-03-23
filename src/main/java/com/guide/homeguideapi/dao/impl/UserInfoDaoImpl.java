package com.guide.homeguideapi.dao.impl;

import com.guide.homeguideapi.dao.UserInfoDao;
import com.guide.homeguideapi.entity.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 用户信息 DAO 实现类，基于 JdbcTemplate 操作 user_info 表
 *
 * @author zky
 */
@Repository
@RequiredArgsConstructor
public class UserInfoDaoImpl implements UserInfoDao {

    /** JdbcTemplate，用于执行 SQL 操作 */
    private final JdbcTemplate jdbcTemplate;

    /** 结果集映射器，将查询结果自动映射为 UserInfo 对象 */
    private static final BeanPropertyRowMapper<UserInfo> ROW_MAPPER =
            BeanPropertyRowMapper.newInstance(UserInfo.class);

    /**
     * 新增用户信息
     * 插入 openid、phone、home_latitude、home_longitude、home_address 字段
     *
     * @param u 用户信息实体
     * @return 影响行数
     */
    @Override
    public int insert(UserInfo u) {
        String sql = "INSERT INTO user_info (openid, phone, home_latitude, home_longitude, home_address) " +
                     "VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, u.getOpenid(), u.getPhone(),
                u.getHomeLatitude(), u.getHomeLongitude(), u.getHomeAddress());
    }

    /**
     * 根据 id 更新用户信息
     * 可更新 phone、home_latitude、home_longitude、home_address 字段
     *
     * @param u 用户信息实体（需包含 id）
     * @return 影响行数
     */
    @Override
    public int update(UserInfo u) {
        String sql = "UPDATE user_info SET phone=?, home_latitude=?, home_longitude=?, home_address=? WHERE id=?";
        return jdbcTemplate.update(sql, u.getPhone(),
                u.getHomeLatitude(), u.getHomeLongitude(), u.getHomeAddress(), u.getId());
    }

    /**
     * 根据主键删除用户
     *
     * @param id 主键ID
     * @return 影响行数
     */
    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM user_info WHERE id=?", id);
    }

    /**
     * 根据主键查询用户
     *
     * @param id 主键ID
     * @return 用户信息（Optional 包装）
     */
    @Override
    public Optional<UserInfo> findById(Long id) {
        List<UserInfo> list = jdbcTemplate.query("SELECT * FROM user_info WHERE id=?", ROW_MAPPER, id);
        return list.stream().findFirst();
    }

    /**
     * 根据微信 openid 查询用户
     *
     * @param openid 微信用户唯一标识
     * @return 用户信息（Optional 包装）
     */
    @Override
    public Optional<UserInfo> findByOpenid(String openid) {
        List<UserInfo> list = jdbcTemplate.query("SELECT * FROM user_info WHERE openid=?", ROW_MAPPER, openid);
        return list.stream().findFirst();
    }

    /**
     * 查询所有用户
     *
     * @return 用户列表
     */
    @Override
    public List<UserInfo> findAll() {
        return jdbcTemplate.query("SELECT * FROM user_info", ROW_MAPPER);
    }
}
